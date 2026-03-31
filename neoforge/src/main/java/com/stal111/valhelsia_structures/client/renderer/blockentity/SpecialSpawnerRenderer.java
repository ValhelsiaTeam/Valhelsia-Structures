package com.stal111.valhelsia_structures.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.stal111.valhelsia_structures.common.block.SpecialBaseSpawner;
import com.stal111.valhelsia_structures.common.block.entity.SpecialSpawnerBlockEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SpawnerRenderer;
import net.minecraft.client.renderer.blockentity.TrialSpawnerRenderer;
import net.minecraft.client.renderer.blockentity.state.SpawnerRenderState;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

/**
 * Special Spawner Renderer <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.renderer.entity.block.SpecialSpawnerRenderer
 *
 * @author Valhelsia Team
 * @since 2021-10-03
 */
public class SpecialSpawnerRenderer implements BlockEntityRenderer<SpecialSpawnerBlockEntity, SpawnerRenderState> {

    private final EntityRenderDispatcher entityRenderer;

    public SpecialSpawnerRenderer(BlockEntityRendererProvider.Context context) {
        this.entityRenderer = context.entityRenderer();
    }

    @Override
    public SpawnerRenderState createRenderState() {
        return new SpawnerRenderState();
    }

    @Override
    public void extractRenderState(SpecialSpawnerBlockEntity blockEntity, SpawnerRenderState renderState, float partialTick, Vec3 vec3, @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTick, vec3, crumblingOverlay);

        if (blockEntity.getLevel() != null) {
            SpecialBaseSpawner spawner = blockEntity.getSpawner();
            Entity entity = spawner.getOrCreateDisplayEntity(blockEntity.getLevel(), blockEntity.getBlockPos());
            TrialSpawnerRenderer.extractSpawnerData(renderState, partialTick, entity, this.entityRenderer, spawner.getOSpin(), spawner.getSpin());
        }
    }

    @Override
    public void submit(SpawnerRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        if (renderState.displayEntity != null) {
            SpawnerRenderer.submitEntityInSpawner(poseStack, nodeCollector, renderState.displayEntity, this.entityRenderer, renderState.spin, renderState.scale, cameraRenderState);
        }
    }
}
