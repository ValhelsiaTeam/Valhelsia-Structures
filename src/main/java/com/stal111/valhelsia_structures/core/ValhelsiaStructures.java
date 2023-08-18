package com.stal111.valhelsia_structures.core;

import com.mojang.datafixers.util.Either;
import com.mojang.logging.LogUtils;
import com.stal111.valhelsia_structures.client.ClientSetup;
import com.stal111.valhelsia_structures.common.CommonSetup;
import com.stal111.valhelsia_structures.common.item.ModCreativeModeTabs;
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProviderType;
import com.stal111.valhelsia_structures.common.world.structures.pools.SimpleStructurePools;
import com.stal111.valhelsia_structures.common.world.structures.pools.SpawnerDungeonPools;
import com.stal111.valhelsia_structures.common.world.structures.pools.ValhelsiaSinglePoolElement;
import com.stal111.valhelsia_structures.common.world.structures.processor.ModProcessors;
import com.stal111.valhelsia_structures.core.config.ConfigValidator;
import com.stal111.valhelsia_structures.core.config.ModConfig;
import com.stal111.valhelsia_structures.core.init.*;
import com.stal111.valhelsia_structures.core.init.world.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.valhelsia.valhelsia_core.core.ValhelsiaMod;
import net.valhelsia.valhelsia_core.core.registry.RegistryManager;
import net.valhelsia.valhelsia_core.core.registry.helper.TemplatePoolRegistryHelper;
import org.slf4j.Logger;

import java.util.List;
import java.util.function.Supplier;

/**
 * Valhelsia Structures Main <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.ValhelsiaStructures
 *
 * @author Valhelsia Team
 * @version 1.19 - 0.2.0
 * @since 2019-10-31
 */

@Mod(ValhelsiaStructures.MOD_ID)
public class ValhelsiaStructures extends ValhelsiaMod {

    public static final String MOD_ID = "valhelsia_structures";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final RegistryManager REGISTRY_MANAGER = RegistryManager.builder(MOD_ID)
            .addBlockHelper(ForgeRegistries.Keys.BLOCKS, ModCreativeModeTabs.MAIN, ModBlocks::new)
            .addHelper(ForgeRegistries.Keys.ITEMS, ModItems::new)
            .addHelper(ForgeRegistries.Keys.ENTITY_TYPES, ModEntities::new)
            .addHelper(Registry.STRUCTURE_TYPE_REGISTRY, ModStructureTypes::new)
            .addHelper(Registry.STRUCTURE_REGISTRY, ModStructures::new)
            .addHelper(Registry.STRUCTURE_SET_REGISTRY, ModStructureSets::new)
            .addHelper(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, ModBlockEntities::new)
            .addHelper(ForgeRegistries.Keys.RECIPE_SERIALIZERS, ModRecipes::new)
            .addHelper(Registry.STRUCTURE_POOL_ELEMENT_REGISTRY, ModStructurePoolElementTypes::new)
            .addHelper(Registry.TEMPLATE_POOL_REGISTRY, new TemplatePoolRegistryHelper(() -> List.of(ModProcessors.STONE_REPLACEMENT_PROCESSOR, ModProcessors.GRASS_BLOCK_REPLACEMENT_PROCESSOR), (resourceLocation, holder, projection, terrainAdjustment) -> projection1 -> new ValhelsiaSinglePoolElement(Either.left(resourceLocation), holder, projection1, terrainAdjustment), SpawnerDungeonPools::new, SimpleStructurePools::new))
            .addHelper(Registry.STRUCTURE_PROCESSOR_REGISTRY, ModStructureProcessors::new)
            .addHelper(Registry.SOUND_EVENT_REGISTRY, ModSoundEvents::new)
            .setConfigValidator(new ConfigValidator())
            .create();

    public static final Supplier<IForgeRegistry<StructureHeightProviderType<?>>> STRUCTURE_HEIGHT_PROVIDER_TYPES = ModStructureHeightProviderTypes.TYPES.makeRegistry(() ->
            new RegistryBuilder<StructureHeightProviderType<?>>().setMaxID(Integer.MAX_VALUE - 1).onAdd((owner, stage, id, key, obj, old) -> {}
            ).setDefaultKey(new ResourceLocation(ValhelsiaStructures.MOD_ID, "null")));

    private IForgeRegistry<StructureHeightProviderType<?>> structureHeightProviderTypes;

    public ValhelsiaStructures() {
        super(MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientSetup::new);

       ModStructureHeightProviderTypes.TYPES.register(eventBus);

        // Event Listeners
        FMLJavaModLoadingContext.get().getModEventBus().addListener(CommonSetup::setup);

        // Config
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.COMMON_SPEC);

        MinecraftForge.EVENT_BUS.register(this);

//        Logger rootLogger = LogUtils.getRootLogger();
//        if (rootLogger instanceof org.apache.logging.log4j.core.Logger) {
//            ((org.apache.logging.log4j.core.Logger) rootLogger).addFilter(new LogFilter());
//        } else {
//            LOGGER.error("Log Filter registration failed with unexpected class: {}", rootLogger.getClass());
//        }
    }

    @Override
    public RegistryManager getRegistryManager() {
        return REGISTRY_MANAGER;
    }
}
