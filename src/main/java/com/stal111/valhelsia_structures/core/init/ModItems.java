package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.common.item.ModCreativeModeTabs;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.core.registry.ItemRegistryHelper;

/**
 * Items <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModItems
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {

    public static final ItemRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getItemHelper();

    public static final RegistryObject<StandingAndWallBlockItem> DOUSED_TORCH = HELPER.register("doused_torch", () -> new StandingAndWallBlockItem(ModBlocks.DOUSED_TORCH.get(), ModBlocks.DOUSED_WALL_TORCH.get(), new Item.Properties().tab(ModCreativeModeTabs.MAIN)));
    public static final RegistryObject<StandingAndWallBlockItem> DOUSED_SOUL_TORCH = HELPER.register("doused_soul_torch", () -> new StandingAndWallBlockItem(ModBlocks.DOUSED_SOUL_TORCH.get(), ModBlocks.DOUSED_SOUL_WALL_TORCH.get(), new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

}
