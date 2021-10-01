package com.stal111.valhelsia_structures.common.block;

import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Bone Pile Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.BonePileBlock
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-03-18
 */
public class BonePileBlock extends Block implements SimpleWaterloggedBlock {

    public static final IntegerProperty LAYERS = ModBlockStateProperties.LAYERS_1_5;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape[] SHAPES = new VoxelShape[] {
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D)
    };

    public BonePileBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(LAYERS, 1).setValue(WATERLOGGED, false));
    }

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPES[state.getValue(LAYERS) - 1];
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter level, @Nonnull BlockPos pos) {
        return state.getValue(LAYERS) == 1;
    }

    @Override
    public boolean canSurvive(@Nonnull BlockState state, @Nonnull LevelReader level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos.below());
        if (blockstate.is(Blocks.BARRIER)) {
            return false;
        }

        if (!blockstate.is(Blocks.HONEY_BLOCK) && !blockstate.is(Blocks.SOUL_SAND)) {
            return Block.isFaceFull(blockstate.getCollisionShape(level, pos.below()), Direction.UP);
        }

        return true;
    }

    @Nonnull
    @Override
    public BlockState updateShape(BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull LevelAccessor level, @Nonnull BlockPos currentPos, @Nonnull BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public boolean canBeReplaced(@Nonnull BlockState state, BlockPlaceContext context) {
        return !context.isSecondaryUseActive() && context.getItemInHand().is(this.asItem()) && state.getValue(LAYERS) < 5 || super.canBeReplaced(state, context);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockState state = level.getBlockState(context.getClickedPos());

        if (state.is(this)) {
            return state.setValue(LAYERS, Math.min(5, state.getValue(LAYERS) + 1));
        } else {
            return super.defaultBlockState().setValue(WATERLOGGED, level.getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LAYERS, WATERLOGGED);
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
