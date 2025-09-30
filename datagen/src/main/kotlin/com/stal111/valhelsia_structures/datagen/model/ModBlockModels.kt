package com.stal111.valhelsia_structures.datagen.model

import com.stal111.valhelsia_structures.common.block.CutPostBlock
import com.stal111.valhelsia_structures.common.block.PostBlock
import com.stal111.valhelsia_structures.common.block.entity.ExplorersTentBlockEntity
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties
import com.stal111.valhelsia_structures.core.init.ModBlocks
import com.stal111.valhelsia_structures.datagen.model.ModTextureMapping.brazier
import com.stal111.valhelsia_structures.datagen.model.ModTextureMapping.bundledPosts
import com.stal111.valhelsia_structures.datagen.model.ModTextureMapping.jar
import com.stal111.valhelsia_structures.datagen.model.ModTextureMapping.metalFramedGlassPane
import com.stal111.valhelsia_structures.datagen.model.ModTextureMapping.post
import com.stal111.valhelsia_structures.datagen.model.ModTextureMapping.sleepingBag
import net.minecraft.client.color.item.Dye
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.BlockModelGenerators.plainVariant
import net.minecraft.client.data.models.MultiVariant
import net.minecraft.client.data.models.blockstates.MultiPartGenerator
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator
import net.minecraft.client.data.models.blockstates.PropertyDispatch
import net.minecraft.client.data.models.model.*
import net.minecraft.client.renderer.block.model.VariantMutator
import net.minecraft.core.Direction
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BedPart
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.valhelsia.dataforge.model.BlockModelGenerator
import net.valhelsia.dataforge.model.createModel

class ModBlockModels(private val defaultGenerators: BlockModelGenerators) : BlockModelGenerator(defaultGenerators) {
    override fun generate() {
        defaultGenerators.registerSimpleFlatItemModel(ModBlocks.DUNGEON_DOOR.get().asItem())

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
        defaultGenerators.createParticleOnlyBlock(ModBlocks.DUNGEON_DOOR.get())
        defaultGenerators.createParticleOnlyBlock(ModBlocks.DUNGEON_DOOR_LEAF.get())

        blockStateOutput.accept(
            MultiVariantGenerator.dispatch(
                ModBlocks.GIANT_FERN.get(),
                BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.GIANT_FERN.get()))
            )
        )

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
        val model = plainVariant(ModModelTemplates.TEMPLATE_POST.createModel(block, textureMapping))
        val attachedModel = plainVariant(ModModelTemplates.TEMPLATE_POST_ATTACHED.createModel(block, textureMapping, "_attached"))

        val attachedDispatch: PropertyDispatch<MultiVariant> = PropertyDispatch.initial(PostBlock.ATTACHED)
            .select(true, attachedModel)
            .select(false, model)

        val rotationDispatch = PropertyDispatch.modify(PostBlock.AXIS).generate { axis: Direction.Axis ->
            when (axis) {
                Direction.Axis.X -> BlockModelGenerators.X_ROT_90.then(BlockModelGenerators.Y_ROT_90)
                Direction.Axis.Z -> BlockModelGenerators.X_ROT_90
                else -> BlockModelGenerators.NOP
            }
        }

        blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(attachedDispatch).with(rotationDispatch))
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

        val attachedDispatch = PropertyDispatch.initial(CutPostBlock.ATTACHED, CutPostBlock.PARTS)
            .generate { attached, parts ->
                plainVariant(if (attached) attachedModels[parts - 1] else models[parts - 1])
            }

        val rotationDispatch = PropertyDispatch.modify(BlockStateProperties.FACING).generate {
            when (it) {
                Direction.DOWN -> BlockModelGenerators.X_ROT_180
                Direction.SOUTH -> BlockModelGenerators.Y_ROT_180.then(BlockModelGenerators.X_ROT_90)
                Direction.WEST -> BlockModelGenerators.Y_ROT_270.then(BlockModelGenerators.X_ROT_90)
                Direction.EAST -> BlockModelGenerators.Y_ROT_90.then(BlockModelGenerators.X_ROT_90)
                Direction.NORTH -> BlockModelGenerators.X_ROT_90
                else -> BlockModelGenerators.NOP
            }
        }

        blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(attachedDispatch).with(rotationDispatch))
        this.delegateItemModel(block, models.first())
    }

    private fun crateBrazier(block: Block) {
        val model = BlockModelGenerators.plainVariant(ModModelTemplates.TEMPLATE_BRAZIER_OFF.createModel(block, brazier(block, false)))
        val litModel = BlockModelGenerators.plainVariant(ModModelTemplates.TEMPLATE_BRAZIER.createModel(block, brazier(block, true), "_lit"))

        val litDispatch: PropertyDispatch<MultiVariant> = PropertyDispatch.initial(BlockStateProperties.LIT)
            .select(true, litModel)
            .select(false, model)

        blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(litDispatch))
    }

    private fun createBundledPosts(block: Block) {
        val mapping = bundledPosts(block)
        val model = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_COLUMN.createModel(block, mapping))
        val modelHorizontal = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_COLUMN_HORIZONTAL.createModel(block, mapping))
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
        val postModel = plainVariant(ModelTemplates.STAINED_GLASS_PANE_POST.createModel(paneBlock, textureMapping))
        val paneSideModel = plainVariant(ModelTemplates.STAINED_GLASS_PANE_SIDE.createModel(paneBlock, textureMapping))
        val paneSideAltModel = plainVariant(ModelTemplates.STAINED_GLASS_PANE_SIDE_ALT.createModel(paneBlock, textureMapping))
        val paneNoSideModel = plainVariant(ModelTemplates.STAINED_GLASS_PANE_NOSIDE.createModel(paneBlock, textureMapping))
        val paneNoSideAltModel = plainVariant(ModelTemplates.STAINED_GLASS_PANE_NOSIDE_ALT.createModel(paneBlock, textureMapping))

        ModelTemplates.FLAT_ITEM.createModel(
            ModelLocationUtils.getModelLocation(paneBlock),
            TextureMapping.layer0(fullBlock)
        )

        this.blockStateOutput
            .accept(
                MultiPartGenerator.multiPart(paneBlock)
                    .with(postModel)
                    .with(
                        BlockModelGenerators.condition().term(BlockStateProperties.NORTH, true),
                        paneSideModel
                    )
                    .with(
                        BlockModelGenerators.condition().term(BlockStateProperties.EAST, true),
                        paneSideModel.with(BlockModelGenerators.Y_ROT_90)
                    )
                    .with(
                        BlockModelGenerators.condition().term(BlockStateProperties.SOUTH, true),
                        paneSideAltModel
                    )
                    .with(
                        BlockModelGenerators.condition().term(BlockStateProperties.WEST, true),
                        paneSideAltModel.with(BlockModelGenerators.Y_ROT_90)
                    )
                    .with(
                        BlockModelGenerators.condition().term(BlockStateProperties.NORTH, false),
                        paneNoSideModel
                    )
                    .with(
                        BlockModelGenerators.condition().term(BlockStateProperties.EAST, false),
                        paneNoSideAltModel
                    )
                    .with(
                        BlockModelGenerators.condition().term(BlockStateProperties.SOUTH, false),
                        paneNoSideAltModel.with(BlockModelGenerators.Y_ROT_90)
                    )
                    .with(
                        BlockModelGenerators.condition().term(BlockStateProperties.WEST, false),
                        paneNoSideModel.with(BlockModelGenerators.Y_ROT_270)
                    )
            )
    }

    private fun createSleepingBag(block: Block) {
        val textureMapping = sleepingBag(block)
        val modelFoot = plainVariant(ModModelTemplates.SLEEPING_BAG_FOOT.createModel(block, textureMapping, "_foot"))
        val modelHead = plainVariant(ModModelTemplates.SLEEPING_BAG_HEAD.createModel(block, textureMapping, "_head"))
        val modelInventory = ModModelTemplates.SLEEPING_BAG_INVENTORY.createModel(block, textureMapping, "_inventory")

        val dispatch = PropertyDispatch.initial(BlockStateProperties.BED_PART)
            .select(BedPart.HEAD,  modelHead)
            .select(BedPart.FOOT, modelFoot)


        blockStateOutput.accept(
            MultiVariantGenerator.dispatch(block)
                .with(dispatch)
                .with(ROTATION_HORIZONTAL_FACING)
        )
        defaultGenerators.registerSimpleItemModel(block, modelInventory)
    }

    private fun createHangingVines(block: Block) {
        val model = plainVariant(BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/"))
        val attachedModel = plainVariant(BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/attached_"))

        val dispatch = PropertyDispatch.initial(ModBlockStateProperties.ATTACHED)
            .select(true,  attachedModel)
            .select(false, model)

        blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(dispatch))

        if (block.asItem() !== Items.AIR) {
            defaultGenerators.registerSimpleTintedItemModel(
                block,
                defaultGenerators.createFlatItemModelWithBlockTexture(block.asItem(), block),
                ItemModelUtils.constantTint(-12012264)
            )
        }
    }

    private fun createExplorersTent(block: Block) {
        blockStateOutput.accept(
            MultiVariantGenerator.dispatch(
                block,
                plainVariant(ModelLocationUtils.getModelLocation(block))
            )
        )
        defaultGenerators.itemModelOutput.accept(
            block.asItem(),
            ItemModelUtils.tintedModel(
                createFlatItemModelWithOverlay(block.asItem(), "_layer"),
                Dye(ExplorersTentBlockEntity.DEFAULT_COLOR)
            )
        )
    }

    private fun createBonePile(block: Block) {
        ModelTemplates.FLAT_ITEM.createModel(block, TextureMapping.layer0(block))
        blockStateOutput.accept(
            MultiVariantGenerator.dispatch(
                block,
                plainVariant(ModelLocationUtils.getModelLocation(block))
            )
        )

        ModelTemplates.CARPET.createModel(
            ModelLocationUtils.getModelLocation(block.asItem()),
            TextureMapping.wool(block)
        )
    }

    private fun createJar(block: Block) {
        val model = plainVariant(ModModelTemplates.TEMPLATE_JAR.createModel(block, jar(block)))
        val rotatedModel = plainVariant(ModModelTemplates.TEMPLATE_JAR_ROTATED.createModel(block, jar(block), "_rotated"))

        val dispatch = PropertyDispatch.initial(ModBlockStateProperties.ROTATED)
            .select(true, rotatedModel)
            .select(false, model)

        blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(dispatch))
    }

    private fun delegateItemModel(block: Block, resourceLocation: ResourceLocation) {
        modelOutput.accept(ModelLocationUtils.getModelLocation(block.asItem()), DelegatedModel(resourceLocation))
    }

    fun createFlatItemModelWithOverlay(item: Item, suffix: String): ResourceLocation {
        val texture = TextureMapping.getItemTexture(item)
        val overlayTexture = TextureMapping.getItemTexture(item, suffix)

        return ModelTemplates.TWO_LAYERED_ITEM.create(
            ModelLocationUtils.getModelLocation(item),
            TextureMapping.layered(texture, overlayTexture),
            this.modelOutput
        )
    }

    companion object {
        val ROTATION_HORIZONTAL_FACING: PropertyDispatch<VariantMutator> =
            PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
                .select(Direction.EAST, BlockModelGenerators.Y_ROT_90)
                .select(Direction.SOUTH, BlockModelGenerators.Y_ROT_180)
                .select(Direction.WEST, BlockModelGenerators.Y_ROT_270)
                .select(Direction.NORTH, BlockModelGenerators.NOP)
    }
}
