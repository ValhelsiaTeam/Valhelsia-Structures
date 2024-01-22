package com.stal111.valhelsia_structures.core.init.other;

import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.valhelsia.valhelsia_core.api.common.registry.helper.block.CompostableHelper;

/**
 * Compostable Registry <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.other.CompostableRegistry
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-02-18
 */
public class CompostableRegistry {

    private static final CompostableHelper HELPER = CompostableHelper.get();

    public static void register() {
        HELPER.register05(ModBlocks.HANGING_VINES.get());
        //HELPER.register065(ModBlocks.HIBISCUS.get());
        HELPER.register065(ModBlocks.GIANT_FERN.get());
    }
}
