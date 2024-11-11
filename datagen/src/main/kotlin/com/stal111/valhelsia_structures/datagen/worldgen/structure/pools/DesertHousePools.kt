package com.stal111.valhelsia_structures.datagen.worldgen.structure.pools

import com.stal111.valhelsia_structures.common.builtin.BuiltInStructurePools
import com.stal111.valhelsia_structures.utils.TemplatePoolHelper
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.valhelsia.dataforge.RegistryDataProvider

object DesertHousePools : RegistryDataProvider<StructureTemplatePool> {

    val HELPER = TemplatePoolHelper.INSTANCE

    override fun bootstrap(context: BootstrapContext<StructureTemplatePool>) {
        BuiltInStructurePools.DESERT_HOUSES.create(HELPER, context, "desert_house") { it.element("desert_house") }
        HELPER.create("desert_house/oasis_plate", context, "desert_house") { it.element("oasis_plate") }
        HELPER.create("desert_house/well_or_oasis_plate", context, "desert_house") { it.element("well_or_oasis_plate") }
        HELPER.create("desert_house/oasis", context, "desert_house") {
            it.element("feature_oasis_1").element("feature_oasis_2")
        }
        HELPER.create("desert_house/wells_and_oasis", context, "desert_house") {
            it.element("feature_oasis_1").element("feature_oasis_2").element("feature_well_1").element("feature_well_2")
        }
    }
}