package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProvider;
import com.stal111.valhelsia_structures.core.init.world.ModStructureTypes;
import com.stal111.valhelsia_structures.core.init.world.ModStructures;
import com.stal111.valhelsia_structures.utils.StructureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.common.world.structure.SimpleValhelsiaStructure;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;

/**
 * Base class for all structures added by the mod. Provides a few additional placement checks and configuration options over the {@link net.minecraft.world.level.levelgen.structure.structures.JigsawStructure}.
 *
 * @author Valhelsia Team
 * @since 2020-03-22
 */
public class LegacyValhelsiaJigsawStructure extends SimpleValhelsiaStructure {

    public static final Codec<LegacyValhelsiaJigsawStructure> CODEC = RecordCodecBuilder.<LegacyValhelsiaJigsawStructure>mapCodec((instance) -> {
        return instance.group(settingsCodec(instance), Codec.STRING.fieldOf("name").forGetter(SimpleValhelsiaStructure::getName), StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter((structure) -> {
            return structure.startPool;
        }), ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter((structure) -> {
            return structure.startJigsawName;
        }), Codec.intRange(0, 7).fieldOf("size").forGetter((structure) -> {
            return structure.maxDepth;
        }), StructureHeightProvider.CODEC.fieldOf("start_height").forGetter((structure) -> {
            return structure.startHeight;
        }), Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter((structure) -> {
            return structure.projectStartToHeightmap;
        }), Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter((structure) -> {
            return structure.maxDistanceFromCenter;
        }), Codec.list(StructureSet.CODEC).fieldOf("requires_distance_to").forGetter(structure -> {
            return structure.requiresDistanceTo;
        })).apply(instance, LegacyValhelsiaJigsawStructure::new);
    }).flatXmap(verifyRange(), verifyRange()).codec();

    private static Function<LegacyValhelsiaJigsawStructure, DataResult<LegacyValhelsiaJigsawStructure>> verifyRange() {
        return (structure) -> {
            int i = switch (structure.terrainAdaptation()) {
                case NONE -> 0;
                case BURY, BEARD_THIN, BEARD_BOX -> 12;
            };
            return structure.maxDistanceFromCenter + i > 128 ? DataResult.error("Structure size including terrain adaptation must not exceed 128") : DataResult.success(structure);
        };
    }

  //  private final ValhelsiaStructureSettings structureSettings;
  //  private final ResourceKey<StructureSet> structureSetResourceKey;

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final int maxDepth;
    private final StructureHeightProvider startHeight;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final int maxDistanceFromCenter;
    private final List<Holder<StructureSet>> requiresDistanceTo;

/*    public AbstractValhelsiaStructure(Structure.StructureSettings settings, String name, Holder<StructureTemplatePool> startPool, int size, ValhelsiaStructureSettings structureSettings) {
        super(settings, name);
        this.structureSettings = structureSettings;
        this.structureSetResourceKey = ResourceKey.create(Registry.STRUCTURE_SET_REGISTRY, new ResourceLocation(ValhelsiaStructures.MOD_ID, name + "s"));
    }*/

    public LegacyValhelsiaJigsawStructure(StructureSettings settings, String name, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int maxDepth, StructureHeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter, List<Holder<StructureSet>> requiresDistanceTo) {
        super(settings, name);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.maxDepth = maxDepth;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
        this.requiresDistanceTo = requiresDistanceTo;
    }

    public LegacyValhelsiaJigsawStructure(StructureSettings p_227620_, String name, Holder<StructureTemplatePool> startPool, int p_227622_, StructureHeightProvider p_227623_, Heightmap.Types p_227625_, RegistryObject<StructureSet>... requiresDistanceTo) {
        this(p_227620_, name, startPool, Optional.empty(), p_227622_, p_227623_, Optional.of(p_227625_), 80, Arrays.stream(requiresDistanceTo).map(registryObject -> registryObject.getHolder().get()).toList());
    }

    public LegacyValhelsiaJigsawStructure(StructureSettings p_227614_, String name, Holder<StructureTemplatePool> startPool, int p_227616_, StructureHeightProvider p_227617_, RegistryObject<StructureSet>... requiresDistanceTo) {
        this(p_227614_, name, startPool, Optional.empty(), p_227616_, p_227617_, Optional.empty(), 80, Arrays.stream(requiresDistanceTo).map(registryObject -> registryObject.getHolder().get()).toList());
    }

    private boolean canGenerate(GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos pos = chunkPos.getWorldPosition();
        ChunkGenerator generator = context.chunkGenerator();
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));

        // Check if the surface is flat
        if (this.checkSurface() && !StructureUtils.isSurfaceFlat(context, this.getStructureSettings().size().get())) {
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

        // Check for other structures
        if (StructureUtils.isStructureInDistance(generator, context.randomState(), context.seed(), context.chunkPos(), this.requiresDistanceTo)) {
            return false;
        }

        // Check the spawn chance
        random.setSeed((long) (chunkPos.x >> 4 ^ chunkPos.z >> 4 << 4) ^ context.seed());

        return random.nextDouble() < this.getStructureSettings().spawnChance().get();
    }

    @Nonnull
    @Override
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();

        if (!this.canGenerate(context)) {
            return Optional.empty();
        }

        BlockPos pos = chunkPos.getWorldPosition();

        OptionalInt y = this.startHeight.sample(pos, context, Heightmap.Types.WORLD_SURFACE);

        if (y.isEmpty()) {
            return Optional.empty();
        }

        return JigsawPlacement.addPieces(
                context,
                this.startPool,
                this.startJigsawName,
                this.maxDepth,
                pos.atY(y.getAsInt()),
                true,
                this.projectStartToHeightmap,
                this.maxDistanceFromCenter
        );
    }

    @Nonnull
    @Override
    public StructureType<?> type() {
        return ModStructureTypes.LEGACY_VALHELSIA_JIGSAW_STRUCTURE.get();
    }

    public ValhelsiaStructureSettings getStructureSettings() {
        return ModStructures.STRUCTURE_SETTINGS_MAP.get(this.getName());
    }

    public boolean checkSurface() {
        return this.getStructureSettings().size() != null;
    }

    public boolean canGenerateOnWater() {
        return this.step() == GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
    }

    public boolean hasMargin() {
        return this.getStructureSettings().margin().get() != 0;
    }

    public int getMargin() {
        return this.getStructureSettings().margin().get();
    }

    @Nonnull
    @Override
    public BoundingBox adjustBoundingBox(@Nonnull BoundingBox boundingBox) {
        return this.hasMargin() ? boundingBox.inflatedBy(this.getMargin()) : boundingBox;
    }
}