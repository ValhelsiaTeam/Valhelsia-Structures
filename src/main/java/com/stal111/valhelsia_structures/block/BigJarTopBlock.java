package com.stal111.valhelsia_structures.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.helper.VoxelShapeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Big Jar Top Block
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.BigJarTopBlock
 *
 * @author Valhelsia Team
 * @version 0.1.1
 * @since 2021-05-15
 */
public class BigJarTopBlock extends Block implements IWaterLoggable {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE = VoxelShapeHelper.add(0.0D, -16.0D, 0.0D, 0.0D, -16.0D, 0.0D, BigJarBlock.SHAPE);

    public BigJarTopBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, false));
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        return SHAPE;
    }

    @Nonnull
    @Override
    public BlockRenderType getRenderType(@Nonnull BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nonnull
    @Override
    public BlockState updatePostPlacement(@Nonnull BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull IWorld world, @Nonnull BlockPos currentPos, @Nonnull BlockPos facingPos) {
        return !state.isValidPosition(world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        boolean flag = context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER;
        return this.getDefaultState().with(WATERLOGGED, flag);
    }

    @Override
    public boolean isValidPosition(@Nonnull BlockState state, IWorldReader world, BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock() instanceof BigJarBlock;
    }

    @Override
    public void onReplaced(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull BlockState newState, boolean isMoving) {
        if (!newState.isIn(this)) {
            this.destroyJarBlock(world, pos, null);
        }
    }

    @Override
    public void onBlockHarvested(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull PlayerEntity player) {
        this.destroyJarBlock(world, pos, player);
    }

    private void destroyJarBlock(IWorld world, BlockPos pos, @Nullable PlayerEntity player) {
        BlockPos posDown = pos.down();
        BlockState stateDown = world.getBlockState(posDown);

        if (player == null) {
            spawnDrops(stateDown, (World) world, posDown);
        } else {
            spawnDrops(stateDown, (World) world, posDown, null, player, player.getHeldItemMainhand());
        }

        world.playEvent(null, 2001, posDown, Block.getStateId(stateDown));
        world.removeBlock(posDown, false);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(world.getBlockState(pos.down()).getBlock());
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
}