package com.stal111.valhelsia_structures.init;

import com.google.common.collect.Sets;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import net.minecraft.util.ResourceLocation;

import java.util.Set;

/**
 * Loot Tables
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModLootTables
 *
 * @author Valhelsia Team
 * @version 15.0.3
 */
public class ModLootTables {

    private static final Set<ResourceLocation> LOOT_TABLES = Sets.newHashSet();

    public static final ResourceLocation CHESTS_CASTLE = register(new ResourceLocation(ValhelsiaStructures.MOD_ID, "chests/castle"));
    public static final ResourceLocation CHESTS_CASTLE_RUIN = register(new ResourceLocation(ValhelsiaStructures.MOD_ID, "chests/castle_ruin"));
    public static final ResourceLocation CHESTS_DESERT_HOUSE = register(new ResourceLocation(ValhelsiaStructures.MOD_ID, "chests/desert_house"));
    public static final ResourceLocation CHESTS_SMALL_DUNGEON = register(new ResourceLocation(ValhelsiaStructures.MOD_ID, "chests/dungeon1"));
    public static final ResourceLocation CHESTS_FORGE = register(new ResourceLocation(ValhelsiaStructures.MOD_ID, "chests/forge"));
    public static final ResourceLocation CHESTS_PLAYER_HOUSE = register(new ResourceLocation(ValhelsiaStructures.MOD_ID, "chests/player_house"));

    private static ResourceLocation register(String id) {
        return register(new ResourceLocation(id));
    }

    private static ResourceLocation register(ResourceLocation id) {
        if (LOOT_TABLES.add(id)) {
            return id;
        } else {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table");
        }
    }
}
