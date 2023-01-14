package com.stal111.valhelsia_structures.data.worldgen.processors;

import com.stal111.valhelsia_structures.common.world.structures.processor.SpawnerRoomLegProcessor;
import com.stal111.valhelsia_structures.common.world.structures.processor.WitchHutLegProcessor;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.valhelsia.valhelsia_core.common.world.structure.processor.RemoveWaterProcessor;
import net.valhelsia.valhelsia_core.core.data.DataProviderInfo;
import net.valhelsia.valhelsia_core.core.registry.helper.DatapackRegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.DatapackRegistryHelper;

import java.util.List;

/**
 * @author Valhelsia Team
 * @since 2023-01-14
 */
public class ModProcessorLists extends DatapackRegistryClass<StructureProcessorList> {

    public static final DatapackRegistryHelper<StructureProcessorList> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getDatapackHelper(Registries.PROCESSOR_LIST);

    public static final ResourceKey<StructureProcessorList> REMOVE_WATER = HELPER.createKey("remove_water");
    public static final ResourceKey<StructureProcessorList> WITCH_HUT = HELPER.createKey("witch_hut");
    public static final ResourceKey<StructureProcessorList> SPAWNER_ROOM = HELPER.createKey("spawner_room");
    public static final ResourceKey<StructureProcessorList> DEEP_SPAWNER_ROOM = HELPER.createKey("deep_spawner_room");

    public ModProcessorLists(DataProviderInfo info, BootstapContext<StructureProcessorList> context) {
        super(info, context);
    }

    @Override
    public void bootstrap(BootstapContext<StructureProcessorList> context) {
        context.register(REMOVE_WATER, new StructureProcessorList(List.of(RemoveWaterProcessor.INSTANCE)));
        context.register(WITCH_HUT, new StructureProcessorList(List.of(WitchHutLegProcessor.INSTANCE)));
        context.register(SPAWNER_ROOM, new StructureProcessorList(List.of(new SpawnerRoomLegProcessor(Blocks.COBBLESTONE.defaultBlockState(), Blocks.COBBLESTONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP)), RemoveWaterProcessor.INSTANCE)));
        context.register(DEEP_SPAWNER_ROOM, new StructureProcessorList(List.of(new SpawnerRoomLegProcessor(Blocks.COBBLED_DEEPSLATE.defaultBlockState(), Blocks.COBBLED_DEEPSLATE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP)), RemoveWaterProcessor.INSTANCE)));
    }
}
