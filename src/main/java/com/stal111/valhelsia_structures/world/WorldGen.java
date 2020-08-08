package com.stal111.valhelsia_structures.world;

import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.init.ModStructures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * World Generation
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.WorldGen
 *
 * @author Valhelsia Team
 * @version 16.0.2
 * @since 2019-10-31
 */
public class WorldGen {

    /**
     * Setup World Generation
     *
     * Note: This should only ever be called from the main thread, since adding features to biomes is not thread-safe.
     */
    public static void setupWorldGen() {
        Iterator<Biome> biomes = ForgeRegistries.BIOMES.iterator();
        biomes.forEachRemaining((biome) -> {
            // Check Blacklist
            if (StructureGenConfig.BLACKLISTED_BIOMES.get().contains(Objects.requireNonNull(biome.getRegistryName()).toString())) {
                return;
            }

            // Add Structures
            if (StructureGenConfig.GENERATE_CASTLES.get() && checkBiome(StructureGenConfig.CASTLE_BIOME_CATEGORIES.get(), StructureGenConfig.CASTLE_BIOME_BLACKLIST.get(), biome)) {
                addStructure(biome, ModStructures.CASTLE.get());
            }

            if (StructureGenConfig.GENERATE_CASTLE_RUINS.get() && checkBiome(StructureGenConfig.CASTLE_RUIN_BIOME_CATEGORIES.get(), StructureGenConfig.CASTLE_RUIN_BIOME_BLACKLIST.get(), biome)) {
                addStructure(biome, ModStructures.CASTLE_RUIN.get());
            }

            if (StructureGenConfig.GENERATE_FORGES.get() && checkBiome(StructureGenConfig.FORGE_BIOME_CATEGORIES.get(), StructureGenConfig.FORGE_BIOME_BLACKLISTS.get(), biome)) {
                addStructure(biome, ModStructures.FORGE.get());
            }

            if (StructureGenConfig.GENERATE_PLAYER_HOUSES.get() && checkBiome(StructureGenConfig.PLAYER_HOUSE_BIOME_CATEGORIES.get(), StructureGenConfig.PLAYER_HOUSE_BIOME_BLACKLIST.get(), biome)) {
                addStructure(biome, ModStructures.PLAYER_HOUSE.get());
            }

            if (StructureGenConfig.GENERATE_TOWER_RUINS.get() && checkBiome(StructureGenConfig.TOWER_RUIN_BIOME_CATEGORIES.get(), StructureGenConfig.TOWER_RUIN_BIOME_BLACKLIST.get(), biome)) {
                addStructure(biome, ModStructures.TOWER_RUIN.get());
            }

            if (StructureGenConfig.GENERATE_DESERT_HOUSES.get() && checkBiome(StructureGenConfig.DESERT_HOUSE_BIOME_CATEGORIES.get(), StructureGenConfig.DESERT_HOUSE_BIOME_BLACKLIST.get(), biome)) {
                addStructure(biome, ModStructures.DESERT_HOUSE.get());
            }

            if (StructureGenConfig.GENERATE_SMALL_DUNGEONS.get() && checkBiome(StructureGenConfig.SMALL_DUNGEON_BIOME_CATEGORIES.get(), StructureGenConfig.DESERT_HOUSE_BIOME_BLACKLIST.get(), biome)) {
                addStructure(biome, ModStructures.SMALL_DUNGEON.get());
            }
        });
    }

    /**
     * Adds a structure to the given biome.
     * @param biome The biome to add a structure to.
     * @param structure The structure to add.
     */
    private static void addStructure(Biome biome, Structure<NoFeatureConfig> structure) {
        biome.func_235063_a_(structure.func_236391_a_(NoFeatureConfig.field_236559_b_));
    }

    private static boolean checkBiome(List<? extends Biome.Category> allowedBiomeCategories, List<? extends String> blacklistedBiomes, Biome biome) {
        boolean flag = allowedBiomeCategories.contains(biome.getCategory().toString());

        if (!blacklistedBiomes.isEmpty() && flag) {
            flag = !blacklistedBiomes.contains(Objects.requireNonNull(biome.getRegistryName()).toString());
        }

        return flag;
    }
}