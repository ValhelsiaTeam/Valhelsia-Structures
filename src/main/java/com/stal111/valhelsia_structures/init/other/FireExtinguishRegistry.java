package com.stal111.valhelsia_structures.init.other;

import com.stal111.valhelsia_structures.block.BrazierBlock;
import com.stal111.valhelsia_structures.block.DousedWallTorchBlock;
import com.stal111.valhelsia_structures.utils.TorchTransformationHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.TorchBlock;
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

        FireExtinguishHelper.addExtinguishFireEffect(
                state -> state.getBlock() instanceof TorchBlock && TorchTransformationHandler.hasDousedVersion(state.getBlock()),
                state -> {
                    BlockState newState = TorchTransformationHandler.getDousedTorchFor(state.getBlock()).getDefaultState();
                    if (newState.getBlock() instanceof DousedWallTorchBlock) {
                        newState = newState.with(HorizontalBlock.HORIZONTAL_FACING, state.get(HorizontalBlock.HORIZONTAL_FACING));
                    }
                    return newState;
                },
                (world, blockPos) -> world.playEvent(null, 1009, blockPos, 0));
    }
}
