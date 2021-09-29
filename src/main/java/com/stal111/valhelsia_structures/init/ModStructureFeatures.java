package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.world.structures.pools.*;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Structure Features
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModStructureFeatures
 *
 * @author Valhelsia Team
 * @version 1.0.3
 * @since 2020-09-17
 */
public class ModStructureFeatures {

    private static final List<StructureFeature<?, ?>> MOD_STRUCTURE_FEATURES = new ArrayList<>();

    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> CASTLE = register(ModStructures.CASTLE.get().withConfiguration(new VillageConfig(() -> SimpleStructurePools.CASTLE_PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> CASTLE_RUIN = register(ModStructures.CASTLE_RUIN.get().withConfiguration(new VillageConfig(() -> SimpleStructurePools.CASTLE_RUIN_PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> DESERT_HOUSE = register(ModStructures.DESERT_HOUSE.get().withConfiguration(new VillageConfig(() -> DesertHousePools.PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> FORGE = register(ModStructures.FORGE.get().withConfiguration(new VillageConfig(() -> SimpleStructurePools.FORGE_PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> PLAYER_HOUSE = register(ModStructures.PLAYER_HOUSE.get().withConfiguration(new VillageConfig(() -> PlayerHousePools.PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> SPAWNER_DUNGEON = register(ModStructures.SPAWNER_DUNGEON.get().withConfiguration(new VillageConfig(() -> SpawnerDungeonPools.PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> TOWER_RUIN = register(ModStructures.TOWER_RUIN.get().withConfiguration(new VillageConfig(() -> SimpleStructurePools.TOWER_RUIN_PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> WITCH_HUT = register(ModStructures.WITCH_HUT.get().withConfiguration(new VillageConfig(() -> SimpleStructurePools.WITCH_HUT_PATTERN, 7)));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> BIG_TREE = register(ModStructures.BIG_TREE.get().withConfiguration(new VillageConfig(() -> BigTreePools.PATTERN, 7)));

    private static <FC extends IFeatureConfig, F extends Structure<FC>> StructureFeature<FC, F> register(StructureFeature<FC, F> structureFeature) {
        MOD_STRUCTURE_FEATURES.add(structureFeature);

        return structureFeature;
    }

    public static void registerStructureFeatures() {
        for (StructureFeature<?, ?> structureFeature : MOD_STRUCTURE_FEATURES) {
            WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, structureFeature.field_236268_b_.getStructureName(), structureFeature);

            FlatGenerationSettings.STRUCTURES.put(structureFeature.field_236268_b_, structureFeature);
        }
    }
}
