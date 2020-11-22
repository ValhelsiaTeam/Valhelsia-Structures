package com.stal111.valhelsia_structures.block.properties;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

/**
 * Block Properties
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.properties.BlockProperties
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-22
 */
public class BlockProperties {

    public static final AbstractBlock.Properties LAPIDIFIED_JUNGLE_LOG = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.DIRT).setRequiresTool().harvestTool(ToolType.AXE).harvestLevel(2).hardnessAndResistance(3.0F).sound(SoundType.WOOD);
    public static final AbstractBlock.Properties LAPIDIFIED_JUNGLE_PLANKS = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.DIRT).setRequiresTool().harvestTool(ToolType.AXE).harvestLevel(2).hardnessAndResistance(3.0F, 4.0F).sound(SoundType.WOOD);
}
