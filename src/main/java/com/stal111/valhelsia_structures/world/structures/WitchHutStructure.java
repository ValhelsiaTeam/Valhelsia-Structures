package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.init.ModStructureFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

/**
 * Witch Hut Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.WitchHutStructure
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-04-23
 */

public class WitchHutStructure extends AbstractValhelsiaStructure {

    public WitchHutStructure(Codec<VillageConfig> villageConfigCodec) {
        super(villageConfigCodec, "witch_hut", 3,
                new StructureConfigEntry(0.7D, 25, 7, Biome.Category.SWAMP.getName()));
    }

    @Override
    public int getSeedModifier() {
        return 70882951;
    }

    @Override
    public StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> getStructureFeature() {
        return ModStructureFeatures.WITCH_HUT;
    }
}
