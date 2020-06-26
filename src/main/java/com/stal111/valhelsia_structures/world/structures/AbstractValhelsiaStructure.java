package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nonnull;
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

    public AbstractValhelsiaStructure(Codec<NoFeatureConfig> noFeatureConfigCodec, String name) {
        super(noFeatureConfigCodec);
        this.name = name;
    }

    @Override
    @Nonnull
    public String getStructureName() {
        return new ResourceLocation(ValhelsiaStructures.MOD_ID, name).toString();
    }

    @Override
    protected boolean func_230363_a_(ChunkGenerator generator, BiomeProvider biomeProvider, long seed, SharedSeedRandom sharedSeedRandom, int chunkX, int chunkZ, Biome biome, ChunkPos pos, NoFeatureConfig noFeatureConfig) {
        int i = chunkX >> 4;
        int j = chunkZ >> 4;
        sharedSeedRandom.setSeed((long)(i ^ j << 4) ^ seed);
        sharedSeedRandom.nextInt();

        for(int k = chunkX - 10; k <= chunkX + 10; ++k) {
            for(int l = chunkZ - 10; l <= chunkZ + 10; ++l) {
                ChunkPos chunkpos = Structure.field_236381_q_.func_236392_a_(generator.func_235957_b_().func_236197_a_(Structure.field_236381_q_), seed, sharedSeedRandom, k, l);
                if (k == chunkpos.x && l == chunkpos.z) {
                    return false;
                }
            }
        }

        return true;
    }

    protected boolean isSurfaceFlat(@Nonnull ChunkGenerator generator, int chunkX, int chunkZ) {
        // Size of the area to check.
        int offset = 2 * 16;

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
}
