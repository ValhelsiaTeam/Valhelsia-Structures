package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
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
        super(villageConfigCodec, name, 2);
    }

    @Override
    public int getSeparation() {
        return 8;
    }

    @Override
    public int getDistance() {
        return 35;
    }

    @Override
    public int getSeedModifier() {
        return 1666666;
    }

    @Override
    public double getSpawnChance() {
        return 0;
    }
}
