package com.stal111.valhelsia_structures.common.block;

import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
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
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.valhelsia.valhelsia_core.api.common.helper.VoxelShapeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumMap;

/**
 * Dungeon Door Leaf Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.DungeonDoorLeafBlock
 *
 * @author Valhelsia Team
 * @version 1.18.1-0.1.1
 * @since 2021-01-22
 */
public class DungeonDoorLeafBlock extends Block implements SimpleWaterloggedBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty MIRRORED = ModBlockStateProperties.MIRRORED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE = Block.box(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape MIRRORED_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D);

    private final EnumMap<Direction, Pair<VoxelShape, VoxelShape>> shapesCache;

    public DungeonDoorLeafBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(MIRRORED, false).setValue(WATERLOGGED, false));
        this.shapesCache = this.buildShapes();
    }

    private EnumMap<Direction, Pair<VoxelShape, VoxelShape>> buildShapes() {
        EnumMap<Direction, Pair<VoxelShape, VoxelShape>> map = new EnumMap<>(Direction.class);

        VoxelShapeHelper.getHorizontalRotatedShapes(SHAPE).forEach((direction, voxelShape) -> {
            map.put(direction, Pair.of(voxelShape, VoxelShapeHelper.rotateShapeHorizontal(MIRRORED_SHAPE, direction)));
        });

        return map;
    }

    @Nonnull
    @Override
    public String getDescriptionId() {
        return ModBlocks.DUNGEON_DOOR.get().getDescriptionId();
    }

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        Pair<VoxelShape, VoxelShape> pair = this.shapesCache.get(state.getValue(FACING));

        return state.getValue(MIRRORED) ? pair.getSecond() : pair.getFirst();
    }

    @Nonnull
    @Override
    public RenderShape getRenderShape(@Nonnull BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, flag);
    }

    @Nonnull
    @Override
    public BlockState updateShape(BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull LevelAccessor level, @Nonnull BlockPos currentPos, @Nonnull BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if (!level.getBlockState(currentPos.relative(Direction.fromYRot(state.getValue(FACING).toYRot()).getOpposite())).is(ModBlocks.DUNGEON_DOOR.get())) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Nonnull
    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, @Nonnull Player player, @Nonnull BlockHitResult hit) {
        BlockPos offsetPos = pos.relative(Direction.fromYRot(state.getValue(FACING).toYRot()).getOpposite());
        BlockState offsetState = level.getBlockState(offsetPos);

        offsetState.useWithoutItem(level, player, hit);

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockPos offsetPos = pos.relative(Direction.fromYRot(state.getValue(FACING).toYRot()).getOpposite());
        BlockState offsetState = level.getBlockState(offsetPos);

        level.levelEvent(null, 2001, offsetPos, Block.getId(offsetState));
        level.setBlock(offsetPos, offsetState.getValue(WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState(), 35);
    }

    @Nonnull
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Nonnull
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Nonnull
    @Override
    public PushReaction getPistonPushReaction(@Nonnull BlockState state) {
        return PushReaction.BLOCK;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, MIRRORED, WATERLOGGED);
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
