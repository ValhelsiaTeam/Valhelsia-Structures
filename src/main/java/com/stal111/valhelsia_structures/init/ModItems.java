package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.ValhelsiaStructuresItemGroups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Items
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModItems
 *
 * @author Valhelsia Team
 * @version 16.0.4
 */
public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ValhelsiaStructures.MOD_ID);

    public static RegistryObject<BlockItem> registerBlockItem(String name, Block block) {
        return register(name, new BlockItem(block, new Item.Properties().group(ValhelsiaStructuresItemGroups.MAIN)));
    }

    private static <T extends Item> RegistryObject<T> register(String name, T item) {
        return ITEMS.register(name, () -> item);
    }
}
