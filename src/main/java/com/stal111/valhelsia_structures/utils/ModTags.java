package com.stal111.valhelsia_structures.utils;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class ModTags {

    public static class Blocks {
        public static final ITag.INamedTag<Block> BRAZIERS = forgeTag("braziers");
        public static final ITag.INamedTag<Block> POSTS = modTag("posts");
        public static final ITag.INamedTag<Block> JARS = modTag("jars");
        public static final ITag.INamedTag<Block> COLORED_JARS = modTag("colored_jars");

        public static final ITag.INamedTag<Block> LAPIDIFIED_JUNGLE_LOGS = modTag("lapidified_jungle_logs");

        private static ITag.INamedTag<Block> forgeTag(String name) {
            return BlockTags.makeWrapperTag(new ResourceLocation("forge", name).toString());
        }

        private static ITag.INamedTag<Block> modTag(String name) {
            return BlockTags.makeWrapperTag(new ResourceLocation(ValhelsiaStructures.MOD_ID, name).toString());
        }
    }

    public static class Items {
        public static final ITag.INamedTag<Item> AXE_CRAFTING_BLACKLISTED = modTag( "axe_crafting_blacklisted");

        private static ITag.INamedTag<Item> forgeTag(String name) {
            return ItemTags.makeWrapperTag(new ResourceLocation("forge", name).toString());
        }

        private static ITag.INamedTag<Item> modTag(String name) {
            return ItemTags.makeWrapperTag(new ResourceLocation(ValhelsiaStructures.MOD_ID, name).toString());
        }
    }
}
