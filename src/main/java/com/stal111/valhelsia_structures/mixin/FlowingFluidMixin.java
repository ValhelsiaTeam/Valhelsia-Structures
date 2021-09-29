package com.stal111.valhelsia_structures.mixin;

import com.stal111.valhelsia_structures.block.DousedWallTorchBlock;
import com.stal111.valhelsia_structures.utils.TorchTransformationHandler;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Flowing Fluid Mixin
 * Valhelsia Structures - com.stal111.valhelsia_structures.mixin.FlowingFluidMixin
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-09
 */
@Mixin(FlowingFluid.class)
public class FlowingFluidMixin {

    @Inject(at = @At(value = "HEAD"), method = "isBlocked", cancellable = true)
    private void valhelsia_isBlocked(BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluid, CallbackInfoReturnable<Boolean> cir) {
        Block block = state.getBlock();
        if (block instanceof TorchBlock && fluid == Fluids.FLOWING_WATER) {
            cir.setReturnValue(false);
        }
    }

    @Inject(at = @At(value = "HEAD"), method = "flowInto", cancellable = true)
    private void valhelsia_flowInto(LevelAccessor world, BlockPos pos, BlockState state, Direction direction, FluidState fluidState, CallbackInfo ci) {
        if (TorchTransformationHandler.hasDousedVersion(state.getBlock())) {
            if (fluidState.getFluid() == Fluids.WATER) {
                if (!world.isRemote()) {
                    BlockState newState = TorchTransformationHandler.getDousedTorchFor(state.getBlock()).getDefaultState().with(BlockStateProperties.WATERLOGGED, true);
                    if (newState.getBlock() instanceof DousedWallTorchBlock) {
                        newState = newState.with(HorizontalBlock.HORIZONTAL_FACING, state.get(HorizontalBlock.HORIZONTAL_FACING));
                    }
                    world.setBlockState(pos, newState, 3);
                    world.getPendingFluidTicks().scheduleTick(pos, fluidState.getFluid(), fluidState.getFluid().getTickRate(world));
                }
                ci.cancel();
            }
        }
    }
}
