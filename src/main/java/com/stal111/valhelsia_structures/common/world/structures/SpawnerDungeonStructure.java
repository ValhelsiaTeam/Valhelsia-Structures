package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.init.ModStructureFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

import javax.annotation.Nonnull;

/**
 * Spawner Dungeon Structure <br>
 * Valhelsia-Structures - com.stal111.valhelsia_structures.common.world.structures.SpawnerDungeonStructure
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2020-05-27
 */
public class SpawnerDungeonStructure extends AbstractValhelsiaStructure {

    public SpawnerDungeonStructure(Codec<JigsawConfiguration> villageConfigCodec) {
        super(villageConfigCodec, "spawner_dungeon", 3,
                new StructureConfigEntry(0.7D, 30, 8,
                        Biome.BiomeCategory.PLAINS.getName(),
                        Biome.BiomeCategory.FOREST.getName(),
                        Biome.BiomeCategory.EXTREME_HILLS.getName(),
                        Biome.BiomeCategory.TAIGA.getName(),
                        Biome.BiomeCategory.DESERT.getName(),
                        Biome.BiomeCategory.MESA.getName(),
                        Biome.BiomeCategory.SAVANNA.getName(),
                        Biome.BiomeCategory.JUNGLE.getName(),
                        Biome.BiomeCategory.ICY.getName(),
                        Biome.BiomeCategory.SWAMP.getName(),
                        Biome.BiomeCategory.MUSHROOM.getName()
                ));
    }

    @Override
    public int getSeedModifier() {
        return 23498567;
    }

    @Override
    public ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> getStructureFeature() {
        return ModStructureFeatures.SPAWNER_DUNGEON;
    }

    @Nonnull
    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
    }

    @Override
    public boolean transformsSurroundingLand() {
        return false;
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