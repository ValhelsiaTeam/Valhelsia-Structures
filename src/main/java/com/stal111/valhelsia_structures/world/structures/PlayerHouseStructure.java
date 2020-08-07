package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.world.structures.pieces.PlayerHousePieces;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

/**
 * Player House Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.PlayerHouseStructure
 *
 * @author Valhelsia Team
 * @version 16.0.2
 * @since 2020-05-27
 */

public class PlayerHouseStructure extends AbstractValhelsiaStructure<NoFeatureConfig> {

    public PlayerHouseStructure(Codec<NoFeatureConfig> noFeatureConfigCodec) {
        super(noFeatureConfigCodec, "player_house", 2);
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

    @Override
    @Nonnull
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    public static class Start extends ValhelsiaStructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox bounds, int reference, long seed) {
            super(structure, chunkX, chunkZ, bounds, reference, seed);
        }

        @Override
        void generate(ChunkGenerator generator, TemplateManager templateManager, BlockPos pos, List<StructurePiece> components, Random random) {
            PlayerHousePieces.generate(generator, templateManager, pos, this.components, this.rand);
        }
    }
}
