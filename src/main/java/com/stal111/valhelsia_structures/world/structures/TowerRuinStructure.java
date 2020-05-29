package com.stal111.valhelsia_structures.world.structures;

import com.mojang.datafixers.Dynamic;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
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

import javax.annotation.Nonnull;
import java.util.function.Function;


/**
 * Tower Ruin Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structure.world.structures.TowerRuinStructure
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2019-10-31
 */

public class TowerRuinStructure extends AbstractValhelsiaStructure {
    public static final String SHORT_NAME = "tower_ruin";

    public TowerRuinStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn, SHORT_NAME);
    }

    @Override
    protected int getFeatureDistance(ChunkGenerator<?> generator) {
        return StructureGenConfig.TOWER_RUIN_DISTANCE.get();
    }

    @Override
    protected int getFeatureSeparation(ChunkGenerator<?> generator) {
        return StructureGenConfig.TOWER_RUIN_SEPARATION.get();
    }

    @Override
    protected double getSpawnChance() {
        return StructureGenConfig.TOWER_RUIN_SPAWN_CHANCE.get();
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
    @Nonnull
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    public static class Start extends MarginedStructureStart {

        public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bounds, int reference, long seed) {
            super(structure, chunkX, chunkZ, bounds, reference, seed);
        }

        @Override
        public void init(@Nonnull ChunkGenerator<?> generator, @Nonnull TemplateManager templateManager, int chunkX, int chunkZ, @Nonnull Biome biome) {
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            int xOffset = 16;
            int zOffset = 16;
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
            TowerRuinPieces.generate(generator, templateManager, blockpos, this.components, this.rand);
            this.recalculateStructureSize();
        }
    }
}