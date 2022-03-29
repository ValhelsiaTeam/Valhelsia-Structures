package com.stal111.valhelsia_structures.common.world.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.init.ModStructureFeatures;
import com.stal111.valhelsia_structures.utils.ConfigurableValue;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Witch Hut Structure <br>
 * Valhelsia-Structures - com.stal111.valhelsia_structures.common.world.structures.WitchHutStructure
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.1.0
 * @since 2021-04-23
 */
public class WitchHutStructure extends AbstractValhelsiaStructure {

    private static final List<MobSpawnSettings.SpawnerData> MONSTER_SPAWN_LIST = ImmutableList.of(new MobSpawnSettings.SpawnerData(EntityType.WITCH, 100, 1, 1));
    private static final List<MobSpawnSettings.SpawnerData> CREATURE_SPAWN_LIST = ImmutableList.of(new MobSpawnSettings.SpawnerData(EntityType.CAT, 100, 1, 1));

    public WitchHutStructure(Codec<JigsawConfiguration> villageConfigCodec, Predicate<PieceGeneratorSupplier.Context<JigsawConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<JigsawConfiguration>, Optional<PieceGenerator<JigsawConfiguration>>> pieceCreationPredicate) {
        super(villageConfigCodec, "witch_hut",
                locationCheckPredicate,
                pieceCreationPredicate,
                new StructureSettings(ConfigurableValue.of(0.85D), ConfigurableValue.of(23), ConfigurableValue.of(6), ModTags.Biomes.HAS_WITCH_HUT, Map.of(
                        MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, WeightedRandomList.create(MONSTER_SPAWN_LIST)),
                        MobCategory.CREATURE, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, WeightedRandomList.create(CREATURE_SPAWN_LIST))
                ))
        );
    }

    public static WitchHutStructure create(Codec<JigsawConfiguration> codec) {
        MutableObject<WitchHutStructure> box = new MutableObject<>();

        WitchHutStructure finalInstance = new WitchHutStructure(
                codec,
                context -> box.getValue().isFeatureChunk(context),
                context -> box.getValue().generatePieces(context)
        );

        box.setValue(finalInstance);
        return finalInstance;
    }

    @Override
    public Pair<Integer, Integer> getSize() {
        return Pair.of(13, 12);
    }

    @Override
    public int getSeedModifier() {
        return 70882951;
    }

    @Override
    public Holder<ConfiguredStructureFeature<?, ?>> getStructureFeature() {
        return ModStructureFeatures.WITCH_HUT;
    }

    @Override
    public int getMargin() {
        return 3;
    }

    @Override
    public boolean canGenerateOnWater() {
        return true;
    }
}