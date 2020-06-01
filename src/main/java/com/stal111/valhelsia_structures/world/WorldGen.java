package com.stal111.valhelsia_structures.world;

import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * World Generation
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.WorldGen
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2019-10-31
 */
public class WorldGen {

    public static List<Structure<NoFeatureConfig>> structures = new ArrayList<>();

    /**
     * Setup World Generation
     */
    public static void setupWorldGen() {

        // This is apparently deprecated but the replacement isn't in yet?
        // Regardless, this makes the structure additions thread-safe.
        //noinspection deprecation
        DeferredWorkQueue.runLater(() -> {
            Iterator<Biome> biomes = ForgeRegistries.BIOMES.iterator();
            biomes.forEachRemaining((biome) -> {
                // Check Blacklist
                if (!(biome.getCategory() == Biome.Category.RIVER || biome.getCategory() == Biome.Category.OCEAN)) {
                    // Plains / Forest Structures
                    if (biome.getCategory() == Biome.Category.PLAINS || biome.getCategory() == Biome.Category.FOREST) {
                        if (biome.getTempCategory() == Biome.TempCategory.MEDIUM && biome.getPrecipitation() == Biome.RainType.RAIN) {

                            if (StructureGenConfig.GENERATE_CASTLES.get()) {
                                addSurfaceStructure(biome, ModStructures.CASTLE.get());
                                structures.add(ModStructures.CASTLE.get());
                            }

                            if (StructureGenConfig.GENERATE_CASTLE_RUINS.get()) {
                                addSurfaceStructure(biome, ModStructures.CASTLE_RUIN.get());
                                structures.add(ModStructures.CASTLE_RUIN.get());
                            }

                            if (StructureGenConfig.GENERATE_FORGES.get()) {
                                addSurfaceStructure(biome, ModStructures.FORGE.get());
                                structures.add(ModStructures.FORGE.get());
                            }

                            if (StructureGenConfig.GENERATE_PLAYER_HOUSES.get()) {
                                addSurfaceStructure(biome, ModStructures.PLAYER_HOUSE.get());
                                structures.add(ModStructures.PLAYER_HOUSE.get());
                            }

                            if (StructureGenConfig.GENERATE_TOWER_RUINS.get()) {
                                addSurfaceStructure(biome, ModStructures.TOWER_RUIN.get());
                                structures.add(ModStructures.TOWER_RUIN.get());
                            }
                        }
                    }

                    // Desert Structures
                    if (biome.getCategory() == Biome.Category.DESERT && biome.getPrecipitation() == Biome.RainType.NONE) {
                        if (StructureGenConfig.GENERATE_DESERT_HOUSES.get()) {
                            addSurfaceStructure(biome, ModStructures.DESERT_HOUSE.get());
                            structures.add(ModStructures.DESERT_HOUSE.get());
                        }
                    }

                    // Dungeons
                    if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
                        if (StructureGenConfig.GENERATE_SMALL_DUNGEONS.get()) {
                            addUndergroundStructure(biome, ModStructures.SMALL_DUNGEON.get());
                            structures.add(ModStructures.SMALL_DUNGEON.get());
                        }
                    }
                }
            });
        });
    }

    /**
     * Add a surface structure to the given biome.
     * @param biome The biome to add a structure to.
     * @param structure The structure to add.
     */
    private static void addSurfaceStructure(Biome biome, Structure<NoFeatureConfig> structure) {
        biome.addStructure(structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
    }

    /**
     * Add an underground structure to the given biome.
     * @param biome The biome to add a structure to.
     * @param structure The structure to add.
     */
    private static void addUndergroundStructure(Biome biome, Structure<NoFeatureConfig> structure) {
        biome.addStructure(structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.DUNGEONS.configure(new ChanceConfig(8))));
    }
}
