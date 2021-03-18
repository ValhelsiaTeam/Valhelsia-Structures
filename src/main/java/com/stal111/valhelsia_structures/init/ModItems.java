package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.ValhelsiaStructuresItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.WallOrFloorItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.valhelsia.valhelsia_core.registry.ItemRegistryHelper;

/**
 * Items
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModItems
 *
 * @author Valhelsia Team
 * @version 16.0.4
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {

    public static final ItemRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getItemHelper();

    public static final RegistryObject<WallOrFloorItem> DOUSED_TORCH = HELPER.register("doused_torch", () -> new WallOrFloorItem(ModBlocks.DOUSED_TORCH.get(), ModBlocks.DOUSED_WALL_TORCH.get(), new Item.Properties().group(ValhelsiaStructuresItemGroups.MAIN)));
    public static final RegistryObject<WallOrFloorItem> DOUSED_SOUL_TORCH = HELPER.register("doused_soul_torch", () -> new WallOrFloorItem(ModBlocks.DOUSED_SOUL_TORCH.get(), ModBlocks.DOUSED_SOUL_WALL_TORCH.get(), new Item.Properties().group(ValhelsiaStructuresItemGroups.MAIN)));

}
