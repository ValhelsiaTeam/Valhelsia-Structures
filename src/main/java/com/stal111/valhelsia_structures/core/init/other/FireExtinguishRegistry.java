package com.stal111.valhelsia_structures.core.init.other;

import com.stal111.valhelsia_structures.common.block.BrazierBlock;
import com.stal111.valhelsia_structures.common.block.DousedWallTorchBlock;
import com.stal111.valhelsia_structures.utils.TorchTransformationHandler;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.valhelsia.valhelsia_core.common.helper.FireExtinguishHelper;

/**
 * Fire Extinguish Registry <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.other.FireExtinguishRegistry
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-01-09
 */
public class FireExtinguishRegistry {

    public static void register() {
        FireExtinguishHelper.addExtinguishFireEffect(
                state -> state.getBlock() instanceof BrazierBlock && state.getValue(BlockStateProperties.LIT),
                state -> state.setValue(BlockStateProperties.LIT, false),
                (level, blockPos) -> level.levelEvent(null, 1009, blockPos, 0)
        );

        FireExtinguishHelper.addExtinguishFireEffect(
                state -> state.getBlock() instanceof TorchBlock && TorchTransformationHandler.hasDousedVersion(state.getBlock()),
                state -> {
                    BlockState newState = TorchTransformationHandler.getDousedTorchFor(state.getBlock()).defaultBlockState();
                    if (newState.getBlock() instanceof DousedWallTorchBlock) {
                        newState = newState.setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING));
                    }
                    return newState;
                },
                (level, blockPos) -> level.levelEvent(null, 1009, blockPos, 0)
        );
    }
}
