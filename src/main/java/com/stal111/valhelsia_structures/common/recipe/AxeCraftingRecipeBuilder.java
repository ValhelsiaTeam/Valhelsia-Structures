package com.stal111.valhelsia_structures.common.recipe;

import com.google.gson.JsonObject;
import com.stal111.valhelsia_structures.core.init.ModRecipes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Axe Crafting Recipe Builder <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.recipe.AxeCraftingRecipeBuilder
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-01-28
 */
public class AxeCraftingRecipeBuilder {

    private final Ingredient input;
    private final Item output;
    private final int count;

    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public AxeCraftingRecipeBuilder(Ingredient input, Item output, int count) {
        this.input = input;
        this.output = output;
        this.count = count;
    }

    public AxeCraftingRecipeBuilder unlocks(String name, CriterionTriggerInstance triggerInstance) {
        this.advancement.addCriterion(name, triggerInstance);
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer) {
        this.save(consumer, ForgeRegistries.ITEMS.getKey(this.output));
    }

    public void save(Consumer<FinishedRecipe> consumer, String save) {
        ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.output);
        if (new ResourceLocation(save).equals(resourcelocation)) {
            throw new IllegalStateException("Axe Crafting Recipe " + save + " should remove its 'save' argument");
        } else {
            this.save(consumer, new ResourceLocation(save));
        }
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        this.ensureValid(id);
        this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new AxeCraftingRecipeBuilder.Result(id, this.output, this.count, this.input, this.advancement, new ResourceLocation(id.getNamespace(), "recipes/" + this.output.getItemCategory().getRecipeFolderName() + "/" + id.getPath())));
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item output;
        private final int count;
        private final Ingredient input;
        private final Advancement.Builder advancementBuilder;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, Item output, int count, Ingredient input, Advancement.Builder advancementBuilder, ResourceLocation advancementId) {
            this.id = id;
            this.output = output;
            this.count = count;
            this.input = input;
            this.advancementBuilder = advancementBuilder;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(@Nonnull JsonObject json) {
            json.add("input", this.input.toJson());
            JsonObject output = new JsonObject();
            output.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this.output)).toString());
            if (this.count > 1) {
                output.addProperty("count", this.count);
            }

            json.add("output", output);
        }

        @Nonnull
        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.AXE_CRAFTING_SERIALIZER.get();
        }

        @Nonnull
        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return this.advancementBuilder.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
