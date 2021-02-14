package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.init.ModStructureFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

/**
 * Forge Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.ForgeStructure
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2020-05-27
 */

public class ForgeStructure extends AbstractValhelsiaStructure {

    public ForgeStructure(Codec<VillageConfig> villageConfigCodec) {
        super(villageConfigCodec, "forge", 2,
                new StructureConfigEntry(0.7D, 30, 8,
                        Biome.Category.PLAINS.getName(),
                        Biome.Category.FOREST.getName(),
                        Biome.Category.EXTREME_HILLS.getName(),
                        Biome.Category.TAIGA.getName()
                ));
    }

    @Override
    public int getSeedModifier() {
        return 12857691;
    }

    @Override
    public StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> getStructureFeature() {
        return ModStructureFeatures.FORGE;
    }
}
