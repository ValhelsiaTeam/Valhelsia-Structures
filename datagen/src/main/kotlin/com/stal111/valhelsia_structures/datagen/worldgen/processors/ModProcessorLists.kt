package com.stal111.valhelsia_structures.datagen.worldgen.processors

import com.stal111.valhelsia_structures.common.builtin.BuiltInProcessorLists
import com.stal111.valhelsia_structures.common.world.structures.processor.SpawnerRoomLegProcessor
import com.stal111.valhelsia_structures.common.world.structures.processor.StructureDataProcessor
import com.stal111.valhelsia_structures.common.world.structures.processor.WitchHutLegProcessor
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.state.properties.SlabType
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList
import net.valhelsia.dataforge.RegistryDataProvider

object ModProcessorLists : RegistryDataProvider<StructureProcessorList> {
    override fun bootstrap(context: BootstrapContext<StructureProcessorList>): Unit = context.run {
        register(
            BuiltInProcessorLists.WITCH_HUT,
            WitchHutLegProcessor.INSTANCE,
            StructureDataProcessor.INSTANCE
        )
        register(
            BuiltInProcessorLists.SPAWNER_ROOM,
            SpawnerRoomLegProcessor(
                Blocks.COBBLESTONE.defaultBlockState(),
                Blocks.COBBLESTONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP)
            ),
            StructureDataProcessor.INSTANCE
        )
        register(
            BuiltInProcessorLists.DEEP_SPAWNER_ROOM,
            SpawnerRoomLegProcessor(
                Blocks.COBBLED_DEEPSLATE.defaultBlockState(),
                Blocks.COBBLED_DEEPSLATE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP)
            ),
            StructureDataProcessor.INSTANCE
        )
        register(
            BuiltInProcessorLists.SPAWNER_DUNGEON,
            StructureDataProcessor.INSTANCE
        )
    }

    private fun BootstrapContext<StructureProcessorList>.register(
        key: ResourceKey<StructureProcessorList>,
        vararg processors: StructureProcessor
    ) {
        register(key, StructureProcessorList(processors.toList()))
    }
}
