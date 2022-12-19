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

import javax.annotation.Nonnull;

/**
 * Dungeon Door Renderer <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.renderer.entity.block.DungeonDoorRenderer
 *
 * @author Valhelsia Team
 * @since 2021-10-03
 */
public class DungeonDoorRenderer implements BlockEntityRenderer<DungeonDoorBlockEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(ValhelsiaStructures.MOD_ID, "textures/block/dungeon_door.png");

    private final DungeonDoorModel model;

    public DungeonDoorRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new DungeonDoorModel(context.bakeLayer(DungeonDoorModel.DUNGEON_DOOR));
    }

    @Override
    public void render(@Nonnull DungeonDoorBlockEntity blockEntity, float partialTicks, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        BlockState state = blockEntity.getBlockState();
        poseStack.pushPose();

        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.mulPose(Axis.YP.rotationDegrees(-state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180));
        poseStack.mulPose(Axis.ZP.rotationDegrees(180));

        this.model.setupAnim(blockEntity, partialTicks);
        this.model.renderToBuffer(poseStack, buffer.getBuffer(this.model.renderType(TEXTURE)), combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(@Nonnull DungeonDoorBlockEntity blockEntity) {
        return true;
    }
}
