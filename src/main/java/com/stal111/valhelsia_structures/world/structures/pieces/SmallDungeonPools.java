package com.stal111.valhelsia_structures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.utils.JigsawHelper;
import com.stal111.valhelsia_structures.world.template.Processors;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;

public class SmallDungeonPools {

    public static final JigsawPattern PATTERN = JigsawHelper.register("dungeon1/entrances", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("dungeon1/entrance", 1), Pair.of("dungeon1/entrance1", 1)), true);

    public static void load() {
        JigsawHelper.register("dungeon1/main_rooms", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("dungeon1/main_room", 1), Pair.of("dungeon1/main_room1", 1), Pair.of("dungeon1/main_room2", 1)), true, Processors.OBSIDIAN_REPLACEMENT_PROCESSOR);
        JigsawHelper.register("dungeon1/side_rooms", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("dungeon1/side_room", 1), Pair.of("dungeon1/side_room1", 1)), true, Processors.OBSIDIAN_REPLACEMENT_PROCESSOR);
    }
}