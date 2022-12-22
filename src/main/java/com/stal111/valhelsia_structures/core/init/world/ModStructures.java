package com.stal111.valhelsia_structures.core.init.world;

import com.stal111.valhelsia_structures.common.world.structures.ValhelsiaJigsawStructure;
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProvider;
import com.stal111.valhelsia_structures.common.world.structures.pools.*;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.valhelsia.valhelsia_core.core.data.DataProviderInfo;
import net.valhelsia.valhelsia_core.core.registry.helper.DatapackRegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.DatapackRegistryHelper;

import java.util.function.UnaryOperator;

/**
 * @author Valhelsia Team
 * @since 2022-06-24
 */
public class ModStructures extends DatapackRegistryClass<Structure> {

    public static final DatapackRegistryHelper<Structure> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getDatapackHelper(Registries.STRUCTURE);

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
        this.surfaceStructure(context, CASTLE, ModTags.Biomes.HAS_CASTLE, TerrainAdjustment.BEARD_THIN, SimpleStructurePools.CASTLES, builder -> builder.chance(0.5D));
        this.surfaceStructure(context, CASTLE_RUIN, ModTags.Biomes.HAS_CASTLE_RUIN, TerrainAdjustment.BEARD_THIN, SimpleStructurePools.CASTLE_RUINS, builder -> builder.chance(0.6D));
        this.surfaceStructure(context, DESERT_HOUSE, ModTags.Biomes.HAS_DESERT_HOUSE, TerrainAdjustment.BEARD_THIN, DesertHousePools.START, builder -> builder.chance(0.75D));
        this.surfaceStructure(context, FORGE, ModTags.Biomes.HAS_FORGE, TerrainAdjustment.BEARD_THIN, SimpleStructurePools.FORGES, builder -> builder.chance(0.75D));
        this.surfaceStructure(context, PLAYER_HOUSE, ModTags.Biomes.HAS_PLAYER_HOUSE, TerrainAdjustment.BEARD_THIN, PlayerHousePools.START, builder -> builder.chance(0.75D));
        this.surfaceStructure(context, SPAWNER_DUNGEON, ModTags.Biomes.HAS_SPAWNER_DUNGEON, TerrainAdjustment.NONE, SpawnerDungeonPools.START, builder -> builder.chance(0.8D).startHeight(StructureHeightProvider.surfaceBetween(VerticalAnchor.absolute(0), VerticalAnchor.absolute(75))).individualTerrainAdjustment());
        this.surfaceStructure(context, TOWER_RUIN, ModTags.Biomes.HAS_TOWER_RUIN, TerrainAdjustment.BEARD_THIN, SimpleStructurePools.TOWER_RUINS, builder -> builder.chance(0.8D));
        this.surfaceStructure(context, WITCH_HUT, ModTags.Biomes.HAS_WITCH_HUT, TerrainAdjustment.BEARD_THIN, SimpleStructurePools.WITCH_HUTS, builder -> builder.chance(0.85D).margin(3));
        this.surfaceStructure(context, BIG_TREE, ModTags.Biomes.HAS_BIG_TREE, TerrainAdjustment.BEARD_THIN, BigTreePools.START, builder -> builder.chance(0.7D));

        this.undergroundStructure(context, SPAWNER_ROOM, ModTags.Biomes.HAS_SPAWNER_ROOM, TerrainAdjustment.NONE, SimpleStructurePools.SPAWNER_ROOMS, builder -> builder.chance(0.9D).startHeight(StructureHeightProvider.spawnerRoom(VerticalAnchor.absolute(0))));
        this.undergroundStructure(context, DEEP_SPAWNER_ROOM, ModTags.Biomes.HAS_DEEP_SPAWNER_ROOM, TerrainAdjustment.NONE, SimpleStructurePools.DEEP_SPAWNER_ROOMS, builder -> builder.startHeight(StructureHeightProvider.deepSpawnerRoom(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(-1))));
    }

    protected void surfaceStructure(BootstapContext<Structure> context, ResourceKey<Structure> key, TagKey<Biome> biomeTagKey, TerrainAdjustment terrainAdjustment, ResourceKey<StructureTemplatePool> startPool, UnaryOperator<ValhelsiaJigsawStructure.Builder> builderUnaryOperator) {
        context.register(key, builderUnaryOperator.apply(ValhelsiaJigsawStructure.builder(context, biomeTagKey, GenerationStep.Decoration.SURFACE_STRUCTURES, terrainAdjustment, startPool)).build());
    }

    protected void undergroundStructure(BootstapContext<Structure> context, ResourceKey<Structure> key, TagKey<Biome> biomeTagKey, TerrainAdjustment terrainAdjustment, ResourceKey<StructureTemplatePool> startPool, UnaryOperator<ValhelsiaJigsawStructure.Builder> builderUnaryOperator) {
        context.register(key, builderUnaryOperator.apply(ValhelsiaJigsawStructure.builder(context, biomeTagKey, GenerationStep.Decoration.UNDERGROUND_STRUCTURES, terrainAdjustment, startPool)).build());
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

}
