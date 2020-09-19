package com.stal111.valhelsia_structures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.utils.JigsawHelper;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;

/**
 * Castle Pieces
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.pieces.CastlePieces
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2020-05-27
 */

public class CastlePools {

    public static final JigsawPattern PATTERN = JigsawHelper.register("castles", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("castle", 1)), true);

}