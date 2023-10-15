package com.stal111.valhelsia_structures.data.recipes;

import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.common.recipe.AxeCraftingRecipeBuilder;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.utils.ModTags;
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
import net.valhelsia.valhelsia_core.core.data.DataProviderInfo;
import net.valhelsia.valhelsia_core.data.recipes.RecipePart;
import net.valhelsia.valhelsia_core.data.recipes.ValhelsiaRecipeProvider;

import java.util.Objects;

/**
 * @author Valhelsia Team
 * @since 2022-10-24
 */
public class ModRecipeProvider extends ValhelsiaRecipeProvider {

    public ModRecipeProvider(DataProviderInfo info) {
        super(info);
    }

    @Override
    protected void registerRecipes() {
        // Crafting Recipes
        this.brazier(ModBlocks.BRAZIER.get(), ItemTags.COALS);
        this.brazier(ModBlocks.SOUL_BRAZIER.get(), ItemTags.SOUL_FIRE_BASE_BLOCKS);

        ModBlocks.HELPER.getRegistryObjects().forEach(registryObject -> {
            if (registryObject.get() instanceof PostBlock postBlock) {
                this.add(new AxeCraftingRecipeBuilder(Ingredient.of(postBlock.getLogBlock()), postBlock.asItem(), 2).unlockedBy("has_item", has(postBlock.getLogBlock())));
            } else if (registryObject.get() instanceof CutPostBlock block) {
                Block postBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ValhelsiaStructures.MOD_ID, Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath().substring(4)));

                if (postBlock != null) {
                    this.add(new AxeCraftingRecipeBuilder(Ingredient.of(postBlock), registryObject.get().asItem(), 4).unlockedBy("has_item", has(postBlock)));
                }
            }
        });

        this.metalFramedGlass(ModBlocks.METAL_FRAMED_GLASS.get(), RecipePart.of(Tags.Items.GLASS_COLORLESS));
        this.glassPane(ModBlocks.METAL_FRAMED_GLASS_PANE.get(), RecipePart.of(ModBlocks.METAL_FRAMED_GLASS.get()));

        ModBlocks.COLORED_METAL_FRAMED_GLASS.forEach((color, registryObject) -> {
            Block block = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(color.getName() + "_stained_glass")));
            this.metalFramedGlass(registryObject.get(), RecipePart.of(block));
        });

        ModBlocks.COLORED_METAL_FRAMED_GLASS_PANES.forEach((color, registryObject) -> {
            Block block = ModBlocks.COLORED_METAL_FRAMED_GLASS.get(color).get();
            this.glassPane(registryObject.get(), RecipePart.of(ModBlocks.METAL_FRAMED_GLASS.get()));
        });

        this.shaped(ModBlocks.PAPER_WALL.get(), 2, builder -> builder.pattern("#X#").pattern("#X#").pattern("#X#").define('#', Items.BAMBOO).define('X', Items.PAPER).unlockedBy(this, Items.BAMBOO));
        this.shaped(ModBlocks.BONE_PILE.get(), 3, builder -> builder.pattern("###").define('#', Items.BONE).group("bone_pile").unlockedBy(this, Items.BONE));
        this.storageRecipe(Items.BONE, ModBlocks.BONE_PILE_BLOCK.get());
        this.shaped(ModBlocks.BONE_PILE_BLOCK.get(), builder -> builder.pattern("###").pattern("###").pattern("###").define('#', ModBlocks.BONE_PILE.get()).group("bone_pile_block").unlockedBy(this, ModBlocks.BONE_PILE.get()), "bone_pile_block_from_bone_piles");

        this.shaped(ModBlocks.EXPLORERS_TENT.get(), builder -> builder.pattern(" # ").pattern("#X#").pattern("#X#").define('#', Tags.Items.LEATHER).define('X', Tags.Items.RODS_WOODEN).unlockedBy(this, RecipePart.of(Tags.Items.LEATHER)));

        this.glazedJar(ModBlocks.GLAZED_JAR.get(), Blocks.TERRACOTTA);
        this.bigGlazedJar(ModBlocks.BIG_GLAZED_JAR.get(), Blocks.TERRACOTTA);

        ModBlocks.COLORED_GLAZED_JARS.forEach(registryObject -> {
            String name = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(registryObject.get())).getPath();
            Block block = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name.substring(0, name.length() - 11) + "_terracotta")));
            this.glazedJar(registryObject.get(), block);
        });

        ModBlocks.BIG_COLORED_GLAZED_JARS.forEach(registryObject -> {
            String name = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(registryObject.get())).getPath();
            Block block = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name.substring(4, name.length() - 11) + "_terracotta")));
            this.bigGlazedJar(registryObject.get(), block);
        });

        this.surroundingItem(ModBlocks.LAPIDIFIED_JUNGLE_LOG.get(), RecipePart.of(Ingredient.of(Blocks.VINE, ModBlocks.HANGING_VINES.get())), RecipePart.of(Blocks.JUNGLE_LOG), 8);
        this.wood(ModBlocks.LAPIDIFIED_JUNGLE_WOOD.get(), RecipePart.of(ModBlocks.LAPIDIFIED_JUNGLE_LOG.get()));
        this.planks(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get(), ModTags.Items.LAPIDIFIED_JUNGLE_LOGS);
        this.slab(ModBlocks.LAPIDIFIED_JUNGLE_SLAB.get(), RecipePart.of(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()));
        this.stairs(ModBlocks.LAPIDIFIED_JUNGLE_STAIRS.get(), RecipePart.of(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()));
        this.pressurePlate(ModBlocks.LAPIDIFIED_JUNGLE_PRESSURE_PLATE.get(), RecipePart.of(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()));
        this.button(ModBlocks.LAPIDIFIED_JUNGLE_BUTTON.get(), ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get());
        this.fence(ModBlocks.LAPIDIFIED_JUNGLE_FENCE.get(), RecipePart.of(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()));
        this.fenceGate(ModBlocks.LAPIDIFIED_JUNGLE_FENCE_GATE.get(), RecipePart.of(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get()));

        this.simple2x2(ModBlocks.BUNDLED_OAK_POSTS.get(), RecipePart.of(ModBlocks.OAK_POST.get()));
        this.simple2x2(ModBlocks.BUNDLED_SPRUCE_POSTS.get(), RecipePart.of(ModBlocks.SPRUCE_POST.get()));
        this.simple2x2(ModBlocks.BUNDLED_BIRCH_POSTS.get(), RecipePart.of(ModBlocks.BIRCH_POST.get()));
        this.simple2x2(ModBlocks.BUNDLED_JUNGLE_POSTS.get(), RecipePart.of(ModBlocks.JUNGLE_POST.get()));
        this.simple2x2(ModBlocks.BUNDLED_ACACIA_POSTS.get(), RecipePart.of(ModBlocks.ACACIA_POST.get()));
        this.simple2x2(ModBlocks.BUNDLED_DARK_OAK_POSTS.get(), RecipePart.of(ModBlocks.DARK_OAK_POST.get()));
        this.simple2x2(ModBlocks.BUNDLED_CRIMSON_POSTS.get(), RecipePart.of(ModBlocks.CRIMSON_POST.get()));
        this.simple2x2(ModBlocks.BUNDLED_WARPED_POSTS.get(), RecipePart.of(ModBlocks.WARPED_POST.get()));

        this.simple2x2(ModBlocks.BUNDLED_STRIPPED_OAK_POSTS.get(), RecipePart.of(ModBlocks.STRIPPED_OAK_POST.get()));
        this.simple2x2(ModBlocks.BUNDLED_STRIPPED_SPRUCE_POSTS.get(), RecipePart.of(ModBlocks.STRIPPED_SPRUCE_POST.get()));
        this.simple2x2(ModBlocks.BUNDLED_STRIPPED_BIRCH_POSTS.get(), RecipePart.of(ModBlocks.STRIPPED_BIRCH_POST.get()));
        this.simple2x2(ModBlocks.BUNDLED_STRIPPED_JUNGLE_POSTS.get(), RecipePart.of(ModBlocks.STRIPPED_JUNGLE_POST.get()));
        this.simple2x2(ModBlocks.BUNDLED_STRIPPED_ACACIA_POSTS.get(), RecipePart.of(ModBlocks.STRIPPED_ACACIA_POST.get()));
        this.simple2x2(ModBlocks.BUNDLED_STRIPPED_DARK_OAK_POSTS.get(), RecipePart.of(ModBlocks.STRIPPED_DARK_OAK_POST.get()));
        this.simple2x2(ModBlocks.BUNDLED_STRIPPED_CRIMSON_POSTS.get(), RecipePart.of(ModBlocks.STRIPPED_CRIMSON_POST.get()));
        this.simple2x2(ModBlocks.BUNDLED_STRIPPED_WARPED_POSTS.get(), RecipePart.of(ModBlocks.STRIPPED_WARPED_POST.get()));

        Block whiteSleepingBag = ModBlocks.SLEEPING_BAGS.get(DyeColor.WHITE).get();

        ModBlocks.SLEEPING_BAGS.forEach((color, registryObject) -> {
            Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(color.getName() + "_wool"));

            if (block != null) {
                this.singleRow(registryObject.get(), RecipePart.of(block));
            }

            if (color != DyeColor.WHITE) {
                this.shapeless(registryObject.get(), builder -> builder.requires(whiteSleepingBag).requires(color.getTag()).unlockedBy("has_item", has(whiteSleepingBag)).unlockedBy("has_color", has(color.getTag())), registryObject.getId().getPath() + "_from_white_sleeping_bag");
            }
        });

        // Smelting Recipes
        this.add(SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.GLAZED_JAR.get()), ModBlocks.CRACKED_GLAZED_JAR.get(), 0.1F, 200).unlockedBy("has_item", has(ModBlocks.GLAZED_JAR.get())), "smelting/cracked_glazed_jar");
        this.add(SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.BIG_GLAZED_JAR.get()), ModBlocks.CRACKED_BIG_GLAZED_JAR.get(), 0.1F, 200).unlockedBy("has_item", has(ModBlocks.BIG_GLAZED_JAR.get())), "smelting/cracked_big_glazed_jar");
    }

    public void brazier(ItemLike result, TagKey<Item> tagKey) {
        this.shaped(result, (builder) -> builder.pattern("*X*").pattern("###").define('#', Tags.Items.INGOTS_IRON).define('X', tagKey).define('*', Items.IRON_BARS).unlockedBy("has_" + tagKey.location().getPath(), has(tagKey)).unlockedBy("has_iron_bars", has(Items.IRON_BARS)));
    }

    public void metalFramedGlass(ItemLike result, RecipePart<?> glass) {
        this.surroundingItem(result, RecipePart.of(Tags.Items.INGOTS_IRON), glass, 8);
    }

    public void glazedJar(ItemLike result, ItemLike terracotta) {
        this.shaped(result, builder -> builder.pattern("# #").pattern(" # ").define('#', terracotta).group("glazedJar").unlockedBy(this, terracotta));
    }

    public void bigGlazedJar(ItemLike result, ItemLike terracotta) {
        this.shaped(result, builder -> builder.pattern("# #").pattern("# #").pattern(" # ").define('#', terracotta).group("biGlazedJar").unlockedBy(this, terracotta));
    }
}
