package com.stal111.valhelsia_structures.event;

import com.stal111.valhelsia_structures.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.valhelsia.valhelsia_core.world.IValhelsiaStructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * World Load Listener <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.event.WorldLoadListener
 *
 * @author Valhelsia Team
 * @version 0.1.6
 * @since 2021-05-28
 */
@Mod.EventBusSubscriber
public class WorldLoadListener {

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            if (serverWorld.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator && serverWorld.getDimensionKey().equals(World.OVERWORLD)) {
                return;
            }

            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());

            ResourceLocation currDimension = serverWorld.getDimensionKey().getLocation();
            for (String dimension : StructureGenConfig.BLACKLISTED_DIMENSIONS.get()) {
                if (dimension.equals(currDimension.toString()) || checkWildcard(dimension, currDimension.toString())) {
                    ModStructures.MOD_STRUCTURES.stream().map(IValhelsiaStructure::getStructure).collect(Collectors.toList()).forEach(tempMap.keySet()::remove);
                    serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
                    return;
                }
            }

            for (IValhelsiaStructure iStructure : ModStructures.MOD_STRUCTURES) {
                AbstractValhelsiaStructure structure = (AbstractValhelsiaStructure) iStructure.getStructure();
                StructureConfigEntry configEntry = structure.getStructureConfigEntry();

                if (checkDimension(configEntry.configuredBlacklistedDimensions.get(), currDimension)) {
                    tempMap.putIfAbsent(structure, DimensionStructuresSettings.field_236191_b_.get(structure));
                } else {
                    tempMap.keySet().remove(structure);
                }
            }

            serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
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
