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
public class DesertHousePools {

    public static final Holder<StructureTemplatePool> PATTERN = JigsawHelper.register("desert_house/houses", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("desert_house/desert_house", 1)));

    public static void load() {
        JigsawHelper.register("desert_house/oasis_plate", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("desert_house/oasis_plate", 1)));
        JigsawHelper.register("desert_house/well_or_oasis_plate", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("desert_house/well_or_oasis_plate", 1)));
        JigsawHelper.register("desert_house/oasis", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("desert_house/feature_oasis_1", 1), Pair.of("desert_house/feature_oasis_2", 1)));
        JigsawHelper.register("desert_house/wells_and_oasis", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("desert_house/feature_oasis", 1), Pair.of("desert_house/feature_well_1", 1), Pair.of("desert_house/feature_well_2", 1)));
    }
}