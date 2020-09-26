package com.stal111.valhelsia_structures.utils;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class ModTags {

    public static class Items {
        public static final ITag.INamedTag<Item> AXE_CRAFTING_BLACKLISTED = ItemTags.makeWrapperTag( ValhelsiaStructures.MOD_ID + ":axe_crafting_blacklisted");
    }
}
