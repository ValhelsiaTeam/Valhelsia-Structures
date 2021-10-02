package com.stal111.valhelsia_structures.core.mixin;

import com.stal111.valhelsia_structures.utils.TorchTransformationHandler;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nullable;

/**
 * Torch Block Mixin <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.mixin.TorchBlockMixin
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
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
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        if (fluidState.getType() == Fluids.WATER && TorchTransformationHandler.hasDousedVersion(this)) {
            return TorchTransformationHandler.getDousedTorchFor(this).defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true);
        }
        return super.getStateForPlacement(context);
    }
}
