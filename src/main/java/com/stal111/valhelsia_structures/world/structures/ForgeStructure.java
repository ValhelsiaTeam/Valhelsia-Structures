package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.init.ModStructureFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

/**
 * Forge Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.ForgeStructure
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2020-05-27
 */

public class ForgeStructure extends AbstractValhelsiaStructure {

    public ForgeStructure(Codec<JigsawConfiguration> villageConfigCodec) {
        super(villageConfigCodec, "forge", 2,
                new StructureConfigEntry(0.7D, 30, 8,
                        Biome.BiomeCategory.PLAINS.getName(),
                        Biome.BiomeCategory.FOREST.getName(),
                        Biome.BiomeCategory.EXTREME_HILLS.getName(),
                        Biome.BiomeCategory.TAIGA.getName()
                ));
    }

    @Override
    public int getSeedModifier() {
        return 12857691;
    }

    @Override
    public ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> getStructureFeature() {
        return ModStructureFeatures.FORGE;
    }
}
