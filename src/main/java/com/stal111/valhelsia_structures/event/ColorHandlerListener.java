package com.stal111.valhelsia_structures.event;

import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.GrassColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Color Handler Listener
 * Valhelsia Structures - com.stal111.valhelsia_structures.event.ColorHandlerListener
 *
 * @author Valhelsia Team
 * @version 16.0.4
 * @since 2020-10-16
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ColorHandlerListener {

    @SubscribeEvent
    public static void onBlockColor(ColorHandlerEvent.Block event) {
        BlockColors blockColors = event.getBlockColors();

        blockColors.register(
                (state, reader, pos, color) -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos) : FoliageColors.getDefault(),
                ModBlocks.HANGING_VINES_BODY.get(), ModBlocks.HANGING_VINES.get());
        blockColors.register((state, reader, pos, color) -> reader != null && pos != null ? BiomeColors.getGrassColor(reader, pos) : GrassColors.get(0.5D, 1.0D),
                ModBlocks.GRASS_BLOCK.get());
    }

    @SubscribeEvent
    public static void onItemColor(ColorHandlerEvent.Item event) {
        BlockColors blockColors = event.getBlockColors();
        ItemColors itemColors = event.getItemColors();

        itemColors.register((stack, color) -> {
            BlockState blockstate = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
            return blockColors.getColor(blockstate, null, null, color);
        }, ModBlocks.HANGING_VINES.get(), ModBlocks.GRASS_BLOCK.get());
    }
}
