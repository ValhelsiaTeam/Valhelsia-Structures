package com.stal111.valhelsia_structures.mixin;

import com.stal111.valhelsia_structures.init.ModStructures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Cat Entity Mixin
 * Valhelsia Structures - com.stal111.valhelsia_structures.mixin.CatEntityMixin
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-04-26
 */
@Mixin(CatEntity.class)
public abstract class CatEntityMixin extends TameableEntity {

    protected CatEntityMixin(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    @Inject(at = @At(value = "HEAD"), method = "onInitialSpawn", cancellable = true)
    private void valhelsia_avoidOverridingType(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, ILivingEntityData spawnData, CompoundNBT dataTag, CallbackInfoReturnable<ILivingEntityData> cir) {

        if (reason == SpawnReason.STRUCTURE && world.getWorld().func_241112_a_().getStructureStart(this.getPosition(), true, ModStructures.WITCH_HUT.get()).isValid()) {
            System.out.println("TEST");
            cir.setReturnValue(super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag));
        }
    }
}
