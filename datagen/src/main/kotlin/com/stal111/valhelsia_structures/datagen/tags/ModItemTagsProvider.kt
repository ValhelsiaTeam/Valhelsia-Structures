package com.stal111.valhelsia_structures.datagen.tags

import com.stal111.valhelsia_structures.core.init.ModBlocks
import com.stal111.valhelsia_structures.utils.ModTags
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import net.neoforged.neoforge.common.Tags
import net.valhelsia.dataforge.DataProviderContext
import net.valhelsia.dataforge.tag.DataForgeItemTagsProvider

class ModItemTagsProvider(context: DataProviderContext) : DataForgeItemTagsProvider(context) {
    override fun addTags(provider: HolderLookup.Provider) {
        this.copy(ModTags.Blocks.POSTS, ModTags.Items.POSTS)
        this.copy(ModTags.Blocks.CUT_POSTS, ModTags.Items.CUT_POSTS)
        this.copy(ModTags.Blocks.NON_FLAMMABLE_POSTS, ModTags.Items.NON_FLAMMABLE_POSTS)
        this.copy(Tags.Blocks.GLASS_BLOCKS_COLORLESS, Tags.Items.GLASS_BLOCKS_COLORLESS)
        this.copy(Tags.Blocks.GLASS_PANES, Tags.Items.GLASS_PANES)
        this.copy(Tags.Blocks.GLASS_PANES_COLORLESS, Tags.Items.GLASS_PANES_COLORLESS)
        this.copy(ModTags.Blocks.COLORED_JARS, ModTags.Items.COLORED_JARS)
        this.copy(ModTags.Blocks.JARS, ModTags.Items.JARS)
        this.copy(ModTags.Blocks.SLEEPING_BAGS, ModTags.Items.SLEEPING_BAGS)

        this.copy(BlockTags.PIGLIN_REPELLENTS, ItemTags.PIGLIN_REPELLENTS)

        tag(ModTags.Items.JAR_BLACKLISTED).add(Items.CACTUS, Items.CRIMSON_ROOTS, Items.WARPED_ROOTS)
        tag(ItemTags.DYEABLE).add(ModBlocks.EXPLORERS_TENT.get().asItem())
    }
}
