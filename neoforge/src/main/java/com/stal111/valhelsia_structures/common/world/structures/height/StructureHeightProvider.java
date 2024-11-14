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
public interface StructureHeightProvider {

    DeferredCodec<StructureHeightProvider> CODEC = new DeferredCodec<>(() -> ValhelsiaStructures.STRUCTURE_HEIGHT_PROVIDER_TYPES_REGISTRY.byNameCodec().dispatch(StructureHeightProvider::getType, StructureHeightProviderType::codec));

    OptionalInt sample(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType);
    int minY(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType);
    int maxY(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType);

    StructureHeightProviderType<?> getType();

    static WorldGenerationContext getWorldGenerationContext(Structure.GenerationContext context) {
        return new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor());
    }

    static DefaultHeightProvider of(HeightProvider provider) {
        return new DefaultHeightProvider(provider);
    }

    static BelowSurfaceHeightProvider belowSurface(VerticalAnchor verticalAnchor) {
        return new BelowSurfaceHeightProvider(verticalAnchor);
    }

    static DefaultHeightProvider constant(VerticalAnchor verticalAnchor) {
        return StructureHeightProvider.of(ConstantHeight.of(verticalAnchor));
    }

    static UniformHeightProvider uniform(VerticalAnchor minInclusive, VerticalAnchor maxInclusive) {
        return UniformHeightProvider.of(minInclusive, maxInclusive);
    }

    static SpawnerRoomHeightProvider spawnerRoom(VerticalAnchor minInclusive) {
        return new SpawnerRoomHeightProvider(minInclusive);
    }

    static DeepSpawnerRoomHeightProvider deepSpawnerRoom(VerticalAnchor minInclusive, VerticalAnchor maxInclusive) {
        return new DeepSpawnerRoomHeightProvider(minInclusive, maxInclusive);
    }

    static SurfaceHeightProvider surfaceBetween(VerticalAnchor minInclusive, VerticalAnchor maxInclusive) {
        return new SurfaceHeightProvider(minInclusive, maxInclusive);
    }
}
