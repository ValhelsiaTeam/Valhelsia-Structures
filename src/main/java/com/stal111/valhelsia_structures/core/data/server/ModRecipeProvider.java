package com.stal111.valhelsia_structures.core.data.server;

import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.common.recipe.AxeCraftingRecipeBuilder;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
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
 * @version 1.17.1-0.1.0
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

        ModBlocks.HELPER.getDeferredRegister().getEntries().forEach(registryObject -> {
            if (registryObject.get() instanceof PostBlock postBlock) {
                new AxeCraftingRecipeBuilder(Ingredient.of(postBlock.getLogBlock()), postBlock.asItem(), 2).unlocks("has_item", has(postBlock.getLogBlock())).save(consumer);
            } else  if (registryObject.get() instanceof CutPostBlock) {
                Block postBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ValhelsiaStructures.MOD_ID, Objects.requireNonNull(registryObject.get().getRegistryName()).getPath().substring(4)));

                if (postBlock != null) {
                    new AxeCraftingRecipeBuilder(Ingredient.of(postBlock), registryObject.get().asItem(), 4).unlocks("has_item", has(postBlock)).save(consumer);
                }
            }
        });

        ShapedRecipeBuilder.shaped(ModBlocks.METAL_FRAMED_GLASS.get(), 8).pattern("###").pattern("#X#").pattern("###").define('#', Blocks.GLASS).define('X', Tags.Items.INGOTS_IRON).unlockedBy("has_item", has(Blocks.GLASS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.METAL_FRAMED_GLASS_PANE.get(), 16).pattern("###").pattern("###").define('#', ModBlocks.METAL_FRAMED_GLASS.get()).unlockedBy("has_item", has(ModBlocks.METAL_FRAMED_GLASS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.PAPER_WALL.get(), 2).pattern("#X#").pattern("#X#").pattern("#X#").define('#', Items.BAMBOO).define('X', Items.PAPER).unlockedBy("has_item", has(Items.BAMBOO)).save(consumer);
//        ShapedRecipeBuilder.shaped(ModBlocks.LAPIDIFIED_JUNGLE_LOG.get(), 8).patternLine("###").patternLine("#X#").patternLine("###").key('#', Blocks.JUNGLE_LOG).key('X', Ingredient.fromItems(Blocks.VINE, ModBlocks.HANGING_VINES.get())).addCriterion("has_item", hasItem(Blocks.VINE)).build(consumer);
//        ShapedRecipeBuilder.shaped(ModBlocks.LAPIDIFIED_JUNGLE_WOOD.get(), 3).patternLine("##").patternLine("##").key('#', ModBlocks.LAPIDIFIED_JUNGLE_LOG.get()).addCriterion("has_item", hasItem(ModBlocks.LAPIDIFIED_JUNGLE_LOG.get())).build(consumer);
//        ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get(), 4).addIngredient(ModTags.Items.LAPIDIFIED_JUNGLE_LOGS).addCriterion("has_item", hasItem(ModTags.Items.LAPIDIFIED_JUNGLE_LOGS)).build(consumer);
//        ShapedRecipeBuilder.shaped(ModBlocks.LAPIDIFIED_JUNGLE_SLAB.get(), 6).patternLine("###").key('#', ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).addCriterion("has_item", hasItem(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).build(consumer);
//        ShapedRecipeBuilder.shaped(ModBlocks.LAPIDIFIED_JUNGLE_STAIRS.get(), 4).patternLine("#  ").patternLine("## ").patternLine("###").key('#', ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).addCriterion("has_item", hasItem(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).build(consumer);
//        ShapedRecipeBuilder.shaped(ModBlocks.LAPIDIFIED_JUNGLE_PRESSURE_PLATE.get()).patternLine("##").key('#', ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).addCriterion("has_item", hasItem(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).build(consumer);
//        ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.LAPIDIFIED_JUNGLE_BUTTON.get()).addIngredient(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).addCriterion("has_item", hasItem(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).build(consumer);
//        ShapedRecipeBuilder.shaped(ModBlocks.LAPIDIFIED_JUNGLE_FENCE.get(), 3).patternLine("#X#").patternLine("#X#").key('#', ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).key('X', Tags.Items.RODS_WOODEN).addCriterion("has_item", hasItem(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).build(consumer);
//        ShapedRecipeBuilder.shaped(ModBlocks.LAPIDIFIED_JUNGLE_FENCE_GATE.get()).patternLine("#X#").patternLine("#X#").key('#', Tags.Items.RODS_WOODEN).key('X', ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()).addCriterion("has_item", hasItem(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get())).build(consumer);

        ShapedRecipeBuilder.shaped(ModBlocks.BONE_PILE.get(), 3).pattern("###").define('#', Items.BONE).group("bone_pile").unlockedBy("has_item", has(Items.BONE)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.BONE_PILE.get(), 9).requires(ModBlocks.BONE_PILE_BLOCK.get()).group("bone_pile").unlockedBy("has_item", has(ModBlocks.BONE_PILE_BLOCK.get())).save(consumer, "bone_pile_from_bone_pile_block");
        ShapedRecipeBuilder.shaped(ModBlocks.BONE_PILE_BLOCK.get()).pattern("###").pattern("###").pattern("###").define('#', Items.BONE).group("bone_pile_block").unlockedBy("has_item", has(Items.BONE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.BONE_PILE_BLOCK.get()).pattern("###").pattern("###").pattern("###").define('#', ModBlocks.BONE_PILE.get()).group("bone_pile_block").unlockedBy("has_item", has(ModBlocks.BONE_PILE.get())).save(consumer, "bone_pile_block_from_bone_piles");
        ShapedRecipeBuilder.shaped(ModBlocks.GLAZED_JAR.get()).pattern("# #").pattern(" # ").define('#', Blocks.TERRACOTTA).group("jar").unlockedBy("has_item", has(Blocks.TERRACOTTA)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.BIG_GLAZED_JAR.get()).pattern("# #").pattern("# #").pattern(" # ").define('#', Blocks.TERRACOTTA).group("big_jar").unlockedBy("has_item", has(Blocks.TERRACOTTA)).save(consumer);

        ModBlocks.COLORED_GLAZED_JARS.forEach(blockRegistryObject -> {
            String name = blockRegistryObject.get().getRegistryName().getPath();
            Block block = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name.substring(0, name.length() - 11) + "_terracotta")));
            ShapedRecipeBuilder.shaped(blockRegistryObject.get()).pattern("# #").pattern(" # ").define('#', block).group("jar").unlockedBy("has_item", has(block)).save(consumer);
        });

        ModBlocks.BIG_COLORED_GLAZED_JARS.forEach(blockRegistryObject -> {
            String name = blockRegistryObject.get().getRegistryName().getPath();
            Block block = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name.substring(4, name.length() - 11) + "_terracotta")));
            ShapedRecipeBuilder.shaped(blockRegistryObject.get()).pattern("# #").pattern("# #").pattern(" # ").define('#', block).group("big_jar").unlockedBy("has_item", has(block)).save(consumer);
        });

        //Smelting Recipes
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.GLAZED_JAR.get()), ModBlocks.CRACKED_GLAZED_JAR.get(), 0.1F, 200).unlockedBy("has_item", has(ModBlocks.GLAZED_JAR.get())).save(consumer, "valhelsia_structures:smelting/cracked_glazed_jar");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.BIG_GLAZED_JAR.get()), ModBlocks.CRACKED_BIG_GLAZED_JAR.get(), 0.1F, 200).unlockedBy("has_item", has(ModBlocks.BIG_GLAZED_JAR.get())).save(consumer, "valhelsia_structures:smelting/cracked_big_glazed_jar");
    }
}
