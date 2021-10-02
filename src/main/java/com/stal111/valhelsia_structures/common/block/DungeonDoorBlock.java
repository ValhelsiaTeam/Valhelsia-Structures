package com.stal111.valhelsia_structures.common.block;

import com.stal111.valhelsia_structures.common.block.properties.DungeonDoorPart;
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.common.block.entity.DungeonDoorBlockEntity;
import com.stal111.valhelsia_structures.core.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.valhelsia.valhelsia_core.common.helper.VoxelShapeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Dungeon Door Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.DungeonDoorBlock
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-01-13
 */
public class DungeonDoorBlock extends Block implements SimpleWaterloggedBlock, EntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<DungeonDoorPart> PART = ModBlockStateProperties.DUNGEON_DOOR_PART;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 12.0D);
    protected static final VoxelShape SHAPE_OPEN = Block.box(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);

    public DungeonDoorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(PART, DungeonDoorPart.MIDDLE_1).setValue(OPEN, false).setValue(WATERLOGGED, false));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return state.getValue(PART) == DungeonDoorPart.MIDDLE_1 ? new DungeonDoorBlockEntity(pos, state) : null;
    }

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        boolean open = state.getValue(OPEN);
        VoxelShape shape = open ? SHAPE_OPEN : SHAPE;

        if (open && state.getValue(PART).isRight()) {
            shape = VoxelShapeHelper.add(-12.0D, 0.0D, 0.0D, -12.0D, 0.0D, 0.0D, shape);
        }
        return open && state.getValue(PART).isMiddle() ? Shapes.empty() : VoxelShapeHelper.rotateShapeDirection(shape, state.getValue(FACING));
    }

    @Nonnull
    @Override
    public RenderShape getRenderShape(@Nonnull BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();

        if (!canPlace(context)) {
            return null;
        }

        for (Position position : Position.values()) {
            for (int i = 0; i < 4; i++) {
                BlockPos offsetPos = pos.above(i);
                if (position != Position.MIDDLE) {
                    offsetPos = position.offsetBlockPos(offsetPos, context.getHorizontalDirection().getOpposite());
                }
                if (!context.getLevel().getBlockState(offsetPos).canBeReplaced(context)) {
                    return null;
                }
            }
        }
        boolean flag = context.getLevel().getFluidState(pos).getType() == Fluids.WATER;

        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, flag);
    }

    @Nonnull
    @Override
    public BlockState updateShape(BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull LevelAccessor level, @Nonnull BlockPos currentPos, @Nonnull BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if (!state.canSurvive(level, currentPos)) {
            this.breakDoor(level, currentPos, state, null);
            return Blocks.AIR.defaultBlockState();
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public void setPlacedBy(@Nonnull Level world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable LivingEntity placer, @Nonnull ItemStack stack) {
        for (Position position : Position.values()) {
            for (int i = 0; i < 4; i++) {
                BlockPos offsetPos = pos.above(i);
                if (position != Position.MIDDLE) {
                    offsetPos = position.offsetBlockPos(offsetPos,state.getValue(FACING));
                }
                if (offsetPos != pos) {
                    DungeonDoorPart part = DungeonDoorPart.valueOf(position + "_" + (i + 1));
                    boolean flag = world.getFluidState(offsetPos).getType() == Fluids.WATER;

                    world.setBlock(offsetPos, state.setValue(PART, part).setValue(WATERLOGGED, flag), 3);
                }
            }
        }
    }

    @Nonnull
    @Override
    public InteractionResult use(BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        boolean open = !state.getValue(OPEN);

        Map<BlockPos, BlockState> map = new HashMap<>();
        boolean canOpen = true;

        if (!level.isClientSide()) {
            for (Position position : Position.values()) {
                for (int i = 0; i < 4; i++) {
                    BlockPos offsetPos = this.getMainBlock(pos, state).above(i);

                    if (position != Position.MIDDLE) {
                        offsetPos = position.offsetBlockPos(offsetPos, state.getValue(FACING));
                        BlockPos leafPos = offsetPos.relative(state.getValue(FACING));

                        if (open) {
                            if (!level.getBlockState(leafPos).canBeReplaced(new BlockPlaceContext(player, hand, new ItemStack(ModBlocks.DUNGEON_DOOR.get()), hit))) {
                                canOpen = false;
                            }
                            map.put(leafPos, ModBlocks.DUNGEON_DOOR_LEAF.get().defaultBlockState()
                                    .setValue(FACING, state.getValue(FACING))
                                    .setValue(ModBlockStateProperties.MIRRORED, position == Position.RIGHT)
                                    .setValue(WATERLOGGED, level.getBlockState(leafPos).getBlock() == Blocks.WATER));
                        } else {
                            map.put(leafPos, level.getBlockState(leafPos).getValue(WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState());
                        }
                    }
                    map.put(offsetPos, level.getBlockState(offsetPos).setValue(OPEN, open));
                }
            }
        }
        if (canOpen) {
            map.forEach((blockPos, blockState) -> level.setBlock(blockPos, blockState, blockState == Blocks.AIR.defaultBlockState() ? 35 : 10));
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public void playerWillDestroy(@Nonnull Level level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull Player player) {
        this.breakDoor(level, pos, state, player);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos posDown = pos.below();
        BlockState stateDown = level.getBlockState(posDown);

        if (state.getValue(PART).isBottom()) {
            return stateDown.isFaceSturdy(level, posDown, Direction.UP);
        } else if (state.getValue(PART).getSerializedName().endsWith("3") && !level.getBlockState(pos.above()).is(this)) {
            return false;
        }

        return stateDown.is(this);
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

    private boolean canPlace(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        if (pos.getY() > level.getHeight() - 4) {
            return false;
        }

        for (Position position : Position.values()) {
            BlockPos posDown = pos.below();
            if (position != Position.MIDDLE) {
                posDown = position.offsetBlockPos(posDown, context.getHorizontalDirection().getOpposite());
            }
            if (!level.getBlockState(posDown).isFaceSturdy(level, pos.below(), Direction.UP)) {
                return false;
            }
        }
        return true;
    }

    private void breakDoor(LevelAccessor level, BlockPos pos, BlockState state, @Nullable Player player) {
        BlockPos mainPos = getMainBlock(pos, state);

        if (level.isClientSide() || (player != null && !player.isCreative())) {
            return;
        }

        for (Position position : Position.values()) {
            for (int k = 0; k < 4; k++) {
                BlockPos offsetPos = mainPos.above(k);
                if (position != Position.MIDDLE) {
                    offsetPos = position.offsetBlockPos(offsetPos, state.getValue(FACING));

                    if (state.getValue(OPEN)) {
                        offsetPos = offsetPos.relative(state.getValue(FACING));
                        BlockState offsetState = level.getBlockState(offsetPos);

                        if (offsetState.getBlock() == ModBlocks.DUNGEON_DOOR_LEAF.get()) {
                            level.levelEvent(player, 2001, offsetPos, Block.getId(offsetState));
                            level.setBlock(offsetPos, offsetState.getValue(WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState(), 35);
                        }
                    }
                }
                BlockState state1 = level.getBlockState(offsetPos);
                if (state1.getBlock() == ModBlocks.DUNGEON_DOOR.get()) {
                    level.levelEvent(player, 2001, offsetPos, Block.getId(state1));
                    level.setBlock(offsetPos, state1.getValue(WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState(), 35);
                }
            }
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@Nonnull Level level, @Nonnull BlockState state, @Nonnull BlockEntityType<T> blockEntityType) {
        if (level.isClientSide()) {
            return BaseEntityBlock.createTickerHelper(blockEntityType, ModBlockEntities.DUNGEON_DOOR.get(), DungeonDoorBlockEntity::clientTick);
        }
        return null;
    }

    @Nonnull
    @Override
    public PushReaction getPistonPushReaction(@Nonnull BlockState state) {
        return PushReaction.BLOCK;
    }

    private BlockPos getMainBlock(BlockPos pos, BlockState state) {
        DungeonDoorPart part = state.getValue(PART);

        if (!part.isMiddle()) {
            pos = Position.getPositionFromPart(part).offsetBlockPos(pos, state.getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite());
        }

        return pos.below(Integer.parseInt(String.valueOf(part.getSerializedName().charAt(part.getSerializedName().length() - 1))) - 1);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, OPEN, WATERLOGGED);
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    private enum Position {
        LEFT(Direction.WEST),
        MIDDLE(null),
        RIGHT(Direction.EAST);

        private final Direction direction;

        Position(Direction direction) {
            this.direction = direction;
        }

        public Direction getDirection() {
            return direction;
        }

        public static Position getPositionFromPart(DungeonDoorPart part) {
            return part.isLeft() ? Position.LEFT : part.isRight() ? Position.RIGHT : Position.MIDDLE;
        }

        public BlockPos offsetBlockPos(BlockPos pos, Direction direction) {
            return pos.relative(Direction.fromYRot(direction.toYRot() + this.getDirection().toYRot()));
        }
    }
}
