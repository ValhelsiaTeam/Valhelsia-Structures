package com.stal111.valhelsia_structures.core.init.world;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.core.registry.RegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.RegistryHelper;

import java.util.List;

/**
 * @author Valhelsia Team
 * @since 2022-06-24
 */
public class ModStructureSets implements RegistryClass {

    public static final RegistryHelper<StructureSet> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registry.STRUCTURE_SET_REGISTRY);

    public static final RegistryObject<StructureSet> CASTLES = HELPER.register("castles", () -> new StructureSet(List.of(StructureSet.entry((Holder<Structure>) ModStructures.CASTLE)), placement(37, 7, 16987356)));
    public static final RegistryObject<StructureSet> CASTLE_RUINS = HELPER.register("castle_ruins", () -> new StructureSet(List.of(StructureSet.entry((Holder<Structure>) ModStructures.CASTLE_RUIN)), placement(33, 7, 436946199)));
    public static final RegistryObject<StructureSet> DESERT_HOUSES = HELPER.register("desert_houses", () -> new StructureSet(List.of(StructureSet.entry((Holder<Structure>) ModStructures.DESERT_HOUSE)), placement(28, 7, 572792859)));
    public static final RegistryObject<StructureSet> FORGES = HELPER.register("forges", () -> new StructureSet(List.of(StructureSet.entry((Holder<Structure>) ModStructures.FORGE)), placement(28, 7, 12857691)));
    public static final RegistryObject<StructureSet> PLAYER_HOUSES = HELPER.register("player_houses", () -> new StructureSet(List.of(StructureSet.entry((Holder<Structure>) ModStructures.PLAYER_HOUSE)), placement(28, 7, 292107367)));
    public static final RegistryObject<StructureSet> SPAWNER_DUNGEONS = HELPER.register("spawner_dungeons", () -> new StructureSet(List.of(StructureSet.entry((Holder<Structure>) ModStructures.SPAWNER_DUNGEON)), placement(28, 7, 23498567)));
    public static final RegistryObject<StructureSet> TOWER_RUINS = HELPER.register("tower_ruins", () -> new StructureSet(List.of(StructureSet.entry((Holder<Structure>) ModStructures.TOWER_RUIN)), placement(23, 7, 24357670)));
    public static final RegistryObject<StructureSet> WITCH_HUTS = HELPER.register("witch_huts", () -> new StructureSet(List.of(StructureSet.entry((Holder<Structure>) ModStructures.WITCH_HUT)), placement(23, 6, 70882951)));
    public static final RegistryObject<StructureSet> BIG_TREES = HELPER.register("big_trees", () -> new StructureSet(List.of(StructureSet.entry((Holder<Structure>) ModStructures.BIG_TREE)), placement(28, 7, 35122018)));
    public static final RegistryObject<StructureSet> SPAWNER_ROOMS = HELPER.register("spawner_rooms", () -> new StructureSet(List.of(StructureSet.entry((Holder<Structure>) ModStructures.SPAWNER_ROOM)), placement(3, 2, 820846813)));
    public static final RegistryObject<StructureSet> DEEP_SPAWNER_ROOMS = HELPER.register("deep_spawner_rooms", () -> new StructureSet(List.of(StructureSet.entry((Holder<Structure>) ModStructures.DEEP_SPAWNER_ROOM)), placement(3, 2, 601654390)));


    public static RandomSpreadStructurePlacement placement(int spacing, int separation, int seed) {
        return new RandomSpreadStructurePlacement(spacing, separation, RandomSpreadType.LINEAR, seed);
    }
}
