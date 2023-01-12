package com.stal111.valhelsia_structures.core;

import com.mojang.logging.LogUtils;
import com.stal111.valhelsia_structures.client.ClientSetup;
import com.stal111.valhelsia_structures.common.CommonSetup;
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProviderType;
import com.stal111.valhelsia_structures.core.config.ConfigValidator;
import com.stal111.valhelsia_structures.core.config.ModConfig;
import com.stal111.valhelsia_structures.core.init.world.ModStructureHeightProviderTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.valhelsia.valhelsia_core.core.ValhelsiaMod;
import net.valhelsia.valhelsia_core.core.registry.RegistryManager;
import org.slf4j.Logger;

import java.util.function.Supplier;

/**
 * Valhelsia Structures Main <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.ValhelsiaStructures
 *
 * @author Valhelsia Team
 * @since 2019-10-31
 */

@Mod(ValhelsiaStructures.MOD_ID)
public class ValhelsiaStructures extends ValhelsiaMod {

    public static final String MOD_ID = "valhelsia_structures";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final RegistryManager REGISTRY_MANAGER = new RegistryManager(new ModRegistries(ValhelsiaStructures.MOD_ID), new ConfigValidator());

    public static final Supplier<IForgeRegistry<StructureHeightProviderType<?>>> STRUCTURE_HEIGHT_PROVIDER_TYPES = ModStructureHeightProviderTypes.TYPES.makeRegistry(() ->
            new RegistryBuilder<StructureHeightProviderType<?>>().setMaxID(Integer.MAX_VALUE - 1).onAdd((owner, stage, id, key, obj, old) -> {}
            ).setDefaultKey(new ResourceLocation(ValhelsiaStructures.MOD_ID, "null")));

    private IForgeRegistry<StructureHeightProviderType<?>> structureHeightProviderTypes;

    public ValhelsiaStructures() {
        super(MOD_ID, FMLJavaModLoadingContext.get().getModEventBus(), ValhelsiaStructures.REGISTRY_MANAGER);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientSetup::new);

       ModStructureHeightProviderTypes.TYPES.register(eventBus);

        // Event Listeners
        FMLJavaModLoadingContext.get().getModEventBus().addListener(CommonSetup::setup);

        // Config
        this.registerCommonConfig(ModConfig.COMMON_SPEC);
    }

    @Override
    public EventHandler buildEventHandler() {
        return new ModEventHandler();
    }
}
