package com.stal111.valhelsia_structures.core.config;

import com.stal111.valhelsia_structures.core.init.ModStructures;
import com.stal111.valhelsia_structures.utils.StructureUtils;
import com.stal111.valhelsia_structures.common.world.structures.AbstractValhelsiaStructure;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.common.world.IValhelsiaStructure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Common Config <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.config.CommonConfig
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-10-01
 */
public class CommonConfig {

    public final ForgeConfigSpec.IntValue flatnessDelta;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistedDimensions;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistedBiomes;
    public final ForgeConfigSpec.BooleanValue disableDousedTorch;

    public CommonConfig(ForgeConfigSpec.Builder builder) {
        builder.push("structures");
        this.flatnessDelta = builder.comment("How flat does terrain need to be for surface structures to spawn? (in blocks) [default: 4]").defineInRange("global.flatness_delta", 4, 0, 64);
        this.blacklistedDimensions = builder.comment("Dimensions in which Structures can NOT generate in").defineList("global.blacklisted_dimensions", Collections.emptyList(), this::validateDimension);
        this.blacklistedBiomes = builder.comment("Biomes in which Structures can NOT generate in").defineList("global.blacklisted_biomes", StructureUtils.getAllBiomesForCategory(Biome.BiomeCategory.RIVER, Biome.BiomeCategory.OCEAN, Biome.BiomeCategory.BEACH), this::validateBiome);

        for (IValhelsiaStructure iStructure : ModStructures.MOD_STRUCTURES) {
            AbstractValhelsiaStructure structure = (AbstractValhelsiaStructure) iStructure.getStructure();

            StructureConfigEntry structureConfigEntry = structure.getStructureConfigEntry();

            structureConfigEntry.generate = builder.comment("Generate? [default: true]").define(structure.getName() + ".generate", true);

            structureConfigEntry.configuredSpawnChance = builder.comment("Spawn Chance [default: " + structureConfigEntry.getDefaultSpawnChance() + "]").defineInRange(structure.getName() + ".spawn_chance", structureConfigEntry.getDefaultSpawnChance(), 0.0, 1.0);
            structureConfigEntry.configuredSpacing = builder.comment("Spacing (in chunks) [default: " + structureConfigEntry.getDefaultSpacing() + "]").defineInRange(structure.getName() + ".spacing", structureConfigEntry.getDefaultSpacing(), 0, 200);
            structureConfigEntry.configuredSeparation = builder.comment("Minimum Separation (in chunks) [default: " + structureConfigEntry.getDefaultSeparation() + "]").defineInRange(structure.getName() + ".separation", structureConfigEntry.getDefaultSeparation(), 0, 200);

            structureConfigEntry.configuredBiomeCategories = builder.comment("Biome Categories the structure can generate in \nAllowed Values: " + Arrays.toString(Biome.BiomeCategory.values()).toLowerCase(Locale.ROOT)).defineList(structure.getName() + ".biome_categories", structureConfigEntry.getDefaultBiomeCategories(), o -> o instanceof String);
            structureConfigEntry.configuredBlacklistedDimensions = builder.comment("Dimensions the structure can NOT generate in").defineList(structure.getName() + ".blacklisted_dimensions", structureConfigEntry.getDefaultBlacklistedDimensions(), this::validateDimension);
            structureConfigEntry.configuredBlacklistedBiomes = builder.comment("Biomes the structure can NOT generate in").defineList(structure.getName() + ".blacklisted_biomes", structureConfigEntry.getDefaultBlacklistedBiomes(), this::validateBiome);
        }

        builder.pop();

        builder.push("blocks");

        this.disableDousedTorch = builder.comment("Enable/Disable the Doused Torch Feature. If disabled Water will no longer transform normal Torches into Doused Torches. \\n Doused Torches will however still generate in structures. [default: false]").define("doused_torch.disable", false);

        builder.pop();
    }

    private boolean validateBiome(Object o) {
        return o == null || ((String) o).contains("*") || ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) o));
    }

    private boolean validateDimension(Object o) {
        return o == null || ((String) o).contains("*") || ((String) o).contains(":");
    }
}
