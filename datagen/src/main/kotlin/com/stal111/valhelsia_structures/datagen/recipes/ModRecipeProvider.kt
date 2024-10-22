package com.stal111.valhelsia_structures.datagen.recipes

import com.stal111.valhelsia_structures.common.recipe.AxeCraftingRecipeBuilder
import com.stal111.valhelsia_structures.core.init.ModBlocks
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.neoforged.neoforge.common.Tags
import net.valhelsia.dataforge.recipe.*

class ModRecipeProvider : RecipeSubProvider() {
    override fun registerRecipes(lookupProvider: HolderLookup.Provider) {

        // Crafting Recipes
        this.brazier(ModBlocks.BRAZIER.get(), ItemTags.COALS)
        this.brazier(ModBlocks.SOUL_BRAZIER.get(), ItemTags.SOUL_FIRE_BASE_BLOCKS)

        for (woodType in ModBlocks.WoodType.entries) {
            val postBlock = ModBlocks.WOODEN_POSTS[woodType]!!.get()
            val strippedPostBlock = ModBlocks.STRIPPED_WOODEN_POSTS[woodType]!!.get()

            val logBlock = WOOD_TYPE_TO_LOG[woodType]!!
            val strippedLogBlock = WOOD_TYPE_TO_STRIPPED_LOG[woodType]!!

            this.add(
                AxeCraftingRecipeBuilder(
                    RecipeCategory.BUILDING_BLOCKS,
                    Ingredient.of(logBlock),
                    postBlock,
                    2
                ).unlockedBy(logBlock)
            )
            this.add(
                AxeCraftingRecipeBuilder(
                    RecipeCategory.BUILDING_BLOCKS,
                    Ingredient.of(strippedLogBlock),
                    strippedPostBlock,
                    2
                ).unlockedBy(strippedLogBlock)
            )

            this.add(
                AxeCraftingRecipeBuilder(
                    RecipeCategory.BUILDING_BLOCKS,
                    Ingredient.of(postBlock),
                    ModBlocks.CUT_WOODEN_POSTS[woodType]!!.get(),
                    4
                ).unlockedBy(postBlock as Block)
            )
            this.add(
                AxeCraftingRecipeBuilder(
                    RecipeCategory.BUILDING_BLOCKS,
                    Ingredient.of(strippedPostBlock),
                    ModBlocks.CUT_STRIPPED_WOODEN_POSTS[woodType]!!.get(),
                    4
                ).unlockedBy(strippedPostBlock as Block)
            )
        }

        this.metalFramedGlass(ModBlocks.METAL_FRAMED_GLASS.get(), Tags.Items.GLASS_BLOCKS_COLORLESS)
        this.glassPane(ModBlocks.METAL_FRAMED_GLASS_PANE.get(), ModBlocks.METAL_FRAMED_GLASS.get() as Block)

        ModBlocks.COLORED_METAL_FRAMED_GLASS.forEach { color, entry ->
            val block =
                BuiltInRegistries.BLOCK[ResourceLocation.withDefaultNamespace(color.getName() + "_stained_glass")]
            this.metalFramedGlass(entry.get() as Block, block)
        }

        ModBlocks.COLORED_METAL_FRAMED_GLASS_PANES.forEach { color, entry ->
            val block: Block = ModBlocks.COLORED_METAL_FRAMED_GLASS[color]!!.get()
            this.glassPane(entry.get() as Block, block)
        }

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PAPER_WALL.get(), 2) {
            it.pattern("#X#", "#X#", "#X#").define('#', Items.BAMBOO).define('X', Items.PAPER).unlockedBy(Items.BAMBOO)
        }

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BONE_PILE.get(), 3) {
            it.pattern("###").define('#', Items.BONE).group("bone_pile").unlockedBy(Items.BONE)
        }

        this.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BONE_PILE.get(), 9, "bone_pile_from_bone_pile_block") {
            it.requires(ModBlocks.BONE_PILE_BLOCK.get()).group("bone_pile")
                .unlockedBy(ModBlocks.BONE_PILE_BLOCK.get() as Block)
        }

        this.storageRecipe(Items.BONE, ModBlocks.BONE_PILE_BLOCK.get())
        this.shaped(
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.BONE_PILE_BLOCK.get(),
            1,
            "bone_pile_block_from_bone_piles"
        ) {
            it.pattern("###", "###", "###").define('#', ModBlocks.BONE_PILE.get()).group("bone_pile_block")
                .unlockedBy(ModBlocks.BONE_PILE.get() as Block)
        }


        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.EXPLORERS_TENT.get()) {
            it.pattern(" # ", "#X#", "#X#").define('#', Items.LEATHER)
                .define('X', Tags.Items.RODS_WOODEN)
                .unlockedBy(Items.LEATHER)
        }

        this.glazedJar(ModBlocks.GLAZED_JAR.get(), Blocks.TERRACOTTA)

        ModBlocks.COLORED_GLAZED_JARS.values.forEach {
            val name = BuiltInRegistries.BLOCK.getKey(it.get()).path
            val block = BuiltInRegistries.BLOCK[ResourceLocation.withDefaultNamespace(
                name.substring(
                    0,
                    name.length - 11
                ) + "_terracotta"
            )]
            this.glazedJar(it.get(), block)
        }

        ModBlocks.BUNDLED_POSTS.forEach { woodType, entry ->
            this.simple2x2(
                RecipeCategory.BUILDING_BLOCKS,
                entry.get(),
                ModBlocks.WOODEN_POSTS[woodType]!!.get() as Block
            )
        }

        ModBlocks.BUNDLED_STRIPPED_POSTS.forEach { woodType, entry ->
            this.simple2x2(
                RecipeCategory.BUILDING_BLOCKS,
                entry.get(),
                ModBlocks.STRIPPED_WOODEN_POSTS[woodType]!!.get() as Block
            )
        }

        val whiteSleepingBag: Block = ModBlocks.SLEEPING_BAGS[DyeColor.WHITE]!!.get()

        ModBlocks.SLEEPING_BAGS.forEach { color, entry ->
            val block = BuiltInRegistries.BLOCK[ResourceLocation.withDefaultNamespace(color.getName() + "_wool")]
            this.singleRow(RecipeCategory.DECORATIONS, entry.get(), block)
            if (color != DyeColor.WHITE) {
                this.shapeless(
                    RecipeCategory.DECORATIONS,
                    entry.get(),
                    path = BuiltInRegistries.BLOCK.getKey(entry.get()).path + "_from_white_sleeping_bag"
                ) {
                    it.requires(whiteSleepingBag).requires(color.tag)
                        .unlockedBy(whiteSleepingBag, color.tag as TagKey<Item>)
                }

            }
        }

        // Smelting Recipes
        this.add(
            SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(ModBlocks.GLAZED_JAR.get()),
                RecipeCategory.DECORATIONS,
                ModBlocks.CRACKED_GLAZED_JAR.get(),
                0.1f,
                200
            ).unlockedBy(ModBlocks.GLAZED_JAR.get() as Block), "smelting/cracked_glazed_jar"
        )
    }

    private fun brazier(result: ItemLike, tagKey: TagKey<Item>) {
        this.shaped(RecipeCategory.DECORATIONS, result) {
            it.pattern("*X*", "###").define('#', Tags.Items.INGOTS_IRON).define('X', tagKey)
                .define('*', Items.IRON_BARS).unlockedBy(tagKey, Items.IRON_BARS)
        }
    }

    private fun metalFramedGlass(result: ItemLike, glass: DataForgeRecipePart) {
        this.surroundingItem(RecipeCategory.BUILDING_BLOCKS, result, Tags.Items.INGOTS_IRON, glass, 8)
    }

    private fun glazedJar(result: ItemLike, terracotta: ItemLike) {
        this.shaped(RecipeCategory.DECORATIONS, result) {
            it.pattern("# #", " # ").define('#', terracotta).group("glazedJar").unlockedBy(terracotta)
        }
    }

    companion object {
        val WOOD_TYPE_TO_LOG = mapOf(
            ModBlocks.WoodType.OAK to Blocks.OAK_LOG,
            ModBlocks.WoodType.SPRUCE to Blocks.SPRUCE_LOG,
            ModBlocks.WoodType.BIRCH to Blocks.BIRCH_LOG,
            ModBlocks.WoodType.JUNGLE to Blocks.JUNGLE_LOG,
            ModBlocks.WoodType.ACACIA to Blocks.ACACIA_LOG,
            ModBlocks.WoodType.DARK_OAK to Blocks.DARK_OAK_LOG,
            ModBlocks.WoodType.MANGROVE to Blocks.MANGROVE_LOG,
            ModBlocks.WoodType.CRIMSON to Blocks.CRIMSON_STEM,
            ModBlocks.WoodType.WARPED to Blocks.WARPED_STEM
        )

        val WOOD_TYPE_TO_STRIPPED_LOG = mapOf(
            ModBlocks.WoodType.OAK to Blocks.STRIPPED_OAK_LOG,
            ModBlocks.WoodType.SPRUCE to Blocks.STRIPPED_SPRUCE_LOG,
            ModBlocks.WoodType.BIRCH to Blocks.STRIPPED_BIRCH_LOG,
            ModBlocks.WoodType.JUNGLE to Blocks.STRIPPED_JUNGLE_LOG,
            ModBlocks.WoodType.ACACIA to Blocks.STRIPPED_ACACIA_LOG,
            ModBlocks.WoodType.DARK_OAK to Blocks.STRIPPED_DARK_OAK_LOG,
            ModBlocks.WoodType.MANGROVE to Blocks.MANGROVE_LOG,
            ModBlocks.WoodType.CRIMSON to Blocks.STRIPPED_CRIMSON_STEM,
            ModBlocks.WoodType.WARPED to Blocks.STRIPPED_WARPED_STEM
        )
    }
}
