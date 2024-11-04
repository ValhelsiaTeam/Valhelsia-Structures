package com.stal111.valhelsia_structures.datagen.worldgen.structure.pools

import com.stal111.valhelsia_structures.common.builtin.BuiltInProcessorLists
import com.stal111.valhelsia_structures.common.builtin.BuiltInStructurePools
import com.stal111.valhelsia_structures.utils.TemplatePoolHelper
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList
import net.valhelsia.dataforge.RegistryDataProvider
import java.util.function.UnaryOperator

object SimpleStructurePools : RegistryDataProvider<StructureTemplatePool> {

    val HELPER = TemplatePoolHelper.INSTANCE

    override fun bootstrap(context: BootstrapContext<StructureTemplatePool>) {
        context.lookup<StructureProcessorList>(Registries.PROCESSOR_LIST)

        BuiltInStructurePools.CASTLES.create(
            HELPER,
            context,
            UnaryOperator { it.element("castle") })
        BuiltInStructurePools.CASTLE_RUINS.create(
            HELPER,
            context,
            "ruins",
            UnaryOperator { it.element("castle_ruin") })
        BuiltInStructurePools.FORGES.create(
            HELPER,
            context,
            "forge",
            UnaryOperator { it.element("forge_1").element("forge_2") })
        BuiltInStructurePools.TOWER_RUINS.create(
            HELPER,
            context,
            "ruins",
            UnaryOperator {
                it.element("tower_ruin_1").element("tower_ruin_2").element("tower_ruin_3")
            })
        BuiltInStructurePools.SPAWNER_ROOMS.create(
            HELPER,
            context,
            "spawner_room",
            UnaryOperator {
                it.element("spawner_room_1").element("spawner_room_2")
                    .processors(BuiltInProcessorLists.SPAWNER_ROOM)
            })
        BuiltInStructurePools.DEEP_SPAWNER_ROOMS.create(
            HELPER,
            context,
            "deep_spawner_room",
            UnaryOperator {
                it.element("deep_spawner_room_1").processors(BuiltInProcessorLists.DEEP_SPAWNER_ROOM)
            })
        BuiltInStructurePools.WITCH_HUTS.create(
            HELPER,
            context,
            "witch_hut",
            UnaryOperator {
                it.element("witch_hut_1").element("witch_hut_2").processors(BuiltInProcessorLists.WITCH_HUT)
            })
    }
}
