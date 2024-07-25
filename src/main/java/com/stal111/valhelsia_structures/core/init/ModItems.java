package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryClass;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;
import net.valhelsia.valhelsia_core.api.common.registry.helper.MappedRegistryHelper;

/**
 * Items <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModItems
 *
 * @author Valhelsia Team
 * @version 1.19 - 0.2.0
 */
public class ModItems implements RegistryClass {

    public static final MappedRegistryHelper<Item> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getItemHelper();

    public static final RegistryEntry<Item, StandingAndWallBlockItem> DOUSED_TORCH = HELPER.register("doused_torch", () -> new StandingAndWallBlockItem(ModBlocks.DOUSED_TORCH.get(), ModBlocks.DOUSED_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));
    public static final RegistryEntry<Item, StandingAndWallBlockItem> DOUSED_SOUL_TORCH = HELPER.register("doused_soul_torch", () -> new StandingAndWallBlockItem(ModBlocks.DOUSED_SOUL_TORCH.get(), ModBlocks.DOUSED_SOUL_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));

}
