package com.stal111.valhelsia_structures.common.world.structures.processor;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.init.world.ModStructureProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Valhelsia Team
 * @since 2022-10-31
 */
public class RemoveWaterProcessor extends StructureProcessor {

    protected static final RemoveWaterProcessor INSTANCE = new RemoveWaterProcessor();

    public static final Codec<RemoveWaterProcessor> CODEC = Codec.unit(() -> {
        return RemoveWaterProcessor.INSTANCE;
    });

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(@Nonnull LevelReader level, @Nonnull BlockPos jigsawPiecePos, @Nonnull BlockPos jigsawPieceBottomCenterPos, @Nonnull StructureTemplate.StructureBlockInfo blockInfoLocal, @Nonnull StructureTemplate.StructureBlockInfo blockInfoGlobal, @Nonnull StructurePlaceSettings settings, @Nullable StructureTemplate template) {
        if (blockInfoGlobal.state.hasProperty(BlockStateProperties.WATERLOGGED) && !blockInfoGlobal.state.getValue(BlockStateProperties.WATERLOGGED)) {
            ChunkPos chunkPos = new ChunkPos(blockInfoGlobal.pos);
            ChunkAccess chunk = level.getChunk(chunkPos.x, chunkPos.z);
            int sectionIndex = chunk.getSectionIndex(blockInfoGlobal.pos.getY());

            if (sectionIndex < 0) {
                return blockInfoGlobal;
            }

            LevelChunkSection section = chunk.getSection(sectionIndex);

            if (this.getFluidState(section, blockInfoGlobal.pos).is(FluidTags.WATER)) {
                this.setBlock(section, blockInfoGlobal.pos, blockInfoGlobal.state);
            }

            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            for (Direction direction : Direction.values()) {
                mutable.set(blockInfoGlobal.pos).move(direction);
                if (chunkPos.x != mutable.getX() >> 4 || chunkPos.z != mutable.getZ() >> 4) {
                    chunkPos = new ChunkPos(mutable);
                    chunk = level.getChunk(chunkPos.x, chunkPos.z);
                    sectionIndex = chunk.getSectionIndex(mutable.getY());
                    if (sectionIndex < 0) {
                        return blockInfoGlobal;
                    }
                    section = chunk.getSection(sectionIndex);
                }

                if (this.getFluidState(section, mutable).is(FluidTags.WATER)) {
                    BlockState state = level.getBlockState(mutable);
                    if (!(state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED))) {
                        this.setBlock(section, mutable, Blocks.STONE.defaultBlockState());
                    }
                }
            }
        }

        return blockInfoGlobal;
    }

    private void setBlock(LevelChunkSection section, BlockPos pos, BlockState state) {
        section.setBlockState(SectionPos.sectionRelative(pos.getX()), SectionPos.sectionRelative(pos.getY()), SectionPos.sectionRelative(pos.getZ()), state);
    }

    private FluidState getFluidState(LevelChunkSection section, BlockPos pos) {
        return section.getFluidState(SectionPos.sectionRelative(pos.getX()), SectionPos.sectionRelative(pos.getY()), SectionPos.sectionRelative(pos.getZ()));
    }

    @Nonnull
    @Override
    protected StructureProcessorType<?> getType() {
        return ModStructureProcessors.REMOVE_WATER.get();
    }
}
