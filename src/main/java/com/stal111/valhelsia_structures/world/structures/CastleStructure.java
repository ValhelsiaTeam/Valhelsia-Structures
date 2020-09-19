package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import net.minecraft.world.gen.feature.structure.VillageConfig;

/**
 * Castle Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.CastleStructure
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2020-05-27
 */

public class CastleStructure extends AbstractValhelsiaStructure {

    public CastleStructure(Codec<VillageConfig> villageConfigCodec) {
        super(villageConfigCodec, "castle", 3);
    }

    @Override
    public int getSeparation() {
        return StructureGenConfig.CASTLE_SEPARATION.get();
    }

    @Override
    public int getDistance() {
        return StructureGenConfig.CASTLE_DISTANCE.get();
    }

    @Override
    public int getSeedModifier() {
        return 16987356;
    }

    @Override
    public double getSpawnChance() {
        return StructureGenConfig.CASTLE_SPAWN_CHANCE.get();
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
//            CastlePieces.generate(generator, templateManager, pos, this.components, this.rand);
//        }
//    }
}
