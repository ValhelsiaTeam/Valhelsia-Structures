package com.stal111.valhelsia_structures.common.block.properties;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

/**
 * Block Properties <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.properties.BlockProperties
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2020-11-22
 */
public class BlockProperties {

    public static final BlockBehaviour.Properties LAPIDIFIED_JUNGLE_LOG = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.WOOD);
    public static final BlockBehaviour.Properties LAPIDIFIED_JUNGLE_PLANKS = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(3.0F, 4.0F).sound(SoundType.WOOD);
}