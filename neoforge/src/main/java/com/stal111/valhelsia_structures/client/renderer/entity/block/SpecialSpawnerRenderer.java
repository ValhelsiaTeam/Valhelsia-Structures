package com.stal111.valhelsia_structures.client.renderer.entity.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.stal111.valhelsia_structures.common.block.SpecialBaseSpawner;
import com.stal111.valhelsia_structures.common.block.entity.SpecialSpawnerBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

/**
 * Special Spawner Renderer <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.renderer.entity.block.SpecialSpawnerRenderer
 *
 * @author Valhelsia Team
 * @since 2021-10-03
 */
public class SpecialSpawnerRenderer implements BlockEntityRenderer<SpecialSpawnerBlockEntity> {

    private final EntityRenderDispatcher entityRenderer;

    public SpecialSpawnerRenderer(BlockEntityRendererProvider.Context context) {
        this.entityRenderer = context.getEntityRenderer();
    }

    @Override
    public void render(SpecialSpawnerBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, Vec3 cameraPos) {
        SpecialBaseSpawner spawner = blockEntity.getSpawner();
        Entity entity = spawner.getOrCreateDisplayEntity(Objects.requireNonNull(blockEntity.getLevel()), blockEntity.getBlockPos());

        if (entity != null) {
            poseStack.pushPose();
            poseStack.translate(0.5D, 0.0D, 0.5D);

            float f = 0.53125F;
            float f1 = Math.max(entity.getBbWidth(), entity.getBbHeight());

            if (f1 > 1.0D) {
                f /= f1;
            }

            poseStack.translate(0.0D, 0.4F, 0.0D);
            poseStack.mulPose(Axis.YP.rotationDegrees((float) Mth.lerp(partialTick, spawner.getOSpin(), spawner.getSpin()) * 10.0F));
            poseStack.translate(0.0D, -0.2F, 0.0D);
            poseStack.mulPose(Axis.XP.rotationDegrees(-30.0F));
            poseStack.scale(f, f, f);
            this.entityRenderer.render(entity, 0.0D, 0.0D, 0.0D, partialTick, poseStack, bufferSource, packedLight);

            poseStack.popPose();
        }
    }
}
