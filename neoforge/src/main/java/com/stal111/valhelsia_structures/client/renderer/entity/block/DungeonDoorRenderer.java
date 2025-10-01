package com.stal111.valhelsia_structures.client.renderer.entity.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.stal111.valhelsia_structures.client.model.block.DungeonDoorModel;
import com.stal111.valhelsia_structures.common.block.entity.DungeonDoorBlockEntity;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

/**
 * Dungeon Door Renderer <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.renderer.entity.block.DungeonDoorRenderer
 *
 * @author Valhelsia Team
 * @since 2021-10-03
 */
public class DungeonDoorRenderer implements BlockEntityRenderer<DungeonDoorBlockEntity> {

    private static final ResourceLocation TEXTURE = ValhelsiaStructures.location("textures/block/dungeon_door.png");

    private final DungeonDoorModel model;

    public DungeonDoorRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new DungeonDoorModel(context.bakeLayer(DungeonDoorModel.DUNGEON_DOOR));
    }

    @Override
    public void render(DungeonDoorBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, Vec3 cameraPos) {
        BlockState state = blockEntity.getBlockState();
        poseStack.pushPose();

        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.mulPose(Axis.YP.rotationDegrees(-state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180));
        poseStack.mulPose(Axis.ZP.rotationDegrees(180));

        this.model.setupAnim(blockEntity, partialTick);
        this.model.renderToBuffer(poseStack, bufferSource.getBuffer(this.model.renderType(TEXTURE)), packedLight, packedOverlay);

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
