package com.stal111.valhelsia_structures.utils;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * Mod Tags <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.utils.ModTags
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 */
public class ModTags {

    public static class Blocks {
        public static final Tag.Named<Block> BRAZIERS = forgeTag("braziers");
        public static final Tag.Named<Block> POSTS = modTag("posts");
        public static final Tag.Named<Block> CUT_POSTS = modTag("cut_posts");
        public static final Tag.Named<Block> NON_FLAMMABLE_POSTS = modTag("non_flammable_posts");
        public static final Tag.Named<Block> COLORED_JARS = modTag("colored_jars");
        public static final Tag.Named<Block> JARS = modTag("jars");
        public static final Tag.Named<Block> BIG_COLORED_JARS = modTag("big_colored_jars");
        public static final Tag.Named<Block> BIG_JARS = modTag("big_jars");
        public static final Tag.Named<Block> LAPIDIFIED_JUNGLE_LOGS = modTag("lapidified_jungle_logs");

        private static Tag.Named<Block> forgeTag(String name) {
            return BlockTags.bind(new ResourceLocation("forge", name).toString());
        }

        private static Tag.Named<Block> modTag(String name) {
            return BlockTags.bind(new ResourceLocation(ValhelsiaStructures.MOD_ID, name).toString());
        }
    }

    public static class Items {
        public static final Tag.Named<Item> POSTS = modTag("posts");
        public static final Tag.Named<Item> CUT_POSTS = modTag("cut_posts");
        public static final Tag.Named<Item> NON_FLAMMABLE_POSTS = modTag("non_flammable_posts");
        public static final Tag.Named<Item> COLORED_JARS = modTag("colored_jars");
        public static final Tag.Named<Item> JARS = modTag("jars");
        public static final Tag.Named<Item> JAR_BLACKLISTED = modTag("jar_blacklisted");
        public static final Tag.Named<Item> BIG_COLORED_JARS = modTag("big_colored_jars");
        public static final Tag.Named<Item> BIG_JARS = modTag("big_jars");
        public static final Tag.Named<Item> LAPIDIFIED_JUNGLE_LOGS = modTag("lapidified_jungle_logs");

        public static final Tag.Named<Item> AXE_CRAFTING_BLACKLISTED = modTag( "axe_crafting_blacklisted");

        private static Tag.Named<Item> forgeTag(String name) {
            return ItemTags.bind(new ResourceLocation("forge", name).toString());
        }

        private static Tag.Named<Item> modTag(String name) {
            return ItemTags.bind(new ResourceLocation(ValhelsiaStructures.MOD_ID, name).toString());
        }
    }
}
