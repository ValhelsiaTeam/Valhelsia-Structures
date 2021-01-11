package com.stal111.valhelsia_structures.data.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;

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
    }
}
