package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
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
 * Removed Structure
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.RemovedStructure
 *
 * Represents a structure that has been removed from the mod. This is to work around a vanilla bug without having
 * to directly modify vanilla code.
 *
 * @author Valhelsia Team
 * @version 15.0.3a
 * @since 2020-06-05
 */
public class RemovedStructure extends AbstractValhelsiaStructure {

    public RemovedStructure(Codec<NoFeatureConfig> noFeatureConfigCodec, String name) {
        super(noFeatureConfigCodec, name, 2);
    }

    @Override
    public int getSeparation() {
        return 8;
    }

    @Override
    public int getDistance() {
        return 35;
    }

    @Override
    public int getSeedModifier() {
        return 1666666;
    }

    @Override
    public double getSpawnChance() {
        return 0;
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
        }
    }
}
