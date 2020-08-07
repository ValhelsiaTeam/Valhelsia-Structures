package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.world.structures.pieces.DesertHousePieces;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
 * Desert House Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.DesertHouseStructure
 *
 * @author Valhelsia Team
 * @version 16.0.2
 * @since 2020-05-27
 */

public class DesertHouseStructure extends AbstractValhelsiaStructure<NoFeatureConfig> {

    public DesertHouseStructure(Codec<NoFeatureConfig> noFeatureConfigCodec) {
        super(noFeatureConfigCodec, "desert_house", 2);
    }

    @Override
    public int getSeparation() {
        return StructureGenConfig.DESERT_HOUSE_SEPARATION.get();
    }

    @Override
    public int getDistance() {
        return StructureGenConfig.DESERT_HOUSE_DISTANCE.get();
    }

    @Override
    public int getSeedModifier() {
        return 14862926;
    }

    @Override
    public double getSpawnChance() {
        return StructureGenConfig.DESERT_HOUSE_SPAWN_CHANCE.get();
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
            DesertHousePieces.generate(generator, templateManager, pos, this.components, this.rand);
        }

        @Override
        protected BlockState getTopLayerBlock() {
            return Blocks.SAND.getDefaultState();
        }

        @Override
        protected BlockState getLowerLayerBlock() {
            return Blocks.SANDSTONE.getDefaultState();
        }
    }
}
