package com.stal111.valhelsia_structures.common.item;

import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Valhelsia Team
 * @since 2020-06-01
 */
public class ModCreativeModeTabs {

    public static final List<ItemStack> HIDDEN_ITEMS = new ArrayList<>();

//    public static final CreativeModeTab MAIN = new CreativeModeTab(ValhelsiaStructures.MOD_ID + "_main") {
//
//        @Nonnull
//        @Override
//        public ItemStack makeIcon() {
//            return new ItemStack(ModBlocks.BRAZIER.get().asItem());
//        }
//
//        @Override
//        public void fillItemList(@Nonnull NonNullList<ItemStack> items) {
//            super.fillItemList(items);
//            HIDDEN_ITEMS.clear();
//
//            items.removeIf(itemStack -> {
//                Item item = itemStack.getItem();
//
//                if (Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString().contains("lapidified") || (item instanceof BlockItem blockItem && (blockItem.getBlock() == ModBlocks.GIANT_FERN.get() || blockItem.getBlock() == ModBlocks.HIBISCUS.get()))) {
//                    HIDDEN_ITEMS.add(itemStack);
//                    return true;
//                }
//                return false;
//            });
//        }
//    };
}
