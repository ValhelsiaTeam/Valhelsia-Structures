package com.stal111.valhelsia_structures.mixin;

import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.BlockGetter;
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
@Mixin(BlockBehaviour.BlockStateBase.class)
public class AbstractBlockStateMixin {

    @Inject(at = @At(value = "HEAD"), method = "getOffset", cancellable = true)
    private void valhelsia_placeDousedTorch(BlockGetter access, BlockPos pos, CallbackInfoReturnable<Vec3> cir) {
        if (access != null && access.getBlockState(pos).getBlock() == ModBlocks.BONE_PILE.get()) {
            cir.setReturnValue(new Vec3(0.0D, -0.46875D, 0.0D));
        }
    }
}
