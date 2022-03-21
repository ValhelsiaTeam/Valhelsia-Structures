package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.common.world.structures.AbstractValhelsiaStructure;
import com.stal111.valhelsia_structures.common.world.structures.pools.*;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Structure Features <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModStructureFeatures
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.1.0
 * @since 2020-09-17
 */
public class ModStructureFeatures {

    public static final Map<String, ResourceKey<ConfiguredStructureFeature<?, ?>>> RESOURCE_KEY_MAP = new HashMap<>();

    public static final Holder<ConfiguredStructureFeature<?, ?>> CASTLE = register("castle", ModStructures.CASTLE, SimpleStructurePools.CASTLE_PATTERN);
    public static final Holder<ConfiguredStructureFeature<?, ?>> CASTLE_RUIN = register("castle_ruin", ModStructures.CASTLE_RUIN, SimpleStructurePools.CASTLE_RUIN_PATTERN);
    public static final Holder<ConfiguredStructureFeature<?, ?>> DESERT_HOUSE = register("desert_house", ModStructures.DESERT_HOUSE, DesertHousePools.PATTERN);
    public static final Holder<ConfiguredStructureFeature<?, ?>> FORGE = register("forge", ModStructures.FORGE, SimpleStructurePools.FORGE_PATTERN);
    public static final Holder<ConfiguredStructureFeature<?, ?>> PLAYER_HOUSE = register("player_house", ModStructures.PLAYER_HOUSE, PlayerHousePools.PATTERN);
    public static final Holder<ConfiguredStructureFeature<?, ?>> SPAWNER_DUNGEON = register("spawner_dungeon", ModStructures.SPAWNER_DUNGEON, SpawnerDungeonPools.PATTERN);
    public static final Holder<ConfiguredStructureFeature<?, ?>> TOWER_RUIN = register("tower_ruin", ModStructures.TOWER_RUIN, SimpleStructurePools.TOWER_RUIN_PATTERN);
    public static final Holder<ConfiguredStructureFeature<?, ?>> WITCH_HUT = register("witch_hut", ModStructures.WITCH_HUT, SimpleStructurePools.WITCH_HUT_PATTERN);
    public static final Holder<ConfiguredStructureFeature<?, ?>> BIG_TREE = register("big_tree", ModStructures.BIG_TREE, BigTreePools.PATTERN);

    private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> Holder<ConfiguredStructureFeature<?, ?>> register(String name, RegistryObject<? extends AbstractValhelsiaStructure> structure, Holder<StructureTemplatePool> startPool) {
        var structureFeature = structure.get().getStructureSettings().buildFeature(structure, startPool);

        ResourceKey<ConfiguredStructureFeature<?, ?>> resourceKey = ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(ValhelsiaStructures.MOD_ID, name));

        RESOURCE_KEY_MAP.put(name, resourceKey);

        return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, resourceKey, structureFeature);
    }
}
