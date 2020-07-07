package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.utils.StructureUtils;
import com.stal111.valhelsia_structures.world.structures.pieces.CastlePieces;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nonnull;

/**
 * Castle Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.CastleStructure
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2020-05-27
 */

public class CastleStructure extends AbstractValhelsiaStructure {
    public static final String SHORT_NAME = "castle";

    public CastleStructure(Codec<NoFeatureConfig> noFeatureConfigCodec) {
        super(noFeatureConfigCodec, SHORT_NAME, 3);
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

    @Override
    @Nonnull
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    public static class Start extends MarginedStructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox bounds, int reference, long seed) {
            super(structure, chunkX, chunkZ, bounds, reference, seed);
        }

        @Override
        public void func_230364_a_(ChunkGenerator generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig noFeatureConfig) {
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            BlockPos position = StructureUtils.getSurfaceStructurePosition(generator, 3, rotation, chunkX, chunkZ);
            CastlePieces.generate(generator, templateManager, position, this.components, this.rand);
            this.recalculateStructureSize();
        }
    }
}
