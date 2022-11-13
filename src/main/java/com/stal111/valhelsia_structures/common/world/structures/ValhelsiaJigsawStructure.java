package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.common.world.structures.height.StructureHeightProvider;
import com.stal111.valhelsia_structures.core.init.world.ModStructureTypes;
import com.stal111.valhelsia_structures.core.init.world.ModStructures;
import com.stal111.valhelsia_structures.utils.StructureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.valhelsia.valhelsia_core.common.world.SimpleValhelsiaStructure;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;

/**
 * Base class for all structures added by the mod. Provides a few additional placement checks and configuration options over the {@link net.minecraft.world.level.levelgen.structure.structures.JigsawStructure}.
 *
 * @author Valhelsia Team
 * @since 2020-03-22
 */
public class ValhelsiaJigsawStructure extends SimpleValhelsiaStructure {

    public static final Codec<ValhelsiaJigsawStructure> CODEC = RecordCodecBuilder.<ValhelsiaJigsawStructure>mapCodec((instance) -> {
        return instance.group(settingsCodec(instance), Codec.STRING.fieldOf("name").forGetter(SimpleValhelsiaStructure::getName), StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter((structure) -> {
            return structure.startPool;
        }), ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter((structure) -> {
            return structure.startJigsawName;
        }), Codec.intRange(0, 7).fieldOf("size").forGetter((structure) -> {
            return structure.maxDepth;
        }), StructureHeightProvider.CODEC.fieldOf("start_height").forGetter((structure) -> {
            return structure.startHeight;
        }), Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter((structure) -> {
            return structure.projectStartToHeightmap;
        }), Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter((structure) -> {
            return structure.maxDistanceFromCenter;
        })).apply(instance, ValhelsiaJigsawStructure::new);
    }).flatXmap(verifyRange(), verifyRange()).codec();

    private static Function<ValhelsiaJigsawStructure, DataResult<ValhelsiaJigsawStructure>> verifyRange() {
        return (structure) -> {
            int i = switch (structure.terrainAdaptation()) {
                case NONE -> 0;
                case BURY, BEARD_THIN, BEARD_BOX -> 12;
            };
            return structure.maxDistanceFromCenter + i > 128 ? DataResult.error("Structure size including terrain adaptation must not exceed 128") : DataResult.success(structure);
        };
    }

  //  private final ValhelsiaStructureSettings structureSettings;
  //  private final ResourceKey<StructureSet> structureSetResourceKey;

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final int maxDepth;
    private final StructureHeightProvider startHeight;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final int maxDistanceFromCenter;

/*    public AbstractValhelsiaStructure(Structure.StructureSettings settings, String name, Holder<StructureTemplatePool> startPool, int size, ValhelsiaStructureSettings structureSettings) {
        super(settings, name);
        this.structureSettings = structureSettings;
        this.structureSetResourceKey = ResourceKey.create(Registry.STRUCTURE_SET_REGISTRY, new ResourceLocation(ValhelsiaStructures.MOD_ID, name + "s"));
    }*/

    public ValhelsiaJigsawStructure(Structure.StructureSettings settings, String name, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int maxDepth, StructureHeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter) {
        super(settings, name);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.maxDepth = maxDepth;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }

    public ValhelsiaJigsawStructure(Structure.StructureSettings p_227620_, String name, Holder<StructureTemplatePool> p_227621_, int p_227622_, StructureHeightProvider p_227623_, Heightmap.Types p_227625_) {
        this(p_227620_, name, p_227621_, Optional.empty(), p_227622_, p_227623_, Optional.of(p_227625_), 80);
    }

    public ValhelsiaJigsawStructure(Structure.StructureSettings p_227614_, String name, Holder<StructureTemplatePool> p_227615_, int p_227616_, StructureHeightProvider p_227617_) {
        this(p_227614_, name, p_227615_, Optional.empty(), p_227616_, p_227617_, Optional.empty(), 80);
    }

    private boolean canGenerate(Structure.GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos pos = chunkPos.getWorldPosition();
        ChunkGenerator generator = context.chunkGenerator();
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));

        // Check if the surface is flat
        if (this.checkSurface() && !StructureUtils.isSurfaceFlat(context, this.getStructureSettings().size().get())) {
            return false;
        }

        // Check for water
        if (!this.canGenerateOnWater()) {
            int landHeight = generator.getFirstOccupiedHeight(pos.getX(), pos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());

            NoiseColumn columnOfBlocks = generator.getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor(), context.randomState());
            BlockState topBlock = columnOfBlocks.getBlock(landHeight);

            if (!topBlock.getFluidState().isEmpty()) {
                return false;
            }
        }

        // Check for other structures
//        List<Holder<StructureSet>> structures = ModStructures.MOD_STRUCTURES.stream()
//                .filter(structure -> structure.step() == this.step() && structure.getStructureSetResourceKey() != this.getStructureSetResourceKey())
//                .map(AbstractValhelsiaStructure::getStructureSetResourceKey).toList();
//
//        if (StructureUtils.isStructureInDistance(generator, context.randomState(), context.seed(), context.chunkPos(), structures)) {
//            return false;
//        }

        // Check the spawn chance
        random.setSeed((long) (chunkPos.x >> 4 ^ chunkPos.z >> 4 << 4) ^ context.seed());

        return random.nextDouble() < this.getStructureSettings().spawnChance().get();
    }

    private Optional<Integer> findSuitableY(ChunkGenerator generator, GenerationContext context, BlockPos pos, StructureHeightProvider heightProvider) {
        NoiseColumn columnOfBlocks = generator.getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor(), context.randomState());

        int maxY = heightProvider.maxY(pos, context, Heightmap.Types.WORLD_SURFACE);
        int minY = heightProvider.minY(pos, context, Heightmap.Types.WORLD_SURFACE);

        int airCount = 0;

        while (maxY > minY) {
            if (columnOfBlocks.getBlock(maxY).isAir()) {
                airCount++;
            } else {
                if (airCount != 0 && airCount <= 8) {
                    return Optional.of(maxY);
                }

                airCount = 0;
            }
            maxY--;
        }

        return Optional.empty();
    }

    @Nonnull
    @Override
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();

        if (!this.canGenerate(context)) {
            return Optional.empty();
        }

        BlockPos pos = chunkPos.getWorldPosition();

        OptionalInt y = this.startHeight.sample(pos, context, Heightmap.Types.WORLD_SURFACE);

        if (y.isEmpty()) {
            return Optional.empty();
        }

        return JigsawPlacement.addPieces(
                context,
                this.startPool,
                this.startJigsawName,
                this.maxDepth,
                pos.atY(y.getAsInt()),
                true,
                this.projectStartToHeightmap,
                this.maxDistanceFromCenter
        );
    }

    @Nonnull
    @Override
    public StructureType<?> type() {
        return ModStructureTypes.VALHELSIA_JIGSAW_STRUCTURE.get();
    }

    public ValhelsiaStructureSettings getStructureSettings() {
        return ModStructures.STRUCTURE_SETTINGS_MAP.get(this.getName());
    }

    public boolean checkSurface() {
        return this.getStructureSettings().size() != null;
    }

    public boolean canGenerateOnWater() {
        return this.step() == GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
    }

    public boolean hasMargin() {
        return this.getStructureSettings().margin().get() != 0;
    }

    public int getMargin() {
        return this.getStructureSettings().margin().get();
    }

    @Nonnull
    @Override
    public BoundingBox adjustBoundingBox(@Nonnull BoundingBox boundingBox) {
        return this.hasMargin() ? boundingBox.inflatedBy(this.getMargin()) : boundingBox;
    }
}