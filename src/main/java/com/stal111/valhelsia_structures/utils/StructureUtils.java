package com.stal111.valhelsia_structures.utils;

import com.stal111.valhelsia_structures.core.config.ModConfig;
import net.minecraft.core.Holder;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.List;

/**
 * Structure Utils <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.utils.StructureUtils
 * <p>
 * Some utilities needed for structure placement.
 *
 * @author Valhelsia Team
 * @version 1.19 - 0.2.0
 */
public class StructureUtils {

    /**
     * Checks whether any of the specified structures is near the given position.
     *
     * @param generator   the chunk generator
     * @param randomState the random state
     * @param seed        the seed to use
     * @param pos         the position to check around
     * @param structures  list of structures that should be checked for
     * @return <code>true</code> if any of the specified structures is near the given position
     */
    public static boolean isStructureInDistance(ChunkGenerator generator, RandomState randomState, long seed, ChunkPos pos, List<Holder<StructureSet>> structures) {
        if (ModConfig.COMMON.minStructureDistance.get() == 0) {
            return false;
        }

        for (Holder<StructureSet> structure : structures) {
            //TODO
            //if (generator.sta.hasStructureChunkInRange(structure, randomState, seed, pos.x, pos.z, ModConfig.COMMON.minStructureDistance.get())) {
              //  return true;
            //}
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
    public static boolean isSurfaceFlat(Structure.GenerationContext context, int size) {
        ChunkPos pos = context.chunkPos();
        int[] cornerHeights = getCornerHeights(context, pos.getMinBlockX(), size, pos.getMinBlockZ(), size);

        int minHeight = Math.min(Math.min(cornerHeights[0], cornerHeights[1]), Math.min(cornerHeights[2], cornerHeights[3]));
        int maxHeight = Math.max(Math.max(cornerHeights[0], cornerHeights[1]), Math.max(cornerHeights[2], cornerHeights[3]));

        return Math.abs(maxHeight - minHeight) <= ModConfig.COMMON.flatnessDelta.get();
    }

    public static int[] getCornerHeights(Structure.GenerationContext context, int middleBlockX, int xSize, int middleBlockZ, int zSize) {
        ChunkGenerator generator = context.chunkGenerator();
        LevelHeightAccessor heightAccessor = context.heightAccessor();
        RandomState randomState = context.randomState();

        return new int[]{generator.getFirstOccupiedHeight(middleBlockX, middleBlockZ, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, randomState), generator.getFirstOccupiedHeight(middleBlockX, middleBlockZ + zSize, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, randomState), generator.getFirstOccupiedHeight(middleBlockX + xSize, middleBlockZ, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, randomState), generator.getFirstOccupiedHeight(middleBlockX + xSize, middleBlockZ + zSize, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, randomState)};
    }
}
