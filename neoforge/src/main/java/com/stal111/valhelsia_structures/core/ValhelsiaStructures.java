package com.stal111.valhelsia_structures.core;

import com.mojang.logging.LogUtils;
import com.stal111.valhelsia_structures.client.ClientSetup;
import com.stal111.valhelsia_structures.common.CommonSetup;
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProviderType;
import com.stal111.valhelsia_structures.core.config.ModConfig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryManager;
import net.valhelsia.valhelsia_core.core.ModDefinition;
import org.slf4j.Logger;

/**
 * Valhelsia Structures Main <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.ValhelsiaStructures
 *
 * @author Valhelsia Team
 * @since 2019-10-31
 */

@Mod(ValhelsiaStructures.MOD_ID)
public class ValhelsiaStructures {

    public static final String MOD_ID = "valhelsia_structures";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final ResourceKey<Registry<StructureHeightProviderType<?>>> STRUCTURE_HEIGHT_PROVIDER_TYPES = ResourceKey.createRegistryKey(ValhelsiaStructures.location("structure_height_provider_types"));
    public static final Registry<StructureHeightProviderType<?>> STRUCTURE_HEIGHT_PROVIDER_TYPES_REGISTRY = new RegistryBuilder<>(STRUCTURE_HEIGHT_PROVIDER_TYPES).create();

    public static final RegistryManager REGISTRY_MANAGER = new RegistryManager(new ModRegistries(ValhelsiaStructures.MOD_ID));

    public ValhelsiaStructures(IEventBus eventBus, ModContainer modContainer) {
        ModDefinition.of(ValhelsiaStructures.MOD_ID)
                .withRegistryManager(ValhelsiaStructures.REGISTRY_MANAGER)
                .withEventHandler(new ModEventHandler(eventBus))
                .clientSetup(() -> helper -> new ClientSetup(helper, eventBus))
                .create();

        // Event Listeners
        eventBus.addListener(CommonSetup::setup);
        eventBus.addListener(NewRegistryEvent.class, event -> event.register(STRUCTURE_HEIGHT_PROVIDER_TYPES_REGISTRY));

        // Config
        modContainer.registerConfig(net.neoforged.fml.config.ModConfig.Type.COMMON, ModConfig.COMMON_SPEC);
        modContainer.registerConfig(net.neoforged.fml.config.ModConfig.Type.CLIENT, ModConfig.CLIENT_SPEC);
    }

    public static boolean isFurnitureInstalled() {
        return ModList.get().isLoaded("valhelsia_furniture");
    }

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(ValhelsiaStructures.MOD_ID, path);
    }
}
