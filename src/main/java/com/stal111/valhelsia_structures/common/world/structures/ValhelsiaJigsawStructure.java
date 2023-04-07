package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProvider;
import com.stal111.valhelsia_structures.core.init.world.ModStructureTypes;
import com.stal111.valhelsia_structures.utils.StartPoolKeySet;
import com.stal111.valhelsia_structures.utils.StructureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;

/**
 * Base class for all structures added by the mod. Provides a few additional placement checks and configuration options over the {@link net.minecraft.world.level.levelgen.structure.structures.JigsawStructure}.
 *
 * @author Valhelsia Team
 * @since 2020-03-22
 */
public class ValhelsiaJigsawStructure extends Structure {

    public static final Codec<ValhelsiaJigsawStructure> CODEC = RecordCodecBuilder.<ValhelsiaJigsawStructure>mapCodec((instance) -> instance.group(
            settingsCodec(instance),
            ValhelsiaStructureSettings.CODEC.forGetter(structure -> structure.settings),
            StartPoolDecider.CODEC.fieldOf("start_pool").forGetter(structure -> {
                return structure.startPoolDecider;
            }),
            Codec.intRange(0, 7).fieldOf("size").forGetter(structure -> structure.maxDepth),
            StructureHeightProvider.CODEC.optionalFieldOf("start_height").forGetter(structure -> Optional.ofNullable(structure.startHeight)),
            Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> Optional.ofNullable(structure.projectStartToHeightmap)),
            Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
    ).apply(instance, (structureSettings, valhelsiaStructureSettings, startPoolDecider, integer, structureHeightProvider, types, integer2) -> {
        return new ValhelsiaJigsawStructure(structureSettings, valhelsiaStructureSettings, startPoolDecider, integer, structureHeightProvider.orElse(null), types.orElse(null), integer2);
    })).flatXmap(verifyRange(), verifyRange()).codec();

    private final ValhelsiaStructureSettings settings;
    private final StartPoolDecider startPoolDecider;

    private final int maxDepth;
    @Nullable
    private final StructureHeightProvider startHeight;
    @Nullable
    private final Heightmap.Types projectStartToHeightmap;
    private final int maxDistanceFromCenter;

    public ValhelsiaJigsawStructure(StructureSettings settings, ValhelsiaStructureSettings valhelsiaStructureSettings, StartPoolDecider startPoolDecider, int maxDepth, @Nullable StructureHeightProvider startHeight, @Nullable Heightmap.Types projectStartToHeightmap, int maxDistanceFromCenter) {
        super(settings);
        this.settings = valhelsiaStructureSettings;
        this.startPoolDecider = startPoolDecider;
        this.maxDepth = maxDepth;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }

    public static ValhelsiaJigsawStructure.Builder builder(BootstapContext<Structure> context, HolderSet<Biome> biomeTagKey, GenerationStep.Decoration step, TerrainAdjustment terrainAdjustment, StartPoolKeySet startPool) {
        return new Builder(context, biomeTagKey, step, terrainAdjustment, startPool);
    }

    private static Function<ValhelsiaJigsawStructure, DataResult<ValhelsiaJigsawStructure>> verifyRange() {
        return (structure) -> {
            int i = switch (structure.terrainAdaptation()) {
                case NONE -> 0;
                case BURY, BEARD_THIN, BEARD_BOX -> 12;
            };
            return structure.maxDistanceFromCenter + i > 128 ? DataResult.error(() -> "Structure size including terrain adaptation must not exceed 128") : DataResult.success(structure);
        };
    }

    private boolean canGenerate(Structure.GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos pos = chunkPos.getWorldPosition();
        ChunkGenerator generator = context.chunkGenerator();
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));

        // Check if the surface is flat
        if (this.step() == GenerationStep.Decoration.SURFACE_STRUCTURES && !StructureUtils.isSurfaceFlat(context, 15)) {
            return false;
        }

        // Check for water
        if (!this.canGenerateOnWater()) {
            int landHeight = generator.getFirstOccupiedHeight(pos.getX(), pos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());

            NoiseColumn columnOfBlocks = generator.getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor(), context.randomState());
            BlockState topBlock = columnOfBlocks.getBlock(landHeight);

            if (!topBlock.getFluidState().isEmpty()) {
                return false;
            }
        }

        // Check the spawn chance
        random.setSeed((long) (chunkPos.x >> 4 ^ chunkPos.z >> 4 << 4) ^ context.seed());

        return random.nextDouble() < this.getStructureSettings().spawnChance();
    }


    @Nonnull
    @Override
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();

        if (!this.canGenerate(context)) {
            return Optional.empty();
        }

        BlockPos pos = chunkPos.getWorldPosition();

        OptionalInt y = OptionalInt.of(0);

        if (this.startHeight != null) {
            y = this.startHeight.sample(pos, context, Heightmap.Types.WORLD_SURFACE);
        }

        if (y.isEmpty()) {
            return Optional.empty();
        }

        return JigsawPlacement.addPieces(
                context,
                this.startPoolDecider.decide(),
                Optional.empty(),
                this.maxDepth,
                pos.atY(y.getAsInt()),
                true,
                Optional.ofNullable(this.projectStartToHeightmap),
                this.maxDistanceFromCenter
        );
    }

    @Nonnull
    @Override
    public StructureType<?> type() {
        return ModStructureTypes.VALHELSIA_JIGSAW_STRUCTURE.get();
    }

    public ValhelsiaStructureSettings getStructureSettings() {
        return this.settings;
    }

    public boolean canGenerateOnWater() {
        return this.step() == GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
    }

    @Nonnull
    @Override
    public BoundingBox adjustBoundingBox(@Nonnull BoundingBox boundingBox) {
        return boundingBox.inflatedBy(this.settings.customMargin());
    }

    public boolean hasIndividualTerrainAdjustment() {
        return this.settings.individualTerrainAdjustment();
    }

    public static class Builder {

        private final BootstapContext<Structure> context;
        private final HolderSet<Biome> biomeHolderSet;

        private final GenerationStep.Decoration step;
        private final TerrainAdjustment terrainAdjustment;
        private final StartPoolKeySet startPool;

        private final Map<MobCategory, StructureSpawnOverride> spawnOverrides = new HashMap<>();

        private int maxDepth = 7;
        @Nullable
        private Heightmap.Types projectStartToHeightmap = Heightmap.Types.WORLD_SURFACE_WG;
        @Nullable
        private StructureHeightProvider heightProvider = null;
        private int maxDistanceFromCenter = 80;
        @Nullable
        private Double spawnChance = null;
        @Nullable
        private Integer customMargin = null;
        @Nullable
        private Boolean individualTerrainAdjustment = null;

        private Builder(BootstapContext<Structure> context, HolderSet<Biome> biomeHolderSet, GenerationStep.Decoration step, TerrainAdjustment terrainAdjustment, StartPoolKeySet startPool) {
            this.context = context;
            this.biomeHolderSet = biomeHolderSet;
            this.step = step;
            this.terrainAdjustment = terrainAdjustment;
            this.startPool = startPool;
        }

        public Builder addSpawnOverride(MobCategory category, StructureSpawnOverride override) {
            this.spawnOverrides.put(category, override);

            return this;
        }

        public Builder maxDepth(int maxDepth) {
            this.maxDepth = maxDepth;

            return this;
        }

        public Builder startHeight(StructureHeightProvider heightProvider) {
            this.projectStartToHeightmap = null;
            this.heightProvider = heightProvider;

            return this;
        }

        public Builder heightmap(Heightmap.Types projectStartToHeightmap) {
            this.projectStartToHeightmap = projectStartToHeightmap;
            this.heightProvider = null;

            return this;
        }

        public Builder individualTerrainAdjustment() {
            this.individualTerrainAdjustment = true;

            return this;
        }

        public Builder chance(double spawnChance) {
            this.spawnChance = spawnChance;

            return this;
        }

        public Builder margin(int customMargin) {
            this.customMargin = customMargin;

            return this;
        }

        public ValhelsiaJigsawStructure build() {
            StructureSettings settings = new StructureSettings(biomeHolderSet, this.spawnOverrides, this.step, this.terrainAdjustment);
            ValhelsiaStructureSettings valhelsiaStructureSettings = new ValhelsiaStructureSettings(this.spawnChance, this.customMargin, this.individualTerrainAdjustment);

            return new ValhelsiaJigsawStructure(settings, valhelsiaStructureSettings, StartPoolDecider.of(this.context.lookup(Registries.TEMPLATE_POOL), this.startPool), this.maxDepth, this.heightProvider, this.projectStartToHeightmap, this.maxDistanceFromCenter);
        }
    }
}