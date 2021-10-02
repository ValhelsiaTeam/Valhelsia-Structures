package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.utils.JigsawHelper;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;

/**
 * Spawner Dungeon Pools <br>
 * Valhelsia-Structures - com.stal111.valhelsia_structures.common.world.structures.pools.SpawnerDungeonPieces
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 */
public class SpawnerDungeonPools {

    public static final StructureTemplatePool PATTERN = JigsawHelper.register("spawner_dungeon/entrances", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("spawner_dungeon/entrance", 1)), true);

    public static void load() {
        JigsawHelper.register("spawner_dungeon/main_rooms", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("spawner_dungeon/main_room_1", 1), Pair.of("spawner_dungeon/main_room_2", 1), Pair.of("spawner_dungeon/main_room_3", 1)), true);
        JigsawHelper.register("spawner_dungeon/side_rooms", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("spawner_dungeon/side_room_1", 1), Pair.of("spawner_dungeon/side_room_2", 1)), true);
        JigsawHelper.register("spawner_dungeon/spawner_rooms", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("spawner_dungeon/zombie_spawner", 1), Pair.of("spawner_dungeon/skeleton_spawner", 1), Pair.of("spawner_dungeon/spider_spawner", 1)), true);
        JigsawHelper.register("spawner_dungeon/stairs", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("spawner_dungeon/stairs_1", 1), Pair.of("spawner_dungeon/stairs_2", 1)), true);
        JigsawHelper.register("spawner_dungeon/passages", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("spawner_dungeon/passage_1", 1), Pair.of("spawner_dungeon/passage_2", 1)), true);
        JigsawHelper.register("spawner_dungeon/caves", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("spawner_dungeon/cave_entrance", 1)));
    }
}