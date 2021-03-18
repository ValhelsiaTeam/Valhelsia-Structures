package com.stal111.valhelsia_structures.mixin;

import com.stal111.valhelsia_structures.utils.TorchTransformationHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.IParticleData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Wall Torch Block Mixin
 * Valhelsia Structures - com.stal111.valhelsia_structures.mixin.WallTorchBlockMixin
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-09
 */
@Mixin(WallTorchBlock.class)
public class WallTorchBlockMixin extends TorchBlock {

    public WallTorchBlockMixin(Properties properties, IParticleData particleData) {
        super(properties, particleData);
    }

    @Inject(at = @At(value = "HEAD"), method = "getStateForPlacement", cancellable = true)
    public void valhelsia_getStateForPlacement(BlockItemUseContext context, CallbackInfoReturnable<BlockState> cir) {
        FluidState fluidState = context.getWorld().getFluidState(context.getPos());
        if (fluidState.getFluid() == Fluids.WATER && TorchTransformationHandler.hasDousedVersion(this)) {
            cir.setReturnValue(TorchTransformationHandler.getDousedTorchFor(this).getStateForPlacement(context));
        }
    }
}
