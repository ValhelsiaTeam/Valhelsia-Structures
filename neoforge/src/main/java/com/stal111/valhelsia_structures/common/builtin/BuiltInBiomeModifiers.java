package com.stal111.valhelsia_structures.common.builtin;

import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class BuiltInBiomeModifiers {

    public static final ResourceKeyHelper<BiomeModifier> HELPER = ResourceKeyHelper.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS);

    public static final ResourceKey<BiomeModifier> REMOVE_MONSTER_ROOM = HELPER.createKey("remove_monster_room");
}
