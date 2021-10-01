package com.stal111.valhelsia_structures.data.server;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

/**
 * Mod Block Tags Provider
 * Valhelsia Structures - com.stal111.valhelsia_structures.data.server.ModBlockTagsProvider
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-11
 */
public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, ValhelsiaStructures.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        getOrCreateBuilder(ModTags.Blocks.BRAZIERS).add(ModBlocks.BRAZIER.get(), ModBlocks.SOUL_BRAZIER.get());
        ModBlocks.HELPER.getDeferredRegister().getEntries().forEach(registryObject -> {
            if (registryObject.get() instanceof PostBlock) {
                getOrCreateBuilder(ModTags.Blocks.POSTS).add(registryObject.get());
            } else if (registryObject.get() instanceof CutPostBlock) {
                getOrCreateBuilder(ModTags.Blocks.CUT_POSTS).add(registryObject.get());
            }
        });
        getOrCreateBuilder(ModTags.Blocks.NON_FLAMMABLE_POSTS).add(ModBlocks.WARPED_POST.get(), ModBlocks.CRIMSON_POST.get()
            //    , ModBlocks.LAPIDIFIED_JUNGLE_POST.get()
        );
        getOrCreateBuilder(BlockTags.IMPERMEABLE).add(ModBlocks.METAL_FRAMED_GLASS.get());
        getOrCreateBuilder(Tags.Blocks.GLASS).add(ModBlocks.METAL_FRAMED_GLASS.get());
        getOrCreateBuilder(Tags.Blocks.GLASS_COLORLESS).add(ModBlocks.METAL_FRAMED_GLASS.get());
        getOrCreateBuilder(Tags.Blocks.GLASS_PANES).add(ModBlocks.METAL_FRAMED_GLASS_PANE.get());
        getOrCreateBuilder(Tags.Blocks.GLASS_PANES_COLORLESS).add(ModBlocks.METAL_FRAMED_GLASS_PANE.get());
        getOrCreateBuilder(BlockTags.CLIMBABLE).add(ModBlocks.HANGING_VINES_BODY.get(), ModBlocks.HANGING_VINES.get());
        ModBlocks.COLORED_GLAZED_JARS.forEach(registryObject -> getOrCreateBuilder(ModTags.Blocks.COLORED_JARS).add(registryObject.get()));
        getOrCreateBuilder(ModTags.Blocks.JARS).add(ModBlocks.GLAZED_JAR.get(), ModBlocks.CRACKED_GLAZED_JAR.get()).addTag(ModTags.Blocks.COLORED_JARS);
//        getOrCreateBuilder(ModTags.Blocks.LAPIDIFIED_JUNGLE_LOGS).add(ModBlocks.LAPIDIFIED_JUNGLE_LOG.get(), ModBlocks.LAPIDIFIED_JUNGLE_WOOD.get());
//        getOrCreateBuilder(BlockTags.LOGS).add(ModBlocks.LAPIDIFIED_JUNGLE_LOG.get(), ModBlocks.LAPIDIFIED_JUNGLE_WOOD.get()).addTag(ModTags.Blocks.LAPIDIFIED_JUNGLE_LOGS);
//        getOrCreateBuilder(BlockTags.PLANKS).add(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get());
//        getOrCreateBuilder(BlockTags.WOODEN_SLABS).add(ModBlocks.LAPIDIFIED_JUNGLE_SLAB.get());
//        getOrCreateBuilder(BlockTags.WOODEN_STAIRS).add(ModBlocks.LAPIDIFIED_JUNGLE_STAIRS.get());
//        getOrCreateBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.LAPIDIFIED_JUNGLE_PRESSURE_PLATE.get());
//        getOrCreateBuilder(BlockTags.WOODEN_BUTTONS).add(ModBlocks.LAPIDIFIED_JUNGLE_BUTTON.get());
//        getOrCreateBuilder(BlockTags.WOODEN_FENCES).add(ModBlocks.LAPIDIFIED_JUNGLE_FENCE.get());
//        getOrCreateBuilder(Tags.Blocks.FENCES_WOODEN).add(ModBlocks.LAPIDIFIED_JUNGLE_FENCE.get());
//        getOrCreateBuilder(BlockTags.FENCE_GATES).add(ModBlocks.LAPIDIFIED_JUNGLE_FENCE_GATE.get());
//        getOrCreateBuilder(Tags.Blocks.FENCE_GATES_WOODEN).add(ModBlocks.LAPIDIFIED_JUNGLE_FENCE_GATE.get());
//        getOrCreateBuilder(BlockTags.FLOWERS).add(ModBlocks.HIBISCUS.get());
        getOrCreateBuilder(Tags.Blocks.DIRT).add(ModBlocks.GRASS_BLOCK.get(), ModBlocks.DIRT.get(), ModBlocks.COARSE_DIRT.get());

        getOrCreateBuilder(BlockTags.PIGLIN_REPELLENTS).add(ModBlocks.SOUL_BRAZIER.get());
        getOrCreateBuilder(BlockTags.WALL_POST_OVERRIDE).add(
              //  ModBlocks.LAPIDIFIED_JUNGLE_PRESSURE_PLATE.get(),
                ModBlocks.DOUSED_TORCH.get(),
                ModBlocks.DOUSED_SOUL_TORCH.get()
        );
     //   getOrCreateBuilder(BlockTags.UNSTABLE_BOTTOM_CENTER).add(ModBlocks.LAPIDIFIED_JUNGLE_FENCE_GATE.get());
        getOrCreateBuilder(BlockTags.VALID_SPAWN).add(ModBlocks.GRASS_BLOCK.get());
        getOrCreateBuilder(BlockTags.BAMBOO_PLANTABLE_ON).add(ModBlocks.GRASS_BLOCK.get(), ModBlocks.DIRT.get());
        getOrCreateBuilder(BlockTags.ENDERMAN_HOLDABLE).add(ModBlocks.GRASS_BLOCK.get(), ModBlocks.DIRT.get(), ModBlocks.COARSE_DIRT.get());


    }
}
