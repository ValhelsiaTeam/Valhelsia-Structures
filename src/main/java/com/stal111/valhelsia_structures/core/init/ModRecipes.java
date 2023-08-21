package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.common.recipe.AxeCraftingRecipe;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryClass;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;
import net.valhelsia.valhelsia_core.api.common.registry.helper.MappedRegistryHelper;

/**
 * Recipes <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModRecipes
 *
 * @author Valhelsia Team
 * @since 2020-06-01
 */
public class ModRecipes implements RegistryClass {

    public static final MappedRegistryHelper<RecipeSerializer<?>> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(ForgeRegistries.Keys.RECIPE_SERIALIZERS);

    public static final RegistryEntry<RecipeSerializer<AxeCraftingRecipe>> AXE_CRAFTING_SERIALIZER = HELPER.register("axe_crafting", AxeCraftingRecipe.Serializer::new);

}
