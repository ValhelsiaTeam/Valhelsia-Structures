package com.stal111.valhelsia_structures.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Structure Config Entry <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.config.StructureConfigEntry
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-02-08
 */
public class StructureConfigEntry {

    public ForgeConfigSpec.BooleanValue generate;

    private final double defaultSpawnChance;
    public ForgeConfigSpec.DoubleValue configuredSpawnChance;
    private final int defaultSpacing;
    public ForgeConfigSpec.IntValue configuredSpacing;
    private final int defaultSeparation;
    public ForgeConfigSpec.IntValue configuredSeparation;

    private final List<String> defaultBiomeCategories;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> configuredBiomeCategories;
    private final List<String> defaultBlacklistedDimensions;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> configuredBlacklistedDimensions;
    private final List<String> defaultBlacklistedBiomes;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> configuredBlacklistedBiomes;

    public StructureConfigEntry(double spawnChance, int spacing, int separation, String... biomeCategories) {
        this.defaultSpawnChance = spawnChance;
        this.defaultSpacing = spacing;
        this.defaultSeparation = separation;
        this.defaultBiomeCategories = Arrays.asList(biomeCategories);
        this.defaultBlacklistedBiomes = Collections.emptyList();
        this.defaultBlacklistedDimensions = Collections.emptyList();
    }

    public StructureConfigEntry(double spawnChance, int spacing, int separation, List<String> biomeCategories, List<String> blacklistedBiomes, List<String> blacklistedDimensions) {
        this.defaultSpawnChance = spawnChance;
        this.defaultSpacing = spacing;
        this.defaultSeparation = separation;
        this.defaultBiomeCategories = biomeCategories;
        this.defaultBlacklistedBiomes = blacklistedBiomes;
        this.defaultBlacklistedDimensions = blacklistedDimensions;
    }

    public double getDefaultSpawnChance() {
        return defaultSpawnChance;
    }

    public int getDefaultSpacing() {
        return defaultSpacing;
    }

    public int getDefaultSeparation() {
        return defaultSeparation;
    }

    public List<String> getDefaultBiomeCategories() {
        return defaultBiomeCategories;
    }

    public List<String> getDefaultBlacklistedDimensions() {
        return defaultBlacklistedDimensions;
    }

    public List<String> getDefaultBlacklistedBiomes() {
        return defaultBlacklistedBiomes;
    }
}
