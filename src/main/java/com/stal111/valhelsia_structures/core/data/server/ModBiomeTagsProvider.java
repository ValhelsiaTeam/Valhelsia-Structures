package com.stal111.valhelsia_structures.core.data.server;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

/**
 * Mod Biome Tags Provider <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.data.ModBiomeTagsProvider
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.1.0
 * @since 2022-03-20
 */
public class ModBiomeTagsProvider extends BiomeTagsProvider {

    public ModBiomeTagsProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, ValhelsiaStructures.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ModTags.Biomes.IS_PLAINS).add(Biomes.PLAINS, Biomes.SNOWY_PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.MEADOW);
        this.tag(ModTags.Biomes.IS_DESERT).add(Biomes.DESERT);

        this.tag(ModTags.Biomes.HAS_CASTLE).addTag(ModTags.Biomes.IS_PLAINS).addTag(BiomeTags.IS_FOREST).addTag(BiomeTags.IS_TAIGA);
        this.tag(ModTags.Biomes.HAS_CASTLE_RUIN).addTag(ModTags.Biomes.IS_PLAINS).addTag(BiomeTags.IS_FOREST).addTag(BiomeTags.IS_TAIGA);
        this.tag(ModTags.Biomes.HAS_DESERT_HOUSE).addTag(ModTags.Biomes.IS_DESERT);
        this.tag(ModTags.Biomes.HAS_FORGE).addTag(ModTags.Biomes.IS_PLAINS).addTag(BiomeTags.IS_FOREST).addTag(BiomeTags.IS_TAIGA).addTag(BiomeTags.IS_HILL);
        this.tag(ModTags.Biomes.HAS_PLAYER_HOUSE).addTag(ModTags.Biomes.IS_PLAINS).addTag(BiomeTags.IS_FOREST).addTag(BiomeTags.IS_TAIGA).addTag(BiomeTags.IS_HILL);
        this.tag(ModTags.Biomes.HAS_SPAWNER_DUNGEON).addTag(ModTags.Biomes.IS_PLAINS).add(Biomes.ICE_SPIKES).add(Biomes.DESERT).add(Biomes.FOREST).add(Biomes.FLOWER_FOREST).add(Biomes.BIRCH_FOREST).add(Biomes.DARK_FOREST).add(Biomes.OLD_GROWTH_BIRCH_FOREST).add(Biomes.OLD_GROWTH_PINE_TAIGA).add(Biomes.OLD_GROWTH_SPRUCE_TAIGA).add(Biomes.TAIGA).add(Biomes.SNOWY_TAIGA).add(Biomes.SAVANNA).add(Biomes.SAVANNA_PLATEAU).add(Biomes.WINDSWEPT_HILLS).add(Biomes.WINDSWEPT_GRAVELLY_HILLS).add(Biomes.WINDSWEPT_FOREST).add(Biomes.WINDSWEPT_SAVANNA).add(Biomes.JUNGLE).add(Biomes.SPARSE_JUNGLE).add(Biomes.BAMBOO_JUNGLE).add(Biomes.BADLANDS).add(Biomes.ERODED_BADLANDS).add(Biomes.WOODED_BADLANDS).add(Biomes.MEADOW).add(Biomes.GROVE).add(Biomes.SNOWY_SLOPES).add(Biomes.FROZEN_PEAKS).add(Biomes.JAGGED_PEAKS).add(Biomes.STONY_PEAKS).add(Biomes.MUSHROOM_FIELDS).add(Biomes.DRIPSTONE_CAVES).add(Biomes.LUSH_CAVES);
        this.tag(ModTags.Biomes.HAS_TOWER_RUIN).addTag(ModTags.Biomes.IS_PLAINS).addTag(BiomeTags.IS_FOREST).addTag(BiomeTags.IS_TAIGA).addTag(BiomeTags.IS_HILL);
        this.tag(ModTags.Biomes.HAS_WITCH_HUT).add(Biomes.SWAMP);
        this.tag(ModTags.Biomes.HAS_BIG_TREE).addTag(ModTags.Biomes.IS_PLAINS).addTag(BiomeTags.IS_FOREST);
    }
}
