package com.stal111.valhelsia_structures.common.block;

import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.valhelsia.valhelsia_core.common.helper.VoxelShapeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Big Jar Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.BigJarBlock
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-04-17
 */
public class BigJarBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty TREASURE = ModBlockStateProperties.TREASURE;
    public static final IntegerProperty ROTATION = ModBlockStateProperties.ROTATION_0_7;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape SHAPE = VoxelShapeHelper.combineAll(
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 12.0D, 13.0D),
            Block.box(6.0D, 12.0D, 6.0D, 10.0D, 18.0D, 10.0D),
            Block.box(5.0D, 18.0D, 5.0D, 11.0D, 20.0D, 11.0D)
    );

    public BigJarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(TREASURE, false).setValue(ROTATION, 0).setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, false));
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();

        if (pos.getY() > level.getHeight() - 2 || !level.getBlockState(pos.above()).isAir()) {
            return null;
        }

        FluidState fluidState = level.getFluidState(pos);
        boolean flag = fluidState.getType() == Fluids.WATER;
        return this.defaultBlockState().setValue(WATERLOGGED, flag).setValue(ROTATION, Mth.floor((double) ((180.0F + context.getRotation()) * 8.0F / 360.0F) + 0.5D) & 7);
    }

    @Override
    public void setPlacedBy(@Nonnull Level level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable LivingEntity placer, @Nonnull ItemStack stack) {
        level.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }

    @Nonnull
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(ROTATION, rot.rotate(state.getValue(ROTATION), 8));
    }

    @Nonnull
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.setValue(ROTATION, mirror.mirror(state.getValue(ROTATION), 8));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TREASURE, ROTATION, HALF, WATERLOGGED);
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
