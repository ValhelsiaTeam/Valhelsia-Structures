package com.stal111.valhelsia_structures.mixin;

import com.stal111.valhelsia_structures.core.init.ModStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.valhelsia.valhelsia_core.world.IValhelsiaStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

/**
 * Lakes Feature Mixin
 * Valhelsia Structures - com.stal111.valhelsia_structures.mixin.LakesFeatureMixin
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-02-08
 */
@Mixin(LakeFeature.class)
public class LakesFeatureMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;down(I)Lnet/minecraft/util/math/BlockPos;"), method = "generate", cancellable = true)
    private void valhelsia_checkForStructures(WorldGenLevel reader, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateConfiguration config, CallbackInfoReturnable<Boolean> cir) {
        for (IValhelsiaStructure iStructure : ModStructures.MOD_STRUCTURES) {
            StructureFeature<?> structure = iStructure.getStructure();

            if (structure.getDecorationStage() == GenerationStage.Decoration.SURFACE_STRUCTURES) {
                if (reader.func_241827_a(SectionPos.from(pos), structure).findAny().isPresent()) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
