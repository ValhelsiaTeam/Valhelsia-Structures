package com.stal111.valhelsia_structures.client.renderer.entity.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.stal111.valhelsia_structures.client.model.block.ExplorersTentModel;
import com.stal111.valhelsia_structures.common.block.ExplorersTentBlock;
import com.stal111.valhelsia_structures.common.block.SleepingBagBlock;
import com.stal111.valhelsia_structures.common.block.entity.ExplorersTentBlockEntity;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

/**
 * Explorers Tent Renderer <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.renderer.entity.block.ExplorersTentRenderer
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.2.0
 * @since 2021-10-03
 */
public class ExplorersTentRenderer implements BlockEntityRenderer<ExplorersTentBlockEntity> {

    private static final ResourceLocation TENT_TEXTURE = ValhelsiaStructures.location("textures/block/explorers_tent.png");
    private static final ResourceLocation TENT_STICKS_TEXTURE = ValhelsiaStructures.location("textures/block/explorers_tent_sticks.png");

    private final ExplorersTentModel model;

    public ExplorersTentRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new ExplorersTentModel(context.bakeLayer(ExplorersTentModel.EXPLORERS_TENT));
    }

    @Override
    public void render(@Nonnull ExplorersTentBlockEntity blockEntity, float partialTicks, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Direction direction = blockEntity.getBlockState().getValue(ExplorersTentBlock.FACING).getClockWise();
        float rotation = -direction.toYRot();

        poseStack.pushPose();

        poseStack.translate(0.5D, 1.8D, 0.5D);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        poseStack.mulPose(Axis.ZP.rotationDegrees(180));

        this.model.renderToBuffer(poseStack, buffer.getBuffer(this.model.renderType(TENT_TEXTURE)), combinedLight, combinedOverlay, blockEntity.getColor());

        poseStack.translate(0.0D, 0.3D, 0.0D);
        poseStack.mulPose(Axis.YP.rotationDegrees(90));

        this.model.renderSticksToBuffer(poseStack, buffer.getBuffer(this.model.renderType(TENT_STICKS_TEXTURE)), combinedLight, combinedOverlay);

        poseStack.popPose();

        if (!blockEntity.getSleepingBag().isEmpty()) {
            poseStack.pushPose();

            poseStack.translate(0.5D, 0.0D, 0.5D);
            poseStack.mulPose(Axis.YP.rotationDegrees(rotation + 180));

            poseStack.translate(-1.0D, 0.0D, -0.5D);

            Direction bagDirection = switch (direction) {
                case DOWN, UP, NORTH -> direction;
                case SOUTH -> direction.getOpposite();
                case WEST -> direction.getClockWise();
                case EAST -> direction.getCounterClockWise();
            };


            BlockState block = Block.byItem(blockEntity.getSleepingBag().getItem()).defaultBlockState().setValue(SleepingBagBlock.FACING, bagDirection.getClockWise());

            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(block, poseStack, buffer, combinedLight, OverlayTexture.NO_OVERLAY);

            poseStack.translate(1.0D, 0.0D, 0.0D);
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(block.setValue(SleepingBagBlock.PART, BedPart.HEAD), poseStack, buffer, combinedLight, OverlayTexture.NO_OVERLAY);

            poseStack.popPose();
        }
    }

    @Override
    public boolean shouldRenderOffScreen(@Nonnull ExplorersTentBlockEntity blockEntity) {
        return true;
    }

    @Override
    public @NotNull AABB getRenderBoundingBox(ExplorersTentBlockEntity blockEntity) {
        return new AABB(blockEntity.getBlockPos()).inflate(1.0D);
    }
}
