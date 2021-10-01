package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.init.ModStructureFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

/**
 * Player House Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.PlayerHouseStructure
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2020-05-27
 */

public class PlayerHouseStructure extends AbstractValhelsiaStructure {

    public PlayerHouseStructure(Codec<JigsawConfiguration> villageConfigCodec) {
        super(villageConfigCodec, "player_house", 2,
                new StructureConfigEntry(0.7D, 30, 8,
                        Biome.BiomeCategory.PLAINS.getName(),
                        Biome.BiomeCategory.FOREST.getName(),
                        Biome.BiomeCategory.EXTREME_HILLS.getName(),
                        Biome.BiomeCategory.TAIGA.getName()
                ));
    }

    @Override
    public int getSeedModifier() {
        return 17357645;
    }

    @Override
    public ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> getStructureFeature() {
        return ModStructureFeatures.PLAYER_HOUSE;
    }
}