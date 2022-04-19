package com.stal111.valhelsia_structures.common.block;

import com.google.common.collect.ImmutableMap;
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.common.helper.VoxelShapeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Cut Post Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.CutPostBlock
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 */
public class CutPostBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty ATTACHED = ModBlockStateProperties.ATTACHED;
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public static final IntegerProperty PARTS = ModBlockStateProperties.PARTS_1_4;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final Map<Direction, List<VoxelShape>> SHAPES = new ImmutableMap.Builder<Direction, List<VoxelShape>>()
            .put(Direction.UP, Arrays.asList(Block.box(3.0D, 0.0D, 3.0D, 13.0D, 4.0D, 13.0D), Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D), Block.box(3.0D, 0.0D, 3.0D, 13.0D, 12.0D, 13.0D)))
            .put(Direction.DOWN, Arrays.asList(Block.box(3.0D, 12.0D, 3.0D, 13.0D, 16.0D, 13.0D), Block.box(3.0D, 8.0D, 3.0D, 13.0D, 16.0D, 13.0D), Block.box(3.0D, 4.0D, 3.0D, 13.0D, 16.0D, 13.0D)))
            .put(Direction.SOUTH, Arrays.asList(Block.box(3.0D, 3.0D, 0.0D, 13.0D, 13.0D, 4.0D), Block.box(3.0D, 3.0D, 0.0D, 13.0D, 13.0D, 8.0D), Block.box(3.0D, 3.0D, 0.0D, 13.0D, 13.0D, 12.0D)))
            .put(Direction.NORTH, Arrays.asList(Block.box(3.0D, 3.0D, 12.0D, 13.0D, 13.0D, 16.0D), Block.box(3.0D, 3.0D, 8.0D, 13.0D, 13.0D, 16.0D), Block.box(3.0D, 3.0D, 4.0D, 13.0D, 13.0D, 16.0D)))
            .put(Direction.EAST, Arrays.asList(Block.box(0.0D, 3.0D, 3.0D, 4.0D, 13.0D, 13.0D), Block.box(0.0D, 3.0D, 3.0D, 8.0D, 13.0D, 13.0D), Block.box(0.0D, 3.0D, 3.0D, 12.0D, 13.0D, 13.0D)))
            .put(Direction.WEST, Arrays.asList(Block.box(12.0D, 3.0D, 3.0D, 16.0D, 13.0D, 13.0D), Block.box(8.0D, 3.0D, 3.0D, 16.0D, 13.0D, 13.0D), Block.box(4.0D, 3.0D, 3.0D, 16.0D, 13.0D, 13.0D)))
            .build();


    public CutPostBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(ATTACHED, false).setValue(PARTS, 1).setValue(FACING, Direction.UP).setValue(WATERLOGGED, false));
    }

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        int parts = state.getValue(PARTS);
        Direction facing = state.getValue(FACING);

        VoxelShape shape = parts == 4 ? PostBlock.SHAPES.get(facing.getAxis()) : SHAPES.get(facing).get(parts - 1);

        return state.getValue(ATTACHED) ? VoxelShapeHelper.add(0, -3, 0, 0, -3, 0, shape) : shape;
    }

    @Override
    public void setPlacedBy(@Nonnull Level level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable LivingEntity placer, @Nonnull ItemStack stack) {
        if (this.shouldAttach(level, pos)) {
            level.setBlock(pos, state.setValue(ATTACHED, true), 2);
        }
        super.setPlacedBy(level, pos, state, placer, stack);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockState state = level.getBlockState(context.getClickedPos());

        if (state.is(this)) {
            return state.setValue(PARTS, Math.min(4, state.getValue(PARTS) + 1));
        }

        boolean flag = level.getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState().setValue(FACING, context.getClickedFace()).setValue(WATERLOGGED, flag);
    }

    @Nonnull
    @Override
    public BlockState updateShape(@Nonnull BlockState state, @Nonnull Direction direction, @Nonnull BlockState neighborState, @Nonnull LevelAccessor level, @Nonnull BlockPos currentPos, @Nonnull BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    @Override
    public boolean canBeReplaced(@Nonnull BlockState state, @Nonnull BlockPlaceContext context) {
        return !context.isSecondaryUseActive() && context.getItemInHand().is(this.asItem()) && state.getValue(PARTS) < 4 || super.canBeReplaced(state, context);
    }

    private boolean shouldAttach(Level world, BlockPos pos) {
        return world.getBlockState(pos.below()).isFaceSturdy(world, pos.below(), Direction.UP) && world.getBlockState(pos).getValue(FACING).getAxis() != Direction.Axis.Y;
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, Level level, BlockPos pos, Player player, ItemStack stack, ToolAction toolAction) {
        ResourceLocation location = state.getBlock().getRegistryName();

        if (!stack.canPerformAction(toolAction) || Objects.requireNonNull(location).getPath().contains("stripped")) {
            return null;
        }

        if (toolAction == ToolActions.AXE_STRIP) {
            return Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(location.getNamespace(), "cut_stripped_" + location.getPath().substring(4)))).defaultBlockState()
                    .setValue(FACING, state.getValue(FACING))
                    .setValue(ATTACHED, state.getValue(ATTACHED))
                    .setValue(PARTS, state.getValue(PARTS))
                    .setValue(WATERLOGGED, state.getValue(WATERLOGGED));
        }

        return null;
    }

    @Nonnull
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Nonnull
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.setValue(FACING, mirror.mirror(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ATTACHED, PARTS, FACING, WATERLOGGED);
    }

    @Override
    public boolean isPathfindable(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull PathComputationType type) {
        return false;
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}