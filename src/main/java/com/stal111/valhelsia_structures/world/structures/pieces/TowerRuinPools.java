package com.stal111.valhelsia_structures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.utils.JigsawHelper;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;

public class TowerRuinPools {

    public static final JigsawPattern PATTERN = JigsawHelper.register("tower_ruins", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("tower_ruin", 1), Pair.of("tower_ruin1", 1), Pair.of("tower_ruin2", 1)), true);

}