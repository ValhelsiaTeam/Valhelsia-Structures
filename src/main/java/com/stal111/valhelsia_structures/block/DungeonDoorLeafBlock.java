package com.stal111.valhelsia_structures.block;

import com.stal111.valhelsia_structures.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.tileentity.DungeonDoorTileEntity;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.helper.VoxelShapeHelper;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Dungeon Door Leaf Block
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.DungeonDoorLeafBlock
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-22
 */
public class DungeonDoorLeafBlock extends Block implements IWaterLoggable {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final BooleanProperty MIRRORED = ModBlockStateProperties.MIRRORED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public DungeonDoorLeafBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(MIRRORED, false).with(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape shape = SHAPE;
        if (state.get(MIRRORED)) {
            shape = VoxelShapeHelper.rotateShape(shape, VoxelShapeHelper.RotationAmount.HUNDRED_EIGHTY);
        }
        return VoxelShapeHelper.rotateShapeDirection(shape, state.get(FACING));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        boolean flag = fluidstate.getFluid() == Fluids.WATER;
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED, flag);
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        if (state.get(WATERLOGGED)) {
            world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        BlockPos pos1 = pos.offset(Direction.fromAngle(state.get(FACING).getHorizontalAngle()).getOpposite());

        BlockState state1 = world.getBlockState(pos1);
        state1.getBlock().onBlockActivated(state1, world, pos1, player, hand, hit);

        return ActionResultType.func_233537_a_(world.isRemote);
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
            BlockPos pos1 = pos.offset(Direction.fromAngle(state.get(FACING).getHorizontalAngle()).getOpposite());
            DungeonDoorTileEntity tileEntity = (DungeonDoorTileEntity) world.getTileEntity(pos1);

            BlockState state1 = world.getBlockState(Objects.requireNonNull(tileEntity).getMainBlock());

            state1.getBlock().onBlockHarvested(world, tileEntity.getMainBlock(), state1, player);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, MIRRORED, WATERLOGGED);
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(ModBlocks.DUNGEON_DOOR.get());
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
}
