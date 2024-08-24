package com.stal111.valhelsia_structures.common.integration;

import com.stal111.valhelsia_structures.common.recipe.AxeCraftingRecipe;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.ItemAbilities;

import java.util.List;

/**
 * Axe Crafting Recipe Maker <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.integration.AxeCraftingRecipeMaker
 *
 * @author Valhelsia Team
 * @since 2020.08.12
 */
public class AxeCraftingRecipeMaker {

    public static List<RecipeHolder<CraftingRecipe>> createAxeCraftingRecipes() {
        String group = ValhelsiaStructures.MOD_ID + ".post";
        ClientLevel level = Minecraft.getInstance().level;

        if (level == null) {
            return List.of();
        }

        Ingredient axeIngredient = Ingredient.of(BuiltInRegistries.ITEM.stream().map(ItemStack::new).filter(stack -> stack.canPerformAction(ItemAbilities.AXE_DIG) && !stack.is(ModTags.Items.AXE_CRAFTING_BLACKLISTED)));

        return level.getRecipeManager().getAllRecipesFor(RecipeType.CRAFTING).stream()
                .filter(recipe -> recipe.value() instanceof AxeCraftingRecipe)
                .map(recipe -> (AxeCraftingRecipe) recipe.value())
                .map(recipe -> {
                    ItemStack output = recipe.getOutput();
                    ResourceLocation id = ValhelsiaStructures.location("jei.axe_crafting." + output.getDescriptionId());

                    return new RecipeHolder<CraftingRecipe>(id, new ShapelessRecipe(group, CraftingBookCategory.BUILDING, output, NonNullList.of(Ingredient.EMPTY, axeIngredient, recipe.getInput())));
                })
                .toList();
    }
}
