package com.stal111.valhelsia_structures.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

/**
 * Mossy Skeleton Entity
 * Valhelsia Structures - com.stal111.valhelsia_structures.entity.MossySkeletonEntity
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-02-20
 */
public class MossySkeletonEntity extends AbstractSkeletonEntity {

    public MossySkeletonEntity(EntityType<? extends AbstractSkeletonEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_SKELETON_STEP;
    }

    @Override
    protected AbstractArrowEntity fireArrow(ItemStack arrowStack, float distanceFactor) {
        AbstractArrowEntity arrowEntity = super.fireArrow(arrowStack, distanceFactor);

        if (arrowEntity instanceof ArrowEntity) {
            ((ArrowEntity) arrowEntity).addEffect(new EffectInstance(Effects.POISON, 100));
        }
        return arrowEntity;
    }
}
