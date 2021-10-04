package com.stal111.valhelsia_structures.client.renderer.entity.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.stal111.valhelsia_structures.client.model.block.ExplorersTentModel;
import com.stal111.valhelsia_structures.common.block.ExplorersTentBlock;
import com.stal111.valhelsia_structures.common.block.entity.ExplorersTentBlockEntity;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * Explorers Tent Renderer <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.renderer.entity.block.Explorers Tent Renderer
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-10-03
 */
public class ExplorersTentRenderer implements BlockEntityRenderer<ExplorersTentBlockEntity> {

    private static final ResourceLocation TENT_TEXTURE = new ResourceLocation(ValhelsiaStructures.MOD_ID, "textures/block/explorers_tent.png");
    private static final ResourceLocation TENT_STICKS_TEXTURE = new ResourceLocation(ValhelsiaStructures.MOD_ID, "textures/block/explorers_tent_sticks.png");

    private final ExplorersTentModel model;

    public ExplorersTentRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new ExplorersTentModel(context.bakeLayer(ExplorersTentModel.EXPLORERS_TENT));
    }

    @Override
    public void render(@Nonnull ExplorersTentBlockEntity blockEntity, float partialTicks, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        poseStack.pushPose();

        poseStack.translate(0.5D, 1.8D, 0.5D);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(blockEntity.getBlockState().getValue(ExplorersTentBlock.FACING).toYRot() + 90));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180));

        int i = blockEntity.getColor();
        float red = (float) (i >> 16 & 255) / 255.0F;
        float green = (float) (i >> 8 & 255) / 255.0F;
        float blue = (float) (i & 255) / 255.0F;

        this.model.renderToBuffer(poseStack, buffer.getBuffer(this.model.renderType(TENT_TEXTURE)), combinedLight, combinedOverlay, red, green, blue, 1.0F);

        poseStack.translate(0.0D, 0.3D, 0.0D);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(90));

        this.model.renderSticksToBuffer(poseStack, buffer.getBuffer(this.model.renderType(TENT_STICKS_TEXTURE)), combinedLight, combinedOverlay);

        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(@Nonnull ExplorersTentBlockEntity blockEntity) {
        return true;
    }
}
