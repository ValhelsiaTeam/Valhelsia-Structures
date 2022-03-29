package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.utils.JigsawHelper;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

/**
 * Player House Pools <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.world.structures.pools.PlayerHousePools
 *
 * @author Valhelsia Team
 * @version 1.18.2-0.1.0
 * @since 2021-04-27
 */
public class PlayerHousePools {

    public static final Holder<StructureTemplatePool> PATTERN = JigsawHelper.register("player_house/houses", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("player_house/house", 1), Pair.of("player_house/incomplete_house", 1)));

    public static void load() {
        JigsawHelper.register("player_house/feature_plate", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("player_house/feature_plate_1", 1)));
        JigsawHelper.register("player_house/farms", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("player_house/feature_farm", 1), Pair.of("player_house/feature_bee", 1)));
        JigsawHelper.register("player_house/portals_and_farms", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("player_house/feature_farm", 1), Pair.of("player_house/feature_bee", 1), Pair.of("player_house/feature_portal_1", 1), Pair.of("player_house/feature_portal_2", 1)));
    }
}