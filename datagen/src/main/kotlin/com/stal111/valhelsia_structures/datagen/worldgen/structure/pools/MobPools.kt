package com.stal111.valhelsia_structures.datagen.worldgen.structure.pools

import com.stal111.valhelsia_structures.utils.TemplatePoolHelper
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.valhelsia.dataforge.RegistryDataProvider
import java.util.function.UnaryOperator

object MobPools : RegistryDataProvider<StructureTemplatePool> {

    val HELPER = TemplatePoolHelper.INSTANCE

    override fun bootstrap(context: BootstrapContext<StructureTemplatePool>) {
        // Entities
        HELPER.create(
            "mobs/bee",
            context,
            "mobs/bee",
            UnaryOperator { it.element("bee_1") })
        HELPER.create(
            "mobs/bees",
            context,
            "mobs/bee",
            UnaryOperator { it.element("bee_1").element("bee_2") })
        HELPER.create(
            "mobs/skeleton",
            context,
            "mobs",
            UnaryOperator { it.element("skeleton") })
        HELPER.create(
            "mobs/pillager_with_axe_1",
            context,
            "mobs",
            UnaryOperator { it.element("pillager_with_axe_1") })
        HELPER.create(
            "mobs/pillager_with_crossbow_1",
            context,
            "mobs",
            UnaryOperator { it.element("pillager_with_crossbow_1") })
        HELPER.create(
            "mobs/pillager_with_sword_1",
            context,
            "mobs",
            UnaryOperator { it.element("pillager_with_sword_1") })
        HELPER.create(
            "mobs/witch_with_cat",
            context,
            "mobs",
            UnaryOperator { it.element("witch_with_cat") })
        HELPER.create(
            "mobs/horse",
            context,
            "mobs",
            UnaryOperator { it.element("horse") })
        HELPER.create(
            "mobs/villagers",
            context,
            "mobs/villagers",
            UnaryOperator {
                it.element("villager").element("villager_khytwel").element("villager_vaelzan")
                    .element("villager_stal").element("villager_cynthal").element("villager_kanadet")
            })
        HELPER.create(
            "mobs/chickens",
            context,
            "mobs/chickens",
            UnaryOperator {
                it.element("chicken_1").element("chicken_2").element("chicken_3")
            })

        // Spawners
        HELPER.create(
            "mobs/spawners/zombie_or_skeleton_or_spider",
            context,
            "mobs/spawners",
            UnaryOperator {
                it.element("zombie").element("skeleton").element("spider")
            })
        HELPER.create(
            "mobs/spawners/zombie",
            context,
            "mobs/spawners",
            UnaryOperator { it.element("zombie") })
        HELPER.create(
            "mobs/spawners/skeleton",
            context,
            "mobs/spawners",
            UnaryOperator { it.element("skeleton") })
        HELPER.create(
            "mobs/spawners/spider",
            context,
            "mobs/spawners",
            UnaryOperator { it.element("spider") })

        // Special Spawners
        HELPER.create(
            "mobs/special_spawners/zombie_or_skeleton_or_spider",
            context,
            "mobs/special_spawners",
            UnaryOperator {
                it.element("zombie").element("skeleton").element("spider")
            })
        HELPER.create(
            "mobs/special_spawners/drowned",
            context,
            "mobs/special_spawners",
            UnaryOperator { it.element("drowned") })
    }
}
