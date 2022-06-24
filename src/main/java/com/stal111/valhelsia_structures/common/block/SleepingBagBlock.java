package com.stal111.valhelsia_structures.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.valhelsia.valhelsia_core.common.helper.VoxelShapeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/**
 * Sleeping Bag Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.SleepingBagBlock
 *
 * @author Valhelsia Team
 * @version 1.19 - 0.2.0
 * @since 2022-04-02
 */
public class SleepingBagBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {

    public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;
    public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.getHorizontalRotatedShapes(Block.box(1.0D, 0.0D, 1.0D, 15.0D, 4.0D, 16.0D));
    protected static final Map<Direction, VoxelShape> FEET_SHAPES = VoxelShapeHelper.getHorizontalRotatedShapes(Block.box(1.0D, 0.0D, 0.0D, 15.0D, 4.0D, 15.0D));

    public SleepingBagBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PART, BedPart.FOOT).setValue(OCCUPIED, false).setValue(WATERLOGGED, false));
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return state.getValue(PART) == BedPart.HEAD ? SHAPES.get(state.getValue(FACING)) : FEET_SHAPES.get(state.getValue(FACING));
    }

    @Nonnull
    @Override
    public InteractionResult use(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        if (level.isClientSide()) {
            return InteractionResult.CONSUME;
        }

        if (state.getValue(PART) != BedPart.HEAD) {
            pos = pos.relative(state.getValue(FACING));
            state = level.getBlockState(pos);

            if (!state.is(this)) {
                return InteractionResult.CONSUME;
            }
        }

        if (!BedBlock.canSetSpawn(level)) {
            level.removeBlock(pos, false);

            pos = pos.relative(state.getValue(FACING).getOpposite());

            if (level.getBlockState(pos).is(this)) {
                level.removeBlock(pos, false);
            }

            level.explode(null, DamageSource.badRespawnPointExplosion(), null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 5.0F, true, Explosion.BlockInteraction.DESTROY);

            return InteractionResult.SUCCESS;
        }

        if (state.getValue(OCCUPIED)) {
            if (!kickVillagerOutOfBed(level, pos)) {
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


    public static boolean kickVillagerOutOfBed(Level level, BlockPos pos) {
        List<Villager> list = level.getEntitiesOfClass(Villager.class, new AABB(pos), LivingEntity::isSleeping);

        if (list.isEmpty()) {
            return false;
        }

        list.get(0).stopSleeping();

        return true;
    }

    @Override
    public void fallOn(@Nonnull Level level, @Nonnull BlockState state, @Nonnull BlockPos pos, @Nonnull Entity entity, float height) {
        super.fallOn(level, state, pos, entity, height * 0.7F);
    }

    @Override
    public void updateEntityAfterFallOn(@Nonnull BlockGetter level, @Nonnull Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(level, entity);
        } else {
            this.bounceUp(entity);
        }
    }

    private void bounceUp(Entity entity) {
        Vec3 vec3 = entity.getDeltaMovement();

        if (vec3.y < 0.0D) {
            double d0 = entity instanceof LivingEntity ? 1.0D : 0.8D;
            entity.setDeltaMovement(vec3.x, -vec3.y * 0.33F * d0, vec3.z);
        }
    }

    @Nonnull
    @Override
    public BlockState updateShape(@Nonnull BlockState state, @Nonnull Direction direction, @Nonnull BlockState neighborState, @Nonnull LevelAccessor level, @Nonnull BlockPos currentPos, @Nonnull BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if (direction == this.getNeighbourDirection(state.getValue(PART), state.getValue(FACING))) {
            return neighborState.is(this) && neighborState.getValue(PART) != state.getValue(PART) ? state.setValue(OCCUPIED, neighborState.getValue(OCCUPIED)) : Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
        }
    }

    private Direction getNeighbourDirection(BedPart part, Direction direction) {
        return part == BedPart.FOOT ? direction : direction.getOpposite();
    }

    @Override
    public void playerWillDestroy(@Nonnull Level level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull Player player) {
        super.playerWillDestroy(level, pos, state, player);

        if (level.isClientSide() || !player.isCreative()) {
            return;
        }

        BedPart part = state.getValue(PART);

        if (part == BedPart.HEAD) {
            return;
        }

        BlockPos headPos = pos.relative(this.getNeighbourDirection(part, state.getValue(FACING)));
        BlockState headState = level.getBlockState(headPos);

        if (headState.is(this) && headState.getValue(PART) == BedPart.HEAD) {
            level.setBlock(headPos, headState.getValue(WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState(), 35);
            level.levelEvent(player, 2001, headPos, Block.getId(headState));
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection();
        BlockPos pos = context.getClickedPos();
        BlockPos relativePos = pos.relative(direction);
        Level level = context.getLevel();

        if (level.getBlockState(relativePos).canBeReplaced(context) && level.getWorldBorder().isWithinBounds(relativePos)) {
            return this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, level.getFluidState(pos).is(Fluids.WATER));
        }

        return null;
    }

    @Override
    public void setPlacedBy(@Nonnull Level level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable LivingEntity placer, @Nonnull ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);

        if (!level.isClientSide()) {
            BlockPos facingPos = pos.relative(state.getValue(FACING));

            level.setBlockAndUpdate(facingPos, state.setValue(PART, BedPart.HEAD).setValue(WATERLOGGED, level.getFluidState(facingPos).is(Fluids.WATER)));
            level.blockUpdated(pos, Blocks.AIR);

            state.updateNeighbourShapes(level, pos, 3);
        }
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, OCCUPIED, WATERLOGGED);
    }

    @Override
    public boolean isBed(BlockState state, BlockGetter level, BlockPos pos, @Nullable Entity player) {
        return true;
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
