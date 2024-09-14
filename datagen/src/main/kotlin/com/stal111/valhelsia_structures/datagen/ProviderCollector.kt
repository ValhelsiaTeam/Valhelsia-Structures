package com.stal111.valhelsia_structures.datagen

import com.stal111.valhelsia_structures.core.ValhelsiaStructures
import com.stal111.valhelsia_structures.datagen.model.ModBlockModels
import com.stal111.valhelsia_structures.datagen.tags.ModBiomeTagsProvider
import com.stal111.valhelsia_structures.datagen.tags.ModBlockTagsProvider
import com.stal111.valhelsia_structures.datagen.tags.ModItemTagsProvider
import net.valhelsia.dataforge.DataCollector
import net.valhelsia.dataforge.DataProviderContext
import net.valhelsia.dataforge.DataTarget
import net.valhelsia.dataforge.model.DataForgeModelProvider

class ProviderCollector : DataCollector() {
    override fun collectData(context: DataProviderContext) {
        val blocks = ValhelsiaStructures.REGISTRY_MANAGER.blockHelper.registryEntries.map { { it.value() } }

        with(DataTarget.CLIENT) {
            addProvider(this, ModSoundsProvider(context))
            addProvider(this, DataForgeModelProvider(context, blocks, { ModBlockModels(it) }, null))
        }

        with(DataTarget.SERVER) {
            addProvider(this, ModBlockTagsProvider(context))
            addProvider(this, ModItemTagsProvider(context))
            addProvider(this, ModBiomeTagsProvider(context))
          //  addProvider(this, ModStructureTagsProvider(context))
          //  addProvider(this, ValhelsiaRecipeProvider(context, { ModRecipeProvider(it) }))
        }
    }
}
