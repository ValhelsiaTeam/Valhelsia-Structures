package com.stal111.valhelsia_structures.mixin;

import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.utils.StructureType;
import com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure;
import com.stal111.valhelsia_structures.world.structures.RemovedStructure;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.LakesFeature;
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
@Mixin(LakesFeature.class)
public class LakesFeatureMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;down(I)Lnet/minecraft/util/math/BlockPos;"), method = "generate", cancellable = true)
    private void valhelsia_checkForStructures(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config, CallbackInfoReturnable<Boolean> cir) {
        for (AbstractValhelsiaStructure structure : ModStructures.STRUCTURES_MAP.get(StructureType.SURFACE)) {
            if (!(structure instanceof RemovedStructure)) {
                if (reader.func_241827_a(SectionPos.from(pos), structure).findAny().isPresent()) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
