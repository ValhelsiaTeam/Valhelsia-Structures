package com.stal111.valhelsia_structures.world.structures;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.function.Function;

public class SmallDungeonStructure extends AbstractValhelsiaStructure {

    public SmallDungeonStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn, "small_dungeon");
    }

    @Override
    protected int getFeatureDistance(ChunkGenerator<?> generator) {
        return 35;
    }

    @Override
    protected int getFeatureSeparation(ChunkGenerator<?> generator) {
        return 8;
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    protected int getSeedModifier() {
        return 14357618;
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
        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            SmallDungeonPieces.generate(generator, templateManagerIn, blockpos, this.components, this.rand);
            this.recalculateStructureSize();
            this.func_214628_a(generator.getSeaLevel(), this.rand, 10);
        }
    }
}
