package com.stal111.valhelsia_structures.common.builtin;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class BuiltInStructures {

    public static final ResourceKeyHelper<Structure> HELPER = ResourceKeyHelper.create(Registries.STRUCTURE);

    public static final ResourceKey<Structure> CASTLE = HELPER.createKey("castle");
    public static final ResourceKey<Structure> CASTLE_RUIN = HELPER.createKey("castle_ruin");
    public static final ResourceKey<Structure> DESERT_HOUSE = HELPER.createKey("desert_house");
    public static final ResourceKey<Structure> FORGE = HELPER.createKey("forge");
    public static final ResourceKey<Structure> PLAYER_HOUSE = HELPER.createKey("player_house");
    public static final ResourceKey<Structure> SPAWNER_DUNGEON = HELPER.createKey("spawner_dungeon");
    public static final ResourceKey<Structure> TOWER_RUIN = HELPER.createKey("tower_ruin");
    public static final ResourceKey<Structure> WITCH_HUT = HELPER.createKey("witch_hut");
    public static final ResourceKey<Structure> BIG_TREE = HELPER.createKey("big_tree");
    public static final ResourceKey<Structure> SPAWNER_ROOM = HELPER.createKey("spawner_room");
    public static final ResourceKey<Structure> DEEP_SPAWNER_ROOM = HELPER.createKey("deep_spawner_room");
}
