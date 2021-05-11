package com.stal111.valhelsia_structures.event;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.init.ModEntities;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Entity Event Listener
 * Valhelsia Structures - com.stal111.valhelsia_structures.event.EntityEventListener
 * <p>
 * For all entity-related event handling.
 * </p>
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-05-11
 */

@Mod.EventBusSubscriber(modid = ValhelsiaStructures.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityEventListener {
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MOSSY_SKELETON.get(), AbstractSkeletonEntity.registerAttributes().create());
    }
}
