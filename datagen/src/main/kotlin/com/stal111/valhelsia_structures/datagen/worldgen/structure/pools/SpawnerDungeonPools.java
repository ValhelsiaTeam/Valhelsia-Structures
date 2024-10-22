package com.stal111.valhelsia_structures.datagen.worldgen.structure.pools;

import com.stal111.valhelsia_structures.common.builtin.BuiltInStructurePools;
import com.stal111.valhelsia_structures.utils.TemplatePoolHelper;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.valhelsia.valhelsia_core.api.common.registry.helper.datapack.DatapackRegistryClass;


/**
 * @author Valhelsia Team
 */
public class SpawnerDungeonPools extends DatapackRegistryClass<StructureTemplatePool> {

    public static final TemplatePoolHelper HELPER = TemplatePoolHelper.INSTANCE;

    public SpawnerDungeonPools(BootstrapContext<StructureTemplatePool> context) {
        super(context);
    }

    @Override
    public void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        BuiltInStructurePools.SPAWNER_DUNGEONS.create(HELPER, context, "spawner_dungeon/cave_entrance", builder -> builder.element("large").element("small_1").element("small_2").element("small_3"), TerrainAdjustment.BEARD_THIN);
        HELPER.create("spawner_dungeon/cave_entrance/large_bottom", context, "spawner_dungeon/cave_entrance", builder -> builder.element("large_bottom"));
        HELPER.create("spawner_dungeon/cave_entrance/small_1_bottom", context, "spawner_dungeon/cave_entrance", builder -> builder.element("small_1_bottom"));
        HELPER.create("spawner_dungeon/cave_entrance/small_2_bottom", context, "spawner_dungeon/cave_entrance", builder -> builder.element("small_2_bottom"));
        HELPER.create("spawner_dungeon/cave_entrance/small_3_bottom", context, "spawner_dungeon/cave_entrance", builder -> builder.element("small_3_bottom"));
        HELPER.create("spawner_dungeon/caves", context, "spawner_dungeon/cave", builder -> builder.element("cave_1").element("cave_2"));
        HELPER.create("spawner_dungeon/entrances", context, "spawner_dungeon/entrance", builder -> builder.element("entrance_1").element("entrance_2"));
        HELPER.create("spawner_dungeon/main_rooms", context, "spawner_dungeon/main_room", builder -> builder.element("main_room_1").element("main_room_2").element("main_room_3"));
        HELPER.create("spawner_dungeon/side_rooms", context, "spawner_dungeon/side_room", builder -> builder.element("side_room_1").element("side_room_2").element("side_room_3").element("side_room_4"));
        HELPER.create("spawner_dungeon/spawner_rooms", context, "spawner_dungeon/spawner_room", builder -> builder.element("zombie_spawner_room").element("skeleton_spawner_room").element("spider_spawner_room"));

        HELPER.create("spawner_dungeon/stairs", context, "spawner_dungeon/stairs", builder -> builder.element("stairs_1").element("stairs_2").element("stairs_3").element("stairs_4").element("stairs_5").element("stairs_6"));
        HELPER.create("spawner_dungeon/passages", context, "spawner_dungeon/passage", builder -> builder.element("passage_1").element("passage_2").element("passage_3").element("passage_4").element("passage_5").element("passage_6"));
    }
}