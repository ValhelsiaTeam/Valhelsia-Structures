package com.stal111.valhelsia_structures.integration;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.recipe.AxeCraftingRecipe;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.StackList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

/**
 * Axe Crafting Recipe Maker
 * Valhelsia Structures - com.stal111.valhelsia_structures.integration.AxeCraftingRecipeMaker
 *
 * @author Valhelsia Team
 * @version 16.0.2
 * @since 2020.08.12
 */

public class AxeCraftingRecipeMaker {

    public static List<ShapelessRecipe> createTippedArrowRecipes() {
        List<ShapelessRecipe> recipes = new ArrayList<>();
        String group = ValhelsiaStructures.MOD_ID + ".post";

        AxeCraftingRecipe.LOG_POST_MAP.forEach((block, block2) -> {
            Ingredient axeIngredient = Ingredient.fromItemListStream(convertItemListToItemStackList(ForgeRegistries.ITEMS.getValues()).stream().filter(stack -> stack.getItem() instanceof AxeItem).map(stack -> new StackList(Collections.singleton(stack))));
            Ingredient logIngredient = Ingredient.fromStacks(new ItemStack(block.asItem()));

            ItemStack output = new ItemStack(block2.asItem(), 2);
            ResourceLocation id = new ResourceLocation(ValhelsiaStructures.MOD_ID, "jei.axe_crafting." + output.getTranslationKey());

            System.out.println(Arrays.toString(axeIngredient.getMatchingStacks()));

            ShapelessRecipe recipe = new ShapelessRecipe(id, group, output, NonNullList.from(Ingredient.EMPTY, axeIngredient, logIngredient));

            recipes.add(recipe);
        });
        return recipes;
    }

    private static List<ItemStack> convertItemListToItemStackList(Collection<Item> list) {
        List<ItemStack> newList = new ArrayList<>();

        list.forEach(item -> newList.add(new ItemStack(item)));

        return newList;
    }
}
