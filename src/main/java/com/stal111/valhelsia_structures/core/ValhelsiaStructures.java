package com.stal111.valhelsia_structures.core;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.mojang.logging.LogUtils;
import com.stal111.valhelsia_structures.client.ClientSetup;
import com.stal111.valhelsia_structures.common.CommonSetup;
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProviderType;
import com.stal111.valhelsia_structures.core.config.ModConfig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryManager;
import net.valhelsia.valhelsia_core.core.ModDefinition;
import org.slf4j.Logger;

import java.io.File;
import java.util.Locale;

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

    public static final ResourceKey<Registry<StructureHeightProviderType<?>>> STRUCTURE_HEIGHT_PROVIDER_TYPES = ResourceKey.createRegistryKey(new ResourceLocation("structure_height_provider_types"));
    public static final Registry<StructureHeightProviderType<?>> STRUCTURE_HEIGHT_PROVIDER_TYPES_REGISTRY = new RegistryBuilder<>(STRUCTURE_HEIGHT_PROVIDER_TYPES).create();

    public static final RegistryManager REGISTRY_MANAGER = new RegistryManager(new ModRegistries(ValhelsiaStructures.MOD_ID));

    public ValhelsiaStructures() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModDefinition.of(ValhelsiaStructures.MOD_ID)
                .withRegistryManager(ValhelsiaStructures.REGISTRY_MANAGER)
                .withEventHandler(new ModEventHandler(eventBus))
                .clientSetup(() -> ClientSetup::new)
                .create();

        // Event Listeners
        FMLJavaModLoadingContext.get().getModEventBus().addListener(CommonSetup::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(NewRegistryEvent.class, event -> event.register(STRUCTURE_HEIGHT_PROVIDER_TYPES_REGISTRY));

        // Config
        this.registerConfig(net.neoforged.fml.config.ModConfig.Type.COMMON, ModConfig.COMMON_SPEC);
        this.registerConfig(net.neoforged.fml.config.ModConfig.Type.CLIENT, ModConfig.CLIENT_SPEC);
    }

    public static boolean isFurnitureInstalled() {
        return ModList.get().isLoaded("valhelsia_furniture");
    }

    private void registerConfig(net.neoforged.fml.config.ModConfig.Type type, ModConfigSpec configSpec) {
        ModLoadingContext.get().registerConfig(type, configSpec);

        this.loadConfig(configSpec, FMLPaths.CONFIGDIR.get().resolve(ValhelsiaStructures.MOD_ID + "-" + type.name().toLowerCase(Locale.ROOT) + ".toml").toString());
    }

    private void loadConfig(ModConfigSpec configSpec, String path) {
        CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).preserveInsertionOrder().sync().autosave().writingMode(WritingMode.REPLACE).build();

        file.load();
        configSpec.setConfig(file);
    }
}
