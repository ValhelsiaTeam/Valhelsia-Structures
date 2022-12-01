package com.stal111.valhelsia_structures.core.mixin;

import com.stal111.valhelsia_structures.common.world.structures.ValhelsiaJigsawStructure;
import com.stal111.valhelsia_structures.common.world.structures.pools.ValhelsiaSinglePoolElement;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Beardifier;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.pools.JigsawJunction;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Valhelsia Team
 * @since 2022-11-29
 */
@Mixin(Beardifier.class)
public class BeardifierMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/Structure;terrainAdaptation()Lnet/minecraft/world/level/levelgen/structure/TerrainAdjustment;"), method = "lambda$forStructuresInChunk$1", remap = false)
    private static TerrainAdjustment valhelsia_forStructuresInChunk(Structure structure) {
        if (structure instanceof ValhelsiaJigsawStructure valhelsiaJigsawStructure && valhelsiaJigsawStructure.hasIndividualTerrainAdjustment()) {
            return TerrainAdjustment.BEARD_THIN;
        }

        return structure.terrainAdaptation();
    }

    @Inject(at = @At(value = "HEAD"), method = "lambda$forStructuresInChunk$2", cancellable = true, remap = false)
    private static void valhelsia_forStructuresInChunk(ChunkPos chunkPos, ObjectList<Beardifier.Rigid> rigids, int minX, int minZ, ObjectList<JigsawJunction> junctions, StructureStart start, CallbackInfo ci) {
        if (start.getStructure() instanceof ValhelsiaJigsawStructure valhelsiaJigsawStructure && valhelsiaJigsawStructure.hasIndividualTerrainAdjustment()) {
            TerrainAdjustment terrainAdjustment = start.getStructure().terrainAdaptation();

            for(StructurePiece structurePiece : start.getPieces()) {
                if (structurePiece.isCloseToChunk(chunkPos, 12)) {
                    if (structurePiece instanceof PoolElementStructurePiece piece) {
                        StructureTemplatePool.Projection projection = piece.getElement().getProjection();
                        if (projection == StructureTemplatePool.Projection.RIGID) {
                            if (piece.getElement() instanceof ValhelsiaSinglePoolElement element) {
                                rigids.add(new Beardifier.Rigid(piece.getBoundingBox(), element.getTerrainAdjustment(), piece.getGroundLevelDelta()));
                            } else {
                                rigids.add(new Beardifier.Rigid(piece.getBoundingBox(), terrainAdjustment, piece.getGroundLevelDelta()));
                            }
                        }

                        for(JigsawJunction jigsawjunction : piece.getJunctions()) {
                            int k = jigsawjunction.getSourceX();
                            int l = jigsawjunction.getSourceZ();
                            if (k > minX - 12 && l > minZ - 12 && k < minX + 15 + 12 && l < minZ + 15 + 12) {
                                junctions.add(jigsawjunction);
                            }
                        }
                    } else {
                        rigids.add(new Beardifier.Rigid(structurePiece.getBoundingBox(), terrainAdjustment, 0));
                    }
                }
            }

            ci.cancel();
        }
    }
}
