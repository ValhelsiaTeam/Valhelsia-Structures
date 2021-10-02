package com.stal111.valhelsia_structures.tileentity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;

/**
 * Tent Model
 * Valhelsia Structures - com.stal111.valhelsia_structures.tileentity.model.TentModel
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-12-10
 */
public class ExplorersTentModel extends Model {
    public ModelPart stick;
    public ModelPart frontStick;
    public ModelPart backStick;
    public ModelPart rightBackHook;
    public ModelPart leftBackHook;
    public ModelPart rightFrontHook;
    public ModelPart leftFrontHook;
    public ModelPart leftSlope;
    public ModelPart rightSlope;

    public ExplorersTentModel() {
        super(RenderType::entityCutoutNoCull);
//        this.textureWidth = 128;
//        this.textureHeight = 128;
//        this.frontStick = new ModelRenderer(this, 0, 0);
//        this.frontStick.setRotationPoint(0.0F, 24.0F, -21.0F);
//        this.frontStick.setTextureOffset(94, 93).addBox(-0.5F, -32.0F, 0.0F, 1.0F, 32.0F, 3.0F, 0.0F, 0.0F, 0.0F);
//        this.leftFrontHook = new ModelRenderer(this, 0, 0);
//        this.leftFrontHook.setRotationPoint(20.0F, 24.0F, -17.0F);
//        this.leftFrontHook.setTextureOffset(120, 108).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, 0.0F, 0.0F);
//        this.rightBackHook = new ModelRenderer(this, 0, 0);
//        this.rightBackHook.setRotationPoint(-20.0F, 24.0F, 17.0F);
//        this.rightBackHook.setTextureOffset(112, 118).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, 0.0F, 0.0F);
//        this.rightFrontHook = new ModelRenderer(this, 0, 0);
//        this.rightFrontHook.setRotationPoint(-20.0F, 24.0F, -17.0F);
//        this.rightFrontHook.setTextureOffset(112, 108).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, 0.0F, 0.0F);
//        this.backStick = new ModelRenderer(this, 0, 0);
//        this.backStick.setRotationPoint(0.0F, 24.0F, 21.0F);
//        this.backStick.setTextureOffset(104, 93).addBox(-0.5F, -32.0F, -3.0F, 1.0F, 32.0F, 3.0F, 0.0F, 0.0F, 0.0F);
//        this.leftSlope = new ModelRenderer(this, 0, 0);
//        this.leftSlope.setRotationPoint(0.0F, 0.25F, 0.0F);
//        this.leftSlope.setTextureOffset(0, 46).addBox(-19.0F, 0.0F, 0.0F, 38.0F, 36.0F, 10.0F, 0.0F, 0.0F, 0.0F);
//        this.setRotateAngle(leftSlope, -0.6981317007977318F, 0.0F, 0.0F);
//        this.leftBackHook = new ModelRenderer(this, 0, 0);
//        this.leftBackHook.setRotationPoint(20.0F, 24.0F, 17.0F);
//        this.leftBackHook.setTextureOffset(120, 118).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, 0.0F, 0.0F);
//        this.rightSlope = new ModelRenderer(this, 0, 0);
//        this.rightSlope.setRotationPoint(0.0F, 0.25F, 0.0F);
//        this.rightSlope.addBox(-19.0F, 0.0F, 0.0F, 38.0F, 36.0F, 10.0F, 0.0F, 0.0F, 0.0F);
//        this.setRotateAngle(rightSlope, -0.6981317007977318F, -3.141592653589793F, 0.0F);
//        this.stick = new ModelRenderer(this, 0, 0);
//        this.stick.setRotationPoint(0.0F, -5.0F, 0.0F);
//        this.stick.setTextureOffset(0, 122).addBox(-22.0F, -1.0F, -1.5F, 44.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
//        this.setRotateAngle(stick, 0.0F, -1.5707963267948966F, 0.0F);
//        this.stick.addChild(this.leftSlope);
//        this.stick.addChild(this.rightSlope);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {

    }

//    @Override
//    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//        ImmutableList.of(this.leftSlope, this.rightSlope).forEach((modelRenderer) -> {
//            modelRenderer.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        });
//    }
//
//    public void renderSticks(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//        ImmutableList.of(this.frontStick, this.leftFrontHook, this.rightBackHook, this.rightFrontHook, this.backStick, this.leftBackHook, this.stick).forEach((modelRenderer) -> {
//            modelRenderer.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//        });
//    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
//     */
//    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
}
