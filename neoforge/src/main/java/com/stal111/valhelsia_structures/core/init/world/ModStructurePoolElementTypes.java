package com.stal111.valhelsia_structures.core.init.world;

import com.stal111.valhelsia_structures.common.world.structures.pools.ValhelsiaPoolElementWrapper;
import com.stal111.valhelsia_structures.common.world.structures.pools.ValhelsiaSinglePoolElement;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;
import net.valhelsia.valhelsia_core.api.common.registry.helper.RegistryHelper;

/**
 * @author Valhelsia Team
 * @since 2022-11-18
 */
public class ModStructurePoolElementTypes {

    public static final RegistryHelper<StructurePoolElementType<?>> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.STRUCTURE_POOL_ELEMENT);

    public static final RegistryEntry<StructurePoolElementType<?>, StructurePoolElementType<ValhelsiaPoolElementWrapper>> VALHELSIA_POOL_ELEMENT_WRAPPER = HELPER.register("valhelsia_pool_element_wrapper", () -> () -> ValhelsiaPoolElementWrapper.CODEC);
    public static final RegistryEntry<StructurePoolElementType<?>, StructurePoolElementType<ValhelsiaSinglePoolElement>> VALHELSIA_SINGLE_POOL_ELEMENT = HELPER.register("valhelsia_single_pool_element", () -> () -> ValhelsiaSinglePoolElement.CODEC);

}
