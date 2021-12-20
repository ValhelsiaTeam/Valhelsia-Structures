package com.stal111.valhelsia_structures.utils;

import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Structure Utils <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.utils.StructureUtils
 *
 * @author Valhelsia Team
 * @version 1.17.1 - 0.1.0
 */
public class StructureUtils {

    /**
     * Checks if other Structures are in the area around the given Structure.
     *
     * @param structure  the structure to check the area around.
     * @param generator  the Chunk Generator to get the Separation Settings from.
     * @param seed       the Seed to use.
     * @param pos        The ChunkPos of the structure.
     * @param structures Structures to check for.
     * @return True if there are no structures from the list around the given structure.
     */
    public static boolean checkForOtherStructures(StructureFeature<?> structure, ChunkGenerator generator, long seed, ChunkPos pos, List<StructureFeature<?>> structures) {
        for (StructureFeature<?> structure1 : structures) {
            StructureFeatureConfiguration featureConfiguration = generator.getSettings().getConfig(structure1);

            if (featureConfiguration == null || structure == structure1) {
                continue;
            }

            for (int x = pos.x - 5; x <= pos.x + 5; x++) {
                for (int z = pos.z - 5; z <= pos.z + 5; z++) {
                    ChunkPos structurePos = structure1.getPotentialFeatureChunk(featureConfiguration, seed, x, z);

                    if (x == structurePos.x && z == structurePos.z) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static List<String> getAllBiomesForCategory(Biome.BiomeCategory... categories) {
        List<String> biomes = new ArrayList<>();

        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            for (Biome.BiomeCategory category : categories) {
                if (biome.getBiomeCategory() == category) {
                    biomes.add(Objects.requireNonNull(biome.getRegistryName()).toString());
                }
            }
        }

        return biomes;
    }
}
