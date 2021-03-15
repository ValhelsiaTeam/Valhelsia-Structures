package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.init.ModStructureFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

import java.util.Arrays;

/**
 * Castle Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.CastleStructure
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2020-05-27
 */

public class CastleStructure extends AbstractValhelsiaStructure {

    public CastleStructure(Codec<VillageConfig> villageConfigCodec) {
        super(villageConfigCodec, "castle", 3,
                new StructureConfigEntry(0.5D, 40, 8,
                        Biome.Category.PLAINS.getName(),
                        Biome.Category.FOREST.getName(),
                        Biome.Category.TAIGA.getName()
                ));
    }

    @Override
    public int getSeedModifier() {
        return 16987356;
    }

    @Override
    public StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> getStructureFeature() {
        return ModStructureFeatures.CASTLE;
    }
}
