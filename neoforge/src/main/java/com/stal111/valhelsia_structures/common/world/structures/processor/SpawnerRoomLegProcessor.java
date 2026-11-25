package com.stal111.valhelsia_structures.common.world.structures.processor;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

/**
 * @author Valhelsia Team
 * @since 2022-11-30
 */
public record SpawnerRoomLegProcessor(BlockState block, BlockState slabBlock) implements StructureProcessor {

    public static final MapCodec<SpawnerRoomLegProcessor> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockState.CODEC.fieldOf("block").forGetter(SpawnerRoomLegProcessor::block),
            BlockState.CODEC.fieldOf("slab_block").forGetter(SpawnerRoomLegProcessor::slabBlock)
    ).apply(instance, SpawnerRoomLegProcessor::new));

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos targetPosition, BlockPos referencePos, BlockPos templateRelativePos, StructureTemplate.StructureBlockInfo processedBlockInfo, StructurePlaceSettings settings) {
        if (processedBlockInfo.state().is(Blocks.STRUCTURE_BLOCK)) {
            StructureMode mode = processedBlockInfo.nbt().read("mode", StructureMode.LEGACY_CODEC).orElseThrow();

            if (mode == StructureMode.DATA) {
                String data = processedBlockInfo.nbt().getString("metadata").orElseThrow();

                if (data.startsWith("support_leg")) {
                    BlockPos.MutableBlockPos mutable = processedBlockInfo.pos().below().mutable();

                    while (mutable.getY() > level.getMinY() && mutable.getY() < level.getMaxY()) {
                        if (!this.placeBlock(level, mutable, this.block)) {
                            break;
                        }

                        mutable.move(Direction.DOWN);
                    }


                    Direction direction = Direction.byName(data.split(";")[1]);

                    if (direction != null) {
                        direction = settings.getRotation().rotate(direction);

                        mutable = processedBlockInfo.pos().below().mutable().move(direction);
                        this.placeBlock(level, mutable, this.slabBlock);

                        mutable.move(direction.getClockWise());
                        this.placeBlock(level, mutable, this.slabBlock);

                        mutable.move(direction.getOpposite());
                        this.placeBlock(level, mutable, this.slabBlock);
                    }

                    return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), Blocks.COBBLESTONE.defaultBlockState(), null);
                }
            }
        }

        return processedBlockInfo;
    }

    @Override
    public MapCodec<? extends StructureProcessor> codec() {
        return CODEC;
    }

    private boolean placeBlock(LevelReader level, BlockPos pos, BlockState state) {
        BlockState currentState = level.getBlockState(pos);

        if (currentState.isAir() || !currentState.getFluidState().isEmpty()) {
            level.getChunk(pos).setBlockState(pos, state, 3);

            return true;
        }

        return false;
    }
}
