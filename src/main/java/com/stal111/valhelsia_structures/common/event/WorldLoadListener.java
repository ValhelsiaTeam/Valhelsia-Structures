package com.stal111.valhelsia_structures.common.event;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.stal111.valhelsia_structures.common.world.structures.AbstractValhelsiaStructure;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.config.ModConfig;
import com.stal111.valhelsia_structures.core.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.core.init.ModStructures;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.valhelsia.valhelsia_core.common.world.IValhelsiaStructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * World Load Listener <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.event.WorldLoadListener
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-05-28
 */
@Mod.EventBusSubscriber
public class WorldLoadListener {

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        if (!(event.getWorld() instanceof ServerLevel level)) {
            return;
        }

        ChunkGenerator generator = level.getChunkSource().getGenerator();

        if (generator instanceof FlatLevelSource && level.dimension().equals(Level.OVERWORLD)) {
            return;
        }

        StructureSettings structureSettings = generator.getSettings();

        HashMap<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> structureToMultiMap = new HashMap<>();

        biomeLoop: for(Map.Entry<ResourceKey<Biome>, Biome> biomeEntry : level.registryAccess().ownedRegistryOrThrow(Registry.BIOME_REGISTRY).entrySet()) {
            Biome.BiomeCategory category = biomeEntry.getValue().getBiomeCategory();
            ResourceLocation name = biomeEntry.getValue().getRegistryName();

            if (name == null) {
                continue;
            }

            // Check Blacklist
            for (String biome : ModConfig.COMMON.blacklistedBiomes.get()) {
                if (biome.equals(name.toString()) || checkWildcard(biome, name.toString())) {
                    continue biomeLoop;
                }
            }

            // Add Structures
            for (IValhelsiaStructure iStructure : ModStructures.MOD_STRUCTURES) {
                AbstractValhelsiaStructure structure = (AbstractValhelsiaStructure) iStructure.getStructure();
                StructureConfigEntry configEntry = structure.getStructureConfigEntry();

                if (configEntry.generate.get()) {
                    if (checkBiome(configEntry.configuredBiomeCategories.get(), configEntry.configuredBlacklistedBiomes.get(), name, category)) {
                        associateBiomeToConfiguredStructure(structureToMultiMap, structure.getStructureFeature(), biomeEntry.getKey());
                    }
                }
            }
        }

        ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> tempStructureToMultiMap = ImmutableMap.builder();
        structureSettings.configuredStructures.entrySet().stream().filter(entry -> !structureToMultiMap.containsKey(entry.getKey())).forEach(tempStructureToMultiMap::put);

        structureToMultiMap.forEach((key, value) -> tempStructureToMultiMap.put(key, ImmutableMultimap.copyOf(value)));

        structureSettings.configuredStructures = tempStructureToMultiMap.build();

        Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureSettings.structureConfig());
//        for (IValhelsiaStructure structure : ModStructures.MOD_STRUCTURES) {
//            tempMap.putIfAbsent(structure.getStructure(), StructureSettings.DEFAULTS.get(structure.getStructure()));
//        }

        ResourceLocation currDimension = level.dimension().location();
        for (String dimension : ModConfig.COMMON.blacklistedDimensions.get()) {
            if (dimension.equals(currDimension.toString()) || checkWildcard(dimension, currDimension.toString())) {
                ModStructures.MOD_STRUCTURES.stream().map(IValhelsiaStructure::getStructure).toList().forEach(tempMap.keySet()::remove);
                generator.getSettings().structureConfig = tempMap;
                    return;
                }
            }

            for (IValhelsiaStructure iStructure : ModStructures.MOD_STRUCTURES) {
                AbstractValhelsiaStructure structure = (AbstractValhelsiaStructure) iStructure.getStructure();
                StructureConfigEntry configEntry = structure.getStructureConfigEntry();

                if (checkDimension(configEntry.configuredBlacklistedDimensions.get(), currDimension)) {
                    tempMap.putIfAbsent(structure, StructureSettings.DEFAULTS.get(structure));
                } else {
                    tempMap.keySet().remove(structure);
                }
            }

            structureSettings.structureConfig = tempMap;
    }

    private static boolean checkWildcard(String blacklistedDimension, String dimension) {
        if (blacklistedDimension.startsWith("*") && blacklistedDimension.endsWith("*")) {
            return dimension.contains(blacklistedDimension.substring(1, blacklistedDimension.length() - 1));
        } else if (blacklistedDimension.startsWith("*")) {
            return dimension.endsWith(blacklistedDimension.substring(1));
        } else if (blacklistedDimension.endsWith("*")) {
            return dimension.startsWith(blacklistedDimension.substring(0, blacklistedDimension.length() - 1));
        }
        return false;
    }

    private static boolean checkDimension(List<? extends String> blacklistedDimensions, ResourceLocation name) {
        boolean flag = true;

        if (!blacklistedDimensions.isEmpty()) {
            flag = !blacklistedDimensions.contains(name.toString());

            if (flag) {
                for (String dimension : blacklistedDimensions) {
                    if (checkWildcard(dimension, name.toString())) {
                        flag = false;
                        break;
                    }
                }
            }
        }

        return flag;
    }

    private static boolean checkBiome(List<? extends String> allowedBiomeCategories, List<? extends String> blacklistedBiomes, ResourceLocation name, Biome.BiomeCategory category) {
        boolean flag = allowedBiomeCategories.contains(category.getName());

        if (!blacklistedBiomes.isEmpty() && flag) {
            flag = !blacklistedBiomes.contains(name.toString());

            if (flag) {
                for (String biome : blacklistedBiomes) {
                    if (checkWildcard(biome, name.toString())) {
                        flag = false;
                        break;
                    }
                }
            }
        }

        return flag;
    }

    private static void associateBiomeToConfiguredStructure(Map<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> structureToMultiMap, ConfiguredStructureFeature<?, ?> configuredStructureFeature, ResourceKey<Biome> biomeRegistryKey) {
        structureToMultiMap.putIfAbsent(configuredStructureFeature.feature, HashMultimap.create());

        System.out.println(configuredStructureFeature.feature.getRegistryName());

        HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> configuredStructureToBiomeMultiMap = structureToMultiMap.get(configuredStructureFeature.feature);

        if (configuredStructureToBiomeMultiMap.containsValue(biomeRegistryKey)) {
            ValhelsiaStructures.LOGGER.error("""
                    Detected 2 ConfiguredStructureFeatures that share the same base StructureFeature trying to be added to same biome. One will be prevented from spawning.
                    This issue happens with vanilla too and is why a Snowy Village and Plains Village cannot spawn in the same biome because they both use the Village base structure.
                    The two conflicting ConfiguredStructures are: {}, {}
                    The biome that is attempting to be shared: {}
                """,
                    BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureFeature),
                    BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureToBiomeMultiMap.entries().stream().filter(e -> e.getValue() == biomeRegistryKey).findFirst().get().getKey()),
                    biomeRegistryKey
            );
        } else{
            configuredStructureToBiomeMultiMap.put(configuredStructureFeature, biomeRegistryKey);
        }
    }
}
