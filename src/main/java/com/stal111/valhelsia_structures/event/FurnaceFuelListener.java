package com.stal111.valhelsia_structures.event;

import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.PostBlock;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Furnace Fuel Listener
 * Valhelsia Structures - com.stal111.valhelsia_structures.event.FurnaceFuelListener
 *
 * @author Valhelsia Team
 * @version 0.1.4
 * @since 2021-07-12
 */
@Mod.EventBusSubscriber
public class FurnaceFuelListener {

    @SubscribeEvent
    public static void onFurnaceFuel(FurnaceFuelBurnTimeEvent event) {
        ItemStack stack = event.getItemStack();

        if (stack.getItem() instanceof BlockItem) {
            BlockItem item = (BlockItem) stack.getItem();

            if (item.getBlock() instanceof PostBlock || item.getBlock() == ModBlocks.PAPER_WALL.get()) {
                event.setBurnTime(300);
            } else if (item.getBlock() instanceof CutPostBlock) {
                event.setBurnTime(100);
            }
        }
    }
}
