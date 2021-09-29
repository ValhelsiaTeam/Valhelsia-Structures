package com.stal111.valhelsia_structures.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.world.structures.*;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.world.IValhelsiaStructure;

import java.util.*;

/**
 * Structures
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModStructures
 *
 * @author Valhelsia Team
 * @version 1.0.2
 */
public class ModStructures {

    public static final List<IValhelsiaStructure> MOD_STRUCTURES = new ArrayList<>();

    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<CastleStructure> CASTLE = register(new CastleStructure(VillageConfig.field_236533_a_));
    public static final RegistryObject<CastleRuinStructure> CASTLE_RUIN = register(new CastleRuinStructure(VillageConfig.field_236533_a_));
    public static final RegistryObject<DesertHouseStructure> DESERT_HOUSE = register(new DesertHouseStructure(VillageConfig.field_236533_a_));
    public static final RegistryObject<ForgeStructure> FORGE = register(new ForgeStructure(VillageConfig.field_236533_a_));
    public static final RegistryObject<PlayerHouseStructure> PLAYER_HOUSE = register(new PlayerHouseStructure(VillageConfig.field_236533_a_));
    public static final RegistryObject<SpawnerDungeonStructure> SPAWNER_DUNGEON = register(new SpawnerDungeonStructure(VillageConfig.field_236533_a_));
    public static final RegistryObject<TowerRuinStructure> TOWER_RUIN = register(new TowerRuinStructure(VillageConfig.field_236533_a_));
    public static final RegistryObject<WitchHutStructure> WITCH_HUT = register(new WitchHutStructure(VillageConfig.field_236533_a_));
    public static final RegistryObject<BigTreeStructure> BIG_TREE = register(new BigTreeStructure(VillageConfig.field_236533_a_));

    private static <T extends AbstractValhelsiaStructure> RegistryObject<T> register(T structure) {
        MOD_STRUCTURES.add(structure);
        return STRUCTURES.register(structure.getName(), () -> structure);
    }

    public static void setupStructures() {
        for (IValhelsiaStructure iStructure : MOD_STRUCTURES) {
            Structure<?> structure = iStructure.getStructure();
            StructureSeparationSettings separationSettings = iStructure.getSeparationSettings();

            Structure.NAME_STRUCTURE_BIMAP.put(Objects.requireNonNull(structure.getRegistryName()).toString(), structure);

            if (iStructure.transformsSurroundingLand()) {
                Structure.field_236384_t_ =
                        ImmutableList.<Structure<?>>builder()
                                .addAll(Structure.field_236384_t_)
                                .add(structure)
                                .build();
            }

            DimensionStructuresSettings.field_236191_b_ =
                    ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                            .putAll(DimensionStructuresSettings.field_236191_b_)
                            .put(structure, separationSettings)
                            .build();

            WorldGenRegistries.NOISE_SETTINGS.getEntries().forEach(settings -> {
                Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().getStructures().func_236195_a_();

                if (structureMap instanceof ImmutableMap) {
                    Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                    tempMap.put(structure, separationSettings);
                    settings.getValue().getStructures().field_236193_d_ = tempMap;
                } else {
                    structureMap.put(structure, separationSettings);
                }
            });
        }
    }
}
