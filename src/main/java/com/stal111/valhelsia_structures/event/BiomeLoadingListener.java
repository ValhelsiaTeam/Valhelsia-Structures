package com.stal111.valhelsia_structures.event;

import com.stal111.valhelsia_structures.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.utils.StructureType;
import com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure;
import com.stal111.valhelsia_structures.world.structures.RemovedStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;

/**
 * Biome Loading Listener
 * Valhelsia Structures - com.stal111.valhelsia_structures.event.BiomeLoadingListener
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2020-09-17
 */

@Mod.EventBusSubscriber
public class BiomeLoadingListener {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        ResourceLocation name = event.getName();
        Biome.Category category = event.getCategory();

        if (name != null) {
            // Check Blacklist
            if (StructureGenConfig.BLACKLISTED_BIOMES.get().contains(name.toString())) {
                return;
            }

            // Add Structures
            for (Map.Entry<StructureType, List<AbstractValhelsiaStructure>> entry : ModStructures.STRUCTURES_MAP.entrySet()) {
                entry.getValue().forEach(structure -> {
                    if (!(structure instanceof RemovedStructure)) {
                        StructureConfigEntry configEntry = structure.getStructureConfigEntry();
                        if (configEntry.generate.get()) {
                            if (checkBiome(configEntry.configuredBiomeCategories.get(), configEntry.configuredBlacklistedBiomes.get(), name, category)) {
                                event.getGeneration().withStructure(structure.getStructureFeature());
                            }
                        }
                    }
                });
            }
        }
    }

    private static boolean checkBiome(List<? extends String> allowedBiomeCategories, List<? extends String> blacklistedBiomes, ResourceLocation name, Biome.Category category) {
        boolean flag = allowedBiomeCategories.contains(category.getName());

        if (!blacklistedBiomes.isEmpty() && flag) {
            flag = !blacklistedBiomes.contains(name.toString());
        }

        return flag;
    }
}
