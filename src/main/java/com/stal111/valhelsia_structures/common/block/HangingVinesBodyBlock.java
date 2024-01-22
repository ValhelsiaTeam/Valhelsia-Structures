package com.stal111.valhelsia_structures.common.block;

import com.mojang.serialization.MapCodec;
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Hanging Vines Body Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.HangingVinesBodyBlock
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.1.0
 * @since 2020-10-16
 */
public class HangingVinesBodyBlock extends GrowingPlantBodyBlock {

    public static final MapCodec<HangingVinesBodyBlock> CODEC = simpleCodec(HangingVinesBodyBlock::new);

    public static final BooleanProperty ATTACHED = ModBlockStateProperties.ATTACHED;

    public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public HangingVinesBodyBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false);
        this.registerDefaultState(this.getStateDefinition().any().setValue(ATTACHED, false));
    }

    @Override
    public boolean canSurvive(@Nonnull BlockState state, LevelReader level, BlockPos pos) {
        BlockPos offsetPos = pos.relative(this.growthDirection.getOpposite());
        BlockState offsetState = level.getBlockState(offsetPos);

        if (!this.canAttachTo(offsetState)) {
            return false;
        }
        return offsetState.is(this.getHeadBlock()) || offsetState.is(this.getBodyBlock()) || offsetState.isFaceSturdy(level, offsetPos, this.growthDirection) || offsetState.is(BlockTags.LEAVES);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean flag = context.getLevel().getBlockState(context.getClickedPos().above()).getBlock() != this.getBodyBlock();
        return Objects.requireNonNull(super.getStateForPlacement(context)).setValue(ATTACHED, flag);
    }

    @Override
    protected @NotNull MapCodec<? extends GrowingPlantBodyBlock> codec() {
        return CODEC;
    }

    @Nonnull
    @Override
    public BlockState updateShape(@Nonnull BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, LevelAccessor level, BlockPos currentPos, @Nonnull BlockPos facingPos) {
        boolean flag = !level.getBlockState(currentPos.above()).is(this.getBodyBlock());
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos).setValue(ATTACHED, flag);
    }

    @Nonnull
    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return ModBlocks.HANGING_VINES.get();
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ATTACHED);
    }
}
