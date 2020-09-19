package com.stal111.valhelsia_structures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.utils.JigsawHelper;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;

/**
 * Desert House Pieces
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.pieces.DesertHousePieces
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2020-05-27
 */
public class DesertHousePools {

    public static final JigsawPattern PATTERN = JigsawHelper.register("desert_houses", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("desert_house", 1)));

}
