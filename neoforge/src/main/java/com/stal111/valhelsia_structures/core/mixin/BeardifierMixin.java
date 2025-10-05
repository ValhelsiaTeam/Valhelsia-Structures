package com.stal111.valhelsia_structures.core.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.stal111.valhelsia_structures.common.world.structures.ValhelsiaJigsawStructure;
import com.stal111.valhelsia_structures.common.world.structures.pools.ValhelsiaPoolElementWrapper;
import net.minecraft.world.level.levelgen.Beardifier;
import net.minecraft.world.level.levelgen.structure.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * @author Valhelsia Team
 * @since 2022-11-29
 */
@Mixin(Beardifier.class)
public class BeardifierMixin {

    @WrapOperation(
            method = "lambda$forStructuresInChunk$1",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/Structure;terrainAdaptation()Lnet/minecraft/world/level/levelgen/structure/TerrainAdjustment;"))
    private static TerrainAdjustment valhelsia_forStructuresInChunk(Structure structure, Operation<TerrainAdjustment> original) {
        if (structure instanceof ValhelsiaJigsawStructure valhelsiaJigsawStructure && valhelsiaJigsawStructure.hasIndividualTerrainAdjustment()) {
            return TerrainAdjustment.BEARD_THIN;
        }

        return original.call(structure);
    }

    @ModifyArg(
            method = "forStructuresInChunk",
            at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 1))
    private static <E> E valhelsia_forStructuresInChunk(E e, @Local StructureStart start, @Local StructurePiece structurePiece) {
        if (start.getStructure() instanceof ValhelsiaJigsawStructure valhelsiaJigsawStructure && valhelsiaJigsawStructure.hasIndividualTerrainAdjustment()) {
            if (e instanceof Beardifier.Rigid rigid && structurePiece instanceof PoolElementStructurePiece piece && piece.getElement() instanceof ValhelsiaPoolElementWrapper wrapper && wrapper.getTerrainAdjustment() != null) {
                return (E) new Beardifier.Rigid(rigid.box(), wrapper.getTerrainAdjustment(), rigid.groundLevelDelta());
            }
        }

        return e;
    }
}
