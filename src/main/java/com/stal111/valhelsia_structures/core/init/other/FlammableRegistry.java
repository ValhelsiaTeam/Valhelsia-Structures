package com.stal111.valhelsia_structures.core.init.other;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.valhelsia.valhelsia_core.core.registry.helper.block.FlammableHelper;

/**
 * Flammable Registry <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.other.FlammableRegistry
 *
 * @author Valhelsia Team
 * @since 2021-02-14
 */
public class FlammableRegistry {

    private static final FlammableHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getBlockHelper().getFlammableHelper();

    public static void register() {
        ModBlocks.WOODEN_POSTS.forEach((woodType, registryObject) -> {
            if (woodType.isFlammable()) {
                HELPER.registerLog(registryObject.get());
            }
        });

        ModBlocks.STRIPPED_WOODEN_POSTS.forEach((woodType, registryObject) -> {
            if (woodType.isFlammable()) {
                HELPER.registerLog(registryObject.get());
            }
        });

        ModBlocks.CUT_WOODEN_POSTS.forEach((woodType, registryObject) -> {
            if (woodType.isFlammable()) {
                HELPER.registerLog(registryObject.get());
            }
        });

        ModBlocks.CUT_STRIPPED_WOODEN_POSTS.forEach((woodType, registryObject) -> {
            if (woodType.isFlammable()) {
                HELPER.registerLog(registryObject.get());
            }
        });

        HELPER.register(ModBlocks.PAPER_WALL.get(), 60, 60);
        HELPER.register(ModBlocks.HANGING_VINES_BODY.get(), 15, 100);
        HELPER.register(ModBlocks.HANGING_VINES.get(), 15, 100);
       // HELPER.registerPlant(ModBlocks.HIBISCUS.get());
       // HELPER.registerPlant(ModBlocks.GIANT_FERN.get());
    }
}
