package com.stal111.valhelsia_structures.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nonnull;

/**
 * Valhelsia Grass Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.ValhelsiaGrassBlock
 *
 * @author Valhelsia Team
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
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        return new ItemStack(Blocks.GRASS_BLOCK);
    }
}
