package com.stal111.valhelsia_structures.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;

/**
 * Valhelsia Grass Block
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.ValhelsiaGrassBlock
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-06
 */
public class ValhelsiaGrassBlock extends GrassBlock {

    public ValhelsiaGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(Blocks.GRASS_BLOCK);
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
    }
}
