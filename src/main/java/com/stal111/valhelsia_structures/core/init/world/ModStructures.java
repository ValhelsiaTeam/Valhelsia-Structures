package com.stal111.valhelsia_structures.core.init.world;

import com.stal111.valhelsia_structures.common.world.structures.ValhelsiaJigsawStructure;
import com.stal111.valhelsia_structures.common.world.structures.ValhelsiaStructureSettings;
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProvider;
import com.stal111.valhelsia_structures.common.world.structures.pools.*;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.ConfigurableValue;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.core.registry.RegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.RegistryHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Valhelsia Team
 * @since 2022-06-24
 */
public class ModStructures implements RegistryClass {

    public static final RegistryHelper<Structure> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registry.STRUCTURE_REGISTRY);

    public static final Map<String, ValhelsiaStructureSettings> STRUCTURE_SETTINGS_MAP = new HashMap<>();

    public static final Holder<? extends Structure> CASTLE = register("castle",
            () -> new ValhelsiaJigsawStructure(structure(ModTags.Biomes.HAS_CASTLE, TerrainAdjustment.BEARD_THIN), "castle", SimpleStructurePools.CASTLE_PATTERN, 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG),
            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.5D), ConfigurableValue.of(32)));
    public static final Holder<? extends Structure> CASTLE_RUIN = register("castle_ruin",
            () -> new ValhelsiaJigsawStructure(structure(ModTags.Biomes.HAS_CASTLE_RUIN, TerrainAdjustment.BEARD_THIN), "castle_ruin", SimpleStructurePools.CASTLE_RUIN_PATTERN, 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG),
            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.6D), ConfigurableValue.of(32)));
    public static final Holder<? extends Structure> DESERT_HOUSE = register("desert_house",
            () -> new ValhelsiaJigsawStructure(structure(ModTags.Biomes.HAS_DESERT_HOUSE, TerrainAdjustment.BEARD_THIN), "desert_house", DesertHousePools.PATTERN, 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG),
            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.7D), ConfigurableValue.of(20)));
    public static final Holder<? extends Structure> FORGE = register("forge",
            () -> new ValhelsiaJigsawStructure(structure(ModTags.Biomes.HAS_FORGE, TerrainAdjustment.BEARD_THIN), "forge", SimpleStructurePools.FORGE_PATTERN, 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG),
            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.7D), ConfigurableValue.of(20)));
    public static final Holder<? extends Structure> PLAYER_HOUSE = register("player_house",
            () -> new ValhelsiaJigsawStructure(structure(ModTags.Biomes.HAS_PLAYER_HOUSE, TerrainAdjustment.BEARD_THIN), "player_house", PlayerHousePools.PATTERN, 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG),
            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.7D), ConfigurableValue.of(18)));
    public static final Holder<? extends Structure> SPAWNER_DUNGEON = register("spawner_dungeon",
            () -> new ValhelsiaJigsawStructure(structure(ModTags.Biomes.HAS_SPAWNER_DUNGEON, GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE), "spawner_dungeon", SpawnerDungeonPools.PATTERN, 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG),
            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.7D), null, ConfigurableValue.of(0)));
    public static final Holder<? extends Structure> TOWER_RUIN = register("tower_ruin",
            () -> new ValhelsiaJigsawStructure(structure(ModTags.Biomes.HAS_TOWER_RUIN, TerrainAdjustment.BEARD_THIN), "tower_ruin", SimpleStructurePools.TOWER_RUIN_PATTERN, 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG),
            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.8D), ConfigurableValue.of(22)));
    public static final Holder<? extends Structure> WITCH_HUT = register("witch_hut",
            () -> new ValhelsiaJigsawStructure(structure(ModTags.Biomes.HAS_WITCH_HUT, TerrainAdjustment.BEARD_THIN), "witch_hut", SimpleStructurePools.WITCH_HUT_PATTERN, 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG),
            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.85D), ConfigurableValue.of(13), ConfigurableValue.of(3)));
    public static final Holder<? extends Structure> BIG_TREE = register("big_tree",
            () -> new ValhelsiaJigsawStructure(structure(ModTags.Biomes.HAS_BIG_TREE, TerrainAdjustment.BEARD_THIN), "big_tree", BigTreePools.PATTERN, 7, StructureHeightProvider.constant(VerticalAnchor.absolute(0)), Heightmap.Types.WORLD_SURFACE_WG),
            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.7D), ConfigurableValue.of(25)));
    public static final Holder<? extends Structure> SPAWNER_ROOM = register("spawner_room",
            () -> new ValhelsiaJigsawStructure(structure(ModTags.Biomes.HAS_SPAWNER_ROOM, GenerationStep.Decoration.UNDERGROUND_STRUCTURES, TerrainAdjustment.NONE), "spawner_room", SimpleStructurePools.SPAWNER_ROOM_PATTERN, 7, StructureHeightProvider.spawnerRoom(VerticalAnchor.absolute(0))),
            ValhelsiaStructureSettings.of(ConfigurableValue.of(0.9D), null, ConfigurableValue.of(0)));
    public static final Holder<? extends Structure> DEEP_SPAWNER_ROOM = register("deep_spawner_room",
            () -> new ValhelsiaJigsawStructure(structure(ModTags.Biomes.HAS_DEEP_SPAWNER_ROOM, GenerationStep.Decoration.UNDERGROUND_STRUCTURES, TerrainAdjustment.NONE), "deep_spawner_room", SimpleStructurePools.DEEP_SPAWNER_ROOM_PATTERN, 7, StructureHeightProvider.deepSpawnerRoom(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(-1))),
            ValhelsiaStructureSettings.of(ConfigurableValue.of(1.0D), null, ConfigurableValue.of(0)));

    public static Holder<? extends Structure> register(String name, Supplier<Structure> structure, ValhelsiaStructureSettings structureSettings) {
        RegistryObject<Structure> registryObject = HELPER.register(name, structure);

        STRUCTURE_SETTINGS_MAP.put(name, structureSettings);

        return registryObject.getHolder().get();
    }

    public static void setup() {
//        HELPER.getRegistryObjects().forEach(structureRegistryObject -> {
//            if (structureRegistryObject.get() instanceof ValhelsiaJigsawStructure structure) {
//                structure.setStructureSettings(STRUCTURE_SETTINGS_MAP.get(structureRegistryObject));
//                System.out.println(structureRegistryObject.getId());
//            }
//        });
    }

    private static Structure.StructureSettings structure(TagKey<Biome> tagKey, Map<MobCategory, StructureSpawnOverride> spawnOverrideMap, GenerationStep.Decoration decoration, TerrainAdjustment terrainAdjustment) {
        return new Structure.StructureSettings(biomes(tagKey), spawnOverrideMap, decoration, terrainAdjustment);
    }

    private static Structure.StructureSettings structure(TagKey<Biome> tagKey, GenerationStep.Decoration decoration, TerrainAdjustment terrainAdjustment) {
        return structure(tagKey, Map.of(), decoration, terrainAdjustment);
    }

    private static Structure.StructureSettings structure(TagKey<Biome> tagKey, TerrainAdjustment terrainAdjustment) {
        return structure(tagKey, Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, terrainAdjustment);
    }

    private static Holder<Structure> register(ResourceKey<Structure> resourceKey, Structure structure) {
        return BuiltinRegistries.register(BuiltinRegistries.STRUCTURES, resourceKey, structure);
    }

    private static HolderSet<Biome> biomes(TagKey<Biome> tagKey) {
        return BuiltinRegistries.BIOME.getOrCreateTag(tagKey);
    }
}
