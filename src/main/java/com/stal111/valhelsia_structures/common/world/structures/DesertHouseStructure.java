package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.core.init.ModStructureFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

/**
 * Desert House Structure <br>
 * Valhelsia-Structures - com.stal111.valhelsia_structures.common.world.structures.DesertHouseStructure
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2020-05-27
 */

public class DesertHouseStructure extends AbstractValhelsiaStructure {

    public DesertHouseStructure(Codec<JigsawConfiguration> villageConfigCodec) {
        super(villageConfigCodec, "desert_house", 2,
                new StructureConfigEntry(0.7D, 30, 8, Biome.BiomeCategory.DESERT.getName()));
    }

    @Override
    public int getSeedModifier() {
        return 14862926;
    }

    @Override
    public ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> getStructureFeature() {
        return ModStructureFeatures.DESERT_HOUSE;
    }
}
