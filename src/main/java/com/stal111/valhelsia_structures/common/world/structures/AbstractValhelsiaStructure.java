package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.config.ModConfig;
import com.stal111.valhelsia_structures.core.config.StructureConfigEntry;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.valhelsia.valhelsia_core.common.world.ValhelsiaJigsawStructure;

import javax.annotation.Nonnull;
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
 * @version 1.18.1-0.1.0
 * @since 2020-03-22
 */
public abstract class AbstractValhelsiaStructure extends ValhelsiaJigsawStructure<JigsawConfiguration> {

    private final StructureConfigEntry structureConfigEntry;

    public AbstractValhelsiaStructure(Codec<JigsawConfiguration> codec, String name, Predicate<PieceGeneratorSupplier.Context<JigsawConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<JigsawConfiguration>, Optional<PieceGenerator<JigsawConfiguration>>> pieceCreationPredicate, StructureConfigEntry structureConfigEntry) {
        super(codec, name, locationCheckPredicate, pieceCreationPredicate);
        this.structureConfigEntry = structureConfigEntry;
    }

    protected boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        BlockPos pos = context.chunkPos().getWorldPosition();
        ChunkGenerator generator = context.chunkGenerator();
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));

        if (this.checkSurface() && !this.isSurfaceFlat(context)) {
            return false;
        }

        if (!this.canGenerateOnWater()) {
            int landHeight = generator.getFirstOccupiedHeight(pos.getX(), pos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());

            NoiseColumn columnOfBlocks = generator.getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor());
            BlockState topBlock = columnOfBlocks.getBlock(landHeight);

            if (!topBlock.getFluidState().isEmpty()) {
                return false;
            }
        }

        // Check for other structures

        // TODO do we still need this check?
//        if (this.step() == GenerationStep.Decoration.SURFACE_STRUCTURES) {
//            List<StructureFeature<?>> structures = new ArrayList<>();
//
//            for (IValhelsiaStructure iStructure : ModStructures.MOD_STRUCTURES) {
//                StructureFeature<?> structure = iStructure.getStructure();
//
//                if (structure.step() == this.step()) {
//                    structures.add(structure);
//                }
//            }
//
//            structures.add(StructureFeature.VILLAGE);
//
//            if (!StructureUtils.checkForOtherStructures(this, generator, context.seed(), context.chunkPos(), structures)) {
//                return false;
//            }
//        }

        return true;
      //  return random.nextDouble() < this.getSpawnChance();
    }

    @Nonnull
    @Override
    public String getFeatureName() {
        return new ResourceLocation(ValhelsiaStructures.MOD_ID, this.getName()).toString();
    }

    private boolean isSurfaceFlat(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        ChunkPos pos = context.chunkPos();
        int[] cornerHeights = context.getCornerHeights(pos.getMiddleBlockX(), this.getSize().getFirst(), pos.getMiddleBlockZ(), this.getSize().getSecond());

        int minHeight = Math.min(Math.min(cornerHeights[0], cornerHeights[1]), Math.min(cornerHeights[2], cornerHeights[3]));
        int maxHeight = Math.max(Math.max(cornerHeights[0], cornerHeights[1]), Math.max(cornerHeights[2], cornerHeights[3]));

        return Math.abs(maxHeight - minHeight) <= ModConfig.COMMON.flatnessDelta.get();
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

    public StructureConfigEntry getStructureConfigEntry() {
        return structureConfigEntry;
    }

    public int getSpacing() {
        return Math.max(getStructureConfigEntry().configuredSpacing.get(), getSeparation() + 1);
    }

    public int getSeparation() {
        return getStructureConfigEntry().configuredSeparation.get();
    }

    public abstract int getSeedModifier();

    @Override
    public StructureFeatureConfiguration getFeatureConfiguration() {
        return new StructureFeatureConfiguration(this.getSpacing(), this.getSeparation(), this.getSeedModifier());
    }

    public double getSpawnChance() {
        return getStructureConfigEntry().configuredSpawnChance.get();
    }

    public abstract ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> getStructureFeature();

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

    @Nonnull
    @Override
    public BoundingBox adjustBoundingBox(@Nonnull BoundingBox boundingBox) {
        return super.adjustBoundingBox(boundingBox).inflatedBy(this.hasMargin() ? this.getMargin() : 0);
    }

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