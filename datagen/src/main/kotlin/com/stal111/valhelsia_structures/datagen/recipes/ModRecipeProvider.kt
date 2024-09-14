package com.stal111.valhelsia_structures.datagen.recipes

import com.stal111.valhelsia_structures.core.init.ModBlocks
import net.minecraft.Util
import net.minecraft.core.HolderLookup
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.valhelsia.dataforge.recipe.DataForgeRecipeProvider
import net.valhelsia.dataforge.recipe.RecipeSubProvider
import java.util.*

class ModRecipeProvider(provider: DataForgeRecipeProvider) : RecipeSubProvider(provider) {
    override fun registerRecipes(lookupProvider: HolderLookup.Provider) {

        // Crafting Recipes
//        this.brazier(ModBlocks.BRAZIER.get(), ItemTags.COALS)
//        this.brazier(ModBlocks.SOUL_BRAZIER.get(), ItemTags.SOUL_FIRE_BASE_BLOCKS)

//        for (woodType in ModBlocks.WoodType.entries.toTypedArray()) {
//            val postBlock = ModBlocks.WOODEN_POSTS[woodType]!!.get()
//            val strippedPostBlock = ModBlocks.STRIPPED_WOODEN_POSTS[woodType]!!.get()
//
//            val logBlock = WOOD_TYPE_TO_LOG[woodType]
//            val strippedLogBlock = WOOD_TYPE_TO_STRIPPED_LOG[woodType]
//
//            this.add(
//                AxeCraftingRecipeBuilder(
//                    RecipeCategory.BUILDING_BLOCKS,
//                    Ingredient.of(logBlock),
//                    postBlock,
//                    2
//                ).unlockedBy("has_item", has(logBlock))
//            )
//            this.add(
//                AxeCraftingRecipeBuilder(
//                    RecipeCategory.BUILDING_BLOCKS,
//                    Ingredient.of(strippedLogBlock),
//                    strippedPostBlock,
//                    2
//                ).unlockedBy("has_item", has(strippedLogBlock))
//            )
//
//            this.add(
//                AxeCraftingRecipeBuilder(
//                    RecipeCategory.BUILDING_BLOCKS, Ingredient.of(postBlock), ModBlocks.CUT_WOODEN_POSTS[woodType]!!
//                        .get(), 4
//                ).unlockedBy("has_item", has(postBlock))
//            )
//            this.add(
//                AxeCraftingRecipeBuilder(
//                    RecipeCategory.BUILDING_BLOCKS,
//                    Ingredient.of(strippedPostBlock),
//                    ModBlocks.CUT_STRIPPED_WOODEN_POSTS[woodType]!!
//                        .get(),
//                    4
//                ).unlockedBy("has_item", has(strippedPostBlock))
//            )
//        }
//
//        this.metalFramedGlass(ModBlocks.METAL_FRAMED_GLASS.get(), RecipePart.of(Tags.Items.GLASS_BLOCKS_COLORLESS))
//        this.glassPane(ModBlocks.METAL_FRAMED_GLASS_PANE.get(), RecipePart.of(ModBlocks.METAL_FRAMED_GLASS.get()))
//
//        ModBlocks.COLORED_METAL_FRAMED_GLASS.forEach { color: DyeColor, registryObject: BlockRegistryEntry<StainedGlassBlock?> ->
//            val block =
//                BuiltInRegistries.BLOCK[ResourceLocation.withDefaultNamespace(color.getName() + "_stained_glass")]
//            this.metalFramedGlass(registryObject.get(), RecipePart.of(block))
//        }
//
//        ModBlocks.COLORED_METAL_FRAMED_GLASS_PANES.forEach { color: DyeColor?, registryObject: BlockRegistryEntry<StainedGlassPaneBlock?> ->
//            val block: Block = ModBlocks.COLORED_METAL_FRAMED_GLASS[color]!!
//                .get()
//            this.glassPane(registryObject.get(), RecipePart.of(block))
//        }
//
//        this.shaped(
//            RecipeCategory.BUILDING_BLOCKS,
//            ModBlocks.PAPER_WALL.get(),
//            2
//        ) { builder: ValhelsiaShapedRecipeBuilder ->
//            builder.pattern("#X#").pattern("#X#").pattern("#X#").define('#', Items.BAMBOO).define('X', Items.PAPER)
//                .unlockedBy(
//                    this, Items.BAMBOO
//                )
//        }
//        this.shaped(
//            RecipeCategory.BUILDING_BLOCKS,
//            ModBlocks.BONE_PILE.get(),
//            3
//        ) { builder: ValhelsiaShapedRecipeBuilder ->
//            builder.pattern("###").define('#', Items.BONE).group("bone_pile").unlockedBy(
//                this, Items.BONE
//            )
//        }
//        this.shapeless(
//            RecipeCategory.BUILDING_BLOCKS,
//            ModBlocks.BONE_PILE.get(),
//            9,
//            { builder: ShapelessRecipeBuilder ->
//                builder.requires(ModBlocks.BONE_PILE_BLOCK.get()).group("bone_pile").unlockedBy(
//                    getHasName(ModBlocks.BONE_PILE_BLOCK.get()), has(ModBlocks.BONE_PILE_BLOCK.get())
//                )
//            },
//            "bone_pile_from_bone_pile_block"
//        )
//        this.storageRecipe(Items.BONE, ModBlocks.BONE_PILE_BLOCK.get())
//        this.shaped(
//            RecipeCategory.BUILDING_BLOCKS,
//            ModBlocks.BONE_PILE_BLOCK.get(),
//            { builder: ValhelsiaShapedRecipeBuilder ->
//                builder.pattern("###").pattern("###").pattern("###").define('#', ModBlocks.BONE_PILE.get())
//                    .group("bone_pile_block").unlockedBy(
//                    this, ModBlocks.BONE_PILE.get()
//                )
//            },
//            "bone_pile_block_from_bone_piles"
//        )
//
//        this.shaped(
//            RecipeCategory.DECORATIONS,
//            ModBlocks.EXPLORERS_TENT.get()
//        ) { builder: ValhelsiaShapedRecipeBuilder ->
//            builder.pattern(" # ").pattern("#X#").pattern("#X#").define('#', Items.LEATHER)
//                .define('X', Tags.Items.RODS_WOODEN).unlockedBy(
//                this, Items.LEATHER
//            )
//        }
//
//        this.glazedJar(ModBlocks.GLAZED_JAR.get(), Blocks.TERRACOTTA)
//
//        ModBlocks.COLORED_GLAZED_JARS.values.forEach(Consumer { registryObject: BlockRegistryEntry<JarBlock?> ->
//            val name = BuiltInRegistries.BLOCK.getKey(registryObject.get()).path
//            val block = BuiltInRegistries.BLOCK[ResourceLocation.withDefaultNamespace(
//                name.substring(
//                    0,
//                    name.length - 11
//                ) + "_terracotta"
//            )]
//            this.glazedJar(registryObject.get(), block)
//        })
//
//        ModBlocks.BUNDLED_POSTS.forEach { woodType: ModBlocks.WoodType?, registryObject: BlockRegistryEntry<RotatedPillarBlock?> ->
//            this.simple2x2(
//                RecipeCategory.BUILDING_BLOCKS, registryObject.get(), RecipePart.of(
//                    ModBlocks.WOODEN_POSTS[woodType]!!.get()
//                )
//            )
//        }
//
//        ModBlocks.BUNDLED_STRIPPED_POSTS.forEach { woodType: ModBlocks.WoodType?, registryObject: BlockRegistryEntry<RotatedPillarBlock?> ->
//            this.simple2x2(
//                RecipeCategory.BUILDING_BLOCKS, registryObject.get(), RecipePart.of(
//                    ModBlocks.STRIPPED_WOODEN_POSTS[woodType]!!.get()
//                )
//            )
//        }
//
//        val whiteSleepingBag: Block = ModBlocks.SLEEPING_BAGS[DyeColor.WHITE]!!
//            .get()
//
//        ModBlocks.SLEEPING_BAGS.forEach { color: DyeColor, entry: BlockRegistryEntry<SleepingBagBlock?> ->
//            val block = BuiltInRegistries.BLOCK[ResourceLocation.withDefaultNamespace(color.getName() + "_wool")]
//            this.singleRow(RecipeCategory.DECORATIONS, entry.get(), RecipePart.of(block))
//            if (color != DyeColor.WHITE) {
//                this.shapeless(
//                    RecipeCategory.DECORATIONS,
//                    entry.get(),
//                    { builder: ShapelessRecipeBuilder ->
//                        builder.requires(whiteSleepingBag).requires(color.tag)
//                            .unlockedBy("has_item", has(whiteSleepingBag)).unlockedBy("has_color", has(color.tag))
//                    },
//                    BuiltInRegistries.BLOCK.getKey(entry.get()).path + "_from_white_sleeping_bag"
//                )
//            }
//        }
//
//        // Smelting Recipes
//        this.add(
//            SimpleCookingRecipeBuilder.smelting(
//                Ingredient.of(ModBlocks.GLAZED_JAR.get()),
//                RecipeCategory.DECORATIONS,
//                ModBlocks.CRACKED_GLAZED_JAR.get(),
//                0.1f,
//                200
//            ).unlockedBy("has_item", has(ModBlocks.GLAZED_JAR.get())), "smelting/cracked_glazed_jar"
//        )
//    }
//
//    fun brazier(result: ItemLike?, tagKey: TagKey<Item?>) {
//        this.shaped(RecipeCategory.DECORATIONS, result) { builder: ValhelsiaShapedRecipeBuilder ->
//            builder.pattern("*X*").pattern("###").define('#', Tags.Items.INGOTS_IRON).define('X', tagKey)
//                .define('*', Items.IRON_BARS).unlockedBy("has_" + tagKey.location().path, has(tagKey)).unlockedBy(
//                "has_iron_bars", has(
//                    Items.IRON_BARS
//                )
//            )
//        }
//    }
//
//    fun metalFramedGlass(result: ItemLike?, glass: RecipePart<*>?) {
//        this.surroundingItem(RecipeCategory.BUILDING_BLOCKS, result, RecipePart.of(Tags.Items.INGOTS_IRON), glass, 8)
//    }
//
//    fun glazedJar(result: ItemLike?, terracotta: ItemLike?) {
//        this.shaped(RecipeCategory.DECORATIONS, result) { builder: ValhelsiaShapedRecipeBuilder ->
//            builder.pattern("# #").pattern(" # ").define('#', terracotta).group("glazedJar").unlockedBy(
//                this, terracotta
//            )
//        }
//    }
    }

    companion object {
        val WOOD_TYPE_TO_LOG: EnumMap<ModBlocks.WoodType, Block> = Util.make(
            EnumMap(
                ModBlocks.WoodType::class.java
            )
        ) { map: EnumMap<ModBlocks.WoodType, Block> ->
            map[ModBlocks.WoodType.OAK] = Blocks.OAK_LOG
            map[ModBlocks.WoodType.SPRUCE] = Blocks.SPRUCE_LOG
            map[ModBlocks.WoodType.BIRCH] = Blocks.BIRCH_LOG
            map[ModBlocks.WoodType.JUNGLE] = Blocks.JUNGLE_LOG
            map[ModBlocks.WoodType.ACACIA] = Blocks.ACACIA_LOG
            map[ModBlocks.WoodType.DARK_OAK] = Blocks.DARK_OAK_LOG
            map[ModBlocks.WoodType.MANGROVE] = Blocks.MANGROVE_LOG
            map[ModBlocks.WoodType.CRIMSON] = Blocks.CRIMSON_STEM
            map[ModBlocks.WoodType.WARPED] = Blocks.WARPED_STEM
        }

        val WOOD_TYPE_TO_STRIPPED_LOG: EnumMap<ModBlocks.WoodType, Block> = Util.make(
            EnumMap(
                ModBlocks.WoodType::class.java
            )
        ) { map: EnumMap<ModBlocks.WoodType, Block> ->
            map[ModBlocks.WoodType.OAK] = Blocks.STRIPPED_OAK_LOG
            map[ModBlocks.WoodType.SPRUCE] = Blocks.STRIPPED_SPRUCE_LOG
            map[ModBlocks.WoodType.BIRCH] = Blocks.STRIPPED_BIRCH_LOG
            map[ModBlocks.WoodType.JUNGLE] = Blocks.STRIPPED_JUNGLE_LOG
            map[ModBlocks.WoodType.ACACIA] = Blocks.STRIPPED_ACACIA_LOG
            map[ModBlocks.WoodType.DARK_OAK] = Blocks.STRIPPED_DARK_OAK_LOG
            map[ModBlocks.WoodType.MANGROVE] = Blocks.MANGROVE_LOG
            map[ModBlocks.WoodType.CRIMSON] = Blocks.STRIPPED_CRIMSON_STEM
            map[ModBlocks.WoodType.WARPED] = Blocks.STRIPPED_WARPED_STEM
        }
    }
}
