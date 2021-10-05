package com.stal111.valhelsia_structures.common.block;

import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.common.helper.VoxelShapeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Big Jar Top Block
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.BigJarTopBlock
 *
 * @author Valhelsia Team
 * @version 0.1.1
 * @since 2021-05-15
 */
public class BigJarTopBlock extends Block implements SimpleWaterloggedBlock {

    public static final IntegerProperty ROTATION = ModBlockStateProperties.ROTATION_0_7;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE = VoxelShapeHelper.add(0.0D, -16.0D, 0.0D, 0.0D, -16.0D, 0.0D, BigJarBlock.SHAPE);

    private BigJarBlock bigJarBlock;

    public BigJarTopBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(ROTATION, 0).setValue(WATERLOGGED, false));
    }

    @Nonnull
    @Override
    public String getDescriptionId() {
        return this.getJarBlock().getDescriptionId();
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE;
    }

    @Nonnull
    @Override
    public RenderShape getRenderShape(@Nonnull BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Nonnull
    @Override
    public BlockState updateShape(@Nonnull BlockState state, @Nonnull Direction direction, @Nonnull BlockState neighborState, @Nonnull LevelAccessor level, @Nonnull BlockPos currentPos, @Nonnull BlockPos neighborPos) {
        return direction == Direction.DOWN && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState().setValue(WATERLOGGED, flag);
    }

    @Override
    public boolean canSurvive(@Nonnull BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).getBlock() instanceof BigJarBlock;
    }

    private BigJarBlock getJarBlock() {
        if (this.bigJarBlock == null) {
            this.bigJarBlock = (BigJarBlock) ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ValhelsiaStructures.MOD_ID, Objects.requireNonNull(this.getRegistryName()).getPath().replace("_top", "")));
        }
        return this.bigJarBlock;
    }

    @Nonnull
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(ROTATION, rot.rotate(state.getValue(ROTATION), 8));
    }

    @Nonnull
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.setValue(ROTATION, mirror.mirror(state.getValue(ROTATION), 8));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ROTATION, WATERLOGGED);
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}