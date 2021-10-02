package com.stal111.valhelsia_structures.common.item;

import com.stal111.valhelsia_structures.common.block.entity.DyeableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Dyeable Block Item <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.item.DyeableBlockItem
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2020-12-12
 */
public class DyeableBlockItem extends BlockItem implements DyeableLeatherItem {

    public DyeableBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    protected boolean updateCustomBlockEntityTag(@Nonnull BlockPos pos, Level level, @Nullable Player player, @Nonnull ItemStack stack, @Nonnull BlockState state) {
        if (level.getBlockEntity(pos) instanceof DyeableBlockEntity blockEntity) {
            blockEntity.setColor(getColor(stack));
        }
        return super.updateCustomBlockEntityTag(pos, level, player, stack, state);
    }
}
