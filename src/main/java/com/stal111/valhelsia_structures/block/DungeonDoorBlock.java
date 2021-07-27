package com.stal111.valhelsia_structures.block;

import com.stal111.valhelsia_structures.block.properties.DungeonDoorPart;
import com.stal111.valhelsia_structures.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.tileentity.DungeonDoorTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.helper.VoxelShapeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Dungeon Door Block
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.DungeonDoorBlock
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-13
 */
public class DungeonDoorBlock extends Block implements IWaterLoggable {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final EnumProperty<DungeonDoorPart> PART = ModBlockStateProperties.DUNGEON_DOOR_PART;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 12.0D);
    protected static final VoxelShape SHAPE_OPEN = Block.makeCuboidShape(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);

    public DungeonDoorBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(PART, DungeonDoorPart.MIDDLE_1).with(OPEN, false).with(WATERLOGGED, false));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DungeonDoorTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return state.get(PART) == DungeonDoorPart.MIDDLE_1;
    }

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        VoxelShape shape = state.get(OPEN) ? SHAPE_OPEN : SHAPE;
        if (state.get(OPEN) && state.get(PART).isRight()) {
            shape = VoxelShapeHelper.add(-12.0D, 0.0D, 0.0D, -12.0D, 0.0D, 0.0D, shape);
        }
        return state.get(OPEN) && state.get(PART).isMiddle() ? VoxelShapes.empty() : VoxelShapeHelper.rotateShapeDirection(shape, state.get(FACING));
    }

    @Nonnull
    @Override
    public BlockRenderType getRenderType(@Nonnull BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos pos = context.getPos();

        if (!canPlace(context)) {
            return null;
        }

        for (Position position : Position.values()) {
            for (int i = 0; i < 4; i++) {
                BlockPos pos1 = pos.up(i);
                if (position != Position.MIDDLE) {
                    pos1 = pos1.offset(Direction.fromAngle(context.getPlacementHorizontalFacing().getOpposite().getHorizontalAngle() + position.getDirection().getHorizontalAngle()));
                }
                if (!context.getWorld().getBlockState(pos1).isReplaceable(context)) {
                    return null;
                }
            }
        }
        FluidState fluidState = context.getWorld().getFluidState(context.getPos());
        boolean flag = fluidState.getFluid() == Fluids.WATER;

        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED, flag);
    }

    @Nonnull
    @Override
    public BlockState updatePostPlacement(BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull IWorld world, @Nonnull BlockPos currentPos, @Nonnull BlockPos facingPos) {
        if (state.get(WATERLOGGED)) {
            world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        if (!state.isValidPosition(world, currentPos)) {
            this.breakDoor(world, currentPos, state, null);
            return Blocks.AIR.getDefaultState();
        }

        return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public void onBlockPlacedBy(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable LivingEntity placer, @Nonnull ItemStack stack) {
        for (Position position : Position.values()) {
            for (int i = 0; i < 4; i++) {
                BlockPos pos1 = pos.up(i);
                if (position != Position.MIDDLE) {
                    pos1 = pos1.offset(Direction.fromAngle(state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + position.getDirection().getHorizontalAngle()));
                }
                if (pos1 != pos) {
                    DungeonDoorPart part = DungeonDoorPart.valueOf(position + "_" + (i + 1));

                    FluidState fluidstate = world.getFluidState(pos1);
                    boolean flag = fluidstate.getFluid() == Fluids.WATER;

                    world.setBlockState(pos1, state.with(PART, part).with(WATERLOGGED, flag), 3);
                }
            }
        }
    }

    @Nonnull
    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand hand, @Nonnull BlockRayTraceResult hit) {
        boolean open = !state.get(OPEN);

        Map<BlockPos, BlockState> map = new HashMap<>();
        boolean canOpen = true;

        if (!world.isRemote()) {
            for (Position position : Position.values()) {
                for (int i = 0; i < 4; i++) {
                    BlockPos offsetPos = this.getMainBlock(pos, state).up(i);

                    if (position != Position.MIDDLE) {
                        offsetPos = offsetPos.offset(Direction.fromAngle(state.get(FACING).getHorizontalAngle() + position.getDirection().getHorizontalAngle()));
                        BlockPos leafPos = offsetPos.offset(state.get(FACING));

                        if (open) {
                            if (!world.getBlockState(leafPos).isReplaceable(new BlockItemUseContext(player, hand, new ItemStack(ModBlocks.DUNGEON_DOOR.get()), hit))) {
                                canOpen = false;
                            }
                            map.put(leafPos, ModBlocks.DUNGEON_DOOR_LEAF.get().getDefaultState()
                                    .with(HorizontalBlock.HORIZONTAL_FACING, state.get(FACING))
                                    .with(ModBlockStateProperties.MIRRORED, position == Position.RIGHT)
                                    .with(BlockStateProperties.WATERLOGGED, world.getBlockState(leafPos).getBlock() == Blocks.WATER));
                        } else {
                            map.put(leafPos, world.getBlockState(leafPos).get(WATERLOGGED) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState());
                        }
                    }
                    map.put(offsetPos, world.getBlockState(offsetPos).with(OPEN, open));
                }
            }
        }
        if (canOpen) {
            map.forEach((blockPos, blockState) -> world.setBlockState(blockPos, blockState, blockState == Blocks.AIR.getDefaultState() ? 35 : 10));
        }

        return ActionResultType.func_233537_a_(world.isRemote);
    }

    @Override
    public void onBlockHarvested(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull PlayerEntity player) {
        this.breakDoor(world, pos, state, player);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos posDown = pos.down();
        BlockState stateDown = world.getBlockState(posDown);

        if (state.get(PART).isBottom()) {
            return stateDown.isSolidSide(world, posDown, Direction.UP);
        } else if (state.get(PART).getString().endsWith("3") && !world.getBlockState(pos.up()).isIn(this)) {
            return false;
        }

        return stateDown.isIn(this);
    }

    @Nonnull
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Nonnull
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.toRotation(state.get(FACING)));
    }

    private boolean canPlace(BlockItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();

        if (pos.getY() > context.getWorld().getHeight() - 4) {
            return false;
        }

        for (Position position : Position.values()) {
            BlockPos posDown = pos.down();
            if (position != Position.MIDDLE) {
                posDown = posDown.offset(Direction.fromAngle(context.getPlacementHorizontalFacing().getOpposite().getHorizontalAngle() + position.getDirection().getHorizontalAngle()));
            }
            if (!world.getBlockState(posDown).isSolidSide(world, pos.down(), Direction.UP)) {
                return false;
            }
        }
        return true;
    }

    private void breakDoor(IWorld world, BlockPos pos, BlockState state, @Nullable PlayerEntity player) {
        BlockPos mainPos = getMainBlock(pos, state);

        if (world.isRemote() || (player != null && !player.abilities.isCreativeMode)) {
            return;
        }

        for (Position position : Position.values()) {
            for (int k = 0; k < 4; k++) {
                BlockPos pos1 = mainPos.up(k);
                if (position != Position.MIDDLE) {
                    pos1 = pos1.offset(Direction.fromAngle(state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + position.getDirection().getHorizontalAngle()));

                    if (state.get(OPEN)) {
                        BlockPos pos2 = pos1.offset(Direction.fromAngle(state.get(FACING).getHorizontalAngle()));
                        BlockState state1 = world.getBlockState(pos2);

                        if (state1.getBlock() == ModBlocks.DUNGEON_DOOR_LEAF.get()) {
                            world.playEvent(player, 2001, pos2, Block.getStateId(state1));
                            world.setBlockState(pos2, state1.get(WATERLOGGED) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState(), 35);
                        }
                    }
                }
                BlockState state1 = world.getBlockState(pos1);
                if (state1.getBlock() == ModBlocks.DUNGEON_DOOR.get()) {
                    world.playEvent(player, 2001, pos1, Block.getStateId(state1));
                    world.setBlockState(pos1, state1.get(WATERLOGGED) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState(), 35);
                }
            }
        }
    }

    @Nonnull
    @Override
    public PushReaction getPushReaction(@Nonnull BlockState state) {
        return PushReaction.BLOCK;
    }

    private BlockPos getMainBlock(BlockPos pos, BlockState state) {
        DungeonDoorPart part = state.get(PART);

        if (!part.isMiddle()) {
            pos = pos.offset(Direction.fromAngle(state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + Position.getPositionFromPart(part).getDirection().getHorizontalAngle()).getOpposite());
        }

        return pos.down(Integer.parseInt(String.valueOf(part.getString().charAt(part.getString().length() - 1))) - 1);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, OPEN, WATERLOGGED);
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
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
    }
}
