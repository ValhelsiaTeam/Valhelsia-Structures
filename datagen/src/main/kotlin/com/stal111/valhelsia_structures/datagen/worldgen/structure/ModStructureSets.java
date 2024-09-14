package com.stal111.valhelsia_structures.datagen.worldgen.structure;

import com.stal111.valhelsia_structures.common.builtin.BuiltInStructures;
import com.stal111.valhelsia_structures.common.world.structures.placement.ValhelsiaStructurePlacement;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.valhelsia.valhelsia_core.api.common.registry.helper.datapack.DatapackRegistryClass;

import java.util.stream.Stream;

import static com.stal111.valhelsia_structures.common.builtin.BuiltInStructureSets.*;

/**
 * @author Valhelsia Team
 * @since 2022-06-24
 */
public class ModStructureSets extends DatapackRegistryClass<StructureSet> {

    public ModStructureSets(BootstrapContext<StructureSet> context) {
        super(context);
    }

    @Override
    public void bootstrap(BootstrapContext<StructureSet> context) {
        HolderGetter<Structure> structureRegistry = context.lookup(Registries.STRUCTURE);

        context.register(CASTLES, new StructureSet(structureRegistry.getOrThrow(BuiltInStructures.CASTLE), this.placement(context, 37, 7, 16987356, CASTLE_RUINS, FORGES, PLAYER_HOUSES, TOWER_RUINS, BIG_TREES)));
        context.register(CASTLE_RUINS, new StructureSet(structureRegistry.getOrThrow(BuiltInStructures.CASTLE_RUIN), this.placement(context, 33, 7, 436946199, FORGES, PLAYER_HOUSES, TOWER_RUINS, BIG_TREES)));
        context.register(DESERT_HOUSES, new StructureSet(structureRegistry.getOrThrow(BuiltInStructures.DESERT_HOUSE), this.placement(context, 28, 7, 572792859)));
        context.register(FORGES, new StructureSet(structureRegistry.getOrThrow(BuiltInStructures.FORGE), this.placement(context, 28, 7, 12857691, PLAYER_HOUSES, TOWER_RUINS, BIG_TREES)));
        context.register(PLAYER_HOUSES, new StructureSet(structureRegistry.getOrThrow(BuiltInStructures.PLAYER_HOUSE), this.placement(context, 28, 7, 292107367, TOWER_RUINS, BIG_TREES)));
        context.register(SPAWNER_DUNGEONS, new StructureSet(structureRegistry.getOrThrow(BuiltInStructures.SPAWNER_DUNGEON), this.placement(context, 28, 7, 23498567)));
        context.register(TOWER_RUINS, new StructureSet(structureRegistry.getOrThrow(BuiltInStructures.TOWER_RUIN), this.placement(context, 23, 7, 24357670, BIG_TREES)));
        context.register(WITCH_HUTS, new StructureSet(structureRegistry.getOrThrow(BuiltInStructures.WITCH_HUT), this.placement(context, 23, 6, 70882951)));
        context.register(BIG_TREES, new StructureSet(structureRegistry.getOrThrow(BuiltInStructures.BIG_TREE), this.placement(context, 28, 7, 35122018)));
        context.register(SPAWNER_ROOMS, new StructureSet(structureRegistry.getOrThrow(BuiltInStructures.SPAWNER_ROOM), this.placement(context, 3, 2, 820846813)));
        context.register(DEEP_SPAWNER_ROOMS, new StructureSet(structureRegistry.getOrThrow(BuiltInStructures.DEEP_SPAWNER_ROOM), this.placement(context, 3, 2, 601654390)));
    }

    @SafeVarargs
    public final ValhelsiaStructurePlacement placement(BootstrapContext<StructureSet> context, int spacing, int separation, int seed, ResourceKey<StructureSet>... exclusionZones) {
        return new ValhelsiaStructurePlacement(seed, spacing, separation, Stream.of(exclusionZones).map(structureSetResourceKey -> new StructurePlacement.ExclusionZone(context.lookup(Registries.STRUCTURE_SET).getOrThrow(structureSetResourceKey), 10)).toList());
    }
}
