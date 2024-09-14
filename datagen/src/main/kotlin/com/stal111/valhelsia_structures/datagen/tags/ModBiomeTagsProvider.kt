package com.stal111.valhelsia_structures.datagen.tags

import com.stal111.valhelsia_structures.utils.ModTags
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.biome.Biomes
import net.neoforged.neoforge.common.Tags
import net.valhelsia.dataforge.DataProviderContext
import net.valhelsia.dataforge.tag.DataForgeBiomeTagsProvider

class ModBiomeTagsProvider(context: DataProviderContext) : DataForgeBiomeTagsProvider(context) {
    override fun addTags(provider: HolderLookup.Provider) {
        tag(ModTags.Biomes.HAS_CASTLE).addTags(Tags.Biomes.IS_PLAINS, BiomeTags.IS_FOREST, BiomeTags.IS_TAIGA)
        tag(ModTags.Biomes.HAS_CASTLE_RUIN).addTags(Tags.Biomes.IS_PLAINS, BiomeTags.IS_FOREST, BiomeTags.IS_TAIGA)
        tag(ModTags.Biomes.HAS_DESERT_HOUSE).addTag(Tags.Biomes.IS_DESERT)
        tag(ModTags.Biomes.DESERT_HOUSE_CONDITION).addTag(Tags.Biomes.IS_SANDY)
        tag(ModTags.Biomes.HAS_FORGE).addTags(
            Tags.Biomes.IS_PLAINS,
            BiomeTags.IS_FOREST,
            BiomeTags.IS_TAIGA,
            BiomeTags.IS_HILL
        )
        tag(ModTags.Biomes.HAS_PLAYER_HOUSE).addTags(
            Tags.Biomes.IS_PLAINS,
            BiomeTags.IS_FOREST,
            BiomeTags.IS_TAIGA,
            BiomeTags.IS_HILL
        )
        tag(ModTags.Biomes.HAS_SPAWNER_DUNGEON).addTag(Tags.Biomes.IS_PLAINS).add(
            Biomes.ICE_SPIKES,
            Biomes.DESERT,
            Biomes.FOREST,
            Biomes.FLOWER_FOREST,
            Biomes.BIRCH_FOREST,
            Biomes.DARK_FOREST,
            Biomes.OLD_GROWTH_BIRCH_FOREST,
            Biomes.OLD_GROWTH_PINE_TAIGA,
            Biomes.OLD_GROWTH_SPRUCE_TAIGA,
            Biomes.TAIGA,
            Biomes.SNOWY_TAIGA,
            Biomes.SAVANNA,
            Biomes.SAVANNA_PLATEAU,
            Biomes.WINDSWEPT_HILLS,
            Biomes.WINDSWEPT_GRAVELLY_HILLS,
            Biomes.WINDSWEPT_FOREST,
            Biomes.WINDSWEPT_SAVANNA,
            Biomes.JUNGLE,
            Biomes.SPARSE_JUNGLE,
            Biomes.BAMBOO_JUNGLE,
            Biomes.BADLANDS,
            Biomes.ERODED_BADLANDS,
            Biomes.WOODED_BADLANDS,
            Biomes.MEADOW,
            Biomes.GROVE,
            Biomes.SNOWY_SLOPES,
            Biomes.FROZEN_PEAKS,
            Biomes.JAGGED_PEAKS,
            Biomes.STONY_PEAKS,
            Biomes.MUSHROOM_FIELDS,
            Biomes.DRIPSTONE_CAVES,
            Biomes.LUSH_CAVES
        )
        tag(ModTags.Biomes.HAS_TOWER_RUIN).addTags(
            Tags.Biomes.IS_PLAINS,
            BiomeTags.IS_FOREST,
            BiomeTags.IS_TAIGA,
            BiomeTags.IS_HILL
        )
        tag(ModTags.Biomes.HAS_WITCH_HUT).add(Biomes.SWAMP)
        tag(ModTags.Biomes.HAS_BIG_TREE).addTags(Tags.Biomes.IS_PLAINS, BiomeTags.IS_FOREST)
        tag(ModTags.Biomes.HAS_SPAWNER_ROOM).addTag(BiomeTags.IS_OVERWORLD)
        tag(ModTags.Biomes.HAS_DEEP_SPAWNER_ROOM).addTag(BiomeTags.IS_OVERWORLD)

        tag(BiomeTags.HAS_SWAMP_HUT).replace()
    }
}
