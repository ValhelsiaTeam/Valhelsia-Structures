package com.stal111.valhelsia_structures.utils;

import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.core.config.ModConfig;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.List;

/**
 * Structure Utils <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.utils.StructureUtils
 *
 * Some utilities needed for structure placement.
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.1.0
 */
public class StructureUtils {

    /**
     * Checks whether any of the specified structures is near the given position.
     *
     * @param generator  the chunk generator
     * @param seed       the seed to use
     * @param pos        the position to check around
     * @param structures list of structures that should be checked for
     * @return <code>true</code> if any of the specified structures is near the given position
     */
    public static boolean isStructureInDistance(ChunkGenerator generator, long seed, ChunkPos pos, List<ResourceKey<StructureSet>> structures) {
        if (ModConfig.COMMON.minStructureDistance.get() == 0) {
            return false;
        }

        for (ResourceKey<StructureSet> structure : structures) {
            if (generator.hasFeatureChunkInRange(structure, seed, pos.x, pos.z, ModConfig.COMMON.minStructureDistance.get())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether the height difference of the four corners of the structure is smaller than the flatness delta set in the {@link com.stal111.valhelsia_structures.core.config.CommonConfig}.
     *
     * @param context the piece generator context
     * @param size    the size of the structure
     * @return <code>true</code> if the surface is flat at the position
     */
    public static boolean isSurfaceFlat(PieceGeneratorSupplier.Context<JigsawConfiguration> context, Pair<Integer, Integer> size) {
        ChunkPos pos = context.chunkPos();
        int[] cornerHeights = context.getCornerHeights(pos.getMiddleBlockX(), size.getFirst(), pos.getMiddleBlockZ(), size.getSecond());

        int minHeight = Math.min(Math.min(cornerHeights[0], cornerHeights[1]), Math.min(cornerHeights[2], cornerHeights[3]));
        int maxHeight = Math.max(Math.max(cornerHeights[0], cornerHeights[1]), Math.max(cornerHeights[2], cornerHeights[3]));

        return Math.abs(maxHeight - minHeight) <= ModConfig.COMMON.flatnessDelta.get();
    }
}
