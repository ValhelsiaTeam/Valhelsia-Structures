package com.stal111.valhelsia_structures.core.init.other;

import com.stal111.valhelsia_structures.common.block.BrazierBlock;
import com.stal111.valhelsia_structures.common.block.DousedTorchBlock;
import com.stal111.valhelsia_structures.common.block.DousedWallTorchBlock;
import com.stal111.valhelsia_structures.utils.TorchTransformationHandler;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.valhelsia.valhelsia_core.common.helper.FlintAndSteelHelper;

import java.util.Objects;
import java.util.Random;

/**
 * Flint And Steel Registry <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.other.FlintAndSteelRegistry
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1..0
 * @since 2021-01-09
 */
public class FlintAndSteelRegistry {

    public static void register() {
        FlintAndSteelHelper.addUse(
                state -> state.getBlock() instanceof BrazierBlock && !state.getValue(BlockStateProperties.LIT) && !state.getValue(BlockStateProperties.WATERLOGGED),
                state -> state.getBlock().defaultBlockState(),
                (playerEntity, world, blockPos) -> world.playSound(playerEntity, blockPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, new Random().nextFloat() * 0.4F + 0.8F),
                world -> InteractionResult.sidedSuccess(world.isClientSide())
        );

        FlintAndSteelHelper.addUse(
                state -> state.getBlock() instanceof DousedTorchBlock && TorchTransformationHandler.getLitVersionFromDoused(state.getBlock()) != null && !state.getValue(BlockStateProperties.WATERLOGGED),
                state -> {
                    BlockState newState = Objects.requireNonNull(TorchTransformationHandler.getLitVersionFromDoused(state.getBlock())).defaultBlockState();
                    if (state.getBlock() instanceof DousedWallTorchBlock) {
                        newState = newState.setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING));
                    }
                    return newState;
                },
                (playerEntity, world, blockPos) -> world.playSound(playerEntity, blockPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, new Random().nextFloat() * 0.4F + 0.8F),
                world -> InteractionResult.sidedSuccess(world.isClientSide())
        );
    }
}
