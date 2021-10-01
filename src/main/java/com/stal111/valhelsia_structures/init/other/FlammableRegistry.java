package com.stal111.valhelsia_structures.init.other;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.valhelsia.valhelsia_core.registry.block.FlammableHelper;

/**
 * Flammable Registry
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.other.FlammableRegistry
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-02-14
 */
public class FlammableRegistry {

    private static final FlammableHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getBlockHelper().getFlammableHelper();

    public static void register() {
        HELPER.registerLog(ModBlocks.OAK_POST.get());
        HELPER.registerLog(ModBlocks.SPRUCE_POST.get());
        HELPER.registerLog(ModBlocks.BIRCH_POST.get());
        HELPER.registerLog(ModBlocks.JUNGLE_POST.get());
        HELPER.registerLog(ModBlocks.ACACIA_POST.get());
        HELPER.registerLog(ModBlocks.DARK_OAK_POST.get());
        HELPER.registerLog(ModBlocks.CUT_OAK_POST.get());
        HELPER.registerLog(ModBlocks.CUT_SPRUCE_POST.get());
        HELPER.registerLog(ModBlocks.CUT_BIRCH_POST.get());
        HELPER.registerLog(ModBlocks.CUT_JUNGLE_POST.get());
        HELPER.registerLog(ModBlocks.CUT_ACACIA_POST.get());
        HELPER.registerLog(ModBlocks.CUT_DARK_OAK_POST.get());
        HELPER.register(ModBlocks.PAPER_WALL.get(), 60, 60);
        HELPER.register(ModBlocks.HANGING_VINES_BODY.get(), 15, 100);
        HELPER.register(ModBlocks.HANGING_VINES.get(), 15, 100);
       // HELPER.registerPlant(ModBlocks.HIBISCUS.get());
       // HELPER.registerPlant(ModBlocks.GIANT_FERN.get());
    }
}
