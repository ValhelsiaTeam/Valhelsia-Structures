package com.stal111.valhelsia_structures.entity.render.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.entity.MossySkeletonEntity;
import com.stal111.valhelsia_structures.entity.model.MossySkeletonModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;

/**
 * Mossy Skeleton Vines Layer
 * Valhelsia Structures - com.stal111.valhelsia_structures.entity.render.layer.MossySkeletonVinesLayer
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-02-20
 */
public class MossySkeletonVinesLayer<T extends MossySkeletonEntity> extends RenderLayer<T, MossySkeletonModel<T>> {

    private static final ResourceLocation VINES_TEXTURE = new ResourceLocation(ValhelsiaStructures.MOD_ID, "textures/entity/mossy_skeleton_overlay.png");
    private static final ResourceLocation HANGING_VINES_TEXTURE = new ResourceLocation(ValhelsiaStructures.MOD_ID, "textures/entity/mossy_skeleton_hanging_vines.png");

    private final MossySkeletonModel<T> layerModel = new MossySkeletonModel<>(0.25F, true);

    public ModelPart vines = new ModelPart(32, 32, 0, 0);

    public MossySkeletonVinesLayer(RenderLayerParent<T, MossySkeletonModel<T>> entityRenderer) {
        super(entityRenderer);
        this.vines.addBox(-4.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        this.vines.setRotationPoint(0.0F, 0.0F, 0.0F);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        renderCopyCutoutModel(this.getEntityModel(), this.layerModel, VINES_TEXTURE, matrixStack, buffer, packedLight, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0F, 1.0F, 1.0F);

        if (!entity.isInvisible()) {
            IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.getEntityCutoutNoCull(HANGING_VINES_TEXTURE));

            this.vines.copyModelAngles(this.getEntityModel().getModelHead());
            vines.render(matrixStack, vertexBuilder, packedLight, LivingRenderer.getPackedOverlay(entity, 0.0F));
        }
    }
}
