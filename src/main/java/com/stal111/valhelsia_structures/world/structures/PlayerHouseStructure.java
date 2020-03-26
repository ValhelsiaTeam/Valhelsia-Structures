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

import javax.annotation.Nonnull;
import java.util.function.Function;

/**
 * Player House Structure
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.PlayerHouseStructure
 *
 * @author Valhelsia Team
 * @version 14.0.3
 * @since 2019-10-31
 */
public class PlayerHouseStructure extends AbstractValhelsiaStructure {
    public static final String SHORT_NAME = "player_house";
    public static final String FULL_NAME = ValhelsiaStructures.MOD_ID + ":" + SHORT_NAME;

    private static final int CHUNK_RADIUS = 2;
    private static final int FEATURE_DISTANCE = 30;
    private static final int FEATURE_SEPARATION = 8;
    private static final int SEED_MODIFIER = 17357645;

    public PlayerHouseStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> deserialize) {
        super(deserialize);
    }

    @Override
    protected int getSeedModifier() {
        return SEED_MODIFIER;
    }

    @Override
    public @Nonnull IStartFactory getStartFactory() {
        return Start::new;
    }

    @Override
    public @Nonnull String getStructureName() {
        return FULL_NAME;
    }

    @Override
    public int getSize() {
        return CHUNK_RADIUS;
    }

    @Override
    protected int getFeatureDistance(ChunkGenerator<?> generator) {
        return FEATURE_DISTANCE;
    }

    @Override
    protected int getFeatureSeparation(ChunkGenerator<?> generator) {
        return FEATURE_SEPARATION;
    }

    public static class Start extends MarginedStructureStart {
        public Start(Structure<?> structure, int chunkX, int chunkY, Biome biome, MutableBoundingBox bounds, int reference, long seed) {
            super(structure, chunkX, chunkY, biome, bounds, reference, seed);
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
            //int maxHeight = Math.max(Math.max(i1, j1), Math.max(k1, l1));
            //if (Math.abs(maxHeight - minHeight) <= 2 && minHeight >= 60) {
                BlockPos blockpos = new BlockPos(chunkX * 16, minHeight - 1, chunkZ * 16);
                PlayerHousePieces.generate(generator, templateManager, blockpos, this.components, this.rand);
                this.recalculateStructureSize();
            //}
        }
    }
}
