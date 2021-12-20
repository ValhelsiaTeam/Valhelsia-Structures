package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.config.StructureConfigEntry;
import com.stal111.valhelsia_structures.core.init.ModStructureFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Castle Structure <br>
 * Valhelsia-Structures - com.stal111.valhelsia_structures.common.world.structures.CastleStructure
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2020-05-27
 */

public class CastleStructure extends AbstractValhelsiaStructure {

    public CastleStructure(Codec<JigsawConfiguration> villageConfigCodec, Predicate<PieceGeneratorSupplier.Context<JigsawConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<JigsawConfiguration>, Optional<PieceGenerator<JigsawConfiguration>>> pieceCreationPredicate) {
        super(villageConfigCodec, "castle",
                locationCheckPredicate,
                pieceCreationPredicate,
                new StructureConfigEntry(0.5D, 40, 8,
                        Biome.BiomeCategory.PLAINS.getName(),
                        Biome.BiomeCategory.FOREST.getName(),
                        Biome.BiomeCategory.TAIGA.getName()
                ));
    }

    public static CastleStructure create(Codec<JigsawConfiguration> codec) {
        MutableObject<CastleStructure> box = new MutableObject<>();

        CastleStructure finalInstance = new CastleStructure(
                codec,
                context -> box.getValue().isFeatureChunk(context),
                context -> box.getValue().generatePieces(context)
        );

        box.setValue(finalInstance);
        return finalInstance;
    }

    @Override
    public Pair<Integer, Integer> getSize() {
        return Pair.of(32, 32);
    }

    @Override
    public int getSeedModifier() {
        return 16987356;
    }

    @Override
    public ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> getStructureFeature() {
        return ModStructureFeatures.CASTLE;
    }
}
