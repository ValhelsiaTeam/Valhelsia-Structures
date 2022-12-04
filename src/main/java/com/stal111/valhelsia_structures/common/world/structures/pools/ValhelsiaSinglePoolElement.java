package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.common.block.entity.SpecialSpawnerBlockEntity;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.core.init.world.ModStructurePoolElementTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author Valhelsia Team
 * @since 2022-11-18
 */
public class ValhelsiaSinglePoolElement extends SinglePoolElement {

    private static final Codec<Either<ResourceLocation, StructureTemplate>> TEMPLATE_CODEC = Codec.of(ValhelsiaSinglePoolElement::encodeTemplate, ResourceLocation.CODEC.map(Either::left));

    public static final Codec<ValhelsiaSinglePoolElement> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(valhelsiaTemplateCodec(), processorsCodec(), projectionCodec(), TerrainAdjustment.CODEC.optionalFieldOf("terrain_adjustment").forGetter(element -> {
            return Optional.ofNullable(element.terrainAdjustment);
        })).apply(instance, (either, processorListHolder, projection, terrainAdjustment) -> {
            return new ValhelsiaSinglePoolElement(either, processorListHolder, projection, terrainAdjustment.orElse(null));
        });
    });

    private static final List<EntityType<?>> SPAWNER_ENTITY = List.of(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.SPIDER);

    private final TerrainAdjustment terrainAdjustment;

    public ValhelsiaSinglePoolElement(Either<ResourceLocation, StructureTemplate> resourceLocation, Holder<StructureProcessorList> processors, StructureTemplatePool.Projection projection, @Nullable TerrainAdjustment terrainAdjustment) {
        super(resourceLocation, processors, projection);
        this.terrainAdjustment = terrainAdjustment;
    }

    private static <T> DataResult<T> encodeTemplate(Either<ResourceLocation, StructureTemplate> either, DynamicOps<T> dynamicOps, T t) {
        Optional<ResourceLocation> optional = either.left();
        return optional.isEmpty() ? DataResult.error("Can not serialize a runtime pool element") : ResourceLocation.CODEC.encode(optional.get(), dynamicOps, t);
    }

    protected static <E extends ValhelsiaSinglePoolElement> RecordCodecBuilder<E, Either<ResourceLocation, StructureTemplate>> valhelsiaTemplateCodec() {
        return TEMPLATE_CODEC.fieldOf("location").forGetter((element) -> {
            return element.template;
        });
    }

    @Override
    public boolean place(StructureTemplateManager templateManager, @NotNull WorldGenLevel level, @NotNull StructureManager structureManager, @NotNull ChunkGenerator generator, @NotNull BlockPos offset, @NotNull BlockPos pos, @NotNull Rotation rotation, @NotNull BoundingBox box, @NotNull RandomSource random, boolean keepJigsaws) {
        StructureTemplate structuretemplate = this.template.map(templateManager::getOrCreate, Function.identity());
        StructurePlaceSettings structureplacesettings = this.getSettings(rotation, box, keepJigsaws);

        if (!structuretemplate.placeInWorld(level, offset, pos, structureplacesettings, random, 18)) {
            return false;
        }

        for (StructureTemplate.StructureBlockInfo info : structuretemplate.filterBlocks(offset, this.getSettings(rotation, box, keepJigsaws), Blocks.STRUCTURE_BLOCK)) {
            StructureMode mode = StructureMode.valueOf(info.nbt.getString("mode"));

            if (mode == StructureMode.DATA) {
                this.handleDataMarker(level, info, info.pos, rotation, random, box);
            }
        }

        return true;
    }

    @Override
    public void handleDataMarker(@NotNull LevelAccessor level, @NotNull StructureTemplate.StructureBlockInfo blockInfo, @NotNull BlockPos pos, @NotNull Rotation rotation, @NotNull RandomSource random, @NotNull BoundingBox box) {
        String data = blockInfo.nbt.getString("metadata");

        if (data.startsWith("spawner:")) {
            level.removeBlock(pos, false);
            level.setBlock(pos, Blocks.SPAWNER.defaultBlockState(), 2);

            if (level.getBlockEntity(pos) instanceof SpawnerBlockEntity blockEntity) {
                EntityType<?> entityType = switch (data) {
                    case "spawner:zombie_or_skeleton_or_spider" ->
                            SPAWNER_ENTITY.get(random.nextInt(SPAWNER_ENTITY.size()));
                    case "spawner:zombie" -> EntityType.ZOMBIE;
                    case "spawner:skeleton" -> EntityType.SKELETON;
                    case "spawner:spider" -> EntityType.SPIDER;
                    default -> null;
                };

                if (entityType != null) {
                    blockEntity.getSpawner().setEntityId(entityType);
                }
            }
        } else if (data.startsWith("special_spawner:")) {
            level.removeBlock(pos, false);
            level.setBlock(pos, ModBlocks.SPECIAL_SPAWNER.get().defaultBlockState(), 2);

            if (level.getBlockEntity(pos) instanceof SpecialSpawnerBlockEntity blockEntity) {
                EntityType<?> entityType = switch (data) {
                    case "special_spawner:zombie_or_skeleton_or_spider" ->
                            SPAWNER_ENTITY.get(random.nextInt(SPAWNER_ENTITY.size()));
                    case "special_spawner:drowned" -> EntityType.DROWNED;
                    default -> null;
                };

                if (entityType != null) {
                    blockEntity.getSpawner().setEntityId(entityType);
                }
            }
        } else if (data.equals("sculk_sensor")) {
            level.removeBlock(pos, false);
            level.setBlock(pos, Blocks.SCULK_SENSOR.defaultBlockState(), 2);
        } else if (data.equals("sculk_shrieker")) {
            level.removeBlock(pos, false);
            level.setBlock(pos, Blocks.SCULK_SHRIEKER.defaultBlockState().setValue(BlockStateProperties.CAN_SUMMON, true), 2);
        }

        super.handleDataMarker(level, blockInfo, pos, rotation, random, box);
    }

    @NotNull
    public List<StructureTemplate.StructureBlockInfo> getDataMarkers(StructureTemplateManager templateManager, @NotNull BlockPos pos, @NotNull Rotation rotation, boolean relativePosition) {
        StructureTemplate structuretemplate = this.template.map(templateManager::getOrCreate, Function.identity());
        List<StructureTemplate.StructureBlockInfo> structureBlockInfos = structuretemplate.filterBlocks(pos, new StructurePlaceSettings().setRotation(rotation), Blocks.STRUCTURE_BLOCK, relativePosition);
        List<StructureTemplate.StructureBlockInfo> markers = new ArrayList<>();

        for (StructureTemplate.StructureBlockInfo info : structureBlockInfos) {
            StructureMode mode = StructureMode.valueOf(info.nbt.getString("mode"));

            if (mode == StructureMode.DATA) {
                markers.add(info);
            }
        }

        return markers;
    }

    @Nonnull
    @Override
    protected StructurePlaceSettings getSettings(@Nonnull Rotation rotation, @Nonnull BoundingBox box, boolean keepJigsawBlocks) {
        return super.getSettings(rotation, box, keepJigsawBlocks).popProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
    }

    public TerrainAdjustment getTerrainAdjustment() {
        return this.terrainAdjustment != null ? this.terrainAdjustment : TerrainAdjustment.NONE;
    }

    @NotNull
    @Override
    public StructurePoolElementType<?> getType() {
        return ModStructurePoolElementTypes.VALHELSIA_SINGLE.get();
    }
}
