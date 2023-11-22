package com.stal111.valhelsia_structures.common.integration;

/**
 * Valhelsia Structures JEI Plugin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.integration.ValhelsiaStructuresJEIPlugin
 *
 * @author Valhelsia Team
 * @since 2020.08.12
 */
//@JeiPlugin
//public class ValhelsiaStructuresJEIPlugin implements IModPlugin {
//
//    private static final ResourceLocation ID = new ResourceLocation(ValhelsiaStructures.MOD_ID, "main");
//
//    @Override
//    @NotNull
//    public ResourceLocation getPluginUid() {
//        return ID;
//    }
//
//    @Override
//    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
//        jeiRuntime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, ModCreativeModeTabs.HIDDEN_ITEMS.stream().map(supplier -> new ItemStack(supplier.get())).toList());
//    }
//
//    @Override
//    public void registerRecipes(IRecipeRegistration registration) {
//        registration.addRecipes(RecipeTypes.CRAFTING, AxeCraftingRecipeMaker.createAxeCraftingRecipes());
//    }
//}