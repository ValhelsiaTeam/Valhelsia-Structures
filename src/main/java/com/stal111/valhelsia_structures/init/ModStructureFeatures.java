package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.world.structures.pools.*;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

/**
 * Structure Features
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModStructureFeatures
 *
 * @author Valhelsia Team
 * @version 1.0.2
 * @since 2020-09-17
 */
public class ModStructureFeatures {

    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> CASTLE = register("castle", ModStructures.CASTLE.get().withConfiguration(new VillageConfig(() -> SimpleStructurePools.CASTLE_PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> CASTLE_RUIN = register("castle_ruin", ModStructures.CASTLE_RUIN.get().withConfiguration(new VillageConfig(() -> SimpleStructurePools.CASTLE_RUIN_PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> DESERT_HOUSE = register("desert_house", ModStructures.DESERT_HOUSE.get().withConfiguration(new VillageConfig(() -> DesertHousePools.PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> FORGE = register("forge", ModStructures.FORGE.get().withConfiguration(new VillageConfig(() -> SimpleStructurePools.FORGE_PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> PLAYER_HOUSE = register("player_house", ModStructures.PLAYER_HOUSE.get().withConfiguration(new VillageConfig(() -> PlayerHousePools.PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> SPAWNER_DUNGEON = register("small_dungeon", ModStructures.SPAWNER_DUNGEON.get().withConfiguration(new VillageConfig(() -> SpawnerDungeonPools.PATTERN, 10)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> TOWER_RUIN = register("tower_ruin", ModStructures.TOWER_RUIN.get().withConfiguration(new VillageConfig(() -> SimpleStructurePools.TOWER_RUIN_PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> WITCH_HUT = register("witch_hut", ModStructures.WITCH_HUT.get().withConfiguration(new VillageConfig(() -> SimpleStructurePools.WITCH_HUT_PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> BIG_TREE = register("big_tree", ModStructures.BIG_TREE.get().withConfiguration(new VillageConfig(() -> BigTreePools.PATTERN, 7)));

    private static <FC extends IFeatureConfig, F extends Structure<FC>> StructureFeature<FC, F> register(String name, StructureFeature<FC, F> structureFeature) {
        if (!FlatGenerationSettings.STRUCTURES.containsKey(structureFeature.field_236268_b_)) {
            FlatGenerationSettings.STRUCTURES.put(structureFeature.field_236268_b_, structureFeature);
        }

        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, name, structureFeature);
    }
}
