package com.stal111.valhelsia_structures.mixin;

import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Abstract Block State Mixin
 * Valhelsia Structures - com.stal111.valhelsia_structures.mixin.AbstractBlockStateMixin
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-03-24
 */
@Mixin(AbstractBlock.AbstractBlockState.class)
public class AbstractBlockStateMixin {

    @Inject(at = @At(value = "HEAD"), method = "getOffset", cancellable = true)
    private void valhelsia_placeDousedTorch(IBlockReader access, BlockPos pos, CallbackInfoReturnable<Vector3d> cir) {
        if (access.getBlockState(pos).getBlock() == ModBlocks.BONE_PILE.get()) {
            cir.setReturnValue(new Vector3d(0.0D, -0.46875D, 0.0D));
        }
    }
}
