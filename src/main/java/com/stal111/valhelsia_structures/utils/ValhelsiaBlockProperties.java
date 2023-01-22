package com.stal111.valhelsia_structures.utils;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Function;

/**
 * @author Valhelsia Team
 * @since 2023-01-22
 */
public interface ValhelsiaBlockProperties {
    BlockBehaviour.Properties color(Function<BlockState, MaterialColor> materialColor);
}
