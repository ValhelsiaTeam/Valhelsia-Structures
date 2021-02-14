package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.utils.StructureType;
import com.stal111.valhelsia_structures.utils.StructureUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Abstract Valhelsia Structure
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure
 * <p>
 * Serves as a way to reduce duplicate code in structures - this has sensible defaults for most surface structures
 * but can be overridden if needed.
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2020-03-22
 */

public abstract class AbstractValhelsiaStructure extends JigsawStructure {

    private final String name;
    private final int size;

    private final StructureConfigEntry structureConfigEntry;

    public AbstractValhelsiaStructure(Codec<VillageConfig> configCoded, String name, int size, StructureConfigEntry structureConfigEntry) {
        super(configCoded, 0, true, true);
        this.name = name;
        this.size = size;
        this.structureConfigEntry = structureConfigEntry;
    }

    @Override
    protected boolean func_230363_a_(ChunkGenerator generator, BiomeProvider provider, long seed, SharedSeedRandom rand, int chunkX, int chunkZ, Biome biome, ChunkPos pos, VillageConfig config) {
        if (isSurfaceFlat(generator, chunkX, chunkZ)) {

            // Check the entire size of the structure to see if it's all a viable biome
            for(Biome biome1 : provider.getBiomes(chunkX * 16 + 9, generator.getSeaLevel(), chunkZ * 16 + 9, getSize() * 16 / 2)) {
                if (!biome1.getGenerationSettings().hasStructure(this)) {
                    return false;
                }
            }

            // Check the entire size of the structure for Blacklisted Biomes
            for(Biome biome1 : provider.getBiomes(chunkX * 16 + 9, generator.getSeaLevel(), chunkZ * 16 + 9, getSize() * 16 / 2)) {
                if (biome1.getRegistryName() != null) {
                    if (StructureGenConfig.BLACKLISTED_BIOMES.get().contains(Objects.requireNonNull(biome1.getRegistryName()).toString())) {
                        return false;
                    }
                }
            }

            int i = chunkX >> 4;
            int j = chunkZ >> 4;
            rand.setSeed((long) (i ^ j << 4) ^ seed);
            rand.nextInt();

            // Check for other structures
            List<Structure<?>> structures = new ArrayList<>(ModStructures.STRUCTURES_MAP.get(getStructureType()));

            if (getStructureType() == StructureType.SURFACE) {
                structures.add(Structure.VILLAGE);
            } else if (getStructureType() == StructureType.UNDERGROUND) {
                structures.addAll(Arrays.asList(Structure.MINESHAFT, Structure.STRONGHOLD));
            }

            if (!StructureUtils.checkForOtherStructures(this, generator, seed, rand, chunkX, chunkZ, structures)) {
                return false;
            }

            return rand.nextDouble() < getSpawnChance();
        }

        return false;
    }

    @Override
    @Nonnull
    public String getStructureName() {
        return new ResourceLocation(ValhelsiaStructures.MOD_ID, name).toString();
    }

    protected boolean isSurfaceFlat(@Nonnull ChunkGenerator generator, int chunkX, int chunkZ) {
        // Size of the area to check.
        int offset = getSize() * 16 / 2;

        int xStart = (chunkX << 4);
        int zStart = (chunkZ << 4);

        int i1 = generator.getNoiseHeightMinusOne(xStart, zStart, Heightmap.Type.WORLD_SURFACE_WG);
        int j1 = generator.getNoiseHeightMinusOne(xStart, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int k1 = generator.getNoiseHeightMinusOne(xStart + offset, zStart, Heightmap.Type.WORLD_SURFACE_WG);
        int l1 = generator.getNoiseHeightMinusOne(xStart + offset, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int minHeight = Math.min(Math.min(i1, j1), Math.min(k1, l1));
        int maxHeight = Math.max(Math.max(i1, j1), Math.max(k1, l1));

        return Math.abs(maxHeight - minHeight) <= StructureGenConfig.FLATNESS_DELTA.get();
    }

    public String getName() {
        return name;
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

    public double getSpawnChance() {
        return getStructureConfigEntry().configuredSpawnChance.get();
    }

    public StructureType getStructureType() {
        return StructureType.SURFACE;
    }

    public abstract StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> getStructureFeature();

    @Nullable
    @Override
    public BlockPos func_236388_a_(IWorldReader world, StructureManager structureManager, BlockPos startPos, int searchRadius, boolean skipExistingChunks, long seed, StructureSeparationSettings settings) {
        return super.func_236388_a_(world, structureManager, startPos, searchRadius, skipExistingChunks, seed, new StructureSeparationSettings(this.getSpacing(), this.getSeparation(), this.getSeedModifier()));
    }

    public Structure.IStartFactory<VillageConfig> getStartFactory() {
        return (p_242778_1_, p_242778_2_, p_242778_3_, p_242778_4_, p_242778_5_, p_242778_6_) -> new Start(this, p_242778_2_, p_242778_3_, p_242778_4_, p_242778_5_, p_242778_6_, getGenerationHeight());
    }

    protected int getGenerationHeight() {
        return -1;
    }

    public static class Start extends MarginedStructureStart<VillageConfig> {
        private final AbstractValhelsiaStructure structure;
        private final int height;

        public Start(AbstractValhelsiaStructure structure, int p_i241979_2_, int p_i241979_3_, MutableBoundingBox boundingBox, int p_i241979_5_, long p_i241979_6_) {
            this(structure, p_i241979_2_, p_i241979_3_, boundingBox, p_i241979_5_, p_i241979_6_, -1);
        }

        public Start(AbstractValhelsiaStructure structure, int p_i241979_2_, int p_i241979_3_, MutableBoundingBox boundingBox, int p_i241979_5_, long p_i241979_6_, int height) {
            super(structure, p_i241979_2_, p_i241979_3_, boundingBox, p_i241979_5_, p_i241979_6_);
            this.structure = structure;
            this.height = height;
        }

        @Override
        public void func_230364_a_(DynamicRegistries registries, ChunkGenerator generator, TemplateManager manager, int p_230364_4_, int p_230364_5_, Biome biome, VillageConfig villageConfig) {
            BlockPos blockpos = new BlockPos(p_230364_4_ * 16, 0, p_230364_5_ * 16);
            JigsawManager.func_242837_a(registries, villageConfig, AbstractVillagePiece::new, generator, manager, blockpos, this.components, this.rand, true, true);
            this.recalculateStructureSize();

            if (height != -1) {
                this.func_214628_a(generator.getSeaLevel(), this.rand, height);
            }
        }
    }
}
