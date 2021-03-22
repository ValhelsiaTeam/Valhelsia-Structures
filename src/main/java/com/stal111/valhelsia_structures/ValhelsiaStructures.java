package com.stal111.valhelsia_structures;

import com.google.common.collect.ImmutableMap;
import com.stal111.valhelsia_structures.config.Config;
import com.stal111.valhelsia_structures.config.ConfigValidator;
import com.stal111.valhelsia_structures.init.ModRecipes;
import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.init.ModTileEntities;
import com.stal111.valhelsia_structures.init.other.FireExtinguishRegistry;
import com.stal111.valhelsia_structures.init.other.FlintAndSteelRegistry;
import com.stal111.valhelsia_structures.setup.ClientSetup;
import com.stal111.valhelsia_structures.setup.CommonSetup;
import com.stal111.valhelsia_structures.utils.StructureType;
import com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure;
import com.stal111.valhelsia_structures.world.structures.RemovedStructure;
import com.stal111.valhelsia_structures.world.structures.pools.SmallDungeonPools;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.valhelsia.valhelsia_core.registry.EntityRegistryHelper;
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
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(CommonSetup::setup);

        // Config
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-client.toml").toString());
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-common.toml").toString());

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        FlintAndSteelRegistry.register();
        FireExtinguishRegistry.register();

        for (Map.Entry<StructureType, List<AbstractValhelsiaStructure>> entry : ModStructures.STRUCTURES_MAP.entrySet()) {
            entry.getValue().forEach(structure -> {
                if (!(structure instanceof RemovedStructure)) {
                    DimensionStructuresSettings.field_236191_b_ = // Default structures
                            ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                                    .putAll(DimensionStructuresSettings.field_236191_b_)
                                    .put(structure, new StructureSeparationSettings(structure.getSpacing(), structure.getSeparation(), structure.getSeedModifier()))
                                    .build();

                    DimensionSettings.field_242740_q.getStructures().field_236193_d_.put(structure, new StructureSeparationSettings(structure.getSpacing(), structure.getSeparation(), structure.getSeedModifier()));
                }
            });
        }

        SmallDungeonPools.load();
    }
}
