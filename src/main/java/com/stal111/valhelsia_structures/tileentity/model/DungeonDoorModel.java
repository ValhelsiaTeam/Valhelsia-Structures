package com.stal111.valhelsia_structures.tileentity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Dungeon Door Model
 * Valhelsia Structures - com.stal111.valhelsia_structures.tileentity.model.DungeonDoorModel
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-13
 */
@OnlyIn(Dist.CLIENT)
public class DungeonDoorModel extends Model {
    public ModelPart rightDoor;
    public ModelPart leftDoor;
    public ModelPart rightKnobFront;
    public ModelPart rightKnobBack;
    public ModelPart rightKnobFront_1;
    public ModelPart rightKnobBack_1;

    public DungeonDoorModel() {
        super(RenderType::entityCutout);
//        this.textureWidth = 128;
//        this.textureHeight = 96;
//        this.rightDoor = new ModelRenderer(this, 0, 0);
//        this.rightDoor.setRotationPoint(-24.0F, -8.0F, 0.0F);
//        this.rightDoor.addBox(0.0F, -32.0F, 0.0F, 24.0F, 64.0F, 4.0F, 0.0F, 0.0F, 0.0F);
//        this.rightDoor.setTextureOffset(0, 68).addBox(0.0F, -16.0F, -2.0F, 3.0F, 6.0F, 8.0F, 0.0F, 0.0F, 0.0F);
//        this.rightDoor.setTextureOffset(0, 82).addBox(0.0F, 10.0F, -2.0F, 3.0F, 6.0F, 8.0F, 0.0F, 0.0F, 0.0F);
//        this.rightKnobFront = new ModelRenderer(this, 0, 0);
//        this.rightKnobFront.setRotationPoint(18.0F, 6.0F, 0.1F);
//        this.rightKnobFront.setTextureOffset(112, 0).addBox(-4.0F, 0.0F, -1.0F, 7.0F, 8.0F, 1.0F, 0.0F, 0.0F, 0.0F);
//        this.setRotateAngle(rightKnobFront, -0.20943951023931953F, 0.0F, 0.0F);
//        this.rightKnobFront_1 = new ModelRenderer(this, 0, 0);
//        this.rightKnobFront_1.setRotationPoint(-18.0F, 6.0F, 0.1F);
//        this.rightKnobFront_1.setTextureOffset(112, 18).addBox(-3.0F, 0.0F, -1.0F, 7.0F, 8.0F, 1.0F, 0.0F, 0.0F, 0.0F);
//        this.setRotateAngle(rightKnobFront_1, -0.20943951023931953F, 0.0F, 0.0F);
//        this.rightKnobBack_1 = new ModelRenderer(this, 0, 0);
//        this.rightKnobBack_1.setRotationPoint(-18.0F, 6.0F, 3.9F);
//        this.rightKnobBack_1.setTextureOffset(112, 27).addBox(-3.0F, 0.0F, 0.0F, 7.0F, 8.0F, 1.0F, 0.0F, 0.0F, 0.0F);
//        this.setRotateAngle(rightKnobBack_1, 0.20943951023931953F, 0.0F, 0.0F);
//        this.leftDoor = new ModelRenderer(this, 0, 0);
//        this.leftDoor.setRotationPoint(24.0F, -8.0F, 0.0F);
//        this.leftDoor.setTextureOffset(56, 0).addBox(-24.0F, -32.0F, 0.0F, 24.0F, 64.0F, 4.0F, 0.0F, 0.0F, 0.0F);
//        this.leftDoor.setTextureOffset(22, 68).addBox(-3.0F, -16.0F, -2.0F, 3.0F, 6.0F, 8.0F, 0.0F, 0.0F, 0.0F);
//        this.leftDoor.setTextureOffset(22, 82).addBox(-3.0F, 10.0F, -2.0F, 3.0F, 6.0F, 8.0F, 0.0F, 0.0F, 0.0F);
//        this.rightKnobBack = new ModelRenderer(this, 0, 0);
//        this.rightKnobBack.setRotationPoint(18.0F, 6.0F, 3.9F);
//        this.rightKnobBack.setTextureOffset(112, 9).addBox(-4.0F, 0.0F, 0.0F, 7.0F, 8.0F, 1.0F, 0.0F, 0.0F, 0.0F);
//        this.setRotateAngle(rightKnobBack, 0.20943951023931953F, 0.0F, 0.0F);
//        this.rightDoor.addChild(this.rightKnobFront);
//        this.leftDoor.addChild(this.rightKnobFront_1);
//        this.leftDoor.addChild(this.rightKnobBack_1);
//        this.rightDoor.addChild(this.rightKnobBack);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {

    }

//    @Override
//    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
//        ImmutableList.of(this.rightDoor, this.leftDoor).forEach((modelRenderer) -> {
//            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        });
//    }
//
//    /**
//     * This is a helper function from Tabula to set the rotation of model parts
//     */
//    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
}
