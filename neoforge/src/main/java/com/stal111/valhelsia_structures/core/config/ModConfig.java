package com.stal111.valhelsia_structures.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Mod Config <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.config.ModConfig
 *
 * @author Valhelsia Team
 * @since 2021-10-01
 */
public class ModConfig {

    public static final ModConfigSpec CLIENT_SPEC;
    public static final ClientConfig CLIENT;

    static {
        final Pair<ClientConfig, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT_SPEC = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
    }
}
