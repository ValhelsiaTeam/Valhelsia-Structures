package com.stal111.valhelsia_structures.world.structures;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Function;

/**
 * Abstract Overworld Structure
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.AbstractOverworldStructure
 *
 * Serves as a way to reduce duplicate code in structures - this has sensible defaults for most surface structures
 * but can be overridden if needed.
 *
 * @author Valhelsia Team
 * @version 14.0.3
 * @since 2020-03-22
 */
public abstract class AbstractValhelsiaStructure extends ScatteredStructure<NoFeatureConfig> {
    public static final int DEFAULT_CHUNK_RADIUS = 2;

    public AbstractValhelsiaStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> deserialize) {
        super(deserialize);
    }

    @Override
    public int getSize() {
        return DEFAULT_CHUNK_RADIUS;
    }

    protected int getFeatureDistance(ChunkGenerator<?> generator) {
        return this.getBiomeFeatureDistance(generator);
    }

    protected int getFeatureSeparation(ChunkGenerator<?> generator) {
        return this.getBiomeFeatureSeparation(generator);
    }

    @Override
    protected @Nonnull ChunkPos getStartPositionForPosition(@Nonnull ChunkGenerator<?> generator, @Nonnull Random random, int chunkX, int chunkZ, int offsetX, int offsetZ) {
        int featureDistance = getFeatureDistance(generator);
        int featureSeparation = getFeatureSeparation(generator);

        int xTemp = chunkX + featureDistance * offsetX;
        int zTemp = chunkZ + featureDistance * offsetZ;
        int validChunkX = (xTemp < 0 ? xTemp - featureDistance + 1 : xTemp) / featureDistance;
        int validChunkZ = (zTemp < 0 ? zTemp - featureDistance + 1 : zTemp) / featureDistance;
        ((SharedSeedRandom)random).setLargeFeatureSeedWithSalt(generator.getSeed(), validChunkX, validChunkZ, this.getSeedModifier());
        validChunkX *= featureDistance;
        validChunkZ *= featureDistance;
        validChunkX += random.nextInt(featureDistance - featureSeparation);
        validChunkZ += random.nextInt(featureDistance - featureSeparation);
        return new ChunkPos(validChunkX, validChunkZ);
    }

    @Override
    public boolean hasStartAt(@Nonnull ChunkGenerator<?> generator, @Nonnull Random random, int chunkX, int chunkZ) {
        ChunkPos chunkPos = this.getStartPositionForPosition(generator, random, chunkX, chunkZ, 0, 0);
        if (chunkX == chunkPos.x && chunkZ == chunkPos.z) {
            if (isSurfaceFlat(generator, chunkX, chunkZ)) {
                return generator.getBiomeProvider().getBiomesInSquare((chunkX << 4) + 9, (chunkZ << 4) + 9, getSize() * 16)
                        .stream()
                        .allMatch(biome -> generator.hasStructure(biome, this));
            }
        }

        return false;
    }

    protected boolean isSurfaceFlat(@Nonnull ChunkGenerator<?> generator, int chunkX, int chunkZ) {
        // Size of the area to check.
        int offset = 16;

        int xStart = (chunkX << 4) + (7 - (offset / 2));
        int zStart = (chunkZ << 4) + (7 - (offset / 2));

        int i1 = generator.func_222531_c(xStart, zStart, Heightmap.Type.WORLD_SURFACE_WG);
        int j1 = generator.func_222531_c(xStart, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int k1 = generator.func_222531_c(xStart + offset, zStart, Heightmap.Type.WORLD_SURFACE_WG);
        int l1 = generator.func_222531_c(xStart + offset, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int minHeight = Math.min(Math.min(i1, j1), Math.min(k1, l1));
        int maxHeight = Math.max(Math.max(i1, j1), Math.max(k1, l1));

        // Considering a difference of three or less to be flat enough.
        return Math.abs(maxHeight - minHeight) <= 3;
    }
}
