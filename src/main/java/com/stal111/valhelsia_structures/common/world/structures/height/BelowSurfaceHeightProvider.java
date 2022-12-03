package com.stal111.valhelsia_structures.common.world.structures.height;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.core.init.world.ModStructureHeightProviderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.OptionalInt;

/**
 * @author Valhelsia Team
 * @since 2022-10-28
 */
public class BelowSurfaceHeightProvider extends StructureHeightProvider {

    public static final Codec<BelowSurfaceHeightProvider> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(VerticalAnchor.CODEC.fieldOf("min_inclusive").forGetter(provider -> {
            return provider.minInclusive;
        })).apply(instance, BelowSurfaceHeightProvider::new);
    });

    private final VerticalAnchor minInclusive;

    public BelowSurfaceHeightProvider(VerticalAnchor minInclusive) {
        this.minInclusive = minInclusive;
    }

    @Override
    public OptionalInt sample(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType) {
        int min = this.minY(pos, context, heightmapType);
        int max = this.maxY(pos, context, heightmapType);

        return OptionalInt.of(Mth.randomBetweenInclusive(context.random(), min, max));
    }

    @Override
    public int minY(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType) {
        return this.minInclusive.resolveY(new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor()));
    }

    @Override
    public int maxY(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType) {
        return Math.max(this.minY(pos, context, heightmapType), context.chunkGenerator().getFirstOccupiedHeight(pos.getX(), pos.getZ(), heightmapType, context.heightAccessor(), context.randomState()) - 5);
    }

    @Override
    public StructureHeightProviderType<?> getType() {
        return ModStructureHeightProviderTypes.BELOW_SURFACE_HEIGHT.get();
    }

    protected VerticalAnchor getMinInclusive() {
        return this.minInclusive;
    }
}
