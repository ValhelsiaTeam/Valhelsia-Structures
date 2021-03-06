package com.stal111.valhelsia_structures.data.data;

import com.stal111.valhelsia_structures.block.PostBlock;
import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.recipe.AxeCraftingRecipeBuilder;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

/**
 * Mod Recipe Provider
 * Valhelsia Structures - com.stal111.valhelsia_structures.data.server.ModRecipeProvider
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-16
 */
public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.BRAZIER.get()).patternLine("*X*").patternLine("###").key('#', Items.IRON_INGOT).key('X', ItemTags.COALS).key('*', Items.IRON_BARS).addCriterion("has_item", hasItem(ItemTags.COALS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.SOUL_BRAZIER.get()).patternLine("*X*").patternLine("###").key('#', Items.IRON_INGOT).key('X', ItemTags.SOUL_FIRE_BASE_BLOCKS).key('*', Items.IRON_BARS).addCriterion("has_item", hasItem(ItemTags.SOUL_FIRE_BASE_BLOCKS)).build(consumer);

        ModBlocks.HELPER.getDeferredRegister().getEntries().forEach(registryObject -> {
            if (registryObject.get() instanceof PostBlock) {
                PostBlock postBlock = (PostBlock) registryObject.get();

                new AxeCraftingRecipeBuilder(Ingredient.fromItems(postBlock.getLogBlock()), postBlock.asItem(), 2)
                        .addCriterion("has_item", hasItem(postBlock.getLogBlock()))
                        .build(consumer);
            }
        });

        ShapedRecipeBuilder.shapedRecipe(ModBlocks.METAL_FRAMED_GLASS.get(), 8).patternLine("###").patternLine("#X#").patternLine("###").key('#', Blocks.GLASS).key('X', Tags.Items.INGOTS_IRON).addCriterion("has_item", hasItem(Blocks.GLASS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.METAL_FRAMED_GLASS_PANE.get(), 16).patternLine("###").patternLine("###").key('#', ModBlocks.METAL_FRAMED_GLASS.get()).addCriterion("has_item", hasItem(ModBlocks.METAL_FRAMED_GLASS.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.PAPER_WALL.get(), 2).patternLine("#X#").patternLine("#X#").patternLine("#X#").key('#', Items.BAMBOO).key('X', Items.PAPER).addCriterion("has_item", hasItem(Items.BAMBOO)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.LAPIDIFIED_JUNGLE_LOG.get(), 8).patternLine("###").patternLine("#X#").patternLine("###").key('#', Blocks.JUNGLE_LOG).key('X', Ingredient.fromItems(Blocks.VINE, ModBlocks.HANGING_VINES.get())).addCriterion("has_item", hasItem(Blocks.VINE)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.LAPIDIFIED_JUNGLE_WOOD.get(), 3).patternLine("##").patternLine("##").key('#', ModBlocks.LAPIDIFIED_JUNGLE_LOG.get()).addCriterion("has_item", hasItem(ModBlocks.LAPIDIFIED_JUNGLE_LOG.get())).build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get(), 4).addIngredient(ModTags.Items.LAPIDIFIED_JUNGLE_LOGS).addCriterion("has_item", hasItem(ModTags.Items.LAPIDIFIED_JUNGLE_LOGS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.LAPIDIFIED_JUNGLE_SLAB.get(), 6).patternLine("###").key('#', ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).addCriterion("has_item", hasItem(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.LAPIDIFIED_JUNGLE_STAIRS.get(), 4).patternLine("#  ").patternLine("## ").patternLine("###").key('#', ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).addCriterion("has_item", hasItem(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.LAPIDIFIED_JUNGLE_PRESSURE_PLATE.get()).patternLine("##").key('#', ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).addCriterion("has_item", hasItem(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.LAPIDIFIED_JUNGLE_BUTTON.get()).addIngredient(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).addCriterion("has_item", hasItem(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.LAPIDIFIED_JUNGLE_FENCE.get(), 3).patternLine("#X#").patternLine("#X#").key('#', ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).key('X', Tags.Items.RODS_WOODEN).addCriterion("has_item", hasItem(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.LAPIDIFIED_JUNGLE_FENCE_GATE.get()).patternLine("#X#").patternLine("#X#").key('#', Tags.Items.RODS_WOODEN).key('X', ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).addCriterion("has_item", hasItem(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).build(consumer);

    }
}
