package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.common.block.entity.*;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryClass;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;
import net.valhelsia.valhelsia_core.api.common.registry.helper.MappedRegistryHelper;
import net.valhelsia.valhelsia_core.api.common.registry.helper.block.BlockRegistryEntry;

import java.util.stream.Stream;

/**
 * Block Entities <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModBlockEntities
 *
 * @author Valhelsia Team
 */
public class ModBlockEntities implements RegistryClass {

    public static final MappedRegistryHelper<BlockEntityType<?>> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.BLOCK_ENTITY_TYPE);


    public static final RegistryEntry<BlockEntityType<?>, BlockEntityType<SpecialSpawnerBlockEntity>> SPECIAL_SPAWNER = HELPER.register("special_spawner", () -> BlockEntityType.Builder.of(SpecialSpawnerBlockEntity::new, ModBlocks.SPECIAL_SPAWNER.get()).build(null));
    public static final RegistryEntry<BlockEntityType<?>, BlockEntityType<JarBlockEntity>> JAR = HELPER.register("jar", () -> BlockEntityType.Builder.of(JarBlockEntity::new, Stream.concat(Stream.of(ModBlocks.GLAZED_JAR.get(), ModBlocks.CRACKED_GLAZED_JAR.get()), ModBlocks.COLORED_GLAZED_JARS.values().stream().map(BlockRegistryEntry::get)).toArray(Block[]::new)).build(null));
    public static final RegistryEntry<BlockEntityType<?>, BlockEntityType<ExplorersTentBlockEntity>> TENT = HELPER.register("explorers_tent", () -> BlockEntityType.Builder.of(ExplorersTentBlockEntity::new, ModBlocks.EXPLORERS_TENT.get()).build(null));
    public static final RegistryEntry<BlockEntityType<?>, BlockEntityType<GiantFernBlockEntity>> GIANT_FERN = HELPER.register("giant_fern", () -> BlockEntityType.Builder.of(GiantFernBlockEntity::new, ModBlocks.GIANT_FERN.get()).build(null));
    public static final RegistryEntry<BlockEntityType<?>, BlockEntityType<DungeonDoorBlockEntity>> DUNGEON_DOOR = HELPER.register("dungeon_door", () -> BlockEntityType.Builder.of(DungeonDoorBlockEntity::new, ModBlocks.DUNGEON_DOOR.get()).build(null));

}
