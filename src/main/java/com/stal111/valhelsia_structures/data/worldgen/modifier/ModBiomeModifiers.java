package com.stal111.valhelsia_structures.data.worldgen.modifier;

import com.google.gson.JsonElement;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.valhelsia.valhelsia_core.core.data.DataProviderInfo;
import net.valhelsia.valhelsia_core.data.worldgen.ValhelsiaBiomeModifierProvider;

import java.util.Collections;

/**
 * @author Valhelsia Team
 * @since 2022-12-08
 */
public class ModBiomeModifiers extends ValhelsiaBiomeModifierProvider {

    public ModBiomeModifiers(DataProviderInfo info, RegistryOps<JsonElement> ops) {
        super(info, ops);
    }

    @Override
    protected void addModifiers() {
        this.add("remove_monster_room", new ForgeBiomeModifiers.RemoveFeaturesBiomeModifier(this.isOverworld, HolderSet.direct(this.featureRegistry.getHolderOrThrow(CavePlacements.MONSTER_ROOM.unwrapKey().orElseThrow()), this.featureRegistry.getHolderOrThrow(CavePlacements.MONSTER_ROOM_DEEP.unwrapKey().orElseThrow())), Collections.singleton(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)));
    }
}
