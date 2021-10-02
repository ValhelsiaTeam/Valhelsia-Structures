package com.stal111.valhelsia_structures.common.block;

import com.stal111.valhelsia_structures.common.block.entity.SpecialSpawnerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Special Spawner Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.SpecialSpawnerBlock
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
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
    public int getExpDrop(BlockState state, LevelReader world, BlockPos pos, int fortune, int silktouch) {
        return 10 + RANDOM.nextInt(15) + RANDOM.nextInt(15);
    }

    @Nonnull
    @Override
    public InteractionResult use(@Nonnull BlockState state, Level level, @Nonnull BlockPos pos, Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult result) {
        ItemStack stack = player.getItemInHand(hand);

        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        if (!(level.getBlockEntity(pos) instanceof SpecialSpawnerBlockEntity blockEntity) || !(stack.getItem() instanceof SpawnEggItem)) {
            return InteractionResult.PASS;
        }

        BaseSpawner spawner = blockEntity.getSpawner();

        spawner.setEntityId(((SpawnEggItem) stack.getItem()).getType(stack.getTag()));
        blockEntity.setChanged();
        level.sendBlockUpdated(pos, state, state, 3);

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Nonnull
    @Override
    public ItemStack getCloneItemStack(@Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        return ItemStack.EMPTY;
    }
}