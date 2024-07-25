package com.stal111.valhelsia_structures.common.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
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
public class AxeCraftingRecipeBuilder implements RecipeBuilder {

    private final RecipeCategory category;
    private final Ingredient input;
    private final Item result;
    private final int count;

    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public AxeCraftingRecipeBuilder(RecipeCategory category, Ingredient input, ItemLike result, int count) {
        this.category = category;
        this.input = input;
        this.result = result.asItem();
        this.count = count;
    }

    @Override
    public void save(@NotNull RecipeOutput output) {
        this.save(output, BuiltInRegistries.ITEM.getKey(this.result));
    }

    @Override
    public void save(@NotNull RecipeOutput output, @NotNull String location) {
        ResourceLocation resourcelocation = BuiltInRegistries.ITEM.getKey(this.result);
        if (ResourceLocation.parse(location).equals(resourcelocation)) {
            throw new IllegalStateException("Axe Crafting Recipe " + location + " should remove its 'save' argument");
        } else {
            this.save(output, ResourceLocation.parse(location));
        }
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

    public void save(RecipeOutput output, @NotNull ResourceLocation id) {
        this.ensureValid(id);

        AxeCraftingRecipe recipe = new AxeCraftingRecipe(this.input, new ItemStack(this.result, this.count), RecipeBuilder.determineBookCategory(this.category));

        Advancement.Builder builder = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);

        this.criteria.forEach(builder::addCriterion);

        output.accept(id, recipe, builder.build(id.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation id) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }
}
