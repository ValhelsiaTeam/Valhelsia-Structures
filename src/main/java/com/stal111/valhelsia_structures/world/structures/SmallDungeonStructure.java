package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.utils.StructureType;
import net.minecraft.world.gen.feature.structure.VillageConfig;

/**
 * Small Dungeon Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.SmallDungeonStructure
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2020-05-27
 */

public class SmallDungeonStructure extends AbstractValhelsiaStructure {

    public SmallDungeonStructure(Codec<VillageConfig> villageConfigCodec) {
        super(villageConfigCodec, "small_dungeon", 3);
    }

    @Override
    public int getSeparation() {
        return StructureGenConfig.SMALL_DUNGEON_SEPARATION.get();
    }

    @Override
    public int getDistance() {
        return StructureGenConfig.SMALL_DUNGEON_DISTANCE.get();
    }

    @Override
    public int getSeedModifier() {
        return 23498567;
    }

    @Override
    public double getSpawnChance() {
        return StructureGenConfig.SMALL_DUNGEON_SPAWN_CHANCE.get();
    }

    @Override
    protected int getGenerationHeight() {
        return 20;
    }
}
