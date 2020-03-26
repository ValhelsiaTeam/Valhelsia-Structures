package com.stal111.valhelsia_structures.world.structures;

import com.mojang.datafixers.Dynamic;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Function;

/**
 * Small Dungeon Structure
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.SmallDungeonStructure
 *
 * @author Valhelsia Team
 * @version 14.0.3
 * @since 2020-03-22
 */
public class SmallDungeonStructure extends AbstractValhelsiaStructure {
    public static final String SHORT_NAME = "small_dungeon";
    public static final String FULL_NAME = ValhelsiaStructures.MOD_ID + ":" + SHORT_NAME;

    private static final int CHUNK_RADIUS = 3;
    private static final int FEATURE_DISTANCE = 35;
    private static final int FEATURE_SEPARATION = 8;
    private static final int SEED_MODIFIER = 14357670;

    public SmallDungeonStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> deserialize) {
        super(deserialize);
    }

    @Override
    protected int getSeedModifier() {
        return SEED_MODIFIER;
    }

    @Override
    public @Nonnull IStartFactory getStartFactory() {
        return Start::new;
    }

    @Override
    public @Nonnull String getStructureName() {
        return FULL_NAME;
    }

    @Override
    public int getSize() {
        return CHUNK_RADIUS;
    }

    @Override
    protected int getFeatureDistance(ChunkGenerator<?> generator) {
        return FEATURE_DISTANCE;
    }

    @Override
    protected int getFeatureSeparation(ChunkGenerator<?> generator) {
        return FEATURE_SEPARATION;
    }

    @Override
    public boolean hasStartAt(@Nonnull ChunkGenerator<?> generator, @Nonnull Random random, int chunkX, int chunkZ) {
        // The dungeon only cares that biomes match, not that the surface is level.
        ChunkPos chunkPos = this.getStartPositionForPosition(generator, random, chunkX, chunkZ, 0, 0);
        if (chunkX == chunkPos.x && chunkZ == chunkPos.z) {
            return generator.getBiomeProvider().getBiomesInSquare((chunkX << 4) + 9, (chunkZ << 4) + 9, getSize() * 16)
                    .stream()
                    .allMatch(biome -> generator.hasStructure(biome, this));
        }

        return false;
    }

    public static class Start extends MarginedStructureStart {
        public Start(Structure<?> structure, int chunkX, int chunkY, Biome biome, MutableBoundingBox bounds, int reference, long seed) {
            super(structure, chunkX, chunkY, biome, bounds, reference, seed);
        }

        @Override
        public void init(@Nonnull ChunkGenerator<?> generator, @Nonnull TemplateManager templateManager, int chunkX, int chunkZ, @Nonnull Biome biome) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            SmallDungeonPieces.generate(generator, templateManager, blockpos, this.components, this.rand);
            this.recalculateStructureSize();
            this.func_214628_a(generator.getSeaLevel(), this.rand, 10);
        }
    }
}
