package com.stal111.valhelsia_structures;

import com.stal111.valhelsia_structures.world.WorldGen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ValhelsiaStructure.MOD_ID)
public class ValhelsiaStructure {
    public static final String MOD_ID = "valhelsia_structure";

    private static final Logger LOGGER = LogManager.getLogger();


    public ValhelsiaStructure() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        WorldGen.setupWorldGen();
    }
}
