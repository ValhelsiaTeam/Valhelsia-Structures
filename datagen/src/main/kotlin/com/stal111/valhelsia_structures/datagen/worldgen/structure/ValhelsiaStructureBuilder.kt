package com.stal111.valhelsia_structures.datagen.worldgen.structure

import com.stal111.valhelsia_structures.common.world.structures.StartPoolDecider
import com.stal111.valhelsia_structures.common.world.structures.ValhelsiaJigsawStructure
import com.stal111.valhelsia_structures.common.world.structures.ValhelsiaStructureSettings
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProvider
import com.stal111.valhelsia_structures.utils.StartPoolKeySet
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings

@DslMarker
annotation class ValhelsiaJigsawStructureDsl

@ValhelsiaJigsawStructureDsl
class ValhelsiaStructureBuilder(
    private val context: BootstrapContext<Structure>,
    private val biomeHolderSet: HolderSet<Biome>,
    private val step: GenerationStep.Decoration,
    private val terrainAdjustment: TerrainAdjustment,
    private val startPool: StartPoolKeySet
) {
    private val spawnOverrides = mutableMapOf<MobCategory, StructureSpawnOverride>()
    private var maxDepth: Int = 7
    private var projectStartToHeightmap: Heightmap.Types? = Heightmap.Types.WORLD_SURFACE_WG
    private var heightProvider: StructureHeightProvider? = null
    private var maxDistanceFromCenter: Int = 80
    private val structureSettings = ValhelsiaStructureSettings.builder()

    fun addSpawnOverride(category: MobCategory, override: StructureSpawnOverride) = apply {
        spawnOverrides[category] = override
    }

    fun maxDepth(maxDepth: Int) = apply {
        this.maxDepth = maxDepth
    }

    fun startHeight(heightProvider: StructureHeightProvider) = apply {
        this.projectStartToHeightmap = null
        this.heightProvider = heightProvider
    }

    fun heightmap(projectStartToHeightmap: Heightmap.Types) = apply {
        this.projectStartToHeightmap = projectStartToHeightmap
        this.heightProvider = null
    }

    fun individualTerrainAdjustment() = apply {
        structureSettings.enableIndividualTerrainAdjustment(true)
    }

    fun chance(spawnChance: Double) = apply {
        structureSettings.setSpawnChance(spawnChance)
    }

    fun margin(customMargin: Int) = apply {
        structureSettings.setCustomMargin(customMargin)
    }

    fun ignoreWaterLogging() = apply {
        structureSettings.setLiquidSettings(LiquidSettings.IGNORE_WATERLOGGING)
    }

    fun build(): ValhelsiaJigsawStructure {
        val settings = Structure.StructureSettings(biomeHolderSet, spawnOverrides, step, terrainAdjustment)
        return ValhelsiaJigsawStructure(
            settings,
            structureSettings.build(),
            StartPoolDecider.of(context.lookup(Registries.TEMPLATE_POOL), startPool),
            maxDepth,
            heightProvider,
            projectStartToHeightmap,
            maxDistanceFromCenter
        )
    }
}