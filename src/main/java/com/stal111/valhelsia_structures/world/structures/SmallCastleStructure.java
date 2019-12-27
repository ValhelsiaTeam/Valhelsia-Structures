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
    protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> generator, Random random, int chunkX, int chunkZ, int offsetX, int offsetZ) {
        int lvt_9_1_ = chunkX + FEATURE_DISTANCE * offsetX;
        int lvt_10_1_ = chunkZ + FEATURE_DISTANCE * offsetZ;
        int lvt_11_1_ = lvt_9_1_ < 0 ? lvt_9_1_ - FEATURE_DISTANCE + 1 : lvt_9_1_;
        int lvt_12_1_ = lvt_10_1_ < 0 ? lvt_10_1_ - FEATURE_DISTANCE + 1 : lvt_10_1_;
        int lvt_13_1_ = lvt_11_1_ / FEATURE_DISTANCE;
        int lvt_14_1_ = lvt_12_1_ / FEATURE_DISTANCE;
        ((SharedSeedRandom)random).setLargeFeatureSeedWithSalt(generator.getSeed(), lvt_13_1_, lvt_14_1_, 10387312);
        lvt_13_1_ *= FEATURE_DISTANCE;
        lvt_14_1_ *= FEATURE_DISTANCE;
        lvt_13_1_ += random.nextInt(FEATURE_DISTANCE - FEATURE_SEPARATION);
        lvt_14_1_ += random.nextInt(FEATURE_DISTANCE - FEATURE_SEPARATION);
        return new ChunkPos(lvt_13_1_, lvt_14_1_);
    }

    @Override
    public boolean hasStartAt(ChunkGenerator<?> generator, Random random, int chunkX, int chunkZ) {
        ChunkPos chunkPos = this.getStartPositionForPosition(generator, random, chunkX, chunkZ, 0, 0);
        if (chunkX == chunkPos.x && chunkZ == chunkPos.z) {
            return generator.getBiomeProvider().getBiomesInSquare((chunkX << 4) + 9, (chunkZ << 4) + 9, CHUNK_RADIUS * 16)
                    .stream()
                    .allMatch(biome -> generator.hasStructure(biome, this));
        } else {
            return false;
        }
    }

    @Override
    protected int getSeedModifier() {
        return 14357618;
    }

    public static class Start extends MarginedStructureStart {

        public Start(Structure<?> p_i50497_1_, int p_i50497_2_, int p_i50497_3_, Biome p_i50497_4_, MutableBoundingBox p_i50497_5_, int p_i50497_6_, long p_i50497_7_) {
            super(p_i50497_1_, p_i50497_2_, p_i50497_3_, p_i50497_4_, p_i50497_5_, p_i50497_6_, p_i50497_7_);
        }

        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
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