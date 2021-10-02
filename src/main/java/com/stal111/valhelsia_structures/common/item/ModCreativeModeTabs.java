package com.stal111.valhelsia_structures.common.item;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * Mod Creative Mode Tabs <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.item.ValhelsiaStructuresItemGroups
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2020-06-01
 */
public class ModCreativeModeTabs {
    public static final CreativeModeTab MAIN = new CreativeModeTab(ValhelsiaStructures.MOD_ID + "_main") {

        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.BRAZIER.get().asItem());
        }
    };
}
