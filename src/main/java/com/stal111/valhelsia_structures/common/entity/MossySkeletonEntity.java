package com.stal111.valhelsia_structures.common.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.AbstractSkeleton;
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
}
