package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.common.recipe.AxeCraftingRecipe;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.core.registry.RegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.MappedRegistryHelper;

/**
 * Recipes <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModRecipes
 *
 * @author Valhelsia Team
 * @since 2020-06-01
 */

public class ModRecipes implements RegistryClass {

    public static final MappedRegistryHelper<RecipeSerializer<?>> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getMappedHelper(ForgeRegistries.Keys.RECIPE_SERIALIZERS);

    public static final RegistryObject<RecipeSerializer<AxeCraftingRecipe>> AXE_CRAFTING_SERIALIZER = HELPER.register("axe_crafting", AxeCraftingRecipe.Serializer::new);

}
