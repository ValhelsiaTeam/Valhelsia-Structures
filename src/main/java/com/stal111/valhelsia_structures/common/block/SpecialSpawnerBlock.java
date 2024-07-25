package com.stal111.valhelsia_structures.common.block;

import com.stal111.valhelsia_structures.common.block.entity.SpecialSpawnerBlockEntity;
import com.stal111.valhelsia_structures.core.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Special Spawner Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.SpecialSpawnerBlock
 *
 * @author Valhelsia Team
 * @version 1.19 - 0.2.0
 */
public class SpecialSpawnerBlock extends Block implements EntityBlock {

    public SpecialSpawnerBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new SpecialSpawnerBlockEntity(pos, state);
    }

    @Override
    public int getExpDrop(BlockState state, LevelAccessor level, BlockPos pos, @org.jetbrains.annotations.Nullable BlockEntity blockEntity, @org.jetbrains.annotations.Nullable Entity breaker, ItemStack tool) {
        return 10 + level.getRandom().nextInt(15) + level.getRandom().nextInt(15);
    }

    @Nonnull
    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof SpecialSpawnerBlockEntity blockEntity) || !(stack.getItem() instanceof SpawnEggItem spawnEggItem)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        if (level.isClientSide()) {
            return ItemInteractionResult.SUCCESS;
        }

        SpecialBaseSpawner spawner = blockEntity.getSpawner();

        spawner.setEntityId(spawnEggItem.getType(stack));
        blockEntity.setChanged();
        level.sendBlockUpdated(pos, state, state, 3);

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return ItemInteractionResult.CONSUME;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@Nonnull Level level, @Nonnull BlockState state, @Nonnull BlockEntityType<T> blockEntityType) {
        if (level.isClientSide()) {
            return BaseEntityBlock.createTickerHelper(blockEntityType, ModBlockEntities.SPECIAL_SPAWNER.get(), SpecialSpawnerBlockEntity::clientTick);
        }
        return BaseEntityBlock.createTickerHelper(blockEntityType, ModBlockEntities.SPECIAL_SPAWNER.get(), SpecialSpawnerBlockEntity::serverTick);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return ItemStack.EMPTY;
    }
}