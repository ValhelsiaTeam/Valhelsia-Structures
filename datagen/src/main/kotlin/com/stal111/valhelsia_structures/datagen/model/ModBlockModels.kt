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
import net.minecraft.data.models.model.DelegatedModel
import net.minecraft.data.models.model.ModelLocationUtils
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BedPart
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.valhelsia.dataforge.model.BlockModelGenerator
import java.util.*

class ModBlockModels(private val defaultGenerators: BlockModelGenerators) : BlockModelGenerator(defaultGenerators) {
    override fun generate() {
        this.createSimpleFlatItemModel(ModBlocks.DUNGEON_DOOR.get())

        for (woodType in ModBlocks.WoodType.entries.toTypedArray()) {
            this.createPostVariants(
                ModBlocks.WOODEN_POSTS[woodType]!!.get(), ModBlocks.CUT_WOODEN_POSTS[woodType]!!.get()
            )
            this.createPostVariants(
                ModBlocks.STRIPPED_WOODEN_POSTS[woodType]!!
                    .get(), ModBlocks.CUT_STRIPPED_WOODEN_POSTS[woodType]!!.get()
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

        defaultGenerators.createTrivialCube(ModBlocks.SPECIAL_SPAWNER.get())
        defaultGenerators.createTrivialCube(ModBlocks.BONE_PILE_BLOCK.get())

        this.createBonePile(ModBlocks.BONE_PILE.get())

        createMetalFramedGlass(defaultGenerators, ModBlocks.METAL_FRAMED_GLASS.get(), ModBlocks.METAL_FRAMED_GLASS_PANE.get())

        this.createJar(ModBlocks.GLAZED_JAR.get())
        this.createJar(ModBlocks.CRACKED_GLAZED_JAR.get())

        for (color in DyeColor.entries) {
            this.createMetalFramedGlass(
                defaultGenerators,
                ModBlocks.COLORED_METAL_FRAMED_GLASS[color]!!.get(),
                ModBlocks.COLORED_METAL_FRAMED_GLASS_PANES[color]!!
                    .get()
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
        val model = ModModelTemplates.TEMPLATE_POST.create(block, textureMapping, this.modelOutput)
        val attachedModel = ModModelTemplates.TEMPLATE_POST_ATTACHED.createWithSuffix(
            block,
            "_attached",
            textureMapping,
            this.modelOutput
        )

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
        val models = Arrays.asList(
            ModModelTemplates.TEMPLATE_CUT_POST_1.createWithSuffix(block, "_1", textureMapping, this.modelOutput),
            ModModelTemplates.TEMPLATE_CUT_POST_2.createWithSuffix(block, "_2", textureMapping, this.modelOutput),
            ModModelTemplates.TEMPLATE_CUT_POST_3.createWithSuffix(block, "_3", textureMapping, this.modelOutput),
            ModModelTemplates.TEMPLATE_POST.create(block, textureMapping, this.modelOutput)
        )
        val attachedModels = Arrays.asList(
            ModModelTemplates.TEMPLATE_CUT_POST_1_ATTACHED.createWithSuffix(
                block,
                "_1_attached",
                textureMapping,
                this.modelOutput
            ),
            ModModelTemplates.TEMPLATE_CUT_POST_2_ATTACHED.createWithSuffix(
                block,
                "_2_attached",
                textureMapping,
                this.modelOutput
            ),
            ModModelTemplates.TEMPLATE_CUT_POST_3_ATTACHED.createWithSuffix(
                block,
                "_3_attached",
                textureMapping,
                this.modelOutput
            ),
            ModModelTemplates.TEMPLATE_POST_ATTACHED.createWithSuffix(
                block,
                "_attached",
                textureMapping,
                this.modelOutput
            )
        )

        val attachedDispatch = PropertyDispatch.properties(CutPostBlock.ATTACHED, CutPostBlock.PARTS)
            .generate { attached: Boolean, parts: Int ->
                Variant.variant()
                    .with(VariantProperties.MODEL, if (attached) attachedModels[parts - 1] else models[parts - 1])
            }

        val rotationDispatch = PropertyDispatch.property(BlockStateProperties.FACING).generate { facing: Direction? ->
            Variant.variant()
                .with(
                    VariantProperties.X_ROT, when (facing) {
                        Direction.DOWN -> VariantProperties.Rotation.R180
                        Direction.UP -> VariantProperties.Rotation.R0
                        else -> VariantProperties.Rotation.R90
                    }
                )
                .with(
                    VariantProperties.Y_ROT, when (facing) {
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
        val model = ModModelTemplates.TEMPLATE_BRAZIER_OFF.create(block, brazier(block, false), this.modelOutput)
        val litModel =
            ModModelTemplates.TEMPLATE_BRAZIER.createWithSuffix(block, "_lit", brazier(block, true), this.modelOutput)

        val litDispatch: PropertyDispatch = PropertyDispatch.property(BlockStateProperties.LIT)
            .select(true, Variant.variant().with(VariantProperties.MODEL, litModel))
            .select(false, Variant.variant().with(VariantProperties.MODEL, model))

        blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(litDispatch))
    }

    private fun createBundledPosts(block: Block) {
        val mapping = bundledPosts(block)
        val model = ModelTemplates.CUBE_COLUMN.create(block, mapping, this.modelOutput)
        val modelHorizontal = ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(block, mapping, this.modelOutput)
        blockStateOutput.accept(
            BlockModelGenerators.createRotatedPillarWithHorizontalVariant(
                block,
                model,
                modelHorizontal
            )
        )
    }

    private fun createMetalFramedGlass(generators: BlockModelGenerators, glassBlock: Block, paneBlock: Block) {
        generators.createTrivialCube(glassBlock)

        val texturemapping = metalFramedGlassPane(glassBlock)
        this.createPane(glassBlock, paneBlock, texturemapping)
    }

    private fun createPaperWall(block: Block) {
        val texturemapping = TextureMapping.pane(block, block)
        this.createPane(block, block, texturemapping)
    }

    private fun createPane(fullBlock: Block, paneBlock: Block, textureMapping: TextureMapping) {
        val postModel = ModelTemplates.STAINED_GLASS_PANE_POST.create(paneBlock, textureMapping, this.modelOutput)
        val paneSideModel = ModelTemplates.STAINED_GLASS_PANE_SIDE.create(paneBlock, textureMapping, this.modelOutput)
        val paneSideAltModel =
            ModelTemplates.STAINED_GLASS_PANE_SIDE_ALT.create(paneBlock, textureMapping, this.modelOutput)
        val paneNoSideModel =
            ModelTemplates.STAINED_GLASS_PANE_NOSIDE.create(paneBlock, textureMapping, this.modelOutput)
        val paneNoSideAltModel =
            ModelTemplates.STAINED_GLASS_PANE_NOSIDE_ALT.create(paneBlock, textureMapping, this.modelOutput)

        ModelTemplates.FLAT_ITEM.create(
            ModelLocationUtils.getModelLocation(paneBlock),
            TextureMapping.layer0(fullBlock),
            this.modelOutput
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
        val modelFoot =
            ModModelTemplates.SLEEPING_BAG_FOOT.createWithSuffix(block, "_foot", textureMapping, this.modelOutput)
        val modelHead =
            ModModelTemplates.SLEEPING_BAG_HEAD.createWithSuffix(block, "_head", textureMapping, this.modelOutput)
        val modelInventory = ModModelTemplates.SLEEPING_BAG_INVENTORY.createWithSuffix(
            block,
            "_inventory",
            textureMapping,
            this.modelOutput
        )

        val dispatch: PropertyDispatch = PropertyDispatch.property(BlockStateProperties.BED_PART)
            .select(BedPart.HEAD, Variant.variant().with(VariantProperties.MODEL, modelHead))
            .select(BedPart.FOOT, Variant.variant().with(VariantProperties.MODEL, modelFoot))

        blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(block).with(dispatch)
                .with(BlockModelGenerators.createHorizontalFacingDispatch())
        )
        this.delegateItemModel(block, modelInventory)
    }

    private fun createHangingVines(block: Block) {
        val dispatch: PropertyDispatch = PropertyDispatch.property(ModBlockStateProperties.ATTACHED)
            .select(
                true,
                Variant.variant()
                    .with(VariantProperties.MODEL, BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/attached_"))
            )
            .select(
                false,
                Variant.variant()
                    .with(VariantProperties.MODEL, BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/"))
            )

        blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(dispatch))

        if (block.asItem() !== Items.AIR) {
            ModelTemplates.FLAT_ITEM.create(
                ModelLocationUtils.getModelLocation(block.asItem()),
                TextureMapping.layer0(block),
                this.modelOutput
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
        ModelTemplates.TWO_LAYERED_ITEM.create(
            ModelLocationUtils.getModelLocation(block.asItem()), TextureMapping.layered(
                TextureMapping.getItemTexture(block.asItem()), TextureMapping.getItemTexture(block.asItem(), "_layer")
            ), this.modelOutput
        )
    }

    private fun createBonePile(block: Block) {
        ModelTemplates.FLAT_ITEM.create(block, TextureMapping.layer0(block), this.modelOutput)
        blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(
                block,
                Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block))
            )
        )

        ModelTemplates.CARPET.create(
            ModelLocationUtils.getModelLocation(block.asItem()),
            TextureMapping.wool(block),
            this.modelOutput
        )
    }

    private fun createJar(block: Block) {
        val model = ModModelTemplates.TEMPLATE_JAR.create(block, jar(block), this.modelOutput)
        val rotatedModel =
            ModModelTemplates.TEMPLATE_JAR_ROTATED.createWithSuffix(block, "_rotated", jar(block), this.modelOutput)

        val dispatch: PropertyDispatch = PropertyDispatch.property(ModBlockStateProperties.ROTATED)
            .select(true, Variant.variant().with(VariantProperties.MODEL, rotatedModel))
            .select(false, Variant.variant().with(VariantProperties.MODEL, model))

        blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(dispatch))
    }

    private fun createSimpleFlatItemModel(item: ItemLike) {
        ModelTemplates.FLAT_ITEM.create(
            ModelLocationUtils.getModelLocation(item.asItem()),
            TextureMapping.layer0(item.asItem()),
            this.modelOutput
        )
    }

    private fun delegateItemModel(block: Block, resourceLocation: ResourceLocation) {
        modelOutput.accept(ModelLocationUtils.getModelLocation(block.asItem()), DelegatedModel(resourceLocation))
    }

    companion object {
        fun createSimpleBlock(block: Block, resourceLocation: ResourceLocation): MultiVariantGenerator {
            return MultiVariantGenerator.multiVariant(
                block,
                Variant.variant().with(VariantProperties.MODEL, resourceLocation)
            )
        }
    }
}
