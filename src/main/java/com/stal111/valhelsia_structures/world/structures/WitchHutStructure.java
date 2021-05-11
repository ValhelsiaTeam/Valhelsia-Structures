package com.stal111.valhelsia_structures.world.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.init.ModStructureFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

import java.util.List;

/**
 * Witch Hut Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.WitchHutStructure
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-04-23
 */

public class WitchHutStructure extends AbstractValhelsiaStructure {

    private static final List<MobSpawnInfo.Spawners> MONSTER_SPAWN_LIST = ImmutableList.of(new MobSpawnInfo.Spawners(EntityType.WITCH, 100, 1, 1));
    private static final List<MobSpawnInfo.Spawners> CREATURE_SPAWN_LIST = ImmutableList.of(new MobSpawnInfo.Spawners(EntityType.CAT, 100, 1, 1));

    public WitchHutStructure(Codec<VillageConfig> villageConfigCodec) {
        super(villageConfigCodec, "witch_hut", 1,
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

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return MONSTER_SPAWN_LIST;
    }

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList() {
        return CREATURE_SPAWN_LIST;
    }
}
