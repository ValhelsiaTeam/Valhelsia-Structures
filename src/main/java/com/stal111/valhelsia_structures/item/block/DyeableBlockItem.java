package com.stal111.valhelsia_structures.item.block;

import com.stal111.valhelsia_structures.tileentity.IDyeableTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Dyeable Block Item
 * Valhelsia Structures - com.stal111.valhelsia_structures.item.block.DyeableBlockItem
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-12-12
 */
public class DyeableBlockItem extends BlockItem implements IDyeableArmorItem {

    public DyeableBlockItem(Block block, Properties builder) {
        super(block, builder);
    }

    @Override
    protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack stack, BlockState state) {
        boolean flag = super.onBlockPlaced(pos, world, player, stack, state);

        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof IDyeableTileEntity) {
            IDyeableTileEntity dyeableTileEntity = (IDyeableTileEntity) world.getTileEntity(pos);

            Objects.requireNonNull(dyeableTileEntity).setColor(getColor(stack));
        }

        return flag;
    }
}
