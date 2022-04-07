package com.stal111.valhelsia_structures.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import java.util.function.BiPredicate;
import java.util.function.Function;

public class BrightnessCombinerUtils {

    public static DoubleBlockCombiner.NeighborCombineResult<BlockPos> combineWithNeigbour(Function<BlockState, DoubleBlockCombiner.BlockType> typeFunction, Function<BlockState, Direction> directionFunction, DirectionProperty directionProperty, BlockState state, BlockAndTintGetter levelAccessor, BlockPos pos, BiPredicate<LevelAccessor, BlockPos> predicate) {
        if (predicate.test(Minecraft.getInstance().level, pos)) {
            return DoubleBlockCombiner.Combiner::acceptNone;
        } else {
            DoubleBlockCombiner.BlockType doubleblockcombiner$blocktype = typeFunction.apply(state);
            boolean flag = doubleblockcombiner$blocktype == DoubleBlockCombiner.BlockType.SINGLE;
            boolean flag1 = doubleblockcombiner$blocktype == DoubleBlockCombiner.BlockType.FIRST;

            if (!flag) {
                BlockPos blockpos = pos.relative(directionFunction.apply(state));
                BlockState blockstate = levelAccessor.getBlockState(blockpos);
                if (blockstate.is(state.getBlock())) {
                    DoubleBlockCombiner.BlockType doubleblockcombiner$blocktype1 = typeFunction.apply(blockstate);
                    if (doubleblockcombiner$blocktype1 != DoubleBlockCombiner.BlockType.SINGLE && doubleblockcombiner$blocktype != doubleblockcombiner$blocktype1 && blockstate.getValue(directionProperty) == state.getValue(directionProperty)) {
                        if (predicate.test(Minecraft.getInstance().level, blockpos)) {
                            return DoubleBlockCombiner.Combiner::acceptNone;
                        }

                        BlockPos s2 = flag1 ? pos : blockpos;
                        BlockPos s3 = flag1 ? blockpos : pos;
                        return new DoubleBlockCombiner.NeighborCombineResult.Double<>(s2, s3);
                    }
                }

            }
            return new DoubleBlockCombiner.NeighborCombineResult.Single<>(pos);
        }
    }
}
