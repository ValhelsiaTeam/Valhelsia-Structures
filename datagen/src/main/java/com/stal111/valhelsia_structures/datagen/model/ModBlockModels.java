package com.stal111.valhelsia_structures.datagen.model;

import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.DelegatedModel;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.valhelsia.valhelsia_core.datagen.model.BlockModelGenerator;

import java.util.Arrays;

/**
 * @author Vahelsia Team - stal111
 * @since 28.07.2024
 */
public class ModBlockModels extends BlockModelGenerator {

    public ModBlockModels(BlockModelGenerators defaultGenerators) {
        super(defaultGenerators);
    }

    @Override
    public void generate() {
        this.createSimpleFlatItemModel(ModBlocks.DUNGEON_DOOR.get());

        for (ModBlocks.WoodType woodType : ModBlocks.WoodType.values()) {
            this.createPostVariants(ModBlocks.WOODEN_POSTS.get(woodType).get(), ModBlocks.CUT_WOODEN_POSTS.get(woodType).get());
            this.createPostVariants(ModBlocks.STRIPPED_WOODEN_POSTS.get(woodType).get(), ModBlocks.CUT_STRIPPED_WOODEN_POSTS.get(woodType).get());
            this.createBundledPosts(ModBlocks.BUNDLED_POSTS.get(woodType).get());
            this.createBundledPosts(ModBlocks.BUNDLED_STRIPPED_POSTS.get(woodType).get());
        }

        var generators = this.getDefaultGenerators();

//        generators.createNormalTorch(ModBlocks.UNLIT_TORCH.get(), ModBlocks.UNLIT_WALL_TORCH.get());
//        generators.createNormalTorch(ModBlocks.UNLIT_SOUL_TORCH.get(), ModBlocks.UNLIT_SOUL_WALL_TORCH.get());
//        generators.createLantern(ModBlocks.UNLIT_LANTERN.get());
//        generators.createLantern(ModBlocks.UNLIT_SOUL_LANTERN.get());

        this.crateBrazier(ModBlocks.BRAZIER.get());
        this.crateBrazier(ModBlocks.SOUL_BRAZIER.get());
        this.createPaperWall(ModBlocks.PAPER_WALL.get());
        this.createHangingVines(ModBlocks.HANGING_VINES.get());
        this.createHangingVines(ModBlocks.HANGING_VINES_BODY.get());
        this.createExplorersTent(ModBlocks.EXPLORERS_TENT.get());

        generators.createTrivialCube(ModBlocks.SPECIAL_SPAWNER.get());
        generators.createTrivialCube(ModBlocks.BONE_PILE_BLOCK.get());

        this.createBonePile(ModBlocks.BONE_PILE.get());

        createMetalFramedGlass(generators, ModBlocks.METAL_FRAMED_GLASS.get(), ModBlocks.METAL_FRAMED_GLASS_PANE.get());

        for (DyeColor color : DyeColor.values()) {
            this.createMetalFramedGlass(generators, ModBlocks.COLORED_METAL_FRAMED_GLASS.get(color).get(), ModBlocks.COLORED_METAL_FRAMED_GLASS_PANES.get(color).get());
            this.createSleepingBag(ModBlocks.SLEEPING_BAGS.get(color).get());
        }
    }

    private void createPostVariants(PostBlock postBlock, CutPostBlock cutPostBlock) {
        TextureMapping textureMapping = ModTextureMapping.post(postBlock);

        this.createPost(postBlock, textureMapping);
        this.createCutPost(cutPostBlock, textureMapping);
    }

    private void createPost(PostBlock block, TextureMapping textureMapping) {
        ResourceLocation model = ModModelTemplates.TEMPLATE_POST.create(block, textureMapping, this.modelOutput);
        ResourceLocation attachedModel = ModModelTemplates.TEMPLATE_POST_ATTACHED.createWithSuffix(block, "_attached", textureMapping, this.modelOutput);

        PropertyDispatch attachedDispatch = PropertyDispatch.property(PostBlock.ATTACHED)
                .select(true, Variant.variant().with(VariantProperties.MODEL, attachedModel))
                .select(false, Variant.variant().with(VariantProperties.MODEL, model));

        PropertyDispatch rotationDispatch = PropertyDispatch.property(PostBlock.AXIS).generate(axis -> Variant.variant()
                .with(VariantProperties.X_ROT, axis == Direction.Axis.Y ? VariantProperties.Rotation.R0 : VariantProperties.Rotation.R90)
                .with(VariantProperties.Y_ROT, axis == Direction.Axis.X ? VariantProperties.Rotation.R90 : VariantProperties.Rotation.R0));

        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(attachedDispatch).with(rotationDispatch));
    }

    private void createCutPost(CutPostBlock block, TextureMapping textureMapping) {
        var models = Arrays.asList(
                ModModelTemplates.TEMPLATE_CUT_POST_1.createWithSuffix(block, "_1", textureMapping, this.modelOutput),
                ModModelTemplates.TEMPLATE_CUT_POST_2.createWithSuffix(block, "_2", textureMapping, this.modelOutput),
                ModModelTemplates.TEMPLATE_CUT_POST_3.createWithSuffix(block, "_3", textureMapping, this.modelOutput),
                ModModelTemplates.TEMPLATE_POST.create(block, textureMapping, this.modelOutput)
        );
        var attachedModels = Arrays.asList(
                ModModelTemplates.TEMPLATE_CUT_POST_1_ATTACHED.createWithSuffix(block, "_1_attached", textureMapping, this.modelOutput),
                ModModelTemplates.TEMPLATE_CUT_POST_2_ATTACHED.createWithSuffix(block, "_2_attached", textureMapping, this.modelOutput),
                ModModelTemplates.TEMPLATE_CUT_POST_3_ATTACHED.createWithSuffix(block, "_3_attached", textureMapping, this.modelOutput),
                ModModelTemplates.TEMPLATE_POST_ATTACHED.createWithSuffix(block, "_attached", textureMapping, this.modelOutput)
        );

        PropertyDispatch attachedDispatch = PropertyDispatch.properties(CutPostBlock.ATTACHED, CutPostBlock.PARTS)
                .generate((attached, parts) -> Variant.variant().with(VariantProperties.MODEL, attached ? attachedModels.get(parts - 1) : models.get(parts - 1)));

        PropertyDispatch rotationDispatch = PropertyDispatch.property(BlockStateProperties.FACING).generate(facing -> Variant.variant()
                .with(VariantProperties.X_ROT, switch (facing) {
                    case DOWN -> VariantProperties.Rotation.R180;
                    case UP -> VariantProperties.Rotation.R0;
                    default -> VariantProperties.Rotation.R90;
                })
                .with(VariantProperties.Y_ROT, switch (facing) {
                    case SOUTH -> VariantProperties.Rotation.R180;
                    case WEST -> VariantProperties.Rotation.R270;
                    case EAST -> VariantProperties.Rotation.R90;
                    default -> VariantProperties.Rotation.R0;
                }));

        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(attachedDispatch).with(rotationDispatch));
        this.delegateItemModel(block, models.getFirst());
    }

    private void crateBrazier(Block block) {
        ResourceLocation model = ModModelTemplates.TEMPLATE_BRAZIER_OFF.create(block, ModTextureMapping.brazier(block, false), this.modelOutput);
        ResourceLocation litModel = ModModelTemplates.TEMPLATE_BRAZIER.createWithSuffix(block, "_lit", ModTextureMapping.brazier(block, true), this.modelOutput);

        PropertyDispatch litDispatch = PropertyDispatch.property(BlockStateProperties.LIT)
                .select(true, Variant.variant().with(VariantProperties.MODEL, litModel))
                .select(false, Variant.variant().with(VariantProperties.MODEL, model));

        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(litDispatch));
    }

    private void createBundledPosts(Block block) {
        TextureMapping mapping = ModTextureMapping.bundledPosts(block);
        ResourceLocation model = ModelTemplates.CUBE_COLUMN.create(block, mapping, this.modelOutput);
        ResourceLocation modelHorizontal = ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(block, mapping, this.modelOutput);
//        this.blockStateOutput.accept(BlockModelGenerators.createRotatedPillarWithHorizontalVariant(block, model, modelHorizontal));
    }

    private void createMetalFramedGlass(BlockModelGenerators generators, Block glassBlock, Block paneBlock) {
        generators.createTrivialCube(glassBlock);

        TextureMapping texturemapping = ModTextureMapping.metalFramedGlassPane(glassBlock);
        this.createPane(glassBlock, paneBlock, texturemapping);
    }

    private void createPaperWall(Block block) {
        TextureMapping texturemapping = TextureMapping.pane(block, block);
        this.createPane(block, block, texturemapping);
    }

    private void createPane(Block fullBlock, Block paneBlock, TextureMapping textureMapping) {
        ResourceLocation postModel = ModelTemplates.STAINED_GLASS_PANE_POST.create(paneBlock, textureMapping, this.modelOutput);
        ResourceLocation paneSideModel = ModelTemplates.STAINED_GLASS_PANE_SIDE.create(paneBlock, textureMapping, this.modelOutput);
        ResourceLocation paneSideAltModel = ModelTemplates.STAINED_GLASS_PANE_SIDE_ALT.create(paneBlock, textureMapping, this.modelOutput);
        ResourceLocation paneNoSideModel = ModelTemplates.STAINED_GLASS_PANE_NOSIDE.create(paneBlock, textureMapping, this.modelOutput);
        ResourceLocation paneNoSideAltModel = ModelTemplates.STAINED_GLASS_PANE_NOSIDE_ALT.create(paneBlock, textureMapping, this.modelOutput);

        ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(paneBlock), TextureMapping.layer0(fullBlock), this.modelOutput);

        this.blockStateOutput.accept(MultiPartGenerator.multiPart(paneBlock)
                .with(Variant.variant().with(VariantProperties.MODEL, postModel))
                .with(Condition.condition().term(BlockStateProperties.NORTH, true), Variant.variant().with(VariantProperties.MODEL, paneSideModel))
                .with(Condition.condition().term(BlockStateProperties.EAST, true), Variant.variant().with(VariantProperties.MODEL, paneSideModel).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                .with(Condition.condition().term(BlockStateProperties.SOUTH, true), Variant.variant().with(VariantProperties.MODEL, paneSideAltModel))
                .with(Condition.condition().term(BlockStateProperties.WEST, true), Variant.variant().with(VariantProperties.MODEL, paneSideAltModel).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                .with(Condition.condition().term(BlockStateProperties.NORTH, false), Variant.variant().with(VariantProperties.MODEL, paneNoSideModel))
                .with(Condition.condition().term(BlockStateProperties.EAST, false), Variant.variant().with(VariantProperties.MODEL, paneNoSideAltModel))
                .with(Condition.condition().term(BlockStateProperties.SOUTH, false), Variant.variant().with(VariantProperties.MODEL, paneNoSideAltModel).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                .with(Condition.condition().term(BlockStateProperties.WEST, false), Variant.variant().with(VariantProperties.MODEL, paneNoSideModel).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
        );
    }

    private void createSleepingBag(Block block) {
        TextureMapping textureMapping = ModTextureMapping.sleepingBag(block);
        ResourceLocation modelFoot = ModModelTemplates.SLEEPING_BAG_FOOT.createWithSuffix(block, "_foot", textureMapping, this.modelOutput);
        ResourceLocation modelHead = ModModelTemplates.SLEEPING_BAG_HEAD.createWithSuffix(block, "_head", textureMapping, this.modelOutput);
        ResourceLocation modelInventory = ModModelTemplates.SLEEPING_BAG_INVENTORY.createWithSuffix(block, "_inventory", textureMapping, this.modelOutput);

        PropertyDispatch dispatch = PropertyDispatch.property(BlockStateProperties.BED_PART)
                .select(BedPart.HEAD, Variant.variant().with(VariantProperties.MODEL, modelHead))
                .select(BedPart.FOOT, Variant.variant().with(VariantProperties.MODEL, modelFoot));

//        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(dispatch).with(BlockModelGenerators.createHorizontalFacingDispatch()));
        this.delegateItemModel(block, modelInventory);
    }

    private void createHangingVines(Block block) {
        PropertyDispatch dispatch = PropertyDispatch.property(ModBlockStateProperties.ATTACHED)
                .select(true, Variant.variant().with(VariantProperties.MODEL, BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/attached_")))
                .select(false, Variant.variant().with(VariantProperties.MODEL, BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/")));

        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(dispatch));

        if (block.asItem() != Items.AIR) {
            ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(block.asItem()), TextureMapping.layer0(block), this.modelOutput);
        }
    }

    private void createExplorersTent(Block block) {
        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block))));
        ModelTemplates.TWO_LAYERED_ITEM.create(ModelLocationUtils.getModelLocation(block.asItem()), TextureMapping.layered(TextureMapping.getItemTexture(block.asItem()), TextureMapping.getItemTexture(block.asItem(), "_layer")), this.modelOutput);
    }

    private void createBonePile(Block block) {
        ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(block), TextureMapping.layer0(block), this.modelOutput);
        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block))));

        ModelTemplates.CARPET.create(ModelLocationUtils.getModelLocation(block.asItem()), TextureMapping.wool(block), this.modelOutput);
    }

    static MultiVariantGenerator createSimpleBlock(Block block, ResourceLocation resourceLocation) {
        return MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, resourceLocation));
    }

    void createSimpleFlatItemModel(ItemLike item) {
        ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(item.asItem()), TextureMapping.layer0(item.asItem()), this.modelOutput);
    }

    void delegateItemModel(Block block, ResourceLocation resourceLocation) {
        this.modelOutput.accept(ModelLocationUtils.getModelLocation(block.asItem()), new DelegatedModel(resourceLocation));
    }
}
