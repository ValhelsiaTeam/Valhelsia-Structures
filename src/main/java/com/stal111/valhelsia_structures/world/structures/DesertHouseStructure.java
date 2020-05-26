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

/**
 * Desert House Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.DesertHouseStructure
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2020-05-27
 */
public class DesertHouseStructure extends AbstractValhelsiaStructure {
    public static final String SHORT_NAME = "desert_house";

    public DesertHouseStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn, SHORT_NAME);
    }

    @Override
    protected int getFeatureDistance(ChunkGenerator<?> generator) {
        return 30;
    }

    @Override
    protected int getFeatureSeparation(ChunkGenerator<?> generator) {
        return 8;
    }

    @Override
    protected int getSeedModifier() {
        return 14862926;
    }

    @Override
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    public static class Start extends MarginedStructureStart {

        public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bounds, int reference, long seed) {
            super(structure, chunkX, chunkZ, bounds, reference, seed);
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

            BlockPos blockpos = new BlockPos(chunkX * 16, minHeight - 2, chunkZ * 16);
            DesertHousePieces.generate(generator, templateManager, blockpos, this.components, this.rand);
            this.recalculateStructureSize();
        }
    }
}
