package com.stal111.valhelsia_structures.common.world.structures.processor;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.core.init.world.ModStructureProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Valhelsia Team
 * @since 2022-11-30
 */
public class SpawnerRoomLegProcessor extends StructureProcessor {

    public static final MapCodec<SpawnerRoomLegProcessor> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockState.CODEC.fieldOf("block").forGetter(processor -> {
                return processor.block;
            }),
            BlockState.CODEC.fieldOf("slab_block").forGetter(processor -> {
                return processor.slabBlock;
            })
    ).apply(instance, SpawnerRoomLegProcessor::new));

    private final BlockState block;
    private final BlockState slabBlock;

    public SpawnerRoomLegProcessor(BlockState block, BlockState slabBlock) {
        this.block = block;
        this.slabBlock = slabBlock;
    }

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(@NotNull LevelReader level, @NotNull BlockPos piecePos, @NotNull BlockPos pieceBottomCenterPos, @NotNull StructureTemplate.StructureBlockInfo blockInfo, @NotNull StructureTemplate.StructureBlockInfo relativeBlockInfo, @NotNull StructurePlaceSettings placeSettings, @Nullable StructureTemplate template) {
        if (relativeBlockInfo.state().is(Blocks.STRUCTURE_BLOCK)) {
            StructureMode mode = StructureMode.valueOf(relativeBlockInfo.nbt().getString("mode"));

            if (mode == StructureMode.DATA) {
                String data = relativeBlockInfo.nbt().getString("metadata");

                if (data.startsWith("support_leg")) {
                    BlockPos.MutableBlockPos mutable = relativeBlockInfo.pos().below().mutable();

                    while (mutable.getY() > level.getMinBuildHeight() && mutable.getY() < level.getMaxBuildHeight()) {
                        if (!this.placeBlock(level, mutable, this.block)) {
                            break;
                        }

                        mutable.move(Direction.DOWN);
                    }


                    Direction direction = Direction.byName(data.split(";")[1]);

                    if (direction != null) {
                        direction = placeSettings.getRotation().rotate(direction);

                        mutable = relativeBlockInfo.pos().below().mutable().move(direction);
                        this.placeBlock(level, mutable, this.slabBlock);

                        mutable.move(direction.getClockWise());
                        this.placeBlock(level, mutable, this.slabBlock);

                        mutable.move(direction.getOpposite());
                        this.placeBlock(level, mutable, this.slabBlock);
                    }

                    return new StructureTemplate.StructureBlockInfo(relativeBlockInfo.pos(), Blocks.COBBLESTONE.defaultBlockState(), null);
                }
            }
        }

        return super.process(level, piecePos, pieceBottomCenterPos, blockInfo, relativeBlockInfo, placeSettings, template);
    }

    private boolean placeBlock(LevelReader level, BlockPos pos, BlockState state) {
        BlockState currentState = level.getBlockState(pos);

        if (currentState.isAir() || !currentState.getFluidState().isEmpty()) {
            level.getChunk(pos).setBlockState(pos, state, false);

            return true;
        }

        return false;
    }

    @NotNull
    @Override
    protected StructureProcessorType<?> getType() {
        return ModStructureProcessors.SPAWNER_ROOM_LEG.get();
    }
}
