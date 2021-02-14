package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureConfigEntry;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

/**
 * Removed Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.RemovedStructure
 *
 * Represents a structure that has been removed from the mod. This is to work around a vanilla bug without having
 * to directly modify vanilla code.
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2020-06-05
 */
public class RemovedStructure extends AbstractValhelsiaStructure {

    public RemovedStructure(Codec<VillageConfig> villageConfigCodec, String name) {
        super(villageConfigCodec, name, 2, new StructureConfigEntry(0.0D, 0, 0));
    }

    @Override
    public int getSeedModifier() {
        return 1666666;
    }

    @Override
    public StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> getStructureFeature() {
        return null;
    }
}
