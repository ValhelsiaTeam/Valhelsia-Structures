package com.stal111.valhelsia_structures.common;

import com.stal111.valhelsia_structures.core.init.ModStructureFeatures;
import com.stal111.valhelsia_structures.core.init.ModStructures;
import com.stal111.valhelsia_structures.core.init.other.CompostableRegistry;
import com.stal111.valhelsia_structures.core.init.other.FireExtinguishRegistry;
import com.stal111.valhelsia_structures.core.init.other.FlammableRegistry;
import com.stal111.valhelsia_structures.core.init.other.FlintAndSteelRegistry;
import com.stal111.valhelsia_structures.common.world.structures.pools.*;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Common Setup <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.CommonSetup
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-02-14
 */
public class CommonSetup {

    public static void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            FlammableRegistry.register();
            CompostableRegistry.register();

            ModStructures.setupStructures();
            ModStructureFeatures.registerStructureFeatures();
        });

        FlintAndSteelRegistry.register();
        FireExtinguishRegistry.register();

        SpawnerDungeonPools.load();
        MobPools.load();
        PlayerHousePools.load();
        DesertHousePools.load();
        BigTreePools.load();
    }
}
