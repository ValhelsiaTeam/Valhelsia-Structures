package com.stal111.valhelsia_structures.item.block;

import com.stal111.valhelsia_structures.tileentity.IDyeableTileEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Objects;

import net.minecraft.world.item.Item.Properties;

/**
 * Dyeable Block Item
 * Valhelsia Structures - com.stal111.valhelsia_structures.item.block.DyeableBlockItem
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-12-12
 */
public class DyeableBlockItem extends BlockItem implements DyeableLeatherItem {

    public DyeableBlockItem(Block block, Properties builder) {
        super(block, builder);
    }

    @Override
    protected boolean onBlockPlaced(BlockPos pos, Level world, @Nullable Player player, ItemStack stack, BlockState state) {
        boolean flag = super.onBlockPlaced(pos, world, player, stack, state);

        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof IDyeableTileEntity) {
            IDyeableTileEntity dyeableTileEntity = (IDyeableTileEntity) world.getTileEntity(pos);

            Objects.requireNonNull(dyeableTileEntity).setColor(getColor(stack));
        }

        return flag;
    }
}
