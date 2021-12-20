package com.stal111.valhelsia_structures.core.mixin;

import com.stal111.valhelsia_structures.common.block.DousedWallTorchBlock;
import com.stal111.valhelsia_structures.utils.TorchTransformationHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Flowing Fluid Mixin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.mixin.FlowingFluidMixin
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-09
 */
@Mixin(FlowingFluid.class)
public class FlowingFluidMixin {

    @Inject(at = @At(value = "HEAD"), method = "canHoldFluid", cancellable = true)
    private void valhelsia_canHoldFluid(BlockGetter level, BlockPos pos, BlockState state, Fluid fluid, CallbackInfoReturnable<Boolean> cir) {
        Block block = state.getBlock();
        if (block instanceof TorchBlock && fluid == Fluids.FLOWING_WATER) {
            cir.setReturnValue(false);
        }
    }

    @Inject(at = @At(value = "HEAD"), method = "spreadTo", cancellable = true)
    private void valhelsia_spreadTo(LevelAccessor world, BlockPos pos, BlockState state, Direction direction, FluidState fluidState, CallbackInfo ci) {
        if (TorchTransformationHandler.hasDousedVersion(state.getBlock())) {
            if (fluidState.getType() == Fluids.WATER) {
                if (!world.isClientSide()) {
                    BlockState newState = TorchTransformationHandler.getDousedTorchFor(state.getBlock()).defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true);
                    if (newState.getBlock() instanceof DousedWallTorchBlock) {
                        newState = newState.setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING));
                    }
                    world.setBlock(pos, newState, 3);
                    world.scheduleTick(pos, fluidState.getType(), fluidState.getType().getTickDelay(world));
                }
                ci.cancel();
            }
        }
    }
}
