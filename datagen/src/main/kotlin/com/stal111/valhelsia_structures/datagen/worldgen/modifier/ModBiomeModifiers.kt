package com.stal111.valhelsia_structures.datagen.worldgen.modifier

import com.stal111.valhelsia_structures.common.builtin.BuiltInBiomeModifiers
import net.minecraft.core.HolderSet
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.CavePlacements
import net.minecraft.world.level.levelgen.GenerationStep
import net.neoforged.neoforge.common.world.BiomeModifier
import net.valhelsia.dataforge.worldgen.DataForgeBiomeModifierProvider

object ModBiomeModifiers : DataForgeBiomeModifierProvider() {
    override fun bootstrap(context: BootstrapContext<BiomeModifier>) {
        context.removeFeature(
            key = BuiltInBiomeModifiers.REMOVE_MONSTER_ROOM,
            biomes = context.overWorldBiomes,
            features = HolderSet.direct(
                context.featureLookup.getOrThrow(CavePlacements.MONSTER_ROOM),
                context.featureLookup.getOrThrow(CavePlacements.MONSTER_ROOM_DEEP)
            ),
            steps = setOf(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
        )
    }
}
