package com.stal111.valhelsia_structures.datagen.worldgen.structure;

import com.stal111.valhelsia_structures.common.builtin.BuiltInStructurePools;
import com.stal111.valhelsia_structures.common.builtin.BuiltInStructures;
import com.stal111.valhelsia_structures.common.world.structures.ValhelsiaJigsawStructure;
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProvider;
import com.stal111.valhelsia_structures.utils.ModTags;
import com.stal111.valhelsia_structures.utils.StartPoolKeySet;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
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
import net.neoforged.neoforge.registries.holdersets.AndHolderSet;
import net.valhelsia.valhelsia_core.api.common.registry.helper.datapack.DatapackRegistryClass;

import java.util.List;
import java.util.function.UnaryOperator;

/**
 * @author Valhelsia Team
 * @since 2022-06-24
 */
public class ModStructures extends DatapackRegistryClass<Structure> {

    public ModStructures(BootstrapContext<Structure> context) {
        super(context);
    }

    public void bootstrap(BootstrapContext<Structure> context) {
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

        this.surfaceStructure(context, BuiltInStructures.CASTLE, castleBiomes, TerrainAdjustment.BEARD_THIN, BuiltInStructurePools.CASTLES, builder -> builder.chance(0.4D));
        this.surfaceStructure(context, BuiltInStructures.CASTLE_RUIN, castleRuinBiomes, TerrainAdjustment.BEARD_THIN, BuiltInStructurePools.CASTLE_RUINS, builder -> builder.chance(0.5D));
        this.surfaceStructure(context, BuiltInStructures.DESERT_HOUSE, desertHouseBiomes, TerrainAdjustment.BEARD_THIN, BuiltInStructurePools.DESERT_HOUSES, builder -> builder.chance(0.7D));
        this.surfaceStructure(context, BuiltInStructures.FORGE, forgeBiomes, TerrainAdjustment.BEARD_THIN, BuiltInStructurePools.FORGES, builder -> builder.chance(0.65D));
        this.surfaceStructure(context, BuiltInStructures.PLAYER_HOUSE, playerHouseBiomes, TerrainAdjustment.BEARD_THIN, BuiltInStructurePools.PLAYER_HOUSES, builder -> builder.chance(0.65D));
        this.surfaceStructure(context, BuiltInStructures.SPAWNER_DUNGEON, spawnerDungeonBiomes, TerrainAdjustment.NONE, BuiltInStructurePools.SPAWNER_DUNGEONS, builder -> builder.chance(0.7D).startHeight(StructureHeightProvider.surfaceBetween(VerticalAnchor.absolute(0), VerticalAnchor.absolute(75))).individualTerrainAdjustment().ignoreWaterLogging());
        this.surfaceStructure(context, BuiltInStructures.TOWER_RUIN, towerRuinBiomes, TerrainAdjustment.BEARD_THIN, BuiltInStructurePools.TOWER_RUINS, builder -> builder.chance(0.7D));
        this.surfaceStructure(context, BuiltInStructures.WITCH_HUT, witchHutBiomes, TerrainAdjustment.BEARD_THIN, BuiltInStructurePools.WITCH_HUTS, builder -> builder.chance(0.85D).margin(3)
                .addSpawnOverride(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(EntityType.WITCH, 1, 1, 1))))
                .addSpawnOverride(MobCategory.CREATURE, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(EntityType.CAT, 1, 1, 1))))
        );
        this.surfaceStructure(context, BuiltInStructures.BIG_TREE, bigTreeBiomes, TerrainAdjustment.BEARD_THIN, BuiltInStructurePools.BIG_TREES, builder -> builder.chance(0.6D));

        this.undergroundStructure(context, BuiltInStructures.SPAWNER_ROOM, spawnerRoomBiomes, TerrainAdjustment.NONE, BuiltInStructurePools.SPAWNER_ROOMS, builder -> builder.chance(0.9D).startHeight(StructureHeightProvider.spawnerRoom(VerticalAnchor.absolute(0))).ignoreWaterLogging());
        this.undergroundStructure(context, BuiltInStructures.DEEP_SPAWNER_ROOM, deepSpawnerRoomBiomes, TerrainAdjustment.NONE, BuiltInStructurePools.DEEP_SPAWNER_ROOMS, builder -> builder.startHeight(StructureHeightProvider.deepSpawnerRoom(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(-1))).ignoreWaterLogging());
    }

    protected void surfaceStructure(BootstrapContext<Structure> context, ResourceKey<Structure> key, HolderSet<Biome> biomeHolderSet, TerrainAdjustment terrainAdjustment, StartPoolKeySet startPool, UnaryOperator<ValhelsiaJigsawStructure.Builder> builderUnaryOperator) {
        context.register(key, builderUnaryOperator.apply(ValhelsiaJigsawStructure.builder(context, biomeHolderSet, GenerationStep.Decoration.TOP_LAYER_MODIFICATION, terrainAdjustment, startPool)).build());
    }

    protected void undergroundStructure(BootstrapContext<Structure> context, ResourceKey<Structure> key, HolderSet<Biome> biomeHolderSet, TerrainAdjustment terrainAdjustment, StartPoolKeySet startPool, UnaryOperator<ValhelsiaJigsawStructure.Builder> builderUnaryOperator) {
        context.register(key, builderUnaryOperator.apply(ValhelsiaJigsawStructure.builder(context, biomeHolderSet, GenerationStep.Decoration.UNDERGROUND_STRUCTURES, terrainAdjustment, startPool)).build());
    }

    private HolderSet.Named<Biome> singleTag(HolderGetter<Biome> biomeHolderGetter, TagKey<Biome> tagKey) {
        return biomeHolderGetter.getOrThrow(tagKey);
    }

    private AndHolderSet<Biome> withConditionTag(HolderGetter<Biome> biomeHolderGetter, TagKey<Biome> tagKey, TagKey<Biome> conditionTagKey) {
        return new AndHolderSet<>(List.of(biomeHolderGetter.getOrThrow(tagKey), biomeHolderGetter.getOrThrow(conditionTagKey)));
    }
}
