package com.stal111.valhelsia_structures.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

/**
 * Valhelsia Stone Block
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.ValhelsiaStoneBlock
 *
 * @author Valhelsia Team
 * @version 15.0.3
 */

public class ValhelsiaStoneBlock extends Block {

    private final Supplier<Block> pickBlock;

    public ValhelsiaStoneBlock(Supplier<Block> pickBlock, Properties properties) {
        super(properties);
        this.pickBlock = pickBlock;
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(pickBlock.get());
    }

    public Supplier<Block> getPickBlock() {
        return pickBlock;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
    }
}
