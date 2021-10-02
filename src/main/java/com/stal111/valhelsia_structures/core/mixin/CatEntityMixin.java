package com.stal111.valhelsia_structures.core.mixin;

import com.stal111.valhelsia_structures.core.init.ModStructures;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Cat Entity Mixin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.mixin.CatEntityMixin
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-04-26
 */
@Mixin(Cat.class)
public abstract class CatEntityMixin extends TamableAnimal {

    protected CatEntityMixin(EntityType<? extends TamableAnimal> type, Level world) {
        super(type, world);
    }

    @Inject(at = @At(value = "HEAD"), method = "finalizeSpawn", cancellable = true)
    private void valhelsia_avoidOverridingType(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, SpawnGroupData spawnData, CompoundTag dataTag, CallbackInfoReturnable<SpawnGroupData> cir) {
        if (reason == MobSpawnType.STRUCTURE && level.getLevel().structureFeatureManager().getStructureAt(this.blockPosition(), true, ModStructures.WITCH_HUT.get()).isValid()) {
            cir.setReturnValue(super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag));
        }
    }
}
