package com.stal111.valhelsia_structures.core.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.stal111.valhelsia_structures.common.world.structures.*;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.common.world.IValhelsiaStructure;

import java.util.*;

/**
 * Structures <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModStructures
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 */
public class ModStructures {

    public static final List<IValhelsiaStructure> MOD_STRUCTURES = new ArrayList<>();

    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<CastleStructure> CASTLE = register(new CastleStructure(JigsawConfiguration.CODEC));
    public static final RegistryObject<CastleRuinStructure> CASTLE_RUIN = register(new CastleRuinStructure(JigsawConfiguration.CODEC));
    public static final RegistryObject<DesertHouseStructure> DESERT_HOUSE = register(new DesertHouseStructure(JigsawConfiguration.CODEC));
    public static final RegistryObject<ForgeStructure> FORGE = register(new ForgeStructure(JigsawConfiguration.CODEC));
    public static final RegistryObject<PlayerHouseStructure> PLAYER_HOUSE = register(new PlayerHouseStructure(JigsawConfiguration.CODEC));
    public static final RegistryObject<SpawnerDungeonStructure> SPAWNER_DUNGEON = register(new SpawnerDungeonStructure(JigsawConfiguration.CODEC));
    public static final RegistryObject<TowerRuinStructure> TOWER_RUIN = register(new TowerRuinStructure(JigsawConfiguration.CODEC));
    public static final RegistryObject<WitchHutStructure> WITCH_HUT = register(new WitchHutStructure(JigsawConfiguration.CODEC));
    public static final RegistryObject<BigTreeStructure> BIG_TREE = register(new BigTreeStructure(JigsawConfiguration.CODEC));

    private static <T extends AbstractValhelsiaStructure> RegistryObject<T> register(T structure) {
        MOD_STRUCTURES.add(structure);
        return STRUCTURES.register(structure.getName(), () -> structure);
    }

    public static void setupStructures() {
        for (IValhelsiaStructure structure : MOD_STRUCTURES) {
            StructureFeature<?> structureFeature = structure.getStructure();
            StructureFeatureConfiguration featureConfiguration = structure.getFeatureConfiguration();

            StructureFeature.STRUCTURES_REGISTRY.put(Objects.requireNonNull(structureFeature.getRegistryName()).toString(), structureFeature);

            if (structure.transformsSurroundingLand()) {
                StructureFeature.NOISE_AFFECTING_FEATURES =
                        ImmutableList.<StructureFeature<?>>builder()
                                .addAll(StructureFeature.NOISE_AFFECTING_FEATURES)
                                .add(structureFeature)
                                .build();
            }

            StructureSettings.DEFAULTS =
                    ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
                            .putAll(StructureSettings.DEFAULTS)
                            .put(structureFeature, featureConfiguration)
                            .build();

            BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
                Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap = settings.getValue().structureSettings().structureConfig();

                if (structureMap instanceof ImmutableMap) {
                    Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureMap);
                    tempMap.put(structureFeature, featureConfiguration);
                    settings.getValue().structureSettings().structureConfig = tempMap;
                } else {
                    structureMap.put(structureFeature, featureConfiguration);
                }
            });
        }
    }
}
