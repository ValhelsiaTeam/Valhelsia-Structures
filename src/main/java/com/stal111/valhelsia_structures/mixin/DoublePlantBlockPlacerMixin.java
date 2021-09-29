package com.stal111.valhelsia_structures.mixin;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.blockplacers.DoublePlantPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

/**
 * Double Plant Block Placer Mixin
 * Valhelsia Structures - com.stal111.valhelsia_structures.mixin.DoublePlantBlockPlacerMixin
 *
 * @author Valhelsia Team
 * @version 0.1.1
 * @since 2021-05-15
 */
@Mixin(DoublePlantPlacer.class)
public class DoublePlantBlockPlacerMixin {

    @Inject(at = @At(value = "HEAD"), method = "place", cancellable = true)
    private void valhelsia_checkForAir(LevelAccessor world, BlockPos pos, BlockState state, Random random, CallbackInfo ci) {
        if (!world.getBlockState(pos.up()).isAir()) {
            ci.cancel();
        }
    }
}
