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
 * Desert House Structure <br>
 * Valhelsia-Structures - com.stal111.valhelsia_structures.common.world.structures.DesertHouseStructure
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.1.0
 * @since 2020-05-27
 */

public class DesertHouseStructure extends AbstractValhelsiaStructure {

    public DesertHouseStructure(Codec<JigsawConfiguration> villageConfigCodec, Predicate<PieceGeneratorSupplier.Context<JigsawConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<JigsawConfiguration>, Optional<PieceGenerator<JigsawConfiguration>>> pieceCreationPredicate) {
        super(villageConfigCodec, "desert_house",
                locationCheckPredicate,
                pieceCreationPredicate,
                new StructureSettings(ConfigurableValue.of(0.7D), ConfigurableValue.of(28), ConfigurableValue.of(7), ModTags.Biomes.HAS_DESERT_HOUSE));
    }

    public static DesertHouseStructure create(Codec<JigsawConfiguration> codec) {
        MutableObject<DesertHouseStructure> box = new MutableObject<>();

        DesertHouseStructure finalInstance = new DesertHouseStructure(
                codec,
                context -> box.getValue().isFeatureChunk(context),
                context -> box.getValue().generatePieces(context)
        );

        box.setValue(finalInstance);
        return finalInstance;
    }

    @Override
    public Pair<Integer, Integer> getSize() {
        return Pair.of(20, 19);
    }

    @Override
    public int getSeedModifier() {
        return 572792859;
    }

    @Override
    public Holder<ConfiguredStructureFeature<?, ?>> getStructureFeature() {
        return ModStructureFeatures.DESERT_HOUSE;
    }
}
