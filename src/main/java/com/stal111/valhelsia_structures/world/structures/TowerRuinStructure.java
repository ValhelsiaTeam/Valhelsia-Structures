package com.stal111.valhelsia_structures.world.structures;

import com.mojang.datafixers.Dynamic;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;


/**
 * Small Castle Structure
 * ValhelsiaStructure - com.stal111.valhelsia_structure.world.structures.SmallCastleStructure
 *
 * @author Valhelsia Team
 * @version 0.1
 * @since 2019-10-31
 */

public class TowerRuinStructure extends AbstractValhelsiaStructure {

    @SuppressWarnings("WeakerAccess")
    public static final String NAME = ValhelsiaStructures.MOD_ID + ":Tower_Ruin";
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int CHUNK_RADIUS = 1;
    private static final int FEATURE_DISTANCE = 25;
    private static final int FEATURE_SEPARATION = 8;

    public TowerRuinStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn, "tower_ruin");
    }

    @Override
    protected int getFeatureDistance(ChunkGenerator<?> generator) {
        return 25;
    }

    @Override
    protected int getFeatureSeparation(ChunkGenerator<?> generator) {
        return 8;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    protected int getSeedModifier() {
        return 24357670;
    }

    @Override
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    public static class Start extends MarginedStructureStart {

        public Start(Structure<?> p_i225874_1_, int p_i225874_2_, int p_i225874_3_, MutableBoundingBox p_i225874_4_, int p_i225874_5_, long p_i225874_6_) {
            super(p_i225874_1_, p_i225874_2_, p_i225874_3_, p_i225874_4_, p_i225874_5_, p_i225874_6_);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biomeIn) {
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            int xOffset = 32;
            int zOffset = 32;
            if (rotation == Rotation.CLOCKWISE_90) {
                xOffset *= -1;
            } else if (rotation == Rotation.CLOCKWISE_180) {
                xOffset *= -1;
                zOffset *= -1;
            } else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                zOffset *= -1;
            }

            int xCenter = (chunkX << 4) + 7;
            int zCenter = (chunkZ << 4) + 7;

            int i1 = generator.func_222531_c(xCenter, zCenter, Heightmap.Type.WORLD_SURFACE_WG);
            int j1 = generator.func_222531_c(xCenter, zCenter + zOffset, Heightmap.Type.WORLD_SURFACE_WG);
            int k1 = generator.func_222531_c(xCenter + xOffset, zCenter, Heightmap.Type.WORLD_SURFACE_WG);
            int l1 = generator.func_222531_c(xCenter + xOffset, zCenter + zOffset, Heightmap.Type.WORLD_SURFACE_WG);
            int minHeight = Math.min(Math.min(i1, j1), Math.min(k1, l1));

            BlockPos blockpos = new BlockPos(chunkX * 16, minHeight - 1, chunkZ * 16);
            TowerRuinPieces.generate(generator, templateManager, blockpos, this.components, this.rand);
            this.recalculateStructureSize();
        }
    }
}