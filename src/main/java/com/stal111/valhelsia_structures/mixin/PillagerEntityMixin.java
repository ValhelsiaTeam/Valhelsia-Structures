package com.stal111.valhelsia_structures.mixin;

import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Pillager Entity Mixin
 * Valhelsia Structures - com.stal111.valhelsia_structures.mixin.PillagerEntityMixin
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-04-26
 */
@Mixin(Pillager.class)
public class PillagerEntityMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/monster/PillagerEntity;setItemStackToSlot(Lnet/minecraft/inventory/EquipmentSlotType;Lnet/minecraft/item/ItemStack;)V"), method = "setEquipmentBasedOnDifficulty")
    private void valhelsia_avoidOverridingEquipment(Pillager entity, EquipmentSlot slot, ItemStack stack) {
        if (!entity.hasItemInSlot(slot)) {
            entity.setItemStackToSlot(slot, stack);
        }
    }
}