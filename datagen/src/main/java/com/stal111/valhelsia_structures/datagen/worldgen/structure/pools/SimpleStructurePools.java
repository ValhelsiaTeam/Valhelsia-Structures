package com.stal111.valhelsia_structures.datagen.worldgen.structure.pools;

import com.stal111.valhelsia_structures.common.builtin.BuiltInProcessorLists;
import com.stal111.valhelsia_structures.common.builtin.BuiltInStructurePools;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.valhelsia.valhelsia_core.api.common.registry.helper.TemplatePoolRegistryHelper;
import net.valhelsia.valhelsia_core.api.common.registry.helper.datapack.DatapackRegistryClass;

/**
 * @author Valhelsia Team
 * @since 2022-11-26
 */
public class SimpleStructurePools extends DatapackRegistryClass<StructureTemplatePool> {

    public static final TemplatePoolRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.TEMPLATE_POOL);

    public SimpleStructurePools(BootstrapContext<StructureTemplatePool> context) {
        super(context);
    }

    @Override
    public void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        HolderGetter<StructureProcessorList> processorListRegistry = context.lookup(Registries.PROCESSOR_LIST);

        BuiltInStructurePools.CASTLES.create(HELPER, context, builder -> builder.element("castle"));
        BuiltInStructurePools.CASTLE_RUINS.create(HELPER, context, "ruins", builder -> builder.element("castle_ruin"));
        BuiltInStructurePools.FORGES.create(HELPER, context, "forge", builder -> builder.element("forge_1").element("forge_2"));
        BuiltInStructurePools.TOWER_RUINS.create(HELPER, context, "ruins", builder -> builder.element("tower_ruin_1").element("tower_ruin_2").element("tower_ruin_3"));
        BuiltInStructurePools.SPAWNER_ROOMS.create(HELPER, context, "spawner_room", builder -> builder.element("spawner_room_1").element("spawner_room_2").processors(BuiltInProcessorLists.SPAWNER_ROOM));
        BuiltInStructurePools.DEEP_SPAWNER_ROOMS.create(HELPER, context, "deep_spawner_room", builder -> builder.element("deep_spawner_room_1").processors(BuiltInProcessorLists.DEEP_SPAWNER_ROOM));
        BuiltInStructurePools.WITCH_HUTS.create(HELPER, context, "witch_hut", builder -> builder.element("witch_hut_1").element("witch_hut_2").processors(BuiltInProcessorLists.WITCH_HUT));
    }
}
