package com.stal111.valhelsia_structures;

import com.google.common.collect.ImmutableMap;
import com.stal111.valhelsia_structures.config.Config;
import com.stal111.valhelsia_structures.init.ModRecipes;
import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.init.ModTileEntities;
import com.stal111.valhelsia_structures.init.other.FireExtinguishRegistry;
import com.stal111.valhelsia_structures.init.other.FlintAndSteelRegistry;
import com.stal111.valhelsia_structures.proxy.ClientProxy;
import com.stal111.valhelsia_structures.proxy.IProxy;
import com.stal111.valhelsia_structures.proxy.ServerProxy;
import com.stal111.valhelsia_structures.utils.StructureType;
import com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure;
import com.stal111.valhelsia_structures.world.structures.pieces.SmallDungeonPools;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.valhelsia.valhelsia_core.registry.RegistryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Valhelsia Structures Main
 * Valhelsia Structures - com.stal111.valhelsia_structures.ValhelsiaStructures
 *
 * @author Valhelsia Team
 * @version 16.0.4
 * @since 2019-10-31
 */

@Mod(ValhelsiaStructures.MOD_ID)
public class ValhelsiaStructures {
    public static final String MOD_ID = "valhelsia_structures";

    public static final Logger LOGGER = LogManager.getLogger();

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static final RegistryManager REGISTRY_MANAGER = new RegistryManager.Builder(MOD_ID).addDefaultHelpers().build();

    public ValhelsiaStructures() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //REGISTRY_MANAGER = new RegistryManager.Builder(MOD_ID).addDefaultHelpers().build();

        // Deferred Registration
        ModRecipes.SERIALIZERS.register(eventBus);
        ModTileEntities.TILE_ENTITIES.register(eventBus);
        ModStructures.STRUCTURES.register(eventBus);

        REGISTRY_MANAGER.getBlockHelper().setDefaultGroup(ValhelsiaStructuresItemGroups.MAIN);

        REGISTRY_MANAGER.register(eventBus);

        // Event Listeners
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Config
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-client.toml").toString());
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-server.toml").toString());

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // This deprecation can be safely ignored - Forge hasn't actually added the proposed replacement for it yet.
        DeferredWorkQueue.runLater(() -> {
            proxy.init();
        });

        FlintAndSteelRegistry.register();
        FireExtinguishRegistry.register();

        for (Map.Entry<StructureType, List<AbstractValhelsiaStructure>> entry : ModStructures.STRUCTURES_MAP.entrySet()) {
            entry.getValue().forEach(structure -> {
                DimensionStructuresSettings.field_236191_b_ = // Default structures
                        ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                                .putAll(DimensionStructuresSettings.field_236191_b_)
                                .put(structure, new StructureSeparationSettings(structure.getDistance(), structure.getSeparation(), structure.getSeedModifier()))
                                .build();

                DimensionSettings.field_242740_q.getStructures().field_236193_d_.put(structure, new StructureSeparationSettings(structure.getDistance(), structure.getSeparation(), structure.getSeedModifier()));
            });
        }

        SmallDungeonPools.load();
    }
}
