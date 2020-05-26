package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.tileentity.SpecialMobSpawnerTileEntity;
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
 * @version 15.0.3
 */
public class ModTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<TileEntityType<SpecialMobSpawnerTileEntity>> SPECIAL_SPAWNER = register("special_spawner", () -> TileEntityType.Builder.create(SpecialMobSpawnerTileEntity::new, ModBlocks.SPECIAL_SPAWNER.get()).build(null));

    private static <T extends TileEntityType<?>> RegistryObject<T> register(String name, Supplier<T> tileEntity) {
        return TILE_ENTITIES.register(name, tileEntity);
    }
}
