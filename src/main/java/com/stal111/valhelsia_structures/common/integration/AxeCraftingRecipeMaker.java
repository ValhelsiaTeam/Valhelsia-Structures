package com.stal111.valhelsia_structures.common.integration;

import com.stal111.valhelsia_structures.common.recipe.AxeCraftingRecipe;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

/**
 * Axe Crafting Recipe Maker <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.integration.AxeCraftingRecipeMaker
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.1.0
 * @since 2020.08.12
 */
public class AxeCraftingRecipeMaker {

    public static List<ShapelessRecipe> createAxeCraftingRecipes() {
        List<ShapelessRecipe> recipes = new ArrayList<>();
        String group = ValhelsiaStructures.MOD_ID + ".post";

        if (Minecraft.getInstance().level != null) {
            Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(RecipeType.CRAFTING).forEach(craftingRecipe -> {
                if (craftingRecipe instanceof AxeCraftingRecipe axeRecipe) {
                    Ingredient axeIngredient = Ingredient.of(ForgeRegistries.ITEMS.getValues().stream().filter(item -> item instanceof AxeItem && !item.builtInRegistryHolder().is(ModTags.Items.AXE_CRAFTING_BLACKLISTED)).map(ItemStack::new));
                    ItemStack output = axeRecipe.getOutput();
                    ResourceLocation id = new ResourceLocation(ValhelsiaStructures.MOD_ID, "jei.axe_crafting." + output.getDescriptionId());

                    ShapelessRecipe recipe = new ShapelessRecipe(id, group, output, NonNullList.of(Ingredient.EMPTY, axeIngredient, axeRecipe.getInput()));

                    recipes.add(recipe);
                }
            });
        }

        return recipes;
    }
}
