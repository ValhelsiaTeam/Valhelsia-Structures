package com.stal111.valhelsia_structures.common;

import com.stal111.valhelsia_structures.core.init.other.*;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Common Setup <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.CommonSetup
 *
 * @author Valhelsia Team
 * @since 2021-02-14
 */
public class CommonSetup {

    public static void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            FlammableRegistry.register();
            CompostableRegistry.register();

            ModWoodTypes.registerWoodTypes();
        });

        FlintAndSteelRegistry.register();
        FireExtinguishRegistry.register();
    }
}
