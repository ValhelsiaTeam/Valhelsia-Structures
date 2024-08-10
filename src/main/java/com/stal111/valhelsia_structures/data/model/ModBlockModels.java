package com.stal111.valhelsia_structures.data.model;

import com.google.gson.JsonElement;
import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Vahelsia Team - stal111
 * @since 28.07.2024
 */
public class ModBlockModels {

    private final BlockModelGenerators generators;
    private final Consumer<BlockStateGenerator> blockStateOutput;
    private final BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput;

    private ModBlockModels(BlockModelGenerators generators) {
        this.generators = generators;
        this.blockStateOutput = generators.blockStateOutput;
        this.modelOutput = generators.modelOutput;
    }

    public static void create(BlockModelGenerators generators) {
        new ModBlockModels(generators).createModels();
    }

    public void createModels() {
        ModBlocks.WOODEN_POSTS.forEach((woodType, entry) -> this.createPost(entry.get()));
        ModBlocks.STRIPPED_WOODEN_POSTS.forEach((woodType, entry) -> this.createPost(entry.get()));
    }

    private void createPost(PostBlock block) {
        TextureMapping textureMapping = ModTextureMapping.post(block);
        ResourceLocation model = ModModelTemplates.TEMPLATE_POST.create(block, textureMapping, this.modelOutput);
        ResourceLocation attachedModel = ModModelTemplates.TEMPLATE_ATTACHED_POST.createWithSuffix(block, "_attached", textureMapping, this.modelOutput);

        PropertyDispatch attachedDispatch = PropertyDispatch.property(PostBlock.ATTACHED)
                .select(true, Variant.variant().with(VariantProperties.MODEL, attachedModel))
                .select(false, Variant.variant().with(VariantProperties.MODEL, model));

        PropertyDispatch rotationDispatch = PropertyDispatch.property(PostBlock.AXIS).generate(axis -> Variant.variant()
                .with(VariantProperties.X_ROT, axis == Direction.Axis.Y ? VariantProperties.Rotation.R0 : VariantProperties.Rotation.R90)
                .with(VariantProperties.Y_ROT, axis == Direction.Axis.X ? VariantProperties.Rotation.R90 : VariantProperties.Rotation.R0));

        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(attachedDispatch).with(rotationDispatch));
    }

    static MultiVariantGenerator createSimpleBlock(Block block, ResourceLocation resourceLocation) {
        return MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, resourceLocation));
    }
}
