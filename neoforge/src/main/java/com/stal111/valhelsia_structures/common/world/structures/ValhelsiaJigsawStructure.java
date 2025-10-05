package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProvider;
import com.stal111.valhelsia_structures.core.init.world.ModStructureTypes;
import com.stal111.valhelsia_structures.utils.StructureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
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
public class ValhelsiaJigsawStructure extends Structure {

    public static final MapCodec<ValhelsiaJigsawStructure> CODEC = RecordCodecBuilder.<ValhelsiaJigsawStructure>mapCodec(instance -> instance.group(
            settingsCodec(instance),
            ValhelsiaStructureSettings.CODEC.forGetter(structure -> structure.settings),
            StartPoolDecider.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPoolDecider),
            Codec.intRange(0, 7).fieldOf("size").forGetter(structure -> structure.maxDepth),
            StructureHeightProvider.CODEC.optionalFieldOf("start_height").forGetter(structure -> Optional.ofNullable(structure.startHeight)),
            Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> Optional.ofNullable(structure.projectStartToHeightmap)),
            JigsawStructure.MaxDistance.CODEC.fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
    ).apply(instance, (structureSettings, valhelsiaStructureSettings, startPoolDecider, integer, structureHeightProvider, types, integer2) -> {
        return new ValhelsiaJigsawStructure(structureSettings, valhelsiaStructureSettings, startPoolDecider, integer, structureHeightProvider.orElse(null), types.orElse(null), integer2);
    })).flatXmap(verifyRange(), verifyRange());

    private final ValhelsiaStructureSettings settings;
    private final StartPoolDecider startPoolDecider;

    private final int maxDepth;
    @Nullable
    private final StructureHeightProvider startHeight;
    @Nullable
    private final Heightmap.Types projectStartToHeightmap;
    private final JigsawStructure.MaxDistance maxDistanceFromCenter;

    public ValhelsiaJigsawStructure(StructureSettings settings, ValhelsiaStructureSettings valhelsiaStructureSettings, StartPoolDecider startPoolDecider, int maxDepth, @Nullable StructureHeightProvider startHeight, @Nullable Heightmap.Types projectStartToHeightmap, JigsawStructure.MaxDistance maxDistanceFromCenter) {
        super(settings);
        this.settings = valhelsiaStructureSettings;
        this.startPoolDecider = startPoolDecider;
        this.maxDepth = maxDepth;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }

    private static Function<ValhelsiaJigsawStructure, DataResult<ValhelsiaJigsawStructure>> verifyRange() {
        return (structure) -> {
            int i = switch (structure.terrainAdaptation()) {
                case NONE -> 0;
                case BURY, BEARD_THIN, BEARD_BOX, ENCAPSULATE -> 12;
            };
            return structure.maxDistanceFromCenter.horizontal() + i > 128 ? DataResult.error(() -> "Structure size including terrain adaptation must not exceed 128") : DataResult.success(structure);
        };
    }

    private boolean canGenerate(Structure.GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos pos = chunkPos.getWorldPosition();
        ChunkGenerator generator = context.chunkGenerator();
        WorldgenRandom random = context.random();

        // Check if the surface is flat
        if (this.step() == GenerationStep.Decoration.FLUID_SPRINGS && !StructureUtils.isSurfaceFlat(context, 15, this.settings.flatnessDelta())) {
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
                this.maxDistanceFromCenter,
                PoolAliasLookup.create(List.of(), pos, context.seed()),
                DimensionPadding.ZERO,
                this.settings.liquidSettings()
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
}