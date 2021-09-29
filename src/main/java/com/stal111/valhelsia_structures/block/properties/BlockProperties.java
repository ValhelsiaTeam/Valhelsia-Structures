package com.stal111.valhelsia_structures.block.properties;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
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

    public static final BlockBehaviour.Properties LAPIDIFIED_JUNGLE_LOG = BlockBehaviour.Properties.create(Material.WOOD, MaterialColor.DIRT).setRequiresTool().harvestTool(ToolType.AXE).harvestLevel(2).hardnessAndResistance(3.0F).sound(SoundType.WOOD);
    public static final AbstractBlock.Properties LAPIDIFIED_JUNGLE_PLANKS = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.DIRT).setRequiresTool().harvestTool(ToolType.AXE).harvestLevel(2).hardnessAndResistance(3.0F, 4.0F).sound(SoundType.WOOD);

    public static AbstractBlock.Properties createCutPostBlock(MaterialColor topColor, MaterialColor barkColor) {
        return AbstractBlock.Properties.create(Material.WOOD, (state) -> state.get(DirectionalBlock.FACING).getAxis() == Direction.Axis.Y ? topColor : barkColor).hardnessAndResistance(2.0F).sound(SoundType.WOOD).notSolid();
    }

    public static AbstractBlock.Properties createCutNetherPostBlock(MaterialColor color) {
        return AbstractBlock.Properties.create(Material.NETHER_WOOD, color).hardnessAndResistance(2.0F).sound(SoundType.HYPHAE).notSolid();
    }
}