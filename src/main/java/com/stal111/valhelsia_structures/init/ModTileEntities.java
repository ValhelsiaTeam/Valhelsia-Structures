package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.tileentity.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

/**
 * Tile Entities
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModTileEntities
 *
 * @author Valhelsia Team
 * @version 16.1.0
 */
public class ModTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<TileEntityType<SpecialMobSpawnerTileEntity>> SPECIAL_SPAWNER = register("special_spawner", () -> TileEntityType.Builder.create(SpecialMobSpawnerTileEntity::new, ModBlocks.SPECIAL_SPAWNER.get()).build(null));
    public static final RegistryObject<TileEntityType<JarTileEntity>> JAR = register("jar", () -> TileEntityType.Builder.create(JarTileEntity::new, ModBlocks.GLAZED_JAR.get(), ModBlocks.CRACKED_GLAZED_JAR.get(), ModBlocks.COLORED_GLAZED_JARS.get(0).get(), ModBlocks.COLORED_GLAZED_JARS.get(1).get(), ModBlocks.COLORED_GLAZED_JARS.get(2).get(), ModBlocks.COLORED_GLAZED_JARS.get(3).get(), ModBlocks.COLORED_GLAZED_JARS.get(4).get(), ModBlocks.COLORED_GLAZED_JARS.get(5).get(), ModBlocks.COLORED_GLAZED_JARS.get(6).get(), ModBlocks.COLORED_GLAZED_JARS.get(7).get(), ModBlocks.COLORED_GLAZED_JARS.get(8).get(), ModBlocks.COLORED_GLAZED_JARS.get(9).get(), ModBlocks.COLORED_GLAZED_JARS.get(10).get(), ModBlocks.COLORED_GLAZED_JARS.get(11).get(), ModBlocks.COLORED_GLAZED_JARS.get(12).get(), ModBlocks.COLORED_GLAZED_JARS.get(13).get(), ModBlocks.COLORED_GLAZED_JARS.get(14).get(), ModBlocks.COLORED_GLAZED_JARS.get(15).get()).build(null));
    public static final RegistryObject<TileEntityType<ExplorersTentTileEntity>> TENT = register("explorers_tent", () -> TileEntityType.Builder.create(ExplorersTentTileEntity::new, ModBlocks.EXPLORERS_TENT.get()).build(null));
    public static final RegistryObject<TileEntityType<GiantFernTileEntity>> GIANT_FERN = register("giant_fern", () -> TileEntityType.Builder.create(GiantFernTileEntity::new, ModBlocks.GIANT_FERN.get()).build(null));
    public static final RegistryObject<TileEntityType<DungeonDoorTileEntity>> DUNGEON_DOOR = register("dungeon_door", () -> TileEntityType.Builder.create(DungeonDoorTileEntity::new, ModBlocks.DUNGEON_DOOR.get()).build(null));

    private static <T extends TileEntityType<?>> RegistryObject<T> register(String name, Supplier<T> tileEntity) {
        return TILE_ENTITIES.register(name, tileEntity);
    }
}
