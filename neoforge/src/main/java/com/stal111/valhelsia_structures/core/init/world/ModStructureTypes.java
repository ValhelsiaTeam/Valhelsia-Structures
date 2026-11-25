package com.stal111.valhelsia_structures.core.init.world;

import com.stal111.valhelsia_structures.common.world.structures.ValhelsiaJigsawStructure;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;
import net.valhelsia.valhelsia_core.api.common.registry.helper.RegistryHelper;

/**
 * @author Valhelsia Team
 * @since 2022-06-24
 */
public class ModStructureTypes {

    public static final RegistryHelper<StructureType<?>> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.STRUCTURE_TYPE);

    public static final RegistryEntry<StructureType<?>, StructureType<ValhelsiaJigsawStructure>> VALHELSIA_JIGSAW_STRUCTURE = HELPER.register("valhelsia_jigsaw_structure", () -> () -> ValhelsiaJigsawStructure.CODEC);
}
