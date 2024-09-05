package com.stal111.valhelsia_structures.common.builtin;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.valhelsia.valhelsia_core.api.common.registry.helper.datapack.DatapackRegistryHelper;

public class BuiltInProcessorLists extends BuiltInDataProvider<StructureProcessorList> {

    public static final DatapackRegistryHelper<StructureProcessorList> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.PROCESSOR_LIST);

    public static final ResourceKey<StructureProcessorList> WITCH_HUT = HELPER.createKey("witch_hut");
    public static final ResourceKey<StructureProcessorList> SPAWNER_ROOM = HELPER.createKey("spawner_room");
    public static final ResourceKey<StructureProcessorList> DEEP_SPAWNER_ROOM = HELPER.createKey("deep_spawner_room");

    protected BuiltInProcessorLists() {
        super(Registries.PROCESSOR_LIST);
    }
}
