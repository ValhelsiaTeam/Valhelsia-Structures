package com.stal111.valhelsia_structures.core.init.world;

import com.mojang.serialization.MapCodec;
import com.stal111.valhelsia_structures.common.world.structures.processor.SpawnerRoomLegProcessor;
import com.stal111.valhelsia_structures.common.world.structures.processor.StructureDataProcessor;
import com.stal111.valhelsia_structures.common.world.structures.processor.WitchHutLegProcessor;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;
import net.valhelsia.valhelsia_core.api.common.registry.helper.RegistryHelper;

/**
 * @author Valhelsia Team
 * @since 2022-11-30
 */
public class ModStructureProcessors {

    public static final RegistryHelper<MapCodec<? extends StructureProcessor>> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.STRUCTURE_PROCESSOR);

    public static final RegistryEntry<MapCodec<? extends StructureProcessor>, MapCodec<SpawnerRoomLegProcessor>> SPAWNER_ROOM_LEG = HELPER.register("spawner_room_leg", () -> SpawnerRoomLegProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>, MapCodec<WitchHutLegProcessor>> WITCH_HUT_LEG = HELPER.register("witch_hut_leg", () -> WitchHutLegProcessor.CODEC);
    public static final RegistryEntry<MapCodec<? extends StructureProcessor>, MapCodec<StructureDataProcessor>> STRUCTURE_DATA = HELPER.register("structure_data", () -> StructureDataProcessor.CODEC);

}
