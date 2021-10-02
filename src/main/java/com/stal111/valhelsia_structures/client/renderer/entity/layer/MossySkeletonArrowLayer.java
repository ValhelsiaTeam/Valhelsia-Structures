package com.stal111.valhelsia_structures.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.stal111.valhelsia_structures.client.model.MossySkeletonModel;
import com.stal111.valhelsia_structures.common.entity.MossySkeletonEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.projectile.Arrow;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * Mossy Skeleton Arrow Layer <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.renderer.entity.layer.MossySkeletonArrowLayer
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-02-21
 */
public class MossySkeletonArrowLayer<T extends MossySkeletonEntity> extends RenderLayer<T, MossySkeletonModel<T>> {

    private final EntityRenderDispatcher dispatcher;

    public MossySkeletonArrowLayer(EntityRendererProvider.Context context, LivingEntityRenderer<T, MossySkeletonModel<T>> entityRenderer) {
        super(entityRenderer);
        this.dispatcher = context.getEntityRenderDispatcher();
    }

    @Override
    public void render(PoseStack poseStack, @Nonnull MultiBufferSource buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        Random random = new Random(entity.getId());

        poseStack.pushPose();

        ModelPart modelRenderer = this.getParentModel().getHead();
        modelRenderer.translateAndRotate(poseStack);

        Arrow arrowEntity = new Arrow(entity.level, entity.getX(), entity.getY(), entity.getZ());
        arrowEntity.setYRot(-120.0F);
        arrowEntity.setXRot(30.0F);
        poseStack.translate(0.0, -0.25, 0.0);
        dispatcher.render(arrowEntity, 0.0D, 0.0D, 0.0D, 0.0F, random.nextFloat(), poseStack, buffer, packedLight);

        poseStack.popPose();
    }
}
