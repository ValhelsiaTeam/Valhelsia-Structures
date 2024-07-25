package com.stal111.valhelsia_structures.common.world.structures.height;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.core.init.world.ModStructureHeightProviderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.OptionalInt;

/**
 * @author Valhelsia Team
 * @since 2022-12-14
 */
public class SurfaceHeightProvider extends StructureHeightProvider {

    public static final MapCodec<SurfaceHeightProvider> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            VerticalAnchor.CODEC.fieldOf("min_inclusive").forGetter(provider -> {
                return provider.minInclusive;
            }),
            VerticalAnchor.CODEC.fieldOf("max_inclusive").forGetter(provider -> {
                return provider.maxInclusive;
            })
    ).apply(instance, SurfaceHeightProvider::new));

    private final VerticalAnchor minInclusive;
    private final VerticalAnchor maxInclusive;

    public SurfaceHeightProvider(VerticalAnchor minInclusive, VerticalAnchor maxInclusive) {
        this.minInclusive = minInclusive;
        this.maxInclusive = maxInclusive;
    }

    @Override
    public OptionalInt sample(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType) {
        int height = context.chunkGenerator().getFirstFreeHeight(pos.getX(), pos.getZ(), heightmapType, context.heightAccessor(), context.randomState());

        int min = this.minY(pos, context, heightmapType);
        int max = this.maxY(pos, context, heightmapType);

        if (height < min || height > max) {
            return OptionalInt.empty();
        }

        return OptionalInt.of(height);
    }

    @Override
    public int minY(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType) {
        return this.minInclusive.resolveY(this.getWorldGenerationContext(context));
    }

    @Override
    public int maxY(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType) {
        return this.maxInclusive.resolveY(this.getWorldGenerationContext(context));
    }

    @Override
    public StructureHeightProviderType<?> getType() {
        return ModStructureHeightProviderTypes.SURFACE_HEIGHT.get();
    }
}
