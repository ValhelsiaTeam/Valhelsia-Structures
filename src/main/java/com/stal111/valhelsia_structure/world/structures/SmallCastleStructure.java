package com.stal111.valhelsia_structure.world.structures;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Function;

/**
 * Small Castle Structure
 * ValhelsiaStructure - com.stal111.valhelsia_structure.world.structures.SmallCastleStructure
 *
 * @author Valhelsia Team
 * @version 0.1
 * @since 2019-10-31
 */
public class SmallCastleStructure extends ScatteredStructure<NoFeatureConfig> {

    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("WeakerAccess")
    public static final String NAME = "valhelsia_structure:small_castle";
    private static final int CHUNK_RADIUS = 2;
    private static final int FEATURE_DISTANCE = 16;
    private static final int FEATURE_SEPARATION = 8;

    public SmallCastleStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> deserialize) {
        super(deserialize);
    }

    @Override
    protected int getSeedModifier() {
        return 23183018;
    }

    @Override @Nonnull
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    @Override @Nonnull
    public String getStructureName() {
        return NAME;
    }

    @Override
    public int getSize() {
        return CHUNK_RADIUS;
    }
    @Override
    protected int getBiomeFeatureDistance(ChunkGenerator<?> generator) {
        return FEATURE_DISTANCE;
    }

    @Override
    protected int getBiomeFeatureSeparation(ChunkGenerator<?> generator) {
        return FEATURE_SEPARATION;
    }

    @Override
    public boolean hasStartAt(@Nonnull ChunkGenerator<?> generator, @Nonnull Random random, int chunkX, int chunkZ) {
        ChunkPos startPos = this.getStartPositionForPosition(generator, random, chunkX, chunkZ, 0, 0);

        // Might want to avoid cutting into villages - can use the Pillager Outpost Structure as a basis.

        if (chunkX == startPos.x && chunkZ == startPos.z) {
            BiomeProvider biomeProvider = generator.getBiomeProvider();
            return biomeProvider.getBiomesInSquare((chunkX << 4) + 9, (chunkZ << 4) + 9, CHUNK_RADIUS * 16)
                    .stream()
                    .allMatch(biome -> generator.hasStructure(biome, this));
        }

        return false;
    }


    public static class Start extends StructureStart {
        @SuppressWarnings("WeakerAccess")
        public Start(Structure<?> structure, int chunkX, int chunkZ, Biome biome, MutableBoundingBox bounds, int reference, long seed) {
            super(structure, chunkX, chunkZ, biome, bounds, reference, seed);
        }

        @Override
        public void init(@Nonnull ChunkGenerator<?> generator, @Nonnull TemplateManager templateManager, int chunkX, int chunkZ, @Nonnull Biome biome) {
            int i = chunkX * 16;
            int j = chunkZ * 16;
            BlockPos blockpos = new BlockPos(i, 90, j);
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            SmallCastlePiece.addCastlePieces(generator, templateManager, blockpos, rotation, this.components);
            this.recalculateStructureSize();

        }
    }
}
