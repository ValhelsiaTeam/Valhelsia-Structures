package com.stal111.valhelsia_structures.common.world.structures.height;

import com.mojang.serialization.Codec;
import com.stal111.valhelsia_structures.core.init.world.ModStructureHeightProviderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.OptionalInt;

/**
 * @author Valhelsia Team
 * @since 2022-11-13
 */
public class DeepSpawnerRoomHeightProvider extends UniformHeightProvider {

    public static final Codec<DeepSpawnerRoomHeightProvider> CODEC = UniformHeightProvider.CODEC.xmap(provider -> new DeepSpawnerRoomHeightProvider(provider.getMinInclusive(), provider.getMaxInclusive()), provider -> provider);


    protected DeepSpawnerRoomHeightProvider(VerticalAnchor minInclusive, VerticalAnchor maxInclusive) {
        super(minInclusive, maxInclusive);
    }

    @Override
    public OptionalInt sample(BlockPos pos, Structure.GenerationContext context, Heightmap.Types heightmapType) {
        NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor(), context.randomState());

        int maxY = this.maxY(pos, context, Heightmap.Types.WORLD_SURFACE);
        int minY = this.minY(pos, context, Heightmap.Types.WORLD_SURFACE);

        int airCount = 0;

        while (maxY > minY) {
            if (columnOfBlocks.getBlock(maxY).isAir()) {
                airCount++;
            } else {
                if (airCount != 0 && airCount <= 8) {
                    return OptionalInt.of(maxY);
                }

                airCount = 0;
            }
            maxY--;
        }

        return OptionalInt.empty();    }

    @Override
    public StructureHeightProviderType<?> getType() {
        return ModStructureHeightProviderTypes.DEEP_SPAWNER_ROOM_HEIGHT.get();
    }
}
