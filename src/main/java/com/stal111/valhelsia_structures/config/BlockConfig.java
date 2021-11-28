package com.stal111.valhelsia_structures.config;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;

/**
 * Block Config
 * Valhelsia Structures - com.stal111.valhelsia_structures.config.BlockConfig
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-15
 */
public class BlockConfig {

    public static ForgeConfigSpec.BooleanValue DISABLE_DOUSED_TORCH;

    public static void init(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        COMMON_BUILDER.comment("Block Config");

        DISABLE_DOUSED_TORCH = COMMON_BUILDER.comment("Enable/Disable the Doused Torch Feature. If disabled Water will no longer transform normal Torches into Doused Torches. \n Doused Torches will however still generate in structures. [default: false]").define("blocks.doused_torch.disable", false);
    }

    private static boolean validateItem(Object o) {
        return o == null || ForgeRegistries.ITEMS.containsKey(new ResourceLocation((String) o));
    }
}
