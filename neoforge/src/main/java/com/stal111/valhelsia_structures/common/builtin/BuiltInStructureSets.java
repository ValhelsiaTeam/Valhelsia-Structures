package com.stal111.valhelsia_structures.common.builtin;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.StructureSet;

public class BuiltInStructureSets {

    public static final ResourceKeyHelper<StructureSet> HELPER = ResourceKeyHelper.create(Registries.STRUCTURE_SET);

    public static final ResourceKey<StructureSet> CASTLES = HELPER.createKey("castles");
    public static final ResourceKey<StructureSet> CASTLE_RUINS = HELPER.createKey("castle_ruins");
    public static final ResourceKey<StructureSet> DESERT_HOUSES = HELPER.createKey("desert_houses");
    public static final ResourceKey<StructureSet> FORGES = HELPER.createKey("forges");
    public static final ResourceKey<StructureSet> PLAYER_HOUSES = HELPER.createKey("player_houses");
    public static final ResourceKey<StructureSet> SPAWNER_DUNGEONS = HELPER.createKey("spawner_dungeons");
    public static final ResourceKey<StructureSet> TOWER_RUINS = HELPER.createKey("tower_ruins");
    public static final ResourceKey<StructureSet> WITCH_HUTS = HELPER.createKey("witch_huts");
    public static final ResourceKey<StructureSet> BIG_TREES = HELPER.createKey("big_trees");
    public static final ResourceKey<StructureSet> SPAWNER_ROOMS = HELPER.createKey("spawner_rooms");
    public static final ResourceKey<StructureSet> DEEP_SPAWNER_ROOMS = HELPER.createKey("deep_spawner_rooms");
}
