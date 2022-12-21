package com.stal111.valhelsia_structures.core.init.world;

import com.stal111.valhelsia_structures.common.world.structures.pools.ValhelsiaSinglePoolElement;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.core.registry.RegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.MappedRegistryHelper;

/**
 * @author Valhelsia Team
 * @since 2022-11-18
 */
public class ModStructurePoolElementTypes implements RegistryClass {

    public static final MappedRegistryHelper<StructurePoolElementType<?>> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getMappedHelper(Registries.STRUCTURE_POOL_ELEMENT);

    public static final RegistryObject<StructurePoolElementType<ValhelsiaSinglePoolElement>> VALHELSIA_SINGLE = HELPER.register("valhelsia_single", () -> () -> ValhelsiaSinglePoolElement.CODEC);
}
