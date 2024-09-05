package com.stal111.valhelsia_structures.client;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.valhelsia.valhelsia_core.api.client.ClientSetupHelper;

/**
 * Client Setup <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.client.ClientSetup
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-02-20
 */
public class ClientSetup {

    public ClientSetup(ClientSetupHelper helper, IEventBus modEventBus) {
        modEventBus.addListener(this::onClientSetup);
    }

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
    }
}
