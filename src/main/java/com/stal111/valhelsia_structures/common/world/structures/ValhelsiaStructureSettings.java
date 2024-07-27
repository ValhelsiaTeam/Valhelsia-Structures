package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.utils.ConfigurableValue;

/**
 * @author Valhelsia Team
 * @since 2022-12-22
 */
public record ValhelsiaStructureSettings(double spawnChance, int customMargin, boolean individualTerrainAdjustment) {

    public static final ConfigurableValue<Integer> DEFAULT_MARGIN = ConfigurableValue.of(12);

    public static final MapCodec<ValhelsiaStructureSettings> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.DOUBLE.optionalFieldOf("spawn_chance", 1.0D).forGetter(ValhelsiaStructureSettings::spawnChance),
            Codec.INT.optionalFieldOf("custom_margin", DEFAULT_MARGIN.get()).forGetter(ValhelsiaStructureSettings::customMargin),
            Codec.BOOL.optionalFieldOf("individual_terrain_adjustment", false).forGetter(ValhelsiaStructureSettings::individualTerrainAdjustment)
    ).apply(instance, ValhelsiaStructureSettings::new));
}
