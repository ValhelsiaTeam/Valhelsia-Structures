package com.stal111.valhelsia_structures.core.init.world;

import com.stal111.valhelsia_structures.common.world.structures.ValhelsiaJigsawStructure;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.core.registry.RegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.RegistryHelper;

/**
 * @author Valhelsia Team
 * @since 2022-06-24
 */
public class ModStructureTypes implements RegistryClass {

    public static final RegistryHelper<StructureType<?>> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registry.STRUCTURE_TYPE_REGISTRY);

    public static final RegistryObject<StructureType<ValhelsiaJigsawStructure>> VALHELSIA_JIGSAW_STRUCTURE = HELPER.register("valhelsia_jigsaw_structure", () -> () -> ValhelsiaJigsawStructure.CODEC);
}
