package com.stal111.valhelsia_structures.core.mixin;

import net.minecraft.world.level.levelgen.Beardifier;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Valhelsia Team
 * @since 2022-11-29
 */
@Mixin(Beardifier.class)
public class BeardifierMixin {

//    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/Structure;terrainAdaptation()Lnet/minecraft/world/level/levelgen/structure/TerrainAdjustment;"), method = {"lambda$forStructuresInChunk$1", "m_223940_"})
//    private static TerrainAdjustment valhelsia_forStructuresInChunk(Structure structure) {
//        if (structure instanceof ValhelsiaJigsawStructure valhelsiaJigsawStructure && valhelsiaJigsawStructure.hasIndividualTerrainAdjustment()) {
//            return TerrainAdjustment.BEARD_THIN;
//        }
//
//        return structure.terrainAdaptation();
//    }
//
//    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/StructureStart;getStructure()Lnet/minecraft/world/level/levelgen/structure/Structure;"), method = "forStructuresInChunk", cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
//    private static void valhelsia_forStructuresInChunk(StructureManager structureManager, ChunkPos chunkPos, CallbackInfoReturnable<Beardifier> cir, List list, int i, int j, List list1, List list2, BoundingBox boundingbox, Iterator var8, StructureStart structurestart, ChunkPos chunkPos2, ObjectList<Beardifier.Rigid> rigids, int minX, int minZ, ObjectList<JigsawJunction> junctions, StructureStart start) {
//        if (start.getStructure() instanceof ValhelsiaJigsawStructure valhelsiaJigsawStructure && valhelsiaJigsawStructure.hasIndividualTerrainAdjustment()) {
//            TerrainAdjustment terrainAdjustment = start.getStructure().terrainAdaptation();
//
//            for(StructurePiece structurePiece : start.getPieces()) {
//                if (structurePiece.isCloseToChunk(chunkPos, 12)) {
//                    if (structurePiece instanceof PoolElementStructurePiece piece) {
//                        StructureTemplatePool.Projection projection = piece.getElement().getProjection();
//                        if (projection == StructureTemplatePool.Projection.RIGID) {
//                            terrainAdjustment = (piece.getElement() instanceof ValhelsiaPoolElementWrapper wrapper && wrapper.getTerrainAdjustment() != null) ? wrapper.getTerrainAdjustment() : terrainAdjustment;
//                            rigids.add(new Beardifier.Rigid(piece.getBoundingBox(), terrainAdjustment, piece.getGroundLevelDelta()));
//                        }
//
//                        for(JigsawJunction jigsawjunction : piece.getJunctions()) {
//                            int k = jigsawjunction.getSourceX();
//                            int l = jigsawjunction.getSourceZ();
//                            if (k > minX - 12 && l > minZ - 12 && k < minX + 15 + 12 && l < minZ + 15 + 12) {
//                                junctions.add(jigsawjunction);
//                            }
//                        }
//                    } else {
//                        rigids.add(new Beardifier.Rigid(structurePiece.getBoundingBox(), terrainAdjustment, 0));
//                    }
//                }
//            }
//
//            cir.cancel();
//        }
//    }
}
