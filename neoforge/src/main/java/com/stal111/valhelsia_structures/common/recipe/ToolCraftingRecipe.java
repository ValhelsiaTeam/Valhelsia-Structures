package com.stal111.valhelsia_structures.common.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.core.init.ModRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Axe Crafting Recipe <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.recipe.ToolCraftingRecipe
 * <p>
 * A crafting recipe that can use any axe (that extends {@link AxeItem}) and a number of other ingredients in a shapeless
 * form. The axe loses one durability per craft but is returned.
 *
 * @author Valhelsia Team
 * @since 2020-06-01
 */
public record ToolCraftingRecipe(
        CraftingBookCategory category,
        Ingredient ingredient,
        Ingredient tool,
        ItemStack result,
        PlacementInfo placementInfo) implements CraftingRecipe {

    public ToolCraftingRecipe(CraftingBookCategory category, Ingredient ingredient, Ingredient tool, ItemStack result) {
        this(category, ingredient, tool, result, PlacementInfo.create(List.of(ingredient, tool)));
    }

    @Override
    public boolean matches(CraftingInput input, @Nonnull Level level) {
        int axeSlot = -1;
        ItemStack stack = null;

        for (int slot = 0; slot < input.size(); slot++) {
            ItemStack item = input.getItem(slot);

            if (this.tool.test(item)) {
                axeSlot = slot;
                break;
            }
        }

        if (axeSlot == -1) {
            return false;
        }

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack1 = input.getItem(i);
            if (i != axeSlot && !stack1.isEmpty()) {
                if (this.ingredient.test(stack1) && (stack == null || ItemStack.isSameItem(stack, stack1))) {
                    stack = stack1;
                } else {
                    return false;
                }
            }
        }

        return stack != null;
    }

    @Nonnull
    @Override
    public ItemStack assemble(CraftingInput input, @NotNull HolderLookup.Provider lookupProvider) {
        int logCount = 0;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (this.ingredient.test(stack)) {
                logCount++;
            }
        }

        return this.result.copyWithCount(logCount * this.result.getCount());
    }

    @Nonnull
    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> itemStacks = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        int logCount = 0;

        for (int i = 0; i < itemStacks.size(); i++) {
            if (this.ingredient.test(input.getItem(i))) {
                logCount++;
            }
        }

        for (int i = 0; i < itemStacks.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.getItem() instanceof AxeItem) {
                ItemStack stack1 = stack.copy();
                stack1.setDamageValue(stack1.getDamageValue() + logCount);

                if (!(stack1.getDamageValue() >= stack1.getMaxDamage())) {
                    itemStacks.set(i, stack1);
                }
            }
        }

        return itemStacks;
    }

    @Override
    public @NotNull RecipeSerializer<? extends CraftingRecipe> getSerializer() {
        return ModRecipes.TOOL_CRAFTING_SERIALIZER.get();
    }

    @Override
    public @NotNull PlacementInfo placementInfo() {
        return this.placementInfo;
    }

    @Override
    public @NotNull List<RecipeDisplay> display() {
        return CraftingRecipe.super.display();
    }

    public static class Serializer implements RecipeSerializer<ToolCraftingRecipe> {

        private static final MapCodec<ToolCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                CraftingBookCategory.CODEC.optionalFieldOf("category", CraftingBookCategory.MISC).forGetter(ToolCraftingRecipe::category),
                Ingredient.CODEC.fieldOf("ingredient").forGetter(ToolCraftingRecipe::ingredient),
                Ingredient.CODEC.fieldOf("tool").forGetter(ToolCraftingRecipe::tool),
                ItemStack.CODEC.fieldOf("result").forGetter(ToolCraftingRecipe::result)
        ).apply(instance, ToolCraftingRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, ToolCraftingRecipe> STREAM_CODEC = StreamCodec.composite(
                CraftingBookCategory.STREAM_CODEC,
                ToolCraftingRecipe::category,
                Ingredient.CONTENTS_STREAM_CODEC,
                ToolCraftingRecipe::ingredient,
                Ingredient.CONTENTS_STREAM_CODEC,
                ToolCraftingRecipe::tool,
                net.minecraft.world.item.ItemStack.STREAM_CODEC,
                ToolCraftingRecipe::result,
                ToolCraftingRecipe::new
        );

        @Override
        public @NotNull MapCodec<ToolCraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, ToolCraftingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
