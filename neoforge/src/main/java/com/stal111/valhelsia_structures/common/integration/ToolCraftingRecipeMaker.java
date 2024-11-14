package com.stal111.valhelsia_structures.common.integration;

import com.stal111.valhelsia_structures.common.recipe.ToolCraftingRecipe;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import java.util.List;

/**
 * Axe Crafting Recipe Maker <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.integration.ToolCraftingRecipeMaker
 *
 * @author Valhelsia Team
 * @since 2020.08.12
 */
public class ToolCraftingRecipeMaker {

    public static List<RecipeHolder<CraftingRecipe>> createCraftingRecipes() {
        String group = ValhelsiaStructures.MOD_ID + ".post";
        ClientLevel level = Minecraft.getInstance().level;

        if (level == null) {
            return List.of();
        }

        return level.getRecipeManager().getAllRecipesFor(RecipeType.CRAFTING).stream()
                .filter(recipe -> recipe.value() instanceof ToolCraftingRecipe)
                .map(recipe -> (ToolCraftingRecipe) recipe.value())
                .map(recipe -> {
                    ItemStack output = recipe.result();
                    ResourceLocation id = ValhelsiaStructures.location("jei.axe_crafting." + output.getDescriptionId());

                    return new RecipeHolder<CraftingRecipe>(id, new ShapelessRecipe(group, CraftingBookCategory.BUILDING, output, NonNullList.of(Ingredient.EMPTY, recipe.tool(), recipe.ingredient())));
                })
                .toList();
    }
}
