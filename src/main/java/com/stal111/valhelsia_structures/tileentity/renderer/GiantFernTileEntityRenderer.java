package com.stal111.valhelsia_structures.tileentity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.tileentity.GiantFernTileEntity;
import com.stal111.valhelsia_structures.tileentity.model.GiantFernModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

/**
 * Giant Fern Tile Entity Renderer
 * Valhelsia Structures - com.stal111.valhelsia_structures.tileentity.renderer.GiantFernTileEntityRenderer
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-12-22
 */
public class GiantFernTileEntityRenderer extends TileEntityRenderer<GiantFernTileEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(ValhelsiaStructures.MOD_ID, "textures/block/giant_fern.png");

    private final GiantFernModel model = new GiantFernModel();

    public GiantFernTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(GiantFernTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        matrixStack.push();

        matrixStack.translate(0.5, 1.5, 0.5);
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(180));

        if (tileEntity.getBlockState().get(ModBlockStateProperties.ROTATED)) {
            matrixStack.rotate(Vector3f.YP.rotationDegrees(45));
        }

        model.render(matrixStack, buffer.getBuffer(model.getRenderType(TEXTURE)), combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

        matrixStack.pop();
    }
}
