package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.utils.StructureUtils;
import com.stal111.valhelsia_structures.world.structures.start.ValhelsiaStructureStart;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.valhelsia.valhelsia_core.world.IValhelsiaStructure;
import net.valhelsia.valhelsia_core.world.ValhelsiaJigsawStructure;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

/**
 * Abstract Valhelsia Structure <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure
 * <p>
 * Serves as a way to reduce duplicate code in structures - this has sensible defaults for most surface structures
 * but can be overridden if needed.
 *
 * @author Valhelsia Team
 * @version 0.1.6
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
    protected boolean func_230363_a_(@Nonnull ChunkGenerator generator, @Nonnull BiomeSource provider, long seed, @Nonnull WorldgenRandom rand, int chunkX, int chunkZ, @Nonnull Biome biome, @Nonnull ChunkPos pos, @Nonnull JigsawConfiguration config) {
        BlockPos centerOfChunk = new BlockPos(chunkX << 4, 0, chunkZ << 4);

        if (this.checkSurface() && !this.isSurfaceFlat(generator, centerOfChunk.getX(), centerOfChunk.getZ())) {
            return false;
        }

        if (!this.canGenerateOnWater()) {
            int landHeight = generator.getHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG);

            IBlockReader columnOfBlocks = generator.func_230348_a_(centerOfChunk.getX(), centerOfChunk.getZ());
            BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.up(landHeight));

            if (!topBlock.getFluidState().isEmpty()) {
                return false;
            }
        }

        rand.setSeed((long) ((chunkX >> 4) ^ (chunkZ >> 4) << 4) ^ seed);
        rand.nextInt();

        // Check for other structures
        if (this.getDecorationStage() == GenerationStage.Decoration.SURFACE_STRUCTURES) {
            List<Structure<?>> structures = new ArrayList<>();

            for (IValhelsiaStructure iStructure : ModStructures.MOD_STRUCTURES) {
                Structure<?> structure = iStructure.getStructure();

                if (structure.getDecorationStage() == this.getDecorationStage()) {
                    structures.add(structure);
                }
            }

            structures.add(Structure.VILLAGE);

            if (!StructureUtils.checkForOtherStructures(this, generator, seed, rand, chunkX, chunkZ, structures)) {
                return false;
            }
        }

        return rand.nextDouble() < this.getSpawnChance();
    }

    @Override
    @Nonnull
    public String getStructureName() {
        return new ResourceLocation(ValhelsiaStructures.MOD_ID, this.getName()).toString();
    }

    protected boolean isSurfaceFlat(@Nonnull ChunkGenerator generator, int posX, int posZ) {
        // Size of the area to check.
        int offset = getSize() * 16 / 2;

        int height1 = generator.getHeight(posX, posZ, Heightmap.Type.WORLD_SURFACE_WG);
        int height2 = generator.getHeight(posX, posZ + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int height3 = generator.getHeight(posX + offset, posZ, Heightmap.Type.WORLD_SURFACE_WG);
        int height4 = generator.getHeight(posX + offset, posZ + offset, Heightmap.Type.WORLD_SURFACE_WG);

        int minHeight = Math.min(Math.min(height1, height2), Math.min(height3, height4));
        int maxHeight = Math.max(Math.max(height1, height2), Math.max(height3, height4));

        return Math.abs(maxHeight - minHeight) <= StructureGenConfig.FLATNESS_DELTA.get();
    }

    @Nonnull
    @Override
    public GenerationStage.Decoration getDecorationStage() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
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
    public StructureSeparationSettings getSeparationSettings() {
        return new StructureSeparationSettings(this.getSpacing(), this.getSeparation(), this.getSeedModifier());
    }

    public double getSpawnChance() {
        return getStructureConfigEntry().configuredSpawnChance.get();
    }

    public abstract StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> getStructureFeature();

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
    public IStartFactory<VillageConfig> getStartFactory() {
        return AbstractValhelsiaStructure.Start::new;
    }

    public int getGenerationHeight() {
        return -1;
    }

    public static class Start extends ValhelsiaStructureStart<VillageConfig> {
        private final Structure<VillageConfig> structure;
        private final int height;

        public Start(Structure<VillageConfig> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int reference, long seed) {
            this(structure, chunkX, chunkZ, boundingBox, reference, seed, ((AbstractValhelsiaStructure) structure).getGenerationHeight());
        }

        public Start(Structure<VillageConfig> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int reference, long seed, int height) {
            super(structure, chunkX, chunkZ, boundingBox, reference, seed);
            this.structure = structure;
            this.height = height;
        }

        @Override
        public void func_230364_a_(@Nonnull DynamicRegistries registries, @Nonnull ChunkGenerator generator, @Nonnull TemplateManager manager, int chunkX, int chunkZ, @Nonnull Biome biome, @Nonnull VillageConfig villageConfig) {
            BlockPos pos = new BlockPos(chunkX << 4, 0, chunkZ << 4);

            JigsawManager.func_242837_a(registries, villageConfig, AbstractVillagePiece::new, generator, manager, pos, this.components, this.rand, false, true);

            Vector3i structureCenter = this.components.get(0).getBoundingBox().func_215126_f();

            for (StructurePiece piece : this.components){
                piece.offset(pos.getX() - structureCenter.getX(), 0, pos.getZ() - structureCenter.getZ());
            }

            this.recalculateStructureSize();

            if (this.height != -1) {
                int offset = this.height - this.bounds.minY;
                this.bounds.offset(0, offset, 0);

                for (StructurePiece structurepiece : this.components) {
                    structurepiece.offset(0, offset, 0);
                }
            }
        }
    }
}