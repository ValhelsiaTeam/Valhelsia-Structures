package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.init.ModStructureFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

/**
 * Castle Ruin Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.CastleRuinStructure
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2020-05-27
 */

public class CastleRuinStructure extends AbstractValhelsiaStructure {

    public CastleRuinStructure(Codec<VillageConfig> villageConfigCodec) {
        super(villageConfigCodec, "castle_ruin", 2,
                new StructureConfigEntry(0.6D, 35, 8,
                        Biome.Category.PLAINS.getName(),
                        Biome.Category.FOREST.getName(),
                        Biome.Category.TAIGA.getName()
                ));
    }

    @Override
    public int getSeedModifier() {
        return 14357618;
    }

    @Override
    public StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> getStructureFeature() {
        return ModStructureFeatures.CASTLE_RUIN;
    }
}
