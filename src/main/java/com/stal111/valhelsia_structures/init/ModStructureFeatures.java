package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.world.structures.pieces.*;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

/**
 * Structure Features
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModStructureFeatures
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2020-09-17
 */

public class ModStructureFeatures {

    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> CASTLE = register("castle", ModStructures.CASTLE.get().withConfiguration(new VillageConfig(() -> CastlePools.PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> CASTLE_RUIN = register("castle_ruin", ModStructures.CASTLE_RUIN.get().withConfiguration(new VillageConfig(() -> CastleRuinPools.PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> DESERT_HOUSE = register("desert_house", ModStructures.DESERT_HOUSE.get().withConfiguration(new VillageConfig(() -> DesertHousePools.PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> FORGE = register("forge", ModStructures.FORGE.get().withConfiguration(new VillageConfig(() -> ForgePools.PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> PLAYER_HOUSE = register("player_house", ModStructures.PLAYER_HOUSE.get().withConfiguration(new VillageConfig(() -> PlayerHousePools.PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> SMALL_DUNGEON = register("small_dungeon", ModStructures.SMALL_DUNGEON.get().withConfiguration(new VillageConfig(() -> SmallDungeonPools.PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> TOWER_RUIN = register("tower_ruin", ModStructures.TOWER_RUIN.get().withConfiguration(new VillageConfig(() -> TowerRuinPools.PATTERN, 7)));

    private static <FC extends IFeatureConfig, F extends Structure<FC>> StructureFeature<FC, F> register(String name, StructureFeature<FC, F> structureFeature) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, name, structureFeature);
    }
}
