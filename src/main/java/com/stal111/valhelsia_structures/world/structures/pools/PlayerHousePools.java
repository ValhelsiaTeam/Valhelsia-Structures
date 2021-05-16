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
public class PlayerHousePools {

    public static final JigsawPattern PATTERN = JigsawHelper.register("player_house/houses", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("player_house/house", 1), Pair.of("player_house/incomplete_house", 1)));

    public static void load() {
        JigsawHelper.register("player_house/feature_plate", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("player_house/feature_plate_1", 1)));
        JigsawHelper.register("player_house/farms", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("player_house/feature_farm", 1), Pair.of("player_house/feature_bee", 1)));
        JigsawHelper.register("player_house/portals_and_farms", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("player_house/feature_farm", 1), Pair.of("player_house/feature_bee", 1), Pair.of("player_house/feature_portal_1", 1), Pair.of("player_house/feature_portal_2", 1)));
    }
}