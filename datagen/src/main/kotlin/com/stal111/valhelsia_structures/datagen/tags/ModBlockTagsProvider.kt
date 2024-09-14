package com.stal111.valhelsia_structures.datagen.tags

import com.stal111.valhelsia_structures.core.init.ModBlocks
import com.stal111.valhelsia_structures.utils.ModTags
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BlockTags
import net.neoforged.neoforge.common.Tags
import net.valhelsia.dataforge.DataProviderContext
import net.valhelsia.dataforge.tag.DataForgeBlockTagsProvider

class ModBlockTagsProvider(context: DataProviderContext) : DataForgeBlockTagsProvider(context) {
    override fun addTags(provider: HolderLookup.Provider) {
        tag(ModTags.Blocks.BRAZIERS).add(ModBlocks.BRAZIER.get(), ModBlocks.SOUL_BRAZIER.get())

        ModBlocks.WOODEN_POSTS.values.forEach { tag(ModTags.Blocks.POSTS).add(it.get()) }
        ModBlocks.STRIPPED_WOODEN_POSTS.values.forEach { tag(ModTags.Blocks.POSTS).add(it.get()) }
        ModBlocks.CUT_WOODEN_POSTS.values.forEach { tag(ModTags.Blocks.CUT_POSTS).add(it.get()) }
        ModBlocks.CUT_STRIPPED_WOODEN_POSTS.values.forEach { tag(ModTags.Blocks.CUT_POSTS).add(it.get()) }

        ModBlocks.WOODEN_POSTS.filterKeys { !it.isFlammable }.values.forEach {
            tag(ModTags.Blocks.NON_FLAMMABLE_POSTS).add(it.get())
        }

        ModBlocks.STRIPPED_WOODEN_POSTS.filterKeys { !it.isFlammable }.values.forEach {
            tag(ModTags.Blocks.NON_FLAMMABLE_POSTS).add(it.get())
        }

        tag(BlockTags.IMPERMEABLE).add(ModBlocks.METAL_FRAMED_GLASS.get())

        ModBlocks.COLORED_METAL_FRAMED_GLASS.values.forEach {
            tag(BlockTags.IMPERMEABLE).add(it.get())
            tag(Tags.Blocks.GLASS_BLOCKS_TINTED).add(it.get())
        }

        tag(Tags.Blocks.GLASS_BLOCKS_COLORLESS).add(ModBlocks.METAL_FRAMED_GLASS.get())
        tag(Tags.Blocks.GLASS_PANES).add(ModBlocks.METAL_FRAMED_GLASS_PANE.get())

        ModBlocks.COLORED_METAL_FRAMED_GLASS_PANES.values.forEach {
            tag(Tags.Blocks.GLASS_PANES).add(it.get())
            tag(Tags.Blocks.GLASS_BLOCKS_TINTED).add(it.get())
        }

        tag(Tags.Blocks.GLASS_PANES_COLORLESS).add(ModBlocks.METAL_FRAMED_GLASS_PANE.get())
        tag(BlockTags.CLIMBABLE).add(ModBlocks.HANGING_VINES_BODY.get(), ModBlocks.HANGING_VINES.get())
        ModBlocks.COLORED_GLAZED_JARS.values.forEach { tag(ModTags.Blocks.COLORED_JARS).add(it.get()) }
        tag(ModTags.Blocks.JARS).add(ModBlocks.GLAZED_JAR.get(), ModBlocks.CRACKED_GLAZED_JAR.get())
            .addTag(ModTags.Blocks.COLORED_JARS)

        //this.tag(BlockTags.FLOWERS).add(ModBlocks.HIBISCUS.get());
        tag(BlockTags.PIGLIN_REPELLENTS).add(ModBlocks.SOUL_BRAZIER.get())
        tag(BlockTags.WALL_POST_OVERRIDE).add(ModBlocks.UNLIT_TORCH.get(), ModBlocks.UNLIT_SOUL_TORCH.get())

        ModBlocks.SLEEPING_BAGS.values.forEach { tag(ModTags.Blocks.SLEEPING_BAGS).add(it.get()) }

        tag(BlockTags.MINEABLE_WITH_AXE).add(
            ModBlocks.HANGING_VINES.get(),
            ModBlocks.HANGING_VINES_BODY.get(),
            ModBlocks.PAPER_WALL.get()
        )
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.JARS).add(
            ModBlocks.DUNGEON_DOOR.get(),
            ModBlocks.DUNGEON_DOOR_LEAF.get(),
            ModBlocks.BONE_PILE.get(),
            ModBlocks.BONE_PILE_BLOCK.get()
        )
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.DUNGEON_DOOR.get(), ModBlocks.DUNGEON_DOOR_LEAF.get())
    }
}
