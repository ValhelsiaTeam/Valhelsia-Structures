package com.stal111.valhelsia_structures.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.stal111.valhelsia_structures.client.model.ModModelLayers;
import com.stal111.valhelsia_structures.client.renderer.blockentity.state.ExplorersTentRenderState;
import com.stal111.valhelsia_structures.common.block.ExplorersTentBlock;
import com.stal111.valhelsia_structures.common.block.entity.ExplorersTentBlockEntity;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.Direction;
import net.minecraft.util.Unit;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

/**
 * Explorers Tent Renderer <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.renderer.entity.block.ExplorersTentRenderer
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.2.0
 * @since 2021-10-03
 */
public class ExplorersTentRenderer implements BlockEntityRenderer<ExplorersTentBlockEntity, ExplorersTentRenderState> {

    public static final SpriteId TENT_MATERIAL = Sheets.BLOCKS_MAPPER.apply(ValhelsiaStructures.identifier("explorers_tent/explorers_tent"));
    public static final SpriteId TENT_STICKS_MATERIAL = Sheets.BLOCKS_MAPPER.apply(ValhelsiaStructures.identifier("explorers_tent/explorers_tent_sticks"));

    private final SpriteGetter sprites;
    private final Model.Simple model;

    public ExplorersTentRenderer(BlockEntityRendererProvider.Context context) {
        this.sprites = context.sprites();
        this.model = new Model.Simple(context.bakeLayer(ModModelLayers.EXPLORERS_TENT), RenderTypes::entityCutout);
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();

        PartDefinition stick = partDefinition.addOrReplaceChild("stick", CubeListBuilder.create().texOffs(0, 122).addBox(-22.0F, -1.0F, -1.5F, 44.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
        stick.addOrReplaceChild("leftSlope", CubeListBuilder.create().texOffs(0, 46).addBox(-19.0F, 0.0F, 0.0F, 38.0F, 36.0F, 10.0F), PartPose.offsetAndRotation(0.0F, 0.25F, 0.0F, -0.6981F, 0.0F, 0.0F));
        stick.addOrReplaceChild("rightSlope", CubeListBuilder.create().texOffs(0, 0).addBox(-19.0F, 0.0F, 0.0F, 38.0F, 36.0F, 10.0F), PartPose.offsetAndRotation(0.0F, 0.25F, 0.0F, -0.6981F, -3.1416F, 0.0F));

        partDefinition.addOrReplaceChild("frontStick", CubeListBuilder.create().texOffs(94, 93).addBox(-0.5F, -32.0F, 0.0F, 1.0F, 32.0F, 3.0F), PartPose.offset(0.0F, 24.0F, -21.0F));
        partDefinition.addOrReplaceChild("backStick", CubeListBuilder.create().texOffs(104, 93).addBox(-0.5F, -32.0F, -3.0F, 1.0F, 32.0F, 3.0F), PartPose.offset(0.0F, 24.0F, 21.0F));

        CubeListBuilder rightHook = CubeListBuilder.create().texOffs(112, 118).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F);
        CubeListBuilder leftHook = CubeListBuilder.create().texOffs(120, 118).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F);

        partDefinition.addOrReplaceChild("rightBackHook", rightHook, PartPose.offset(-20.0F, 24.0F, 17.0F));
        partDefinition.addOrReplaceChild("leftBackHook", leftHook, PartPose.offset(20.0F, 24.0F, 17.0F));
        partDefinition.addOrReplaceChild("rightFrontHook", rightHook, PartPose.offset(-20.0F, 24.0F, -17.0F));
        partDefinition.addOrReplaceChild("leftFrontHook", leftHook, PartPose.offset(20.0F, 24.0F, -17.0F));

        return LayerDefinition.create(meshDefinition, 128, 128);
    }

    @Override
    public ExplorersTentRenderState createRenderState() {
        return new ExplorersTentRenderState();
    }

    @Override
    public void extractRenderState(ExplorersTentBlockEntity blockEntity, ExplorersTentRenderState renderState, float partialTick, Vec3 vec3, @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTick, vec3, crumblingOverlay);

        renderState.facing = blockEntity.getBlockState().getValue(ExplorersTentBlock.FACING);
        renderState.color = blockEntity.getColor();
    }

    @Override
    public void submit(ExplorersTentRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        Direction direction = renderState.facing.getClockWise();
        float rotation = -direction.toYRot();

        poseStack.pushPose();

        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation + 90));
        poseStack.mulPose(Axis.ZP.rotationDegrees(180));

        nodeCollector.submitModel(this.model, Unit.INSTANCE, poseStack, TENT_MATERIAL.renderType(this.model::renderType), renderState.lightCoords, OverlayTexture.NO_OVERLAY, renderState.color, this.sprites.get(TENT_MATERIAL), 0, renderState.breakProgress);
        nodeCollector.submitModel(this.model, Unit.INSTANCE, poseStack, TENT_STICKS_MATERIAL.renderType(this.model::renderType), renderState.lightCoords, OverlayTexture.NO_OVERLAY, -1, this.sprites.get(TENT_STICKS_MATERIAL), 0, renderState.breakProgress);

//        this.model.renderToBuffer(poseStack, bufferSource.getBuffer(this.model.renderType(TENT_TEXTURE)), packedLight, packedOverlay, blockEntity.getColor());
//        this.model.renderSticksToBuffer(poseStack, bufferSource.getBuffer(this.model.renderType(TENT_STICKS_TEXTURE)), packedLight, packedOverlay);

        poseStack.popPose();

        //TODO
//        if (!blockEntity.getSleepingBag().isEmpty()) {
//            poseStack.pushPose();
//
//            poseStack.translate(0.5D, 0.0D, 0.5D);
//            poseStack.mulPose(Axis.YP.rotationDegrees(rotation + 180));
//
//            poseStack.translate(-1.0D, 0.0D, -0.5D);
//
//            Direction bagDirection = switch (direction) {
//                case DOWN, UP, NORTH -> direction;
//                case SOUTH -> direction.getOpposite();
//                case WEST -> direction.getClockWise();
//                case EAST -> direction.getCounterClockWise();
//            };
//
//
//            BlockState block = Block.byItem(blockEntity.getSleepingBag().getItem()).defaultBlockState().setValue(SleepingBagBlock.FACING, bagDirection.getClockWise());
//
//            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(block, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
//
//            poseStack.translate(1.0D, 0.0D, 0.0D);
//            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(block.setValue(SleepingBagBlock.PART, BedPart.HEAD), poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
//
//            poseStack.popPose();
//        }
    }

    @Override
    public boolean shouldRenderOffScreen() {
        return true;
    }

    @Override
    public AABB getRenderBoundingBox(ExplorersTentBlockEntity blockEntity) {
        return new AABB(blockEntity.getBlockPos()).inflate(1.0D);
    }
}
