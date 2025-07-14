package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryClass;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;
import net.valhelsia.valhelsia_core.api.common.registry.helper.MappedRegistryHelper;
import net.valhelsia.valhelsia_core.api.common.registry.helper.item.ItemRegistryHelper;

/**
 * Items <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModItems
 *
 * @author Valhelsia Team
 * @version 1.19 - 0.2.0
 */
public class ModItems implements RegistryClass {

    public static final ItemRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getItemHelper();

    public static final RegistryEntry<Item, StandingAndWallBlockItem> DOUSED_TORCH = HELPER.register("doused_torch", (properties) -> new StandingAndWallBlockItem(ModBlocks.UNLIT_TORCH.get(), ModBlocks.UNLIT_WALL_TORCH.get(), Direction.DOWN, properties), Item.Properties::new);
    public static final RegistryEntry<Item, StandingAndWallBlockItem> DOUSED_SOUL_TORCH = HELPER.register("doused_soul_torch", (properties) -> new StandingAndWallBlockItem(ModBlocks.UNLIT_SOUL_TORCH.get(), ModBlocks.UNLIT_SOUL_WALL_TORCH.get(), Direction.DOWN, properties), Item.Properties::new);

}
