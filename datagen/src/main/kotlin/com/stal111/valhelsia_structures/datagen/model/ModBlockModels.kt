package com.stal111.valhelsia_structures.datagen.model

import com.stal111.valhelsia_structures.common.block.CutPostBlock
import com.stal111.valhelsia_structures.common.block.PostBlock
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties
import com.stal111.valhelsia_structures.core.init.ModBlocks
import com.stal111.valhelsia_structures.datagen.model.ModTextureMapping.brazier
import com.stal111.valhelsia_structures.datagen.model.ModTextureMapping.bundledPosts
import com.stal111.valhelsia_structures.datagen.model.ModTextureMapping.jar
import com.stal111.valhelsia_structures.datagen.model.ModTextureMapping.metalFramedGlassPane
import com.stal111.valhelsia_structures.datagen.model.ModTextureMapping.post
import com.stal111.valhelsia_structures.datagen.model.ModTextureMapping.sleepingBag
import net.minecraft.core.Direction
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.blockstates.*
import net.minecraft.data.models.model.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BedPart
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.valhelsia.dataforge.model.BlockModelGenerator
import net.valhelsia.dataforge.model.createModel

class ModBlockModels(private val defaultGenerators: BlockModelGenerators) : BlockModelGenerator(defaultGenerators) {
    override fun generate() {
        this.createSimpleFlatItemModel(ModBlocks.DUNGEON_DOOR.get())

        for (woodType in ModBlocks.WoodType.entries.toTypedArray()) {
            this.createPostVariants(
                ModBlocks.WOODEN_POSTS[woodType]!!.get(),
                ModBlocks.CUT_WOODEN_POSTS[woodType]!!.get()
            )
            this.createPostVariants(
                ModBlocks.STRIPPED_WOODEN_POSTS[woodType]!!.get(),
                ModBlocks.CUT_STRIPPED_WOODEN_POSTS[woodType]!!.get()
            )
            this.createBundledPosts(ModBlocks.BUNDLED_POSTS[woodType]!!.get())
            this.createBundledPosts(ModBlocks.BUNDLED_STRIPPED_POSTS[woodType]!!.get())
        }

        defaultGenerators.createNormalTorch(ModBlocks.UNLIT_TORCH.get(), ModBlocks.UNLIT_WALL_TORCH.get())
        defaultGenerators.createNormalTorch(ModBlocks.UNLIT_SOUL_TORCH.get(), ModBlocks.UNLIT_SOUL_WALL_TORCH.get())
        defaultGenerators.createLantern(ModBlocks.UNLIT_LANTERN.get())
        defaultGenerators.createLantern(ModBlocks.UNLIT_SOUL_LANTERN.get())

        this.crateBrazier(ModBlocks.BRAZIER.get())
        this.crateBrazier(ModBlocks.SOUL_BRAZIER.get())
        this.createPaperWall(ModBlocks.PAPER_WALL.get())
        this.createHangingVines(ModBlocks.HANGING_VINES.get())
        this.createHangingVines(ModBlocks.HANGING_VINES_BODY.get())
        this.createExplorersTent(ModBlocks.EXPLORERS_TENT.get())
        defaultGenerators.createTrivialBlock(ModBlocks.DUNGEON_DOOR.get(), TexturedModel.PARTICLE_ONLY)

        defaultGenerators.createTrivialCube(ModBlocks.SPECIAL_SPAWNER.get())
        defaultGenerators.createTrivialCube(ModBlocks.BONE_PILE_BLOCK.get())

        this.createBonePile(ModBlocks.BONE_PILE.get())

        createMetalFramedGlass(
            ModBlocks.METAL_FRAMED_GLASS.get(),
            ModBlocks.METAL_FRAMED_GLASS_PANE.get()
        )

        this.createJar(ModBlocks.GLAZED_JAR.get())
        this.createJar(ModBlocks.CRACKED_GLAZED_JAR.get())

        for (color in DyeColor.entries) {
            this.createMetalFramedGlass(
                ModBlocks.COLORED_METAL_FRAMED_GLASS[color]!!.get(),
                ModBlocks.COLORED_METAL_FRAMED_GLASS_PANES[color]!!.get()
            )
            this.createSleepingBag(ModBlocks.SLEEPING_BAGS[color]!!.get())
            this.createJar(ModBlocks.COLORED_GLAZED_JARS[color]!!.get())
        }
    }

    private fun createPostVariants(postBlock: PostBlock, cutPostBlock: CutPostBlock) {
        val textureMapping = post(postBlock)

        this.createPost(postBlock, textureMapping)
        this.createCutPost(cutPostBlock, textureMapping)
    }

    private fun createPost(block: PostBlock, textureMapping: TextureMapping) {
        val model = ModModelTemplates.TEMPLATE_POST.createModel(block, textureMapping)
        val attachedModel = ModModelTemplates.TEMPLATE_POST_ATTACHED.createModel(block, textureMapping, "_attached")

        val attachedDispatch: PropertyDispatch = PropertyDispatch.property(PostBlock.ATTACHED)
            .select(true, Variant.variant().with(VariantProperties.MODEL, attachedModel))
            .select(false, Variant.variant().with(VariantProperties.MODEL, model))

        val rotationDispatch = PropertyDispatch.property(PostBlock.AXIS).generate { axis: Direction.Axis ->
            Variant.variant()
                .with(
                    VariantProperties.X_ROT,
                    if (axis === Direction.Axis.Y) VariantProperties.Rotation.R0 else VariantProperties.Rotation.R90
                )
                .with(
                    VariantProperties.Y_ROT,
                    if (axis === Direction.Axis.X) VariantProperties.Rotation.R90 else VariantProperties.Rotation.R0
                )
        }

        blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(attachedDispatch).with(rotationDispatch))
    }

    private fun createCutPost(block: CutPostBlock, textureMapping: TextureMapping) {
        val models = listOf(
            ModModelTemplates.TEMPLATE_CUT_POST_1.createModel(block, textureMapping, "_1"),
            ModModelTemplates.TEMPLATE_CUT_POST_2.createModel(block, textureMapping, "_2"),
            ModModelTemplates.TEMPLATE_CUT_POST_3.createModel(block, textureMapping, "_3"),
            ModModelTemplates.TEMPLATE_POST.createModel(block, textureMapping)
        )
        val attachedModels = listOf(
            ModModelTemplates.TEMPLATE_CUT_POST_1_ATTACHED.createModel(block, textureMapping, "_1_attached"),
            ModModelTemplates.TEMPLATE_CUT_POST_2_ATTACHED.createModel(block, textureMapping, "_2_attached"),
            ModModelTemplates.TEMPLATE_CUT_POST_3_ATTACHED.createModel(block, textureMapping, "_3_attached"),
            ModModelTemplates.TEMPLATE_POST_ATTACHED.createModel(block, textureMapping, "_attached")
        )

        val attachedDispatch = PropertyDispatch.properties(CutPostBlock.ATTACHED, CutPostBlock.PARTS)
            .generate { attached, parts ->
                Variant.variant()
                    .with(VariantProperties.MODEL, if (attached) attachedModels[parts - 1] else models[parts - 1])
            }

        val rotationDispatch = PropertyDispatch.property(BlockStateProperties.FACING).generate {
            Variant.variant()
                .with(
                    VariantProperties.X_ROT, when (it) {
                        Direction.DOWN -> VariantProperties.Rotation.R180
                        Direction.UP -> VariantProperties.Rotation.R0
                        else -> VariantProperties.Rotation.R90
                    }
                )
                .with(
                    VariantProperties.Y_ROT, when (it) {
                        Direction.SOUTH -> VariantProperties.Rotation.R180
                        Direction.WEST -> VariantProperties.Rotation.R270
                        Direction.EAST -> VariantProperties.Rotation.R90
                        else -> VariantProperties.Rotation.R0
                    }
                )
        }

        blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(attachedDispatch).with(rotationDispatch))
        this.delegateItemModel(block, models.first())
    }

    private fun crateBrazier(block: Block) {
        val model = ModModelTemplates.TEMPLATE_BRAZIER_OFF.createModel(block, brazier(block, false))
        val litModel = ModModelTemplates.TEMPLATE_BRAZIER.createModel(block, brazier(block, true), "_lit")

        val litDispatch: PropertyDispatch = PropertyDispatch.property(BlockStateProperties.LIT)
            .select(true, Variant.variant().with(VariantProperties.MODEL, litModel))
            .select(false, Variant.variant().with(VariantProperties.MODEL, model))

        blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(litDispatch))
    }

    private fun createBundledPosts(block: Block) {
        val mapping = bundledPosts(block)
        val model = ModelTemplates.CUBE_COLUMN.createModel(block, mapping)
        val modelHorizontal = ModelTemplates.CUBE_COLUMN_HORIZONTAL.createModel(block, mapping)
        blockStateOutput.accept(
            BlockModelGenerators.createRotatedPillarWithHorizontalVariant(block, model, modelHorizontal)
        )
    }

    private fun createMetalFramedGlass(glassBlock: Block, paneBlock: Block) {
        this.defaultGenerators.createTrivialCube(glassBlock)

        val texturemapping = metalFramedGlassPane(glassBlock)
        this.createPane(glassBlock, paneBlock, texturemapping)
    }

    private fun createPaperWall(block: Block) {
        val texturemapping = TextureMapping.pane(block, block)
        this.createPane(block, block, texturemapping)
    }

    private fun createPane(fullBlock: Block, paneBlock: Block, textureMapping: TextureMapping) {
        val postModel = ModelTemplates.STAINED_GLASS_PANE_POST.createModel(paneBlock, textureMapping)
        val paneSideModel = ModelTemplates.STAINED_GLASS_PANE_SIDE.createModel(paneBlock, textureMapping)
        val paneSideAltModel = ModelTemplates.STAINED_GLASS_PANE_SIDE_ALT.createModel(paneBlock, textureMapping)
        val paneNoSideModel = ModelTemplates.STAINED_GLASS_PANE_NOSIDE.createModel(paneBlock, textureMapping)
        val paneNoSideAltModel = ModelTemplates.STAINED_GLASS_PANE_NOSIDE_ALT.createModel(paneBlock, textureMapping)

        ModelTemplates.FLAT_ITEM.createModel(
            ModelLocationUtils.getModelLocation(paneBlock),
            TextureMapping.layer0(fullBlock)
        )

        blockStateOutput.accept(
            MultiPartGenerator.multiPart(paneBlock)
                .with(Variant.variant().with(VariantProperties.MODEL, postModel))
                .with(
                    Condition.condition().term(BlockStateProperties.NORTH, true),
                    Variant.variant().with(VariantProperties.MODEL, paneSideModel)
                )
                .with(
                    Condition.condition().term(BlockStateProperties.EAST, true),
                    Variant.variant().with(VariantProperties.MODEL, paneSideModel).with(
                        VariantProperties.Y_ROT, VariantProperties.Rotation.R90
                    )
                )
                .with(
                    Condition.condition().term(BlockStateProperties.SOUTH, true),
                    Variant.variant().with(VariantProperties.MODEL, paneSideAltModel)
                )
                .with(
                    Condition.condition().term(BlockStateProperties.WEST, true),
                    Variant.variant().with(VariantProperties.MODEL, paneSideAltModel).with(
                        VariantProperties.Y_ROT, VariantProperties.Rotation.R90
                    )
                )
                .with(
                    Condition.condition().term(BlockStateProperties.NORTH, false),
                    Variant.variant().with(VariantProperties.MODEL, paneNoSideModel)
                )
                .with(
                    Condition.condition().term(BlockStateProperties.EAST, false),
                    Variant.variant().with(VariantProperties.MODEL, paneNoSideAltModel)
                )
                .with(
                    Condition.condition().term(BlockStateProperties.SOUTH, false),
                    Variant.variant().with(VariantProperties.MODEL, paneNoSideAltModel).with(
                        VariantProperties.Y_ROT, VariantProperties.Rotation.R90
                    )
                )
                .with(
                    Condition.condition().term(BlockStateProperties.WEST, false),
                    Variant.variant().with(VariantProperties.MODEL, paneNoSideModel).with(
                        VariantProperties.Y_ROT, VariantProperties.Rotation.R270
                    )
                )
        )
    }

    private fun createSleepingBag(block: Block) {
        val textureMapping = sleepingBag(block)
        val modelFoot = ModModelTemplates.SLEEPING_BAG_FOOT.createModel(block, textureMapping, "_foot")
        val modelHead = ModModelTemplates.SLEEPING_BAG_HEAD.createModel(block, textureMapping, "_head")
        val modelInventory = ModModelTemplates.SLEEPING_BAG_INVENTORY.createModel(block, textureMapping, "_inventory")

        val dispatch = PropertyDispatch.property(BlockStateProperties.BED_PART)
            .select(BedPart.HEAD, Variant.variant().with(VariantProperties.MODEL, modelHead))
            .select(BedPart.FOOT, Variant.variant().with(VariantProperties.MODEL, modelFoot))

        blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(block)
                .with(dispatch)
                .with(BlockModelGenerators.createHorizontalFacingDispatch())
        )
        this.delegateItemModel(block, modelInventory)
    }

    private fun createHangingVines(block: Block) {
        val model = BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/")
        val attachedModel = BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/attached_")

        val dispatch = PropertyDispatch.property(ModBlockStateProperties.ATTACHED)
            .select(true, Variant.variant().with(VariantProperties.MODEL, attachedModel))
            .select(false, Variant.variant().with(VariantProperties.MODEL, model))

        blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(dispatch))

        if (block.asItem() !== Items.AIR) {
            ModelTemplates.FLAT_ITEM.createModel(
                ModelLocationUtils.getModelLocation(block.asItem()),
                TextureMapping.layer0(block)
            )
        }
    }

    private fun createExplorersTent(block: Block) {
        blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(
                block,
                Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block))
            )
        )
        ModelTemplates.TWO_LAYERED_ITEM.createModel(
            ModelLocationUtils.getModelLocation(block.asItem()),
            TextureMapping.layered(
                TextureMapping.getItemTexture(block.asItem()),
                TextureMapping.getItemTexture(block.asItem(), "_layer")
            )
        )
    }

    private fun createBonePile(block: Block) {
        ModelTemplates.FLAT_ITEM.createModel(block, TextureMapping.layer0(block))
        blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(
                block,
                Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block))
            )
        )

        ModelTemplates.CARPET.createModel(
            ModelLocationUtils.getModelLocation(block.asItem()),
            TextureMapping.wool(block)
        )
    }

    private fun createJar(block: Block) {
        val model = ModModelTemplates.TEMPLATE_JAR.createModel(block, jar(block))
        val rotatedModel = ModModelTemplates.TEMPLATE_JAR_ROTATED.createModel(block, jar(block), "_rotated")

        val dispatch = PropertyDispatch.property(ModBlockStateProperties.ROTATED)
            .select(true, Variant.variant().with(VariantProperties.MODEL, rotatedModel))
            .select(false, Variant.variant().with(VariantProperties.MODEL, model))

        blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(dispatch))
    }

    private fun createSimpleFlatItemModel(item: ItemLike) {
        ModelTemplates.FLAT_ITEM.createModel(
            ModelLocationUtils.getModelLocation(item.asItem()),
            TextureMapping.layer0(item.asItem())
        )
    }

    private fun delegateItemModel(block: Block, resourceLocation: ResourceLocation) {
        modelOutput.accept(ModelLocationUtils.getModelLocation(block.asItem()), DelegatedModel(resourceLocation))
    }
}
