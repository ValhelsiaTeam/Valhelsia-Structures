package com.stal111.valhelsia_structures.data.worldgen.modifier;

import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.valhelsia.valhelsia_core.core.data.DataProviderInfo;
import net.valhelsia.valhelsia_core.data.worldgen.ValhelsiaBiomeModifierProvider;

import java.util.Collections;

/**
 * @author Valhelsia Team
 * @since 2022-12-08
 */
public class ModBiomeModifiers extends ValhelsiaBiomeModifierProvider {

    public ModBiomeModifiers(DataProviderInfo info, BootstapContext<BiomeModifier> context) {
        super(info, context);
    }

    public void bootstrap(BootstapContext<BiomeModifier> context) {
        this.add("remove_monster_room", new ForgeBiomeModifiers.RemoveFeaturesBiomeModifier(this.isOverworld, HolderSet.direct(this.featureRegistry.getOrThrow(CavePlacements.MONSTER_ROOM), this.featureRegistry.getOrThrow(CavePlacements.MONSTER_ROOM_DEEP)), Collections.singleton(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)));
    }
}
