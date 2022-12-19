package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.stal111.valhelsia_structures.common.world.structures.processor.SpawnerRoomLegProcessor;
import com.stal111.valhelsia_structures.common.world.structures.processor.WitchHutLegProcessor;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.valhelsia.valhelsia_core.core.registry.RegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.TemplatePoolRegistryHelper;

/**
 * @author Valhelsia Team
 * @since 2022-11-26
 */
public class SimpleStructurePools implements RegistryClass {

    public static final TemplatePoolRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.TEMPLATE_POOL);

    public static final ResourceKey<StructureTemplatePool> SPAWNER_ROOM = HELPER.createKey("spawner_rooms");
    public static final ResourceKey<StructureTemplatePool> DEEP_SPAWNER_ROOM = HELPER.createKey("deep_spawner_rooms");
    public static final ResourceKey<StructureTemplatePool> WITCH_HUT = HELPER.createKey("witch_huts");

    public static void bootstrap(BootstapContext<StructureTemplatePool> context) {
        HolderGetter<StructureProcessorList> processorListRegistry = context.lookup(Registries.PROCESSOR_LIST);

        HELPER.createPool(context, "spawner_room", "spawner_rooms", builder -> builder.element("spawner_room_1").element("spawner_room_2").processor(new SpawnerRoomLegProcessor(Blocks.COBBLESTONE.defaultBlockState(), Blocks.COBBLESTONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP))).removeWater());
        HELPER.createPool(context, "deep_spawner_room", "deep_spawner_rooms", builder -> builder.element("deep_spawner_room_1").processor(new SpawnerRoomLegProcessor(Blocks.COBBLED_DEEPSLATE.defaultBlockState(), Blocks.COBBLED_DEEPSLATE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP))).removeWater());
        HELPER.createPool(context, "witch_hut", "witch_huts", builder -> builder.element("witch_hut_1").element("witch_hut_2").processor(WitchHutLegProcessor.INSTANCE));
    }
}
