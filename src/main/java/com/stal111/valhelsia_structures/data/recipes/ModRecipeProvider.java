package com.stal111.valhelsia_structures.data.recipes;

import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.common.recipe.AxeCraftingRecipeBuilder;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.api.datagen.recipes.RecipePart;
import net.valhelsia.valhelsia_core.api.datagen.recipes.RecipeSubProvider;
import net.valhelsia.valhelsia_core.api.datagen.recipes.ValhelsiaRecipeProvider;

import java.util.EnumMap;
import java.util.Objects;

/**
 * @author Valhelsia Team
 * @since 2022-10-24
 */
public class ModRecipeProvider extends RecipeSubProvider {

    public static final EnumMap<ModBlocks.WoodType, Block> WOOD_TYPE_TO_LOG = Util.make(new EnumMap<>(ModBlocks.WoodType.class), map -> {
        map.put(ModBlocks.WoodType.OAK, Blocks.OAK_LOG);
        map.put(ModBlocks.WoodType.SPRUCE, Blocks.SPRUCE_LOG);
        map.put(ModBlocks.WoodType.BIRCH, Blocks.BIRCH_LOG);
        map.put(ModBlocks.WoodType.JUNGLE, Blocks.JUNGLE_LOG);
        map.put(ModBlocks.WoodType.ACACIA, Blocks.ACACIA_LOG);
        map.put(ModBlocks.WoodType.DARK_OAK, Blocks.DARK_OAK_LOG);
        map.put(ModBlocks.WoodType.MANGROVE, Blocks.MANGROVE_LOG);
        map.put(ModBlocks.WoodType.CRIMSON, Blocks.CRIMSON_STEM);
        map.put(ModBlocks.WoodType.WARPED, Blocks.WARPED_STEM);
        map.put(ModBlocks.WoodType.LAPIDIFIED_JUNGLE, ModBlocks.LAPIDIFIED_JUNGLE_LOG.get());
    });

    public static final EnumMap<ModBlocks.WoodType, Block> WOOD_TYPE_TO_STRIPPED_LOG = Util.make(new EnumMap<>(ModBlocks.WoodType.class), map -> {
        map.put(ModBlocks.WoodType.OAK, Blocks.STRIPPED_OAK_LOG);
        map.put(ModBlocks.WoodType.SPRUCE, Blocks.STRIPPED_SPRUCE_LOG);
        map.put(ModBlocks.WoodType.BIRCH, Blocks.STRIPPED_BIRCH_LOG);
        map.put(ModBlocks.WoodType.JUNGLE, Blocks.STRIPPED_JUNGLE_LOG);
        map.put(ModBlocks.WoodType.ACACIA, Blocks.STRIPPED_ACACIA_LOG);
        map.put(ModBlocks.WoodType.DARK_OAK, Blocks.STRIPPED_DARK_OAK_LOG);
        map.put(ModBlocks.WoodType.MANGROVE, Blocks.MANGROVE_LOG);
        map.put(ModBlocks.WoodType.CRIMSON, Blocks.STRIPPED_CRIMSON_STEM);
        map.put(ModBlocks.WoodType.WARPED, Blocks.STRIPPED_WARPED_STEM);
        map.put(ModBlocks.WoodType.LAPIDIFIED_JUNGLE, ModBlocks.LAPIDIFIED_JUNGLE_LOG.get());
    });

    public ModRecipeProvider(ValhelsiaRecipeProvider provider) {
        super(provider);
    }

    @Override
    protected void registerRecipes() {
        // Crafting Recipes
        this.brazier(ModBlocks.BRAZIER.get(), ItemTags.COALS);
        this.brazier(ModBlocks.SOUL_BRAZIER.get(), ItemTags.SOUL_FIRE_BASE_BLOCKS);

        for (ModBlocks.WoodType woodType : ModBlocks.WoodType.values()) {
            PostBlock postBlock = ModBlocks.WOODEN_POSTS.get(woodType).get();
            PostBlock strippedPostBlock = ModBlocks.STRIPPED_WOODEN_POSTS.get(woodType).get();

            Block logBlock = WOOD_TYPE_TO_LOG.get(woodType);
            Block strippedLogBlock = WOOD_TYPE_TO_STRIPPED_LOG.get(woodType);

            this.add(new AxeCraftingRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, Ingredient.of(logBlock), postBlock, 2).unlockedBy("has_item", has(logBlock)));
            this.add(new AxeCraftingRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, Ingredient.of(strippedLogBlock), strippedPostBlock, 2).unlockedBy("has_item", has(strippedLogBlock)));

            this.add(new AxeCraftingRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, Ingredient.of(postBlock), ModBlocks.CUT_WOODEN_POSTS.get(woodType).get(), 4).unlockedBy("has_item", has(postBlock)));
            this.add(new AxeCraftingRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, Ingredient.of(strippedPostBlock), ModBlocks.CUT_STRIPPED_WOODEN_POSTS.get(woodType).get(), 4).unlockedBy("has_item", has(strippedPostBlock)));

        }

        this.metalFramedGlass(ModBlocks.METAL_FRAMED_GLASS.get(), RecipePart.of(Tags.Items.GLASS_COLORLESS));
        this.glassPane(ModBlocks.METAL_FRAMED_GLASS_PANE.get(), RecipePart.of(ModBlocks.METAL_FRAMED_GLASS.get()));

        ModBlocks.COLORED_METAL_FRAMED_GLASS.forEach((color, registryObject) -> {
            Block block = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(color.getName() + "_stained_glass")));
            this.metalFramedGlass(registryObject.get(), RecipePart.of(block));
        });

        ModBlocks.COLORED_METAL_FRAMED_GLASS_PANES.forEach((color, registryObject) -> {
            Block block = ModBlocks.COLORED_METAL_FRAMED_GLASS.get(color).get();
            this.glassPane(registryObject.get(), RecipePart.of(block));
        });

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PAPER_WALL.get(), 2, builder -> builder.pattern("#X#").pattern("#X#").pattern("#X#").define('#', Items.BAMBOO).define('X', Items.PAPER).unlockedBy(this, Items.BAMBOO));
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BONE_PILE.get(), 3, builder -> builder.pattern("###").define('#', Items.BONE).group("bone_pile").unlockedBy(this, Items.BONE));
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BONE_PILE.get(), 9, builder -> builder.requires(ModBlocks.BONE_PILE_BLOCK.get()).group("bone_pile").unlockedBy(getHasName(ModBlocks.BONE_PILE_BLOCK.get()), has(ModBlocks.BONE_PILE_BLOCK.get())), "bone_pile_from_bone_pile_block");
        this.storageRecipe(Items.BONE, ModBlocks.BONE_PILE_BLOCK.get());
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BONE_PILE_BLOCK.get(), builder -> builder.pattern("###").pattern("###").pattern("###").define('#', ModBlocks.BONE_PILE.get()).group("bone_pile_block").unlockedBy(this, ModBlocks.BONE_PILE.get()), "bone_pile_block_from_bone_piles");

        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.EXPLORERS_TENT.get(), builder -> builder.pattern(" # ").pattern("#X#").pattern("#X#").define('#', Tags.Items.LEATHER).define('X', Tags.Items.RODS_WOODEN).unlockedBy(this, RecipePart.of(Tags.Items.LEATHER)));

        this.glazedJar(ModBlocks.GLAZED_JAR.get(), Blocks.TERRACOTTA);
        this.bigGlazedJar(ModBlocks.BIG_GLAZED_JAR.get(), Blocks.TERRACOTTA);

        ModBlocks.COLORED_GLAZED_JARS.values().forEach(registryObject -> {
            String name = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(registryObject.get())).getPath();
            Block block = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name.substring(0, name.length() - 11) + "_terracotta")));
            this.glazedJar(registryObject.get(), block);
        });

        ModBlocks.BIG_COLORED_GLAZED_JARS.values().forEach(registryObject -> {
            String name = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(registryObject.get())).getPath();
            Block block = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name.substring(4, name.length() - 11) + "_terracotta")));
            this.bigGlazedJar(registryObject.get(), block);
        });

        this.surroundingItem(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAPIDIFIED_JUNGLE_LOG.get(), RecipePart.of(Ingredient.of(Blocks.VINE, ModBlocks.HANGING_VINES.get())), RecipePart.of(Blocks.JUNGLE_LOG), 8);
        this.wood(ModBlocks.LAPIDIFIED_JUNGLE_WOOD.get(), RecipePart.of(ModBlocks.LAPIDIFIED_JUNGLE_LOG.get()));
        this.planks(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get(), ModTags.Items.LAPIDIFIED_JUNGLE_LOGS);
        this.slab(ModBlocks.LAPIDIFIED_JUNGLE_SLAB.get(), RecipePart.of(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()));
        this.stairs(ModBlocks.LAPIDIFIED_JUNGLE_STAIRS.get(), RecipePart.of(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()));
        this.pressurePlate(ModBlocks.LAPIDIFIED_JUNGLE_PRESSURE_PLATE.get(), RecipePart.of(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()));
        this.button(ModBlocks.LAPIDIFIED_JUNGLE_BUTTON.get(), ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get());
        this.fence(ModBlocks.LAPIDIFIED_JUNGLE_FENCE.get(), RecipePart.of(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()));
        this.fenceGate(ModBlocks.LAPIDIFIED_JUNGLE_FENCE_GATE.get(), RecipePart.of(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()));

        ModBlocks.BUNDLED_POSTS.forEach((woodType, registryObject) -> {
            this.simple2x2(RecipeCategory.BUILDING_BLOCKS, registryObject.get(), RecipePart.of(ModBlocks.WOODEN_POSTS.get(woodType).get()));
        });

        ModBlocks.BUNDLED_STRIPPED_POSTS.forEach((woodType, registryObject) -> {
            this.simple2x2(RecipeCategory.BUILDING_BLOCKS, registryObject.get(), RecipePart.of(ModBlocks.STRIPPED_WOODEN_POSTS.get(woodType).get()));
        });

        Block whiteSleepingBag = ModBlocks.SLEEPING_BAGS.get(DyeColor.WHITE).get();

        ModBlocks.SLEEPING_BAGS.forEach((color, entry) -> {
            Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(color.getName() + "_wool"));

            if (block != null) {
                this.singleRow(RecipeCategory.DECORATIONS, entry.get(), RecipePart.of(block));
            }

            if (color != DyeColor.WHITE) {
                this.shapeless(RecipeCategory.DECORATIONS, entry.get(), builder -> builder.requires(whiteSleepingBag).requires(color.getTag()).unlockedBy("has_item", has(whiteSleepingBag)).unlockedBy("has_color", has(color.getTag())), BuiltInRegistries.BLOCK.getKey(entry.get()).getPath() + "_from_white_sleeping_bag");
            }
        });

        // Smelting Recipes
        this.add(SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.GLAZED_JAR.get()), RecipeCategory.DECORATIONS, ModBlocks.CRACKED_GLAZED_JAR.get(), 0.1F, 200).unlockedBy("has_item", has(ModBlocks.GLAZED_JAR.get())), "smelting/cracked_glazed_jar");
        this.add(SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.BIG_GLAZED_JAR.get()), RecipeCategory.DECORATIONS, ModBlocks.CRACKED_BIG_GLAZED_JAR.get(), 0.1F, 200).unlockedBy("has_item", has(ModBlocks.BIG_GLAZED_JAR.get())), "smelting/cracked_big_glazed_jar");
    }

    public void brazier(ItemLike result, TagKey<Item> tagKey) {
        this.shaped(RecipeCategory.DECORATIONS, result, (builder) -> builder.pattern("*X*").pattern("###").define('#', Tags.Items.INGOTS_IRON).define('X', tagKey).define('*', Items.IRON_BARS).unlockedBy("has_" + tagKey.location().getPath(), has(tagKey)).unlockedBy("has_iron_bars", has(Items.IRON_BARS)));
    }

    public void metalFramedGlass(ItemLike result, RecipePart<?> glass) {
        this.surroundingItem(RecipeCategory.BUILDING_BLOCKS, result, RecipePart.of(Tags.Items.INGOTS_IRON), glass, 8);
    }

    public void glazedJar(ItemLike result, ItemLike terracotta) {
        this.shaped(RecipeCategory.DECORATIONS, result, builder -> builder.pattern("# #").pattern(" # ").define('#', terracotta).group("glazedJar").unlockedBy(this, terracotta));
    }

    public void bigGlazedJar(ItemLike result, ItemLike terracotta) {
        this.shaped(RecipeCategory.DECORATIONS, result, builder -> builder.pattern("# #").pattern("# #").pattern(" # ").define('#', terracotta).group("biGlazedJar").unlockedBy(this, terracotta));
    }
}
