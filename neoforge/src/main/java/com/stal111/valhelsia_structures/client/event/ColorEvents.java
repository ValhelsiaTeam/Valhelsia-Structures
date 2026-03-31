package com.stal111.valhelsia_structures.client.event;

import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.client.color.block.BlockTintSources;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.List;

/**
 * @author Valhelsia Team
 * @since 2022-10-24
 */
@EventBusSubscriber(value = Dist.CLIENT)
public class ColorEvents {

    @SubscribeEvent
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.BlockTintSources event) {
        event.register(List.of(BlockTintSources.foliage()), ModBlocks.HANGING_VINES.get(), ModBlocks.HANGING_VINES_BODY.get());
    }
}
