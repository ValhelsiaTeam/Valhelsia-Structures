package com.stal111.valhelsia_structures;

import com.stal111.valhelsia_structures.config.Config;
import com.stal111.valhelsia_structures.config.ConfigValidator;
import com.stal111.valhelsia_structures.init.ModRecipes;
import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.init.ModTileEntities;
import com.stal111.valhelsia_structures.setup.ClientSetup;
import com.stal111.valhelsia_structures.setup.CommonSetup;
import com.stal111.valhelsia_structures.utils.LogFilter;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.valhelsia.valhelsia_core.registry.EntityRegistryHelper;
import net.valhelsia.valhelsia_core.registry.RegistryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Valhelsia Structures Main
 * Valhelsia Structures - com.stal111.valhelsia_structures.ValhelsiaStructures
 *
 * @author Valhelsia Team
 * @version 1.0.2
 * @since 2019-10-31
 */

@Mod(ValhelsiaStructures.MOD_ID)
public class ValhelsiaStructures {
    public static final String MOD_ID = "valhelsia_structures";

    public static final Logger LOGGER = LogManager.getLogger();

    public static final RegistryManager REGISTRY_MANAGER = new RegistryManager.Builder(MOD_ID).addDefaultHelpers().addHelpers(new EntityRegistryHelper()).build();

    public ValhelsiaStructures() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientSetup::new);

        // Deferred Registration
        ModRecipes.SERIALIZERS.register(eventBus);
        ModTileEntities.TILE_ENTITIES.register(eventBus);
        ModStructures.STRUCTURES.register(eventBus);

        REGISTRY_MANAGER.getBlockHelper().setDefaultGroup(ValhelsiaStructuresItemGroups.MAIN);
        REGISTRY_MANAGER.registerConfigValidator(new ConfigValidator());

        REGISTRY_MANAGER.register(eventBus);

        // Event Listeners
        FMLJavaModLoadingContext.get().getModEventBus().addListener(CommonSetup::setup);

        // Config
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-client.toml").toString());
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-common.toml").toString());

        MinecraftForge.EVENT_BUS.register(this);

        Logger rootLogger = LogManager.getRootLogger();
        if (rootLogger instanceof org.apache.logging.log4j.core.Logger) {
            ((org.apache.logging.log4j.core.Logger) rootLogger).addFilter(new LogFilter());
        } else {
            LOGGER.error("Log Filter registration failed with unexpected class: {}", rootLogger.getClass());
        }
    }
}
