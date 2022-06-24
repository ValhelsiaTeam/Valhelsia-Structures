package com.stal111.valhelsia_structures.core.mixin;

import com.stal111.valhelsia_structures.core.init.world.ModStructures;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Lakes Feature Mixin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.mixin.LakesFeatureMixin
 *
 * @author Valhelsia Team
 * @version 1.19 - 0.2.0
 * @since 2021-02-08
 */
@Mixin(LakeFeature.class)
public class LakesFeatureMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;below(I)Lnet/minecraft/core/BlockPos;"), method = "place", cancellable = true)
    private void valhelsia_checkForStructures(FeaturePlaceContext<BlockStateConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        if (context.level() instanceof ServerLevel serverLevel) {
            for (RegistryObject<Structure> registryObject : ModStructures.HELPER.getRegistryObjects()) {
                Structure structure = registryObject.get();

                if (structure.step() != GenerationStep.Decoration.SURFACE_STRUCTURES) {
                    continue;
                }

                if (!serverLevel.structureManager().startsForStructure(SectionPos.of(context.origin()), structure).isEmpty()) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
