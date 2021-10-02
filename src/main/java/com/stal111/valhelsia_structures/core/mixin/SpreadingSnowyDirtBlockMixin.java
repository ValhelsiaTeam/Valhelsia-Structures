package com.stal111.valhelsia_structures.core.mixin;

import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Spreading Snowy Dirt Block Mixin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.mixin.SpreadingSnowyDirtBlockMixin
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-01-10
 */
@Mixin(SpreadingSnowyDirtBlock.class)
public class SpreadingSnowyDirtBlockMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/SpreadingSnowyDirtBlock;defaultBlockState()Lnet/minecraft/world/level/block/state/BlockState;"), method = "randomTick")
    private BlockState valhelsia_randomTick(SpreadingSnowyDirtBlock block) {
        if (block == ModBlocks.GRASS_BLOCK.get()) {
            return Blocks.GRASS_BLOCK.defaultBlockState();
        }
        return block.defaultBlockState();
    }
}
