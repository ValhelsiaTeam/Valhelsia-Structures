package com.stal111.valhelsia_structures.data.model;

import com.google.gson.JsonElement;
import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.DelegatedModel;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.valhelsia.valhelsia_core.datagen.model.BlockModelGenerator;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Vahelsia Team - stal111
 * @since 28.07.2024
 */
public class ModBlockModels implements BlockModelGenerator {

    private Consumer<BlockStateGenerator> blockStateOutput;
    private BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput;

    static MultiVariantGenerator createSimpleBlock(Block block, ResourceLocation resourceLocation) {
        return MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, resourceLocation));
    }

    @Override
    public void generate(Consumer<BlockStateGenerator> blockStateOutput, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput) {
        this.blockStateOutput = blockStateOutput;
        this.modelOutput = modelOutput;

        for (ModBlocks.WoodType woodType : ModBlocks.WoodType.values()) {
            this.createPostVariants(ModBlocks.WOODEN_POSTS.get(woodType).get(), ModBlocks.CUT_WOODEN_POSTS.get(woodType).get());
            this.createPostVariants(ModBlocks.STRIPPED_WOODEN_POSTS.get(woodType).get(), ModBlocks.CUT_STRIPPED_WOODEN_POSTS.get(woodType).get());
            this.createBundledPosts(ModBlocks.BUNDLED_POSTS.get(woodType).get());
            this.createBundledPosts(ModBlocks.BUNDLED_STRIPPED_POSTS.get(woodType).get());
        }

        BlockModelGenerators generators = new BlockModelGenerators(this.blockStateOutput, this.modelOutput, item -> {});

        generators.createNormalTorch(ModBlocks.UNLIT_TORCH.get(), ModBlocks.UNLIT_WALL_TORCH.get());
        generators.createNormalTorch(ModBlocks.UNLIT_SOUL_TORCH.get(), ModBlocks.UNLIT_SOUL_WALL_TORCH.get());

        this.crateBrazier(ModBlocks.BRAZIER.get());
        this.crateBrazier(ModBlocks.SOUL_BRAZIER.get());
        this.createPaperWall(ModBlocks.PAPER_WALL.get());

        generators.createTrivialCube(ModBlocks.SPECIAL_SPAWNER.get());
        createMetalFramedGlass(generators, ModBlocks.METAL_FRAMED_GLASS.get(), ModBlocks.METAL_FRAMED_GLASS_PANE.get());

        for (DyeColor color : DyeColor.values()) {
            createMetalFramedGlass(generators, ModBlocks.COLORED_METAL_FRAMED_GLASS.get(color).get(), ModBlocks.COLORED_METAL_FRAMED_GLASS_PANES.get(color).get());
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
        this.blockStateOutput.accept(BlockModelGenerators.createRotatedPillarWithHorizontalVariant(block, model, modelHorizontal));
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

    void delegateItemModel(Block block, ResourceLocation resourceLocation) {
        this.modelOutput.accept(ModelLocationUtils.getModelLocation(block.asItem()), new DelegatedModel(resourceLocation));
    }
}
