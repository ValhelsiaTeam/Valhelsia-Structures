package com.stal111.valhelsia_structures.world.structures.pools;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.utils.JigsawHelper;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;

/**
 * Big Tree Pools
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.pools.BigTreePools
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-04-27
 */
public class BigTreePools {

    public static final JigsawPattern PATTERN = JigsawHelper.register("vegetations/big_trees", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("vegetations/big_tree", 1)));

    public static void load() {
        JigsawHelper.register("vegetations/big_tree/underside", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("vegetations/big_tree_underside", 1)));
    }
}