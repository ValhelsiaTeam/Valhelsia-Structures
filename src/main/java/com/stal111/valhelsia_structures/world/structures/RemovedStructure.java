package com.stal111.valhelsia_structures.world.structures;

import com.mojang.datafixers.Dynamic;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.utils.StructureUtils;
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
import java.util.function.Function;

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

    public RemovedStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory, String name) {
        super(configFactory, name);
    }

    @Override
    protected int getFeatureDistance(ChunkGenerator<?> generator) {
        return 35;
    }

    @Override
    protected int getFeatureSeparation(ChunkGenerator<?> generator) {
        return 8;
    }

    @Override
    protected double getSpawnChance() {
        return 0;
    }

    @Override
    protected int getSeedModifier() {
        return 1666666;
    }

    @Override
    @Nonnull
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    public static class Start extends MarginedStructureStart {

        public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bounds, int reference, long seed) {
            super(structure, chunkX, chunkZ, bounds, reference, seed);
        }

        @Override
        public void init(@Nonnull ChunkGenerator<?> generator, @Nonnull TemplateManager templateManager, int chunkX, int chunkZ, @Nonnull Biome biome) {
            // NO-OP
        }
    }
}
