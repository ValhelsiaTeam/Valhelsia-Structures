package com.stal111.valhelsia_structures.datagen

import com.stal111.valhelsia_structures.core.ValhelsiaStructures
import com.stal111.valhelsia_structures.datagen.loot.ModBlockLoot
import com.stal111.valhelsia_structures.datagen.model.ModBlockModels
import com.stal111.valhelsia_structures.datagen.recipes.ModRecipeProvider
import com.stal111.valhelsia_structures.datagen.tags.ModBiomeTagsProvider
import com.stal111.valhelsia_structures.datagen.tags.ModBlockTagsProvider
import com.stal111.valhelsia_structures.datagen.tags.ModItemTagsProvider
import com.stal111.valhelsia_structures.datagen.tags.ModStructureTagsProvider
import com.stal111.valhelsia_structures.datagen.worldgen.modifier.ModBiomeModifiers
import com.stal111.valhelsia_structures.datagen.worldgen.processors.ModProcessorLists
import com.stal111.valhelsia_structures.datagen.worldgen.structure.ModStructureSets
import com.stal111.valhelsia_structures.datagen.worldgen.structure.ModStructures
import com.stal111.valhelsia_structures.datagen.worldgen.structure.pools.*
import net.minecraft.core.registries.Registries
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.neoforged.neoforge.registries.NeoForgeRegistries
import net.valhelsia.dataforge.DataCollector
import net.valhelsia.dataforge.DataProviderContext
import net.valhelsia.dataforge.model.DataForgeModelProvider
import net.valhelsia.dataforge.recipe.DataForgeRecipeRunner

class ProviderCollector : DataCollector() {
    override fun collectClientProviders(context: DataProviderContext.Client) {
        addClientProvider(ModSoundsProvider(context))
        addClientProvider(DataForgeModelProvider(context, { ModBlockModels(it) }, null))
    }

    override fun collectServerProviders(context: DataProviderContext.Server) {
        val blocks = ValhelsiaStructures.REGISTRY_MANAGER.blockHelper.registryEntries.map { { it.value() } }

        addServerProvider(ModBlockTagsProvider(context))
        addServerProvider(ModItemTagsProvider(context))
        addServerProvider(ModBiomeTagsProvider(context))
        addServerProvider(ModStructureTagsProvider(context))
        addServerProvider(DataForgeRecipeRunner(context, ::ModRecipeProvider))
        addServerProvider(
            LootTableProvider(
                context.packOutput, setOf<ResourceKey<LootTable>>(), listOf(
                    LootTableProvider.SubProviderEntry({ ModBlockLoot(it, blocks) }, LootContextParamSets.BLOCK)
                ),
                context.lookupProvider
            )
        )
    }

    override fun collectRegistryProviders() {
        addRegistryProvider(Registries.PROCESSOR_LIST, ModProcessorLists)
        addRegistryProvider(Registries.STRUCTURE, ModStructures)
        addRegistryProvider(Registries.STRUCTURE_SET, ModStructureSets)
        addRegistryProvider(
            Registries.TEMPLATE_POOL,
            BigTreePools, DesertHousePools, MobPools, PlayerHousePools, SimpleStructurePools, SpawnerDungeonPools
        )
        addRegistryProvider(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers)
    }
}
