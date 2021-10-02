package com.stal111.valhelsia_structures.client.renderer.entity;

import com.stal111.valhelsia_structures.client.model.MossySkeletonModel;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.common.entity.MossySkeletonEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * Mossy Skeleton Renderer <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.renderer.entity.MossySkeletonRenderer
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-02-20
 */
public class MossySkeletonRenderer extends HumanoidMobRenderer<MossySkeletonEntity, MossySkeletonModel<MossySkeletonEntity>> {

    private static final ResourceLocation MOSSY_SKELETON_TEXTURES = new ResourceLocation(ValhelsiaStructures.MOD_ID, "textures/entity/mossy_skeleton.png");

    public MossySkeletonRenderer(EntityRendererProvider.Context context) {
        this(context, ModelLayers.SKELETON, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);

    }

    public MossySkeletonRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer, ModelLayerLocation innerArmorLayer, ModelLayerLocation outerArmorLayer) {
        super(context, new MossySkeletonModel<>(context.bakeLayer(layer)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new MossySkeletonModel<>(context.bakeLayer(innerArmorLayer)), new MossySkeletonModel<>(context.bakeLayer(outerArmorLayer))));

//        this.addLayer(new MossySkeletonVinesLayer<>(this));
//        this.addLayer(new MossySkeletonArrowLayer<>(this));
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull MossySkeletonEntity entity) {
        return MOSSY_SKELETON_TEXTURES;
    }
}
