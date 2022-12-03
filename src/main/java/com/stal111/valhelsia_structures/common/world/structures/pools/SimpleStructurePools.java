package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.stal111.valhelsia_structures.common.world.structures.processor.SpawnerRoomLegProcessor;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.valhelsia.valhelsia_core.core.registry.RegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.TemplatePoolRegistryHelper;

/**
 * @author Valhelsia Team
 * @since 2022-11-26
 */
public class SimpleStructurePools implements RegistryClass {

    public static final TemplatePoolRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registry.TEMPLATE_POOL_REGISTRY);

    public static final Holder<StructureTemplatePool> SPAWNER_ROOM_PATTERN = HELPER.register("spawner_room", "spawner_rooms", builder -> builder.element("spawner_room_1").element("spawner_room_2").processor(new SpawnerRoomLegProcessor(Blocks.COBBLESTONE.defaultBlockState(), Blocks.COBBLESTONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP))).removeWater());
    public static final Holder<StructureTemplatePool> DEEP_SPAWNER_ROOM_PATTERN = HELPER.register("deep_spawner_room", "deep_spawner_rooms", builder -> builder.element("deep_spawner_room_1").processor(new SpawnerRoomLegProcessor(Blocks.COBBLED_DEEPSLATE.defaultBlockState(), Blocks.COBBLED_DEEPSLATE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP))).removeWater());

}
