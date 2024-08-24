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
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
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

    @Override
    public void generate(Consumer<BlockStateGenerator> blockStateOutput, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput) {
        this.blockStateOutput = blockStateOutput;
        this.modelOutput = modelOutput;

        for (ModBlocks.WoodType woodType : ModBlocks.WoodType.values()) {
            this.createPostVariants(ModBlocks.WOODEN_POSTS.get(woodType).get(), ModBlocks.CUT_WOODEN_POSTS.get(woodType).get());
            this.createPostVariants(ModBlocks.STRIPPED_WOODEN_POSTS.get(woodType).get(), ModBlocks.CUT_STRIPPED_WOODEN_POSTS.get(woodType).get());
        }

        BlockModelGenerators generators = new BlockModelGenerators(this.blockStateOutput, this.modelOutput, item -> {});

        generators.createNormalTorch(ModBlocks.UNLIT_TORCH.get(), ModBlocks.UNLIT_WALL_TORCH.get());
        generators.createNormalTorch(ModBlocks.UNLIT_SOUL_TORCH.get(), ModBlocks.UNLIT_SOUL_WALL_TORCH.get());
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

    static MultiVariantGenerator createSimpleBlock(Block block, ResourceLocation resourceLocation) {
        return MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, resourceLocation));
    }

    void delegateItemModel(Block block, ResourceLocation resourceLocation) {
        this.modelOutput.accept(ModelLocationUtils.getModelLocation(block.asItem()), new DelegatedModel(resourceLocation));
    }
}
