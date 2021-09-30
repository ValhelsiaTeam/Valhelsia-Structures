package com.stal111.valhelsia_structures.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nonnull;

/**
 * Valhelsia Grass Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.ValhelsiaGrassBlock
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-01-06
 */
public class ValhelsiaGrassBlock extends GrassBlock {

    public ValhelsiaGrassBlock(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public String getDescriptionId() {
        return Blocks.GRASS_BLOCK.getDescriptionId();
    }

    @Override
    public ItemStack getPickBlock(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        return new ItemStack(Blocks.GRASS_BLOCK);
    }

    @Override
    public void fillItemCategory(@Nonnull CreativeModeTab tab, @Nonnull NonNullList<ItemStack> items) {
    }
}
