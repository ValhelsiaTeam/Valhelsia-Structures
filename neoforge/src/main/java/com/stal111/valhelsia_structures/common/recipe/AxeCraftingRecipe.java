package com.stal111.valhelsia_structures.common.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.core.init.ModRecipes;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

/**
 * Axe Crafting Recipe <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.recipe.AxeCraftingRecipe
 * <p>
 * A crafting recipe that can use any axe (that extends {@link AxeItem}) and a number of other ingredients in a shapeless
 * form. The axe loses one durability per craft but is returned.
 *
 * @author Valhelsia Team
 * @since 2020-06-01
 */
public record AxeCraftingRecipe(
        CraftingBookCategory category,
        Ingredient ingredient,
        ItemStack result) implements CraftingRecipe {

    @Override
    public boolean matches(CraftingInput input, @Nonnull Level level) {
        int axeSlot = -1;
        ItemStack stack = null;

        for (int slot = 0; slot < input.size(); slot++) {
            ItemStack item = input.getItem(slot);

            if (item.getItem() instanceof AxeItem && !item.is(ModTags.Items.AXE_CRAFTING_BLACKLISTED)) {
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
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider registries) {
        return this.result;
    }

    @Nonnull
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.AXE_CRAFTING_SERIALIZER.get();
    }

    public static class Serializer implements RecipeSerializer<AxeCraftingRecipe> {

        private static final MapCodec<AxeCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                CraftingBookCategory.CODEC.fieldOf("category").forGetter(AxeCraftingRecipe::category),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(AxeCraftingRecipe::ingredient),
                ItemStack.CODEC.fieldOf("result").forGetter(AxeCraftingRecipe::result)
        ).apply(instance, AxeCraftingRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, AxeCraftingRecipe> STREAM_CODEC = StreamCodec.composite(
                CraftingBookCategory.STREAM_CODEC,
                AxeCraftingRecipe::category,
                Ingredient.CONTENTS_STREAM_CODEC,
                AxeCraftingRecipe::ingredient,
                ItemStack.STREAM_CODEC,
                AxeCraftingRecipe::result,
                AxeCraftingRecipe::new
        );

        @Override
        public @NotNull MapCodec<AxeCraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, AxeCraftingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
