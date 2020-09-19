package com.stal111.valhelsia_structures.world;

import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

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
        for (Biome biome : WorldGenRegistries.field_243657_i) {
            //if (StructureGenConfig.BLACKLISTED_BIOMES.get().contains(Objects.requireNonNull(biome.getKey().getRegistryName()).toString())) {
              //  return;
            //}

           // if (StructureGenConfig.GENERATE_CASTLES.get() && checkBiome(StructureGenConfig.CASTLE_BIOME_CATEGORIES.get(), StructureGenConfig.CASTLE_BIOME_BLACKLIST.get(), biome.getValue())) {
               // addStructure(biome.getValue(), ModStructures.CASTLE.get());
                //addStructureToBiome(biome, ModStructureFeatures.CASTLE);
                //((AccessorBiomeGenerationSettings) biome.getValue().func_242440_e()).valhelsia_getStructureFeatures().add(() -> ModStructureFeatures.CASTLE);
           //}
        }
//        Iterator<Biome> biomes = ForgeRegistries.BIOMES.iterator();
//        biomes.forEachRemaining((biome) -> {
//            // Check Blacklist
//            if (StructureGenConfig.BLACKLISTED_BIOMES.get().contains(Objects.requireNonNull(biome.getRegistryName()).toString())) {
//                return;
//            }
//

//        });
    }

//    public static void addStructureToBiome(Biome biome, StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> structure) {
//        List<Supplier<StructureFeature<?, ?>>> structures = new ArrayList<>(biome.func_242440_e().func_242487_a());
//
//        structures.add(() -> structure);
//
//        ObfuscationReflectionHelper.setPrivateValue(BiomeGenerationSettings.class, biome.func_242440_e(), structures, "field_242485_g");
//    }

    /**
     * Adds a structure to the given biome.
     * @param biome The biome to add a structure to.
     * @param structure The structure to add.
     */
    private static void addStructure(Biome biome, Structure<VillageConfig> structure) {
        //biome.func_235063_a_(structure.func_236391_a_(NoFeatureConfig.field_236559_b_));
    }

    private static boolean checkBiome(List<? extends Biome.Category> allowedBiomeCategories, List<? extends String> blacklistedBiomes, Biome biome) {
        boolean flag = allowedBiomeCategories.contains(biome.getCategory().toString()) || allowedBiomeCategories.contains(biome.getCategory());

        if (!blacklistedBiomes.isEmpty() && flag) {
            flag = !blacklistedBiomes.contains(Objects.requireNonNull(biome.getRegistryName()).toString());
        }

        return flag;
    }
}