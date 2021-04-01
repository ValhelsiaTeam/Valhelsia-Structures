package com.stal111.valhelsia_structures.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelRotation;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.data.IModelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

/**
 * Block Model Renderer Mixin
 * Valhelsia Structures - com.stal111.valhelsia_structures.mixin.BlockModelRendererMixin
 *
 * @author Valhelsia Team
 * @version 16.2.0
 * @since 2021-03-25
 */
@Mixin(BlockModelRenderer.class)
public abstract class BlockModelRendererMixin {

    @Shadow public abstract boolean renderModelSmooth(IBlockDisplayReader worldIn, IBakedModel modelIn, BlockState stateIn, BlockPos posIn, MatrixStack matrixStackIn, IVertexBuilder buffer, boolean checkSides, Random randomIn, long rand, int combinedOverlayIn, IModelData modelData);

    @Shadow public abstract boolean renderModelFlat(IBlockDisplayReader worldIn, IBakedModel modelIn, BlockState stateIn, BlockPos posIn, MatrixStack matrixStackIn, IVertexBuilder buffer, boolean checkSides, Random randomIn, long rand, int combinedOverlayIn, IModelData modelData);

    @Inject(at = @At(value = "RETURN"), method = "renderModel(Lnet/minecraft/world/IBlockDisplayReader;Lnet/minecraft/client/renderer/model/IBakedModel;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lcom/mojang/blaze3d/matrix/MatrixStack;Lcom/mojang/blaze3d/vertex/IVertexBuilder;ZLjava/util/Random;JILnet/minecraftforge/client/model/data/IModelData;)Z", remap = false, cancellable = true)
    private void valhelsia_placeDousedTorch(IBlockDisplayReader world, IBakedModel model, BlockState state, BlockPos pos, MatrixStack matrix, IVertexBuilder buffer, boolean checkSides, Random randomIn, long rand, int combinedOverlay, IModelData modelData, CallbackInfoReturnable<Boolean> cir) {
        if (state.getBlock() == ModBlocks.BONE_PILE.get()) {
            for (int i = 1; i < state.get(ModBlockStateProperties.LAYERS_1_5); i++) {
                model = ModelLoader.instance().bake(new ResourceLocation(ValhelsiaStructures.MOD_ID, "block/bone_pile"), ModelRotation.getModelRotation(90, 90 * i));

                boolean flag = Minecraft.isAmbientOcclusionEnabled() && state.getLightValue(world, pos) == 0 && model.isAmbientOcclusion();

                matrix.translate(0.0D, 0.0625D, 0.0D);
                modelData = model.getModelData(world, pos, state, modelData);

                try {
                    cir.setReturnValue(flag ? this.renderModelSmooth(world, model, state, pos, matrix, buffer, checkSides, randomIn, rand, combinedOverlay, modelData) : this.renderModelFlat(world, model, state, pos, matrix, buffer, checkSides, randomIn, rand, combinedOverlay, modelData));
                } catch (Throwable throwable) {
                    CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Tesselating block model");
                    CrashReportCategory crashreportcategory = crashreport.makeCategory("Block model being tesselated");
                    CrashReportCategory.addBlockInfo(crashreportcategory, pos, state);
                    crashreportcategory.addDetail("Using AO", flag);
                    throw new ReportedException(crashreport);
                }
            }
        }
    }
}
