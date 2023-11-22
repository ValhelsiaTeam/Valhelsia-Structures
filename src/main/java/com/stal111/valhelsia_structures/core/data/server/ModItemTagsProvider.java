package com.stal111.valhelsia_structures.core.data.server;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

/**
 * Mod Item Tags Provider <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.data.ModItemTagsProvider
 *
 * @author Valhelsia Team
 * @since 2021-01-12
 */
public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagProvider, ValhelsiaStructures.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.copy(ModTags.Blocks.POSTS, ModTags.Items.POSTS);
        this.copy(ModTags.Blocks.CUT_POSTS, ModTags.Items.CUT_POSTS);
        this.copy(ModTags.Blocks.NON_FLAMMABLE_POSTS, ModTags.Items.NON_FLAMMABLE_POSTS);
        this.copy(Tags.Blocks.GLASS_COLORLESS, Tags.Items.GLASS_COLORLESS);
        this.copy(Tags.Blocks.GLASS_PANES, Tags.Items.GLASS_PANES);
        this.copy(Tags.Blocks.GLASS_PANES_COLORLESS, Tags.Items.GLASS_PANES_COLORLESS);
        this.copy(ModTags.Blocks.COLORED_JARS, ModTags.Items.COLORED_JARS);
        this.copy(ModTags.Blocks.JARS, ModTags.Items.JARS);
        ModBlocks.BIG_COLORED_GLAZED_JARS.values().forEach(registryObject -> this.tag(ModTags.Items.BIG_COLORED_JARS).add(registryObject.get().asItem()));
        this.tag(ModTags.Items.BIG_JARS).add(ModBlocks.BIG_GLAZED_JAR.get().asItem(), ModBlocks.CRACKED_BIG_GLAZED_JAR.get().asItem()).addTag(ModTags.Items.BIG_COLORED_JARS);
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
        this.copy(ModTags.Blocks.SLEEPING_BAGS, ModTags.Items.SLEEPING_BAGS);

        this.copy(BlockTags.PIGLIN_REPELLENTS, ItemTags.PIGLIN_REPELLENTS);

        this.tag(ModTags.Items.JAR_BLACKLISTED).add(Items.CACTUS, Items.CRIMSON_ROOTS, Items.WARPED_ROOTS);
    }
}
