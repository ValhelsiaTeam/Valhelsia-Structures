package com.stal111.valhelsia_structures.common.block;

import com.stal111.valhelsia_structures.common.block.entity.ExplorersTentBlockEntity;
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.valhelsia.valhelsia_core.api.common.helper.VoxelShapeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * Tent Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.TentBlock
 *
 * @author Valhelsia Team
 * @since 2020-12-10
 */
public class ExplorersTentBlock extends Block implements SimpleWaterloggedBlock, EntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty SLEEPING_BAG = ModBlockStateProperties.SLEEPING_BAG;
    public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    protected static final Map<Direction, VoxelShape> SLEEPING_BAG_SHAPES = VoxelShapeHelper.getHorizontalRotatedShapes(Block.box(1.0D, 0.0D, 0.0D, 15.0D, 4.0D, 16.0D));

    public ExplorersTentBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(SLEEPING_BAG, false).setValue(OCCUPIED, false).setValue(WATERLOGGED, false));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new ExplorersTentBlockEntity(pos, state);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return level.getBlockEntity(pos) instanceof ExplorersTentBlockEntity blockEntity
                ? blockEntity.getAsItem()
                : super.getCloneItemStack(state, target, level, pos, player);
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE;
    }

    @Nonnull
    @Override
    public VoxelShape getCollisionShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return state.getValue(SLEEPING_BAG) ? SLEEPING_BAG_SHAPES.get(state.getValue(FACING)) : Shapes.empty();
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
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof ExplorersTentBlockEntity blockEntity)) {
            return ItemInteractionResult.FAIL;
        }

        if (stack.is(ModTags.Items.SLEEPING_BAGS) && blockEntity.getSleepingBag().isEmpty()) {
            blockEntity.setSleepingBag(stack.copy().split(1));

            level.setBlockAndUpdate(pos, state.setValue(SLEEPING_BAG, true));

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Nonnull
    @Override
    public InteractionResult useWithoutItem(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof ExplorersTentBlockEntity blockEntity)) {
            return InteractionResult.PASS;
        }

        if (!blockEntity.getSleepingBag().isEmpty()) {
            if (level.isClientSide()) {
                return InteractionResult.CONSUME;
            }

            if (!BedBlock.canSetSpawn(level)) {
                level.removeBlock(pos, false);

                pos = pos.relative(state.getValue(FACING).getOpposite());

                if (level.getBlockState(pos).is(this)) {
                    level.removeBlock(pos, false);
                }

                level.explode(null, level.damageSources().badRespawnPointExplosion(pos.getCenter()), null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 5.0F, true, Level.ExplosionInteraction.BLOCK);

                return InteractionResult.SUCCESS;
            }

            if (state.getValue(OCCUPIED)) {
                if (!SleepingBagBlock.kickVillagerOutOfBed(level, pos)) {
                    player.displayClientMessage(Component.translatable("block.minecraft.sleeping_bag.occupied"), true);
                }

                return InteractionResult.SUCCESS;
            }

            player.startSleepInBed(pos).ifLeft((problem) -> {
                if (problem != null && problem.getMessage().getContents() instanceof TranslatableContents contents) {
                    player.displayClientMessage(Component.translatable("block.valhelsia_structures.sleeping_bag." + contents.getKey().split("\\.")[3]), true);
                }
            });

            return InteractionResult.SUCCESS;
        }

        return super.useWithoutItem(state, level, pos, player, hitResult);
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

    @Override
    public void onRemove(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, @Nonnull BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            if (!level.isClientSide() && level.getBlockEntity(pos) instanceof ExplorersTentBlockEntity blockEntity && blockEntity.getSleepingBag() != null) {
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), blockEntity.getSleepingBag());
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SLEEPING_BAG, OCCUPIED, WATERLOGGED);
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean isBed(BlockState state, BlockGetter level, BlockPos pos, LivingEntity sleeper) {
        return state.getValue(SLEEPING_BAG);
    }
}
