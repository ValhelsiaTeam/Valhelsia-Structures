package com.stal111.valhelsia_structures.client.event;

import com.stal111.valhelsia_structures.common.item.DyeableBlockItem;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

/**
 * @author Valhelsia Team
 * @since 2022-10-24
 */
@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ColorEvents {

    @SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        BlockColors blockColors = event.getBlockColors();

        event.register((stack, tintIndex) -> {
            if (tintIndex == 0 && stack.getItem() instanceof DyeableBlockItem item) {
                return stack.get(DataComponents.DYED_COLOR).rgb();
            }

            return -1;
        }, ModBlocks.EXPLORERS_TENT.get());

        event.register((stack, tintIndex) -> {
            if (stack.getItem() instanceof BlockItem item) {
                BlockState state = item.getBlock().defaultBlockState();

                return blockColors.getColor(state, null, null, tintIndex);
            }

            return -1;
        }, ModBlocks.HANGING_VINES.get());
    }

    @SubscribeEvent
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, tintIndex) -> {
            if (level != null && pos != null) {
                return BiomeColors.getAverageFoliageColor(level, pos);
            }
            return FoliageColor.getDefaultColor();
        }, ModBlocks.HANGING_VINES.get(), ModBlocks.HANGING_VINES_BODY.get());
    }
}
