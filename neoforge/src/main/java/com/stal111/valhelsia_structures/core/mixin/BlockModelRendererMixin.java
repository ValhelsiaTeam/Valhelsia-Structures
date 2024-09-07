package com.stal111.valhelsia_structures.core.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.utils.BrightnessCombinerUtils;
import com.stal111.valhelsia_structures.utils.ModTags;
import com.stal111.valhelsia_structures.utils.PosBasedBrightnessCombiner;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Block Model Renderer Mixin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.mixin.BlockModelRendererMixin
 *
 * @author Valhelsia Team
 * @since 2021-03-25
 */
@Mixin(ModelBlockRenderer.class)
public abstract class BlockModelRendererMixin {

    @Shadow public abstract void tesselateWithAO(BlockAndTintGetter p_111079_, BakedModel p_111080_, BlockState p_111081_, BlockPos p_111082_, PoseStack p_111083_, VertexConsumer p_111084_, boolean p_111085_, RandomSource p_111086_, long p_111087_, int p_111088_);

    @Shadow public abstract void tesselateWithoutAO(BlockAndTintGetter p_111091_, BakedModel p_111092_, BlockState p_111093_, BlockPos p_111094_, PoseStack p_111095_, VertexConsumer p_111096_, boolean p_111097_, RandomSource p_111098_, long p_111099_, int p_111100_);

    @Inject(at = @At(value = "HEAD"), method = "tesselateBlock(Lnet/minecraft/world/level/BlockAndTintGetter;Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;ZLnet/minecraft/util/RandomSource;JILnet/neoforged/neoforge/client/model/data/ModelData;Lnet/minecraft/client/renderer/RenderType;)V", remap = false, cancellable = true)
    private void valhelsia_tesselateBlock(BlockAndTintGetter level, BakedModel model, BlockState state, BlockPos pos, PoseStack poseStack, VertexConsumer vertexConsumer, boolean checkSides, RandomSource random, long seed, int packedOverlay, ModelData modelData, RenderType renderType, CallbackInfo ci) {
        if (state.is(ModBlocks.BONE_PILE.get())) {
            poseStack.translate(0.0D, -0.53125D, 0.0D);

            for (int i = 0; i < state.getValue(ModBlockStateProperties.LAYERS_1_5); i++) {
                model = ModelBakerImplAccessor.createModelBakerImpl(Minecraft.getInstance().getModelManager().getModelBakery(), (resourceLocation, material) -> {
                    return Minecraft.getInstance().getTextureAtlas(material.atlasLocation()).apply(resourceLocation.id());
                }, ModelResourceLocation.inventory(ValhelsiaStructures.location("block/bone_pile"))).bake(ValhelsiaStructures.location("block/bone_pile"), BlockModelRotation.by(90, 90 * i));

                if (model == null) {
                    return;
                }

                boolean flag = Minecraft.useAmbientOcclusion() && state.getLightEmission(level, pos) == 0 && model.useAmbientOcclusion();

                poseStack.translate(0.0D, 0.0625D, 0.0D);
                modelData = model.getModelData(level, pos, state, modelData);

                try {
                    if (flag) {
                        this.tesselateWithAO(level, model, state, pos, poseStack, vertexConsumer, checkSides, random, seed, packedOverlay);
                    } else {
                        this.tesselateWithoutAO(level, model, state, pos, poseStack, vertexConsumer, checkSides, random, seed, packedOverlay);
                    }
                } catch (Throwable throwable) {
                    CrashReport crashreport = CrashReport.forThrowable(throwable, "Tesselating block model");
                    CrashReportCategory crashreportcategory = crashreport.addCategory("Block model being tesselated");
                    CrashReportCategory.populateBlockDetails(crashreportcategory, level, pos, state);
                    crashreportcategory.setDetail("Using AO", flag);
                    throw new ReportedException(crashreport);
                }
            }

            ci.cancel();
        }
    }

    @ModifyVariable(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/block/ModelBlockRenderer;putQuadData(Lnet/minecraft/world/level/BlockAndTintGetter;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lcom/mojang/blaze3d/vertex/VertexConsumer;Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lnet/minecraft/client/renderer/block/model/BakedQuad;FFFFIIIII)V"), method = "renderModelFaceFlat", ordinal = 0, argsOnly = true)
    private int valhelsia_renderModelFaceFlat(int value, BlockAndTintGetter level, BlockState state, BlockPos pos) {
        if (state.is(ModTags.Blocks.SLEEPING_BAGS)) {
            DoubleBlockCombiner.NeighborCombineResult<BlockPos> neighborCombineResult = BrightnessCombinerUtils.combineWithNeigbour(BedBlock::getBlockType, BedBlock::getConnectedDirection, ChestBlock.FACING, state, level, pos, (p_112202_, p_112203_) -> false);

            return neighborCombineResult.apply(new PosBasedBrightnessCombiner()).get(value);
        }

        return value;
    }
}