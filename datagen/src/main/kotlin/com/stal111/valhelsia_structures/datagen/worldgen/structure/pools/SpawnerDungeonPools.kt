package com.stal111.valhelsia_structures.datagen.worldgen.structure.pools

import com.stal111.valhelsia_structures.common.builtin.BuiltInStructurePools
import com.stal111.valhelsia_structures.utils.TemplatePoolHelper
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.valhelsia.dataforge.RegistryDataProvider
import java.util.function.UnaryOperator

object SpawnerDungeonPools : RegistryDataProvider<StructureTemplatePool> {

    val HELPER: TemplatePoolHelper = TemplatePoolHelper.INSTANCE

    override fun bootstrap(context: BootstrapContext<StructureTemplatePool>) {
        BuiltInStructurePools.SPAWNER_DUNGEONS.create(
            HELPER,
            context,
            "spawner_dungeon/cave_entrance",
            UnaryOperator {
                it.element("large").element("small_1").element("small_2").element("small_3")
            },
            TerrainAdjustment.BEARD_THIN
        )
        HELPER.create(
            "spawner_dungeon/cave_entrance/large_bottom",
            context,
            "spawner_dungeon/cave_entrance",
            UnaryOperator { it.element("large_bottom") })
        HELPER.create(
            "spawner_dungeon/cave_entrance/small_1_bottom",
            context,
            "spawner_dungeon/cave_entrance",
            UnaryOperator { it.element("small_1_bottom") })
        HELPER.create(
            "spawner_dungeon/cave_entrance/small_2_bottom",
            context,
            "spawner_dungeon/cave_entrance",
            UnaryOperator { it.element("small_2_bottom") })
        HELPER.create(
            "spawner_dungeon/cave_entrance/small_3_bottom",
            context,
            "spawner_dungeon/cave_entrance",
            UnaryOperator { it.element("small_3_bottom") })
        HELPER.create(
            "spawner_dungeon/caves",
            context,
            "spawner_dungeon/cave",
            UnaryOperator { it.element("cave_1").element("cave_2") })
        HELPER.create(
            "spawner_dungeon/entrances",
            context,
            "spawner_dungeon/entrance",
            UnaryOperator { it.element("entrance_1").element("entrance_2") })
        HELPER.create(
            "spawner_dungeon/main_rooms",
            context,
            "spawner_dungeon/main_room",
            UnaryOperator {
                it.element("main_room_1").element("main_room_2").element("main_room_3")
            })
        HELPER.create(
            "spawner_dungeon/side_rooms",
            context,
            "spawner_dungeon/side_room",
            UnaryOperator {
                it.element("side_room_1").element("side_room_2").element("side_room_3").element("side_room_4")
            })
        HELPER.create(
            "spawner_dungeon/spawner_rooms",
            context,
            "spawner_dungeon/spawner_room",
            UnaryOperator {
                it.element("zombie_spawner_room").element("skeleton_spawner_room").element("spider_spawner_room")
            })

        HELPER.create(
            "spawner_dungeon/stairs",
            context,
            "spawner_dungeon/stairs",
            UnaryOperator {
                it.element("stairs_1").element("stairs_2").element("stairs_3").element("stairs_4")
                    .element("stairs_5").element("stairs_6")
            })
        HELPER.create(
            "spawner_dungeon/passages",
            context,
            "spawner_dungeon/passage",
            UnaryOperator {
                it.element("passage_1").element("passage_2").element("passage_3").element("passage_4")
                    .element("passage_5").element("passage_6")
            })
    }
}