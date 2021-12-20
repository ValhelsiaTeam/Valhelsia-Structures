package com.stal111.valhelsia_structures.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 * Valhelsia Stone Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.ValhelsiaStoneBlock
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 */

public class ValhelsiaStoneBlock extends Block {

    private final Supplier<Block> pickBlock;

    public ValhelsiaStoneBlock(Supplier<Block> pickBlock, Properties properties) {
        super(properties);
        this.pickBlock = pickBlock;
    }

    @Nonnull
    @Override
    public String getDescriptionId() {
        return this.pickBlock.get().getDescriptionId();
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        return new ItemStack(this.pickBlock.get());
    }

    @Override
    public void fillItemCategory(@Nonnull CreativeModeTab tab, @Nonnull NonNullList<ItemStack> items) {
    }
}
