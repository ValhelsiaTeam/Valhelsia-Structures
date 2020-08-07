package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.StructureType;
import com.stal111.valhelsia_structures.world.structures.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
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
 * @version 16.0.2
 */
public class ModStructures {

    public static final Map<StructureType, List<AbstractValhelsiaStructure<?>>> STRUCTURES_MAP = new HashMap<>();

    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<AbstractValhelsiaStructure<NoFeatureConfig>> CASTLE = registerSurfaceStructure(new CastleStructure(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<AbstractValhelsiaStructure<NoFeatureConfig>> CASTLE_RUIN = registerSurfaceStructure(new CastleRuinStructure(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<AbstractValhelsiaStructure<NoFeatureConfig>> DESERT_HOUSE = registerSurfaceStructure(new DesertHouseStructure(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<AbstractValhelsiaStructure<NoFeatureConfig>> FORGE = registerSurfaceStructure(new ForgeStructure(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<AbstractValhelsiaStructure<NoFeatureConfig>> PLAYER_HOUSE = registerSurfaceStructure(new PlayerHouseStructure(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<AbstractValhelsiaStructure<NoFeatureConfig>> SMALL_DUNGEON = registerUndergroundStructure(new SmallDungeonStructure(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<AbstractValhelsiaStructure<NoFeatureConfig>> TOWER_RUIN = registerSurfaceStructure(new TowerRuinStructure(NoFeatureConfig.field_236558_a_));

    // Removed Structures - these prevent crashes related to a vanilla bug.
    public static final RegistryObject<AbstractValhelsiaStructure<NoFeatureConfig>> SMALL_CASTLE = registerSurfaceStructure(new RemovedStructure(NoFeatureConfig.field_236558_a_, "small_castle"));

    private static <T extends AbstractValhelsiaStructure<?>> RegistryObject<T> registerSurfaceStructure(T structure) {
        return register(structure.getName(), structure, GenerationStage.Decoration.SURFACE_STRUCTURES);
    }

    private static <T extends AbstractValhelsiaStructure<?>> RegistryObject<T> registerUndergroundStructure(T structure) {
        return register(structure.getName(), structure, GenerationStage.Decoration.UNDERGROUND_STRUCTURES);
    }

    private static <T extends AbstractValhelsiaStructure<?>> RegistryObject<T> register(String name, T structure, GenerationStage.Decoration decoration) {
        Structure.field_236365_a_.put(ValhelsiaStructures.MOD_ID + ":" + name, structure);
        Structure.field_236385_u_.put(structure, decoration);

        STRUCTURES_MAP.computeIfAbsent(structure.getStructureType(), k -> new ArrayList<>());
        STRUCTURES_MAP.get(structure.getStructureType()).add(structure);

        return STRUCTURES.register(name, () -> structure);
    }
}
