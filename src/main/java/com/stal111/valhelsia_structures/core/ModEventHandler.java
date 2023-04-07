package com.stal111.valhelsia_structures.core;

import com.stal111.valhelsia_structures.client.event.ScreenEvents;
import com.stal111.valhelsia_structures.common.event.CreativeModeTabEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.valhelsia.valhelsia_core.core.ValhelsiaMod;

/**
 * @author Valhelsia Team
 * @since 2023-01-12
 */
public class ModEventHandler extends ValhelsiaMod.EventHandler {

    @Override
    public void registerModEvents(IEventBus eventBus) {
        eventBus.register(new CreativeModeTabEvents());
    }

    @Override
    public void registerForgeEvents(IEventBus eventBus) {
        if (FMLEnvironment.dist == Dist.CLIENT.CLIENT) {
            eventBus.register(new ScreenEvents());
        }
    }
}
