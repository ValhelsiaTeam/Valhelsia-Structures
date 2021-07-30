package com.stal111.valhelsia_structures.config;

import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.utils.StructureUtils;
import com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.world.IValhelsiaStructure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Structure Generation Config
 * Valhelsia Structures - com.stal111.valhelsia_structures.config.StructureGenConfig
 *
 * @author Valhelsia Team
 * @version 0.1.3
 * @since 2020-05-29
 */

public class StructureGenConfig {

    public static ForgeConfigSpec.IntValue FLATNESS_DELTA;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> BLACKLISTED_DIMENSIONS;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> BLACKLISTED_BIOMES;

    public static void init(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        COMMON_BUILDER.push("structures");

        FLATNESS_DELTA = COMMON_BUILDER.comment("How flat does terrain need to be for surface structures to spawn? (in blocks) [default: 4]").defineInRange("global.flatness_delta", 4, 0, 64);
        BLACKLISTED_DIMENSIONS = COMMON_BUILDER.comment("Dimensions in which Structures can NOT generate in").defineList("global.blacklisted_dimensions", Collections.EMPTY_LIST, StructureGenConfig::validateString);
        BLACKLISTED_BIOMES = COMMON_BUILDER.comment("Biomes in which Structures can NOT generate in").defineList("global.blacklisted_biomes", StructureUtils.getAllBiomesForCategory(Biome.Category.RIVER, Biome.Category.OCEAN, Biome.Category.BEACH), StructureGenConfig::validateBiome);

        for (IValhelsiaStructure iStructure : ModStructures.MOD_STRUCTURES) {
            AbstractValhelsiaStructure structure = (AbstractValhelsiaStructure) iStructure.getStructure();

            StructureConfigEntry structureConfigEntry = structure.getStructureConfigEntry();

            structureConfigEntry.generate = COMMON_BUILDER.comment("Generate? [default: true]").define(structure.getName() + ".generate", true);

            structureConfigEntry.configuredSpawnChance = COMMON_BUILDER.comment("Spawn Chance [default: " + structureConfigEntry.getDefaultSpawnChance() + "]").defineInRange(structure.getName() + ".spawn_chance", structureConfigEntry.getDefaultSpawnChance(), 0.0, 1.0);
            structureConfigEntry.configuredSpacing = COMMON_BUILDER.comment("Spacing (in chunks) [default: " + structureConfigEntry.getDefaultSpacing() + "]").defineInRange(structure.getName() + ".spacing", structureConfigEntry.getDefaultSpacing(), 0, 200);
            structureConfigEntry.configuredSeparation = COMMON_BUILDER.comment("Minimum Separation (in chunks) [default: " + structureConfigEntry.getDefaultSeparation() + "]").defineInRange(structure.getName() + ".separation", structureConfigEntry.getDefaultSeparation(), 0, 200);

            structureConfigEntry.configuredBiomeCategories = COMMON_BUILDER.comment("Biome Categories the structure can generate in \nAllowed Values: " + Arrays.toString(Biome.Category.values()).toLowerCase(Locale.ROOT)).defineList(structure.getName() + ".biome_categories", structureConfigEntry.getDefaultBiomeCategories(), o -> o instanceof String);
            structureConfigEntry.configuredBlacklistedDimensions = COMMON_BUILDER.comment("Dimensions the structure can NOT generate in").defineList(structure.getName() + ".blacklisted_dimensions", structureConfigEntry.getDefaultBlacklistedDimensions(), o -> o instanceof String);
            structureConfigEntry.configuredBlacklistedBiomes = COMMON_BUILDER.comment("Biomes the structure can NOT generate in").defineList(structure.getName() + ".blacklisted_biomes", structureConfigEntry.getDefaultBlacklistedBiomes(), o -> o instanceof String);
        }

        COMMON_BUILDER.pop();
    }

    private static boolean validateBiome(Object o) {
        return o == null || ((String) o).contains("*") || ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) o));
    }

    private static boolean validateString(Object o) {
        return o == null || ((String) o).contains("*");
    }
}
