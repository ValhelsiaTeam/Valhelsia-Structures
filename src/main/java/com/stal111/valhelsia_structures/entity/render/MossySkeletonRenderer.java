package com.stal111.valhelsia_structures.entity.render;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.entity.MossySkeletonEntity;
import com.stal111.valhelsia_structures.entity.model.MossySkeletonModel;
import com.stal111.valhelsia_structures.entity.render.layer.MossySkeletonArrowLayer;
import com.stal111.valhelsia_structures.entity.render.layer.MossySkeletonVinesLayer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;

/**
 * Mossy Skeleton Renderer
 * Valhelsia Structures - com.stal111.valhelsia_structures.entity.render.MossySkeletonRenderer
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-02-20
 */
public class MossySkeletonRenderer extends HumanoidMobRenderer<MossySkeletonEntity, MossySkeletonModel<MossySkeletonEntity>> {

    private static final ResourceLocation MOSSY_SKELETON_TEXTURES = new ResourceLocation(ValhelsiaStructures.MOD_ID, "textures/entity/mossy_skeleton.png");

    public MossySkeletonRenderer(EntityRenderDispatcher renderManager) {
        super(renderManager, new MossySkeletonModel<>(), 0.5F);
        this.addLayer(new MossySkeletonVinesLayer<>(this));
        this.addLayer(new MossySkeletonArrowLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(MossySkeletonEntity entity) {
        return MOSSY_SKELETON_TEXTURES;
    }
}
