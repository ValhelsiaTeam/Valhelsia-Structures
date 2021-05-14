package com.stal111.valhelsia_structures.world.structures;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.utils.StructureType;
import com.stal111.valhelsia_structures.utils.StructureUtils;
import com.stal111.valhelsia_structures.world.structures.start.ValhelsiaStructureStart;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Abstract Valhelsia Structure
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure
 * <p>
 * Serves as a way to reduce duplicate code in structures - this has sensible defaults for most surface structures
 * but can be overridden if needed.
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-03-22
 */
public abstract class AbstractValhelsiaStructure extends JigsawStructure {

    private final String name;
    private final int size;

    private final StructureConfigEntry structureConfigEntry;

    public AbstractValhelsiaStructure(Codec<VillageConfig> codec, String name, int size, StructureConfigEntry structureConfigEntry) {
        super(codec, 0, true, true);
        this.name = name;
        this.size = size;
        this.structureConfigEntry = structureConfigEntry;
    }

    @Override
    protected boolean func_230363_a_(@Nonnull ChunkGenerator generator, @Nonnull BiomeProvider provider, long seed, @Nonnull SharedSeedRandom rand, int chunkX, int chunkZ, @Nonnull Biome biome, @Nonnull ChunkPos pos, @Nonnull VillageConfig config) {
        if (this.checkSurface() && !this.isSurfaceFlat(generator, chunkX, chunkZ)) {
            return false;
        }

        // Check the entire size of the structure to see if it's all a viable biome & check for blacklisted biomes
        for (Biome biome1 : provider.getBiomes(chunkX * 16 + 9, generator.getSeaLevel(), chunkZ * 16 + 9, getSize() * 16 / 2)) {
            if (biome1.getRegistryName() != null) {
                if (!biome1.getGenerationSettings().hasStructure(this) || StructureGenConfig.BLACKLISTED_BIOMES.get().contains(Objects.requireNonNull(biome1.getRegistryName()).toString())) {
                    return false;
                }
            }
        }

        int i = chunkX >> 4;
        int j = chunkZ >> 4;
        rand.setSeed((long) (i ^ j << 4) ^ seed);
        rand.nextInt();

        // Check for other structures
        List<Structure<?>> structures = new ArrayList<>(ModStructures.STRUCTURES_MAP.get(getStructureType()));

        if (getStructureType() == StructureType.SURFACE) {
            structures.add(Structure.VILLAGE);
        } else if (getStructureType() == StructureType.UNDERGROUND) {
            structures.addAll(Arrays.asList(Structure.MINESHAFT, Structure.STRONGHOLD));
        }

        if (!StructureUtils.checkForOtherStructures(this, generator, seed, rand, chunkX, chunkZ, structures)) {
            return false;
        }

        return rand.nextDouble() < getSpawnChance();

    }

    @Override
    @Nonnull
    public String getStructureName() {
        return new ResourceLocation(ValhelsiaStructures.MOD_ID, name).toString();
    }

    protected boolean isSurfaceFlat(@Nonnull ChunkGenerator generator, int chunkX, int chunkZ) {
        // Size of the area to check.
        int offset = getSize() * 16 / 2;

        int xStart = (chunkX << 4);
        int zStart = (chunkZ << 4);

        int i1 = generator.getNoiseHeightMinusOne(xStart, zStart, Heightmap.Type.WORLD_SURFACE_WG);
        int j1 = generator.getNoiseHeightMinusOne(xStart, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int k1 = generator.getNoiseHeightMinusOne(xStart + offset, zStart, Heightmap.Type.WORLD_SURFACE_WG);
        int l1 = generator.getNoiseHeightMinusOne(xStart + offset, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
        int minHeight = Math.min(Math.min(i1, j1), Math.min(k1, l1));
        int maxHeight = Math.max(Math.max(i1, j1), Math.max(k1, l1));

        return Math.abs(maxHeight - minHeight) <= StructureGenConfig.FLATNESS_DELTA.get();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public StructureConfigEntry getStructureConfigEntry() {
        return structureConfigEntry;
    }

    public int getSpacing() {
        return Math.max(getStructureConfigEntry().configuredSpacing.get(), getSeparation() + 1);
    }

    public int getSeparation() {
        return getStructureConfigEntry().configuredSeparation.get();
    }

    public abstract int getSeedModifier();

    public double getSpawnChance() {
        return getStructureConfigEntry().configuredSpawnChance.get();
    }

    public StructureType getStructureType() {
        return StructureType.SURFACE;
    }

    public abstract StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> getStructureFeature();

    public boolean checkSurface() {
        return true;
    }

    @Nullable
    @Override
    public BlockPos func_236388_a_(@Nonnull IWorldReader world, @Nonnull StructureManager structureManager, @Nonnull BlockPos startPos, int searchRadius, boolean skipExistingChunks, long seed, @Nonnull StructureSeparationSettings settings) {
        return super.func_236388_a_(world, structureManager, startPos, searchRadius, skipExistingChunks, seed, new StructureSeparationSettings(this.getSpacing(), this.getSeparation(), this.getSeedModifier()));
    }

    public boolean hasMargin() {
        return true;
    }

    public int getMargin() {
        return 12;
    }

    @Override
    @Nonnull
    public IStartFactory<VillageConfig> getStartFactory() {
        return AbstractValhelsiaStructure.Start::new;
    }

    public int getGenerationHeight() {
        return -1;
    }

    public static class Start extends ValhelsiaStructureStart<VillageConfig> {
        private final Structure<VillageConfig> structure;
        private final int height;

        public Start(Structure<VillageConfig> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int reference, long seed) {
            this(structure, chunkX, chunkZ, boundingBox, reference, seed, ((AbstractValhelsiaStructure) structure).getGenerationHeight());
        }

        public Start(Structure<VillageConfig> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int reference, long seed, int height) {
            super(structure, chunkX, chunkZ, boundingBox, reference, seed);
            this.structure = structure;
            this.height = height;
        }

        @Override
        public void func_230364_a_(@Nonnull DynamicRegistries registries, @Nonnull ChunkGenerator generator, @Nonnull TemplateManager manager, int chunkX, int chunkZ, @Nonnull Biome biome, @Nonnull VillageConfig villageConfig) {
            BlockPos blockpos = new BlockPos(chunkX << 4, 0, chunkZ << 4);

            JigsawManager.func_242837_a(registries, villageConfig, AbstractVillagePiece::new, generator, manager, blockpos, this.components, this.rand, false, true);
            this.recalculateStructureSize();

            if (this.height != -1) {
                int offset = this.height - this.bounds.minY;
                this.bounds.offset(0, offset, 0);

                for(StructurePiece structurepiece : this.components) {
                    structurepiece.offset(0, offset, 0);
                }
            }
        }
    }
}