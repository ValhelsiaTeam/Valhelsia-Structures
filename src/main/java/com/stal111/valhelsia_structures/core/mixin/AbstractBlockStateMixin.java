package com.stal111.valhelsia_structures.core.mixin;

import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.BlockGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Abstract Block State Mixin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.mixin.AbstractBlockStateMixin
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-03-24
 */
@Mixin(BlockBehaviour.BlockStateBase.class)
public class AbstractBlockStateMixin {

    //@Inject(at = @At(value = "HEAD"), method = "getOffset", cancellable = true)
    private void valhelsia_getOffset(BlockGetter access, BlockPos pos, CallbackInfoReturnable<Vec3> cir) {
        if (access != null && access.getBlockState(pos).getBlock() == ModBlocks.BONE_PILE.get()) {
            cir.setReturnValue(new Vec3(0.0D, -0.46875D, 0.0D));
        }
    }
}
