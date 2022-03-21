package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModStructures;
import com.stal111.valhelsia_structures.utils.StructureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.valhelsia.valhelsia_core.common.world.ValhelsiaJigsawStructure;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Abstract Valhelsia Structure <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.world.structures.AbstractValhelsiaStructure
 * <p>
 * Serves as a way to reduce duplicate code in structures - this has sensible defaults for most surface structures
 * but can be overridden if needed.
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.1.0
 * @since 2020-03-22
 */
public abstract class AbstractValhelsiaStructure extends ValhelsiaJigsawStructure<JigsawConfiguration> {

    private final StructureSettings structureSettings;
    private final ResourceKey<StructureSet> structureSetResourceKey;

    public AbstractValhelsiaStructure(Codec<JigsawConfiguration> codec, String name, Predicate<PieceGeneratorSupplier.Context<JigsawConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<JigsawConfiguration>, Optional<PieceGenerator<JigsawConfiguration>>> pieceCreationPredicate, StructureSettings structureSettings) {
        super(codec, name, locationCheckPredicate, pieceCreationPredicate);
        this.structureSettings = structureSettings;
        this.structureSetResourceKey = ResourceKey.create(Registry.STRUCTURE_SET_REGISTRY, new ResourceLocation(ValhelsiaStructures.MOD_ID, name + "s"));
    }

    protected boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos pos = chunkPos.getWorldPosition();
        ChunkGenerator generator = context.chunkGenerator();
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));

        // Check if the surface is flat
        if (this.checkSurface() && !StructureUtils.isSurfaceFlat(context, this.getSize())) {
            return false;
        }

        // Check for water
        if (!this.canGenerateOnWater()) {
            int landHeight = generator.getFirstOccupiedHeight(pos.getX(), pos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());

            NoiseColumn columnOfBlocks = generator.getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor());
            BlockState topBlock = columnOfBlocks.getBlock(landHeight);

            if (!topBlock.getFluidState().isEmpty()) {
                return false;
            }
        }

        // Check for other structures
        List<ResourceKey<StructureSet>> structures = ModStructures.MOD_STRUCTURES.stream()
                .filter(structure -> structure.step() == this.step() && structure.getStructureSetResourceKey() != this.getStructureSetResourceKey())
                .map(AbstractValhelsiaStructure::getStructureSetResourceKey).toList();

        if (StructureUtils.isStructureInDistance(generator, context.seed(), context.chunkPos(), structures)) {
            return false;
        }

        // Check the spawn chance
        random.setSeed((long) (chunkPos.x >> 4 ^ chunkPos.z >> 4 << 4) ^ context.seed());

        return random.nextDouble() < this.getSpawnChance();
    }

    @Nonnull
    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    public boolean transformsSurroundingLand() {
        return true;
    }

    public abstract Pair<Integer, Integer> getSize();

    public StructureSettings getStructureSettings() {
        return this.structureSettings;
    }

    public ResourceKey<StructureSet> getStructureSetResourceKey() {
        return this.structureSetResourceKey;
    }

    public int getSpacing() {
        return Math.max(this.getStructureSettings().spacing().get(), getSeparation() + 1);
    }

    public int getSeparation() {
        return this.getStructureSettings().separation().get();
    }

    public abstract int getSeedModifier();

    public double getSpawnChance() {
        return this.getStructureSettings().spawnChance().get();
    }

    public abstract Holder<ConfiguredStructureFeature<?, ?>> getStructureFeature();

    public boolean checkSurface() {
        return true;
    }

    public boolean canGenerateOnWater() {
        return false;
    }

    public boolean hasMargin() {
        return true;
    }

    public int getMargin() {
        return 12;
    }

//    @Nonnull
//    @Override
//    public BoundingBox adjustBoundingBox(@Nonnull BoundingBox boundingBox) {
//        return super.adjustBoundingBox(boundingBox).inflatedBy(this.hasMargin() ? this.getMargin() : 0);
//    }

    public int getGenerationHeight() {
        return -1;
    }

    public Optional<PieceGenerator<JigsawConfiguration>> generatePieces(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        boolean flag = this.getGenerationHeight() == -1;
        BlockPos pos = context.chunkPos().getMiddleBlockPosition(flag ? 0 : this.getGenerationHeight());

        return JigsawPlacement.addPieces(
                context,
                PoolElementStructurePiece::new,
                pos,
                true,
                flag
        );
    }
}