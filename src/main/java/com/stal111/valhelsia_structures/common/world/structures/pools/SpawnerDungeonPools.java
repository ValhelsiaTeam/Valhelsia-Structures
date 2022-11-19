package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.stal111.valhelsia_structures.common.world.structures.jigsaw.JigsawBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

/**
 * @author Valhelsia Team
 */
public class SpawnerDungeonPools {

    public static final Holder<StructureTemplatePool> PATTERN = JigsawBuilder.builder("spawner_dungeon/cave_entrance", "spawner_dungeon/cave_entrances").element("large").element("small_1").element("small_2").element("small_3").build();

    public static void load() {
        JigsawBuilder.builder("spawner_dungeon/cave_entrance", "spawner_dungeon/cave_entrance/large_bottom").element("large_bottom").build();
        JigsawBuilder.builder("spawner_dungeon/cave_entrance", "spawner_dungeon/cave_entrance/small_1_bottom").element("small_1_bottom").build();
        JigsawBuilder.builder("spawner_dungeon/cave_entrance", "spawner_dungeon/cave_entrance/small_2_bottom").element("small_2_bottom").build();
        JigsawBuilder.builder("spawner_dungeon/cave_entrance", "spawner_dungeon/cave_entrance/small_3_bottom").element("small_3_bottom").build();

        JigsawBuilder.builder("spawner_dungeon/cave", "spawner_dungeon/caves").element("cave_1").element("cave_2").build();
        JigsawBuilder.builder("spawner_dungeon/entrance", "spawner_dungeon/entrances").element("entrance_1").element("entrance_2").build();
        JigsawBuilder.builder("spawner_dungeon/main_room", "spawner_dungeon/main_rooms").element("main_room_1").element("main_room_2").element("main_room_3").build();
        JigsawBuilder.builder("spawner_dungeon/side_room", "spawner_dungeon/side_rooms").element("side_room_1").element("side_room_2").element("side_room_3").element("side_room_4").build();
        JigsawBuilder.builder("spawner_dungeon/spawner_room", "spawner_dungeon/spawner_rooms").element("zombie_spawner_room").element("skeleton_spawner_room").element("spider_spawner_room").build();

        JigsawBuilder.builder("spawner_dungeon/stairs", "spawner_dungeon/stairs").element("stairs_1").element("stairs_2").element("stairs_3").element("stairs_4").element("stairs_5").element("stairs_6").build();
        JigsawBuilder.builder("spawner_dungeon/passage", "spawner_dungeon/passages").element("passage_1").element("passage_2").element("passage_3").element("passage_4").element("passage_5").element("passage_6").build();
    }
}