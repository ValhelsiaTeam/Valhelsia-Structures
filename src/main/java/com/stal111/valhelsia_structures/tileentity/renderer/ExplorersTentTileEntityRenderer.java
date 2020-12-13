package com.stal111.valhelsia_structures.tileentity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.block.ExplorersTentBlock;
import com.stal111.valhelsia_structures.tileentity.ExplorersTentTileEntity;
import com.stal111.valhelsia_structures.tileentity.model.ExplorersTentModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

/**
 * Explorers Tent Tile Entity Renderer
 * Valhelsia Structures - com.stal111.valhelsia_structures.tileentity.renderer.ExplorersTentTileEntityRenderer
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-12-10
 */
public class ExplorersTentTileEntityRenderer extends TileEntityRenderer<ExplorersTentTileEntity> {

    private static final ResourceLocation TENT_TEXTURE = new ResourceLocation(ValhelsiaStructures.MOD_ID, "textures/block/explorers_tent.png");
    private static final ResourceLocation TENT_STICKS_TEXTURE = new ResourceLocation(ValhelsiaStructures.MOD_ID, "textures/block/explorers_tent_sticks.png");

    private final ExplorersTentModel tentModel = new ExplorersTentModel();

    public ExplorersTentTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(ExplorersTentTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        matrixStack.push();

        matrixStack.translate(0.5, 1.85, 0.5);
        matrixStack.rotate(Vector3f.YP.rotationDegrees(tileEntity.getBlockState().get(ExplorersTentBlock.FACING).getHorizontalAngle() + 90));
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(180));

        int i = tileEntity.getColor();
        float red = (float) (i >> 16 & 255) / 255.0F;
        float green = (float) (i >> 8 & 255) / 255.0F;
        float blue = (float) (i & 255) / 255.0F;

        tentModel.render(matrixStack, buffer.getBuffer(tentModel.getRenderType(TENT_TEXTURE)), combinedLight, combinedOverlay, red, green, blue, 1.0F);
        matrixStack.translate(0, 0.35, 0);
        matrixStack.rotate(Vector3f.YP.rotationDegrees(90));
        tentModel.renderSticks(matrixStack, buffer.getBuffer(tentModel.getRenderType(TENT_STICKS_TEXTURE)), combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
    }

    @Override
    public boolean isGlobalRenderer(ExplorersTentTileEntity tileEntity) {
        return true;
    }
}
