package com.stal111.valhelsia_structures.event;

import com.stal111.valhelsia_structures.core.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.core.init.ModStructures;
import com.stal111.valhelsia_structures.common.world.structures.AbstractValhelsiaStructure;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.valhelsia.valhelsia_core.world.IValhelsiaStructure;

import java.util.List;

/**
 * Biome Loading Listener
 * Valhelsia Structures - com.stal111.valhelsia_structures.event.BiomeLoadingListener
 *
 * @author Valhelsia Team
 * @version 0.1.3
 * @since 2020-09-17
 */
@Mod.EventBusSubscriber
public class BiomeLoadingListener {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        ResourceLocation name = event.getName();
        Biome.BiomeCategory category = event.getCategory();

        if (name != null) {

            // Check Blacklist
            for (String biome : StructureGenConfig.BLACKLISTED_BIOMES.get()) {
                if (biome.equals(name.toString()) || checkWildcard(biome, name.toString())) {
                    return;
                }
            }

            // Add Structures
            for (IValhelsiaStructure iStructure : ModStructures.MOD_STRUCTURES) {
                AbstractValhelsiaStructure structure = (AbstractValhelsiaStructure) iStructure.getStructure();
                StructureConfigEntry configEntry = structure.getStructureConfigEntry();

                if (configEntry.generate.get()) {
                    if (checkBiome(configEntry.configuredBiomeCategories.get(), configEntry.configuredBlacklistedBiomes.get(), name, category)) {
                        event.getGeneration().withStructure(structure.getStructureFeature());
                    }
                }
            }
        }
    }

    private static boolean checkWildcard(String blacklistedBiome, String biome) {
        if (blacklistedBiome.startsWith("*") && blacklistedBiome.endsWith("*")) {
            return biome.contains(blacklistedBiome.substring(1, blacklistedBiome.length() - 1));
        } else if (blacklistedBiome.startsWith("*")) {
            return biome.endsWith(blacklistedBiome.substring(1));
        } else if (blacklistedBiome.endsWith("*")) {
            return biome.startsWith(blacklistedBiome.substring(0, blacklistedBiome.length() - 1));
        }
        return false;
    }

    private static boolean checkBiome(List<? extends String> allowedBiomeCategories, List<? extends String> blacklistedBiomes, ResourceLocation name, Biome.Category category) {
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
}
