package com.stal111.valhelsia_structures.datagen.worldgen.structure.pools

import com.stal111.valhelsia_structures.common.builtin.BuiltInStructurePools
import com.stal111.valhelsia_structures.utils.TemplatePoolHelper
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.valhelsia.dataforge.RegistryDataProvider

object PlayerHousePools : RegistryDataProvider<StructureTemplatePool> {

    val HELPER = TemplatePoolHelper.INSTANCE

    override fun bootstrap(context: BootstrapContext<StructureTemplatePool>) {
        BuiltInStructurePools.PLAYER_HOUSES.create(HELPER, context, "player_house") {
            it.element("house_1").element("house_2")
        }
        HELPER.create("player_house/feature_plate", context, "player_house") {
            it.element("feature_plate_1").element("feature_plate_2")
        }
        HELPER.create("player_house/farms", context, "player_house") {
            it.element("feature_farm_1").element("feature_farm_2").element("feature_farm_3").element("feature_bee")
                .element("feature_chicken")
        }
        HELPER.create("player_house/portals_and_farms", context, "player_house") {
            it.element("feature_farm_1").element("feature_farm_2").element("feature_farm_3").element("feature_bee")
                .element("feature_chicken").element("feature_portal_1").element("feature_portal_2")
                .element("feature_portal_3")
        }
    }
}