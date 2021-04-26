package com.stal111.valhelsia_structures.world.structures.pools;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.utils.JigsawHelper;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;

/**
 * Mob Pools
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.pools.MobPools
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-04-26
 */
public class MobPools {

    public static void load() {
        JigsawHelper.register("mobs/bee", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("mobs/bee", 1)));
        JigsawHelper.register("mobs/skeleton", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("mobs/skeleton", 1)));
        JigsawHelper.register("mobs/pillager_with_axe_1", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("mobs/pillager_with_axe_1", 1)));
        JigsawHelper.register("mobs/pillager_with_crossbow_1", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("mobs/pillager_with_crossbow_1", 1)));
        JigsawHelper.register("mobs/pillager_with_sword_1", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("mobs/pillager_with_sword_1", 1)));
        JigsawHelper.register("mobs/witch_with_cat", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("mobs/witch_with_cat", 1)));
    }
}
