package com.stal111.valhelsia_structures.world.structures.pools;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.utils.JigsawHelper;
import com.stal111.valhelsia_structures.world.template.Processors;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;

/**
 * Small Dungeon Pools
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.pools.SmallDungeonPieces
 *
 * @author Valhelsia Team
 * @version 16.0.5
 */
public class SmallDungeonPools {

    public static final JigsawPattern PATTERN = JigsawHelper.register("spawner_dungeon/entrances", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("spawner_dungeon/entrance", 1)), true);

    public static void load() {
        JigsawHelper.register("spawner_dungeon/main_rooms", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("spawner_dungeon/main_room_1", 1), Pair.of("spawner_dungeon/main_room_2", 1), Pair.of("spawner_dungeon/main_room_3", 1)), true);
        JigsawHelper.register("spawner_dungeon/side_rooms", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("spawner_dungeon/side_room_1", 1), Pair.of("spawner_dungeon/side_room_2", 1)), true);
        JigsawHelper.register("spawner_dungeon/spawner_rooms", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("spawner_dungeon/zombie_spawner", 1), Pair.of("spawner_dungeon/skeleton_spawner", 1), Pair.of("spawner_dungeon/spider_spawner", 1)), true);
        JigsawHelper.register("spawner_dungeon/stairs", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("spawner_dungeon/stairs_1", 1), Pair.of("spawner_dungeon/stairs_2", 1)), true);
        JigsawHelper.register("spawner_dungeon/passages", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("spawner_dungeon/passage_1", 1), Pair.of("spawner_dungeon/passage_2", 1)), true);
    }
}