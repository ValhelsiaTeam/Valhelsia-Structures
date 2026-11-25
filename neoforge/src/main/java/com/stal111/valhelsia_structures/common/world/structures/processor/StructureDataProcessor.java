package com.stal111.valhelsia_structures.common.world.structures.processor;

import com.mojang.serialization.MapCodec;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.List;
import java.util.Optional;

public class StructureDataProcessor implements StructureProcessor {

    public static final StructureDataProcessor INSTANCE = new StructureDataProcessor();

    public static final MapCodec<StructureDataProcessor> CODEC = MapCodec.unit(() -> StructureDataProcessor.INSTANCE);

    private static final List<EntityType<?>> SPAWNER_ENTITY = List.of(EntityTypes.ZOMBIE, EntityTypes.SKELETON, EntityTypes.SPIDER);

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader level, BlockPos targetPosition, BlockPos referencePos, BlockPos templateRelativePos, StructureTemplate.StructureBlockInfo processedBlockInfo, StructurePlaceSettings settings) {
        if (processedBlockInfo.state().is(Blocks.STRUCTURE_BLOCK)) {
            StructureMode mode = processedBlockInfo.nbt().read("mode", StructureMode.LEGACY_CODEC).orElseThrow();
            RandomSource random = settings.getRandom(processedBlockInfo.pos());
            BlockPos pos = processedBlockInfo.pos();

            if (mode == StructureMode.DATA) {
                String data = processedBlockInfo.nbt().getString("metadata").orElseThrow();

                if (data.startsWith("spawner:")) {
                    EntityType<?> entityType = switch (data) {
                        case "spawner:zombie_or_skeleton_or_spider" ->
                                SPAWNER_ENTITY.get(random.nextInt(SPAWNER_ENTITY.size()));
                        case "spawner:drowned" -> EntityTypes.DROWNED;
                        case "spawner:zombie" -> EntityTypes.ZOMBIE;
                        case "spawner:skeleton" -> EntityTypes.SKELETON;
                        case "spawner:spider" -> EntityTypes.SPIDER;
                        default -> null;
                    };
                    CompoundTag entityTag = new CompoundTag();
                    entityTag.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(entityType).toString());

                    CompoundTag spawnerTag = new CompoundTag();

                    spawnerTag.store("SpawnPotentials", SpawnData.LIST_CODEC, WeightedList.of(new SpawnData(entityTag, Optional.empty(), Optional.empty())));

                    return new StructureTemplate.StructureBlockInfo(pos, Blocks.SPAWNER.defaultBlockState(), spawnerTag);
                } else if (data.startsWith("special_spawner:")) {
                    EntityType<?> entityType = switch (data) {
                        case "special_spawner:zombie_or_skeleton_or_spider" ->
                                SPAWNER_ENTITY.get(random.nextInt(SPAWNER_ENTITY.size()));
                        case "special_spawner:drowned" -> EntityTypes.DROWNED;
                        default -> null;
                    };

                    CompoundTag entityTag = new CompoundTag();
                    entityTag.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(entityType).toString());

                    CompoundTag spawnerTag = new CompoundTag();

                    spawnerTag.store("SpawnPotentials", SpawnData.LIST_CODEC, WeightedList.of(new SpawnData(entityTag, Optional.empty(), Optional.empty())));

                    return new StructureTemplate.StructureBlockInfo(pos, ModBlocks.SPECIAL_SPAWNER.get().defaultBlockState(), spawnerTag);
                } else if (data.equals("sculk_sensor")) {
                    return new StructureTemplate.StructureBlockInfo(pos, Blocks.SCULK_SENSOR.defaultBlockState(), null);
                } else if (data.equals("sculk_shrieker")) {
                    return new StructureTemplate.StructureBlockInfo(pos, Blocks.SCULK_SHRIEKER.defaultBlockState().setValue(BlockStateProperties.CAN_SUMMON, true), null);
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
