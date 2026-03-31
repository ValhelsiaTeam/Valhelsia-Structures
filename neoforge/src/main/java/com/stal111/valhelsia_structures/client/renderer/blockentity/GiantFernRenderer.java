package com.stal111.valhelsia_structures.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.stal111.valhelsia_structures.client.model.ModModelLayers;
import com.stal111.valhelsia_structures.client.renderer.blockentity.state.GiantFernRenderState;
import com.stal111.valhelsia_structures.common.block.entity.GiantFernBlockEntity;
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
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
import net.minecraft.util.Unit;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

/**
 * Giant Fern Renderer <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.renderer.entity.block.GiantFernRenderer
 *
 * @author Valhelsia Team
 * @since 2021-10-03
 */
public class GiantFernRenderer implements BlockEntityRenderer<GiantFernBlockEntity, GiantFernRenderState> {

    public static final SpriteId TEXTURE_MATERIAL = Sheets.BLOCKS_MAPPER.apply(ValhelsiaStructures.identifier("giant_fern"));

    private final SpriteGetter sprites;
    private final Model.Simple model;

    public GiantFernRenderer(BlockEntityRendererProvider.Context context) {
        this.sprites = context.sprites();
        this.model = new Model.Simple(context.bakeLayer(ModModelLayers.GIANT_FERN), RenderTypes::entityCutout);
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();

        CubeListBuilder fernBottom = CubeListBuilder.create().texOffs(24, 0).addBox(-6.0F, -10.0F, 0.0F, 12.0F, 10.0F, 0.0F);
        PartDefinition fernBottom1 = partDefinition.addOrReplaceChild("fernBottom1", fernBottom, PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, -1.1F, 0.7854F, 0.0F));
        PartDefinition fernBottom2 = partDefinition.addOrReplaceChild("fernBottom2", fernBottom, PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, -1.1F, 2.3562F, 0.0F));
        PartDefinition fernBottom3 = partDefinition.addOrReplaceChild("fernBottom3", fernBottom, PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, -1.1F, -2.3562F, 0.0F));
        PartDefinition fernBottom4 = partDefinition.addOrReplaceChild("fernBottom4", fernBottom, PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, -1.1F, -0.7854F, 0.0F));

        CubeListBuilder fernTop = CubeListBuilder.create().texOffs(24, 12).addBox(-6.0F, -10.0F, 0.0F, 12.0F, 10.0F, 0.0F);
        fernBottom1.addOrReplaceChild("fernTop1", fernTop, PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 2.530727415391778F, 3.141592653589793F, 3.141592653589793F));
        fernBottom2.addOrReplaceChild("fernTop2", fernTop, PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 2.530727415391778F, 3.141592653589793F, 3.141592653589793F));
        fernBottom3.addOrReplaceChild("fernTop3", fernTop, PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 2.530727415391778F, 3.141592653589793F, 3.141592653589793F));
        fernBottom4.addOrReplaceChild("fernTop4", fernTop, PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 2.530727415391778F, -3.141592653589793F, 3.141592653589793F));

        CubeListBuilder fern = CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, 0.0F, 0.0F, 12.0F, 16.0F, 0.0F);
        partDefinition.addOrReplaceChild("fern1", fern, PartPose.offsetAndRotation(0.0F, -0.5F, -1.0F, -1.7453F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("fern2", fern, PartPose.offsetAndRotation(-1.0F, -0.5F, 0.0F, -1.7453F, 1.5708F, 0.0F));
        partDefinition.addOrReplaceChild("fern3", fern, PartPose.offsetAndRotation(0.0F, -0.5F, 1.0F, -1.7453F, 3.1416F, 0.0F));
        partDefinition.addOrReplaceChild("fern4", fern, PartPose.offsetAndRotation(1.0F, -0.5F, 0.0F, -1.7453F, -1.5708F, 0.0F));

        CubeListBuilder stem = CubeListBuilder.create().texOffs(0, 16).addBox(-6.0F, -16.0F, 0.0F, 12.0F, 16.0F, 0.0F);
        partDefinition.addOrReplaceChild("stem1", stem, PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
        partDefinition.addOrReplaceChild("stem2", stem, PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        return LayerDefinition.create(meshDefinition, 48, 32);
    }

    @Override
    public GiantFernRenderState createRenderState() {
        return new GiantFernRenderState();
    }

    @Override
    public void extractRenderState(GiantFernBlockEntity blockEntity, GiantFernRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        state.rotated = blockEntity.getBlockState().getValue(ModBlockStateProperties.ROTATED);
    }

    @Override
    public void submit(GiantFernRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        poseStack.pushPose();

        poseStack.translate(0.5D, 0.0D, 0.5D);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180));

        if (renderState.rotated) {
            poseStack.mulPose(Axis.YP.rotationDegrees(45));
        }

        nodeCollector.submitModel(this.model, Unit.INSTANCE, poseStack, TEXTURE_MATERIAL.renderType(RenderTypes::entityCutout), renderState.lightCoords, OverlayTexture.NO_OVERLAY, -1, this.sprites.get(TEXTURE_MATERIAL), 0, renderState.breakProgress);

        poseStack.popPose();
    }
}
