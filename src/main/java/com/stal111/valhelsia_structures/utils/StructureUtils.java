package com.stal111.valhelsia_structures.utils;

import net.minecraft.util.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;

import java.util.Random;

/**
 * Structure Utils
 * Valhelsia Structure - com.stal111.valhelsia_structures.utils.StructureUtils
 *
 * @author Valhelsia Team
 * @version 15.0.3
 */
public class StructureUtils {
    /**
     * Get Random Direction
     * @param rand Instance of Random to use.
     * @return A random cardinal direction, of N/S/E/W.
     */
    public static Direction getRandomDir(final Random rand) {
        return Direction.byHorizontalIndex(rand.nextInt(4));
    }

    /**
     * Gets the lowest height of four corners.
     * @param world The world to use the heightmap from.
     * @param x X Coordinate.
     * @param z Y Coordinate.
     * @param xSize X Size.
     * @param zSize Z Size.
     * @return The lowest height of the four corners.
     */
    public static int getLowestHeight(IWorld world, int x, int z, int xSize, int zSize) {
        int h0 = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, x, z);
        int h1 = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, x + xSize, z);
        int h2 = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, x, z + zSize);
        int h3 = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, x + xSize, z + zSize);
        return Math.min(Math.min(h0, h1), Math.min(h2, h3));
    }
}
