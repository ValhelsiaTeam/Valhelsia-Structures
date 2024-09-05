package com.stal111.valhelsia_structures.common.builtin;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

public class BuiltInProcessorLists {

    public static final ResourceKeyHelper<StructureProcessorList> HELPER = ResourceKeyHelper.create(Registries.PROCESSOR_LIST);

    public static final ResourceKey<StructureProcessorList> WITCH_HUT = HELPER.createKey("witch_hut");
    public static final ResourceKey<StructureProcessorList> SPAWNER_ROOM = HELPER.createKey("spawner_room");
    public static final ResourceKey<StructureProcessorList> DEEP_SPAWNER_ROOM = HELPER.createKey("deep_spawner_room");
}
