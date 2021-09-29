package com.stal111.valhelsia_structures.integration;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * Valhelsia Structures JEI Plugin
 * Valhelsia Structures - com.stal111.valhelsia_structures.integration.ValhelsiaStructuresJEIPlugin
 *
 * @author Valhelsia Team
 * @version 16.0.4
 * @since 2020.08.12
 */
@JeiPlugin
public class ValhelsiaStructuresJEIPlugin implements IModPlugin {

    private static final ResourceLocation ID = new ResourceLocation(ValhelsiaStructures.MOD_ID, "main");

    @Override
    @Nonnull
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(AxeCraftingRecipeMaker.createAxeCraftingRecipes(), VanillaRecipeCategoryUid.CRAFTING);
    }
}