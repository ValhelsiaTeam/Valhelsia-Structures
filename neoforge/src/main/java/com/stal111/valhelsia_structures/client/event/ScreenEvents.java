package com.stal111.valhelsia_structures.client.event;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.ConfigurableValue;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.FocusableTextWidget;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.MutableComponent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;

import java.util.List;

/**
 * @author Valhelsia Team
 * @since 2023-02-18
 */
public class ScreenEvents {

    public static final ConfigurableValue<Boolean> FURNITURE_WARNING_ENABLED = ConfigurableValue.of(true);

    private static final Component FURNITURE_WARNING_1 = Component.translatable("gui.valhelsia_structures.furniture_warning_1").withStyle(ChatFormatting.RED);
    private static final Component FURNITURE_WARNING_2 = Component.translatable("gui.valhelsia_structures.furniture_warning_2").withStyle(ChatFormatting.RED);
    private static final Component FURNITURE_WARNING = ComponentUtils.formatList(List.of(FURNITURE_WARNING_1, FURNITURE_WARNING_2), Component.literal("\n"));

    @SubscribeEvent
    public void onScreenRender(ScreenEvent.Init.Pre event) {
        if (event.getScreen() instanceof CreateWorldScreen screen && screen.height >= 260 && !ValhelsiaStructures.isFurnitureInstalled() && FURNITURE_WARNING_ENABLED.get()) {
            FocusableTextWidget widget = new FocusableTextWidget(screen.width, FURNITURE_WARNING, screen.getMinecraft().font, 12);
            widget.setPosition(screen.width / 2 - widget.getWidth() / 2, 190);

            event.addListener(widget);
        }
    }
}
