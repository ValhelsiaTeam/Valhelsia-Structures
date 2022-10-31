package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.utils.JigsawHelper;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

/**
 * Simple Structure Pools <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.world.structures.pools.SimpleStructurePools
 *
 * @author Valhelsia Team
 * @version 1.18.2-0.1.0
 * @since 2021-03-21
 */
public class SimpleStructurePools {

    public static final Holder<StructureTemplatePool> CASTLE_PATTERN = JigsawHelper.register("castles", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("castle", 1)), true);
    public static final Holder<StructureTemplatePool> CASTLE_RUIN_PATTERN = JigsawHelper.register("castle_ruins", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("ruins/castle_ruin", 1)), true);
    public static final Holder<StructureTemplatePool> FORGE_PATTERN = JigsawHelper.register("forges", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("forge/forge_1", 1), Pair.of("forge/forge_2", 1)), true);
    public static final Holder<StructureTemplatePool> TOWER_RUIN_PATTERN = JigsawHelper.register("tower_ruins", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("ruins/tower_ruin_1", 1), Pair.of("ruins/tower_ruin_2", 1), Pair.of("ruins/tower_ruin_3", 1)), true);
    public static final Holder<StructureTemplatePool> WITCH_HUT_PATTERN = JigsawHelper.register("witch_huts", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("witch_hut/witch_hut_1", 1), Pair.of("witch_hut/witch_hut_2", 1)), true);
    public static final Holder<StructureTemplatePool> SPAWNER_ROOM_PATTERN = JigsawHelper.register("spawner_room", StructureTemplatePool.Projection.RIGID, ImmutableList.of(Pair.of("spawner_room/spawner_room_1", 1), Pair.of("spawner_room/spawner_room_2", 1)), true);

}
