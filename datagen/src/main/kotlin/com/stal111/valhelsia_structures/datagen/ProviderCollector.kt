package com.stal111.valhelsia_structures.datagen

import com.stal111.valhelsia_structures.core.ValhelsiaStructures
import com.stal111.valhelsia_structures.datagen.model.ModBlockModels
import com.stal111.valhelsia_structures.datagen.recipes.ModRecipeProvider
import com.stal111.valhelsia_structures.datagen.tags.ModBiomeTagsProvider
import com.stal111.valhelsia_structures.datagen.tags.ModBlockTagsProvider
import com.stal111.valhelsia_structures.datagen.tags.ModItemTagsProvider
import com.stal111.valhelsia_structures.datagen.tags.ModStructureTagsProvider
import com.stal111.valhelsia_structures.datagen.worldgen.processors.ModProcessorLists
import com.stal111.valhelsia_structures.datagen.worldgen.structure.ModStructureSets
import com.stal111.valhelsia_structures.datagen.worldgen.structure.ModStructures
import com.stal111.valhelsia_structures.datagen.worldgen.structure.pools.BigTreePools
import com.stal111.valhelsia_structures.datagen.worldgen.structure.pools.DesertHousePools
import com.stal111.valhelsia_structures.datagen.worldgen.structure.pools.MobPools
import com.stal111.valhelsia_structures.datagen.worldgen.structure.pools.PlayerHousePools
import com.stal111.valhelsia_structures.datagen.worldgen.structure.pools.SimpleStructurePools
import com.stal111.valhelsia_structures.datagen.worldgen.structure.pools.SpawnerDungeonPools
import net.minecraft.core.registries.Registries
import net.valhelsia.dataforge.DataCollector
import net.valhelsia.dataforge.DataProviderContext
import net.valhelsia.dataforge.DataTarget
import net.valhelsia.dataforge.model.DataForgeModelProvider
import net.valhelsia.dataforge.recipe.DataForgeRecipeProvider

class ProviderCollector : DataCollector() {
    override fun collectProviders(context: DataProviderContext) {
        val blocks = ValhelsiaStructures.REGISTRY_MANAGER.blockHelper.registryEntries.map { { it.value() } }

        with(DataTarget.CLIENT) {
            addProvider(this, ModSoundsProvider(context))
            addProvider(this, DataForgeModelProvider(context, blocks, { ModBlockModels(it) }, null))
        }

        with(DataTarget.SERVER) {
            addProvider(this, ModBlockTagsProvider(context))
            addProvider(this, ModItemTagsProvider(context))
            addProvider(this, ModBiomeTagsProvider(context))
            addProvider(this, ModStructureTagsProvider(context))
            addProvider(this, DataForgeRecipeProvider(context, ModRecipeProvider()))
        }
    }

    override fun collectRegistryProviders() {
        addRegistryProvider(Registries.PROCESSOR_LIST, ModProcessorLists)
        addRegistryProvider(Registries.STRUCTURE, ModStructures)
        addRegistryProvider(Registries.STRUCTURE_SET, ModStructureSets)
        addRegistryProvider(
            Registries.TEMPLATE_POOL,
            BigTreePools, DesertHousePools, MobPools, PlayerHousePools, SimpleStructurePools, SpawnerDungeonPools
        )
    }
}
