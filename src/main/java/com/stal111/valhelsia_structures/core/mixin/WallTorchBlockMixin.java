package com.stal111.valhelsia_structures.core.mixin;

import com.stal111.valhelsia_structures.core.config.ModConfig;
import com.stal111.valhelsia_structures.utils.TorchTransformationHandler;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Wall Torch Block Mixin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.mixin.WallTorchBlockMixin
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-01-09
 */
@Mixin(WallTorchBlock.class)
public class WallTorchBlockMixin extends TorchBlock {

    public WallTorchBlockMixin(Properties properties, ParticleOptions particleData) {
        super(properties, particleData);
    }

    @Inject(at = @At(value = "HEAD"), method = "getStateForPlacement", cancellable = true)
    public void valhelsia_getStateForPlacement(BlockPlaceContext context, CallbackInfoReturnable<BlockState> cir) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());

        if (fluidState.getType() == Fluids.WATER && TorchTransformationHandler.hasDousedVersion(this) && !ModConfig.COMMON.disableDousedTorch.get()) {
            cir.setReturnValue(TorchTransformationHandler.getDousedTorchFor(this).getStateForPlacement(context));
        }
    }
}
