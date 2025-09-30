package com.stal111.valhelsia_structures.client.event;

import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

/**
 * @author Valhelsia Team
 * @since 2022-10-24
 */
@EventBusSubscriber(value = Dist.CLIENT)
public class ColorEvents {

    @SubscribeEvent
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, tintIndex) -> {
            if (level != null && pos != null) {
                return BiomeColors.getAverageFoliageColor(level, pos);
            }
            return FoliageColor.FOLIAGE_DEFAULT;
        }, ModBlocks.HANGING_VINES.get(), ModBlocks.HANGING_VINES_BODY.get());
    }
}
