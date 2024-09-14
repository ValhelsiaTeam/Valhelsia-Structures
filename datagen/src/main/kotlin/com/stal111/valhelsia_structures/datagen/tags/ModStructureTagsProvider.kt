package com.stal111.valhelsia_structures.datagen.tags

import com.stal111.valhelsia_structures.common.builtin.BuiltInStructures
import com.stal111.valhelsia_structures.utils.ModTags
import net.minecraft.core.HolderLookup
import net.valhelsia.dataforge.DataProviderContext
import net.valhelsia.dataforge.tag.DataForgeStructureTagsProvider

class ModStructureTagsProvider(context: DataProviderContext) : DataForgeStructureTagsProvider(context) {
    override fun addTags(provider: HolderLookup.Provider) {
        this.tag(ModTags.Structures.ON_SPAWNER_DUNGEON_EXPLORER_MAPS).add(BuiltInStructures.SPAWNER_DUNGEON)
        this.tag(ModTags.Structures.ON_CASTLE_EXPLORER_MAPS).add(BuiltInStructures.CASTLE)
    }
}
