package com.stal111.valhelsia_structures.integration;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.recipe.AxeCraftingRecipe;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.client.Minecraft;
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
 * @version 16.0.3
 * @since 2020.08.12
 */

public class AxeCraftingRecipeMaker {

    public static List<ShapelessRecipe> createAxeCraftingRecipes() {
        List<ShapelessRecipe> recipes = new ArrayList<>();
        String group = ValhelsiaStructures.MOD_ID + ".post";

        Minecraft.getInstance().world.getRecipeManager().getRecipesForType(AxeCraftingRecipe.RECIPE_TYPE).forEach(iRecipe -> {
            Ingredient axeIngredient = Ingredient.fromItemListStream(convertItemListToItemStackList(ForgeRegistries.ITEMS.getValues()).stream().filter(stack -> stack.getItem() instanceof AxeItem && !ModTags.Items.AXE_CRAFTING_BLACKLISTED.contains(stack.getItem())).map(stack -> new StackList(Collections.singleton(stack))));

            ItemStack output = iRecipe.getOutput();
            ResourceLocation id = new ResourceLocation(ValhelsiaStructures.MOD_ID, "jei.axe_crafting." + output.getTranslationKey());

            System.out.println(Arrays.toString(axeIngredient.getMatchingStacks()));

            ShapelessRecipe recipe = new ShapelessRecipe(id, group, output, NonNullList.from(Ingredient.EMPTY, axeIngredient, iRecipe.getInput()));

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
