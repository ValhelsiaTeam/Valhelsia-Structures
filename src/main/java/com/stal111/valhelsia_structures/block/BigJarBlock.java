package com.stal111.valhelsia_structures.block;

import com.stal111.valhelsia_structures.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.helper.VoxelShapeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Big Jar Block
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.BigJarBlock
 *
 * @author Valhelsia Team
 * @version 0.1.3
 * @since 2021-04-17
 */
public class BigJarBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty TREASURE = ModBlockStateProperties.TREASURE;
    public static final IntegerProperty ROTATION = ModBlockStateProperties.ROTATION_0_7;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape SHAPE = VoxelShapeHelper.combineAll(
            Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 12.0D, 13.0D),
            Block.makeCuboidShape(6.0D, 12.0D, 6.0D, 10.0D, 18.0D, 10.0D),
            Block.makeCuboidShape(5.0D, 18.0D, 5.0D, 11.0D, 20.0D, 11.0D)
    );

    public BigJarBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(TREASURE, false).with(ROTATION, 0).with(WATERLOGGED, false));
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos pos = context.getPos();
        World world = context.getWorld();

        if (pos.getY() > world.getHeight() - 2 || !world.getBlockState(pos.up()).isAir()) {
            return null;
        }

        FluidState fluidState = world.getFluidState(pos);
        boolean flag = fluidState.getFluid() == Fluids.WATER;
        return this.getDefaultState().with(WATERLOGGED, flag).with(ROTATION, MathHelper.floor((double) ((180.0F + context.getPlacementYaw()) * 8.0F / 360.0F) + 0.5D) & 7);
    }

    @Override
    public void onBlockPlacedBy(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable LivingEntity placer, @Nonnull ItemStack stack) {
        world.setBlockState(pos.up(), ModBlocks.BIG_JAR_TOP.get().getDefaultState(), 3);
    }

    @Nonnull
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(ROTATION, rot.rotate(state.get(ROTATION), 8));
    }

    @Nonnull
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.with(ROTATION, mirror.mirrorRotation(state.get(ROTATION), 8));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(TREASURE, ROTATION, WATERLOGGED);
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
}
