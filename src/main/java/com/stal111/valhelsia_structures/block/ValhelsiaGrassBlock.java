package com.stal111.valhelsia_structures.block;

import net.minecraft.block.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

/**
 * Valhelsia Grass Block
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.ValhelsiaGrassBlock
 *
 * @author Valhelsia Team
 * @version 0.1.6
 * @since 2021-01-06
 */
public class ValhelsiaGrassBlock extends GrassBlock {

    public ValhelsiaGrassBlock(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public String getTranslationKey() {
        return Blocks.GRASS_BLOCK.getTranslationKey();
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(Blocks.GRASS_BLOCK);
    }

    @Override
    public void fillItemGroup(@Nonnull ItemGroup group, @Nonnull NonNullList<ItemStack> items) {
    }
}
