package com.stal111.valhelsia_structures.core.mixin;

import com.stal111.valhelsia_structures.core.init.ModStructures;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.valhelsia.valhelsia_core.common.world.IValhelsiaStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Lakes Feature Mixin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.mixin.LakesFeatureMixin
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-02-08
 */
@Mixin(LakeFeature.class)
public class LakesFeatureMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;below(I)Lnet/minecraft/core/BlockPos;"), method = "place", cancellable = true)
    private void valhelsia_checkForStructures(FeaturePlaceContext<BlockStateConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        for (IValhelsiaStructure iStructure : ModStructures.MOD_STRUCTURES) {
            StructureFeature<?> structure = iStructure.getStructure();

            if (structure.step() == GenerationStep.Decoration.SURFACE_STRUCTURES) {
                if (context.level().startsForFeature(SectionPos.of(context.origin()), structure).findAny().isPresent()) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
