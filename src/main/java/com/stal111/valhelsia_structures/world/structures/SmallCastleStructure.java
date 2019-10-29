package com.stal111.valhelsia_structures.world.structures;

import com.mojang.datafixers.Dynamic;
import com.stal111.valhelsia_structures.init.ModFeatures;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.function.Function;

public class SmallCastleStructure extends ScatteredStructure<NoFeatureConfig> {

    public SmallCastleStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51449_1_) {
        super(p_i51449_1_);
    }

    @Override
    protected int getSeedModifier() {
        return 1859856;
    }

    @Override
    public IStartFactory getStartFactory() {
        return SmallCastleStructure.Start::new;
    }

    @Override
    public String getStructureName() {
        return "small_castle";
    }

    @Override
    public int getSize() {
        return 5;
    }

    public static class Start extends StructureStart {
        public Start(Structure<?> p_i50289_1_, int p_i50289_2_, int p_i50289_3_, Biome p_i50289_4_, MutableBoundingBox p_i50289_5_, int p_i50289_6_, long p_i50289_7_) {
            super(p_i50289_1_, p_i50289_2_, p_i50289_3_, p_i50289_4_, p_i50289_5_, p_i50289_6_, p_i50289_7_);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            NoFeatureConfig nofeatureconfig = generator.getStructureConfig(biomeIn, ModFeatures.graveyard);
            int i = chunkX * 16;
            int j = chunkZ * 16;
            BlockPos blockpos = new BlockPos(i + 8, 90, j + 8);
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            SmallCastlePiece.func_207617_a(generator, templateManagerIn, blockpos, rotation, this.components, this.rand, nofeatureconfig);
            this.recalculateStructureSize();

        }
    }
}
