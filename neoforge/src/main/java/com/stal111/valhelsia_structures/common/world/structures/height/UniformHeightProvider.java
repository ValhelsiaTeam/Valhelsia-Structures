package com.stal111.valhelsia_structures.common.world.structures.height;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.core.init.world.ModStructureHeightProviderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.OptionalInt;

/**
 * @author Valhelsia Team
 * @since 2022-11-13
 */
public class UniformHeightProvider implements StructureHeightProvider {

    public static final MapCodec<UniformHeightProvider> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            VerticalAnchor.CODEC.fieldOf("min_inclusive").forGetter(provider -> provider.minInclusive),
            VerticalAnchor.CODEC.fieldOf("max_inclusive").forGetter((provider) -> provider.maxInclusive)
    ).apply(instance, UniformHeightProvider::new));

    private final VerticalAnchor minInclusive;
    private final VerticalAnchor maxInclusive;

    protected UniformHeightProvider(VerticalAnchor minInclusive, VerticalAnchor maxInclusive) {
        this.minInclusive = minInclusive;
        this.maxInclusive = maxInclusive;
    }

    public static UniformHeightProvider of(VerticalAnchor minInclusive, VerticalAnchor maxInclusive) {
        return new UniformHeightProvider(minInclusive, maxInclusive);
    }

    @Override
    public OptionalInt sample(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType) {
        WorldGenerationContext worldGenerationContext = StructureHeightProvider.getWorldGenerationContext(context);

        int i = this.minInclusive.resolveY(worldGenerationContext);
        int j = this.maxInclusive.resolveY(worldGenerationContext);

        UniformInt uniformInt = UniformInt.of(i, j);

        return OptionalInt.of(uniformInt.sample(context.random()));
    }

    @Override
    public int minY(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType) {
        return this.minInclusive.resolveY(StructureHeightProvider.getWorldGenerationContext(context));
    }

    @Override
    public int maxY(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType) {
        return this.maxInclusive.resolveY(StructureHeightProvider.getWorldGenerationContext(context));
    }

    @Override
    public StructureHeightProviderType<?> getType() {
        return ModStructureHeightProviderTypes.UNIFORM_HEIGHT.get();
    }

    public VerticalAnchor getMinInclusive() {
        return this.minInclusive;
    }

    public VerticalAnchor getMaxInclusive() {
        return this.maxInclusive;
    }
}
