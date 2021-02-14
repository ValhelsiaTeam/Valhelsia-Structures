package com.stal111.valhelsia_structures.event;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.config.Config;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Gui Listener
 * Valhelsia Structures - com.stal111.valhelsia_structures.event.GuiListener
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-02-09
 */
@Mod.EventBusSubscriber
public class GuiListener {

    @SubscribeEvent
    public static void onGuiOpen(GuiOpenEvent event) {
        if (event.getGui() instanceof MainMenuScreen && !ValhelsiaStructures.configValidated) {
            if (!Config.validateConfig()) {
                event.setCanceled(true);
            }
        }
    }
}
