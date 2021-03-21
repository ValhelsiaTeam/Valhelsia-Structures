package com.stal111.valhelsia_structures.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Bone Pile Block
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.BonePileBlock
 *
 * @author Valhelsia Team
 * @version 16.2.0
 * @since 2021-03-18
 */
public class BonePileBlock extends Block implements IWaterLoggable {

    public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS_1_8;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape[] SHAPES = new VoxelShape[]{ VoxelShapes.empty(), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) };

    public BonePileBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(LAYERS, 1).with(WATERLOGGED, false));
    }

    @Override
    public boolean allowsMovement(BlockState state, IBlockReader world, BlockPos pos, PathType type) {
        return type == PathType.LAND && state.get(LAYERS) < 5;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.get(LAYERS)];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.get(LAYERS) - 1];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos) {
        return SHAPES[state.get(LAYERS)];
    }

    @Override
    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.get(LAYERS)];
    }

    @Override
    public boolean isTransparent(BlockState state) {
        return true;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        BlockState blockstate = world.getBlockState(pos.down());
        if (!blockstate.isIn(Blocks.BARRIER)) {
            if (!blockstate.isIn(Blocks.HONEY_BLOCK) && !blockstate.isIn(Blocks.SOUL_SAND)) {
                return Block.doesSideFillSquare(blockstate.getCollisionShape(world, pos.down()), Direction.UP) || blockstate.getBlock() == this && blockstate.get(LAYERS) == 8;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return !stateIn.isValidPosition(world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
        if (useContext.getItem().getItem() == this.asItem() && state.get(LAYERS) < 8) {
            if (useContext.replacingClickedOnBlock()) {
                return useContext.getFace() == Direction.UP;
            } else {
                return true;
            }
        } else {
            return state.get(LAYERS) == 1;
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        World world = context.getWorld();
        BlockState state = world.getBlockState(context.getPos());

        if (state.isIn(this)) {
            return state.with(LAYERS, Math.min(8, state.get(LAYERS) + 1));
        } else {
            return super.getDefaultState().with(WATERLOGGED, world.getFluidState(context.getPos()).getFluid() == Fluids.WATER);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LAYERS, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
}
