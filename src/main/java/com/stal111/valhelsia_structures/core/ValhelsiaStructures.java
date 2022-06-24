package com.stal111.valhelsia_structures.core;

import com.stal111.valhelsia_structures.client.ClientSetup;
import com.stal111.valhelsia_structures.common.CommonSetup;
import com.stal111.valhelsia_structures.common.item.ModCreativeModeTabs;
import com.stal111.valhelsia_structures.core.config.ConfigValidator;
import com.stal111.valhelsia_structures.core.config.ModConfig;
import com.stal111.valhelsia_structures.core.init.*;
import com.stal111.valhelsia_structures.core.init.world.ModStructureSets;
import com.stal111.valhelsia_structures.core.init.world.ModStructureTypes;
import com.stal111.valhelsia_structures.core.init.world.ModStructures;
import com.stal111.valhelsia_structures.utils.LogFilter;
import net.minecraft.core.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.core.registry.RegistryHelper;
import net.valhelsia.valhelsia_core.core.registry.RegistryManager;
import net.valhelsia.valhelsia_core.core.registry.block.BlockRegistryHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Valhelsia Structures Main <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.ValhelsiaStructures
 *
 * @author Valhelsia Team
 * @version 1.19 - 0.2.0
 * @since 2019-10-31
 */

@Mod(ValhelsiaStructures.MOD_ID)
public class ValhelsiaStructures {
    public static final String MOD_ID = "valhelsia_structures";

    public static final Logger LOGGER = LogManager.getLogger();

    public static final RegistryManager REGISTRY_MANAGER = RegistryManager.builder(MOD_ID)
            .addHelper(ForgeRegistries.Keys.BLOCKS, new BlockRegistryHelper(ModCreativeModeTabs.MAIN, ModBlocks::new))
            .addHelper(ForgeRegistries.Keys.ITEMS, new RegistryHelper<>(ModItems::new))
            .addHelper(ForgeRegistries.Keys.ENTITY_TYPES, new RegistryHelper<>(ModEntities::new))
            .addHelper(Registry.STRUCTURE_TYPE_REGISTRY, new RegistryHelper<>(ModStructureTypes::new))
            .addHelper(Registry.STRUCTURE_REGISTRY, new RegistryHelper<>(ModStructures::new))
            .addHelper(Registry.STRUCTURE_SET_REGISTRY, new RegistryHelper<>(ModStructureSets::new))
            .setConfigValidator(new ConfigValidator())
            .create();

    public ValhelsiaStructures() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientSetup::new);

        // Deferred Registration
        ModRecipes.SERIALIZERS.register(eventBus);
        ModBlockEntities.TILE_ENTITIES.register(eventBus);

        REGISTRY_MANAGER.register(eventBus);

        // Event Listeners
        FMLJavaModLoadingContext.get().getModEventBus().addListener(CommonSetup::setup);

        // Config
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.COMMON_SPEC);

        MinecraftForge.EVENT_BUS.register(this);

        Logger rootLogger = LogManager.getRootLogger();
        if (rootLogger instanceof org.apache.logging.log4j.core.Logger) {
            ((org.apache.logging.log4j.core.Logger) rootLogger).addFilter(new LogFilter());
        } else {
            LOGGER.error("Log Filter registration failed with unexpected class: {}", rootLogger.getClass());
        }
    }
}
