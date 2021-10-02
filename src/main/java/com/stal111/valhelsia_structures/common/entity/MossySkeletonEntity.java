package com.stal111.valhelsia_structures.common.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

/**
 * Mossy Skeleton Entity <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.entity.MossySkeletonEntity
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-02-20
 */
public class MossySkeletonEntity extends AbstractSkeleton {

    public MossySkeletonEntity(EntityType<? extends AbstractSkeleton> type, Level level) {
        super(type, level);
    }

    @Nonnull
    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.SKELETON_STEP;
    }

    @Nonnull
    @Override
    protected AbstractArrow getArrow(@Nonnull ItemStack arrowStack, float distanceFactor) {
        AbstractArrow arrow = super.getArrow(arrowStack, distanceFactor);

        if (super.getArrow(arrowStack, distanceFactor) instanceof Arrow) {
            ((Arrow) arrow).addEffect(new MobEffectInstance(MobEffects.POISON, 100));
        }

        return arrow;
    }
}
