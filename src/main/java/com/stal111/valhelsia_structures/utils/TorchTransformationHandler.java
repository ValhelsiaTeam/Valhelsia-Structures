package com.stal111.valhelsia_structures.utils;

import com.google.common.collect.ImmutableMap;
import com.stal111.valhelsia_structures.block.DousedTorchBlock;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.TorchBlock;

import java.util.Map;

/**
 * Torch Transformation Handler
 * Valhelsia Structures - com.stal111.valhelsia_structures.utils.TorchTransformationHandler
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-09
 */
public class TorchTransformationHandler {

    private static final Map<Block, DousedTorchBlock> TORCH_TO_DOUSED_TORCH_MAP = new ImmutableMap.Builder<Block, DousedTorchBlock>().put(Blocks.TORCH, ModBlocks.DOUSED_TORCH.get()).put(Blocks.WALL_TORCH, ModBlocks.DOUSED_WALL_TORCH.get()).put(Blocks.SOUL_TORCH, ModBlocks.DOUSED_SOUL_TORCH.get()).put(Blocks.SOUL_WALL_TORCH, ModBlocks.DOUSED_SOUL_WALL_TORCH.get()).build();

    public static boolean hasDousedVersion(Block block) {
        return TORCH_TO_DOUSED_TORCH_MAP.containsKey(block);
    }

    public static DousedTorchBlock getDousedTorchFor(Block block) {
        return TORCH_TO_DOUSED_TORCH_MAP.get(block);
    }

    public static TorchBlock getLitVersionFromDoused(Block block) {
        for (Map.Entry<Block, DousedTorchBlock> entry : TORCH_TO_DOUSED_TORCH_MAP.entrySet()) {
            if (entry.getValue() == block) {
                return (TorchBlock) entry.getKey();
            }
        }
        return null;
    }
}
