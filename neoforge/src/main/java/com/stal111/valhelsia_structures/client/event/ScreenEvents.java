package com.stal111.valhelsia_structures.client.event;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.ConfigurableValue;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;

/**
 * @author Valhelsia Team
 * @since 2023-02-18
 */
public class ScreenEvents {

    public static final ConfigurableValue<Boolean> FURNITURE_WARNING_ENABLED = ConfigurableValue.of(true);

    private static final Component FURNITURE_WARNING_1 = Component.translatable("gui.valhelsia_structures.furniture_warning_1").withStyle(ChatFormatting.RED);
    private static final Component FURNITURE_WARNING_2 = Component.translatable("gui.valhelsia_structures.furniture_warning_2").withStyle(ChatFormatting.RED);

    @SubscribeEvent
    public void onScreenRender(ScreenEvent.BackgroundRendered event) {
        if (event.getScreen() instanceof CreateWorldScreen screen && screen.height >= 260 && !ValhelsiaStructures.isFurnitureInstalled() && FURNITURE_WARNING_ENABLED.get()) {
            event.getGuiGraphics().drawCenteredString(screen.getMinecraft().font, FURNITURE_WARNING_1, screen.width / 2, 230, 16777215);
            event.getGuiGraphics().drawCenteredString(screen.getMinecraft().font, FURNITURE_WARNING_2, screen.width / 2, 242, 16777215);
        }
    }
}
