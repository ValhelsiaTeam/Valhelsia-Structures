package com.stal111.valhelsia_structures.world.structures;

import com.mojang.datafixers.Dynamic;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

import java.util.Random;
import java.util.function.Function;


/**
 * Small Castle Structure
 * ValhelsiaStructure - com.stal111.valhelsia_structure.world.structures.SmallCastleStructure
 *
 * @author Valhelsia Team
 * @version 0.1
 * @since 2019-10-31
 */

public class SmallCastleStructure extends ScatteredStructure<NoFeatureConfig> {

    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("WeakerAccess")
    public static final String NAME = ValhelsiaStructures.MOD_ID +  ":Small_Castle";
    private static final int CHUNK_RADIUS = 3;
    private static final int FEATURE_DISTANCE = 35;
    private static final int FEATURE_SEPARATION = 8;

    public SmallCastleStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> deserialize) {
        super(deserialize);
    }

    @Override @Nonnull
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    @Override @Nonnull
    public String getStructureName() {
        return NAME;
    }

    @Override
    public int getSize() {
        return CHUNK_RADIUS;
    }

    @Override
    protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ) {
        int k = x + FEATURE_DISTANCE * spacingOffsetsX;
        int l = z + FEATURE_DISTANCE * spacingOffsetsZ;
        int i1 = k < 0 ? k - FEATURE_DISTANCE + 1 : k;
        int j1 = l < 0 ? l - FEATURE_DISTANCE + 1 : l;
        int k1 = i1 / FEATURE_DISTANCE;
        int l1 = j1 / FEATURE_DISTANCE;
        ((SharedSeedRandom)random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), k1, l1, 10387312);
        k1 = k1 * FEATURE_DISTANCE;
        l1 = l1 * FEATURE_DISTANCE;
        k1 = k1 + random.nextInt(FEATURE_DISTANCE - FEATURE_SEPARATION);
        l1 = l1 + random.nextInt(FEATURE_DISTANCE - FEATURE_SEPARATION);
        return new ChunkPos(k1, l1);
    }

    @Override
    public boolean func_225558_a_(BiomeManager manager, ChunkGenerator<?> generator, Random random, int chunkX, int chunkZ, Biome biome) {
        ChunkPos chunkpos = this.getStartPositionForPosition(generator, random, chunkX, chunkZ, 0, 0);
        if (chunkX == chunkpos.x && chunkZ == chunkpos.z) {
            for(Biome biome1 : generator.getBiomeProvider().func_225530_a_(chunkX * 16 + 9, generator.getSeaLevel(), chunkZ * 16 + 9, 32)) {
                if (!generator.hasStructure(biome1, this)) {
                    return false;
                }
            }
            Random random1 = new Random((long)(chunkX + chunkZ * 10387313));
            Rotation rotation = Rotation.values()[random1.nextInt(Rotation.values().length)];
            int i = 5;
            int j = 5;
            if (rotation == Rotation.CLOCKWISE_90) {
                i = -5;
            } else if (rotation == Rotation.CLOCKWISE_180) {
                i = -5;
                j = -5;
            } else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                j = -5;
            }

            int k = (chunkX << 4) + 7;
            int l = (chunkZ << 4) + 7;
            int i1 = generator.func_222531_c(k, l, Heightmap.Type.WORLD_SURFACE_WG);
            int j1 = generator.func_222531_c(k, l + j, Heightmap.Type.WORLD_SURFACE_WG);
            int k1 = generator.func_222531_c(k + i, l, Heightmap.Type.WORLD_SURFACE_WG);
            int l1 = generator.func_222531_c(k + i, l + j, Heightmap.Type.WORLD_SURFACE_WG);
            int minHeight = Math.min(Math.min(i1, j1), Math.min(k1, l1));
            int maxHeight = Math.max(Math.max(i1, j1), Math.max(k1, l1));
            if (maxHeight - minHeight < 2 && maxHeight - minHeight > -2) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    protected int getSeedModifier() {
        return 14357618;
    }

    public static class Start extends MarginedStructureStart {

        public Start(Structure<?> p_i225874_1_, int p_i225874_2_, int p_i225874_3_, MutableBoundingBox p_i225874_4_, int p_i225874_5_, long p_i225874_6_) {
            super(p_i225874_1_, p_i225874_2_, p_i225874_3_, p_i225874_4_, p_i225874_5_, p_i225874_6_);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            Random random = new Random((long)(chunkX + chunkZ * 10387313));
            Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
            int i = 5;
            int j = 5;
            if (rotation == Rotation.CLOCKWISE_90) {
                i = -5;
            } else if (rotation == Rotation.CLOCKWISE_180) {
                i = -5;
                j = -5;
            } else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                j = -5;
            }

            int k = (chunkX << 4) + 7;
            int l = (chunkZ << 4) + 7;
            int i1 = generator.func_222531_c(k, l, Heightmap.Type.WORLD_SURFACE_WG);
            int j1 = generator.func_222531_c(k, l + j, Heightmap.Type.WORLD_SURFACE_WG);
            int k1 = generator.func_222531_c(k + i, l, Heightmap.Type.WORLD_SURFACE_WG);
            int l1 = generator.func_222531_c(k + i, l + j, Heightmap.Type.WORLD_SURFACE_WG);
            int minHeight = Math.min(Math.min(i1, j1), Math.min(k1, l1));
            int maxHeight = Math.max(Math.max(i1, j1), Math.max(k1, l1));
            if (maxHeight - minHeight < 2 && maxHeight - minHeight > -2) {
                BlockPos blockpos = new BlockPos(chunkX * 16, minHeight, chunkZ * 16);
                SmallCastlePieces.func_215139_a(generator, templateManagerIn, blockpos, this.components, this.rand);
                this.recalculateStructureSize();
            }
        }
    }
}