package com.stal111.valhelsia_structures.common.builtin;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.StartPoolKeySet;
import net.minecraft.core.registries.Registries;
import net.valhelsia.valhelsia_core.api.common.registry.helper.TemplatePoolRegistryHelper;

public class BuiltInStructurePools {

    public static final TemplatePoolRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.TEMPLATE_POOL);

    public static final StartPoolKeySet CASTLES = StartPoolKeySet.withFurnishedVariant(HELPER, "castles");
    public static final StartPoolKeySet CASTLE_RUINS = StartPoolKeySet.simple(HELPER, "castle_ruins");
    public static final StartPoolKeySet FORGES = StartPoolKeySet.withFurnishedVariant(HELPER, "forges");
    public static final StartPoolKeySet TOWER_RUINS = StartPoolKeySet.simple(HELPER, "tower_ruins");
    public static final StartPoolKeySet SPAWNER_ROOMS = StartPoolKeySet.simple(HELPER, "spawner_rooms");
    public static final StartPoolKeySet DEEP_SPAWNER_ROOMS = StartPoolKeySet.simple(HELPER, "deep_spawner_rooms");
    public static final StartPoolKeySet WITCH_HUTS = StartPoolKeySet.withFurnishedVariant(HELPER, "witch_huts");
    public static final StartPoolKeySet BIG_TREES = StartPoolKeySet.withFurnishedVariant(HELPER, "big_trees");
    public static final StartPoolKeySet DESERT_HOUSES = StartPoolKeySet.withFurnishedVariant(HELPER, "desert_house/houses");
    public static final StartPoolKeySet PLAYER_HOUSES = StartPoolKeySet.withFurnishedVariant(HELPER, "player_house/houses");
    public static final StartPoolKeySet SPAWNER_DUNGEONS = StartPoolKeySet.withFurnishedVariant(HELPER, "spawner_dungeon/cave_entrances");

}
