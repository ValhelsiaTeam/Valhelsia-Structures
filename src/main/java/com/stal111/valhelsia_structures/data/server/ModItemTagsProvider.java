package com.stal111.valhelsia_structures.data.server;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

/**
 * Mod Item Tags Provider
 * Valhelsia Structures - com.stal111.valhelsia_structures.data.server.ModItemTagsProvider
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-12
 */
public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, ValhelsiaStructures.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        this.copy(ModTags.Blocks.POSTS, ModTags.Items.POSTS);
        this.copy(ModTags.Blocks.NON_FLAMMABLE_POSTS, ModTags.Items.NON_FLAMMABLE_POSTS);
        this.copy(Tags.Blocks.GLASS, Tags.Items.GLASS);
        this.copy(Tags.Blocks.GLASS_COLORLESS, Tags.Items.GLASS_COLORLESS);
        this.copy(Tags.Blocks.GLASS_PANES, Tags.Items.GLASS_PANES);
        this.copy(Tags.Blocks.GLASS_PANES_COLORLESS, Tags.Items.GLASS_PANES_COLORLESS);
        this.copy(ModTags.Blocks.COLORED_JARS, ModTags.Items.COLORED_JARS);
        this.copy(ModTags.Blocks.JARS, ModTags.Items.JARS);

        this.copy(BlockTags.PIGLIN_REPELLENTS, ItemTags.PIGLIN_REPELLENTS);
    }
}
