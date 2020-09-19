package com.stal111.valhelsia_structures;

import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

/**
 * Valhelsia Structures Item Groups
 * Valhelsia Structures - com.stal111.valhelsia_structures.ValhelsiaStructuresItemGroups
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2020-06-01
 */
public class ValhelsiaStructuresItemGroups {
    public static final ItemGroup MAIN = new ItemGroup(ValhelsiaStructures.MOD_ID + "_main") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.BRAZIER.get().asItem());
        }
    };
}
