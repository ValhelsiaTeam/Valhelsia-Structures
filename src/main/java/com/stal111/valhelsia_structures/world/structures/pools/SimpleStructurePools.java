package com.stal111.valhelsia_structures.world.structures.pools;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.utils.JigsawHelper;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;

/**
 * Simple Structure Pools
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.pools.SimpleStructurePools
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-03-21
 */
public class SimpleStructurePools {

    public static final JigsawPattern CASTLE_PATTERN = JigsawHelper.register("castles", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("castle", 1)), true);
    public static final JigsawPattern CASTLE_RUIN_PATTERN = JigsawHelper.register("castle_ruins", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("ruins/castle_ruin", 1)), true);
    public static final JigsawPattern FORGE_PATTERN = JigsawHelper.register("forges", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("forge", 1)), true);
    public static final JigsawPattern TOWER_RUIN_PATTERN = JigsawHelper.register("tower_ruins", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("ruins/tower_ruin_1", 1), Pair.of("ruins/tower_ruin_2", 1), Pair.of("ruins/tower_ruin_3", 1)), true);
    public static final JigsawPattern WITCH_HUT_PATTERN = JigsawHelper.register("witch_huts", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("witch_hut/witch_hut_1", 1), Pair.of("witch_hut/witch_hut_2", 1)), true);

}