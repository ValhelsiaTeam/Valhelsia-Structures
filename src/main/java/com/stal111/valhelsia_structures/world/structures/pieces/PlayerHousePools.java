package com.stal111.valhelsia_structures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.utils.JigsawHelper;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;

public class PlayerHousePools {

    public static final JigsawPattern PATTERN = JigsawHelper.register("player_houses", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("player_house", 1)), true);

}
