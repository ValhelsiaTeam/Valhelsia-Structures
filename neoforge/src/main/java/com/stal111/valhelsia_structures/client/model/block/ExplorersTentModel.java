package com.stal111.valhelsia_structures.client.model.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

import javax.annotation.Nonnull;

/**
 * Explorers Tent Model <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.model.block.ExplorersTentModel
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-10-03
 */
public class ExplorersTentModel extends Model {

	public static final ModelLayerLocation EXPLORERS_TENT = new ModelLayerLocation(ValhelsiaStructures.location("explorers_tent"), "main");

	private final ModelPart stick;
	private final ModelPart frontStick;
	private final ModelPart backStick;
	private final ModelPart rightBackHook;
	private final ModelPart leftBackHook;
	private final ModelPart rightFrontHook;
	private final ModelPart leftFrontHook;

	public ExplorersTentModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.stick = root.getChild("stick");
		this.frontStick = root.getChild("frontStick");
		this.backStick = root.getChild("backStick");
		this.rightBackHook = root.getChild("rightBackHook");
		this.leftBackHook = root.getChild("leftBackHook");
		this.rightFrontHook = root.getChild("rightFrontHook");
		this.leftFrontHook = root.getChild("leftFrontHook");
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
	public void renderToBuffer(@Nonnull PoseStack poseStack, @Nonnull VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		stick.getChild("leftSlope").render(poseStack, buffer, packedLight, packedOverlay, color);
		stick.getChild("rightSlope").render(poseStack, buffer, packedLight, packedOverlay, color);
	}

	public void renderSticksToBuffer(@Nonnull PoseStack poseStack, @Nonnull VertexConsumer buffer, int packedLight, int packedOverlay) {
		stick.render(poseStack, buffer, packedLight, packedOverlay);
		frontStick.render(poseStack, buffer, packedLight, packedOverlay);
		backStick.render(poseStack, buffer, packedLight, packedOverlay);
		rightBackHook.render(poseStack, buffer, packedLight, packedOverlay);
		leftBackHook.render(poseStack, buffer, packedLight, packedOverlay);
		rightFrontHook.render(poseStack, buffer, packedLight, packedOverlay);
		leftFrontHook.render(poseStack, buffer, packedLight, packedOverlay);
	}
}