package com.stal111.valhelsia_structures.core;

import com.stal111.valhelsia_structures.common.event.CreativeModeTabEvents;
import net.minecraftforge.eventbus.api.IEventBus;
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

    }
}
