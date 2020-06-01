package com.stal111.valhelsia_structures.world.structures;

import com.mojang.datafixers.Dynamic;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nonnull;
import java.util.function.Function;

public class SmallDungeonStructure extends AbstractValhelsiaStructure {
    public static final String SHORT_NAME = "small_dungeon";

    public SmallDungeonStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory) {
        super(configFactory, SHORT_NAME);
    }

    @Override
    protected int getFeatureDistance(ChunkGenerator<?> generator) {
        return StructureGenConfig.SMALL_DUNGEON_DISTANCE.get();
    }

    @Override
    protected int getFeatureSeparation(ChunkGenerator<?> generator) {
        return StructureGenConfig.SMALL_DUNGEON_SEPARATION.get();
    }

    @Override
    protected double getSpawnChance() {
        return StructureGenConfig.SMALL_DUNGEON_SPAWN_CHANCE.get();
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    protected int getSeedModifier() {
        return 23498567;
    }

    @Override
    @Nonnull
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    public static class Start extends MarginedStructureStart {

        public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bounds, int reference, long seed) {
            super(structure, chunkX, chunkZ, bounds, reference, seed);
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
