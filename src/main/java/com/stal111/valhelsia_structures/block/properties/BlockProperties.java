package com.stal111.valhelsia_structures.block.properties;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

/**
 * Block Properties <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.properties.BlockProperties
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2020-11-22
 */
public class BlockProperties {

    public static final BlockBehaviour.Properties LAPIDIFIED_JUNGLE_LOG = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.WOOD);
    public static final BlockBehaviour.Properties LAPIDIFIED_JUNGLE_PLANKS = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(3.0F, 4.0F).sound(SoundType.WOOD);

    public static BlockBehaviour.Properties createCutPostBlock(MaterialColor topColor, MaterialColor barkColor) {
        return BlockBehaviour.Properties.of(Material.WOOD, (state) -> state.getValue(DirectionalBlock.FACING).getAxis() == Direction.Axis.Y ? topColor : barkColor).strength(2.0F).sound(SoundType.WOOD).noOcclusion();
    }

    public static BlockBehaviour.Properties createCutNetherPostBlock(MaterialColor color) {
        return BlockBehaviour.Properties.of(Material.NETHER_WOOD, color).strength(2.0F).sound(SoundType.STEM).noOcclusion();
    }
}