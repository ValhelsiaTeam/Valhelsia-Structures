package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.init.ModStructureFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

/**
 * Spawner Dungeon Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.SpawnerDungeonStructure
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-05-27
 */

public class SpawnerDungeonStructure extends AbstractValhelsiaStructure {

    public SpawnerDungeonStructure(Codec<VillageConfig> villageConfigCodec) {
        super(villageConfigCodec, "spawner_dungeon", 3,
                new StructureConfigEntry(0.7D, 30, 8,
                        Biome.Category.PLAINS.getName(),
                        Biome.Category.FOREST.getName(),
                        Biome.Category.EXTREME_HILLS.getName(),
                        Biome.Category.TAIGA.getName(),
                        Biome.Category.DESERT.getName(),
                        Biome.Category.MESA.getName(),
                        Biome.Category.SAVANNA.getName(),
                        Biome.Category.JUNGLE.getName(),
                        Biome.Category.ICY.getName(),
                        Biome.Category.SWAMP.getName(),
                        Biome.Category.MUSHROOM.getName()
                ));
    }

    @Override
    public int getSeedModifier() {
        return 23498567;
    }

    @Override
    public StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> getStructureFeature() {
        return ModStructureFeatures.SPAWNER_DUNGEON;
    }

    @Override
    public int getGenerationHeight() {
        return 20;
    }

    @Override
    public boolean checkSurface() {
        return false;
    }

    @Override
    public boolean hasMargin() {
        return false;
    }
}