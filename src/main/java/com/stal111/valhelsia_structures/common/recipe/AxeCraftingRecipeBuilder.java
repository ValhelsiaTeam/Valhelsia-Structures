package com.stal111.valhelsia_structures.common.recipe;

import com.google.gson.JsonObject;
import com.stal111.valhelsia_structures.core.init.ModRecipes;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Valhelsia Team
 * @since 2021-01-28
 */
public class AxeCraftingRecipeBuilder implements RecipeBuilder {

    private final RecipeCategory category;
    private final Ingredient input;
    private final Item result;
    private final int count;

    private final Advancement.Builder advancement = Advancement.Builder.advancement();
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
        if (new ResourceLocation(location).equals(resourcelocation)) {
            throw new IllegalStateException("Axe Crafting Recipe " + location + " should remove its 'save' argument");
        } else {
            this.save(output, new ResourceLocation(location));
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

        Advancement.Builder builder = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);

        this.criteria.forEach(builder::addCriterion);

        output.accept(new AxeCraftingRecipeBuilder.Result(id, this.result, this.count, this.input, builder.build(id.withPrefix("recipes/" + this.category.getFolderName() + "/"))));
    }

    private void ensureValid(ResourceLocation id) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item output;
        private final int count;
        private final Ingredient input;
        private final AdvancementHolder advancement;


        public Result(ResourceLocation id, Item output, int count, Ingredient input, AdvancementHolder advancement) {
            this.id = id;
            this.output = output;
            this.count = count;
            this.input = input;
            this.advancement = advancement;
        }

        @Override
        public void serializeRecipeData(@Nonnull JsonObject json) {
            json.add("input", this.input.toJson(false));
            JsonObject output = new JsonObject();
            output.addProperty("item", Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(this.output)).toString());
            if (this.count > 1) {
                output.addProperty("count", this.count);
            }

            json.add("output", output);
        }

        @Nonnull
        @Override
        public RecipeSerializer<?> type() {
            return ModRecipes.AXE_CRAFTING_SERIALIZER.get();
        }

        @Nonnull
        @Override
        public ResourceLocation id() {
            return this.id;
        }

        @org.jetbrains.annotations.Nullable
        @Override
        public AdvancementHolder advancement() {
            return this.advancement;
        }
    }
}
