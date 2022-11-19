package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
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
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Valhelsia Team
 * @since 2022-11-18
 */
public class ValhelsiaSinglePoolElement extends SinglePoolElement {

    private static final List<EntityType<?>> SPAWNER_ENTITY = List.of(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.SPIDER);

    public static final Codec<ValhelsiaSinglePoolElement> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(templateCodec(), processorsCodec(), projectionCodec()).apply(instance, ValhelsiaSinglePoolElement::new);
    });

    public ValhelsiaSinglePoolElement(Either<ResourceLocation, StructureTemplate> resourceLocation, Holder<StructureProcessorList> processors, StructureTemplatePool.Projection projection) {
        super(resourceLocation, processors, projection);
    }

    @Override
    public boolean place(StructureTemplateManager templateManager, @Nonnull WorldGenLevel level, @Nonnull StructureManager structureManager, @Nonnull ChunkGenerator generator, @Nonnull BlockPos offset, @Nonnull BlockPos pos, @Nonnull Rotation rotation, @Nonnull BoundingBox box, @Nonnull RandomSource random, boolean keepJigsaws) {
        StructureTemplate structuretemplate = this.template.map(templateManager::getOrCreate, Function.identity());
        StructurePlaceSettings structureplacesettings = this.getSettings(rotation, box, keepJigsaws);

        if (!structuretemplate.placeInWorld(level, offset, pos, structureplacesettings, random, 18)) {
            return false;
        }

        for(StructureTemplate.StructureBlockInfo info : structuretemplate.filterBlocks(offset, this.getSettings(rotation, box, keepJigsaws), Blocks.STRUCTURE_BLOCK)) {
            StructureMode mode = StructureMode.valueOf(info.nbt.getString("mode"));

            if (mode == StructureMode.DATA) {
                this.handleDataMarker(level, info, info.pos, rotation, random, box);
            }
        }

        return true;
    }

    @Override
    public void handleDataMarker(@Nonnull LevelAccessor level, @Nonnull StructureTemplate.StructureBlockInfo blockInfo, @Nonnull BlockPos pos, @Nonnull Rotation rotation, @Nonnull RandomSource random, @Nonnull BoundingBox box) {
        String data = blockInfo.nbt.getString("metadata");

        if (data.startsWith("spawner:")) {
            level.setBlock(pos, Blocks.SPAWNER.defaultBlockState(), 2);

            if (level.getBlockEntity(pos) instanceof SpawnerBlockEntity blockEntity) {
                EntityType<?> entityType = switch (data) {
                    case "spawner:zombie_or_skeleton_or_spider" -> SPAWNER_ENTITY.get(random.nextInt(SPAWNER_ENTITY.size()));
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
            level.setBlock(pos, ModBlocks.SPECIAL_SPAWNER.get().defaultBlockState(), 2);

            if (level.getBlockEntity(pos) instanceof SpecialSpawnerBlockEntity blockEntity) {
                EntityType<?> entityType = switch (data) {
                    case "special_spawner:zombie_or_skeleton_or_spider" -> SPAWNER_ENTITY.get(random.nextInt(SPAWNER_ENTITY.size()));
                    case "special_spawner:drowned" -> EntityType.DROWNED;
                    default -> null;
                };

                if (entityType != null) {
                    blockEntity.getSpawner().setEntityId(entityType);
                }
            }
        }

        super.handleDataMarker(level, blockInfo, pos, rotation, random, box);
    }

    @Nonnull
    @Override
    public List<StructureTemplate.StructureBlockInfo> getDataMarkers(StructureTemplateManager templateManager, @Nonnull BlockPos pos, @Nonnull Rotation rotation, boolean relativePosition) {
        StructureTemplate structuretemplate = this.template.map(templateManager::getOrCreate, Function.identity());
        List<StructureTemplate.StructureBlockInfo> structureBlockInfos = structuretemplate.filterBlocks(pos, new StructurePlaceSettings().setRotation(rotation), Blocks.STRUCTURE_BLOCK, relativePosition);
        List<StructureTemplate.StructureBlockInfo> markers = new ArrayList<>();

        for(StructureTemplate.StructureBlockInfo info : structureBlockInfos) {
            StructureMode structuremode = StructureMode.valueOf(info.nbt.getString("mode"));

            if (structuremode == StructureMode.DATA) {
                markers.add(info);
            }
        }

        return markers;
    }

    @Nonnull
    @Override
    public StructurePoolElementType<?> getType() {
        return ModStructurePoolElementTypes.VALHELSIA_SINGLE.get();
    }
}
