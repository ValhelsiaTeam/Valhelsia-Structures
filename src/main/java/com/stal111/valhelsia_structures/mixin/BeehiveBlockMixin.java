package com.stal111.valhelsia_structures.mixin;

import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nonnull;

/**
 * Beehive Block Mixin
 * Valhelsia Structures - com.stal111.valhelsia_structures.mixin.BeehiveBlockMixin
 *
 * Adds missing rotate and mirror methods.
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-05-12
 */
@Mixin(BeehiveBlock.class)
public abstract class BeehiveBlockMixin extends ContainerBlock {

    @Shadow @Final public static DirectionProperty FACING;

    protected BeehiveBlockMixin(Properties builder) {
        super(builder);
    }

    @Nonnull
    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.with(FACING, direction.rotate(state.get(FACING)));
    }

    @Nonnull
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.toRotation(state.get(FACING)));
    }
}