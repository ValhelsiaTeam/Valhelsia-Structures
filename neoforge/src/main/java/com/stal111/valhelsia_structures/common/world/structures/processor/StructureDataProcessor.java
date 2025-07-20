package com.stal111.valhelsia_structures.common.world.structures.processor;

import com.mojang.serialization.MapCodec;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.core.init.world.ModStructureProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.SpawnData;
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

import java.util.List;
import java.util.Optional;

public class StructureDataProcessor extends StructureProcessor {

    public static final StructureDataProcessor INSTANCE = new StructureDataProcessor();

    public static final MapCodec<StructureDataProcessor> CODEC = MapCodec.unit(() -> StructureDataProcessor.INSTANCE);

    private static final List<EntityType<?>> SPAWNER_ENTITY = List.of(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.SPIDER);

    @Override
    public StructureTemplate.StructureBlockInfo process(@NotNull LevelReader level, @NotNull BlockPos piecePos, @NotNull BlockPos pieceBottomCenterPos, @NotNull StructureTemplate.StructureBlockInfo blockInfo, @NotNull StructureTemplate.StructureBlockInfo relativeBlockInfo, @NotNull StructurePlaceSettings placeSettings, @Nullable StructureTemplate template) {
        if (relativeBlockInfo.state().is(Blocks.STRUCTURE_BLOCK)) {
            StructureMode mode = StructureMode.valueOf(relativeBlockInfo.nbt().getString("mode"));
            RandomSource random = placeSettings.getRandom(relativeBlockInfo.pos());
            BlockPos pos = relativeBlockInfo.pos();

            if (mode == StructureMode.DATA) {
                String data = relativeBlockInfo.nbt().getString("metadata");

                if (data.startsWith("spawner:")) {
                    EntityType<?> entityType = switch (data) {
                        case "spawner:zombie_or_skeleton_or_spider" ->
                                SPAWNER_ENTITY.get(random.nextInt(SPAWNER_ENTITY.size()));
                        case "spawner:zombie" -> EntityType.ZOMBIE;
                        case "spawner:skeleton" -> EntityType.SKELETON;
                        case "spawner:spider" -> EntityType.SPIDER;
                        default -> null;
                    };
                    CompoundTag entityTag = new CompoundTag();
                    entityTag.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(entityType).toString());

                    CompoundTag spawnerTag = new CompoundTag();

                    spawnerTag.put("SpawnPotentials", SpawnData.LIST_CODEC.encodeStart(NbtOps.INSTANCE, SimpleWeightedRandomList.single(new SpawnData(entityTag, Optional.empty(), Optional.empty()))).getOrThrow());

                    return new StructureTemplate.StructureBlockInfo(pos, Blocks.SPAWNER.defaultBlockState(), spawnerTag);
                } else if (data.startsWith("special_spawner:")) {
                    EntityType<?> entityType = switch (data) {
                        case "special_spawner:zombie_or_skeleton_or_spider" ->
                                SPAWNER_ENTITY.get(random.nextInt(SPAWNER_ENTITY.size()));
                        case "special_spawner:drowned" -> EntityType.DROWNED;
                        default -> null;
                    };

                    CompoundTag entityTag = new CompoundTag();
                    entityTag.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(entityType).toString());

                    CompoundTag spawnerTag = new CompoundTag();

                    spawnerTag.put("SpawnPotentials", SpawnData.LIST_CODEC.encodeStart(NbtOps.INSTANCE, SimpleWeightedRandomList.single(new SpawnData(entityTag, Optional.empty(), Optional.empty()))).getOrThrow());

                    return new StructureTemplate.StructureBlockInfo(pos, ModBlocks.SPECIAL_SPAWNER.get().defaultBlockState(), spawnerTag);
                } else if (data.equals("sculk_sensor")) {
                    return new StructureTemplate.StructureBlockInfo(pos, Blocks.SCULK_SENSOR.defaultBlockState(), null);
                } else if (data.equals("sculk_shrieker")) {
                    return new StructureTemplate.StructureBlockInfo(pos, Blocks.SCULK_SHRIEKER.defaultBlockState().setValue(BlockStateProperties.CAN_SUMMON, true), null);
                }
            }
        }

        return super.process(level, piecePos, pieceBottomCenterPos, blockInfo, relativeBlockInfo, placeSettings, template);
    }

    @Override
    protected @NotNull StructureProcessorType<?> getType() {
        return ModStructureProcessors.STRUCTURE_DATA.get();
    }

    private void setBlock(LevelReader level, BlockPos pos, BlockState state) {
        level.getChunk(pos).setBlockState(pos, state, false);
    }
}
