package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.utils.StructureType;
import com.stal111.valhelsia_structures.utils.StructureUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Abstract Overworld Structure
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.AbstractOverworldStructure
 * <p>
 * Serves as a way to reduce duplicate code in structures - this has sensible defaults for most surface structures
 * but can be overridden if needed.
 *
 * @author Valhelsia Team
 * @version 16.0.2
 * @since 2020-03-22
 */

public abstract class AbstractValhelsiaStructure<C extends IFeatureConfig> extends Structure<C> {

    private final String name;
    private final int size;

    public AbstractValhelsiaStructure(Codec<C> configCoded, String name, int size) {
        super(configCoded);
        this.name = name;
        this.size = size;
    }

    @Override
    protected boolean func_230363_a_(ChunkGenerator generator, BiomeProvider provider, long seed, SharedSeedRandom rand, int chunkX, int chunkZ, Biome biome, ChunkPos pos, IFeatureConfig config) {
        if (isSurfaceFlat(generator, chunkX, chunkZ)) {

            // Check the entire size of the structure to see if it's all a viable biome:
            for(Biome biome1 : provider.getBiomes(chunkX * 16 + 9, generator.func_230356_f_(), chunkZ * 16 + 9, getSize() * 16)) {
                if (!biome1.hasStructure(this)) {
                    return false;
                }
            }

            // Check the entire size of the structure for Blacklisted Biomes
            for(Biome biome1 : provider.getBiomes(chunkX * 16 + 9, generator.func_230356_f_(), chunkZ * 16 + 9, getSize() * 16)) {
                if (StructureGenConfig.BLACKLISTED_BIOMES.get().contains(Objects.requireNonNull(biome1.getRegistryName()).toString())) {
                    return false;
                }
            }

            // Check for other structures
            List<Structure<?>> structures = new ArrayList<>(ModStructures.STRUCTURES_MAP.get(getStructureType()));

            if (getStructureType() == StructureType.SURFACE) {
                structures.add(Structure.field_236381_q_);
            } else if (getStructureType() == StructureType.UNDERGROUND) {
                structures.addAll(Arrays.asList(Structure.field_236367_c_, Structure.field_236375_k_));
            }

            if (!StructureUtils.checkForOtherStructures(this, generator, seed, rand, chunkX, chunkZ, structures)) {
                return false;
            }

            int i = chunkX >> 4;
            int j = chunkZ >> 4;
            rand.setSeed((long) (i ^ j << 4) ^ seed);
            rand.nextInt();
            return rand.nextDouble() < getSpawnChance();
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

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public abstract int getSeparation();

    public abstract int getDistance();

    public abstract int getSeedModifier();

    public abstract double getSpawnChance();

    public StructureType getStructureType() {
        return StructureType.SURFACE;
    }

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
