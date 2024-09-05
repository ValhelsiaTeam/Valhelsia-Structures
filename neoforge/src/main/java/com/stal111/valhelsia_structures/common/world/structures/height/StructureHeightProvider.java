package com.stal111.valhelsia_structures.common.world.structures.height;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.OptionalInt;

/**
 * @author Valhelsia Team
 * @since 2022-10-28
 */
public abstract class StructureHeightProvider {

    public static final DeferredCodec<StructureHeightProvider> CODEC = new DeferredCodec<>(() -> ValhelsiaStructures.STRUCTURE_HEIGHT_PROVIDER_TYPES_REGISTRY.byNameCodec().dispatch(StructureHeightProvider::getType, StructureHeightProviderType::codec));

    public abstract OptionalInt sample(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType);
    public abstract int minY(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType);
    public abstract int maxY(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType);

    public abstract StructureHeightProviderType<?> getType();

    protected WorldGenerationContext getWorldGenerationContext(Structure.GenerationContext context) {
        return new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor());
    }

    public static DefaultHeightProvider of(HeightProvider provider) {
        return new DefaultHeightProvider(provider);
    }

    public static BelowSurfaceHeightProvider belowSurface(VerticalAnchor verticalAnchor) {
        return new BelowSurfaceHeightProvider(verticalAnchor);
    }

    public static DefaultHeightProvider constant(VerticalAnchor verticalAnchor) {
        return StructureHeightProvider.of(ConstantHeight.of(verticalAnchor));
    }

    public static UniformHeightProvider uniform(VerticalAnchor minInclusive, VerticalAnchor maxInclusive) {
        return UniformHeightProvider.of(minInclusive, maxInclusive);
    }

    public static SpawnerRoomHeightProvider spawnerRoom(VerticalAnchor minInclusive) {
        return new SpawnerRoomHeightProvider(minInclusive);
    }

    public static DeepSpawnerRoomHeightProvider deepSpawnerRoom(VerticalAnchor minInclusive, VerticalAnchor maxInclusive) {
        return new DeepSpawnerRoomHeightProvider(minInclusive, maxInclusive);
    }

    public static SurfaceHeightProvider surfaceBetween(VerticalAnchor minInclusive, VerticalAnchor maxInclusive) {
        return new SurfaceHeightProvider(minInclusive, maxInclusive);
    }
}
