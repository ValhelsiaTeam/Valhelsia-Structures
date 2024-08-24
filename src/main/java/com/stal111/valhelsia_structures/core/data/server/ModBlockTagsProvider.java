package com.stal111.valhelsia_structures.core.data.server;

import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.StainedGlassBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.neoforged.neoforge.common.Tags;
import net.valhelsia.valhelsia_core.api.common.registry.helper.block.BlockRegistryEntry;
import net.valhelsia.valhelsia_core.datagen.DataProviderContext;
import net.valhelsia.valhelsia_core.datagen.tags.ValhelsiaBlockTagsProvider;

import javax.annotation.Nonnull;

/**
 * Mod Block Tags Provider <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.server.ModBlockTagsProvider
 *
 * @author Valhelsia Team
 * @since 2021-01-11
 */
public class ModBlockTagsProvider extends ValhelsiaBlockTagsProvider {

    public ModBlockTagsProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    protected void addTags(@Nonnull HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.BRAZIERS).add(ModBlocks.BRAZIER.get(), ModBlocks.SOUL_BRAZIER.get());
        ModBlocks.HELPER.getRegistryEntries().forEach(entry -> {
            if (entry.get() instanceof PostBlock) {
                this.tag(ModTags.Blocks.POSTS).add(entry.get());
            } else if (entry.get() instanceof CutPostBlock) {
                this.tag(ModTags.Blocks.CUT_POSTS).add(entry.get());
            }
        });

        ModBlocks.WOODEN_POSTS.forEach((woodType, registryObject) -> {
            if (!woodType.isFlammable()) {
                this.tag(ModTags.Blocks.NON_FLAMMABLE_POSTS).add(registryObject.get());
            }
        });

        ModBlocks.STRIPPED_WOODEN_POSTS.forEach((woodType, registryObject) -> {
            if (!woodType.isFlammable()) {
                this.tag(ModTags.Blocks.NON_FLAMMABLE_POSTS).add(registryObject.get());
            }
        });

        this.tag(BlockTags.IMPERMEABLE).add(ModBlocks.METAL_FRAMED_GLASS.get());
        for (BlockRegistryEntry<StainedGlassBlock> registryObject : ModBlocks.COLORED_METAL_FRAMED_GLASS.values()) {
            this.tag(BlockTags.IMPERMEABLE).add(registryObject.get());
            this.tag(Tags.Blocks.GLASS_BLOCKS_TINTED).add(registryObject.get());
        }

        this.tag(Tags.Blocks.GLASS_BLOCKS_COLORLESS).add(ModBlocks.METAL_FRAMED_GLASS.get());
        this.tag(Tags.Blocks.GLASS_PANES).add(ModBlocks.METAL_FRAMED_GLASS_PANE.get());
        for (BlockRegistryEntry<StainedGlassPaneBlock> registryObject : ModBlocks.COLORED_METAL_FRAMED_GLASS_PANES.values()) {
            this.tag(Tags.Blocks.GLASS_PANES).add(registryObject.get());
            this.tag(Tags.Blocks.GLASS_BLOCKS_TINTED).add(registryObject.get());
        }
        this.tag(Tags.Blocks.GLASS_PANES_COLORLESS).add(ModBlocks.METAL_FRAMED_GLASS_PANE.get());
        this.tag(BlockTags.CLIMBABLE).add(ModBlocks.HANGING_VINES_BODY.get(), ModBlocks.HANGING_VINES.get());
        ModBlocks.COLORED_GLAZED_JARS.values().forEach(registryObject -> this.tag(ModTags.Blocks.COLORED_JARS).add(registryObject.get()));
        this.tag(ModTags.Blocks.JARS).add(ModBlocks.GLAZED_JAR.get(), ModBlocks.CRACKED_GLAZED_JAR.get()).addTag(ModTags.Blocks.COLORED_JARS);
        ModBlocks.BIG_COLORED_GLAZED_JARS.values().forEach(registryObject -> this.tag(ModTags.Blocks.BIG_COLORED_JARS).add(registryObject.get()));
        this.tag(ModTags.Blocks.BIG_JARS).add(ModBlocks.BIG_GLAZED_JAR.get(), ModBlocks.CRACKED_BIG_GLAZED_JAR.get()).addTag(ModTags.Blocks.BIG_COLORED_JARS);
        //this.tag(BlockTags.FLOWERS).add(ModBlocks.HIBISCUS.get());

        this.tag(BlockTags.PIGLIN_REPELLENTS).add(ModBlocks.SOUL_BRAZIER.get());
        this.tag(BlockTags.WALL_POST_OVERRIDE).add(
                ModBlocks.DOUSED_TORCH.get(),
                ModBlocks.DOUSED_SOUL_TORCH.get()
        );

        ModBlocks.SLEEPING_BAGS.values().forEach(block -> this.tag(ModTags.Blocks.SLEEPING_BAGS).add(block.get()));

        //TODO
        //this.tag(ValhelsiaTags.Blocks.OFFSET_RENDERING).add(ModBlocks.BONE_PILE.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.HANGING_VINES.get(), ModBlocks.HANGING_VINES_BODY.get(), ModBlocks.PAPER_WALL.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(ModTags.Blocks.JARS).addTag(ModTags.Blocks.BIG_JARS)
                .add(ModBlocks.DUNGEON_DOOR.get(), ModBlocks.DUNGEON_DOOR_LEAF.get(), ModBlocks.BONE_PILE.get(), ModBlocks.BONE_PILE_BLOCK.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.DUNGEON_DOOR.get(), ModBlocks.DUNGEON_DOOR_LEAF.get());
    }
}
