package com.stal111.valhelsia_structures.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.stal111.valhelsia_structures.client.model.block.DungeonDoorModel;
import com.stal111.valhelsia_structures.client.renderer.blockentity.state.DungeonDoorRenderState;
import com.stal111.valhelsia_structures.common.block.entity.DungeonDoorBlockEntity;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Dungeon Door Renderer <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.renderer.entity.block.DungeonDoorRenderer
 *
 * @author Valhelsia Team
 * @since 2021-10-03
 */
public class DungeonDoorRenderer implements BlockEntityRenderer<DungeonDoorBlockEntity, DungeonDoorRenderState> {

    public static final Material TEXTURE_MATERIAL = Sheets.BLOCKS_MAPPER.apply(ValhelsiaStructures.identifier("dungeon_door"));

    private final MaterialSet materials;
    private final DungeonDoorModel model;

    public DungeonDoorRenderer(BlockEntityRendererProvider.Context context) {
        this.materials = context.materials();
        this.model = new DungeonDoorModel(context.bakeLayer(DungeonDoorModel.DUNGEON_DOOR));
    }

    @Override
    public DungeonDoorRenderState createRenderState() {
        return new DungeonDoorRenderState();
    }

    @Override
    public void extractRenderState(DungeonDoorBlockEntity blockEntity, DungeonDoorRenderState renderState, float partialTick, Vec3 vec3, @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTick, vec3, crumblingOverlay);

        renderState.openness = blockEntity.getOpenNess(partialTick);
    }

    @Override
    public void submit(DungeonDoorRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        poseStack.pushPose();

        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.mulPose(Axis.YP.rotationDegrees(-renderState.blockState.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180));
        poseStack.mulPose(Axis.ZP.rotationDegrees(180));

        DungeonDoorModel.State state = new DungeonDoorModel.State(renderState.openness);

        this.model.setupAnim(state);
        nodeCollector.submitModel(this.model, state, poseStack, TEXTURE_MATERIAL.renderType(RenderTypes::entityCutout), renderState.lightCoords, OverlayTexture.NO_OVERLAY, -1, this.materials.get(TEXTURE_MATERIAL), 0, renderState.breakProgress);

        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen() {
        return true;
    }

    @Override
    public @NotNull AABB getRenderBoundingBox(DungeonDoorBlockEntity blockEntity) {
        return new AABB(blockEntity.getBlockPos().offset(-5, -5, -5).getCenter(), blockEntity.getBlockPos().offset(5, 5, 5).getCenter());
    }
}
