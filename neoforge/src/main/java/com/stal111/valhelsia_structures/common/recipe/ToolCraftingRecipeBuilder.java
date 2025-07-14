package com.stal111.valhelsia_structures.common.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Valhelsia Team
 * @since 2021-01-28
 */
public class ToolCraftingRecipeBuilder implements RecipeBuilder {

    private final RecipeCategory category;
    private final Ingredient input;
    private final Ingredient tool;
    private final Item result;
    private final int count;

    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public ToolCraftingRecipeBuilder(RecipeCategory category, Ingredient input, Ingredient tool, ItemLike result, int count) {
        this.category = category;
        this.input = input;
        this.tool = tool;
        this.result = result.asItem();
        this.count = count;
    }

    @Override
    public @NotNull RecipeBuilder unlockedBy(@NotNull String criterionName, @NotNull Criterion<?> criterion) {
        this.criteria.put(criterionName, criterion);

        return this;
    }

    @Nonnull
    @Override
    public RecipeBuilder group(@Nullable String groupName) {
        return this;
    }

    @Nonnull
    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> resourceKey) {
        this.ensureValid(resourceKey);

        Advancement.Builder builder = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceKey))
                .rewards(AdvancementRewards.Builder.recipe(resourceKey))
                .requirements(AdvancementRequirements.Strategy.OR);

        this.criteria.forEach(builder::addCriterion);

        ToolCraftingRecipe recipe = new ToolCraftingRecipe(RecipeBuilder.determineBookCategory(this.category), this.input, this.tool, new ItemStack(this.result, this.count));

        output.accept(resourceKey, recipe, builder.build(resourceKey.location().withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceKey<Recipe<?>> recipe) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipe.location());
        }
    }
}
