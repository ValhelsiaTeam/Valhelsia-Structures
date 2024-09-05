package com.stal111.valhelsia_structures.core.init.other;

import com.stal111.valhelsia_structures.common.block.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.valhelsia.valhelsia_core.api.common.helper.FlintAndSteelHelper;

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
                (player, level, blockPos) -> level.playSound(player, blockPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F),
                level -> InteractionResult.sidedSuccess(level.isClientSide())
        );

        FlintAndSteelHelper.addUse(
                state -> state.getBlock() instanceof UnlitTorchBlock,
                state -> {
                    BlockState newState = ((UnlitTorchBlock) state.getBlock()).getLitState();
                    if (state.getBlock() instanceof UnlitWallTorchBlock) {
                        newState = newState.setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING));
                    }
                    return newState;
                },
                (player, level, blockPos) -> level.playSound(player, blockPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F),
                level -> InteractionResult.sidedSuccess(level.isClientSide())
        );

        FlintAndSteelHelper.addUse(
                state -> state.getBlock() instanceof UnlitLanternBlock,
                state -> ((UnlitLanternBlock) state.getBlock()).getLitLantern().defaultBlockState().setValue(BlockStateProperties.HANGING, state.getValue(BlockStateProperties.HANGING)).setValue(BlockStateProperties.WATERLOGGED, state.getValue(BlockStateProperties.WATERLOGGED)),
                (player, level, blockPos) -> level.playSound(player, blockPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F),
                level -> InteractionResult.sidedSuccess(level.isClientSide())
        );
    }
}
