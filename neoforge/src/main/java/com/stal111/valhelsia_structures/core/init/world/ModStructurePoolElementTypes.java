package com.stal111.valhelsia_structures.core.init.world;

import com.stal111.valhelsia_structures.common.world.structures.pools.ValhelsiaPoolElementWrapper;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryClass;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;
import net.valhelsia.valhelsia_core.api.common.registry.helper.MappedRegistryHelper;

/**
 * @author Valhelsia Team
 * @since 2022-11-18
 */
public class ModStructurePoolElementTypes implements RegistryClass {

    public static final MappedRegistryHelper<StructurePoolElementType<?>> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.STRUCTURE_POOL_ELEMENT);

    public static final RegistryEntry<StructurePoolElementType<?>, StructurePoolElementType<ValhelsiaPoolElementWrapper>> VALHELSIA_POOL_ELEMENT_WRAPPER = HELPER.register("valhelsia_pool_element_wrapper", () -> () -> ValhelsiaPoolElementWrapper.CODEC);

}
