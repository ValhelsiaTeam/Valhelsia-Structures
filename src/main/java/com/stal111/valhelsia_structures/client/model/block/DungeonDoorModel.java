package com.stal111.valhelsia_structures.client.model.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.stal111.valhelsia_structures.common.block.entity.DungeonDoorBlockEntity;
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
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * Dungeon Door Model <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.model.block.DungeonDoorModel
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-10-03
 */
public class DungeonDoorModel extends Model {

	public static final ModelLayerLocation DUNGEON_DOOR = new ModelLayerLocation(new ResourceLocation(ValhelsiaStructures.MOD_ID, "dungeon_door"), "main");

	private final ModelPart rightDoor;
	private final ModelPart leftDoor;

	public DungeonDoorModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.rightDoor = root.getChild("rightDoor");
		this.leftDoor = root.getChild("leftDoor");
	}

	public static LayerDefinition createLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition rightDoor = partDefinition.addOrReplaceChild("rightDoor", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -32.0F, 0.0F, 24.0F, 64.0F, 4.0F).addBox("", 0.0F, -16.0F, -2.0F, 3, 6, 8, 0, 68).addBox("", 0.0F, 10.0F, -2.0F, 3, 6, 8, 0, 82), PartPose.offset(-24.0F, -8.0F, 0.0F));
		rightDoor.addOrReplaceChild("rightKnobFront", CubeListBuilder.create().texOffs(112, 0).addBox(-4.0F, 0.0F, -1.0F, 7.0F, 8.0F, 1.0F), PartPose.offsetAndRotation(18.0F, 6.0F, 0.1F, -0.2094F, 0.0F, 0.0F));
		rightDoor.addOrReplaceChild("rightKnobBack", CubeListBuilder.create().texOffs(112, 9).addBox(-4.0F, 0.0F, 0.0F, 7.0F, 8.0F, 1.0F), PartPose.offsetAndRotation(18.0F, 6.0F, 3.9F, 0.2094F, 0.0F, 0.0F));

		PartDefinition leftDoor = partDefinition.addOrReplaceChild("leftDoor", CubeListBuilder.create().texOffs(56, 0).addBox(-24.0F, -32.0F, 0.0F, 24.0F, 64.0F, 4.0F).addBox("", -3.0F, -16.0F, -2.0F, 3, 6, 8, 22, 68).addBox("", -3.0F, 10.0F, -2.0F, 3, 6, 8, 22, 82), PartPose.offset(24.0F, -8.0F, 0.0F));
		leftDoor.addOrReplaceChild("rightKnobFront_1", CubeListBuilder.create().texOffs(112, 18).addBox(-3.0F, 0.0F, -1.0F, 7.0F, 8.0F, 1.0F), PartPose.offsetAndRotation(-18.0F, 6.0F, 0.1F, -0.2094F, 0.0F, 0.0F));
		leftDoor.addOrReplaceChild("rightKnobBack_1", CubeListBuilder.create().texOffs(112, 27).addBox(-3.0F, 0.0F, 0.0F, 7.0F, 8.0F, 1.0F), PartPose.offsetAndRotation(-18.0F, 6.0F, 3.9F, 0.2094F, 0.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 128, 96);
	}

	public void setupAnim(DungeonDoorBlockEntity blockEntity, float partialTicks) {
		float leafAngle = blockEntity.getOpenNess(partialTicks);

		this.leftDoor.yRot= -(leafAngle * ((float) Math.PI / 2F));
		this.rightDoor.yRot = (leafAngle * ((float) Math.PI / 2F));
	}

	@Override
	public void renderToBuffer(@Nonnull PoseStack poseStack, @Nonnull VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rightDoor.render(poseStack, buffer, packedLight, packedOverlay);
		leftDoor.render(poseStack, buffer, packedLight, packedOverlay);
	}
}