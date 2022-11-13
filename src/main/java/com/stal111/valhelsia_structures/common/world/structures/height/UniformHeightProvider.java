package com.stal111.valhelsia_structures.common.world.structures.height;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.world.ModStructureHeightProviderTypes;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.OptionalInt;

/**
 * @author Valhelsia Team
 * @since 2022-11-13
 */
public class UniformHeightProvider extends StructureHeightProvider {

    public static final Codec<UniformHeightProvider> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(VerticalAnchor.CODEC.fieldOf("min_inclusive").forGetter((provider) -> {
            return provider.minInclusive;
        }), VerticalAnchor.CODEC.fieldOf("max_inclusive").forGetter((provider) -> {
            return provider.maxInclusive;
        })).apply(instance, UniformHeightProvider::new);
    });

    private final VerticalAnchor minInclusive;
    private final VerticalAnchor maxInclusive;

    private final LongSet warnedFor = new LongOpenHashSet();

    protected UniformHeightProvider(VerticalAnchor minInclusive, VerticalAnchor maxInclusive) {
        this.minInclusive = minInclusive;
        this.maxInclusive = maxInclusive;
    }

    public static UniformHeightProvider of(VerticalAnchor minInclusive, VerticalAnchor maxInclusive) {
        return new UniformHeightProvider(minInclusive, maxInclusive);
    }

    @Override
    public OptionalInt sample(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType) {
        WorldGenerationContext worldGenerationContext = this.getWorldGenerationContext(context);

        int i = this.minInclusive.resolveY(worldGenerationContext);
        int j = this.maxInclusive.resolveY(worldGenerationContext);

        if (i > j) {
            if (this.warnedFor.add((long) i << 32 | (long) j)) {
                ValhelsiaStructures.LOGGER.warn("Empty height range: {}", this);
            }

            return OptionalInt.of(i);
        }

        return OptionalInt.of(Mth.randomBetweenInclusive(context.random(), i, j));
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
        return ModStructureHeightProviderTypes.UNIFORM_HEIGHT.get();
    }

    protected VerticalAnchor getMinInclusive() {
        return this.minInclusive;
    }

    protected VerticalAnchor getMaxInclusive() {
        return this.maxInclusive;
    }
}
