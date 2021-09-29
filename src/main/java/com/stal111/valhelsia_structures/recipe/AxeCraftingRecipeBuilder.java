package com.stal111.valhelsia_structures.recipe;

import com.google.gson.JsonObject;
import com.stal111.valhelsia_structures.init.ModRecipes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Axe Crafting Recipe Builder
 * Valhelsia Structures - com.stal111.valhelsia_structures.recipe.AxeCraftingRecipeBuilder
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-28
 */
public class AxeCraftingRecipeBuilder {

    private final Ingredient input;
    private final Item output;
    private final int count;

    private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();

    public AxeCraftingRecipeBuilder(Ingredient input, Item output, int count) {
        this.input = input;
        this.output = output;
        this.count = count;
    }

    public AxeCraftingRecipeBuilder addCriterion(String name, ICriterionInstance criterionIn) {
        this.advancementBuilder.withCriterion(name, criterionIn);
        return this;
    }

    public void build(Consumer<IFinishedRecipe> consumerIn) {
        this.build(consumerIn, Registry.ITEM.getKey(this.output));
    }

    public void build(Consumer<IFinishedRecipe> consumer, String save) {
        ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.output);
        if ((new ResourceLocation(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Axe Crafting Recipe " + save + " should remove its 'save' argument");
        } else {
            this.build(consumer, new ResourceLocation(save));
        }
    }

    public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
        this.advancementBuilder.withParentId(new ResourceLocation("recipes/root")).withCriterion("has_the_recipe", RecipeUnlockedTrigger.create(id)).withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);
        consumerIn.accept(new AxeCraftingRecipeBuilder.Result(id, this.output, this.count, this.input, this.advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + this.output.getGroup().getPath() + "/" + id.getPath())));
    }

    public static class Result implements IFinishedRecipe {
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

        public void serialize(JsonObject json) {
            json.add("input", this.input.serialize());
            JsonObject output = new JsonObject();
            output.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this.output)).toString());
            if (this.count > 1) {
                output.addProperty("count", this.count);
            }

            json.add("output", output);
        }

        public IRecipeSerializer<?> getSerializer() {
            return ModRecipes.AXE_CRAFTING_SERIALIZER.get();
        }

        /**
         * Gets the ID for the recipe.
         */
        public ResourceLocation getID() {
            return this.id;
        }

        /**
         * Gets the JSON for the advancement that unlocks this recipe. Null if there is no advancement.
         */
        @Nullable
        public JsonObject getAdvancementJson() {
            return this.advancementBuilder.serialize();
        }

        /**
         * Gets the ID for the advancement associated with this recipe. Should not be null if {@link #getAdvancementJson}
         * is non-null.
         */
        @Nullable
        public ResourceLocation getAdvancementID() {
            return this.advancementId;
        }
    }
}
