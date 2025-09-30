package com.stal111.valhelsia_structures.datagen.worldgen.structure

import com.stal111.valhelsia_structures.common.builtin.BuiltInStructurePools
import com.stal111.valhelsia_structures.common.builtin.BuiltInStructures
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProvider
import com.stal111.valhelsia_structures.utils.ModTags
import com.stal111.valhelsia_structures.utils.StartPoolKeySet
import net.minecraft.core.HolderGetter
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.util.random.WeightedList
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment
import net.neoforged.neoforge.registries.holdersets.AndHolderSet
import net.valhelsia.dataforge.RegistryDataProvider

object ModStructures : RegistryDataProvider<Structure> {
    override fun bootstrap(context: BootstrapContext<Structure>): Unit = context.run {
        val biomeHolderGetter = lookup(Registries.BIOME)

        val castleBiomes = singleTag(biomeHolderGetter, ModTags.Biomes.HAS_CASTLE)
        val castleRuinBiomes = singleTag(biomeHolderGetter, ModTags.Biomes.HAS_CASTLE_RUIN)
        val desertHouseBiomes = withConditionTag(
            biomeHolderGetter,
            ModTags.Biomes.HAS_DESERT_HOUSE,
            ModTags.Biomes.DESERT_HOUSE_CONDITION
        )
        val forgeBiomes = singleTag(biomeHolderGetter, ModTags.Biomes.HAS_FORGE)
        val playerHouseBiomes = singleTag(biomeHolderGetter, ModTags.Biomes.HAS_PLAYER_HOUSE)
        val spawnerDungeonBiomes = singleTag(biomeHolderGetter, ModTags.Biomes.HAS_SPAWNER_DUNGEON)
        val towerRuinBiomes = singleTag(biomeHolderGetter, ModTags.Biomes.HAS_TOWER_RUIN)
        val witchHutBiomes = singleTag(biomeHolderGetter, ModTags.Biomes.HAS_WITCH_HUT)
        val bigTreeBiomes = singleTag(biomeHolderGetter, ModTags.Biomes.HAS_BIG_TREE)
        val spawnerRoomBiomes = singleTag(biomeHolderGetter, ModTags.Biomes.HAS_SPAWNER_ROOM)
        val deepSpawnerRoomBiomes = singleTag(biomeHolderGetter, ModTags.Biomes.HAS_DEEP_SPAWNER_ROOM)

        surfaceStructure(
            BuiltInStructures.CASTLE,
            castleBiomes,
            TerrainAdjustment.BEARD_THIN,
            BuiltInStructurePools.CASTLES
        ) { chance(0.4) }
        surfaceStructure(
            BuiltInStructures.CASTLE_RUIN,
            castleRuinBiomes,
            TerrainAdjustment.BEARD_THIN,
            BuiltInStructurePools.CASTLE_RUINS
        ) { chance(0.5) }
        surfaceStructure(
            BuiltInStructures.DESERT_HOUSE,
            desertHouseBiomes,
            TerrainAdjustment.BEARD_THIN,
            BuiltInStructurePools.DESERT_HOUSES
        ) { chance(0.7) }
        surfaceStructure(
            BuiltInStructures.FORGE,
            forgeBiomes,
            TerrainAdjustment.BEARD_THIN,
            BuiltInStructurePools.FORGES
        ) { chance(0.65) }
        surfaceStructure(
            BuiltInStructures.PLAYER_HOUSE,
            playerHouseBiomes,
            TerrainAdjustment.BEARD_THIN,
            BuiltInStructurePools.PLAYER_HOUSES
        ) { chance(0.65) }
        surfaceStructure(
            BuiltInStructures.SPAWNER_DUNGEON,
            spawnerDungeonBiomes,
            TerrainAdjustment.NONE,
            BuiltInStructurePools.SPAWNER_DUNGEONS
        ) {
            chance(0.7)
            startHeight(StructureHeightProvider.surfaceBetween(VerticalAnchor.absolute(0), VerticalAnchor.absolute(75)))
            individualTerrainAdjustment()
            ignoreWaterLogging()
        }
        surfaceStructure(
            BuiltInStructures.TOWER_RUIN,
            towerRuinBiomes,
            TerrainAdjustment.BEARD_THIN,
            BuiltInStructurePools.TOWER_RUINS
        ) { chance(0.7) }
        surfaceStructure(
            BuiltInStructures.WITCH_HUT,
            witchHutBiomes,
            TerrainAdjustment.BEARD_THIN,
            BuiltInStructurePools.WITCH_HUTS
        ) {
            chance(0.85)
            margin(3)
            addSpawnOverride(
                MobCategory.MONSTER,
                StructureSpawnOverride(
                    StructureSpawnOverride.BoundingBoxType.PIECE,
                    WeightedList.of(MobSpawnSettings.SpawnerData(EntityType.WITCH, 1, 1))
                )
            )
            addSpawnOverride(
                MobCategory.CREATURE,
                StructureSpawnOverride(
                    StructureSpawnOverride.BoundingBoxType.PIECE,
                    WeightedList.of(MobSpawnSettings.SpawnerData(EntityType.CAT, 1, 1))
                )
            )
        }
        surfaceStructure(
            BuiltInStructures.BIG_TREE,
            bigTreeBiomes,
            TerrainAdjustment.BEARD_THIN,
            BuiltInStructurePools.BIG_TREES
        ) { chance(0.6) }

        undergroundStructure(
            BuiltInStructures.SPAWNER_ROOM,
            spawnerRoomBiomes,
            TerrainAdjustment.NONE,
            BuiltInStructurePools.SPAWNER_ROOMS
        ) {
            chance(0.9)
            startHeight(StructureHeightProvider.spawnerRoom(VerticalAnchor.absolute(0)))
            ignoreWaterLogging()
        }
        undergroundStructure(
            BuiltInStructures.DEEP_SPAWNER_ROOM,
            deepSpawnerRoomBiomes,
            TerrainAdjustment.NONE,
            BuiltInStructurePools.DEEP_SPAWNER_ROOMS
        ) {
            startHeight(
                StructureHeightProvider.deepSpawnerRoom(
                    VerticalAnchor.aboveBottom(6),
                    VerticalAnchor.absolute(-1)
                )
            )
            ignoreWaterLogging()
        }
    }

    private fun BootstrapContext<Structure>.structure(
        key: ResourceKey<Structure>,
        biomeHolderSet: HolderSet<Biome>,
        step: GenerationStep.Decoration,
        terrainAdjustment: TerrainAdjustment,
        startPool: StartPoolKeySet,
        init: ValhelsiaStructureBuilder.() -> Unit
    ) {
        this.register(
            key,
            ValhelsiaStructureBuilder(
                this,
                biomeHolderSet,
                step,
                terrainAdjustment,
                startPool
            ).apply(init).build()
        )
    }

    private fun BootstrapContext<Structure>.surfaceStructure(
        key: ResourceKey<Structure>,
        biomeHolderSet: HolderSet<Biome>,
        terrainAdjustment: TerrainAdjustment,
        startPool: StartPoolKeySet,
        init: ValhelsiaStructureBuilder.() -> Unit
    ) = structure(key, biomeHolderSet, GenerationStep.Decoration.FLUID_SPRINGS, terrainAdjustment, startPool, init)

    private fun BootstrapContext<Structure>.undergroundStructure(
        key: ResourceKey<Structure>,
        biomeHolderSet: HolderSet<Biome>,
        terrainAdjustment: TerrainAdjustment,
        startPool: StartPoolKeySet,
        init: ValhelsiaStructureBuilder.() -> Unit
    ) = structure(
        key,
        biomeHolderSet,
        GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
        terrainAdjustment,
        startPool,
        init
    )

    private fun singleTag(biomeHolderGetter: HolderGetter<Biome>, tagKey: TagKey<Biome>) =
        biomeHolderGetter.getOrThrow(tagKey)

    private fun withConditionTag(
        biomeHolderGetter: HolderGetter<Biome>,
        tagKey: TagKey<Biome>,
        conditionTagKey: TagKey<Biome>
    ) = AndHolderSet(listOf(biomeHolderGetter.getOrThrow(tagKey), biomeHolderGetter.getOrThrow(conditionTagKey)))
}
