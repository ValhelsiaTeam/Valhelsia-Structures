package com.stal111.valhelsia_structures.world.structures;

import com.mojang.datafixers.Dynamic;
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

import java.util.function.Function;

public class ForgeStructure extends AbstractValhelsiaStructure {

    public ForgeStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn, "forge");
    }

    @Override
    protected int getFeatureDistance(ChunkGenerator<?> generator) {
        return 31;
    }

    @Override
    protected int getFeatureSeparation(ChunkGenerator<?> generator) {
        return 8;
    }

    @Override
    protected int getSeedModifier() {
        return 12857691;
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
            ForgePieces.generate(generator, templateManager, blockpos, this.components, this.rand);
            this.recalculateStructureSize();
        }
    }
}
