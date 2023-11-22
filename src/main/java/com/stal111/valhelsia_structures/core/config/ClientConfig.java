package com.stal111.valhelsia_structures.core.config;

import com.stal111.valhelsia_structures.client.event.ScreenEvents;
import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * @author Valhelsia Team
 * @since 2023-03-06
 */
public class ClientConfig {

    public ClientConfig(ModConfigSpec.Builder builder) {
        ScreenEvents.FURNITURE_WARNING_ENABLED.setConfiguredValue(value -> builder.comment("Show a warning if Valhelsia Furniture is not installed before world creation.").define("furniture_warning", value));
    }
}
