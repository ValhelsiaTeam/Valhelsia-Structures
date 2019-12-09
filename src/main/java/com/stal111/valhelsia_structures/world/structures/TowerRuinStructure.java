package com.stal111.valhelsia_structures.world.structures;

import com.mojang.datafixers.Dynamic;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.init.ModStructures;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
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

public class TowerRuinStructure extends ScatteredStructure<NoFeatureConfig> {

    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("WeakerAccess")
    public static final String NAME = ValhelsiaStructures.MOD_ID + ":tower_ruin";
    private static final int CHUNK_RADIUS = 1;
    private static final int FEATURE_DISTANCE = 12;
    private static final int FEATURE_SEPARATION = 8;
    private static int sizeX = 5;
    private static int sizeZ = 5;

    public TowerRuinStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> deserialize) {
        super(deserialize);
    }

    @Override
    protected int getSeedModifier() {
        return 23183018;
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
    protected int getBiomeFeatureDistance(ChunkGenerator<?> generator) {
        return FEATURE_DISTANCE;
    }

    @Override
    protected int getBiomeFeatureSeparation(ChunkGenerator<?> generator) {
        return FEATURE_SEPARATION;
    }

    @Override
    protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> p_211744_1_, Random p_211744_2_, int p_211744_3_, int p_211744_4_, int p_211744_5_, int p_211744_6_) {
        int lvt_9_1_ = p_211744_3_ + FEATURE_DISTANCE * p_211744_5_;
        int lvt_10_1_ = p_211744_4_ + FEATURE_DISTANCE * p_211744_6_;
        int lvt_11_1_ = lvt_9_1_ < 0 ? lvt_9_1_ - FEATURE_DISTANCE + 1 : lvt_9_1_;
        int lvt_12_1_ = lvt_10_1_ < 0 ? lvt_10_1_ - FEATURE_DISTANCE + 1 : lvt_10_1_;
        int lvt_13_1_ = lvt_11_1_ / FEATURE_DISTANCE;
        int lvt_14_1_ = lvt_12_1_ / FEATURE_DISTANCE;
        ((SharedSeedRandom)p_211744_2_).setLargeFeatureSeedWithSalt(p_211744_1_.getSeed(), lvt_13_1_, lvt_14_1_, 10387313);
        lvt_13_1_ *= FEATURE_DISTANCE;
        lvt_14_1_ *= FEATURE_DISTANCE;
        lvt_13_1_ += (p_211744_2_.nextInt(FEATURE_DISTANCE - FEATURE_SEPARATION) + p_211744_2_.nextInt(FEATURE_DISTANCE - FEATURE_SEPARATION)) / 2;
        lvt_14_1_ += (p_211744_2_.nextInt(FEATURE_DISTANCE - FEATURE_SEPARATION) + p_211744_2_.nextInt(FEATURE_DISTANCE - FEATURE_SEPARATION)) / 2;
        return new ChunkPos(lvt_13_1_, lvt_14_1_);
    }

    @Override
    public boolean hasStartAt(@Nonnull ChunkGenerator<?> generator, @Nonnull Random random, int chunkX, int chunkZ) {
        ChunkPos startPos = this.getStartPositionForPosition(generator, random, chunkX, chunkZ, 0, 0);

        if (chunkX == startPos.x && chunkZ == startPos.z) {
            BiomeProvider biomeProvider = generator.getBiomeProvider();
            int lvt_7_1_ = (chunkX << 4) + 7;
            int lvt_8_1_ = (chunkZ << 4) + 7;
            int height1 = generator.func_222531_c(lvt_7_1_, lvt_8_1_, Heightmap.Type.WORLD_SURFACE_WG);
            int height2 = generator.func_222531_c(lvt_7_1_ + 18, lvt_8_1_ + 14, Heightmap.Type.WORLD_SURFACE_WG);
            for(int k = chunkX - 10; k <= chunkX + 10; ++k) {
                for(int l = chunkZ - 10; l <= chunkZ + 10; ++l) {
                    if (Feature.VILLAGE.hasStartAt(generator, random, k, l)) {
                        return false;
                    }
                }
            }
            if (height1 - height2 <= 4 && height1 - height2 >= -4) {
                return biomeProvider.getBiomesInSquare((chunkX << 4) + 9, (chunkZ << 4) + 9, CHUNK_RADIUS * 16)
                        .stream()
                        .allMatch(biome -> {
                            if (!generator.hasStructure(biome, ModStructures.SMALL_CASTLE)) {
                                return generator.hasStructure(biome, this);
                            } else {
                                return false;
                            }
                        });
            }
        }
        return false;
    }

    private static int getYPosForStructure(int chunkX, int chunkZ, ChunkGenerator<?> generator) {
        Random lvt_3_1_ = new Random((chunkX + chunkZ * 10387313));
        Rotation lvt_4_1_ = Rotation.values()[lvt_3_1_.nextInt(Rotation.values().length)];
        if (lvt_4_1_ == Rotation.CLOCKWISE_90) {
            sizeX = -5;
        } else if (lvt_4_1_ == Rotation.CLOCKWISE_180) {
            sizeX = -5;
            sizeZ = -5;
        } else if (lvt_4_1_ == Rotation.COUNTERCLOCKWISE_90) {
            sizeZ = -5;
        }
        int lvt_7_1_ = (chunkX << 4) + 7;
        int lvt_8_1_ = (chunkZ << 4) + 7;
        int lvt_9_1_ = generator.func_222531_c(lvt_7_1_, lvt_8_1_, Heightmap.Type.WORLD_SURFACE_WG);
        int lvt_10_1_ = generator.func_222531_c(lvt_7_1_, lvt_8_1_ + sizeZ, Heightmap.Type.WORLD_SURFACE_WG);
        int lvt_11_1_ = generator.func_222531_c(lvt_7_1_ + sizeX, lvt_8_1_, Heightmap.Type.WORLD_SURFACE_WG);
        int lvt_12_1_ = generator.func_222531_c(lvt_7_1_ + sizeX, lvt_8_1_ + sizeZ, Heightmap.Type.WORLD_SURFACE_WG);
        return Math.min(Math.min(lvt_9_1_, lvt_10_1_), Math.min(lvt_11_1_, lvt_12_1_));
    }

    public static class Start extends StructureStart {

        @SuppressWarnings("WeakerAccess")
        public Start(Structure<?> structure, int chunkX, int chunkZ, Biome biome, MutableBoundingBox bounds, int reference, long seed) {
            super(structure, chunkX, chunkZ, biome, bounds, reference, seed);
        }

        @Override
        public void init(@Nonnull ChunkGenerator<?> generator, @Nonnull TemplateManager templateManager, int chunkX, int chunkZ, @Nonnull Biome biome) {
            BlockPos blockpos = new BlockPos(chunkX * 16 + 8, getYPosForStructure(chunkX, chunkZ, generator), chunkZ * 16 + 8);
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            TowerRuinPiece.addPieces(generator, templateManager, blockpos, rotation, this.components);
            this.recalculateStructureSize();
        }
    }
}