package com.stal111.valhelsia_structures.config;

import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.utils.StructureType;
import com.stal111.valhelsia_structures.utils.StructureUtils;
import com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure;
import com.stal111.valhelsia_structures.world.structures.RemovedStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

/**
 * Structure Generation Config
 * Valhelsia Structures - com.stal111.valhelsia_structures.config.StructureGenConfig
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-05-29
 */

public class StructureGenConfig {

    public static ForgeConfigSpec.IntValue FLATNESS_DELTA;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> BLACKLISTED_BIOMES;

    public static void init(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        COMMON_BUILDER.push("structures");

        FLATNESS_DELTA = COMMON_BUILDER.comment("How flat does terrain need to be for surface structures to spawn? (in blocks) [default: 4]").defineInRange("global.flatness_delta", 4, 0, 64);
        BLACKLISTED_BIOMES = COMMON_BUILDER.comment("Biomes in which Structures can NOT generate in").defineList("global.blacklisted_biomes", StructureUtils.getAllBiomesForCategory(Biome.Category.RIVER, Biome.Category.OCEAN, Biome.Category.BEACH), StructureGenConfig::validateBiome);

        for (Map.Entry<StructureType, List<AbstractValhelsiaStructure>> entry : ModStructures.STRUCTURES_MAP.entrySet()) {
            entry.getValue().forEach(structure -> {
                if (!(structure instanceof RemovedStructure)) {
                    StructureConfigEntry structureConfigEntry = structure.getStructureConfigEntry();

                    structureConfigEntry.generate = COMMON_BUILDER.comment("Generate? [default: true]").define(structure.getName() + ".generate", true);

                    structureConfigEntry.configuredSpawnChance = COMMON_BUILDER.comment("Spawn Chance [default: " + structureConfigEntry.getDefaultSpawnChance() + "]").defineInRange(structure.getName()  + ".spawn_chance", structureConfigEntry.getDefaultSpawnChance(), 0.0, 1.0);
                    structureConfigEntry.configuredSpacing = COMMON_BUILDER.comment("Spacing (in chunks) [default: " + structureConfigEntry.getDefaultSpacing() + "]").defineInRange(structure.getName()  + ".spacing", structureConfigEntry.getDefaultSpacing(), 0, 200);
                    structureConfigEntry.configuredSeparation = COMMON_BUILDER.comment("Minimum Separation (in chunks) [default: " + structureConfigEntry.getDefaultSeparation() + "]").defineInRange(structure.getName()  + ".separation", structureConfigEntry.getDefaultSeparation(), 0, 200);

                    structureConfigEntry.configuredBiomeCategories = COMMON_BUILDER.comment("Biome Categories the structure can generate in \nAllowed Values: " + Arrays.toString(Biome.Category.values()).toLowerCase(Locale.ROOT)).defineList(structure.getName() + ".biome_categories", structureConfigEntry.getDefaultBiomeCategories(), o -> o instanceof String);
                    structureConfigEntry.configuredBlacklistedBiomes = COMMON_BUILDER.comment("Biomes the structure can NOT generate in").defineList(structure.getName() + ".blacklisted_biomes", structureConfigEntry.getDefaultBlacklistedBiomes(), o -> o instanceof String);
                }
            });
        }

        COMMON_BUILDER.pop();
    }

    private static boolean validateBiome(Object o) {
        return o == null || ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) o));
    }
}
