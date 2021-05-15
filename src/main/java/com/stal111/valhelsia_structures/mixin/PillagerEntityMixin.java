package com.stal111.valhelsia_structures.mixin;

import net.minecraft.entity.monster.PillagerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
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
@Mixin(PillagerEntity.class)
public class PillagerEntityMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/monster/PillagerEntity;setItemStackToSlot(Lnet/minecraft/inventory/EquipmentSlotType;Lnet/minecraft/item/ItemStack;)V"), method = "setEquipmentBasedOnDifficulty")
    private void valhelsia_avoidOverridingEquipment(PillagerEntity entity, EquipmentSlotType slot, ItemStack stack) {
        if (!entity.hasItemInSlot(slot)) {
            entity.setItemStackToSlot(slot, stack);
        }
    }
}