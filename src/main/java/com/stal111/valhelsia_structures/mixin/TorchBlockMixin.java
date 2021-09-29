package com.stal111.valhelsia_structures.mixin;

import com.stal111.valhelsia_structures.utils.TorchTransformationHandler;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

/**
 * Torch Block Mixin
 * Valhelsia Structures - com.stal111.valhelsia_structures.mixin.TorchBlockMixin
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-09
 */
@Mixin(TorchBlock.class)
public class TorchBlockMixin extends Block {

    public TorchBlockMixin(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getWorld().getFluidState(context.getPos());
        if (fluidState.getFluid() == Fluids.WATER && TorchTransformationHandler.hasDousedVersion(this)) {
            return TorchTransformationHandler.getDousedTorchFor(this).getDefaultState().with(BlockStateProperties.WATERLOGGED, true);
        }
        return super.getStateForPlacement(context);
    }
}
