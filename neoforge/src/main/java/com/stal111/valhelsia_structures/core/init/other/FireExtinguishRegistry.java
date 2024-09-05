package com.stal111.valhelsia_structures.core.init.other;

import com.google.common.collect.ImmutableMap;
import com.stal111.valhelsia_structures.common.block.BrazierBlock;
import com.stal111.valhelsia_structures.common.block.UnlitTorchBlock;
import com.stal111.valhelsia_structures.common.block.UnlitWallTorchBlock;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.valhelsia.valhelsia_core.api.common.helper.FireExtinguishHelper;

import java.util.Map;

/**
 * Fire Extinguish Registry <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.other.FireExtinguishRegistry
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-01-09
 */
public class FireExtinguishRegistry {

    private static final Map<Block, UnlitTorchBlock> TORCH_TO_DOUSED_TORCH_MAP = new ImmutableMap.Builder<Block, UnlitTorchBlock>()
            .put(Blocks.TORCH, ModBlocks.UNLIT_TORCH.get())
            .put(Blocks.WALL_TORCH, ModBlocks.UNLIT_WALL_TORCH.get())
            .put(Blocks.SOUL_TORCH, ModBlocks.UNLIT_SOUL_TORCH.get())
            .put(Blocks.SOUL_WALL_TORCH, ModBlocks.UNLIT_SOUL_WALL_TORCH.get())
            .build();

    public static void register() {
        FireExtinguishHelper.addExtinguishFireEffect(
                state -> state.getBlock() instanceof BrazierBlock && state.getValue(BlockStateProperties.LIT),
                state -> state.setValue(BlockStateProperties.LIT, false),
                (level, blockPos) -> level.levelEvent(null, 1009, blockPos, 0)
        );

        FireExtinguishHelper.addExtinguishFireEffect(
                state -> TORCH_TO_DOUSED_TORCH_MAP.containsKey(state.getBlock()),
                state -> {
                    BlockState newState = TORCH_TO_DOUSED_TORCH_MAP.get(state.getBlock()).defaultBlockState();
                    if (newState.getBlock() instanceof UnlitWallTorchBlock) {
                        newState = newState.setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING));
                    }
                    return newState;
                },
                (level, blockPos) -> level.levelEvent(null, 1009, blockPos, 0)
        );
    }
}
