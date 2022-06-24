package com.stal111.valhelsia_structures.common.item;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mod Creative Mode Tabs <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.item.ValhelsiaStructuresItemGroups
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2020-06-01
 */
public class ModCreativeModeTabs {

    public static final List<ItemStack> HIDDEN_ITEMS = new ArrayList<>();

    public static final CreativeModeTab MAIN = new CreativeModeTab(ValhelsiaStructures.MOD_ID + "_main") {

        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.BRAZIER.get().asItem());
        }

        @Override
        public void fillItemList(@Nonnull NonNullList<ItemStack> items) {
            super.fillItemList(items);
            HIDDEN_ITEMS.clear();

            items.removeIf(itemStack -> {
                Item item = itemStack.getItem();

                if (Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString().contains("lapidified") || (item instanceof BlockItem blockItem && (blockItem.getBlock() == ModBlocks.EXPLORERS_TENT.get() || blockItem.getBlock() == ModBlocks.GIANT_FERN.get() || blockItem.getBlock() == ModBlocks.HIBISCUS.get()))) {
                    HIDDEN_ITEMS.add(itemStack);
                    return true;
                }
                return false;
            });
        }
    };
}
