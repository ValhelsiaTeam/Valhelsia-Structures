package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.common.block.entity.*;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.core.registry.RegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.RegistryHelper;

/**
 * Block Entities <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModBlockEntities
 *
 * @author Valhelsia Team
 */
public class ModBlockEntities implements RegistryClass {

    public static final RegistryHelper<BlockEntityType<?>> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES);


    public static final RegistryObject<BlockEntityType<SpecialSpawnerBlockEntity>> SPECIAL_SPAWNER = HELPER.register("special_spawner", () -> BlockEntityType.Builder.of(SpecialSpawnerBlockEntity::new, ModBlocks.SPECIAL_SPAWNER.get()).build(null));
    public static final RegistryObject<BlockEntityType<JarBlockEntity>> JAR = HELPER.register("jar", () -> BlockEntityType.Builder.of(JarBlockEntity::new, ModBlocks.GLAZED_JAR.get(), ModBlocks.CRACKED_GLAZED_JAR.get(), ModBlocks.COLORED_GLAZED_JARS.get(0).get(), ModBlocks.COLORED_GLAZED_JARS.get(1).get(), ModBlocks.COLORED_GLAZED_JARS.get(2).get(), ModBlocks.COLORED_GLAZED_JARS.get(3).get(), ModBlocks.COLORED_GLAZED_JARS.get(4).get(), ModBlocks.COLORED_GLAZED_JARS.get(5).get(), ModBlocks.COLORED_GLAZED_JARS.get(6).get(), ModBlocks.COLORED_GLAZED_JARS.get(7).get(), ModBlocks.COLORED_GLAZED_JARS.get(8).get(), ModBlocks.COLORED_GLAZED_JARS.get(9).get(), ModBlocks.COLORED_GLAZED_JARS.get(10).get(), ModBlocks.COLORED_GLAZED_JARS.get(11).get(), ModBlocks.COLORED_GLAZED_JARS.get(12).get(), ModBlocks.COLORED_GLAZED_JARS.get(13).get(), ModBlocks.COLORED_GLAZED_JARS.get(14).get(), ModBlocks.COLORED_GLAZED_JARS.get(15).get()).build(null));
    public static final RegistryObject<BlockEntityType<ExplorersTentBlockEntity>> TENT = HELPER.register("explorers_tent", () -> BlockEntityType.Builder.of(ExplorersTentBlockEntity::new, ModBlocks.EXPLORERS_TENT.get()).build(null));
    public static final RegistryObject<BlockEntityType<GiantFernBlockEntity>> GIANT_FERN = HELPER.register("giant_fern", () -> BlockEntityType.Builder.of(GiantFernBlockEntity::new, ModBlocks.GIANT_FERN.get()).build(null));
    public static final RegistryObject<BlockEntityType<DungeonDoorBlockEntity>> DUNGEON_DOOR = HELPER.register("dungeon_door", () -> BlockEntityType.Builder.of(DungeonDoorBlockEntity::new, ModBlocks.DUNGEON_DOOR.get()).build(null));

}
