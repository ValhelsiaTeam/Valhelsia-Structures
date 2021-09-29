package com.stal111.valhelsia_structures.tileentity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;

/**
 * Giant Fern Model
 * Valhelsia Structures - com.stal111.valhelsia_structures.tileentity.model.GiantFernModel
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-12-22
 */
public class GiantFernModel extends Model {
    public ModelPart fernBottom1;
    public ModelPart fernBottom2;
    public ModelPart fernBottom3;
    public ModelPart fernBottom4;
    public ModelPart fern1;
    public ModelPart fern2;
    public ModelPart fern3;
    public ModelPart fern4;
    public ModelPart stem1;
    public ModelPart stem2;
    public ModelPart fernTop1;
    public ModelPart fernTop2;
    public ModelPart fernTop3;
    public ModelPart fernTop4;

    public GiantFernModel() {
        super(RenderType::getEntityCutoutNoCull);
        this.textureWidth = 48;
        this.textureHeight = 32;
        this.fernTop4 = new ModelRenderer(this, 0, 0);
        this.fernTop4.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.fernTop4.setTextureOffset(24, 12).addBox(-6.0F, -10.0F, 0.0F, 12.0F, 10.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(fernTop4, 2.530727415391778F, -3.141592653589793F, 3.141592653589793F);
        this.fern4 = new ModelRenderer(this, 0, 0);
        this.fern4.setRotationPoint(1.0F, 20.0F, 0.0F);
        this.fern4.addBox(-6.0F, 0.0F, 0.0F, 12.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(fern4, -1.7453292519943295F, -1.5707963267948966F, 0.0F);
        this.fernBottom1 = new ModelRenderer(this, 0, 0);
        this.fernBottom1.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.fernBottom1.setTextureOffset(24, 0).addBox(-6.0F, -10.0F, 0.0F, 12.0F, 10.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(fernBottom1, -1.1780972450961724F, -0.7853981633974483F, 0.0F);
        this.fernBottom3 = new ModelRenderer(this, 0, 0);
        this.fernBottom3.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.fernBottom3.setTextureOffset(24, 0).addBox(-6.0F, -10.0F, 0.0F, 12.0F, 10.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(fernBottom3, -1.1780972450961724F, 2.356194490192345F, 0.0F);
        this.stem2 = new ModelRenderer(this, 0, 0);
        this.stem2.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.stem2.setTextureOffset(0, 16).addBox(-6.0F, -16.0F, 0.0F, 12.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(stem2, 0.0F, -0.7853981633974483F, 0.0F);
        this.fern2 = new ModelRenderer(this, 0, 0);
        this.fern2.setRotationPoint(-1.0F, 20.0F, 0.0F);
        this.fern2.addBox(-6.0F, 0.0F, 0.0F, 12.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(fern2, -1.7453292519943295F, 1.5707963267948966F, 0.0F);
        this.fern3 = new ModelRenderer(this, 0, 0);
        this.fern3.setRotationPoint(0.0F, 20.0F, 1.0F);
        this.fern3.addBox(-6.0F, 0.0F, 0.0F, 12.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(fern3, -1.7453292519943295F, 3.141592653589793F, 0.0F);
        this.fern1 = new ModelRenderer(this, 0, 0);
        this.fern1.setRotationPoint(0.0F, 20.0F, -1.0F);
        this.fern1.addBox(-6.0F, 0.0F, 0.0F, 12.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(fern1, -1.7453292519943295F, 0.0F, 0.0F);
        this.fernBottom4 = new ModelRenderer(this, 0, 0);
        this.fernBottom4.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.fernBottom4.setTextureOffset(24, 0).addBox(-6.0F, -10.0F, 0.0F, 12.0F, 10.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(fernBottom4, -1.1780972450961724F, 0.7853981633974483F, 0.0F);
        this.fernBottom2 = new ModelRenderer(this, 0, 0);
        this.fernBottom2.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.fernBottom2.setTextureOffset(24, 0).addBox(-6.0F, -10.0F, 0.0F, 12.0F, 10.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(fernBottom2, -1.1780972450961724F, -2.356194490192345F, 0.0F);
        this.fernTop1 = new ModelRenderer(this, 0, 0);
        this.fernTop1.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.fernTop1.setTextureOffset(24, 12).addBox(-6.0F, -10.0F, 0.0F, 12.0F, 10.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(fernTop1, 2.530727415391778F, 3.141592653589793F, 3.141592653589793F);
        this.fernTop3 = new ModelRenderer(this, 0, 0);
        this.fernTop3.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.fernTop3.setTextureOffset(24, 12).addBox(-6.0F, -10.0F, 0.0F, 12.0F, 10.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(fernTop3, 2.530727415391778F, 3.141592653589793F, 3.141592653589793F);
        this.stem1 = new ModelRenderer(this, 0, 0);
        this.stem1.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.stem1.setTextureOffset(0, 16).addBox(-6.0F, -16.0F, 0.0F, 12.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(stem1, 0.0F, 0.7853981633974483F, 0.0F);
        this.fernTop2 = new ModelRenderer(this, 0, 0);
        this.fernTop2.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.fernTop2.setTextureOffset(24, 12).addBox(-6.0F, -10.0F, 0.0F, 12.0F, 10.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(fernTop2, 2.530727415391778F, 3.141592653589793F, 3.141592653589793F);
        this.fernBottom4.addChild(this.fernTop4);
        this.fernBottom1.addChild(this.fernTop1);
        this.fernBottom3.addChild(this.fernTop3);
        this.fernBottom2.addChild(this.fernTop2);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.fern4, this.fernBottom1, this.fernBottom3, this.stem2, this.fern2, this.fern3, this.fern1, this.fernBottom4, this.fernBottom2, this.stem1).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
