package com.stal111.valhelsia_structures.world.structures.pools;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.utils.JigsawHelper;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;

/**
 * Player House Pools
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.pools.PlayerHousePools
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-04-27
 */
public class DesertHousePools {

    public static final JigsawPattern PATTERN = JigsawHelper.register("desert_house/houses", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("desert_house/desert_house", 1)));

    public static void load() {
        JigsawHelper.register("desert_house/oasis_plate", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("desert_house/oasis_plate", 1)));
        JigsawHelper.register("desert_house/well_or_oasis_plate", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("desert_house/well_or_oasis_plate", 1)));
        JigsawHelper.register("desert_house/oasis", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("desert_house/feature_oasis", 1)));
        JigsawHelper.register("desert_house/wells_and_oasis", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("desert_house/feature_oasis", 1), Pair.of("desert_house/feature_well_1", 1), Pair.of("desert_house/feature_well_2", 1)));
    }
}