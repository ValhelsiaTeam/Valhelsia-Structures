package com.stal111.valhelsia_structures.common.world.structures;

import com.stal111.valhelsia_structures.utils.ConfigurableValue;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Structure Settings <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.world.structures.StructureSettings
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.1.0
 * @since 2022-03-20
 */
public record StructureSettings(ConfigurableValue<Double> spawnChance,
                                ConfigurableValue<Integer> spacing,
                                ConfigurableValue<Integer> separation,
                                TagKey<Biome> validBiomes,
                                @Nullable Map<MobCategory, StructureSpawnOverride> mobSpawns) {

    public StructureSettings(ConfigurableValue<Double> spawnChance, ConfigurableValue<Integer> spacing, ConfigurableValue<Integer> separation, TagKey<Biome> validBiomes) {
        this(spawnChance, spacing, separation, validBiomes, null);
    }

    public ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> buildFeature(RegistryObject<? extends AbstractValhelsiaStructure> structure, Holder<StructureTemplatePool> startPool) {
        return structure.get().configured(new JigsawConfiguration(startPool, 7), this.validBiomes(), structure.get().step() == GenerationStep.Decoration.SURFACE_STRUCTURES, this.mobSpawns() == null ? Map.of() : this.mobSpawns());
    }
}
