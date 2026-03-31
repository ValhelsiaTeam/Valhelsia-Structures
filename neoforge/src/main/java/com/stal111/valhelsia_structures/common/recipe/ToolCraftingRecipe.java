package com.stal111.valhelsia_structures.common.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
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
public class ToolCraftingRecipe extends NormalCraftingRecipe {

    private static final MapCodec<ToolCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Recipe.CommonInfo.MAP_CODEC.forGetter(o -> o.commonInfo),
            CraftingRecipe.CraftingBookInfo.MAP_CODEC.forGetter(o -> o.bookInfo),
            Ingredient.CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
            Ingredient.CODEC.fieldOf("tool").forGetter(recipe -> recipe.tool),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
    ).apply(instance, ToolCraftingRecipe::new));

    private static final StreamCodec<RegistryFriendlyByteBuf, ToolCraftingRecipe> STREAM_CODEC = StreamCodec.composite(
            Recipe.CommonInfo.STREAM_CODEC,
            recipe -> recipe.commonInfo,
            CraftingRecipe.CraftingBookInfo.STREAM_CODEC,
            recipe -> recipe.bookInfo,
            Ingredient.CONTENTS_STREAM_CODEC,
            recipe -> recipe.ingredient,
            Ingredient.CONTENTS_STREAM_CODEC,
            recipe -> recipe.tool,
            ItemStackTemplate.STREAM_CODEC,
            recipe -> recipe.result,
            ToolCraftingRecipe::new
    );

    public static final RecipeSerializer<ToolCraftingRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);

    private final Ingredient ingredient;
    private final Ingredient tool;
    private final ItemStackTemplate result;

    public ToolCraftingRecipe(Recipe.CommonInfo commonInfo, CraftingRecipe.CraftingBookInfo bookInfo, Ingredient ingredient, Ingredient tool, ItemStackTemplate result) {
        super(commonInfo, bookInfo);
        this.ingredient = ingredient;
        this.tool = tool;
        this.result = result;
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
    public ItemStack assemble(CraftingInput input) {
        int logCount = 0;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (this.ingredient.test(stack)) {
                logCount++;
            }
        }

        return this.result.withCount(logCount * this.result.count()).create();
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
    public RecipeSerializer<? extends NormalCraftingRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    protected PlacementInfo createPlacementInfo() {
        return PlacementInfo.create(List.of(this.ingredient, this.tool));
    }

    @Override
    public @NotNull List<RecipeDisplay> display() {
        return List.of(new ShapelessCraftingRecipeDisplay(
                List.of(this.ingredient.display(), this.tool.display()),
                new SlotDisplay.ItemStackSlotDisplay(this.result),
                new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE)
        ));
    }
}
