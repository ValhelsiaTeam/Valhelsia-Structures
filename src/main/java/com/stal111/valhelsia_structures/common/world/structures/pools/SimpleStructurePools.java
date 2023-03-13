package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.data.worldgen.processors.ModProcessorLists;
import com.stal111.valhelsia_structures.utils.StartPoolKeySet;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
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

    public static final StartPoolKeySet CASTLES = StartPoolKeySet.withFurnishedVariant(HELPER, "castles");
    public static final StartPoolKeySet CASTLE_RUINS = StartPoolKeySet.simple(HELPER, "castle_ruins");
    public static final StartPoolKeySet FORGES = StartPoolKeySet.withFurnishedVariant(HELPER, "forges");
    public static final StartPoolKeySet TOWER_RUINS = StartPoolKeySet.simple(HELPER, "tower_ruins");
    public static final StartPoolKeySet SPAWNER_ROOMS = StartPoolKeySet.simple(HELPER, "spawner_rooms");
    public static final StartPoolKeySet DEEP_SPAWNER_ROOMS = StartPoolKeySet.simple(HELPER, "deep_spawner_rooms");
    public static final StartPoolKeySet WITCH_HUTS = StartPoolKeySet.withFurnishedVariant(HELPER, "witch_huts");

    public SimpleStructurePools(DataProviderInfo info, BootstapContext<StructureTemplatePool> context) {
        super(info, context);
    }

    @Override
    public void bootstrap(BootstapContext<StructureTemplatePool> context) {
        HolderGetter<StructureProcessorList> processorListRegistry = context.lookup(Registries.PROCESSOR_LIST);

        CASTLES.create(HELPER, context, builder -> builder.element("castle"));
        CASTLE_RUINS.create(HELPER, context, "ruins", builder -> builder.element("castle_ruin"));
        FORGES.create(HELPER, context, "forge", builder -> builder.element("forge_1").element("forge_2"));
        TOWER_RUINS.create(HELPER, context, "ruins", builder -> builder.element("tower_ruin_1").element("tower_ruin_2").element("tower_ruin_3"));
        SPAWNER_ROOMS.create(HELPER, context, "spawner_room", builder -> builder.element("spawner_room_1").element("spawner_room_2").processors(ModProcessorLists.SPAWNER_ROOM));
        DEEP_SPAWNER_ROOMS.create(HELPER, context, "deep_spawner_room", builder -> builder.element("deep_spawner_room_1").processors(ModProcessorLists.DEEP_SPAWNER_ROOM));
        WITCH_HUTS.create(HELPER, context, "witch_hut", builder -> builder.element("witch_hut_1").element("witch_hut_2").processors(ModProcessorLists.WITCH_HUT));
    }
}
