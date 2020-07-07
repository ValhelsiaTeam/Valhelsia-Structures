package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.utils.StructureUtils;
import com.stal111.valhelsia_structures.world.structures.pieces.DesertHousePieces;
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
 * Desert House Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.DesertHouseStructure
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2020-05-27
 */

public class DesertHouseStructure extends AbstractValhelsiaStructure {
    public static final String SHORT_NAME = "desert_house";

    public DesertHouseStructure(Codec<NoFeatureConfig> noFeatureConfigCodec) {
        super(noFeatureConfigCodec, SHORT_NAME, 2);
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
            BlockPos position = StructureUtils.getSurfaceStructurePosition(generator, 2, rotation, chunkX, chunkZ);
            DesertHousePieces.generate(generator, templateManager, position, this.components, this.rand);
            this.recalculateStructureSize();
        }
    }
}
