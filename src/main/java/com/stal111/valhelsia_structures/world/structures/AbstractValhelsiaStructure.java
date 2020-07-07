package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

/**
 * Abstract Overworld Structure
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.AbstractOverworldStructure
 *
 * Serves as a way to reduce duplicate code in structures - this has sensible defaults for most surface structures
 * but can be overridden if needed.
 *
 * @author Valhelsia Team
 * @version 15.0.3a
 * @since 2020-03-22
 */

public abstract class AbstractValhelsiaStructure extends Structure<NoFeatureConfig> {

    private final String name;
    private final int size;

    public AbstractValhelsiaStructure(Codec<NoFeatureConfig> noFeatureConfigCodec, String name, int size) {
        super(noFeatureConfigCodec);
        this.name = name;
        this.size = size;
    }

    @Override
    protected boolean func_230363_a_(ChunkGenerator generator, BiomeProvider provider, long seed, SharedSeedRandom rand, int chunkX, int chunkZ, Biome biome, ChunkPos pos, NoFeatureConfig noFeatureConfig) {
        if (isSurfaceFlat(generator, chunkX, chunkZ)) {

                // Check the entire size of the structure to see if it's all a viable biome:
                for (int x = chunkX - getSize(); x <= chunkX + getSize(); x++) {
                    for (int z = chunkZ - getSize(); z <= chunkZ + getSize(); z++) {
                        if (!biome.hasStructure(this)) {
                            return false;
                        }
                    }
                }

                // Note: This was checking a larger radius (10), but I don't think we need to be that far away from a village - Vael.
                for (int k = chunkX - 5; k <= chunkX + 5; ++k) {
                    for (int l = chunkZ - 5; l <= chunkZ + 5; ++l) {
                        BlockPos position = new BlockPos((k << 4) + 9, 0, (l << 4) + 9);

                        ChunkPos villagePos = Structure.field_236381_q_.func_236392_a_(generator.func_235957_b_().func_236197_a_(Structure.field_236381_q_), seed, rand, k, l);

                        if (k == villagePos.x && l == villagePos.z) {
                            return false;
                        }
                    }
                }

                int i = chunkX >> 4;
                int j = chunkZ >> 4;
                rand.setSeed((long) (i ^ j << 4) ^ seed);
                rand.nextInt();
                return rand.nextDouble() < 0.5;

            }

        return false;
    }

    @Override
    protected boolean func_230365_b_() {
        return false;
    }

    @Override
    @Nonnull
    public String getStructureName() {
        return new ResourceLocation(ValhelsiaStructures.MOD_ID, name).toString();
    }

    protected boolean isSurfaceFlat(@Nonnull ChunkGenerator generator, int chunkX, int chunkZ) {
        // Size of the area to check.
        int offset = getSize() * 16;

        int xStart = (chunkX << 4);
        int zStart = (chunkZ << 4);

        int i1 = generator.func_222531_c(xStart, zStart, Heightmap.Type.WORLD_SURFACE_WG);
        int j1 = generator.func_222531_c(xStart, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int k1 = generator.func_222531_c(xStart + offset, zStart, Heightmap.Type.WORLD_SURFACE_WG);
        int l1 = generator.func_222531_c(xStart + offset, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int minHeight = Math.min(Math.min(i1, j1), Math.min(k1, l1));
        int maxHeight = Math.max(Math.max(i1, j1), Math.max(k1, l1));

        return Math.abs(maxHeight - minHeight) <= StructureGenConfig.FLATNESS_DELTA.get();
    }

    public int getSize() {
        return size;
    }

    public abstract int getSeparation();

    public abstract int getDistance();

    public abstract int getSeedModifier();

    public abstract double getSpawnChance();

    @Nullable
    @Override
    public BlockPos func_236388_a_(IWorldReader world, StructureManager structureManager, BlockPos startPos, int searchRadius, boolean skipExistingChunks, long seed, StructureSeparationSettings settings) {
        return super.func_236388_a_(world, structureManager, startPos, searchRadius, skipExistingChunks, seed, new StructureSeparationSettings(this.getDistance(), this.getSeparation(), this.getSeedModifier()));
    }

    @Override
    public ChunkPos func_236392_a_(StructureSeparationSettings settings, long seed, SharedSeedRandom sharedSeedRand, int x, int z) {
        int spacing = this.getDistance();
        int gridX = ((x / spacing) * spacing);
        int gridZ = ((z / spacing) * spacing);

        int offset = this.getSeparation() + 1;
        sharedSeedRand.setLargeFeatureSeedWithSalt(seed, gridX, gridZ, this.getSeedModifier());
        int offsetX = sharedSeedRand.nextInt(offset);
        int offsetZ = sharedSeedRand.nextInt(offset);

        int gridOffsetX = gridX + offsetX;
        int gridOffsetZ = gridZ + offsetZ;

        return new ChunkPos(gridOffsetX, gridOffsetZ);
    }
}
