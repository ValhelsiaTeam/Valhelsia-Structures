package com.stal111.valhelsia_structures.datagen.worldgen.structure.pools

import com.stal111.valhelsia_structures.common.builtin.BuiltInStructurePools
import com.stal111.valhelsia_structures.utils.TemplatePoolHelper
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.valhelsia.dataforge.RegistryDataProvider
import net.valhelsia.valhelsia_core.api.common.registry.helper.datapack.DatapackRegistryClass
import net.valhelsia.valhelsia_core.api.common.world.structure.jigsaw.JigsawBuilder
import java.util.function.UnaryOperator

object BigTreePools : RegistryDataProvider<StructureTemplatePool> {

    val HELPER = TemplatePoolHelper.INSTANCE

    override fun bootstrap(context: BootstrapContext<StructureTemplatePool>) {
        BuiltInStructurePools.BIG_TREES.create(
            HELPER,
            context,
            "vegetations",
            UnaryOperator { it.element("big_tree") })
        HELPER.create(
            "vegetations/big_tree/underside",
            context,
            "vegetations",
            UnaryOperator { it.element("big_tree_underside") })
    }
}