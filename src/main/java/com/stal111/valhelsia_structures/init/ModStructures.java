package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.world.structures.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

/**
 * Structures
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModStructures
 *
 * @author Valhelsia Team
 * @version 15.0.3
 */
public class ModStructures {

    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<Structure<NoFeatureConfig>> CASTLE = register(CastleStructure.SHORT_NAME, new CastleStructure(NoFeatureConfig.field_236558_a_), GenerationStage.Decoration.SURFACE_STRUCTURES);
    public static final RegistryObject<Structure<NoFeatureConfig>> CASTLE_RUIN = register(CastleRuinStructure.SHORT_NAME, new CastleRuinStructure(NoFeatureConfig.field_236558_a_), GenerationStage.Decoration.SURFACE_STRUCTURES);
    public static final RegistryObject<Structure<NoFeatureConfig>> DESERT_HOUSE = register(DesertHouseStructure.SHORT_NAME, new DesertHouseStructure(NoFeatureConfig.field_236558_a_), GenerationStage.Decoration.SURFACE_STRUCTURES);
    public static final RegistryObject<Structure<NoFeatureConfig>> FORGE = register(ForgeStructure.SHORT_NAME, new ForgeStructure(NoFeatureConfig.field_236558_a_), GenerationStage.Decoration.SURFACE_STRUCTURES);
    public static final RegistryObject<Structure<NoFeatureConfig>> PLAYER_HOUSE = register(PlayerHouseStructure.SHORT_NAME, new PlayerHouseStructure(NoFeatureConfig.field_236558_a_), GenerationStage.Decoration.SURFACE_STRUCTURES);
    public static final RegistryObject<Structure<NoFeatureConfig>> SMALL_DUNGEON = register(SmallDungeonStructure.SHORT_NAME, new SmallDungeonStructure(NoFeatureConfig.field_236558_a_), GenerationStage.Decoration.UNDERGROUND_STRUCTURES);
    public static final RegistryObject<Structure<NoFeatureConfig>> TOWER_RUIN = register(TowerRuinStructure.SHORT_NAME, new TowerRuinStructure(NoFeatureConfig.field_236558_a_), GenerationStage.Decoration.SURFACE_STRUCTURES);

    // Removed Structures - these prevent crashes related to a vanilla bug.
    public static final RegistryObject<Structure<NoFeatureConfig>> SMALL_CASTLE = register("small_castle", new RemovedStructure(NoFeatureConfig.field_236558_a_, "small_castle"), GenerationStage.Decoration.SURFACE_STRUCTURES);

    private static <T extends Structure<?>> RegistryObject<T> register(String name, T structure, GenerationStage.Decoration decoration) {
        Structure.field_236365_a_.put(name.toLowerCase(Locale.ROOT), structure);
        Structure.field_236385_u_.put(structure, decoration);
        return STRUCTURES.register(name, () -> structure);
    }
}
