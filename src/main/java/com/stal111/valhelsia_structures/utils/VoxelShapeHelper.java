package com.stal111.valhelsia_structures.utils;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.HashSet;
import java.util.Set;

public class VoxelShapeHelper {

    public static VoxelShape add(double x1, double y1, double z1, double x2, double y2, double z2, VoxelShape... shapes) {
        Set<VoxelShape> result = new HashSet<>();
        for (VoxelShape shape : shapes) {
            shape.forEachBox((x, y, z, x3, y3, z3) -> {
                x = (x * 16); x3 = (x3 * 16);
                y = (y * 16); y3 = (y3 * 16);
                z = (z * 16); z3 = (z3 * 16);

                result.add(Block.makeCuboidShape(x + x1, y + y1, z + z1, x3 + x2, y3 + y2, z3 + z2));
            });
        }
        return result.stream().reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
    }
}
