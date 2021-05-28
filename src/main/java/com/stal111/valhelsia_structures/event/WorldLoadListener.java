package com.stal111.valhelsia_structures.event;

import com.stal111.valhelsia_structures.init.ModStructures;
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
import java.util.Map;

/**
 * World Load Listener
 * Valhelsia Structures - com.stal111.valhelsia_structures.event.WorldLoadListener
 *
 * @author Valhelsia Team
 * @version 1.0.2
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

            for (IValhelsiaStructure structure : ModStructures.MOD_STRUCTURES) {
                tempMap.putIfAbsent(structure.getStructure(), DimensionStructuresSettings.field_236191_b_.get(structure.getStructure()));
            }

            serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
        }
    }
}
