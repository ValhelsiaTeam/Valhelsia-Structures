package com.stal111.valhelsia_structures.common.integration;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModCreativeModeTabs;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Valhelsia Structures JEI Plugin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.integration.ValhelsiaStructuresJEIPlugin
 *
 * @author Valhelsia Team
 * @since 2020.08.12
 */
@JeiPlugin
public class ValhelsiaStructuresJEIPlugin implements IModPlugin {

    private static final ResourceLocation ID = new ResourceLocation(ValhelsiaStructures.MOD_ID, "main");

    @Override
    @NotNull
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        jeiRuntime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, ModCreativeModeTabs.HIDDEN_ITEMS.stream().map(supplier -> new ItemStack(supplier.get())).toList());
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(RecipeTypes.CRAFTING, AxeCraftingRecipeMaker.createAxeCraftingRecipes());
    }
}