package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.common.world.structures.pools.*;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * Structure Features <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModStructureFeatures
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2020-09-17
 */
public class ModStructureFeatures {

    private static final List<ConfiguredStructureFeature<?, ?>> MOD_STRUCTURE_FEATURES = new ArrayList<>();

    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> CASTLE = register(ModStructures.CASTLE.get().configured(new JigsawConfiguration(() -> SimpleStructurePools.CASTLE_PATTERN, 7)));
    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> CASTLE_RUIN = register(ModStructures.CASTLE_RUIN.get().configured(new JigsawConfiguration(() -> SimpleStructurePools.CASTLE_RUIN_PATTERN, 7)));
    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> DESERT_HOUSE = register(ModStructures.DESERT_HOUSE.get().configured(new JigsawConfiguration(() -> DesertHousePools.PATTERN, 7)));
    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> FORGE = register(ModStructures.FORGE.get().configured(new JigsawConfiguration(() -> SimpleStructurePools.FORGE_PATTERN, 7)));
    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> PLAYER_HOUSE = register(ModStructures.PLAYER_HOUSE.get().configured(new JigsawConfiguration(() -> PlayerHousePools.PATTERN, 7)));
    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> SPAWNER_DUNGEON = register(ModStructures.SPAWNER_DUNGEON.get().configured(new JigsawConfiguration(() -> SpawnerDungeonPools.PATTERN, 7)));
    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> TOWER_RUIN = register(ModStructures.TOWER_RUIN.get().configured(new JigsawConfiguration(() -> SimpleStructurePools.TOWER_RUIN_PATTERN, 7)));
    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> WITCH_HUT = register(ModStructures.WITCH_HUT.get().configured(new JigsawConfiguration(() -> SimpleStructurePools.WITCH_HUT_PATTERN, 7)));
    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> BIG_TREE = register(ModStructures.BIG_TREE.get().configured(new JigsawConfiguration(() -> BigTreePools.PATTERN, 7)));

    private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> ConfiguredStructureFeature<FC, F> register(ConfiguredStructureFeature<FC, F> structureFeature) {
        MOD_STRUCTURE_FEATURES.add(structureFeature);

        return structureFeature;
    }

    public static void registerStructureFeatures() {
        for (ConfiguredStructureFeature<?, ?> structureFeature : MOD_STRUCTURE_FEATURES) {
            BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, structureFeature.feature.getFeatureName(), structureFeature);

            FlatLevelGeneratorSettings.STRUCTURE_FEATURES.put(structureFeature.feature, structureFeature);
        }
    }
}
