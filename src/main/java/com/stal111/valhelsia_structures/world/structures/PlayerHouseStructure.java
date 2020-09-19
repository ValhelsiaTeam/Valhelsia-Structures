package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
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
        super(villageConfigCodec, "player_house", 2);
    }

    @Override
    public int getSeparation() {
        return StructureGenConfig.PLAYER_HOUSE_SEPARATION.get();
    }

    @Override
    public int getDistance() {
        return StructureGenConfig.PLAYER_HOUSE_DISTANCE.get();
    }

    @Override
    public int getSeedModifier() {
        return 17357645;
    }

    @Override
    public double getSpawnChance() {
        return StructureGenConfig.PLAYER_HOUSE_SPAWN_CHANCE.get();
    }
}
