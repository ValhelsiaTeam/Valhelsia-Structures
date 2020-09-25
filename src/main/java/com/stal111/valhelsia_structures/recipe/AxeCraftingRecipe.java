package com.stal111.valhelsia_structures.recipe;

import com.google.gson.JsonObject;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.init.ModRecipes;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Axe Crafting Recipe
 * Valhelsia Structures - com.stal111.valhelsia_structures.recipe.AxeCraftingRecipe
 *
 * A crafting recipe that can use any axe (that extends AxeItem) and a number of other ingredients in a shapeless
 * form. The axe loses one durability per craft but is returned.
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2020-06-01
 */

public class AxeCraftingRecipe extends SpecialRecipe {

    private final Ingredient input;
    private final ItemStack output;
    private final int count;

    public static final IRecipeType<AxeCraftingRecipe> RECIPE_TYPE = new IRecipeType<AxeCraftingRecipe>() {
        @Override
        public String toString() {
            return ValhelsiaStructures.MOD_ID + ":axe_crafting";
        }
    };

    public AxeCraftingRecipe(ResourceLocation recipeId, Ingredient input, ItemStack output) {
        super(recipeId);
        this.input = input;
        this.output = output;
        this.count = output.getCount();
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        int axeSlot = -1;
        ItemStack stack = null;

        for (int slot = 0; slot < inv.getSizeInventory(); slot++) {
            if (inv.getStackInSlot(slot).getItem() instanceof AxeItem) {
                axeSlot = slot;
                break;
            }
        }

        if (axeSlot == -1) {
            return false;
        }

        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack1 = inv.getStackInSlot(i);
            if (i != axeSlot && !stack1.isEmpty()) {
                if (input.test(stack1) && (stack == null || stack.isItemEqual(stack1))) {
                    stack = stack1;
                } else {
                    return false;
                }
            }
        }

        return stack != null;
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {
        int logCount = 0;

        for(int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (input.test(stack)) {
                logCount++;
            }
        }

        return new ItemStack(output.getItem(), logCount * count);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        int logCount = 0;

        for(int i = 0; i < nonnulllist.size(); i++) {
            if (input.test(inv.getStackInSlot(i))) {
                logCount++;
            }
        }


        for(int i = 0; i < nonnulllist.size(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack.getItem() instanceof AxeItem) {
                ItemStack stack1 = stack.copy();
                stack1.setDamage(stack1.getDamage() + logCount);

                if (!(stack1.getDamage() >= stack1.getMaxDamage())) {
                    nonnulllist.set(i, stack1);
                }
            }
        }

        return nonnulllist;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.AXE_CRAFTING_SERIALIZER.get();
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AxeCraftingRecipe> {

        @Override
        public AxeCraftingRecipe read(ResourceLocation recipeId, JsonObject json) {
            Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));
            ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));
            return new AxeCraftingRecipe(recipeId, input, output);
        }

        @Override
        public AxeCraftingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            Ingredient input = Ingredient.read(buffer);
            ItemStack output = buffer.readItemStack();
            return new AxeCraftingRecipe(recipeId, input, output);
        }

        @Override
        public void write(PacketBuffer buffer, AxeCraftingRecipe recipe) {
            recipe.input.write(buffer);
            buffer.writeItemStack(recipe.output);
        }
    }
}
