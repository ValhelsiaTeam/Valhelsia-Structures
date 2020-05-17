package com.stal111.valhelsia_structures.block;

import com.google.common.collect.ImmutableMap;
import com.stal111.valhelsia_structures.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class PostBlock extends RotatedPillarBlock {

    public static final BooleanProperty ATTACHED = BooleanProperty.create("attached");

    protected static final Map<Direction.Axis, VoxelShape> SHAPES = ImmutableMap.of(
            Direction.Axis.Y, Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D),
            Direction.Axis.Z, Block.makeCuboidShape(3.0D, 3.0D, 0.0D, 13.0D, 13.0D, 16.0D),
            Direction.Axis.X, Block.makeCuboidShape(0.0D, 3.0D, 3.0D, 16.0D, 13.0D, 13.0D));

    public PostBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(ATTACHED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return state.get(ATTACHED) ? VoxelShapeHelper.add(0, -3, 0, 0, -3, 0, SHAPES.get(state.get(AXIS))) : SHAPES.get(state.get(AXIS));
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (shouldAttach(world, pos)) {
            world.setBlockState(pos, state.with(ATTACHED, true), 2);
        }
        super.onBlockPlacedBy(world, pos, state, placer, stack);
    }

    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (shouldAttach(world, pos) && !state.get(ATTACHED)) {
            world.setBlockState(pos, state.with(ATTACHED, true), 2);
        } else if (!shouldAttach(world, pos) && state.get(ATTACHED)) {
            world.setBlockState(pos, state.with(ATTACHED, false), 2);
        }
        super.neighborChanged(state, world, pos, block, fromPos, isMoving);
    }

    private boolean shouldAttach(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSolidSide(world, pos.down(), Direction.UP) && world.getBlockState(pos).get(AXIS) != Direction.Axis.Y;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ATTACHED);
        super.fillStateContainer(builder);
    }
}
