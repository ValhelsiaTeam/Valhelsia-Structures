package com.stal111.valhelsia_structures.common.event;

import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;

/**
 * Furnace Fuel Listener <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.event.FurnaceFuelListener
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-07-12
 */
@EventBusSubscriber
public class FurnaceFuelListener {

    @SubscribeEvent
    public static void onFurnaceFuel(FurnaceFuelBurnTimeEvent event) {
        ItemStack stack = event.getItemStack();

        if (stack.getItem() instanceof BlockItem item) {
            if (item.getBlock() instanceof PostBlock || item.getBlock() == ModBlocks.PAPER_WALL.get()) {
                event.setBurnTime(300);
            } else if (item.getBlock() instanceof CutPostBlock) {
                event.setBurnTime(100);
            }
        }
    }
}
