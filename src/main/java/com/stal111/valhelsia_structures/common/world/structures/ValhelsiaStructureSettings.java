package com.stal111.valhelsia_structures.common.world.structures;

import com.stal111.valhelsia_structures.utils.ConfigurableValue;

import javax.annotation.Nullable;

/**
 * Valhelsia Structure Settings <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.world.structures.ValhelsiaStructureSettings
 *
 * @author Valhelsia Team
 * @version 1.19 - 0.2.0
 * @since 2022-03-20
 */
public record ValhelsiaStructureSettings(ConfigurableValue<Double> spawnChance, @Nullable ConfigurableValue<Integer> size, ConfigurableValue<Integer> margin) {

    public static int DEFAULT_MARGIN = 12;

    public static ValhelsiaStructureSettings of(ConfigurableValue<Double> spawnChance, @Nullable ConfigurableValue<Integer> size, ConfigurableValue<Integer> margin) {
        return new ValhelsiaStructureSettings(spawnChance, size, margin);
    }

    public static ValhelsiaStructureSettings of(ConfigurableValue<Double> spawnChance, @Nullable ConfigurableValue<Integer> size) {
        return new ValhelsiaStructureSettings(spawnChance, size, ConfigurableValue.of(DEFAULT_MARGIN));
    }
}
