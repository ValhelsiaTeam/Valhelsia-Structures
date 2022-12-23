package com.stal111.valhelsia_structures.core.init.world;

import com.stal111.valhelsia_structures.common.world.structures.placement.ValhelsiaStructurePlacement;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.valhelsia.valhelsia_core.core.data.DataProviderInfo;
import net.valhelsia.valhelsia_core.core.registry.helper.DatapackRegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.DatapackRegistryHelper;

import java.util.stream.Stream;

/**
 * @author Valhelsia Team
 * @since 2022-06-24
 */
public class ModStructureSets extends DatapackRegistryClass<StructureSet> {

    public static final DatapackRegistryHelper<StructureSet> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getDatapackHelper(Registries.STRUCTURE_SET);

    public static final ResourceKey<StructureSet> CASTLES = HELPER.createKey("castles");
    public static final ResourceKey<StructureSet> CASTLE_RUINS = HELPER.createKey("castle_ruins");
    public static final ResourceKey<StructureSet> DESERT_HOUSES = HELPER.createKey("desert_houses");
    public static final ResourceKey<StructureSet> FORGES = HELPER.createKey("forges");
    public static final ResourceKey<StructureSet> PLAYER_HOUSES = HELPER.createKey("player_houses");
    public static final ResourceKey<StructureSet> SPAWNER_DUNGEONS = HELPER.createKey("spawner_dungeons");
    public static final ResourceKey<StructureSet> TOWER_RUINS = HELPER.createKey("tower_ruins");
    public static final ResourceKey<StructureSet> WITCH_HUTS = HELPER.createKey("witch_huts");
    public static final ResourceKey<StructureSet> BIG_TREES = HELPER.createKey("big_trees");
    public static final ResourceKey<StructureSet> SPAWNER_ROOMS = HELPER.createKey("spawner_rooms");
    public static final ResourceKey<StructureSet> DEEP_SPAWNER_ROOMS = HELPER.createKey("deep_spawner_rooms");

    public ModStructureSets(DataProviderInfo info, BootstapContext<StructureSet> context) {
        super(info, context);
    }

    @Override
    public void bootstrap(BootstapContext<StructureSet> context) {
        HolderGetter<Structure> structureRegistry = context.lookup(Registries.STRUCTURE);

        context.register(CASTLES, new StructureSet(structureRegistry.getOrThrow(ModStructures.CASTLE), this.placement(context, 37, 7, 16987356, CASTLE_RUINS, FORGES, PLAYER_HOUSES, TOWER_RUINS, BIG_TREES)));
        context.register(CASTLE_RUINS, new StructureSet(structureRegistry.getOrThrow(ModStructures.CASTLE_RUIN),this. placement(context, 33, 7, 436946199, FORGES, PLAYER_HOUSES, TOWER_RUINS, BIG_TREES)));
        context.register(DESERT_HOUSES, new StructureSet(structureRegistry.getOrThrow(ModStructures.DESERT_HOUSE), this.placement(context, 28, 7, 572792859)));
        context.register(FORGES, new StructureSet(structureRegistry.getOrThrow(ModStructures.FORGE), this.placement(context, 28, 7, 12857691, PLAYER_HOUSES, TOWER_RUINS, BIG_TREES)));
        context.register(PLAYER_HOUSES, new StructureSet(structureRegistry.getOrThrow(ModStructures.PLAYER_HOUSE), this.placement(context, 28, 7, 292107367, TOWER_RUINS, BIG_TREES)));
        context.register(SPAWNER_DUNGEONS, new StructureSet(structureRegistry.getOrThrow(ModStructures.SPAWNER_DUNGEON), this.placement(context, 28, 7, 23498567)));
        context.register(TOWER_RUINS, new StructureSet(structureRegistry.getOrThrow(ModStructures.TOWER_RUIN), this.placement(context, 23, 7, 24357670, BIG_TREES)));
        context.register(WITCH_HUTS, new StructureSet(structureRegistry.getOrThrow(ModStructures.WITCH_HUT), this.placement(context, 23, 6, 70882951)));
        context.register(BIG_TREES, new StructureSet(structureRegistry.getOrThrow(ModStructures.BIG_TREE), this.placement(context, 28, 7, 35122018)));
        context.register(SPAWNER_ROOMS, new StructureSet(structureRegistry.getOrThrow(ModStructures.SPAWNER_ROOM), this.placement(context, 3, 2, 820846813)));
        context.register(DEEP_SPAWNER_ROOMS, new StructureSet(structureRegistry.getOrThrow(ModStructures.DEEP_SPAWNER_ROOM), this.placement(context, 3, 2, 601654390)));
    }

    @SafeVarargs
    public final ValhelsiaStructurePlacement placement(BootstapContext<StructureSet> context, int spacing, int separation, int seed, ResourceKey<StructureSet>... exclusionZones) {
        return new ValhelsiaStructurePlacement(seed, spacing, separation, Stream.of(exclusionZones).map(structureSetResourceKey -> new StructurePlacement.ExclusionZone(context.lookup(Registries.STRUCTURE_SET).getOrThrow(structureSetResourceKey), 10)).toList());
    }
}
