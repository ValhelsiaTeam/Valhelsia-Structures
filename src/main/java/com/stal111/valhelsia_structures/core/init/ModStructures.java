package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.common.world.structures.*;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Structures <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModStructures
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.1.0
 */
public class ModStructures {

    public static final List<AbstractValhelsiaStructure> MOD_STRUCTURES = new ArrayList<>();

    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<CastleStructure> CASTLE = register(CastleStructure.create(JigsawConfiguration.CODEC));
    public static final RegistryObject<CastleRuinStructure> CASTLE_RUIN = register(CastleRuinStructure.create(JigsawConfiguration.CODEC));
    public static final RegistryObject<DesertHouseStructure> DESERT_HOUSE = register(DesertHouseStructure.create(JigsawConfiguration.CODEC));
    public static final RegistryObject<ForgeStructure> FORGE = register(ForgeStructure.create(JigsawConfiguration.CODEC));
    public static final RegistryObject<PlayerHouseStructure> PLAYER_HOUSE = register(PlayerHouseStructure.create(JigsawConfiguration.CODEC));
    public static final RegistryObject<SpawnerDungeonStructure> SPAWNER_DUNGEON = register(SpawnerDungeonStructure.create(JigsawConfiguration.CODEC));
    public static final RegistryObject<TowerRuinStructure> TOWER_RUIN = register(TowerRuinStructure.create(JigsawConfiguration.CODEC));
    public static final RegistryObject<WitchHutStructure> WITCH_HUT = register(WitchHutStructure.create(JigsawConfiguration.CODEC));
    public static final RegistryObject<BigTreeStructure> BIG_TREE = register(BigTreeStructure.create(JigsawConfiguration.CODEC));

    private static <T extends AbstractValhelsiaStructure> RegistryObject<T> register(T structure) {
        MOD_STRUCTURES.add(structure);

        return STRUCTURES.register(structure.getName(), () -> structure);
    }

    public static void setupStructures() {
        for (AbstractValhelsiaStructure structure : MOD_STRUCTURES) {
            BuiltinRegistries.register(BuiltinRegistries.STRUCTURE_SETS, structure.getStructureSetResourceKey(), new StructureSet(List.of(StructureSet.entry(structure.getStructureFeature())), new RandomSpreadStructurePlacement(structure.getSpacing(), structure.getSeparation(), RandomSpreadType.LINEAR, structure.getSeedModifier())));
        }
    }
}
