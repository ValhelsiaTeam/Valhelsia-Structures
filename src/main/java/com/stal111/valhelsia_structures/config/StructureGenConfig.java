package com.stal111.valhelsia_structures.config;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * Structure Generation Config
 * Valhelsia-Structures - com.stal111.valhelsia_structures.config.StructureGenConfig
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2020-05-29
 */
public class StructureGenConfig {

    public static ForgeConfigSpec.BooleanValue GENERATE_CASTLES;
    public static ForgeConfigSpec.DoubleValue CASTLE_SPAWN_CHANCE;
    public static ForgeConfigSpec.IntValue CASTLE_DISTANCE;
    public static ForgeConfigSpec.IntValue CASTLE_SEPARATION;

    public static ForgeConfigSpec.BooleanValue GENERATE_CASTLE_RUINS;
    public static ForgeConfigSpec.DoubleValue CASTLE_RUIN_SPAWN_CHANCE;
    public static ForgeConfigSpec.IntValue CASTLE_RUIN_DISTANCE;
    public static ForgeConfigSpec.IntValue CASTLE_RUIN_SEPARATION;

    public static ForgeConfigSpec.BooleanValue GENERATE_DESERT_HOUSES;
    public static ForgeConfigSpec.DoubleValue DESERT_HOUSE_SPAWN_CHANCE;
    public static ForgeConfigSpec.IntValue DESERT_HOUSE_DISTANCE;
    public static ForgeConfigSpec.IntValue DESERT_HOUSE_SEPARATION;

    public static ForgeConfigSpec.BooleanValue GENERATE_FORGES;
    public static ForgeConfigSpec.DoubleValue FORGE_SPAWN_CHANCE;
    public static ForgeConfigSpec.IntValue FORGE_DISTANCE;
    public static ForgeConfigSpec.IntValue FORGE_SEPARATION;

    public static ForgeConfigSpec.BooleanValue GENERATE_PLAYER_HOUSES;
    public static ForgeConfigSpec.DoubleValue PLAYER_HOUSE_SPAWN_CHANCE;
    public static ForgeConfigSpec.IntValue PLAYER_HOUSE_DISTANCE;
    public static ForgeConfigSpec.IntValue PLAYER_HOUSE_SEPARATION;

    public static ForgeConfigSpec.BooleanValue GENERATE_SMALL_DUNGEONS;
    public static ForgeConfigSpec.DoubleValue SMALL_DUNGEON_SPAWN_CHANCE;
    public static ForgeConfigSpec.IntValue SMALL_DUNGEON_DISTANCE;
    public static ForgeConfigSpec.IntValue SMALL_DUNGEON_SEPARATION;

    public static ForgeConfigSpec.BooleanValue GENERATE_TOWER_RUINS;
    public static ForgeConfigSpec.DoubleValue TOWER_RUIN_SPAWN_CHANCE;
    public static ForgeConfigSpec.IntValue TOWER_RUIN_DISTANCE;
    public static ForgeConfigSpec.IntValue TOWER_RUIN_SEPARATION;

    public static void init(ForgeConfigSpec.Builder SERVER_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        SERVER_BUILDER.comment("Structure Generation Config");

        GENERATE_CASTLES = SERVER_BUILDER.comment("Generate Castles").define("structures.castle.generate", true);
        CASTLE_SPAWN_CHANCE = SERVER_BUILDER.comment("Castle Spawn Chance").defineInRange("structures.castle.spawn_chance",0.7,0.0,1.0);
        CASTLE_DISTANCE = SERVER_BUILDER.comment("Castle Distance (in chunks)").defineInRange("structures.castle.distance", 35, 1, 500);
        CASTLE_SEPARATION = SERVER_BUILDER.comment("Castle Minimum Separation (in chunks)").defineInRange("structures.castle.separation", 8, 1, 500);

        GENERATE_CASTLE_RUINS = SERVER_BUILDER.comment("Generate Castle Ruins").define("structures.castle_ruin.generate", true);
        CASTLE_RUIN_SPAWN_CHANCE = SERVER_BUILDER.comment("Castle Ruin Spawn Chance").defineInRange("structures.castle_ruin.spawn_chance",0.7,0.0,1.0);
        CASTLE_RUIN_DISTANCE = SERVER_BUILDER.comment("Castle Ruin Distance (in chunks)").defineInRange("structures.castle_ruin.distance", 35, 1, 500);
        CASTLE_RUIN_SEPARATION = SERVER_BUILDER.comment("Castle Ruin Minimum Separation (in chunks)").defineInRange("structures.castle_ruin.separation", 8, 1, 500);

        GENERATE_DESERT_HOUSES = SERVER_BUILDER.comment("Generate Desert Houses").define("structures.desert_house.generate", true);
        DESERT_HOUSE_SPAWN_CHANCE = SERVER_BUILDER.comment("Desert House Spawn Chance").defineInRange("structures.desert_house.spawn_chance",0.7,0.0,1.0);
        DESERT_HOUSE_DISTANCE = SERVER_BUILDER.comment("Desert House Distance (in chunks)").defineInRange("structures.desert_house.distance", 30, 1, 500);
        DESERT_HOUSE_SEPARATION = SERVER_BUILDER.comment("Desert House Minimum Separation (in chunks)").defineInRange("structures.desert_house.separation", 8, 1, 500);

        GENERATE_FORGES = SERVER_BUILDER.comment("Generate Forges").define("structures.forge.generate", true);
        FORGE_SPAWN_CHANCE = SERVER_BUILDER.comment("Forge Spawn Chance").defineInRange("structures.forge.spawn_chance",0.7,0.0,1.0);
        FORGE_DISTANCE = SERVER_BUILDER.comment("Forge Distance (in chunks)").defineInRange("structures.forge.distance", 30, 1, 500);
        FORGE_SEPARATION = SERVER_BUILDER.comment("Forge Minimum Separation (in chunks)").defineInRange("structures.forge.separation", 8, 1, 500);

        GENERATE_PLAYER_HOUSES = SERVER_BUILDER.comment("Generate Player Houses").define("structures.player_house.generate", true);
        PLAYER_HOUSE_SPAWN_CHANCE = SERVER_BUILDER.comment("Player House Spawn Chance").defineInRange("structures.player_house.spawn_chance",0.7,0.0,1.0);
        PLAYER_HOUSE_DISTANCE = SERVER_BUILDER.comment("Desert House Distance (in chunks)").defineInRange("structures.player_house.distance", 30, 1, 500);
        PLAYER_HOUSE_SEPARATION = SERVER_BUILDER.comment("Desert House Minimum Separation (in chunks)").defineInRange("structures.player_house.separation", 8, 1, 500);

        GENERATE_SMALL_DUNGEONS = SERVER_BUILDER.comment("Generate Small Dungeons").define("structures.small_dungeon.generate", true);
        SMALL_DUNGEON_SPAWN_CHANCE = SERVER_BUILDER.comment("Small Dungeon Spawn Chance").defineInRange("structures.small_dungeon.spawn_chance",0.7,0.0,1.0);
        SMALL_DUNGEON_DISTANCE = SERVER_BUILDER.comment("Small Dungeon Distance (in chunks)").defineInRange("structures.small_dungeon.distance", 30, 1, 500);
        SMALL_DUNGEON_SEPARATION = SERVER_BUILDER.comment("Small Dungeon Minimum Separation (in chunks)").defineInRange("structures.small_dungeon.separation", 8, 1, 500);

        GENERATE_TOWER_RUINS = SERVER_BUILDER.comment("Generate Tower Ruins").define("structures.tower_ruin.generate", true);
        TOWER_RUIN_SPAWN_CHANCE = SERVER_BUILDER.comment("Tower Ruin Spawn Chance").defineInRange("structures.tower_ruin.spawn_chance",0.7,0.0,1.0);
        TOWER_RUIN_DISTANCE = SERVER_BUILDER.comment("Tower Ruin Distance (in chunks)").defineInRange("structures.tower_ruin.distance", 25, 1, 500);
        TOWER_RUIN_SEPARATION = SERVER_BUILDER.comment("Tower Ruin Minimum Separation (in chunks)").defineInRange("structures.tower_ruin.separation", 8, 1, 500);
    }
}
