package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.recipe.AxeCraftingRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.ToolType;
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

    public static final DeferredRegister<IRecipeSerializer<?>> SERIALIZERS = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<AxeCraftingRecipe.Serializer> AXE_CRAFTING_SERIALIZER = register("axe_crafting", AxeCraftingRecipe.SERIALIZER);

    @SuppressWarnings("SameParameterValue")
    @Nonnull
    private static <T extends IRecipeSerializer<?>> RegistryObject<T> register(@Nonnull String name, @Nonnull T serializer) {
        return SERIALIZERS.register(name, () -> serializer);
    }
}
