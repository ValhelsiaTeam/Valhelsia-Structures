package com.stal111.valhelsia_structures.tileentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.stal111.valhelsia_structures.tileentity.JarTileEntity;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;

/**
 * Jar Tile Entity Renderer
 * Valhelsia Structures - com.stal111.valhelsia_structures.tileentity.renderer.JarTileEntityRenderer
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-13
 */
public class JarTileEntityRenderer extends BlockEntityRenderer<JarTileEntity> {

    public JarTileEntityRenderer(BlockEntityRenderDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(JarTileEntity tileEntity, float partialTicks, PoseStack matrixStack, MultiBufferSource renderTypeBuffer, int combinedLight, int combinedOverlay) {
        if (tileEntity.hasPlant()) {
            matrixStack.push();
            matrixStack.translate(0.2, 0.45, 0.2);
            matrixStack.scale(0.6F, 0.6F, 0.6F);
            Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(Block.getBlockFromItem(tileEntity.getPlant().getItem()).getDefaultState(), matrixStack, renderTypeBuffer, combinedLight, OverlayTexture.NO_OVERLAY);
            matrixStack.pop();
        }
    }
}
