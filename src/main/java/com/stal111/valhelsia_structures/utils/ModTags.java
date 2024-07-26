package com.stal111.valhelsia_structures.utils;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;

/**
 * Mod Tags <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.utils.ModTags
 *
 * @author Valhelsia Team
 * @version 1.19 - 0.2.0
 */
public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> BRAZIERS = modTag("braziers");
        public static final TagKey<Block> POSTS = modTag("posts");
        public static final TagKey<Block> CUT_POSTS = modTag("cut_posts");
        public static final TagKey<Block> NON_FLAMMABLE_POSTS = modTag("non_flammable_posts");
        public static final TagKey<Block> COLORED_JARS = modTag("colored_jars");
        public static final TagKey<Block> JARS = modTag("jars");
        public static final TagKey<Block> BIG_COLORED_JARS = modTag("big_colored_jars");
        public static final TagKey<Block> BIG_JARS = modTag("big_jars");
        public static final TagKey<Block> LAPIDIFIED_JUNGLE_LOGS = modTag("lapidified_jungle_logs");
        public static final TagKey<Block> SLEEPING_BAGS = modTag("sleeping_bags");

        private static TagKey<Block> modTag(String name) {
            return TagKey.create(Registries.BLOCK, ValhelsiaStructures.location(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> POSTS = modTag("posts");
        public static final TagKey<Item> CUT_POSTS = modTag("cut_posts");
        public static final TagKey<Item> NON_FLAMMABLE_POSTS = modTag("non_flammable_posts");
        public static final TagKey<Item> COLORED_JARS = modTag("colored_jars");
        public static final TagKey<Item> JARS = modTag("jars");
        public static final TagKey<Item> JAR_BLACKLISTED = modTag("jar_blacklisted");
        public static final TagKey<Item> BIG_COLORED_JARS = modTag("big_colored_jars");
        public static final TagKey<Item> BIG_JARS = modTag("big_jars");
        public static final TagKey<Item> LAPIDIFIED_JUNGLE_LOGS = modTag("lapidified_jungle_logs");
        public static final TagKey<Item> SLEEPING_BAGS = modTag("sleeping_bags");

        public static final TagKey<Item> AXE_CRAFTING_BLACKLISTED = modTag( "axe_crafting_blacklisted");

        private static TagKey<Item> modTag(String name) {
            return TagKey.create(Registries.ITEM, ValhelsiaStructures.location(name));
        }
    }

    public static class Structures {
        public static final TagKey<Structure> ON_SPAWNER_DUNGEON_EXPLORER_MAPS = modTag("on_spawner_dungeon_explorer_maps");
        public static final TagKey<Structure> ON_CASTLE_EXPLORER_MAPS = modTag("on_castle_explorer_maps");

        private static TagKey<Structure> modTag(String name) {
            return TagKey.create(Registries.STRUCTURE, ValhelsiaStructures.location(name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> HAS_CASTLE = modTag("has_structure/castle");
        public static final TagKey<Biome> HAS_CASTLE_RUIN = modTag("has_structure/castle_ruin");
        public static final TagKey<Biome> HAS_DESERT_HOUSE = modTag("has_structure/has_desert_house");
        public static final TagKey<Biome> DESERT_HOUSE_CONDITION = modTag("has_structure/desert_house_condition");
        public static final TagKey<Biome> HAS_FORGE = modTag("has_structure/has_forge");
        public static final TagKey<Biome> HAS_PLAYER_HOUSE = modTag("has_structure/player_house");
        public static final TagKey<Biome> HAS_SPAWNER_DUNGEON = modTag("has_structure/spawner_dungeon");
        public static final TagKey<Biome> HAS_TOWER_RUIN = modTag("has_structure/tower_ruin");
        public static final TagKey<Biome> HAS_WITCH_HUT = modTag("has_structure/witch_hut");
        public static final TagKey<Biome> HAS_BIG_TREE = modTag("has_structure/big_tree");
        public static final TagKey<Biome> HAS_SPAWNER_ROOM = modTag("has_structure/spawner_room");
        public static final TagKey<Biome> HAS_DEEP_SPAWNER_ROOM = modTag("has_structure/deep_spawner_room");

        private static TagKey<Biome> modTag(String name) {
            return TagKey.create(Registries.BIOME, ValhelsiaStructures.location(name));
        }
    }
}
