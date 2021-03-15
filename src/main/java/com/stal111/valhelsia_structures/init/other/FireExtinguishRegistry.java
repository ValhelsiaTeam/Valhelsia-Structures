package com.stal111.valhelsia_structures.init.other;

import com.stal111.valhelsia_structures.block.BrazierBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.valhelsia.valhelsia_core.helper.FireExtinguishHelper;

/**
 * Fire Extinguish Registry
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.other.FireExtinguishRegistry
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-09
 */
public class FireExtinguishRegistry {

    public static void register() {
        FireExtinguishHelper.addExtinguishFireEffect(
                state -> state.getBlock() instanceof BrazierBlock && state.get(BlockStateProperties.LIT),
                state -> state.with(BlockStateProperties.LIT, false),
                (world, blockPos) -> world.playEvent(null, 1009, blockPos, 0));
    }
}
