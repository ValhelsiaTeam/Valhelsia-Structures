package com.stal111.valhelsia_structures.init.other;

import com.stal111.valhelsia_structures.block.BrazierBlock;
import com.stal111.valhelsia_structures.block.DousedTorchBlock;
import com.stal111.valhelsia_structures.block.DousedWallTorchBlock;
import com.stal111.valhelsia_structures.utils.TorchTransformationHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.valhelsia.valhelsia_core.helper.FlintAndSteelHelper;

import java.util.Objects;
import java.util.Random;

/**
 * Flint And Steel Registry
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.other.FlintAndSteelRegistry
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-09
 */
public class FlintAndSteelRegistry {

    public static void register() {
        FlintAndSteelHelper.addUse(
                state -> state.getBlock() instanceof BrazierBlock && !state.get(BlockStateProperties.LIT) && !state.get(BlockStateProperties.WATERLOGGED),
                state -> state.getBlock().getDefaultState(),
                (playerEntity, world, blockPos) -> world.playSound(playerEntity, blockPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, new Random().nextFloat() * 0.4F + 0.8F),
                world -> ActionResultType.func_233537_a_(world.isRemote()));

        FlintAndSteelHelper.addUse(
                state -> state.getBlock() instanceof DousedTorchBlock && TorchTransformationHandler.getLitVersionFromDoused(state.getBlock()) != null && !state.get(BlockStateProperties.WATERLOGGED),
                state -> {
                    BlockState newState = Objects.requireNonNull(TorchTransformationHandler.getLitVersionFromDoused(state.getBlock())).getDefaultState();
                    if (state.getBlock() instanceof DousedWallTorchBlock) {
                        newState = newState.with(HorizontalBlock.HORIZONTAL_FACING, state.get(HorizontalBlock.HORIZONTAL_FACING));
                    }
                    return newState;
                },
                (playerEntity, world, blockPos) -> world.playSound(playerEntity, blockPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, new Random().nextFloat() * 0.4F + 0.8F),
                world -> ActionResultType.func_233537_a_(world.isRemote()));
    }
}
