package com.stal111.valhelsia_structures.common.world.structures.processor;

import com.mojang.serialization.MapCodec;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.Nullable;

/**
 * @author Valhelsia Team
 * @since 2022-12-12
 */
public class WitchHutLegProcessor implements StructureProcessor {

    public static final WitchHutLegProcessor INSTANCE = new WitchHutLegProcessor();

    public static final MapCodec<WitchHutLegProcessor> CODEC = MapCodec.unit(() -> WitchHutLegProcessor.INSTANCE);

    /**
     * Use {@link WitchHutLegProcessor#INSTANCE} instead of creating a new processor instance.
     */
    private WitchHutLegProcessor() {

    }

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos targetPosition, BlockPos referencePos, BlockPos templateRelativePos, StructureTemplate.StructureBlockInfo processedBlockInfo, StructurePlaceSettings settings) {
        if (processedBlockInfo.state().is(Blocks.STRUCTURE_BLOCK)) {
            StructureMode mode = processedBlockInfo.nbt().read("mode", StructureMode.LEGACY_CODEC).orElseThrow();

            if (mode == StructureMode.DATA) {
                String data = processedBlockInfo.nbt().getString("metadata").orElseThrow();

                if (data.equals("support_leg")) {
                    BlockPos.MutableBlockPos mutable = processedBlockInfo.pos().below().mutable();
                    BlockState currentState = level.getBlockState(mutable);

                    while (mutable.getY() > level.getMinY() && mutable.getY() < level.getMaxY() && (currentState.isAir() || !currentState.getFluidState().isEmpty())) {
                        level.getChunk(mutable).setBlockState(mutable, ModBlocks.WOODEN_POSTS.get(ModBlocks.WoodType.OAK).get().defaultBlockState()
                                        .setValue(BlockStateProperties.WATERLOGGED, currentState.hasProperty(BlockStateProperties.WATERLOGGED) && currentState.getValue(BlockStateProperties.WATERLOGGED))
                                        .setValue(BlockStateProperties.AXIS, Direction.Axis.Y),
                                3);

                        mutable.move(Direction.DOWN);
                        currentState = level.getBlockState(mutable);
                    }

                    return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), ModBlocks.WOODEN_POSTS.get(ModBlocks.WoodType.OAK).get().defaultBlockState()
                            .setValue(BlockStateProperties.WATERLOGGED, level.getBlockState(processedBlockInfo.pos()).hasProperty(BlockStateProperties.WATERLOGGED) && level.getBlockState(processedBlockInfo.pos()).getValue(BlockStateProperties.WATERLOGGED))
                            .setValue(BlockStateProperties.AXIS, Direction.Axis.Y),
                            null);
                } else if (data.equals("support_leg:log")) {
                    BlockPos.MutableBlockPos mutable = processedBlockInfo.pos().below().mutable();
                    BlockState currentState = level.getBlockState(mutable);

                    while (mutable.getY() > level.getMinY() && mutable.getY() < level.getMaxY() && (currentState.isAir() || !currentState.getFluidState().isEmpty())) {
                        level.getChunk(mutable).setBlockState(mutable, Blocks.OAK_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Y), 3);

                        mutable.move(Direction.DOWN);
                        currentState = level.getBlockState(mutable);
                    }

                    return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), Blocks.OAK_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Y), null);
                }
            }
        }

        return processedBlockInfo;
    }

    @Override
    public MapCodec<? extends StructureProcessor> codec() {
        return CODEC;
    }
}
