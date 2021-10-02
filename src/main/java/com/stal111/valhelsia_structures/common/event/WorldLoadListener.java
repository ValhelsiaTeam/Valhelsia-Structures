package com.stal111.valhelsia_structures.common.event;

import com.stal111.valhelsia_structures.common.world.structures.AbstractValhelsiaStructure;
import com.stal111.valhelsia_structures.core.config.ModConfig;
import com.stal111.valhelsia_structures.core.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.core.init.ModStructures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.valhelsia.valhelsia_core.common.world.IValhelsiaStructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (event.getWorld() instanceof ServerLevel level) {
            ChunkGenerator generator = level.getChunkSource().getGenerator();

            if (generator instanceof FlatLevelSource && level.dimension().equals(Level.OVERWORLD)) {
                return;
            }

            Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(generator.getSettings().structureConfig());

            ResourceLocation currDimension = level.dimension().location();
            for (String dimension : ModConfig.COMMON.blacklistedDimensions.get()) {
                if (dimension.equals(currDimension.toString()) || checkWildcard(dimension, currDimension.toString())) {
                    ModStructures.MOD_STRUCTURES.stream().map(IValhelsiaStructure::getStructure).collect(Collectors.toList()).forEach(tempMap.keySet()::remove);
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

            generator.getSettings().structureConfig = tempMap;
        }
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
}
