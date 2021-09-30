package com.stal111.valhelsia_structures.block;

import com.google.common.collect.ImmutableMap;
import com.stal111.valhelsia_structures.block.properties.ModBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.common.helper.VoxelShapeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Post Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.PostBlock
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 */
public class PostBlock extends RotatedPillarBlock implements SimpleWaterloggedBlock {

    public static final BooleanProperty ATTACHED = ModBlockStateProperties.ATTACHED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private final Supplier<? extends Block> logBlock;

    public static final Map<Direction.Axis, VoxelShape> SHAPES = ImmutableMap.of(
            Direction.Axis.Y, Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D),
            Direction.Axis.Z, Block.box(3.0D, 3.0D, 0.0D, 13.0D, 13.0D, 16.0D),
            Direction.Axis.X, Block.box(0.0D, 3.0D, 3.0D, 16.0D, 13.0D, 13.0D));

    public PostBlock(Supplier<? extends Block> logBlock) {
        super(Properties.copy(logBlock.get()).noOcclusion());
        this.logBlock = logBlock;
        this.registerDefaultState(this.getStateDefinition().any().setValue(ATTACHED, false).setValue(WATERLOGGED, false));
    }

    public PostBlock(ResourceLocation logBlock, Properties properties) {
        super(properties.noOcclusion());
        this.logBlock = () -> ForgeRegistries.BLOCKS.getValue(logBlock);
        this.registerDefaultState(this.getStateDefinition().any().setValue(ATTACHED, false).setValue(WATERLOGGED, false));
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        VoxelShape shape = SHAPES.get(state.getValue(AXIS));
        return state.getValue(ATTACHED) ? VoxelShapeHelper.add(0, -3, 0, 0, -3, 0, shape) : shape;
    }

    public Block getLogBlock() {
        return logBlock.get();
    }

    @Override
    public void setPlacedBy(@Nonnull Level level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nullable LivingEntity placer, @Nonnull ItemStack stack) {
        if (shouldAttach(level, pos)) {
            level.setBlock(pos, state.setValue(ATTACHED, true), 2);
        }
        super.setPlacedBy(level, pos, state, placer, stack);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return Objects.requireNonNull(super.getStateForPlacement(context)).setValue(WATERLOGGED, flag);
    }

    @Nonnull
    @Override
    public BlockState updateShape(@Nonnull BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull LevelAccessor level, @Nonnull BlockPos currentPos, @Nonnull BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    public static boolean shouldAttach(Level world, BlockPos pos) {
        return world.getBlockState(pos.below()).isFaceSturdy(world, pos.below(), Direction.UP) && world.getBlockState(pos).getValue(AXIS) != Direction.Axis.Y;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ATTACHED, AXIS, WATERLOGGED);
    }

    @Override
    public boolean isPathfindable(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull PathComputationType type) {
        return false;
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
