package com.stal111.valhelsia_structures.common.world.structures.height;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;

/**
 * @author Valhelsia Team
 * @since 2022-10-28
 */
public abstract class StructureHeightProvider {

    public static final DeferredCodec<StructureHeightProvider> CODEC = new DeferredCodec<>(() -> ValhelsiaStructures.STRUCTURE_HEIGHT_PROVIDER_TYPES.get().getCodec().dispatch(StructureHeightProvider::getType, StructureHeightProviderType::codec));
    public abstract int sample(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType);
    public abstract int minY(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType);
    public abstract int maxY(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType);


    public abstract StructureHeightProviderType<?> getType();

    public static DefaultHeightProvider of(HeightProvider provider) {
        return new DefaultHeightProvider(provider);
    }

    public static BelowSurfaceHeight belowSurface(VerticalAnchor verticalAnchor) {
        return new BelowSurfaceHeight(verticalAnchor);
    }
}
