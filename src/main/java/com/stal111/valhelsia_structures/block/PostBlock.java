package com.stal111.valhelsia_structures.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class PostBlock extends RotatedPillarBlock {

    protected static final VoxelShape SHAPE_Y = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    protected static final VoxelShape SHAPE_Z = Block.makeCuboidShape(3.0D, 3.0D, 0.0D, 13.0D, 13.0D, 16.0D);
    protected static final VoxelShape SHAPE_X = Block.makeCuboidShape(0.0D, 3.0D, 3.0D, 16.0D, 13.0D, 13.0D);

    public PostBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.get(AXIS) == Direction.Axis.Y ? SHAPE_Y : state.get(AXIS) == Direction.Axis.Z ? SHAPE_Z : SHAPE_X;
    }
}
