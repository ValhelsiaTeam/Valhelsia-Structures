package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.data.worldgen.processors.ModProcessorLists;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.valhelsia.valhelsia_core.core.data.DataProviderInfo;
import net.valhelsia.valhelsia_core.core.registry.helper.DatapackRegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.TemplatePoolRegistryHelper;

/**
 * @author Valhelsia Team
 * @since 2022-11-26
 */
public class SimpleStructurePools extends DatapackRegistryClass<StructureTemplatePool> {

    public static final TemplatePoolRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getDatapackHelper(Registries.TEMPLATE_POOL);

    public static final ResourceKey<StructureTemplatePool> CASTLES = HELPER.createKey("castles");
    public static final ResourceKey<StructureTemplatePool> CASTLE_RUINS = HELPER.createKey("castle_ruins");
    public static final ResourceKey<StructureTemplatePool> FORGES = HELPER.createKey("forges");
    public static final ResourceKey<StructureTemplatePool> TOWER_RUINS = HELPER.createKey("tower_ruins");
    public static final ResourceKey<StructureTemplatePool> SPAWNER_ROOMS = HELPER.createKey("spawner_rooms");
    public static final ResourceKey<StructureTemplatePool> DEEP_SPAWNER_ROOMS = HELPER.createKey("deep_spawner_rooms");
    public static final ResourceKey<StructureTemplatePool> WITCH_HUTS = HELPER.createKey("witch_huts");

    public SimpleStructurePools(DataProviderInfo info, BootstapContext<StructureTemplatePool> context) {
        super(info, context);
    }

    @Override
    public void bootstrap(BootstapContext<StructureTemplatePool> context) {
        HolderGetter<StructureProcessorList> processorListRegistry = context.lookup(Registries.PROCESSOR_LIST);

        HELPER.create(CASTLES, context, builder -> builder.element("castle"));
        HELPER.create(CASTLE_RUINS, context, "ruins", builder -> builder.element("castle_ruin"));
        HELPER.create(FORGES, context, "forge", builder -> builder.element("forge_1").element("forge_2"));
        HELPER.create(TOWER_RUINS, context, "ruins", builder -> builder.element("tower_ruin_1").element("tower_ruin_2").element("tower_ruin_3"));
        HELPER.create(SPAWNER_ROOMS, context, "spawner_room", builder -> builder.element("spawner_room_1").element("spawner_room_2").processors(ModProcessorLists.SPAWNER_ROOM));
        HELPER.create(DEEP_SPAWNER_ROOMS, context, "deep_spawner_room", builder -> builder.element("deep_spawner_room_1").processors(ModProcessorLists.DEEP_SPAWNER_ROOM));
        HELPER.create(WITCH_HUTS, context, "witch_hut", builder -> builder.element("witch_hut_1").element("witch_hut_2").processors(ModProcessorLists.WITCH_HUT));
    }
}
