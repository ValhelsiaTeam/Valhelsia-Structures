package com.stal111.valhelsia_structures.mixin;

import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpreadableSnowyDirtBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Spreadable Snowy Dirt Block Mixin
 * Valhelsia Structures - com.stal111.valhelsia_structures.mixin.SpreadableSnowyDirtBlockMixin
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-10
 */
@Mixin(SpreadableSnowyDirtBlock.class)
public class SpreadableSnowyDirtBlockMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/block/SpreadableSnowyDirtBlock;getDefaultState()Lnet/minecraft/block/BlockState;"), method = "randomTick")
    private BlockState valhelsia_isBlocked(SpreadableSnowyDirtBlock block) {
        if (block == ModBlocks.GRASS_BLOCK.get()) {
            return Blocks.GRASS_BLOCK.getDefaultState();
        }
        return block.getDefaultState();
    }
}
