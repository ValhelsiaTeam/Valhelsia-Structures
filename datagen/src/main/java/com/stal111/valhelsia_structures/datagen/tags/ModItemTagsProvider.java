package com.stal111.valhelsia_structures.datagen.tags;

import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.valhelsia.valhelsia_core.datagen.DataProviderContext;
import net.valhelsia.valhelsia_core.datagen.tags.ValhelsiaItemTagsProvider;

import java.util.concurrent.CompletableFuture;

/**
 * Mod Item Tags Provider <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.data.ModItemTagsProvider
 *
 * @author Valhelsia Team
 * @since 2021-01-12
 */
public class ModItemTagsProvider extends ValhelsiaItemTagsProvider {

    public ModItemTagsProvider(DataProviderContext context, CompletableFuture<TagsProvider.TagLookup<Block>> blockTagLookup) {
        super(context, blockTagLookup);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.copy(ModTags.Blocks.POSTS, ModTags.Items.POSTS);
        this.copy(ModTags.Blocks.CUT_POSTS, ModTags.Items.CUT_POSTS);
        this.copy(ModTags.Blocks.NON_FLAMMABLE_POSTS, ModTags.Items.NON_FLAMMABLE_POSTS);
        this.copy(Tags.Blocks.GLASS_BLOCKS_COLORLESS, Tags.Items.GLASS_BLOCKS_COLORLESS);
        this.copy(Tags.Blocks.GLASS_PANES, Tags.Items.GLASS_PANES);
        this.copy(Tags.Blocks.GLASS_PANES_COLORLESS, Tags.Items.GLASS_PANES_COLORLESS);
        this.copy(ModTags.Blocks.COLORED_JARS, ModTags.Items.COLORED_JARS);
        this.copy(ModTags.Blocks.JARS, ModTags.Items.JARS);
        this.copy(ModTags.Blocks.SLEEPING_BAGS, ModTags.Items.SLEEPING_BAGS);

        this.copy(BlockTags.PIGLIN_REPELLENTS, ItemTags.PIGLIN_REPELLENTS);

        this.tag(ModTags.Items.JAR_BLACKLISTED).add(Items.CACTUS, Items.CRIMSON_ROOTS, Items.WARPED_ROOTS);
        this.tag(ItemTags.DYEABLE).add(ModBlocks.EXPLORERS_TENT.get().asItem());
    }
}
