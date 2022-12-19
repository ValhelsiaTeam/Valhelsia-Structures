package com.stal111.valhelsia_structures.core.init.world;

import com.stal111.valhelsia_structures.common.world.structures.processor.SpawnerRoomLegProcessor;
import com.stal111.valhelsia_structures.common.world.structures.processor.WitchHutLegProcessor;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.core.registry.RegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.RegistryHelper;

/**
 * @author Valhelsia Team
 * @since 2022-11-30
 */
public class ModStructureProcessors implements RegistryClass {

    public static final RegistryHelper<StructureProcessorType<?>> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.STRUCTURE_PROCESSOR);

    public static final RegistryObject<StructureProcessorType<SpawnerRoomLegProcessor>> SPAWNER_ROOM_LEG = HELPER.register("spawner_room_leg", () -> () -> SpawnerRoomLegProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<WitchHutLegProcessor>> WITCH_HUT_LEG = HELPER.register("witch_hut_leg", () -> () -> WitchHutLegProcessor.CODEC);

}
