package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Items
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModItems
 *
 * @author Valhelsia Team
 * @version 15.0.3
 */

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ValhelsiaStructures.MOD_ID);

    private static <T extends Item> RegistryObject<T> register(String name, T item) {
        return ITEMS.register(name, () -> item);
    }
}
