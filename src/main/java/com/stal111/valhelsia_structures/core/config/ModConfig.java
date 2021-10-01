package com.stal111.valhelsia_structures.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Mod Config <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.config.ModConfig
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-10-01
 */
public class ModConfig {

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final CommonConfig COMMON;

    static {
        final Pair<CommonConfig, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }
}
