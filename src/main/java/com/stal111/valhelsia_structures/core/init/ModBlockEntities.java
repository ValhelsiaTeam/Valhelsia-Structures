package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.common.block.entity.*;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.core.registry.RegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.MappedRegistryHelper;
import net.valhelsia.valhelsia_core.core.registry.helper.block.BlockRegistryObject;

import java.util.stream.Stream;

/**
 * Block Entities <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModBlockEntities
 *
 * @author Valhelsia Team
 */
public class ModBlockEntities implements RegistryClass {

    public static final MappedRegistryHelper<BlockEntityType<?>> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getMappedHelper(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES);


    public static final RegistryObject<BlockEntityType<SpecialSpawnerBlockEntity>> SPECIAL_SPAWNER = HELPER.register("special_spawner", () -> BlockEntityType.Builder.of(SpecialSpawnerBlockEntity::new, ModBlocks.SPECIAL_SPAWNER.get()).build(null));
    public static final RegistryObject<BlockEntityType<JarBlockEntity>> JAR = HELPER.register("jar", () -> BlockEntityType.Builder.of(JarBlockEntity::new, Stream.concat(Stream.of(ModBlocks.GLAZED_JAR.get(), ModBlocks.CRACKED_GLAZED_JAR.get()), ModBlocks.COLORED_GLAZED_JARS.values().stream().map(BlockRegistryObject::get)).toArray(Block[]::new)).build(null));
    public static final RegistryObject<BlockEntityType<ExplorersTentBlockEntity>> TENT = HELPER.register("explorers_tent", () -> BlockEntityType.Builder.of(ExplorersTentBlockEntity::new, ModBlocks.EXPLORERS_TENT.get()).build(null));
    public static final RegistryObject<BlockEntityType<GiantFernBlockEntity>> GIANT_FERN = HELPER.register("giant_fern", () -> BlockEntityType.Builder.of(GiantFernBlockEntity::new, ModBlocks.GIANT_FERN.get()).build(null));
    public static final RegistryObject<BlockEntityType<DungeonDoorBlockEntity>> DUNGEON_DOOR = HELPER.register("dungeon_door", () -> BlockEntityType.Builder.of(DungeonDoorBlockEntity::new, ModBlocks.DUNGEON_DOOR.get()).build(null));

}
