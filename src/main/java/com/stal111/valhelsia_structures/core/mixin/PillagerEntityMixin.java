package com.stal111.valhelsia_structures.core.mixin;

import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Pillager Entity Mixin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.mixin.PillagerEntityMixin
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-04-26
 */
@Mixin(Pillager.class)
public class PillagerEntityMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Pillager;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"), method = "populateDefaultEquipmentSlots")
    private void valhelsia_avoidOverridingEquipment(Pillager entity, EquipmentSlot slot, ItemStack stack) {
        if (!entity.hasItemInSlot(slot)) {
            entity.setItemSlot(slot, stack);
        }
    }
}