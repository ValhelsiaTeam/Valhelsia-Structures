package com.stal111.valhelsia_structures.core;

import com.stal111.valhelsia_structures.client.event.ScreenEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.valhelsia.valhelsia_core.core.ValhelsiaForgeEventHandler;

/**
 * @author Valhelsia Team
 * @since 2023-01-12
 */
public class ModEventHandler extends ValhelsiaForgeEventHandler {

    public ModEventHandler(IEventBus modEventBus) {
        super(modEventBus);
    }

    @Override
    public void registerModEvents(IEventBus eventBus) {
    }

    @Override
    public void registerForgeEvents(IEventBus eventBus) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            eventBus.register(new ScreenEvents());
        }
    }
}
