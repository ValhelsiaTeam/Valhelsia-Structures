package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.core.init.world.ModStructurePoolElementTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
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
    public List<StructureTemplate.JigsawBlockInfo> getShuffledJigsawBlocks(StructureTemplateManager structureTemplateManager, BlockPos pos, Rotation rotation, RandomSource random) {
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

    public @Nullable TerrainAdjustment getTerrainAdjustment() {
        return this.terrainAdjustment;
    }

    @Override
    public StructurePoolElementType<?> getType() {
        return ModStructurePoolElementTypes.VALHELSIA_POOL_ELEMENT_WRAPPER.get();
    }
}
