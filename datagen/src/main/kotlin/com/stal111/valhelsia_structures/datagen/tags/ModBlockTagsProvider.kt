package com.stal111.valhelsia_structures.datagen.tags

import com.stal111.valhelsia_structures.core.init.ModBlocks
import com.stal111.valhelsia_structures.utils.ModTags
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BlockTags
import net.neoforged.neoforge.common.Tags
import net.valhelsia.dataforge.DataProviderContext
import net.valhelsia.dataforge.tag.DataForgeBlockTagsProvider

class ModBlockTagsProvider(context: DataProviderContext.Server) : DataForgeBlockTagsProvider(context) {
    override fun addTags(provider: HolderLookup.Provider) {
        tag(ModTags.Blocks.BRAZIERS).add(ModBlocks.BRAZIER.key(), ModBlocks.SOUL_BRAZIER.key())

        ModBlocks.WOODEN_POSTS.values().forEach { tag(ModTags.Blocks.POSTS).add(it.key()) }
        ModBlocks.STRIPPED_WOODEN_POSTS.values().forEach { tag(ModTags.Blocks.POSTS).add(it.key()) }
        ModBlocks.CUT_WOODEN_POSTS.values().forEach { tag(ModTags.Blocks.CUT_POSTS).add(it.key()) }
        ModBlocks.CUT_STRIPPED_WOODEN_POSTS.values().forEach { tag(ModTags.Blocks.CUT_POSTS).add(it.key()) }

        ModBlocks.WOODEN_POSTS.forEach { woodType, entry ->
            if (!woodType.isFlammable) {
                tag(ModTags.Blocks.NON_FLAMMABLE_POSTS).add(entry.key())
            }
        }

        ModBlocks.STRIPPED_WOODEN_POSTS.forEach { woodType, entry ->
            if (!woodType.isFlammable) {
                tag(ModTags.Blocks.NON_FLAMMABLE_POSTS).add(entry.key())
            }
        }

        tag(BlockTags.IMPERMEABLE).add(ModBlocks.METAL_FRAMED_GLASS.key())

        ModBlocks.COLORED_METAL_FRAMED_GLASS.values().forEach {
            tag(BlockTags.IMPERMEABLE).add(it.key())
            tag(Tags.Blocks.GLASS_BLOCKS_TINTED).add(it.key())
        }

        tag(Tags.Blocks.GLASS_BLOCKS_COLORLESS).add(ModBlocks.METAL_FRAMED_GLASS.key())
        tag(Tags.Blocks.GLASS_PANES).add(ModBlocks.METAL_FRAMED_GLASS_PANE.key())

        ModBlocks.COLORED_METAL_FRAMED_GLASS_PANES.values().forEach {
            tag(Tags.Blocks.GLASS_PANES).add(it.key())
            tag(Tags.Blocks.GLASS_BLOCKS_TINTED).add(it.key())
        }

        tag(Tags.Blocks.GLASS_PANES_COLORLESS).add(ModBlocks.METAL_FRAMED_GLASS_PANE.key())
        tag(BlockTags.CLIMBABLE).add(ModBlocks.HANGING_VINES_BODY.key(), ModBlocks.HANGING_VINES.key())
        ModBlocks.COLORED_GLAZED_JARS.values().forEach { tag(ModTags.Blocks.COLORED_JARS).add(it.key()) }
        tag(ModTags.Blocks.JARS).add(ModBlocks.GLAZED_JAR.key(), ModBlocks.CRACKED_GLAZED_JAR.key())
            .addTag(ModTags.Blocks.COLORED_JARS)

        //this.tag(BlockTags.FLOWERS).add(ModBlocks.HIBISCUS.get());
        tag(BlockTags.PIGLIN_REPELLENTS).add(ModBlocks.SOUL_BRAZIER.key())
        tag(BlockTags.WALL_POST_OVERRIDE).add(ModBlocks.UNLIT_TORCH.key(), ModBlocks.UNLIT_SOUL_TORCH.key())

        ModBlocks.SLEEPING_BAGS.values().forEach { tag(ModTags.Blocks.SLEEPING_BAGS).add(it.key()) }

        tag(BlockTags.MINEABLE_WITH_AXE).add(
            ModBlocks.HANGING_VINES.key(),
            ModBlocks.HANGING_VINES_BODY.key(),
            ModBlocks.PAPER_WALL.key()
        )
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.JARS).add(
            ModBlocks.DUNGEON_DOOR.key(),
            ModBlocks.DUNGEON_DOOR_LEAF.key(),
            ModBlocks.BONE_PILE.key(),
            ModBlocks.BONE_PILE_BLOCK.key()
        )
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.DUNGEON_DOOR.key(), ModBlocks.DUNGEON_DOOR_LEAF.key())
    }
}
