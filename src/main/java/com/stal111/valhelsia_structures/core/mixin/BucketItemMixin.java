package com.stal111.valhelsia_structures.core.mixin;

import com.stal111.valhelsia_structures.common.block.DousedWallTorchBlock;
import com.stal111.valhelsia_structures.utils.TorchTransformationHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

/**
 * Bucket Item Mixin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.mixin.BucketItemMixin
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-01-09
 */
@Mixin(BucketItem.class)
public abstract class BucketItemMixin {

    @Shadow
    protected abstract void playEmptySound(@Nullable Player player, LevelAccessor worldIn, BlockPos pos);

    @Shadow @Final private Fluid content;

    @Inject(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/material/Material;isLiquid()Z",
                    shift = At.Shift.BEFORE), method = "emptyContents",
            cancellable = true
    )
    private void valhelsia_placeDousedTorch(Player player, Level level, BlockPos pos, BlockHitResult hitResult, CallbackInfoReturnable<Boolean> cir) {
        BlockState state = level.getBlockState(pos);

        if (this.content == Fluids.WATER) {
            if (TorchTransformationHandler.hasDousedVersion(state.getBlock())) {
                BlockState newState = TorchTransformationHandler.getDousedTorchFor(state.getBlock()).defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true);

                if (newState.getBlock() instanceof DousedWallTorchBlock) {
                    newState = newState.setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING));
                }
                if (!level.isClientSide()) {
                    level.setBlock(pos, newState.setValue(BlockStateProperties.WATERLOGGED, true), 3);
                    level.scheduleTick(pos, this.content, this.content.getTickDelay(level));
                }
                playEmptySound(player, level, pos);

                cir.setReturnValue(true);
            }
        }
    }

    @Inject(at = @At(value = "HEAD"), method = "canBlockContainFluid", cancellable = true, remap = false)
    private void valhelsia_canBlockContainFluid(Level level, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        Block block = state.getBlock();
        if (block instanceof TorchBlock && this.content == Fluids.WATER) {
            cir.setReturnValue(true);
        }
    }
}
