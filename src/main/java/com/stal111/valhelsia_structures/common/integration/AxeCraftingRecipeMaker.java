package com.stal111.valhelsia_structures.common.integration;

import com.stal111.valhelsia_structures.common.recipe.AxeCraftingRecipe;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Axe Crafting Recipe Maker <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.integration.AxeCraftingRecipeMaker
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.2.0
 * @since 2020.08.12
 */
public class AxeCraftingRecipeMaker {

    public static List<CraftingRecipe> createAxeCraftingRecipes() {
        String group = ValhelsiaStructures.MOD_ID + ".post";
        ClientLevel level = Minecraft.getInstance().level;

        if (level == null) {
            return List.of();
        }

        Ingredient axeIngredient = Ingredient.of(ForgeRegistries.ITEMS.getValues().stream().map(ItemStack::new).filter(stack -> stack.canPerformAction(ToolActions.AXE_DIG) && !stack.is(ModTags.Items.AXE_CRAFTING_BLACKLISTED)));

        return level.getRecipeManager().getAllRecipesFor(RecipeType.CRAFTING).stream()
                .filter(recipe -> recipe instanceof AxeCraftingRecipe)
                .map(recipe -> (AxeCraftingRecipe) recipe)
                .map(recipe -> {
                    ItemStack output = recipe.getOutput();
                    ResourceLocation id = new ResourceLocation(ValhelsiaStructures.MOD_ID, "jei.axe_crafting." + output.getDescriptionId());

                    return new ShapelessRecipe(id, group, output, NonNullList.of(Ingredient.EMPTY, axeIngredient, recipe.getInput()));
                })
                .collect(Collectors.toList());
    }
}
