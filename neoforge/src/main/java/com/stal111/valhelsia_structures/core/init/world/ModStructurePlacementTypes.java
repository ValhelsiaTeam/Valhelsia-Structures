package com.stal111.valhelsia_structures.core.init.world;

import com.stal111.valhelsia_structures.common.world.structures.placement.ValhelsiaStructurePlacement;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryClass;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;
import net.valhelsia.valhelsia_core.api.common.registry.helper.MappedRegistryHelper;

/**
 * @author Valhelsia Team
 * @since 2022-12-22
 */
public class ModStructurePlacementTypes implements RegistryClass {

    public static final MappedRegistryHelper<StructurePlacementType<?>> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.STRUCTURE_PLACEMENT);

    public static final RegistryEntry<StructurePlacementType<?>, StructurePlacementType<ValhelsiaStructurePlacement>> VALHELSIA_RANDOM_SPREAD = HELPER.register("valhelsia_random_spread", () -> () -> ValhelsiaStructurePlacement.CODEC);
}
