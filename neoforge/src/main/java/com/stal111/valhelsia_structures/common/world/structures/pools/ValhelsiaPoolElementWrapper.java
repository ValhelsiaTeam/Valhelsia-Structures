package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.common.block.entity.SpecialSpawnerBlockEntity;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.core.init.world.ModStructurePoolElementTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class ValhelsiaPoolElementWrapper extends StructurePoolElement {

    public static final MapCodec<ValhelsiaPoolElementWrapper> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            StructurePoolElement.CODEC.fieldOf("element").forGetter(elementWrapper -> elementWrapper.element),
            TerrainAdjustment.CODEC.optionalFieldOf("override_terrain_adaptation").forGetter(elementWrapper -> Optional.ofNullable(elementWrapper.terrainAdjustment))
    ).apply(instance, (element, terrainAdjustment) -> new ValhelsiaPoolElementWrapper(element, terrainAdjustment.orElse(null))));

    private static final List<EntityType<?>> SPAWNER_ENTITY = List.of(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.SPIDER);

    private final StructurePoolElement element;
    private final @Nullable TerrainAdjustment terrainAdjustment;

    public ValhelsiaPoolElementWrapper(StructurePoolElement element, @Nullable TerrainAdjustment terrainAdjustment) {
        super(element.getProjection());
        this.element = element;
        this.terrainAdjustment = terrainAdjustment;
    }

    @Override
    public Vec3i getSize(StructureTemplateManager structureTemplateManager, Rotation rotation) {
        return this.element.getSize(structureTemplateManager, rotation);
    }

    @Override
    public List<StructureTemplate.StructureBlockInfo> getShuffledJigsawBlocks(StructureTemplateManager structureTemplateManager, BlockPos pos, Rotation rotation, RandomSource random) {
        return this.element.getShuffledJigsawBlocks(structureTemplateManager, pos, rotation, random);
    }

    @Override
    public BoundingBox getBoundingBox(StructureTemplateManager structureTemplateManager, BlockPos pos, Rotation rotation) {
        return this.element.getBoundingBox(structureTemplateManager, pos, rotation);
    }

    @Override
    public boolean place(StructureTemplateManager structureTemplateManager, WorldGenLevel level, StructureManager structureManager, ChunkGenerator generator, BlockPos offset, BlockPos pos, Rotation rotation, BoundingBox box, RandomSource random, LiquidSettings liquidSettings, boolean keepJigsaws) {
        return this.element.place(structureTemplateManager, level, structureManager, generator, offset, pos, rotation, box, random, liquidSettings, keepJigsaws);
    }

    @Override
    public void handleDataMarker(LevelAccessor level, StructureTemplate.StructureBlockInfo blockInfo, BlockPos pos, Rotation rotation, RandomSource random, BoundingBox box) {
        String data = blockInfo.nbt().getString("metadata");

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
                    blockEntity.getSpawner().setEntityId(entityType, null, random, pos);
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

    public @Nullable TerrainAdjustment getTerrainAdjustment() {
        return this.terrainAdjustment;
    }

    @Override
    public StructurePoolElementType<?> getType() {
        return ModStructurePoolElementTypes.VALHELSIA_POOL_ELEMENT_WRAPPER.get();
    }
}
