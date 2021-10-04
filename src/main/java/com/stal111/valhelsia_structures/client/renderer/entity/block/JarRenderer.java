package com.stal111.valhelsia_structures.client.renderer.entity.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.stal111.valhelsia_structures.common.block.entity.JarBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nonnull;

/**
 * Jar Renderer <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.renderer.entity.block.JarRenderer
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-10-03
 */
public class JarRenderer implements BlockEntityRenderer<JarBlockEntity> {

    public JarRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(@Nonnull JarBlockEntity blockEntity, float partialTicks, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        if (blockEntity.hasPlant()) {
            poseStack.pushPose();
            poseStack.translate(0.2, 0.45, 0.2);
            poseStack.scale(0.6F, 0.6F, 0.6F);
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(Block.byItem(blockEntity.getPlant().getItem()).defaultBlockState(), poseStack, buffer, combinedLight, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        }
    }
}
