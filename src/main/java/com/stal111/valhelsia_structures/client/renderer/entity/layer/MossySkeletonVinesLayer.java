package com.stal111.valhelsia_structures.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.stal111.valhelsia_structures.client.model.MossySkeletonModel;
import com.stal111.valhelsia_structures.common.entity.MossySkeletonEntity;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * Mossy Skeleton Vines Layer <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.renderer.entity.layer.MossySkeletonVinesLayer
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-02-20
 */
public class MossySkeletonVinesLayer<T extends MossySkeletonEntity> extends RenderLayer<T, MossySkeletonModel<T>> {

    private static final ResourceLocation VINES_TEXTURE = new ResourceLocation(ValhelsiaStructures.MOD_ID, "textures/entity/mossy_skeleton_overlay.png");
    private static final ResourceLocation HANGING_VINES_TEXTURE = new ResourceLocation(ValhelsiaStructures.MOD_ID, "textures/entity/mossy_skeleton_hanging_vines.png");

  //  private final MossySkeletonModel<T> layerModel = new MossySkeletonModel<>(0.25F, true);

   // public ModelPart vines = new ModelPart(32, 32, 0, 0);

    public MossySkeletonVinesLayer(RenderLayerParent<T, MossySkeletonModel<T>> entityRenderer) {
        super(entityRenderer);
       // this.vines.addBox(-4.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
       // this.vines.setRotationPoint(0.0F, 0.0F, 0.0F);
    }

    @Override
    public void render(@Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int packedLight, @Nonnull T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
//        coloredCutoutModelCopyLayerRender(this.getParentModel(), this.layerModel, VINES_TEXTURE, poseStack, buffer, packedLight, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0F, 1.0F, 1.0F);
//
//        if (!entity.isInvisible()) {
//            VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCull(HANGING_VINES_TEXTURE));
//
//            this.vines.copyFrom(this.getParentModel().getHead());
//            vines.render(poseStack, vertexBuilder, packedLight, LivingEntityRenderer.getOverlayCoords(entity, 0.0F));
//        }
    }
}
