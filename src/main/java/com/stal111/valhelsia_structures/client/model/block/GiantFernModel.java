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
 * Giant Fern Model <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.model.block.GiantFernModel
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-10-03
 */
public class GiantFernModel extends Model {

    public static final ModelLayerLocation GIANT_FERN = new ModelLayerLocation(ValhelsiaStructures.location("giant_fern"), "main");

    private final ModelPart fernBottom1;
    private final ModelPart fernBottom2;
    private final ModelPart fernBottom3;
    private final ModelPart fernBottom4;
    private final ModelPart fern1;
    private final ModelPart fern2;
    private final ModelPart fern3;
    private final ModelPart fern4;
    private final ModelPart stem1;
    private final ModelPart stem2;

    public GiantFernModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.fernBottom1 = root.getChild("fernBottom1");
        this.fernBottom2 = root.getChild("fernBottom2");
        this.fernBottom3 = root.getChild("fernBottom3");
        this.fernBottom4 = root.getChild("fernBottom4");
        this.fern1 = root.getChild("fern1");
        this.fern2 = root.getChild("fern2");
        this.fern3 = root.getChild("fern3");
        this.fern4 = root.getChild("fern4");
        this.stem1 = root.getChild("stem1");
        this.stem2 = root.getChild("stem2");
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
    public void renderToBuffer(@Nonnull PoseStack poseStack, @Nonnull VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        fernBottom1.render(poseStack, buffer, packedLight, packedOverlay);
        fernBottom2.render(poseStack, buffer, packedLight, packedOverlay);
        fernBottom3.render(poseStack, buffer, packedLight, packedOverlay);
        fernBottom4.render(poseStack, buffer, packedLight, packedOverlay);
        fern1.render(poseStack, buffer, packedLight, packedOverlay);
        fern2.render(poseStack, buffer, packedLight, packedOverlay);
        fern3.render(poseStack, buffer, packedLight, packedOverlay);
        fern4.render(poseStack, buffer, packedLight, packedOverlay);
        stem1.render(poseStack, buffer, packedLight, packedOverlay);
        stem2.render(poseStack, buffer, packedLight, packedOverlay);
    }
}
