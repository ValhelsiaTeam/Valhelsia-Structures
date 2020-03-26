package com.stal111.valhelsia_structures.init;

import com.google.common.collect.Sets;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import net.minecraft.util.ResourceLocation;

import java.util.Set;

public class ModLootTables {
    private static final Set<ResourceLocation> LOOT_TABLES = Sets.newHashSet();

    public static final ResourceLocation CHESTS_SMALL_CASTLE = register(new ResourceLocation(ValhelsiaStructures.MOD_ID, "chests/small_castle"));

    private static ResourceLocation register(String id) {
        return register(new ResourceLocation(id));
    }

    private static ResourceLocation register(ResourceLocation id) {
        if (LOOT_TABLES.add(id)) {
            return id;
        } else {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table.");
        }
    }
}
