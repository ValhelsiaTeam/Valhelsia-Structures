package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.init.ModStructureFeatures;
import com.stal111.valhelsia_structures.utils.ConfigurableValue;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Castle Ruin Structure <br>
 * Valhelsia-Structures - com.stal111.valhelsia_structures.common.world.structures.CastleRuinStructure
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.1.0
 * @since 2020-05-27
 */

public class CastleRuinStructure extends AbstractValhelsiaStructure {

    public CastleRuinStructure(Codec<JigsawConfiguration> villageConfigCodec, Predicate<PieceGeneratorSupplier.Context<JigsawConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<JigsawConfiguration>, Optional<PieceGenerator<JigsawConfiguration>>> pieceCreationPredicate) {
        super(villageConfigCodec, "castle_ruin",
                locationCheckPredicate,
                pieceCreationPredicate,
                new StructureSettings(ConfigurableValue.of(0.6D), ConfigurableValue.of(33), ConfigurableValue.of(7), ModTags.Biomes.HAS_CASTLE_RUIN));
    }

    public static CastleRuinStructure create(Codec<JigsawConfiguration> codec) {
        MutableObject<CastleRuinStructure> box = new MutableObject<>();

        CastleRuinStructure finalInstance = new CastleRuinStructure(
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
        return 436946199;
    }

    @Override
    public Holder<ConfiguredStructureFeature<?, ?>> getStructureFeature() {
        return ModStructureFeatures.CASTLE_RUIN;
    }
}
