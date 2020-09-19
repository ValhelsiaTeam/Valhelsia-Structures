package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import net.minecraft.world.gen.feature.structure.VillageConfig;

/**
 * Castle Ruin Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.CastleRuinStructure
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2020-05-27
 */

public class CastleRuinStructure extends AbstractValhelsiaStructure {

    public CastleRuinStructure(Codec<VillageConfig> villageConfigCodec) {
        super(villageConfigCodec, "castle_ruin", 2);
    }

    @Override
    public int getSeparation() {
        return StructureGenConfig.CASTLE_RUIN_SEPARATION.get();
    }

    @Override
    public int getDistance() {
        return StructureGenConfig.CASTLE_RUIN_DISTANCE.get();
    }

    @Override
    public int getSeedModifier() {
        return 14357618;
    }

    @Override
    public double getSpawnChance() {
        return StructureGenConfig.CASTLE_RUIN_SPAWN_CHANCE.get();
    }

//    @Override
//    @Nonnull
//    public IStartFactory<NoFeatureConfig> getStartFactory() {
//        return Start::new;
//    }
//
//    public static class Start extends ValhelsiaStructureStart<NoFeatureConfig> {
//
//        public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox bounds, int reference, long seed) {
//            super(structure, chunkX, chunkZ, bounds, reference, seed);
//        }
//
//        @Override
//        void generate(ChunkGenerator generator, TemplateManager templateManager, BlockPos pos, List<StructurePiece> components, Random random) {
//            CastleRuinPieces.generate(generator, templateManager, pos, this.components, this.rand);
//        }
//    }
}
