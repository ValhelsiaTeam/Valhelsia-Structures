package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.config.ModConfig;
import com.stal111.valhelsia_structures.core.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.utils.StructureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.NoiseAffectingStructureStart;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.valhelsia.valhelsia_core.common.world.IValhelsiaStructure;
import net.valhelsia.valhelsia_core.common.world.ValhelsiaJigsawStructure;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Valhelsia Structure <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.world.structures.AbstractValhelsiaStructure
 * <p>
 * Serves as a way to reduce duplicate code in structures - this has sensible defaults for most surface structures
 * but can be overridden if needed.
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2020-03-22
 */
public abstract class AbstractValhelsiaStructure extends ValhelsiaJigsawStructure {

    private final int size;

    private final StructureConfigEntry structureConfigEntry;

    public AbstractValhelsiaStructure(Codec<JigsawConfiguration> codec, String name, int size, StructureConfigEntry structureConfigEntry) {
        super(codec, name);
        this.size = size;
        this.structureConfigEntry = structureConfigEntry;
    }

    @Override
    protected boolean isFeatureChunk(@Nonnull ChunkGenerator generator, @Nonnull BiomeSource provider, long seed, @Nonnull WorldgenRandom rand, @Nonnull ChunkPos chunkPos, @Nonnull Biome biome, @Nonnull ChunkPos potentialPos, @Nonnull JigsawConfiguration config, @Nonnull LevelHeightAccessor level) {
        BlockPos centerOfChunk = chunkPos.getWorldPosition();

        if (this.checkSurface() && !this.isSurfaceFlat(generator, centerOfChunk.getX(), centerOfChunk.getZ(), level)) {
            return false;
        }

        if (!this.canGenerateOnWater()) {
            int landHeight = generator.getBaseHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, level);

            NoiseColumn columnOfBlocks = generator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ(), level);
            BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));

            if (!topBlock.getFluidState().isEmpty()) {
                return false;
            }
        }

        // Check for other structures
        if (this.step() == GenerationStep.Decoration.SURFACE_STRUCTURES) {
            List<StructureFeature<?>> structures = new ArrayList<>();

            for (IValhelsiaStructure iStructure : ModStructures.MOD_STRUCTURES) {
                StructureFeature<?> structure = iStructure.getStructure();

                if (structure.step() == this.step()) {
                    structures.add(structure);
                }
            }

            structures.add(StructureFeature.VILLAGE);

            if (!StructureUtils.checkForOtherStructures(this, generator, seed, rand, chunkPos.x, chunkPos.z, structures)) {
                return false;
            }
        }

        return rand.nextDouble() < this.getSpawnChance();
    }

    @Nonnull
    @Override
    public String getFeatureName() {
        return new ResourceLocation(ValhelsiaStructures.MOD_ID, this.getName()).toString();
    }

    protected boolean isSurfaceFlat(@Nonnull ChunkGenerator generator, int posX, int posZ, LevelHeightAccessor level) {
        // Size of the area to check.
        int offset = getSize() * 16 / 2;

        int height1 = generator.getBaseHeight(posX, posZ, Heightmap.Types.WORLD_SURFACE_WG, level);
        int height2 = generator.getBaseHeight(posX, posZ + offset, Heightmap.Types.WORLD_SURFACE_WG, level);
        int height3 = generator.getBaseHeight(posX + offset, posZ, Heightmap.Types.WORLD_SURFACE_WG, level);
        int height4 = generator.getBaseHeight(posX + offset, posZ + offset, Heightmap.Types.WORLD_SURFACE_WG, level);

        int minHeight = Math.min(Math.min(height1, height2), Math.min(height3, height4));
        int maxHeight = Math.max(Math.max(height1, height2), Math.max(height3, height4));

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

    public int getSize() {
        return size;
    }

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

    @Override
    @Nonnull
    public StructureStartFactory<JigsawConfiguration> getStartFactory() {
        return AbstractValhelsiaStructure.Start::new;
    }

    public int getGenerationHeight() {
        return -1;
    }

    public static class Start extends NoiseAffectingStructureStart<JigsawConfiguration> {
        private final StructureFeature<JigsawConfiguration> structure;
        private final int height;

        public Start(StructureFeature<JigsawConfiguration> structure, ChunkPos chunkPos, int reference, long seed) {
            this(structure, chunkPos, reference, seed, -1);
        }

        public Start(StructureFeature<JigsawConfiguration> structure, ChunkPos chunkPos, int reference, long seed, int height) {
            super(structure, chunkPos, reference, seed);
            this.structure = structure;
            this.height = height;
        }

        @Override
        public void generatePieces(@Nonnull RegistryAccess registryAccess, @Nonnull ChunkGenerator chunkGenerator, @Nonnull StructureManager structureManager, @Nonnull ChunkPos chunkPos, @Nonnull Biome biome, @Nonnull JigsawConfiguration config, @Nonnull LevelHeightAccessor level) {
            BlockPos pos = chunkPos.getWorldPosition();

            JigsawPlacement.addPieces(registryAccess, config, PoolElementStructurePiece::new, chunkGenerator, structureManager, pos, this, this.random, false, true, level);

            BlockPos structureCenter = this.pieces.get(0).getBoundingBox().getCenter();

            for (StructurePiece piece : this.pieces){
                piece.move(pos.getX() - structureCenter.getX(), 0, pos.getZ() - structureCenter.getZ());
            }

            this.getBoundingBox();

            if (this.height != -1) {
                int offset = this.height - this.getBoundingBox().minY();
                this.getBoundingBox().move(0, offset, 0);

                for (StructurePiece piece : this.pieces) {
                    piece.move(0, offset, 0);
                }
            }
        }
    }
}