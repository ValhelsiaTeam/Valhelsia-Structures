package com.stal111.valhelsia_structures.event;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.init.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.PillagerEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Entity Event Listener
 * Valhelsia Structures - com.stal111.valhelsia_structures.event.EntityEventListener
 * <p>
 * For all entity-related event handling.
 * </p>
 * @author Valhelsia Team
 * @version 1.0.2
 * @since 2021-05-11
 */
@Mod.EventBusSubscriber(modid = ValhelsiaStructures.MOD_ID)
public class EntityEventListener {

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof PillagerEntity && !event.getWorld().isRemote()) {
            PillagerEntity pillagerEntity = (PillagerEntity) entity;
            pillagerEntity.goalSelector.addGoal(5, new MeleeAttackGoal(pillagerEntity, 1.0D, true));
        }
    }

    @Mod.EventBusSubscriber(modid = ValhelsiaStructures.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents {

        @SubscribeEvent
        public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
            event.put(ModEntities.MOSSY_SKELETON.get(), AbstractSkeletonEntity.registerAttributes().create());
        }
    }
}
