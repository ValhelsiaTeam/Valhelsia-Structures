package com.stal111.valhelsia_structures.datagen.worldgen.structure

import com.stal111.valhelsia_structures.common.builtin.BuiltInStructureSets
import com.stal111.valhelsia_structures.common.builtin.BuiltInStructures
import com.stal111.valhelsia_structures.common.world.structures.placement.ValhelsiaStructurePlacement
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.StructureSet
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement
import net.valhelsia.dataforge.RegistryDataProvider

object ModStructureSets : RegistryDataProvider<StructureSet> {
    override fun bootstrap(context: BootstrapContext<StructureSet>): Unit = context.run {
        context.lookup<Structure?>(Registries.STRUCTURE)

        register(
            BuiltInStructureSets.CASTLES,
            BuiltInStructures.CASTLE,
            placement(
                context,
                37,
                7,
                16987356,
                BuiltInStructureSets.CASTLE_RUINS,
                BuiltInStructureSets.FORGES,
                BuiltInStructureSets.PLAYER_HOUSES,
                BuiltInStructureSets.TOWER_RUINS,
                BuiltInStructureSets.BIG_TREES
            )
        )
        register(
            BuiltInStructureSets.CASTLE_RUINS,
            BuiltInStructures.CASTLE_RUIN,
            placement(
                context,
                33,
                7,
                436946199,
                BuiltInStructureSets.FORGES,
                BuiltInStructureSets.PLAYER_HOUSES,
                BuiltInStructureSets.TOWER_RUINS,
                BuiltInStructureSets.BIG_TREES
            )
        )
        register(
            BuiltInStructureSets.DESERT_HOUSES,
            BuiltInStructures.DESERT_HOUSE,
            placement(context, 28, 7, 572792859)
        )
        register(
            BuiltInStructureSets.FORGES,
            BuiltInStructures.FORGE,
            placement(
                context,
                28,
                7,
                12857691,
                BuiltInStructureSets.PLAYER_HOUSES,
                BuiltInStructureSets.TOWER_RUINS,
                BuiltInStructureSets.BIG_TREES
            )
        )
        register(
            BuiltInStructureSets.PLAYER_HOUSES,
            BuiltInStructures.PLAYER_HOUSE,
            placement(
                context,
                28,
                7,
                292107367,
                BuiltInStructureSets.TOWER_RUINS,
                BuiltInStructureSets.BIG_TREES
            )
        )
        register(
            BuiltInStructureSets.SPAWNER_DUNGEONS,
            BuiltInStructures.SPAWNER_DUNGEON,
            placement(context, 28, 7, 23498567)
        )
        register(
            BuiltInStructureSets.TOWER_RUINS,
            BuiltInStructures.TOWER_RUIN,
            placement(context, 23, 7, 24357670, BuiltInStructureSets.BIG_TREES)
        )
        register(
            BuiltInStructureSets.WITCH_HUTS,
            BuiltInStructures.WITCH_HUT,
            placement(context, 23, 6, 70882951)
        )
        register(
            BuiltInStructureSets.BIG_TREES,
            BuiltInStructures.BIG_TREE,
            placement(context, 28, 7, 35122018)
        )
        register(
            BuiltInStructureSets.SPAWNER_ROOMS,
            BuiltInStructures.SPAWNER_ROOM,
            placement(context, 3, 2, 820846813)
        )
        register(
            BuiltInStructureSets.DEEP_SPAWNER_ROOMS,
            BuiltInStructures.DEEP_SPAWNER_ROOM,
            placement(context, 3, 2, 601654390)
        )
    }

    private fun BootstrapContext<StructureSet>.register(
        key: ResourceKey<StructureSet>,
        structure: ResourceKey<Structure>,
        placement: ValhelsiaStructurePlacement
    ) = register(key, StructureSet(this.lookup(Registries.STRUCTURE).getOrThrow(structure), placement))

    @SafeVarargs
    fun placement(
        context: BootstrapContext<StructureSet>,
        spacing: Int,
        separation: Int,
        seed: Int,
        vararg exclusionZones: ResourceKey<StructureSet>
    ) = ValhelsiaStructurePlacement(
        seed,
        spacing,
        separation,
        exclusionZones.map {
            StructurePlacement.ExclusionZone(context.lookup(Registries.STRUCTURE_SET).getOrThrow(it), 10)
        }
    )
}
