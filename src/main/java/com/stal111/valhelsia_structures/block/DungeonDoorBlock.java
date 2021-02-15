package com.stal111.valhelsia_structures.block;

import com.stal111.valhelsia_structures.block.properties.DungeonDoorPart;
import com.stal111.valhelsia_structures.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.tileentity.DungeonDoorTileEntity;
import net.minecraft.block.*;
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
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.helper.VoxelShapeHelper;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        VoxelShape shape = state.get(OPEN) ? SHAPE_OPEN : SHAPE;
        if (state.get(OPEN) && state.get(PART).getString().contains("right")) {
            shape = VoxelShapeHelper.add(-12.0D, 0.0D, 0.0D, -12.0D, 0.0D, 0.0D, shape);
        }
        return state.get(OPEN) && state.get(PART).getString().contains("middle") ? VoxelShapes.empty() : VoxelShapeHelper.rotateShapeDirection(shape, state.get(FACING));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos pos = context.getPos();
        if (pos.getY() < 253) {
            boolean canPlace = true;
            for (Position position : Position.values()) {
                for (int i = 0; i < 4; i++) {
                    BlockPos pos1 = pos.up(i);
                    if (position != Position.MIDDLE) {
                        pos1 = pos1.offset(Direction.fromAngle(context.getPlacementHorizontalFacing().getOpposite().getHorizontalAngle() + position.getDirection().getHorizontalAngle()));
                    }
                    if (!context.getWorld().getBlockState(pos1).isReplaceable(context)) {
                        canPlace = false;
                    }
                }
            }
            if (canPlace) {
                FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
                boolean flag = fluidstate.getFluid() == Fluids.WATER;
                return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED, flag);
            }
        }
        return null;
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        if (state.get(WATERLOGGED)) {
            world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        for (Position position : Position.values()) {
            for (int i = 0; i < 4; i++) {
                BlockPos pos1 = pos.up(i);
                if (position != Position.MIDDLE) {
                    pos1 = pos1.offset(Direction.fromAngle(state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + position.getDirection().getHorizontalAngle()));
                }
                if (pos1 != pos) {
                    DungeonDoorPart dungeonDoorPart = DungeonDoorPart.valueOf(position + "_" + (i + 1));

                    FluidState fluidstate = world.getFluidState(pos1);
                    boolean flag = fluidstate.getFluid() == Fluids.WATER;

                    world.setBlockState(pos1, state.with(PART, dungeonDoorPart).with(WATERLOGGED, flag), 3);
                }
                DungeonDoorTileEntity dungeonDoorTileEntity = ((DungeonDoorTileEntity) world.getTileEntity(pos1));
                Objects.requireNonNull(dungeonDoorTileEntity).setMainBlock(pos);
            }
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        boolean open = !state.get(OPEN);

        Map<BlockPos, BlockState> map = new HashMap<>();
        boolean canOpen = true;

        if (!world.isRemote()) {
            DungeonDoorTileEntity tileEntity = (DungeonDoorTileEntity) world.getTileEntity(pos);

            for (Position position : Position.values()) {
                for (int k = 0; k < 4; k++) {
                    BlockPos pos1 = Objects.requireNonNull(tileEntity).getMainBlock().up(k);
                    if (position != Position.MIDDLE) {
                        pos1 = pos1.offset(Direction.fromAngle(state.get(FACING).getHorizontalAngle() + position.getDirection().getHorizontalAngle()));

                        BlockPos pos2 = pos1.offset(state.get(FACING));
                        if (open) {
                            if (!world.getBlockState(pos2).isReplaceable(new BlockItemUseContext(player, hand, new ItemStack(ModBlocks.DUNGEON_DOOR.get()), hit))) {
                                canOpen = false;
                            }
                            map.put(pos2, ModBlocks.DUNGEON_DOOR_LEAF.get().getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, state.get(FACING)).with(ModBlockStateProperties.MIRRORED, position == Position.RIGHT));
                        } else {
                            map.put(pos2, Blocks.AIR.getDefaultState());
                        }
                    }
                    if (pos1 != pos) {
                        map.put(pos1, world.getBlockState(pos1).with(OPEN, open));
                    }
                }
            }
        }
        if (canOpen) {
            world.setBlockState(pos, state, 10);
            map.forEach((blockPos, blockState) -> world.setBlockState(blockPos, blockState, blockState == Blocks.AIR.getDefaultState() ? 35 : 10));
        }

        return ActionResultType.func_233537_a_(world.isRemote);
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
            DungeonDoorTileEntity tileEntity = (DungeonDoorTileEntity) world.getTileEntity(pos);

            for (Position position : Position.values()) {
                for (int k = 0; k < 4; k++) {
                    BlockPos pos1 = Objects.requireNonNull(tileEntity).getMainBlock().up(k);
                    if (position != Position.MIDDLE) {
                        pos1 = pos1.offset(Direction.fromAngle(state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + position.getDirection().getHorizontalAngle()));

                        if (state.get(OPEN)) {
                            BlockPos pos2 = pos1.offset(Direction.fromAngle(state.get(FACING).getHorizontalAngle()));
                            world.playEvent(player, 2001, pos2, Block.getStateId(world.getBlockState(pos2)));
                            world.setBlockState(pos2, Blocks.AIR.getDefaultState(), 35);

                        }
                    }
                    world.playEvent(player, 2001, pos1, Block.getStateId(world.getBlockState(pos1)));
                    world.setBlockState(pos1, Blocks.AIR.getDefaultState(), 35);
                }
            }

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
        builder.add(FACING, PART, OPEN, WATERLOGGED);
    }

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
    }
}