package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.core.init.ModStructureFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import org.apache.commons.lang3.mutable.MutableObject;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Spawner Dungeon Structure <br>
 * Valhelsia-Structures - com.stal111.valhelsia_structures.common.world.structures.SpawnerDungeonStructure
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2020-05-27
 */
public class SpawnerDungeonStructure extends AbstractValhelsiaStructure {

    public SpawnerDungeonStructure(Codec<JigsawConfiguration> villageConfigCodec, Predicate<PieceGeneratorSupplier.Context<JigsawConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<JigsawConfiguration>, Optional<PieceGenerator<JigsawConfiguration>>> pieceCreationPredicate) {
        super(villageConfigCodec, "spawner_dungeon",
                locationCheckPredicate,
                pieceCreationPredicate,
                new StructureConfigEntry(0.7D, 30, 8,
                        Biome.BiomeCategory.PLAINS.getName(),
                        Biome.BiomeCategory.FOREST.getName(),
                        Biome.BiomeCategory.EXTREME_HILLS.getName(),
                        Biome.BiomeCategory.TAIGA.getName(),
                        Biome.BiomeCategory.DESERT.getName(),
                        Biome.BiomeCategory.MESA.getName(),
                        Biome.BiomeCategory.SAVANNA.getName(),
                        Biome.BiomeCategory.JUNGLE.getName(),
                        Biome.BiomeCategory.ICY.getName(),
                        Biome.BiomeCategory.SWAMP.getName(),
                        Biome.BiomeCategory.MUSHROOM.getName()
                ));
    }

    public static SpawnerDungeonStructure create(Codec<JigsawConfiguration> codec) {
        MutableObject<SpawnerDungeonStructure> box = new MutableObject<>();

        SpawnerDungeonStructure finalInstance = new SpawnerDungeonStructure(
                codec,
                context -> box.getValue().isFeatureChunk(context),
                context -> box.getValue().generatePieces(context)
        );

        box.setValue(finalInstance);
        return finalInstance;
    }

    @Override
    public int getSeedModifier() {
        return 23498567;
    }

    @Override
    public ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> getStructureFeature() {
        return ModStructureFeatures.SPAWNER_DUNGEON;
    }

    @Nonnull
    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
    }

    @Override
    public boolean transformsSurroundingLand() {
        return false;
    }

    @Override
    public Pair<Integer, Integer> getSize() {
        return null;
    }

    @Override
    public int getGenerationHeight() {
        return 10;
    }

    @Override
    public boolean checkSurface() {
        return false;
    }

    @Override
    public boolean hasMargin() {
        return false;
    }
}