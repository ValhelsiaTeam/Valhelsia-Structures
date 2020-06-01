package com.stal111.valhelsia_structures.recipe;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

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
public class AxeCraftingRecipe implements ICraftingRecipe {

    public static final IRecipeType<AxeCraftingRecipe> RECIPE_TYPE = new IRecipeType<AxeCraftingRecipe>() {
        @Override
        public String toString() {
            return ValhelsiaStructures.MOD_ID + ":axe_crafting";
        }
    };

    public static final Serializer SERIALIZER = new Serializer();

    private final ResourceLocation recipeId;
    private final String group;
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack result;

    public AxeCraftingRecipe(@Nonnull ResourceLocation recipeId, String group, ItemStack result, NonNullList<Ingredient> ingredients) {
        this.recipeId = recipeId;
        this.group = group;
        this.result = result;
        this.ingredients = ingredients;
    }

    @Override
    public boolean matches(@Nonnull CraftingInventory inventory, @Nonnull World world) {
        ItemStack axe = ItemStack.EMPTY;
        List<ItemStack> list = Lists.newArrayList();

        for (Ingredient ingredient : ingredients) {
            for (int i = 0; i < inventory.getSizeInventory(); ++i) {
                ItemStack stack = inventory.getStackInSlot(i);
                if (!stack.isEmpty()) {
                    if (stack.getItem() instanceof AxeItem) {
                        if (!axe.isEmpty()) {
                            return false;
                        }
                        axe = stack;
                        list.add(stack);
                    } else if (ingredient.test(stack)) {
                        list.add(stack);
                    }
                }
            }
        }

        if (list.size() != ingredients.size()) {
            return false;
        }

        return !axe.isEmpty() && !list.isEmpty();
    }

    @Override
    @Nonnull
    public ItemStack getCraftingResult(@Nonnull CraftingInventory inventory) {
        return result.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= this.ingredients.size() + 1;
    }

    @Override
    @Nonnull
    public ItemStack getRecipeOutput() {
        return result;
    }

    @Override
    @Nonnull
    public NonNullList<ItemStack> getRemainingItems(@Nonnull CraftingInventory inventory) {
        NonNullList<ItemStack> ret = NonNullList.withSize(inventory.getSizeInventory(), ItemStack.EMPTY);

        for(int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if(stack.getItem() instanceof AxeItem) {
                int damage = stack.getDamage();
                if (damage > 0) {
                    ItemStack damaged = stack.copy();
                    stack.setDamage(damage - 1);
                    ret.set(i, damaged);
                }
            }
        }

        return ret;
    }

    @Override
    @Nonnull
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;

//        NonNullList<Ingredient> list = NonNullList.withSize(ingredients.size() + 1, Ingredient.EMPTY);
//
//        // Not sure how to make this work better:
//        list.set(0, Ingredient.fromStacks(new ItemStack(Items.IRON_AXE)));
//
//        int index = 1;
//        for (Ingredient ingredient : ingredients) {
//            list.set(index++, ingredient);
//            ValhelsiaStructures.LOGGER.debug(ingredient);
//        }
//
//        return list;
    }

    @Override
    @Nonnull
    public ResourceLocation getId() {
        return recipeId;
    }

    @Override
    @Nonnull
    public IRecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    @Nonnull
    public IRecipeType<?> getType() {
        return RECIPE_TYPE;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    @Nonnull
    @Override
    public String getGroup() {
        return this.group;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AxeCraftingRecipe> {

        @Override
        @Nonnull
        public AxeCraftingRecipe read(@Nonnull ResourceLocation recipeId, @Nonnull JsonObject json) {
            String group = JSONUtils.getString(json, "group", "");
            NonNullList<Ingredient> ingredients = readIngredients(JSONUtils.getJsonArray(json, "ingredients"));
            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for axe crafting recipe.");
            } else if (ingredients.size() > 8) {
                throw new JsonParseException("Too many ingredients for axe crafting recipe - the max is 8.");
            } else {
                ItemStack result = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
                return new AxeCraftingRecipe(recipeId, group, result, ingredients);
            }
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray jsonArray) {
            NonNullList<Ingredient> ingredients = NonNullList.create();

            // Should be any AxeItem, probably need to make a custom ingredient for that.
            ingredients.add(Ingredient.fromItems(Items.IRON_AXE));

            for(int i = 0; i < jsonArray.size(); ++i) {
                Ingredient ingredient = Ingredient.deserialize(jsonArray.get(i));
                if (!ingredient.hasNoMatchingItems()) {
                    ingredients.add(ingredient);
                }
            }

            return ingredients;
        }


        @Override
        @Nullable
        public AxeCraftingRecipe read(@Nonnull ResourceLocation recipeId, @Nonnull PacketBuffer buffer) {
            String group = buffer.readString(32767);
            int i = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(i, Ingredient.EMPTY);

            for(int j = 0; j < ingredients.size(); ++j) {
                ingredients.set(j, Ingredient.read(buffer));
            }

            ItemStack result = buffer.readItemStack();
            return new AxeCraftingRecipe(recipeId, group, result, ingredients);
        }

        @Override
        public void write(@Nonnull PacketBuffer buffer, @Nonnull AxeCraftingRecipe recipe) {
            buffer.writeString(recipe.group);
            buffer.writeVarInt(recipe.ingredients.size());

            for(Ingredient ingredient : recipe.ingredients) {
                ingredient.write(buffer);
            }

            buffer.writeItemStack(recipe.result);
        }
    }
}
