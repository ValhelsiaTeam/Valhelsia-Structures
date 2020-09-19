package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import net.minecraft.world.gen.feature.structure.VillageConfig;

/**
 * Player House Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.PlayerHouseStructure
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2020-05-27
 */

public class PlayerHouseStructure extends AbstractValhelsiaStructure {

    public PlayerHouseStructure(Codec<VillageConfig> villageConfigCodec) {
        super(villageConfigCodec, "player_house", 2);
    }

    @Override
    public int getSeparation() {
        return StructureGenConfig.PLAYER_HOUSE_SEPARATION.get();
    }

    @Override
    public int getDistance() {
        return StructureGenConfig.PLAYER_HOUSE_DISTANCE.get();
    }

    @Override
    public int getSeedModifier() {
        return 17357645;
    }

    @Override
    public double getSpawnChance() {
        return StructureGenConfig.PLAYER_HOUSE_SPAWN_CHANCE.get();
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
//            PlayerHousePieces.generate(generator, templateManager, pos, this.components, this.rand);
//        }
//    }
}
