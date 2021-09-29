package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.recipe.AxeCraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

/**
 * Recipes
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModRecipes
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2020-06-01
 */

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<RecipeSerializer<AxeCraftingRecipe>> AXE_CRAFTING_SERIALIZER = register("axe_crafting", new AxeCraftingRecipe.Serializer());

    @SuppressWarnings("SameParameterValue")
    @Nonnull
    private static <T extends Recipe<?>> RegistryObject<RecipeSerializer<T>> register(@Nonnull String name, @Nonnull RecipeSerializer<T> serializer) {
        return SERIALIZERS.register(name, () -> serializer);
    }
}
