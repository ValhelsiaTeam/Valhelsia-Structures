package com.stal111.valhelsia_structures.data.server;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

/**
 * Mod Item Tags Provider
 * Valhelsia Structures - com.stal111.valhelsia_structures.data.data.ModItemTagsProvider
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
        this.copy(ModTags.Blocks.CUT_POSTS, ModTags.Items.CUT_POSTS);
        this.copy(ModTags.Blocks.NON_FLAMMABLE_POSTS, ModTags.Items.NON_FLAMMABLE_POSTS);
        this.copy(Tags.Blocks.GLASS, Tags.Items.GLASS);
        this.copy(Tags.Blocks.GLASS_COLORLESS, Tags.Items.GLASS_COLORLESS);
        this.copy(Tags.Blocks.GLASS_PANES, Tags.Items.GLASS_PANES);
        this.copy(Tags.Blocks.GLASS_PANES_COLORLESS, Tags.Items.GLASS_PANES_COLORLESS);
        this.copy(ModTags.Blocks.COLORED_JARS, ModTags.Items.COLORED_JARS);
        this.copy(ModTags.Blocks.JARS, ModTags.Items.JARS);
        this.copy(ModTags.Blocks.LAPIDIFIED_JUNGLE_LOGS, ModTags.Items.LAPIDIFIED_JUNGLE_LOGS);
        this.copy(BlockTags.LOGS, ItemTags.LOGS);
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        this.copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
        this.copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
        this.copy(BlockTags.FLOWERS, ItemTags.FLOWERS);

        this.copy(BlockTags.PIGLIN_REPELLENTS, ItemTags.PIGLIN_REPELLENTS);

        this.getOrCreateBuilder(ModTags.Items.JAR_BLACKLISTED).add(Items.CACTUS, Items.CRIMSON_ROOTS, Items.WARPED_ROOTS);
    }
}
