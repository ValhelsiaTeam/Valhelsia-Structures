package com.stal111.valhelsia_structures.datagen.worldgen.modifier;

import com.stal111.valhelsia_structures.common.builtin.BuiltInBiomeModifiers;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.valhelsia.valhelsia_core.api.datagen.worldgen.ValhelsiaBiomeModifierProvider;

import java.util.Collections;

/**
 * @author Valhelsia Team
 * @since 2022-12-08
 */
public class ModBiomeModifiers extends ValhelsiaBiomeModifierProvider {

    public ModBiomeModifiers(BootstrapContext<BiomeModifier> context) {
        super(context);
    }

    @Override
    public void bootstrap(BootstrapContext<BiomeModifier> context) {
        this.add(BuiltInBiomeModifiers.REMOVE_MONSTER_ROOM, new BiomeModifiers.RemoveFeaturesBiomeModifier(this.isOverworld, HolderSet.direct(this.featureRegistry.getOrThrow(CavePlacements.MONSTER_ROOM), this.featureRegistry.getOrThrow(CavePlacements.MONSTER_ROOM_DEEP)), Collections.singleton(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)));
    }
}
