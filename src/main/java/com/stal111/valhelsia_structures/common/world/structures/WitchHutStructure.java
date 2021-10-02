package com.stal111.valhelsia_structures.common.world.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.init.ModStructureFeatures;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

import java.util.List;

/**
 * Witch Hut Structure <br>
 * Valhelsia-Structures - com.stal111.valhelsia_structures.common.world.structures.WitchHutStructure
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-04-23
 */
public class WitchHutStructure extends AbstractValhelsiaStructure {

    private static final List<MobSpawnSettings.SpawnerData> MONSTER_SPAWN_LIST = ImmutableList.of(new MobSpawnSettings.SpawnerData(EntityType.WITCH, 100, 1, 1));
    private static final List<MobSpawnSettings.SpawnerData> CREATURE_SPAWN_LIST = ImmutableList.of(new MobSpawnSettings.SpawnerData(EntityType.CAT, 100, 1, 1));

    public WitchHutStructure(Codec<JigsawConfiguration> villageConfigCodec) {
        super(villageConfigCodec, "witch_hut", 1,
                new StructureConfigEntry(0.85D, 25, 7, Biome.BiomeCategory.SWAMP.getName()));
    }

    @Override
    public int getSeedModifier() {
        return 70882951;
    }

    @Override
    public ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> getStructureFeature() {
        return ModStructureFeatures.WITCH_HUT;
    }

    @Override
    public List<MobSpawnSettings.SpawnerData> getDefaultSpawnList() {
        return MONSTER_SPAWN_LIST;
    }

    @Override
    public List<MobSpawnSettings.SpawnerData> getDefaultCreatureSpawnList() {
        return CREATURE_SPAWN_LIST;
    }

    @Override
    public int getMargin() {
        return 3;
    }

    @Override
    public boolean canGenerateOnWater() {
        return true;
    }
}