package com.stal111.valhelsia_structures.config;

import net.minecraft.resources.ResourceLocation;
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

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> JAR_BLACKLIST;

    public static void init(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        COMMON_BUILDER.comment("Block Config");

        JAR_BLACKLIST = COMMON_BUILDER.comment("Plants that should not be able to be placed in a jar").defineList("blocks.jar.plant_blacklist", Arrays.asList("minecraft:cactus", "minecraft:crimson_roots", "minecraft:warped_roots"), BlockConfig::validateItem);
    }

    private static boolean validateItem(Object o) {
        return o == null || ForgeRegistries.ITEMS.containsKey(new ResourceLocation((String) o));
    }
}
