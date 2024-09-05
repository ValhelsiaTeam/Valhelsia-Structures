package com.stal111.valhelsia_structures.core.mixin;

import com.stal111.valhelsia_structures.common.world.structures.ValhelsiaJigsawStructure;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Lakes Feature Mixin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.mixin.LakesFeatureMixin
 *
 * @author Valhelsia Team
 * @since 2021-02-08
 */
@Mixin(LakeFeature.class)
public class LakesFeatureMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;below(I)Lnet/minecraft/core/BlockPos;"), method = "place", cancellable = true)
    private void valhelsia_checkForStructures(FeaturePlaceContext<BlockStateConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        if (context.level() instanceof ServerLevel serverLevel) {
            if (!serverLevel.structureManager().startsForStructure(new ChunkPos(context.origin()), structure -> {
                return structure instanceof ValhelsiaJigsawStructure && structure.step() == GenerationStep.Decoration.SURFACE_STRUCTURES;
            }).isEmpty()) {
                cir.setReturnValue(false);
            }
        }
    }
}
