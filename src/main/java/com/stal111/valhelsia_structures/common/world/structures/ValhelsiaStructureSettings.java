package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.utils.ConfigurableValue;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Valhelsia Team
 * @since 2022-12-22
 */
public record ValhelsiaStructureSettings(@Nullable Double spawnChance,
                                         @Nullable Integer customMargin,
                                         @Nullable Boolean individualTerrainAdjustment) {

    public static final ConfigurableValue<Integer> DEFAULT_MARGIN = ConfigurableValue.of(12);

    public static final MapCodec<ValhelsiaStructureSettings> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.DOUBLE.optionalFieldOf("spawn_chance").forGetter(settings -> Optional.ofNullable(settings.spawnChance)),
            Codec.INT.optionalFieldOf("custom_margin").forGetter(settings -> Optional.ofNullable(settings.customMargin)),
            Codec.BOOL.optionalFieldOf("individual_terrain_adjustment").forGetter(settings -> Optional.ofNullable(settings.individualTerrainAdjustment))
    ).apply(instance, (spawnChance, customMargin, individualTerrainAdjustment) -> {
        return new ValhelsiaStructureSettings(spawnChance.orElse(null), customMargin.orElse(null), individualTerrainAdjustment.orElse(null));
    }));

    @Override
    public Double spawnChance() {
        return this.spawnChance == null ? 1.0D : this.spawnChance;
    }

    @Override
    public Integer customMargin() {
        return this.customMargin == null ? DEFAULT_MARGIN.get() : this.customMargin;
    }

    @Override
    public Boolean individualTerrainAdjustment() {
        return individualTerrainAdjustment != null && this.individualTerrainAdjustment;
    }
}
