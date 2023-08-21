package com.stal111.valhelsia_structures.data.worldgen.modifier;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.api.common.registry.helper.datapack.DatapackRegistryHelper;
import net.valhelsia.valhelsia_core.api.datagen.worldgen.ValhelsiaBiomeModifierProvider;

import java.util.Collections;

/**
 * @author Valhelsia Team
 * @since 2022-12-08
 */
public class ModBiomeModifiers extends ValhelsiaBiomeModifierProvider {

    public static final DatapackRegistryHelper<BiomeModifier> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(ForgeRegistries.Keys.BIOME_MODIFIERS);

    public static final ResourceKey<BiomeModifier> REMOVE_MONSTER_ROOM = HELPER.createKey("remove_monster_room");

    public ModBiomeModifiers(BootstapContext<BiomeModifier> context) {
        super(context);
    }

    @Override
    public void bootstrap(BootstapContext<BiomeModifier> context) {
        this.add(REMOVE_MONSTER_ROOM, new ForgeBiomeModifiers.RemoveFeaturesBiomeModifier(this.isOverworld, HolderSet.direct(this.featureRegistry.getOrThrow(CavePlacements.MONSTER_ROOM), this.featureRegistry.getOrThrow(CavePlacements.MONSTER_ROOM_DEEP)), Collections.singleton(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)));
    }
}
