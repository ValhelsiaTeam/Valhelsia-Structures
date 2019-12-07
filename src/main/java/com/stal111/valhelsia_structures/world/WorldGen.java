package com.stal111.valhelsia_structures.world;

import com.stal111.valhelsia_structures.init.ModStructures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * World Generation
 * ValhelsiaStructure - com.stal111.valhelsia_structure.world.WorldGen
 *
 * @author Valhelsia Team
 * @version 0.1
 * @since 2019-10-31
 */
public class WorldGen {

    /**
     * Setup World Generation
     */
    public static void setupWorldGen() {
        // Add Structures
        for (Biome biome : ForgeRegistries.BIOMES) {
            // Blacklisted biomes.
            if (biome == Biomes.RIVER) {
                continue;
            }

            // Use categories to allow compatibility with biome mods such as Biomes O' Plenty.
            if (biome.getCategory() == Biome.Category.PLAINS || biome.getCategory() == Biome.Category.FOREST) {
                if (biome.getTempCategory() == Biome.TempCategory.MEDIUM && biome.getPrecipitation() == Biome.RainType.RAIN) {
                    addStructure(biome, ModStructures.SMALL_CASTLE);
                }
            }
        }
    }

    /**
     * Add a structure to the given biome.
     * @param biome The biome to add a structure to.
     * @param structure The structure to add.
     */
    @SuppressWarnings("SameParameterValue") // temp suppression: won't be needed when more structures exist.
    private static void addStructure(Biome biome, Structure<NoFeatureConfig> structure) {
        biome.addStructure(structure, IFeatureConfig.NO_FEATURE_CONFIG);
        biome.addFeature(
                GenerationStage.Decoration.SURFACE_STRUCTURES,
                Biome.createDecoratedFeature(structure,
                        IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.NOPE,
                        IPlacementConfig.NO_PLACEMENT_CONFIG));
    }
}
