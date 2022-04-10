package com.stal111.valhelsia_structures.core.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.utils.ModTags;
import com.stal111.valhelsia_structures.utils.BrightnessCombinerUtils;
import com.stal111.valhelsia_structures.utils.PosBasedBrightnessCombiner;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.client.model.data.IModelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

/**
 * Block Model Renderer Mixin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.mixin.BlockModelRendererMixin
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.2.0
 * @since 2021-03-25
 */
@Mixin(ModelBlockRenderer.class)
public abstract class BlockModelRendererMixin {

    @Shadow public abstract boolean tesselateWithAO(BlockAndTintGetter pLevel, BakedModel pModel, BlockState pState, BlockPos pPos, PoseStack pMatrixStack, VertexConsumer pBuffer, boolean pCheckSides, Random pRandom, long pRand, int pCombinedOverlay);

    @Shadow public abstract boolean tesselateWithoutAO(BlockAndTintGetter pLevel, BakedModel pModel, BlockState pState, BlockPos pPos, PoseStack pMatrixStack, VertexConsumer pBuffer, boolean pCheckSides, Random pRandom, long pRand, int pCombinedOverlay);

    @Inject(at = @At(value = "RETURN"), method = "tesselateBlock(Lnet/minecraft/world/level/BlockAndTintGetter;Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;ZLjava/util/Random;JILnet/minecraftforge/client/model/data/IModelData;)Z", remap = false, cancellable = true)
    private void valhelsia_tesselateBlock(BlockAndTintGetter level, BakedModel model, BlockState state, BlockPos pos, PoseStack matrix, VertexConsumer buffer, boolean checkSides, Random randomIn, long rand, int combinedOverlay, IModelData modelData, CallbackInfoReturnable<Boolean> cir) {
        if (state.getBlock() == ModBlocks.BONE_PILE.get()) {
            for (int i = 1; i < state.getValue(ModBlockStateProperties.LAYERS_1_5); i++) {
                model = ForgeModelBakery.instance().bake(new ResourceLocation(ValhelsiaStructures.MOD_ID, "block/bone_pile"), BlockModelRotation.by(90, 90 * i));

                if (model != null) {
                    boolean flag = Minecraft.useAmbientOcclusion() && state.getLightEmission(level, pos) == 0 && model.useAmbientOcclusion();

                    matrix.translate(0.0D, 0.0625D, 0.0D);
                    modelData = model.getModelData(level, pos, state, modelData);

                    try {
                        cir.setReturnValue(flag ? this.tesselateWithAO(level, model, state, pos, matrix, buffer, checkSides, randomIn, rand, combinedOverlay) : this.tesselateWithoutAO(level, model, state, pos, matrix, buffer, checkSides, randomIn, rand, combinedOverlay));
                    } catch (Throwable throwable) {
                        CrashReport crashreport = CrashReport.forThrowable(throwable, "Tesselating block model");
                        CrashReportCategory crashreportcategory = crashreport.addCategory("Block model being tesselated");
                        CrashReportCategory.populateBlockDetails(crashreportcategory, level, pos, state);
                        crashreportcategory.setDetail("Using AO", flag);
                        throw new ReportedException(crashreport);
                    }
                }
            }
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