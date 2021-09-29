package com.stal111.valhelsia_structures.block;

import com.stal111.valhelsia_structures.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;

/**
 * Hanging Vines Body Block
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.HangingVinesBodyBlock
 *
 * @author Valhelsia Team
 * @version 16.0.4
 * @since 2020-10-16
 */
public class HangingVinesBodyBlock extends GrowingPlantBodyBlock {

    public static final BooleanProperty ATTACHED = ModBlockStateProperties.ATTACHED;

    public static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public HangingVinesBodyBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false);
        this.setDefaultState(this.stateContainer.getBaseState().with(ATTACHED, false));
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.offset(this.growthDirection.getOpposite());
        BlockState blockstate = world.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (!this.canGrowOn(block)) {
            return false;
        } else {
            return block == this.getTopPlantBlock() || block == this.getBodyPlantBlock() || blockstate.isSolidSide(world, blockpos, this.growthDirection) || blockstate.getBlock() instanceof LeavesBlock;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        boolean flag = context.getWorld().getBlockState(context.getPos().up()).getBlock() != ModBlocks.HANGING_VINES_BODY.get();
        return super.getStateForPlacement(context).with(ATTACHED, flag);
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        boolean flag = world.getBlockState(currentPos.up()).getBlock() != ModBlocks.HANGING_VINES_BODY.get();
        return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos).with(ATTACHED, flag);
    }

    @Override
    protected AbstractTopPlantBlock getTopPlantBlock() {
        return ModBlocks.HANGING_VINES.get();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(ATTACHED);
    }
}
