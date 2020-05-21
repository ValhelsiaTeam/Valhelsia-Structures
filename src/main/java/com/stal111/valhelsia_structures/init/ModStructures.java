package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.world.structures.*;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModStructures {

    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<Structure<NoFeatureConfig>> CASTLE_RUIN = register("castle_ruin", new CastleRuinStructure(NoFeatureConfig::deserialize));
    public static final RegistryObject<Structure<NoFeatureConfig>> TOWER_RUIN = register("tower_ruin", new TowerRuinStructure(NoFeatureConfig::deserialize));
    public static final RegistryObject<Structure<NoFeatureConfig>> PLAYER_HOUSE = register("player_house", new PlayerHouseStructure(NoFeatureConfig::deserialize));
    public static final RegistryObject<Structure<NoFeatureConfig>> FORGE = register("forge", new ForgeStructure(NoFeatureConfig::deserialize));
    public static final RegistryObject<Structure<NoFeatureConfig>> SMALL_DUNGEON = register("small_dungeon", new SmallDungeonStructure(NoFeatureConfig::deserialize));

    private static <T extends Feature<?>> RegistryObject<T> register(String name, T feature) {
        return FEATURES.register(name, () -> feature);
    }
}
