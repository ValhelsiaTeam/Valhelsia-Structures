package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.recipe.AxeCraftingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
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
    // TODO: Add JEI compatibility for our custom crafting.

    public static final DeferredRegister<IRecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<AxeCraftingRecipe>> AXE_CRAFTING_SERIALIZER = register("axe_crafting", new AxeCraftingRecipe.Serializer());

    @SuppressWarnings("SameParameterValue")
    @Nonnull
    private static <T extends IRecipe<?>> RegistryObject<IRecipeSerializer<T>> register(@Nonnull String name, @Nonnull IRecipeSerializer<T> serializer) {
        return SERIALIZERS.register(name, () -> serializer);
    }
}
