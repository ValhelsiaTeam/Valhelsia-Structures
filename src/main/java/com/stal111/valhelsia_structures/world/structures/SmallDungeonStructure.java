package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.world.structures.pieces.SmallDungeonPieces;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nonnull;

public class SmallDungeonStructure extends AbstractValhelsiaStructure {
    public static final String SHORT_NAME = "small_dungeon";

    public SmallDungeonStructure(Codec<NoFeatureConfig> noFeatureConfigCodec) {
        super(noFeatureConfigCodec, SHORT_NAME, 3);
    }

    @Override
    public int getSeparation() {
        return StructureGenConfig.SMALL_DUNGEON_SEPARATION.get();
    }

    @Override
    public int getDistance() {
        return StructureGenConfig.SMALL_DUNGEON_DISTANCE.get();
    }

    @Override
    public int getSeedModifier() {
        return 23498567;
    }

    @Override
    public double getSpawnChance() {
        return StructureGenConfig.SMALL_DUNGEON_SPAWN_CHANCE.get();
    }

    @Override
    @Nonnull
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    public static class Start extends MarginedStructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox bounds, int reference, long seed) {
            super(structure, chunkX, chunkZ, bounds, reference, seed);
        }

        @Override
        public void func_230364_a_(ChunkGenerator generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig noFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            SmallDungeonPieces.generate(generator, templateManager, blockpos, this.components, this.rand);
            this.recalculateStructureSize();
            this.func_214628_a(generator.func_230356_f_(), this.rand, 15);
        }
    }
}
