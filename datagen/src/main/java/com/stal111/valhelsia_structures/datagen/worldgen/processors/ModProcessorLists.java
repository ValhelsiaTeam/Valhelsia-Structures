package com.stal111.valhelsia_structures.datagen.worldgen.processors;

import com.stal111.valhelsia_structures.common.builtin.BuiltInProcessorLists;
import com.stal111.valhelsia_structures.common.world.structures.processor.SpawnerRoomLegProcessor;
import com.stal111.valhelsia_structures.common.world.structures.processor.WitchHutLegProcessor;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.valhelsia.valhelsia_core.api.common.registry.helper.datapack.DatapackRegistryClass;

import java.util.List;

/**
 * @author Valhelsia Team
 * @since 2023-01-14
 */
public class ModProcessorLists extends DatapackRegistryClass<StructureProcessorList> {

    public ModProcessorLists(BootstrapContext<StructureProcessorList> context) {
        super(context);
    }

    @Override
    public void bootstrap(BootstrapContext<StructureProcessorList> context) {
        context.register(BuiltInProcessorLists.WITCH_HUT, new StructureProcessorList(List.of(WitchHutLegProcessor.INSTANCE)));
        context.register(BuiltInProcessorLists.SPAWNER_ROOM, new StructureProcessorList(List.of(new SpawnerRoomLegProcessor(Blocks.COBBLESTONE.defaultBlockState(), Blocks.COBBLESTONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP)))));
        context.register(BuiltInProcessorLists.DEEP_SPAWNER_ROOM, new StructureProcessorList(List.of(new SpawnerRoomLegProcessor(Blocks.COBBLED_DEEPSLATE.defaultBlockState(), Blocks.COBBLED_DEEPSLATE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP)))));
    }
}
