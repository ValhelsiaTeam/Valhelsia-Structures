package com.stal111.valhelsia_structures.utils;

import net.minecraft.util.Direction;

import java.util.Random;

public class StructureUtils {

    public static Direction getRandomDir(final Random rand) {
        final int t = rand.nextInt(4);
        Direction dir = Direction.NORTH;
        switch (t) {
            case 0: {
                dir = Direction.NORTH;
                break;
            }
            case 1: {
                dir = Direction.SOUTH;
                break;
            }
            case 2: {
                dir = Direction.EAST;
                break;
            }
            case 3: {
                dir = Direction.WEST;
                break;
            }
        }
        return dir;
    }
}
