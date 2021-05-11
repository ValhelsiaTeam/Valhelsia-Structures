package com.stal111.valhelsia_structures.init;

import com.google.common.collect.ImmutableList;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.StructureType;
import com.stal111.valhelsia_structures.world.structures.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Structures
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModStructures
 *
 * @author Valhelsia Team
 * @version 16.1.0
 */
public class ModStructures {

    public static final Map<StructureType, List<AbstractValhelsiaStructure>> STRUCTURES_MAP = new HashMap<>();

    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<CastleStructure> CASTLE = registerSurfaceStructure(new CastleStructure(VillageConfig.field_236533_a_));
    public static final RegistryObject<CastleRuinStructure> CASTLE_RUIN = registerSurfaceStructure(new CastleRuinStructure(VillageConfig.field_236533_a_));
    public static final RegistryObject<DesertHouseStructure> DESERT_HOUSE = registerSurfaceStructure(new DesertHouseStructure(VillageConfig.field_236533_a_));
    public static final RegistryObject<ForgeStructure> FORGE = registerSurfaceStructure(new ForgeStructure(VillageConfig.field_236533_a_));
    public static final RegistryObject<PlayerHouseStructure> PLAYER_HOUSE = registerSurfaceStructure(new PlayerHouseStructure(VillageConfig.field_236533_a_));
    public static final RegistryObject<SpawnerDungeonStructure> SPAWNER_DUNGEON = registerUndergroundStructure(new SpawnerDungeonStructure(VillageConfig.field_236533_a_));
    public static final RegistryObject<TowerRuinStructure> TOWER_RUIN = registerSurfaceStructure(new TowerRuinStructure(VillageConfig.field_236533_a_));
    public static final RegistryObject<WitchHutStructure> WITCH_HUT = registerSurfaceStructure(new WitchHutStructure(VillageConfig.field_236533_a_));

    // Removed Structures - these prevent crashes related to a vanilla bug.
    public static final RegistryObject<RemovedStructure> SMALL_CASTLE = registerSurfaceStructure(new RemovedStructure(VillageConfig.field_236533_a_, "small_castle"));
    public static final RegistryObject<RemovedStructure> SMALL_DUNGEON = registerUndergroundStructure(new RemovedStructure(VillageConfig.field_236533_a_, "small_dungeon"));

    private static <T extends AbstractValhelsiaStructure> RegistryObject<T> registerSurfaceStructure(T structure) {
        return register(structure.getName(), structure, GenerationStage.Decoration.SURFACE_STRUCTURES);
    }

    private static <T extends AbstractValhelsiaStructure> RegistryObject<T> registerUndergroundStructure(T structure) {
        return register(structure.getName(), structure, GenerationStage.Decoration.UNDERGROUND_STRUCTURES);
    }

    private static <T extends AbstractValhelsiaStructure> RegistryObject<T> register(String name, T structure, GenerationStage.Decoration decoration) {
        Structure.NAME_STRUCTURE_BIMAP.put(ValhelsiaStructures.MOD_ID + ":" + name, structure);
        Structure.STRUCTURE_DECORATION_STAGE_MAP.put(structure, decoration);

        if (decoration != GenerationStage.Decoration.UNDERGROUND_STRUCTURES) {
            Structure.field_236384_t_ = ImmutableList.<Structure<?>>builder().addAll(Structure.field_236384_t_).add(structure).build();
        }

        STRUCTURES_MAP.computeIfAbsent(structure.getStructureType(), k -> new ArrayList<>());
        STRUCTURES_MAP.get(structure.getStructureType()).add(structure);

        return STRUCTURES.register(name, () -> structure);
    }
}
