package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.init.ModStructureFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

/**
 * Player House Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.PlayerHouseStructure
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2020-05-27
 */

public class PlayerHouseStructure extends AbstractValhelsiaStructure {

    public PlayerHouseStructure(Codec<VillageConfig> villageConfigCodec) {
        super(villageConfigCodec, "player_house", 2,
                new StructureConfigEntry(0.7D, 30, 8,
                        Biome.Category.PLAINS.getName(),
                        Biome.Category.FOREST.getName(),
                        Biome.Category.EXTREME_HILLS.getName(),
                        Biome.Category.TAIGA.getName()
                ));
    }

    @Override
    public int getSeedModifier() {
        return 17357645;
    }

    @Override
    public StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> getStructureFeature() {
        return ModStructureFeatures.PLAYER_HOUSE;
    }
}