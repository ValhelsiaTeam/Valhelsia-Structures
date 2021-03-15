package com.stal111.valhelsia_structures.init.other;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.valhelsia.valhelsia_core.registry.block.CompostableHelper;

/**
 * Compostable Registry
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.other.CompostableRegistry
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-02-18
 */
public class CompostableRegistry {

    private static final CompostableHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getBlockHelper().getCompostableHelper();

    public static void register() {
        HELPER.register05(ModBlocks.HANGING_VINES.get());
    }
}
