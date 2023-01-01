package com.stal111.valhelsia_structures.core.data.server;

import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.StainedGlassBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.core.init.ValhelsiaTags;

import javax.annotation.Nullable;

/**
 * Mod Block Tags Provider <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.server.ModBlockTagsProvider
 *
 * @author Valhelsia Team
 * @since 2021-01-11
 */
public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, ValhelsiaStructures.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ModTags.Blocks.BRAZIERS).add(ModBlocks.BRAZIER.get(), ModBlocks.SOUL_BRAZIER.get());
        ModBlocks.HELPER.getRegistryObjects().forEach(registryObject -> {
            if (registryObject.get() instanceof PostBlock) {
                this.tag(ModTags.Blocks.POSTS).add(registryObject.get());
            } else if (registryObject.get() instanceof CutPostBlock) {
                this.tag(ModTags.Blocks.CUT_POSTS).add(registryObject.get());
            }
        });
        this.tag(ModTags.Blocks.NON_FLAMMABLE_POSTS).add(ModBlocks.WARPED_POST.get(), ModBlocks.CRIMSON_POST.get(), ModBlocks.LAPIDIFIED_JUNGLE_POST.get());

        this.tag(BlockTags.IMPERMEABLE).add(ModBlocks.METAL_FRAMED_GLASS.get());
        for (RegistryObject<StainedGlassBlock> registryObject : ModBlocks.COLORED_METAL_FRAMED_GLASS.values()) {
            this.tag(BlockTags.IMPERMEABLE).add(registryObject.get());
            this.tag(Tags.Blocks.STAINED_GLASS).add(registryObject.get());
        }

        this.tag(Tags.Blocks.GLASS_COLORLESS).add(ModBlocks.METAL_FRAMED_GLASS.get());
        this.tag(Tags.Blocks.GLASS_PANES).add(ModBlocks.METAL_FRAMED_GLASS_PANE.get());
        for (RegistryObject<StainedGlassPaneBlock> registryObject : ModBlocks.COLORED_METAL_FRAMED_GLASS_PANES.values()) {
            this.tag(Tags.Blocks.GLASS_PANES).add(registryObject.get());
            this.tag(Tags.Blocks.STAINED_GLASS_PANES).add(registryObject.get());
        }
        this.tag(Tags.Blocks.GLASS_PANES_COLORLESS).add(ModBlocks.METAL_FRAMED_GLASS_PANE.get());
        this.tag(BlockTags.CLIMBABLE).add(ModBlocks.HANGING_VINES_BODY.get(), ModBlocks.HANGING_VINES.get());
        ModBlocks.COLORED_GLAZED_JARS.forEach(registryObject -> this.tag(ModTags.Blocks.COLORED_JARS).add(registryObject.get()));
        this.tag(ModTags.Blocks.JARS).add(ModBlocks.GLAZED_JAR.get(), ModBlocks.CRACKED_GLAZED_JAR.get()).addTag(ModTags.Blocks.COLORED_JARS);
        ModBlocks.BIG_COLORED_GLAZED_JARS.forEach(registryObject -> this.tag(ModTags.Blocks.BIG_COLORED_JARS).add(registryObject.get()).add(registryObject.get().getTopBlock()));
        this.tag(ModTags.Blocks.BIG_JARS).add(ModBlocks.BIG_GLAZED_JAR.get(), ModBlocks.BIG_GLAZED_JAR_TOP.get(), ModBlocks.CRACKED_BIG_GLAZED_JAR.get(), ModBlocks.CRACKED_BIG_GLAZED_JAR_TOP.get()).addTag(ModTags.Blocks.BIG_COLORED_JARS);
        this.tag(ModTags.Blocks.LAPIDIFIED_JUNGLE_LOGS).add(ModBlocks.LAPIDIFIED_JUNGLE_LOG.get(), ModBlocks.LAPIDIFIED_JUNGLE_WOOD.get());
        this.tag(BlockTags.LOGS).add(ModBlocks.LAPIDIFIED_JUNGLE_LOG.get(), ModBlocks.LAPIDIFIED_JUNGLE_WOOD.get()).addTag(ModTags.Blocks.LAPIDIFIED_JUNGLE_LOGS);
        this.tag(BlockTags.PLANKS).add(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get());
        this.tag(BlockTags.WOODEN_SLABS).add(ModBlocks.LAPIDIFIED_JUNGLE_SLAB.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(ModBlocks.LAPIDIFIED_JUNGLE_STAIRS.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.LAPIDIFIED_JUNGLE_PRESSURE_PLATE.get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(ModBlocks.LAPIDIFIED_JUNGLE_BUTTON.get());
        this.tag(BlockTags.WOODEN_FENCES).add(ModBlocks.LAPIDIFIED_JUNGLE_FENCE.get());
        this.tag(Tags.Blocks.FENCES_WOODEN).add(ModBlocks.LAPIDIFIED_JUNGLE_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(ModBlocks.LAPIDIFIED_JUNGLE_FENCE_GATE.get());
        this.tag(Tags.Blocks.FENCE_GATES_WOODEN).add(ModBlocks.LAPIDIFIED_JUNGLE_FENCE_GATE.get());
        this.tag(BlockTags.FLOWERS).add(ModBlocks.HIBISCUS.get());
        this.tag(BlockTags.DIRT).add(ModBlocks.GRASS_BLOCK.get(), ModBlocks.DIRT.get(), ModBlocks.COARSE_DIRT.get());

        this.tag(BlockTags.PIGLIN_REPELLENTS).add(ModBlocks.SOUL_BRAZIER.get());
        this.tag(BlockTags.WALL_POST_OVERRIDE).add(
                ModBlocks.LAPIDIFIED_JUNGLE_PRESSURE_PLATE.get(),
                ModBlocks.DOUSED_TORCH.get(),
                ModBlocks.DOUSED_SOUL_TORCH.get()
        );
        this.tag(BlockTags.VALID_SPAWN).add(ModBlocks.GRASS_BLOCK.get());
        this.tag(BlockTags.BAMBOO_PLANTABLE_ON).add(ModBlocks.GRASS_BLOCK.get(), ModBlocks.DIRT.get());
        this.tag(BlockTags.ENDERMAN_HOLDABLE).add(ModBlocks.GRASS_BLOCK.get(), ModBlocks.DIRT.get(), ModBlocks.COARSE_DIRT.get());

        ModBlocks.SLEEPING_BAGS.values().forEach(block -> this.tag(ModTags.Blocks.SLEEPING_BAGS).add(block.get()));

        this.tag(ValhelsiaTags.Blocks.OFFSET_RENDERING).add(ModBlocks.BONE_PILE.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(ModTags.Blocks.POSTS).addTag(ModTags.Blocks.CUT_POSTS)
                .add(ModBlocks.HANGING_VINES.get(), ModBlocks.HANGING_VINES_BODY.get(), ModBlocks.BUNDLED_OAK_POSTS.get(), ModBlocks.BUNDLED_STRIPPED_OAK_POSTS.get(), ModBlocks.BUNDLED_BIRCH_POSTS.get(), ModBlocks.BUNDLED_STRIPPED_BIRCH_POSTS.get(), ModBlocks.BUNDLED_SPRUCE_POSTS.get(), ModBlocks.BUNDLED_STRIPPED_SPRUCE_POSTS.get(), ModBlocks.BUNDLED_JUNGLE_POSTS.get(), ModBlocks.BUNDLED_STRIPPED_JUNGLE_POSTS.get(), ModBlocks.BUNDLED_ACACIA_POSTS.get(), ModBlocks.BUNDLED_STRIPPED_ACACIA_POSTS.get(), ModBlocks.BUNDLED_DARK_OAK_POSTS.get(), ModBlocks.BUNDLED_STRIPPED_DARK_OAK_POSTS.get(), ModBlocks.BUNDLED_CRIMSON_POSTS.get(), ModBlocks.BUNDLED_STRIPPED_CRIMSON_POSTS.get(), ModBlocks.BUNDLED_WARPED_POSTS.get(), ModBlocks.BUNDLED_STRIPPED_WARPED_POSTS.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(ModTags.Blocks.BRAZIERS).addTag(ModTags.Blocks.JARS).addTag(ModTags.Blocks.BIG_JARS)
                .add(ModBlocks.DUNGEON_DOOR.get(), ModBlocks.DUNGEON_DOOR_LEAF.get(), ModBlocks.BONE_PILE.get(), ModBlocks.BONE_PILE_BLOCK.get(), ModBlocks.UNLIT_LANTERN.get(), ModBlocks.UNLIT_SOUL_LANTERN.get(), ModBlocks.STONE.get(), ModBlocks.GRANITE.get(), ModBlocks.DIORITE.get(), ModBlocks.ANDESITE.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.GRASS_BLOCK.get(), ModBlocks.DIRT.get(), ModBlocks.COARSE_DIRT.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.DUNGEON_DOOR.get(), ModBlocks.DUNGEON_DOOR_LEAF.get());
    }
}
