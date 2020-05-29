package com.stal111.valhelsia_structures.world.structures;

import com.mojang.datafixers.Dynamic;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Function;

public abstract class AbstractValhelsiaStructure extends ScatteredStructure<NoFeatureConfig> {

    public static final int DEFAULT_CHUNK_RADIUS = 2;
    private final String name;

    public AbstractValhelsiaStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn, String name) {
        super(configFactoryIn);
        this.name = name;
    }

    protected abstract double getSpawnChance();

    @Override
    public int getSize() {
        return DEFAULT_CHUNK_RADIUS;
    }

    @Override
    protected int getBiomeFeatureDistance(ChunkGenerator<?> generator) {
        return getFeatureDistance(generator);
    }

    protected abstract int getFeatureDistance(ChunkGenerator<?> generator);

    @Override
    protected int getBiomeFeatureSeparation(ChunkGenerator<?> generator) {
        return getFeatureSeparation(generator);
    }

    protected abstract int getFeatureSeparation(ChunkGenerator<?> generator);

    @Override
    @Nonnull
    public String getStructureName() {
        return new ResourceLocation(ValhelsiaStructures.MOD_ID, name).toString();
    }

    @Override
    @Nonnull
    protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> generator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ) {
        int featureDistance = getFeatureDistance(generator);
        int featureSeparation = getFeatureSeparation(generator);

        int xTemp = x + featureDistance * spacingOffsetsX;
        int zTemp = z + featureDistance * spacingOffsetsZ;
        int validChunkX = (xTemp < 0 ? xTemp - featureDistance + 1 : xTemp) / featureDistance;
        int validChunkZ = (zTemp < 0 ? zTemp - featureDistance + 1 : zTemp) / featureDistance;
        ((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(generator.getSeed(), validChunkX, validChunkZ, this.getSeedModifier());
        validChunkX *= featureDistance;
        validChunkZ *= featureDistance;
        validChunkX += random.nextInt(featureDistance - featureSeparation);
        validChunkZ += random.nextInt(featureDistance - featureSeparation);
        return new ChunkPos(validChunkX, validChunkZ);
    }

    @Override
    public boolean canBeGenerated(BiomeManager biomeManager, @Nonnull ChunkGenerator<?> generator, @Nonnull Random randIn, int chunkX, int chunkZ, @Nonnull Biome biome) {

        // This will need to be changed if we introduce structures for other dimensions, but for now this blacklists anything
        // that isn't the Overworld:
        if (!(generator instanceof OverworldChunkGenerator)) {
            return false;
        }

        ChunkPos chunkpos = this.getStartPositionForPosition(generator, randIn, chunkX, chunkZ, 0, 0);
        if (chunkX == chunkpos.x && chunkZ == chunkpos.z) {
            if (isSurfaceFlat(generator, chunkX, chunkZ)) {
                if (generator.hasStructure(biome, this)) {
                    // Note: This was checking a larger radius (10), but I don't think we need to be that far away from a village - Vael.
                    for (int k = chunkX - 5; k <= chunkX + 5; ++k) {
                        for (int l = chunkZ - 5; l <= chunkZ + 5; ++l) {
                            if (Feature.VILLAGE.canBeGenerated(biomeManager, generator, randIn, k, l, biomeManager.getBiome(new BlockPos((k << 4) + 9, 0, (l << 4) + 9)))) {
                                return false;
                            }
                            // TODO: Check all of the other structures in this mod so we avoid overlapping with other structures too.
                        }
                    }

                    int i = chunkX >> 4;
                    int j = chunkZ >> 4;
                    randIn.setSeed((long) (i ^ j << 4) ^ generator.getSeed());
                    randIn.nextInt();
                    return randIn.nextDouble() < getSpawnChance();
                }
            }
        }

        return false;
    }

    protected boolean isSurfaceFlat(@Nonnull ChunkGenerator<?> generator, int chunkX, int chunkZ) {
        // Size of the area to check.
        int offset = 32;

        int xStart = (chunkX << 4) + (7 - (offset / 2));
        int zStart = (chunkZ << 4) + (7 - (offset / 2));

        int i1 = generator.func_222531_c(xStart, zStart, Heightmap.Type.WORLD_SURFACE_WG);
        int j1 = generator.func_222531_c(xStart, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int k1 = generator.func_222531_c(xStart + offset, zStart, Heightmap.Type.WORLD_SURFACE_WG);
        int l1 = generator.func_222531_c(xStart + offset, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int minHeight = Math.min(Math.min(i1, j1), Math.min(k1, l1));
        int maxHeight = Math.max(Math.max(i1, j1), Math.max(k1, l1));

        // Considering a difference of two or less to be flat enough.
        return Math.abs(maxHeight - minHeight) <= 2;
    }
}
