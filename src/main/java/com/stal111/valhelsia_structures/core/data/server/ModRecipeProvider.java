package com.stal111.valhelsia_structures.core.data.server;

import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.common.recipe.AxeCraftingRecipeBuilder;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Mod Recipe Provider <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.server.ModRecipeProvider
 *
 * @author Valhelsia Team
 * @version 1.19 - 0.2.0
 * @since 2020-11-16
 */
public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
        //Crafting Recipes
        ShapedRecipeBuilder.shaped(ModBlocks.BRAZIER.get()).pattern("*X*").pattern("###").define('#', Items.IRON_INGOT).define('X', ItemTags.COALS).define('*', Items.IRON_BARS).unlockedBy("has_item", has(ItemTags.COALS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.SOUL_BRAZIER.get()).pattern("*X*").pattern("###").define('#', Items.IRON_INGOT).define('X', ItemTags.SOUL_FIRE_BASE_BLOCKS).define('*', Items.IRON_BARS).unlockedBy("has_item", has(ItemTags.SOUL_FIRE_BASE_BLOCKS)).save(consumer);

        ModBlocks.HELPER.getRegistryObjects().forEach(registryObject -> {
            if (registryObject.get() instanceof PostBlock postBlock) {
                new AxeCraftingRecipeBuilder(Ingredient.of(postBlock.getLogBlock()), postBlock.asItem(), 2).unlocks("has_item", has(postBlock.getLogBlock())).save(consumer);
            } else if (registryObject.get() instanceof CutPostBlock block) {
                Block postBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ValhelsiaStructures.MOD_ID, Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath().substring(4)));

                if (postBlock != null) {
                    new AxeCraftingRecipeBuilder(Ingredient.of(postBlock), registryObject.get().asItem(), 4).unlocks("has_item", has(postBlock)).save(consumer);
                }
            }
        });

        ShapedRecipeBuilder.shaped(ModBlocks.METAL_FRAMED_GLASS.get(), 8).pattern("###").pattern("#X#").pattern("###").define('#', Blocks.GLASS).define('X', Tags.Items.INGOTS_IRON).unlockedBy("has_item", has(Blocks.GLASS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.METAL_FRAMED_GLASS_PANE.get(), 16).pattern("###").pattern("###").define('#', ModBlocks.METAL_FRAMED_GLASS.get()).unlockedBy("has_item", has(ModBlocks.METAL_FRAMED_GLASS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.PAPER_WALL.get(), 2).pattern("#X#").pattern("#X#").pattern("#X#").define('#', Items.BAMBOO).define('X', Items.PAPER).unlockedBy("has_item", has(Items.BAMBOO)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.LAPIDIFIED_JUNGLE_LOG.get(), 8).pattern("###").pattern("#X#").pattern("###").define('#', Blocks.JUNGLE_LOG).define('X', Ingredient.of(Blocks.VINE, ModBlocks.HANGING_VINES.get())).unlockedBy("has_item", has(Blocks.VINE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.LAPIDIFIED_JUNGLE_WOOD.get(), 3).pattern("##").pattern("##").define('#', ModBlocks.LAPIDIFIED_JUNGLE_LOG.get()).unlockedBy("has_item", has(ModBlocks.LAPIDIFIED_JUNGLE_LOG.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get(), 4).requires(ModTags.Items.LAPIDIFIED_JUNGLE_LOGS).unlockedBy("has_item", has(ModTags.Items.LAPIDIFIED_JUNGLE_LOGS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.LAPIDIFIED_JUNGLE_SLAB.get(), 6).pattern("###").define('#', ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).unlockedBy("has_item", has(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.LAPIDIFIED_JUNGLE_STAIRS.get(), 4).pattern("#  ").pattern("## ").pattern("###").define('#', ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).unlockedBy("has_item", has(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.LAPIDIFIED_JUNGLE_PRESSURE_PLATE.get()).pattern("##").define('#', ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).unlockedBy("has_item", has(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.LAPIDIFIED_JUNGLE_BUTTON.get()).requires(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).unlockedBy("has_item", has(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.LAPIDIFIED_JUNGLE_FENCE.get(), 3).pattern("#X#").pattern("#X#").define('#', ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).define('X', Tags.Items.RODS_WOODEN).unlockedBy("has_item", has(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.LAPIDIFIED_JUNGLE_FENCE_GATE.get()).pattern("#X#").pattern("#X#").define('#', Tags.Items.RODS_WOODEN).define('X', ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).unlockedBy("has_item", has(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).save(consumer);

        ShapedRecipeBuilder.shaped(ModBlocks.BONE_PILE.get(), 3).pattern("###").define('#', Items.BONE).group("bone_pile").unlockedBy("has_item", has(Items.BONE)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.BONE_PILE.get(), 9).requires(ModBlocks.BONE_PILE_BLOCK.get()).group("bone_pile").unlockedBy("has_item", has(ModBlocks.BONE_PILE_BLOCK.get())).save(consumer, "bone_pile_from_bone_pile_block");
        ShapedRecipeBuilder.shaped(ModBlocks.BONE_PILE_BLOCK.get()).pattern("###").pattern("###").pattern("###").define('#', Items.BONE).group("bone_pile_block").unlockedBy("has_item", has(Items.BONE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.BONE_PILE_BLOCK.get()).pattern("###").pattern("###").pattern("###").define('#', ModBlocks.BONE_PILE.get()).group("bone_pile_block").unlockedBy("has_item", has(ModBlocks.BONE_PILE.get())).save(consumer, "bone_pile_block_from_bone_piles");
        ShapedRecipeBuilder.shaped(ModBlocks.GLAZED_JAR.get()).pattern("# #").pattern(" # ").define('#', Blocks.TERRACOTTA).group("jar").unlockedBy("has_item", has(Blocks.TERRACOTTA)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.BIG_GLAZED_JAR.get()).pattern("# #").pattern("# #").pattern(" # ").define('#', Blocks.TERRACOTTA).group("big_jar").unlockedBy("has_item", has(Blocks.TERRACOTTA)).save(consumer);

        ModBlocks.COLORED_GLAZED_JARS.forEach(blockRegistryObject -> {
            String name = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get())).getPath();
            Block block = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name.substring(0, name.length() - 11) + "_terracotta")));
            ShapedRecipeBuilder.shaped(blockRegistryObject.get()).pattern("# #").pattern(" # ").define('#', block).group("jar").unlockedBy("has_item", has(block)).save(consumer);
        });

        ModBlocks.BIG_COLORED_GLAZED_JARS.forEach(blockRegistryObject -> {
            String name = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get())).getPath();
            Block block = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name.substring(4, name.length() - 11) + "_terracotta")));
            ShapedRecipeBuilder.shaped(blockRegistryObject.get()).pattern("# #").pattern("# #").pattern(" # ").define('#', block).group("big_jar").unlockedBy("has_item", has(block)).save(consumer);
        });

        this.addSimple2x2Recipe(ModBlocks.BUNDLED_OAK_POSTS.get(), ModBlocks.OAK_POST.get(), consumer);
        this.addSimple2x2Recipe(ModBlocks.BUNDLED_SPRUCE_POSTS.get(), ModBlocks.SPRUCE_POST.get(), consumer);
        this.addSimple2x2Recipe(ModBlocks.BUNDLED_BIRCH_POSTS.get(), ModBlocks.BIRCH_POST.get(), consumer);
        this.addSimple2x2Recipe(ModBlocks.BUNDLED_JUNGLE_POSTS.get(), ModBlocks.JUNGLE_POST.get(), consumer);
        this.addSimple2x2Recipe(ModBlocks.BUNDLED_ACACIA_POSTS.get(), ModBlocks.ACACIA_POST.get(), consumer);
        this.addSimple2x2Recipe(ModBlocks.BUNDLED_DARK_OAK_POSTS.get(), ModBlocks.DARK_OAK_POST.get(), consumer);
        this.addSimple2x2Recipe(ModBlocks.BUNDLED_CRIMSON_POSTS.get(), ModBlocks.CRIMSON_POST.get(), consumer);
        this.addSimple2x2Recipe(ModBlocks.BUNDLED_WARPED_POSTS.get(), ModBlocks.WARPED_POST.get(), consumer);

        Block whiteSleepingBag = ModBlocks.SLEEPING_BAGS.get(DyeColor.WHITE).get();

        ModBlocks.SLEEPING_BAGS.forEach((color, registryObject) -> {
            Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(color.getName() + "_wool"));

            if (block != null) {
                this.addSingleRowRecipe(registryObject.get(), block, consumer);
            }

            if (color != DyeColor.WHITE) {
                ShapelessRecipeBuilder.shapeless(registryObject.get()).requires(whiteSleepingBag).requires(color.getTag()).unlockedBy("has_item", has(whiteSleepingBag)).unlockedBy("has_color", has(color.getTag())).save(consumer, new ResourceLocation(ValhelsiaStructures.MOD_ID, registryObject.getId() + "_from_white_sleeping_bag"));
            }
        });

        //Smelting Recipes
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.GLAZED_JAR.get()), ModBlocks.CRACKED_GLAZED_JAR.get(), 0.1F, 200).unlockedBy("has_item", has(ModBlocks.GLAZED_JAR.get())).save(consumer, "valhelsia_structures:smelting/cracked_glazed_jar");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.BIG_GLAZED_JAR.get()), ModBlocks.CRACKED_BIG_GLAZED_JAR.get(), 0.1F, 200).unlockedBy("has_item", has(ModBlocks.BIG_GLAZED_JAR.get())).save(consumer, "valhelsia_structures:smelting/cracked_big_glazed_jar");
    }

    private void addSimple2x2Recipe(ItemLike result, ItemLike item, @Nonnull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result).pattern("##").pattern("##").define('#', item).unlockedBy("has_item", has(item)).save(consumer);
    }

    private void addSingleRowRecipe(ItemLike result, ItemLike item, @Nonnull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(result).pattern("###").define('#', item).unlockedBy("has_item", has(item)).save(consumer);
    }
}
