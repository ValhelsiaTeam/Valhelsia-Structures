package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.world.structures.*;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Structures
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModStructures
 *
 * @author Valhelsia Team
 * @version 15.0.3
 */
public class ModStructures {
    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<Structure<NoFeatureConfig>> CASTLE = register(CastleStructure.SHORT_NAME, new CastleStructure(NoFeatureConfig::deserialize));
    public static final RegistryObject<Structure<NoFeatureConfig>> CASTLE_RUIN = register(CastleRuinStructure.SHORT_NAME, new CastleRuinStructure(NoFeatureConfig::deserialize));
    public static final RegistryObject<Structure<NoFeatureConfig>> DESERT_HOUSE = register(DesertHouseStructure.SHORT_NAME, new DesertHouseStructure(NoFeatureConfig::deserialize));
    public static final RegistryObject<Structure<NoFeatureConfig>> FORGE = register(ForgeStructure.SHORT_NAME, new ForgeStructure(NoFeatureConfig::deserialize));
    public static final RegistryObject<Structure<NoFeatureConfig>> PLAYER_HOUSE = register(PlayerHouseStructure.SHORT_NAME, new PlayerHouseStructure(NoFeatureConfig::deserialize));
    public static final RegistryObject<Structure<NoFeatureConfig>> SMALL_DUNGEON = register(SmallDungeonStructure.SHORT_NAME, new SmallDungeonStructure(NoFeatureConfig::deserialize));
    public static final RegistryObject<Structure<NoFeatureConfig>> TOWER_RUIN = register(TowerRuinStructure.SHORT_NAME, new TowerRuinStructure(NoFeatureConfig::deserialize));

    // Removed Structures - these prevent crashes related to a vanilla bug.
    public static final RegistryObject<Structure<NoFeatureConfig>> SMALL_CASTLE = register("small_castle", new RemovedStructure(NoFeatureConfig::deserialize, "small_castle"));

    private static <T extends Feature<?>> RegistryObject<T> register(String name, T feature) {
        return FEATURES.register(name, () -> feature);
    }
}
