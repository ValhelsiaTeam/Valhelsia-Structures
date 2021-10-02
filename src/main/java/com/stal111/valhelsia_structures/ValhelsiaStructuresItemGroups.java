package com.stal111.valhelsia_structures;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

/**
 * Valhelsia Structures Item Groups
 * Valhelsia Structures - com.stal111.valhelsia_structures.ValhelsiaStructuresItemGroups
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2020-06-01
 */
public class ValhelsiaStructuresItemGroups {
    public static final CreativeModeTab MAIN = new CreativeModeTab(ValhelsiaStructures.MOD_ID + "_main") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.BRAZIER.get().asItem());
        }
    };
}
