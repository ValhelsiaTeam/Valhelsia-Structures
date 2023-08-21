package com.stal111.valhelsia_structures.common.world.structures.processor;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.core.init.world.ModStructureProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * @author Valhelsia Team
 * @since 2022-12-12
 */
public class WitchHutLegProcessor extends StructureProcessor {

    public static final WitchHutLegProcessor INSTANCE = new WitchHutLegProcessor();

    public static final Codec<WitchHutLegProcessor> CODEC = Codec.unit(() -> {
        return WitchHutLegProcessor.INSTANCE;
    });

    /**
     * Use {@link WitchHutLegProcessor#INSTANCE} instead of creating a new processor instance.
     */
    private WitchHutLegProcessor() {

    }

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(@NotNull LevelReader level, @NotNull BlockPos piecePos, @NotNull BlockPos pieceBottomCenterPos, @NotNull StructureTemplate.StructureBlockInfo blockInfo, @NotNull StructureTemplate.StructureBlockInfo relativeBlockInfo, @NotNull StructurePlaceSettings placeSettings, @Nullable StructureTemplate template) {
        if (relativeBlockInfo.state().is(Blocks.STRUCTURE_BLOCK)) {
            StructureMode mode = StructureMode.valueOf(relativeBlockInfo.nbt().getString("mode"));

            if (mode == StructureMode.DATA) {
                String data = relativeBlockInfo.nbt().getString("metadata");

                if (data.equals("support_leg")) {
                    BlockPos.MutableBlockPos mutable = relativeBlockInfo.pos().below().mutable();
                    BlockState currentState = level.getBlockState(mutable);

                    while (mutable.getY() > level.getMinBuildHeight() && mutable.getY() < level.getMaxBuildHeight() && (currentState.isAir() || !currentState.getFluidState().isEmpty())) {
                        level.getChunk(mutable).setBlockState(mutable, ModBlocks.WOODEN_POSTS.get(ModBlocks.WoodType.OAK).get().defaultBlockState()
                                        .setValue(BlockStateProperties.WATERLOGGED, currentState.hasProperty(BlockStateProperties.WATERLOGGED) && currentState.getValue(BlockStateProperties.WATERLOGGED))
                                        .setValue(BlockStateProperties.AXIS, Direction.Axis.Y),
                                false);

                        mutable.move(Direction.DOWN);
                        currentState = level.getBlockState(mutable);
                    }

                    return new StructureTemplate.StructureBlockInfo(relativeBlockInfo.pos(), ModBlocks.WOODEN_POSTS.get(ModBlocks.WoodType.OAK).get().defaultBlockState()
                            .setValue(BlockStateProperties.WATERLOGGED, level.getBlockState(relativeBlockInfo.pos()).hasProperty(BlockStateProperties.WATERLOGGED) && level.getBlockState(relativeBlockInfo.pos()).getValue(BlockStateProperties.WATERLOGGED))
                            .setValue(BlockStateProperties.AXIS, Direction.Axis.Y),
                            null);
                } else if (data.equals("support_leg:log")) {
                    BlockPos.MutableBlockPos mutable = relativeBlockInfo.pos().below().mutable();
                    BlockState currentState = level.getBlockState(mutable);

                    while (mutable.getY() > level.getMinBuildHeight() && mutable.getY() < level.getMaxBuildHeight() && (currentState.isAir() || !currentState.getFluidState().isEmpty())) {
                        level.getChunk(mutable).setBlockState(mutable, Blocks.OAK_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Y), false);

                        mutable.move(Direction.DOWN);
                        currentState = level.getBlockState(mutable);
                    }

                    return new StructureTemplate.StructureBlockInfo(relativeBlockInfo.pos(), Blocks.OAK_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Y), null);
                }
            }
        }

        return super.process(level, piecePos, pieceBottomCenterPos, blockInfo, relativeBlockInfo, placeSettings, template);
    }

    @Nonnull
    @Override
    protected StructureProcessorType<?> getType() {
        return ModStructureProcessors.WITCH_HUT_LEG.get();
    }
}
