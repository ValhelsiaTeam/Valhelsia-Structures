package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
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

    public AbstractValhelsiaStructure(Codec<VillageConfig> configCoded, String name, int size) {
        super(configCoded, 0, true, true);
        this.name = name;
        this.size = size;
    }

    @Override
    protected boolean func_230363_a_(ChunkGenerator generator, BiomeProvider provider, long seed, SharedSeedRandom rand, int chunkX, int chunkZ, Biome biome, ChunkPos pos, VillageConfig config) {
        if (isSurfaceFlat(generator, chunkX, chunkZ)) {

            // Check the entire size of the structure to see if it's all a viable biome:
            for(Biome biome1 : provider.getBiomes(chunkX * 16 + 9, generator.func_230356_f_(), chunkZ * 16 + 9, getSize() * 16)) {
                if (!biome1.func_242440_e().func_242493_a(this)) {
                    return false;
                }
            }

            // Check the entire size of the structure for Blacklisted Biomes
            for(Biome biome1 : provider.getBiomes(chunkX * 16 + 9, generator.func_230356_f_(), chunkZ * 16 + 9, getSize() * 16)) {
                if (biome1.getRegistryName() != null) {
                    if (StructureGenConfig.BLACKLISTED_BIOMES.get().contains(Objects.requireNonNull(biome1.getRegistryName()).toString())) {
                        return false;
                    }
                }
            }

            // Check for other structures
            List<Structure<?>> structures = new ArrayList<>(ModStructures.STRUCTURES_MAP.get(getStructureType()));

            if (getStructureType() == StructureType.SURFACE) {
                structures.add(Structure.field_236381_q_);
            } else if (getStructureType() == StructureType.UNDERGROUND) {
                structures.addAll(Arrays.asList(Structure.field_236367_c_, Structure.field_236375_k_));
            }

            if (!StructureUtils.checkForOtherStructures(this, generator, seed, rand, chunkX, chunkZ, structures)) {
                return false;
            }

            int i = chunkX >> 4;
            int j = chunkZ >> 4;
            rand.setSeed((long) (i ^ j << 4) ^ seed);
            rand.nextInt();
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
        int offset = getSize() * 16;

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

    public abstract int getSeparation();

    public abstract int getDistance();

    public abstract int getSeedModifier();

    public abstract double getSpawnChance();

    public StructureType getStructureType() {
        return StructureType.SURFACE;
    }

    @Nullable
    @Override
    public BlockPos func_236388_a_(IWorldReader world, StructureManager structureManager, BlockPos startPos, int searchRadius, boolean skipExistingChunks, long seed, StructureSeparationSettings settings) {
        return super.func_236388_a_(world, structureManager, startPos, searchRadius, skipExistingChunks, seed, new StructureSeparationSettings(this.getDistance(), this.getSeparation(), this.getSeedModifier()));
    }

    @Override
    public ChunkPos func_236392_a_(StructureSeparationSettings settings, long seed, SharedSeedRandom sharedSeedRand, int x, int z) {
        int spacing = this.getDistance();
        int separation = this.getSeparation();

        int k = Math.floorDiv(x, spacing);
        int l = Math.floorDiv(z, spacing);

        sharedSeedRand.setLargeFeatureSeedWithSalt(seed, k, l, getSeedModifier());

        int i1;
        int j1;
        if (this.func_230365_b_()) {
            i1 = sharedSeedRand.nextInt(spacing - separation);
            j1 = sharedSeedRand.nextInt(spacing - separation);
        } else {
            i1 = (sharedSeedRand.nextInt(spacing - separation) + sharedSeedRand.nextInt(spacing - separation)) / 2;
            j1 = (sharedSeedRand.nextInt(spacing - separation) + sharedSeedRand.nextInt(spacing - separation)) / 2;
        }

        return new ChunkPos(k * spacing + i1, l * spacing + j1);
    }

    public Structure.IStartFactory<VillageConfig> getStartFactory() {
        return (p_242778_1_, p_242778_2_, p_242778_3_, p_242778_4_, p_242778_5_, p_242778_6_) -> new Start(this, p_242778_2_, p_242778_3_, p_242778_4_, p_242778_5_, p_242778_6_, getGenerationHeight());
    }

    protected int getGenerationHeight() {
        return -1;
    }

    public static class Start extends MarginedStructureStart<VillageConfig> {
        private final JigsawStructure structure;
        private final int height;

        public Start(JigsawStructure structure, int p_i241979_2_, int p_i241979_3_, MutableBoundingBox boundingBox, int p_i241979_5_, long p_i241979_6_) {
            this(structure, p_i241979_2_, p_i241979_3_, boundingBox, p_i241979_5_, p_i241979_6_, -1);
        }

        public Start(JigsawStructure structure, int p_i241979_2_, int p_i241979_3_, MutableBoundingBox boundingBox, int p_i241979_5_, long p_i241979_6_, int height) {
            super(structure, p_i241979_2_, p_i241979_3_, boundingBox, p_i241979_5_, p_i241979_6_);
            this.structure = structure;
            this.height = height;
        }

        public void func_230364_a_(DynamicRegistries registries, ChunkGenerator generator, TemplateManager manager, int p_230364_4_, int p_230364_5_, Biome biome, VillageConfig villageConfig) {
            BlockPos blockpos = new BlockPos(p_230364_4_ * 16, 0, p_230364_5_ * 16);
            JigsawManager.func_242837_a(registries, villageConfig, AbstractVillagePiece::new, generator, manager, blockpos, this.components, this.rand, true, true);
            this.recalculateStructureSize();

            if (height != -1) {
                this.func_214628_a(generator.func_230356_f_(), this.rand, height);
            }
        }
    }
}
