package com.stal111.valhelsia_structures.core.init.world;

import com.stal111.valhelsia_structures.common.world.structures.LegacyValhelsiaJigsawStructure;
import com.stal111.valhelsia_structures.common.world.structures.ValhelsiaJigsawStructure;
import com.stal111.valhelsia_structures.common.world.structures.ValhelsiaStructureSettings;
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProvider;
import com.stal111.valhelsia_structures.common.world.structures.pools.*;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.valhelsia.valhelsia_core.core.data.DataProviderInfo;
import net.valhelsia.valhelsia_core.core.registry.helper.DatapackRegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.DatapackRegistryHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Valhelsia Team
 * @since 2022-06-24
 */
public class ModStructures extends DatapackRegistryClass<Structure> {

    public static final DatapackRegistryHelper<Structure> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getDatapackHelper(Registries.STRUCTURE);

    public static final Map<String, ValhelsiaStructureSettings> STRUCTURE_SETTINGS_MAP = new HashMap<>();

    public static final ResourceKey<Structure> CASTLE = HELPER.createKey("castle");
    public static final ResourceKey<Structure> CASTLE_RUIN = HELPER.createKey("castle_ruin");
    public static final ResourceKey<Structure> DESERT_HOUSE = HELPER.createKey("desert_house");
    public static final ResourceKey<Structure> FORGE = HELPER.createKey("forge");
    public static final ResourceKey<Structure> PLAYER_HOUSE = HELPER.createKey("player_house");
    public static final ResourceKey<Structure> SPAWNER_DUNGEON = HELPER.createKey("spawner_dungeon");
    public static final ResourceKey<Structure> TOWER_RUIN = HELPER.createKey("tower_ruin");
    public static final ResourceKey<Structure> WITCH_HUT = HELPER.createKey("witch_hut");
    public static final ResourceKey<Structure> BIG_TREE = HELPER.createKey("big_tree");
    public static final ResourceKey<Structure> SPAWNER_ROOM = HELPER.createKey("spawner_room");
    public static final ResourceKey<Structure> DEEP_SPAWNER_ROOM = HELPER.createKey("deep_spawner_room");

    public ModStructures(DataProviderInfo info, BootstapContext<Structure> context) {
        super(info, context);
    }

    public void bootstrap(BootstapContext<Structure> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> templatePoolRegistry = context.lookup(Registries.TEMPLATE_POOL);

        context.register(CASTLE, new LegacyValhelsiaJigsawStructure(structure(biomeRegistry.getOrThrow(ModTags.Biomes.HAS_CASTLE), TerrainAdjustment.BEARD_THIN), "castle", templatePoolRegistry.getOrThrow(SimpleStructurePools.CASTLES), 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG));
        context.register(CASTLE_RUIN, new LegacyValhelsiaJigsawStructure(structure(biomeRegistry.getOrThrow(ModTags.Biomes.HAS_CASTLE_RUIN), TerrainAdjustment.BEARD_THIN), "castle_ruin", templatePoolRegistry.getOrThrow(SimpleStructurePools.CASTLE_RUINS), 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG));
        context.register(DESERT_HOUSE, new LegacyValhelsiaJigsawStructure(structure(biomeRegistry.getOrThrow(ModTags.Biomes.HAS_DESERT_HOUSE), TerrainAdjustment.BEARD_THIN), "desert_house", templatePoolRegistry.getOrThrow(DesertHousePools.START), 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG));
        context.register(FORGE, new LegacyValhelsiaJigsawStructure(structure(biomeRegistry.getOrThrow(ModTags.Biomes.HAS_FORGE), TerrainAdjustment.BEARD_THIN), "forge", templatePoolRegistry.getOrThrow(SimpleStructurePools.FORGES), 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG));
        context.register(PLAYER_HOUSE, new LegacyValhelsiaJigsawStructure(structure(biomeRegistry.getOrThrow(ModTags.Biomes.HAS_PLAYER_HOUSE), TerrainAdjustment.BEARD_THIN), "player_house", templatePoolRegistry.getOrThrow(PlayerHousePools.START), 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG));
        context.register(SPAWNER_DUNGEON, ValhelsiaJigsawStructure.builder(context, ModTags.Biomes.HAS_SPAWNER_DUNGEON, GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE, SpawnerDungeonPools.START).startHeight(StructureHeightProvider.surfaceBetween(VerticalAnchor.absolute(0), VerticalAnchor.absolute(75))).individualTerrainAdjustment().build());
        context.register(TOWER_RUIN, new LegacyValhelsiaJigsawStructure(structure(biomeRegistry.getOrThrow(ModTags.Biomes.HAS_TOWER_RUIN), TerrainAdjustment.BEARD_THIN), "tower_ruin", templatePoolRegistry.getOrThrow(SimpleStructurePools.TOWER_RUINS), 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG));
        context.register(WITCH_HUT, ValhelsiaJigsawStructure.builder(context, ModTags.Biomes.HAS_WITCH_HUT, GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.BEARD_THIN, SimpleStructurePools.WITCH_HUTS).build());
        context.register(BIG_TREE, new LegacyValhelsiaJigsawStructure(structure(biomeRegistry.getOrThrow(ModTags.Biomes.HAS_BIG_TREE), TerrainAdjustment.BEARD_THIN), "big_tree", templatePoolRegistry.getOrThrow(BigTreePools.START), 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG));
        context.register(SPAWNER_ROOM, ValhelsiaJigsawStructure.builder(context, ModTags.Biomes.HAS_SPAWNER_ROOM, GenerationStep.Decoration.UNDERGROUND_STRUCTURES, TerrainAdjustment.NONE, SimpleStructurePools.SPAWNER_ROOMS).startHeight(StructureHeightProvider.spawnerRoom(VerticalAnchor.absolute(0))).build());
        context.register(DEEP_SPAWNER_ROOM, ValhelsiaJigsawStructure.builder(context, ModTags.Biomes.HAS_DEEP_SPAWNER_ROOM, GenerationStep.Decoration.UNDERGROUND_STRUCTURES, TerrainAdjustment.NONE, SimpleStructurePools.DEEP_SPAWNER_ROOMS).startHeight(StructureHeightProvider.deepSpawnerRoom(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(-1))).build());
    }

//    public static final Holder<? extends Structure> CASTLE = register("castle",
//            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.5D), ConfigurableValue.of(32)));
//    public static final Holder<? extends Structure> CASTLE_RUIN = register("castle_ruin",
//            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.6D), ConfigurableValue.of(32)));
//    public static final Holder<? extends Structure> DESERT_HOUSE = register("desert_house",
//            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.7D), ConfigurableValue.of(20)));
//    public static final Holder<? extends Structure> FORGE = register("forge",
//            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.7D), ConfigurableValue.of(20)));
//    public static final Holder<? extends Structure> PLAYER_HOUSE = register("player_house",
//            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.7D), ConfigurableValue.of(18)));
//    public static final Holder<? extends Structure> SPAWNER_DUNGEON = register("spawner_dungeon",
//            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.8D), ConfigurableValue.of(18), ConfigurableValue.of(0)));
//    public static final Holder<? extends Structure> TOWER_RUIN = register("tower_ruin",
//            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.8D), ConfigurableValue.of(22)));
//    public static final Holder<? extends Structure> WITCH_HUT = register("witch_hut",
//            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.85D), ConfigurableValue.of(13), ConfigurableValue.of(3)));
//    public static final Holder<? extends Structure> BIG_TREE = register("big_tree",
//            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.7D), ConfigurableValue.of(25)));
//    public static final Holder<? extends Structure> SPAWNER_ROOM = register("spawner_room",
//            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.9D), null, ConfigurableValue.of(0)));
//    public static final Holder<? extends Structure> DEEP_SPAWNER_ROOM = register("deep_spawner_room",
//            ValhelsiaStructureSettings.of(ConfigurableValue.of(1.0D), null, ConfigurableValue.of(0)));

//    public static <T extends Structure> Holder<T> register(String name, Supplier<T> structure, ValhelsiaStructureSettings structureSettings) {
//        RegistryObject<T> registryObject = HELPER.register(name, structure);
//
//        STRUCTURE_SETTINGS_MAP.put(name, structureSettings);
//
//        return registryObject.getHolder().get();
//    }

    private static Structure.StructureSettings structure(HolderSet<Biome> biomes, Map<MobCategory, StructureSpawnOverride> spawnOverrideMap, GenerationStep.Decoration decoration, TerrainAdjustment terrainAdjustment) {
        return new Structure.StructureSettings(biomes, spawnOverrideMap, decoration, terrainAdjustment);
    }

    private static Structure.StructureSettings structure(HolderSet<Biome> biomes, GenerationStep.Decoration decoration, TerrainAdjustment terrainAdjustment) {
        return structure(biomes, Map.of(), decoration, terrainAdjustment);
    }

    private static Structure.StructureSettings structure(HolderSet<Biome> biomes, TerrainAdjustment terrainAdjustment) {
        return structure(biomes, Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, terrainAdjustment);
    }
}
