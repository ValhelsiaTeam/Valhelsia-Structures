package com.stal111.valhelsia_structures.entity.render.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.stal111.valhelsia_structures.entity.MossySkeletonEntity;
import com.stal111.valhelsia_structures.entity.model.MossySkeletonModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.projectile.ArrowEntity;

import java.util.Random;

/**
 * Mossy Skeleton Arrow Layer
 * Valhelsia Structures - com.stal111.valhelsia_structures.entity.render.layer.MossySkeletonArrowLayer
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-02-21
 */
public class MossySkeletonArrowLayer<T extends MossySkeletonEntity> extends RenderLayer<T, MossySkeletonModel<T>> {

    private final EntityRenderDispatcher entityRendererManager;

    public MossySkeletonArrowLayer(LivingEntityRenderer<T, MossySkeletonModel<T>> entityRenderer) {
        super(entityRenderer);
        this.entityRendererManager = entityRenderer.getRenderManager();
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        Random random = new Random(entity.getEntityId());

        matrixStack.push();

        ModelRenderer modelRenderer = this.getEntityModel().getModelHead();
        modelRenderer.translateRotate(matrixStack);

        ArrowEntity arrowEntity = new ArrowEntity(entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ());
        arrowEntity.rotationYaw = -120.0F;
        arrowEntity.rotationPitch = 30.0F;
        matrixStack.translate(0.0, -0.25, 0.0);
        entityRendererManager.renderEntityStatic(arrowEntity, 0.0D, 0.0D, 0.0D, 0.0F, random.nextFloat(), matrixStack, buffer, packedLight);

        matrixStack.pop();
    }
}
