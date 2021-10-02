package com.stal111.valhelsia_structures.mixin;

import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Feature Mixin
 * Valhelsia Structures - com.stal111.valhelsia_structures.mixin.FeatureMixin
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-10
 */
@Mixin(Feature.class)
public class FeatureMixin {

    @Inject(at = @At(value = "HEAD"), method = "isDirt", cancellable = true)
    private static void valhelsia_isDirt(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (block == ModBlocks.GRASS_BLOCK.get() || block == ModBlocks.DIRT.get() || block == ModBlocks.COARSE_DIRT.get()) {
            cir.setReturnValue(false);
        }
    }
}
