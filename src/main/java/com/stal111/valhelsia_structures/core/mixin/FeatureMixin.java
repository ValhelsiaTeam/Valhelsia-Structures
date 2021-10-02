package com.stal111.valhelsia_structures.core.mixin;

import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Feature Mixin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.mixin.FeatureMixin
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-01-10
 */
@Mixin(Feature.class)
public class FeatureMixin {

    @Inject(at = @At(value = "HEAD"), method = "isDirt", cancellable = true)
    private static void valhelsia_isDirt(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(ModBlocks.GRASS_BLOCK.get()) || state.is(ModBlocks.DIRT.get()) || state.is(ModBlocks.COARSE_DIRT.get())) {
            cir.setReturnValue(false);
        }
    }
}
