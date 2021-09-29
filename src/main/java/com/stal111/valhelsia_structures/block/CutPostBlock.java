package com.stal111.valhelsia_structures.block;

import com.google.common.collect.ImmutableMap;
import com.stal111.valhelsia_structures.block.properties.ModBlockStateProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.helper.VoxelShapeHelper;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Cut Post Block
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.CutPostBlock
 *
 * @author Valhelsia Team
 * @version 16.1.0
 */
public class CutPostBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty ATTACHED = ModBlockStateProperties.ATTACHED;
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public static final IntegerProperty PARTS = ModBlockStateProperties.PARTS_1_4;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final Map<Direction, List<VoxelShape>> SHAPES = new ImmutableMap.Builder<Direction, List<VoxelShape>>()
            .put(Direction.UP, Arrays.asList(Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 4.0D, 13.0D), Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D), Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 12.0D, 13.0D)))
            .put(Direction.DOWN, Arrays.asList(Block.makeCuboidShape(3.0D, 12.0D, 3.0D, 13.0D, 16.0D, 13.0D), Block.makeCuboidShape(3.0D, 8.0D, 3.0D, 13.0D, 16.0D, 13.0D), Block.makeCuboidShape(3.0D, 4.0D, 3.0D, 13.0D, 16.0D, 13.0D)))
            .put(Direction.SOUTH, Arrays.asList(Block.makeCuboidShape(3.0D, 3.0D, 0.0D, 13.0D, 13.0D, 4.0D), Block.makeCuboidShape(3.0D, 3.0D, 0.0D, 13.0D, 13.0D, 8.0D), Block.makeCuboidShape(3.0D, 3.0D, 0.0D, 13.0D, 13.0D, 12.0D)))
            .put(Direction.NORTH, Arrays.asList(Block.makeCuboidShape(3.0D, 3.0D, 12.0D, 13.0D, 13.0D, 16.0D), Block.makeCuboidShape(3.0D, 3.0D, 8.0D, 13.0D, 13.0D, 16.0D), Block.makeCuboidShape(3.0D, 3.0D, 4.0D, 13.0D, 13.0D, 16.0D)))
            .put(Direction.EAST, Arrays.asList(Block.makeCuboidShape(0.0D, 3.0D, 3.0D, 4.0D, 13.0D, 13.0D), Block.makeCuboidShape(0.0D, 3.0D, 3.0D, 8.0D, 13.0D, 13.0D), Block.makeCuboidShape(0.0D, 3.0D, 3.0D, 12.0D, 13.0D, 13.0D)))
            .put(Direction.WEST, Arrays.asList(Block.makeCuboidShape(12.0D, 3.0D, 3.0D, 16.0D, 13.0D, 13.0D), Block.makeCuboidShape(8.0D, 3.0D, 3.0D, 16.0D, 13.0D, 13.0D), Block.makeCuboidShape(4.0D, 3.0D, 3.0D, 16.0D, 13.0D, 13.0D)))
            .build();


    public CutPostBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(ATTACHED, false).with(PARTS, 1).with(FACING, Direction.UP).with(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        int parts = state.get(PARTS);
        Direction facing = state.get(FACING);

        VoxelShape shape = parts == 4 ? PostBlock.SHAPES.get(facing.getAxis()) : SHAPES.get(facing).get(parts - 1);

        return state.get(ATTACHED) ? VoxelShapeHelper.add(0, -3, 0, 0, -3, 0, shape) : shape;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (shouldAttach(world, pos)) {
            world.setBlockState(pos, state.with(ATTACHED, true), 2);
        }
        super.onBlockPlacedBy(world, pos, state, placer, stack);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState state = context.getWorld().getBlockState(context.getPos());
        if (state.isIn(this)) {
            return state.with(PARTS, Math.min(4, state.get(PARTS) + 1));
        }
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        boolean flag = fluidstate.getFluid() == Fluids.WATER;
        return Objects.requireNonNull(super.getStateForPlacement(context)).with(FACING, context.getFace()).with(WATERLOGGED, flag);
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        if (state.get(WATERLOGGED)) {
            world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
        return useContext.getItem().getItem() == this.asItem() && state.get(PARTS) < 4 || super.isReplaceable(state, useContext);
    }

    private boolean shouldAttach(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSolidSide(world, pos.down(), Direction.UP) && world.getBlockState(pos).get(FACING).getAxis() != Direction.Axis.Y;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.with(FACING, mirror.mirror(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ATTACHED, PARTS, FACING, WATERLOGGED);
    }

    @Override
    public boolean allowsMovement(BlockState state, IBlockReader world, BlockPos pos, PathType type) {
        return false;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
}