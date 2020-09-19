package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import net.minecraft.world.gen.feature.structure.VillageConfig;

/**
 * Tower Ruin Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structure.world.structures.TowerRuinStructure
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2019-10-31
 */

public class TowerRuinStructure extends AbstractValhelsiaStructure {

    public TowerRuinStructure(Codec<VillageConfig> villageConfigCodec) {
        super(villageConfigCodec, "tower_ruin", 1);
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
        return 24357670;
    }

    @Override
    public double getSpawnChance() {
        return StructureGenConfig.TOWER_RUIN_SPAWN_CHANCE.get();
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
//            TowerRuinPieces.generate(generator, templateManager, pos, this.components, this.rand);
//        }
//    }
}