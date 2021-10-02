package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.core.init.ModStructureFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

/**
 * Castle Ruin Structure <br>
 * Valhelsia-Structures - com.stal111.valhelsia_structures.common.world.structures.CastleRuinStructure
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2020-05-27
 */

public class CastleRuinStructure extends AbstractValhelsiaStructure {

    public CastleRuinStructure(Codec<JigsawConfiguration> villageConfigCodec) {
        super(villageConfigCodec, "castle_ruin", 2,
                new StructureConfigEntry(0.6D, 35, 8,
                        Biome.BiomeCategory.PLAINS.getName(),
                        Biome.BiomeCategory.FOREST.getName(),
                        Biome.BiomeCategory.TAIGA.getName()
                ));
    }

    @Override
    public int getSeedModifier() {
        return 14357618;
    }

    @Override
    public ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> getStructureFeature() {
        return ModStructureFeatures.CASTLE_RUIN;
    }
}
