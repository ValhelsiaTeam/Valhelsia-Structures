package com.stal111.valhelsia_structures.entity.model;

import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.Mob;

/**
 * Mossy Skeleton Model
 * Valhelsia Structures - com.stal111.valhelsia_structures.entity.model.MossySkeletonModel
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-02-20
 */
public class MossySkeletonModel<T extends Mob & RangedAttackMob> extends SkeletonModel<T> {

    public MossySkeletonModel() {
        this(0.0F, false);
    }

    public MossySkeletonModel(float modelSize, boolean p_i46303_2_) {
        super(modelSize, p_i46303_2_);
        if (!p_i46303_2_) {
            this.bipedLeftArm = new ModelRenderer(this, 48, 16);
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 8.0F, 2.0F, modelSize);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
            this.bipedLeftLeg = new ModelRenderer(this, 8, 16);
            this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
            this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        }
    }
}
