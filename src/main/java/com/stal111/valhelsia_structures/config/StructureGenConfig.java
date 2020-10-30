package com.stal111.valhelsia_structures.config;

import com.stal111.valhelsia_structures.utils.StructureUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Structure Generation Config
 * Valhelsia Structures - com.stal111.valhelsia_structures.config.StructureGenConfig
 *
 * @author Valhelsia Team
 * @version 16.0.2
 * @since 2020-05-29
 */

public class StructureGenConfig {

    public static ForgeConfigSpec.IntValue FLATNESS_DELTA;

    public static ForgeConfigSpec.BooleanValue GENERATE_CASTLES;
    public static ForgeConfigSpec.DoubleValue CASTLE_SPAWN_CHANCE;
    public static ForgeConfigSpec.IntValue CASTLE_DISTANCE;
    public static ForgeConfigSpec.IntValue CASTLE_SEPARATION;
    public static ForgeConfigSpec.ConfigValue<List<? extends Biome.Category>> CASTLE_BIOME_CATEGORIES;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> CASTLE_BIOME_BLACKLIST;

    public static ForgeConfigSpec.BooleanValue GENERATE_CASTLE_RUINS;
    public static ForgeConfigSpec.DoubleValue CASTLE_RUIN_SPAWN_CHANCE;
    public static ForgeConfigSpec.IntValue CASTLE_RUIN_DISTANCE;
    public static ForgeConfigSpec.IntValue CASTLE_RUIN_SEPARATION;
    public static ForgeConfigSpec.ConfigValue<List<? extends Biome.Category>> CASTLE_RUIN_BIOME_CATEGORIES;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> CASTLE_RUIN_BIOME_BLACKLIST;

    public static ForgeConfigSpec.BooleanValue GENERATE_DESERT_HOUSES;
    public static ForgeConfigSpec.DoubleValue DESERT_HOUSE_SPAWN_CHANCE;
    public static ForgeConfigSpec.IntValue DESERT_HOUSE_DISTANCE;
    public static ForgeConfigSpec.IntValue DESERT_HOUSE_SEPARATION;
    public static ForgeConfigSpec.ConfigValue<List<? extends Biome.Category>> DESERT_HOUSE_BIOME_CATEGORIES;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> DESERT_HOUSE_BIOME_BLACKLIST;

    public static ForgeConfigSpec.BooleanValue GENERATE_FORGES;
    public static ForgeConfigSpec.DoubleValue FORGE_SPAWN_CHANCE;
    public static ForgeConfigSpec.IntValue FORGE_DISTANCE;
    public static ForgeConfigSpec.IntValue FORGE_SEPARATION;
    public static ForgeConfigSpec.ConfigValue<List<? extends Biome.Category>> FORGE_BIOME_CATEGORIES;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> FORGE_BIOME_BLACKLISTS;

    public static ForgeConfigSpec.BooleanValue GENERATE_PLAYER_HOUSES;
    public static ForgeConfigSpec.DoubleValue PLAYER_HOUSE_SPAWN_CHANCE;
    public static ForgeConfigSpec.IntValue PLAYER_HOUSE_DISTANCE;
    public static ForgeConfigSpec.IntValue PLAYER_HOUSE_SEPARATION;
    public static ForgeConfigSpec.ConfigValue<List<? extends Biome.Category>> PLAYER_HOUSE_BIOME_CATEGORIES;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> PLAYER_HOUSE_BIOME_BLACKLIST;

    public static ForgeConfigSpec.BooleanValue GENERATE_SMALL_DUNGEONS;
    public static ForgeConfigSpec.DoubleValue SMALL_DUNGEON_SPAWN_CHANCE;
    public static ForgeConfigSpec.IntValue SMALL_DUNGEON_DISTANCE;
    public static ForgeConfigSpec.IntValue SMALL_DUNGEON_SEPARATION;
    public static ForgeConfigSpec.ConfigValue<List<? extends Biome.Category>> SMALL_DUNGEON_BIOME_CATEGORIES;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> SMALL_DUNGEON_BIOME_BLACKLIST;


    public static ForgeConfigSpec.BooleanValue GENERATE_TOWER_RUINS;
    public static ForgeConfigSpec.DoubleValue TOWER_RUIN_SPAWN_CHANCE;
    public static ForgeConfigSpec.IntValue TOWER_RUIN_DISTANCE;
    public static ForgeConfigSpec.IntValue TOWER_RUIN_SEPARATION;
    public static ForgeConfigSpec.ConfigValue<List<? extends Biome.Category>> TOWER_RUIN_BIOME_CATEGORIES;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> TOWER_RUIN_BIOME_BLACKLIST;

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> BLACKLISTED_BIOMES;

    public static void init(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        COMMON_BUILDER.comment("Structure Generation Config");

        FLATNESS_DELTA = COMMON_BUILDER.comment("How flat does terrain need to be for surface structures to spawn? (in blocks) [default: 4]").defineInRange("structures.global.flatness_delta", 4, 0, 64);

        GENERATE_CASTLES = COMMON_BUILDER.comment("Generate Castles?").define("structures.castle.generate", true);
        CASTLE_SPAWN_CHANCE = COMMON_BUILDER.comment("Castle Spawn Chance [default: 0.5]").defineInRange("structures.castle.spawn_chance",0.5, 0.0, 1.0);
        CASTLE_DISTANCE = COMMON_BUILDER.comment("Castle Distance (in chunks) [default: 40]").defineInRange("structures.castle.distance", 40, 1, 500);
        CASTLE_SEPARATION = COMMON_BUILDER.comment("Castle Minimum Separation (in chunks) [default: 8]").defineInRange("structures.castle.separation", 8, 1, 500);
        CASTLE_BIOME_CATEGORIES = COMMON_BUILDER.comment("Biome Types the Castle can generate in").defineList("structures.castle.biome_categories", Arrays.asList(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.TAIGA), StructureGenConfig::validateBiomeCategory);
        CASTLE_BIOME_BLACKLIST = COMMON_BUILDER.comment("Biomes the Castle can NOT generate in").defineList("structures.castle.biome_blacklist", Collections.emptyList(), StructureGenConfig::validateBiome);

        GENERATE_CASTLE_RUINS = COMMON_BUILDER.comment("Generate Castle Ruins?").define("structures.castle_ruin.generate", true);
        CASTLE_RUIN_SPAWN_CHANCE = COMMON_BUILDER.comment("Castle Ruin Spawn Chance [default: 0.6]").defineInRange("structures.castle_ruin.spawn_chance",0.6, 0.0, 1.0);
        CASTLE_RUIN_DISTANCE = COMMON_BUILDER.comment("Castle Ruin Distance (in chunks) [default: 35]").defineInRange("structures.castle_ruin.distance", 35, 1, 500);
        CASTLE_RUIN_SEPARATION = COMMON_BUILDER.comment("Castle Ruin Minimum Separation (in chunks) [default: 8]").defineInRange("structures.castle_ruin.separation", 8, 1, 500);
        CASTLE_RUIN_BIOME_CATEGORIES = COMMON_BUILDER.comment("Biome Types the Castle Ruin can generate in").defineList("structures.castle_ruin.biome_categories", Arrays.asList(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.TAIGA), StructureGenConfig::validateBiomeCategory);
        CASTLE_RUIN_BIOME_BLACKLIST = COMMON_BUILDER.comment("Biomes the Castle Ruin can NOT generate in").defineList("structures.castle_ruin.biome_blacklist", new ArrayList<>(), StructureGenConfig::validateBiome);

        GENERATE_DESERT_HOUSES = COMMON_BUILDER.comment("Generate Desert Houses?").define("structures.desert_house.generate", true);
        DESERT_HOUSE_SPAWN_CHANCE = COMMON_BUILDER.comment("Desert House Spawn Chance [default: 0.7]").defineInRange("structures.desert_house.spawn_chance",0.7, 0.0, 1.0);
        DESERT_HOUSE_DISTANCE = COMMON_BUILDER.comment("Desert House Distance (in chunks) [default: 30]").defineInRange("structures.desert_house.distance", 30, 1, 500);
        DESERT_HOUSE_SEPARATION = COMMON_BUILDER.comment("Desert House Minimum Separation (in chunks) [default: 8]").defineInRange("structures.desert_house.separation", 8, 1, 500);
        DESERT_HOUSE_BIOME_CATEGORIES = COMMON_BUILDER.comment("Biome Types the Desert House can generate in").defineList("structures.desert_house.biome_categories", Collections.singletonList(Biome.Category.DESERT), StructureGenConfig::validateBiomeCategory);
        DESERT_HOUSE_BIOME_BLACKLIST = COMMON_BUILDER.comment("Biomes the Desert House can NOT generate in").defineList("structures.desert_house.biome_blacklist", new ArrayList<>(), StructureGenConfig::validateBiome);

        GENERATE_FORGES = COMMON_BUILDER.comment("Generate Forges?").define("structures.forge.generate", true);
        FORGE_SPAWN_CHANCE = COMMON_BUILDER.comment("Forge Spawn Chance [default: 0.7]").defineInRange("structures.forge.spawn_chance",0.7, 0.0, 1.0);
        FORGE_DISTANCE = COMMON_BUILDER.comment("Forge Distance (in chunks) [default: 30]").defineInRange("structures.forge.distance", 30, 1, 500);
        FORGE_SEPARATION = COMMON_BUILDER.comment("Forge Minimum Separation (in chunks) [default: 8]").defineInRange("structures.forge.separation", 8, 1, 500);
        FORGE_BIOME_CATEGORIES = COMMON_BUILDER.comment("Biome Types the Forge can generate in").defineList("structures.forge.biome_categories", Arrays.asList(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA), StructureGenConfig::validateBiomeCategory);
        FORGE_BIOME_BLACKLISTS = COMMON_BUILDER.comment("Biomes the Forge can NOT generate in").defineList("structures.forge.biome_blacklist", new ArrayList<>(), StructureGenConfig::validateBiome);

        GENERATE_PLAYER_HOUSES = COMMON_BUILDER.comment("Generate Player Houses?").define("structures.player_house.generate", true);
        PLAYER_HOUSE_SPAWN_CHANCE = COMMON_BUILDER.comment("Player House Spawn Chance [default: 0.7]").defineInRange("structures.player_house.spawn_chance",0.7, 0.0, 1.0);
        PLAYER_HOUSE_DISTANCE = COMMON_BUILDER.comment("Desert House Distance (in chunks) [default: 30]").defineInRange("structures.player_house.distance", 30, 1, 500);
        PLAYER_HOUSE_SEPARATION = COMMON_BUILDER.comment("Desert House Minimum Separation (in chunks) [default: 8]").defineInRange("structures.player_house.separation", 8, 1, 500);
        PLAYER_HOUSE_BIOME_CATEGORIES = COMMON_BUILDER.comment("Biome Types the Player House can generate in").defineList("structures.player_house.biome_categories", Arrays.asList(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA), StructureGenConfig::validateBiomeCategory);
        PLAYER_HOUSE_BIOME_BLACKLIST = COMMON_BUILDER.comment("Biomes the Player House can NOT generate in").defineList("structures.player_house.biome_blacklist", new ArrayList<>(), StructureGenConfig::validateBiome);

        GENERATE_SMALL_DUNGEONS = COMMON_BUILDER.comment("Generate Small Dungeons?").define("structures.small_dungeon.generate", true);
        SMALL_DUNGEON_SPAWN_CHANCE = COMMON_BUILDER.comment("Small Dungeon Spawn Chance [default: 0.7]").defineInRange("structures.small_dungeon.spawn_chance",0.7, 0.0, 1.0);
        SMALL_DUNGEON_DISTANCE = COMMON_BUILDER.comment("Small Dungeon Distance (in chunks) [default: 30]").defineInRange("structures.small_dungeon.distance", 30, 1, 500);
        SMALL_DUNGEON_SEPARATION = COMMON_BUILDER.comment("Small Dungeon Minimum Separation (in chunks) [default: 8]").defineInRange("structures.small_dungeon.separation", 8, 1, 500);
        SMALL_DUNGEON_BIOME_CATEGORIES = COMMON_BUILDER.comment("Biome Types the Small Dungeon can generate in").defineList("structures.small_dungeon.biome_categories", Arrays.asList(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA, Biome.Category.DESERT, Biome.Category.MESA, Biome.Category.SAVANNA, Biome.Category.JUNGLE, Biome.Category.ICY, Biome.Category.SWAMP), StructureGenConfig::validateBiomeCategory);
        SMALL_DUNGEON_BIOME_BLACKLIST = COMMON_BUILDER.comment("Biomes the Small Dungeon can NOT generate in").defineList("structures.small_dungeon.biome_blacklist", new ArrayList<>(), StructureGenConfig::validateBiome);

        GENERATE_TOWER_RUINS = COMMON_BUILDER.comment("Generate Tower Ruins?").define("structures.tower_ruin.generate", true);
        TOWER_RUIN_SPAWN_CHANCE = COMMON_BUILDER.comment("Tower Ruin Spawn Chance [default: 0.8]").defineInRange("structures.tower_ruin.spawn_chance",0.8, 0.0, 1.0);
        TOWER_RUIN_DISTANCE = COMMON_BUILDER.comment("Tower Ruin Distance (in chunks) [default: 25]").defineInRange("structures.tower_ruin.distance", 25, 1, 500);
        TOWER_RUIN_SEPARATION = COMMON_BUILDER.comment("Tower Ruin Minimum Separation (in chunks) [default: 8]").defineInRange("structures.tower_ruin.separation", 8, 1, 500);
        TOWER_RUIN_BIOME_CATEGORIES = COMMON_BUILDER.comment("Biome Types the Tower Ruin can generate in").defineList("structures.tower_ruin.biome_categories", Arrays.asList(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA), StructureGenConfig::validateBiomeCategory);
        TOWER_RUIN_BIOME_BLACKLIST = COMMON_BUILDER.comment("Biomes the Tower Ruin can NOT generate in").defineList("structures.tower_ruin.biome_blacklist", new ArrayList<>(), StructureGenConfig::validateBiome);

        BLACKLISTED_BIOMES = COMMON_BUILDER.comment("Biomes in which Structures cant generate in").defineList("structures.blacklisted_biomes", StructureUtils.getAllBiomesForCategory(Biome.Category.RIVER, Biome.Category.OCEAN), StructureGenConfig::validateBiome);
    }

    private static boolean validateBiome(Object o) {
        return o == null || ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) o));
    }

    private static boolean validateBiomeCategory(Object o) {
        for (Biome.Category category : Biome.Category.values()) {
            if (category == Biome.Category.valueOf((String) o)) {
                return true;
            }
        }
        return false;
    }
}
