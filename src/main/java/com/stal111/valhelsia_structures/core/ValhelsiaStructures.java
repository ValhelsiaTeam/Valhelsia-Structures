package com.stal111.valhelsia_structures.core;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.mojang.logging.LogUtils;
import com.stal111.valhelsia_structures.client.ClientSetup;
import com.stal111.valhelsia_structures.common.CommonSetup;
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProviderType;
import com.stal111.valhelsia_structures.core.config.ModConfig;
import com.stal111.valhelsia_structures.core.init.world.ModStructureHeightProviderTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryManager;
import net.valhelsia.valhelsia_core.core.ModDefinition;
import org.slf4j.Logger;

import java.io.File;
import java.util.Locale;
import java.util.function.Supplier;

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

    public static final RegistryManager REGISTRY_MANAGER = new RegistryManager(new ModRegistries(ValhelsiaStructures.MOD_ID));

    public static final Supplier<IForgeRegistry<StructureHeightProviderType<?>>> STRUCTURE_HEIGHT_PROVIDER_TYPES = ModStructureHeightProviderTypes.TYPES.makeRegistry(() ->
            new RegistryBuilder<StructureHeightProviderType<?>>().setMaxID(Integer.MAX_VALUE - 1).onAdd((owner, stage, id, key, obj, old) -> {}
            ).setDefaultKey(new ResourceLocation(ValhelsiaStructures.MOD_ID, "null")));

    private IForgeRegistry<StructureHeightProviderType<?>> structureHeightProviderTypes;

    public ValhelsiaStructures() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModDefinition.of(ValhelsiaStructures.MOD_ID)
                .withRegistryManager(ValhelsiaStructures.REGISTRY_MANAGER)
                .withEventHandler(new ModEventHandler(eventBus))
                .clientSetup(() -> ClientSetup::new)
                .create();

        ModStructureHeightProviderTypes.TYPES.register(eventBus);

        // Event Listeners
        FMLJavaModLoadingContext.get().getModEventBus().addListener(CommonSetup::setup);

        // Config
        this.registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.COMMON_SPEC);
        this.registerConfig(net.minecraftforge.fml.config.ModConfig.Type.CLIENT, ModConfig.CLIENT_SPEC);
    }

    public static boolean isFurnitureInstalled() {
        return ModList.get().isLoaded("valhelsia_furniture");
    }

    private void registerConfig(net.minecraftforge.fml.config.ModConfig.Type type, ForgeConfigSpec configSpec) {
        ModLoadingContext.get().registerConfig(type, configSpec);

        this.loadConfig(configSpec, FMLPaths.CONFIGDIR.get().resolve(ValhelsiaStructures.MOD_ID + "-" + type.name().toLowerCase(Locale.ROOT) + ".toml").toString());
    }

    private void loadConfig(ForgeConfigSpec configSpec, String path) {
        CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).preserveInsertionOrder().sync().autosave().writingMode(WritingMode.REPLACE).build();

        file.load();
        configSpec.setConfig(file);
    }
}
