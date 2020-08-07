package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.world.structures.pieces.TowerRuinPieces;
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
 * Tower Ruin Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structure.world.structures.TowerRuinStructure
 *
 * @author Valhelsia Team
 * @version 16.0.2
 * @since 2019-10-31
 */

public class TowerRuinStructure extends AbstractValhelsiaStructure<NoFeatureConfig> {

    public TowerRuinStructure(Codec<NoFeatureConfig> noFeatureConfigCodec) {
        super(noFeatureConfigCodec, "tower_ruin", 1);
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
            TowerRuinPieces.generate(generator, templateManager, pos, this.components, this.rand);
        }
    }
}