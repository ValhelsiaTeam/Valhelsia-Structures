package com.stal111.valhelsia_structures.common.world.structures.height;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.core.init.world.ModStructureHeightProviderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.OptionalInt;

/**
 * @author Valhelsia Team
 * @since 2022-10-28
 */
public record DefaultHeightProvider(HeightProvider heightProvider) implements StructureHeightProvider {

    public static final MapCodec<DefaultHeightProvider> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            HeightProvider.CODEC.fieldOf("height_provider").forGetter(DefaultHeightProvider::heightProvider)
    ).apply(instance, DefaultHeightProvider::new));

    @Override
    public OptionalInt sample(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType) {
        return OptionalInt.of(this.heightProvider.sample(context.random(), new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor())));
    }

    @Override
    public int minY(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType) {
        throw new UnsupportedOperationException("Cannot get minY on the default provider");
    }

    @Override
    public int maxY(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType) {
        throw new UnsupportedOperationException("Cannot get maxY on the default provider");
    }

    @Override
    public StructureHeightProviderType<?> getType() {
        return ModStructureHeightProviderTypes.DEFAULT_HEIGHT.get();
    }
}
