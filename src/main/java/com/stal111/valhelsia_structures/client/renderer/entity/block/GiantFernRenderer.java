package com.stal111.valhelsia_structures.client.renderer.entity.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.stal111.valhelsia_structures.client.model.block.GiantFernModel;
import com.stal111.valhelsia_structures.common.block.entity.GiantFernBlockEntity;
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * Giant Fern Renderer <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.renderer.entity.block.GiantFernRenderer
 *
 * @author Valhelsia Team
 * @since 2021-10-03
 */
public class GiantFernRenderer implements BlockEntityRenderer<GiantFernBlockEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(ValhelsiaStructures.MOD_ID, "textures/block/giant_fern.png");

    private final GiantFernModel model;

    public GiantFernRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new GiantFernModel(context.bakeLayer(GiantFernModel.GIANT_FERN));
    }

    @Override
    public void render(@Nonnull GiantFernBlockEntity blockEntity, float partialTicks, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        poseStack.pushPose();

        poseStack.translate(0.5D, 0.0D, 0.5D);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180));

        if (blockEntity.getBlockState().getValue(ModBlockStateProperties.ROTATED)) {
            poseStack.mulPose(Axis.YP.rotationDegrees(45));
        }

        this.model.renderToBuffer(poseStack, buffer.getBuffer(this.model.renderType(TEXTURE)), combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();
    }
}
