package com.stal111.valhelsia_structures.data.data;

import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.recipe.AxeCraftingRecipeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

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

        ModBlocks.WOODEN_POSTS.forEach(registryObject -> {
            if (ModBlocks.POST_LOG_MAP.containsKey(registryObject.get())) {
                Block block = ForgeRegistries.BLOCKS.getValue(ModBlocks.POST_LOG_MAP.get(registryObject.get()));

                System.out.println(block.getRegistryName());
                if (block != null) {
                    new AxeCraftingRecipeBuilder(Ingredient.fromItems(block), registryObject.get().asItem(), 2)
                            .addCriterion("has_item", hasItem(block))
                            .build(consumer);
                }
            }
        });

        ShapedRecipeBuilder.shapedRecipe(ModBlocks.METAL_FRAMED_GLASS.get(), 8).patternLine("###").patternLine("#X#").patternLine("###").key('#', Blocks.GLASS).key('X', Tags.Items.INGOTS_IRON).addCriterion("has_item", hasItem(Blocks.GLASS)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.METAL_FRAMED_GLASS_PANE.get(), 16).patternLine("###").patternLine("###").key('#', ModBlocks.METAL_FRAMED_GLASS.get()).addCriterion("has_item", hasItem(ModBlocks.METAL_FRAMED_GLASS.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.PAPER_WALL.get(), 2).patternLine("#X#").patternLine("#X#").patternLine("#X#").key('#', Items.BAMBOO).key('X', Items.PAPER).addCriterion("has_item", hasItem(Items.BAMBOO)).build(consumer);
    }
}
