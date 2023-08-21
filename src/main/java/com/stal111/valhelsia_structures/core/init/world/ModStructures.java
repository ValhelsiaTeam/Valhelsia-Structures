package com.stal111.valhelsia_structures.core.init.world;

import com.stal111.valhelsia_structures.common.world.structures.ValhelsiaJigsawStructure;
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProvider;
import com.stal111.valhelsia_structures.common.world.structures.pools.*;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.ModTags;
import com.stal111.valhelsia_structures.utils.StartPoolKeySet;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraftforge.registries.holdersets.AndHolderSet;
import net.valhelsia.valhelsia_core.api.common.registry.helper.datapack.DatapackRegistryClass;
import net.valhelsia.valhelsia_core.api.common.registry.helper.datapack.DatapackRegistryHelper;

import java.util.List;
import java.util.function.UnaryOperator;

/**
 * @author Valhelsia Team
 * @since 2022-06-24
 */
public class ModStructures extends DatapackRegistryClass<Structure> {

    public static final DatapackRegistryHelper<Structure> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.STRUCTURE);

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

    public ModStructures(BootstapContext<Structure> context) {
        super(context);
    }

    public void bootstrap(BootstapContext<Structure> context) {
        HolderGetter<Biome> biomeHolderGetter = context.lookup(Registries.BIOME);

        HolderSet<Biome> castleBiomes = this.singleTag(biomeHolderGetter, ModTags.Biomes.HAS_CASTLE);
        HolderSet<Biome> castleRuinBiomes = this.singleTag(biomeHolderGetter, ModTags.Biomes.HAS_CASTLE_RUIN);
        HolderSet<Biome> desertHouseBiomes = this.withConditionTag(biomeHolderGetter, ModTags.Biomes.HAS_DESERT_HOUSE, ModTags.Biomes.DESERT_HOUSE_CONDITION);
        HolderSet<Biome> forgeBiomes = this.singleTag(biomeHolderGetter, ModTags.Biomes.HAS_FORGE);
        HolderSet<Biome> playerHouseBiomes = this.singleTag(biomeHolderGetter, ModTags.Biomes.HAS_PLAYER_HOUSE);
        HolderSet<Biome> spawnerDungeonBiomes = this.singleTag(biomeHolderGetter, ModTags.Biomes.HAS_SPAWNER_DUNGEON);
        HolderSet<Biome> towerRuinBiomes = this.singleTag(biomeHolderGetter, ModTags.Biomes.HAS_TOWER_RUIN);
        HolderSet<Biome> witchHutBiomes = this.singleTag(biomeHolderGetter, ModTags.Biomes.HAS_WITCH_HUT);
        HolderSet<Biome> bigTreeBiomes = this.singleTag(biomeHolderGetter, ModTags.Biomes.HAS_BIG_TREE);
        HolderSet<Biome> spawnerRoomBiomes = this.singleTag(biomeHolderGetter, ModTags.Biomes.HAS_SPAWNER_ROOM);
        HolderSet<Biome> deepSpawnerRoomBiomes = this.singleTag(biomeHolderGetter, ModTags.Biomes.HAS_DEEP_SPAWNER_ROOM);

        this.surfaceStructure(context, CASTLE, castleBiomes, TerrainAdjustment.BEARD_THIN, SimpleStructurePools.CASTLES, builder -> builder.chance(0.5D));
        this.surfaceStructure(context, CASTLE_RUIN, castleRuinBiomes, TerrainAdjustment.BEARD_THIN, SimpleStructurePools.CASTLE_RUINS, builder -> builder.chance(0.6D));
        this.surfaceStructure(context, DESERT_HOUSE, desertHouseBiomes, TerrainAdjustment.BEARD_THIN, DesertHousePools.START, builder -> builder.chance(0.75D));
        this.surfaceStructure(context, FORGE, forgeBiomes, TerrainAdjustment.BEARD_THIN, SimpleStructurePools.FORGES, builder -> builder.chance(0.75D));
        this.surfaceStructure(context, PLAYER_HOUSE, playerHouseBiomes, TerrainAdjustment.BEARD_THIN, PlayerHousePools.START, builder -> builder.chance(0.75D));
        this.surfaceStructure(context, SPAWNER_DUNGEON, spawnerDungeonBiomes, TerrainAdjustment.NONE, SpawnerDungeonPools.START, builder -> builder.chance(0.8D).startHeight(StructureHeightProvider.surfaceBetween(VerticalAnchor.absolute(0), VerticalAnchor.absolute(75))).individualTerrainAdjustment());
        this.surfaceStructure(context, TOWER_RUIN, towerRuinBiomes, TerrainAdjustment.BEARD_THIN, SimpleStructurePools.TOWER_RUINS, builder -> builder.chance(0.8D));
        this.surfaceStructure(context, WITCH_HUT, witchHutBiomes, TerrainAdjustment.BEARD_THIN, SimpleStructurePools.WITCH_HUTS, builder -> builder.chance(0.85D).margin(3)
                .addSpawnOverride(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(EntityType.WITCH, 1, 1, 1))))
                .addSpawnOverride(MobCategory.CREATURE, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(EntityType.CAT, 1, 1, 1))))
        );
        this.surfaceStructure(context, BIG_TREE, bigTreeBiomes, TerrainAdjustment.BEARD_THIN, BigTreePools.START, builder -> builder.chance(0.7D));

        this.undergroundStructure(context, SPAWNER_ROOM, spawnerRoomBiomes, TerrainAdjustment.NONE, SimpleStructurePools.SPAWNER_ROOMS, builder -> builder.chance(0.9D).startHeight(StructureHeightProvider.spawnerRoom(VerticalAnchor.absolute(0))));
        this.undergroundStructure(context, DEEP_SPAWNER_ROOM, deepSpawnerRoomBiomes, TerrainAdjustment.NONE, SimpleStructurePools.DEEP_SPAWNER_ROOMS, builder -> builder.startHeight(StructureHeightProvider.deepSpawnerRoom(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(-1))));
    }

    protected void surfaceStructure(BootstapContext<Structure> context, ResourceKey<Structure> key, HolderSet<Biome> biomeHolderSet, TerrainAdjustment terrainAdjustment, StartPoolKeySet startPool, UnaryOperator<ValhelsiaJigsawStructure.Builder> builderUnaryOperator) {
        context.register(key, builderUnaryOperator.apply(ValhelsiaJigsawStructure.builder(context, biomeHolderSet, GenerationStep.Decoration.TOP_LAYER_MODIFICATION, terrainAdjustment, startPool)).build());
    }

    protected void undergroundStructure(BootstapContext<Structure> context, ResourceKey<Structure> key, HolderSet<Biome> biomeHolderSet, TerrainAdjustment terrainAdjustment, StartPoolKeySet startPool, UnaryOperator<ValhelsiaJigsawStructure.Builder> builderUnaryOperator) {
        context.register(key, builderUnaryOperator.apply(ValhelsiaJigsawStructure.builder(context, biomeHolderSet, GenerationStep.Decoration.UNDERGROUND_STRUCTURES, terrainAdjustment, startPool)).build());
    }

    private HolderSet.Named<Biome> singleTag(HolderGetter<Biome> biomeHolderGetter, TagKey<Biome> tagKey) {
        return biomeHolderGetter.getOrThrow(tagKey);
    }

    private AndHolderSet<Biome> withConditionTag(HolderGetter<Biome> biomeHolderGetter, TagKey<Biome> tagKey, TagKey<Biome> conditionTagKey) {
        return new AndHolderSet<>(List.of(biomeHolderGetter.getOrThrow(tagKey), biomeHolderGetter.getOrThrow(conditionTagKey)));
    }
}
