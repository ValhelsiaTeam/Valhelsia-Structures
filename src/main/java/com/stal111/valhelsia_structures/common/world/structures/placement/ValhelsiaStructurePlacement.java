package com.stal111.valhelsia_structures.common.world.structures.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.core.init.world.ModStructurePlacementTypes;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

/**
 * @author Valhelsia Team
 * @since 2022-12-22
 */
public class ValhelsiaStructurePlacement extends RandomSpreadStructurePlacement {

    public static final Codec<ValhelsiaStructurePlacement> CODEC = RecordCodecBuilder.<ValhelsiaStructurePlacement>mapCodec(instance -> {
        var placementCodec = placementCodec(instance);

        return instance.group(
                placementCodec.t1(),
                placementCodec.t2(),
                placementCodec.t3(),
                placementCodec.t4(),
                placementCodec.t5(),
                Codec.intRange(0, 4096).fieldOf("spacing").forGetter(ValhelsiaStructurePlacement::spacing),
                Codec.intRange(0, 4096).fieldOf("separation").forGetter(RandomSpreadStructurePlacement::separation),
                RandomSpreadType.CODEC.optionalFieldOf("spread_type", RandomSpreadType.LINEAR).forGetter(RandomSpreadStructurePlacement::spreadType),
                Codec.list(ExclusionZone.CODEC).fieldOf("exclusion_zones").forGetter(ValhelsiaStructurePlacement::getExclusionZones)
        ).apply(instance, ValhelsiaStructurePlacement::new);
    }).flatXmap(placement -> {
        return placement.spacing() <= placement.separation() ? DataResult.error(() -> "Spacing has to be larger than separation") : DataResult.success(placement);
    }, DataResult::success).codec();

    private final List<ExclusionZone> exclusionZones;

    public ValhelsiaStructurePlacement(int salt, int spacing, int separation, List<ExclusionZone> exclusionZones) {
        super(Vec3i.ZERO, FrequencyReductionMethod.DEFAULT, 1.0F, salt, Optional.empty(), spacing, separation, RandomSpreadType.LINEAR);
        this.exclusionZones = exclusionZones;
    }

    public ValhelsiaStructurePlacement(Vec3i locateOffset, FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone, int spacing, int separation, RandomSpreadType randomSpreadType, List<ExclusionZone> exclusionZones) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone, spacing, separation, randomSpreadType);
        this.exclusionZones = exclusionZones;
    }

    @Override
    public boolean isStructureChunk(@Nonnull ChunkGeneratorStructureState structureState, int xPos, int zPos) {
        if (!this.isPlacementChunk(structureState, xPos, zPos)) {
            return false;
        } else if (this.frequency() < 1.0F && !this.frequencyReductionMethod().shouldGenerate(structureState.getLevelSeed(), this.salt(), xPos, zPos, this.frequency())) {
            return false;
        }

        for (ExclusionZone exclusionZone : this.exclusionZones) {
            StructurePlacement placement = exclusionZone.otherSet().value().placement();

            for(int i = xPos - exclusionZone.chunkCount(); i <= xPos + exclusionZone.chunkCount(); ++i) {
                for (int j = zPos - exclusionZone.chunkCount(); j <= zPos + exclusionZone.chunkCount(); ++j) {
                    if (placement instanceof ValhelsiaStructurePlacement valhelsiaStructurePlacement) {
                        if (valhelsiaStructurePlacement.isPlacementChunk(structureState, xPos, zPos)) {
                            return false;
                        } else if (valhelsiaStructurePlacement.frequency() < 1.0F && valhelsiaStructurePlacement.frequencyReductionMethod().shouldGenerate(structureState.getLevelSeed(), valhelsiaStructurePlacement.salt(), xPos, zPos, valhelsiaStructurePlacement.frequency())) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public List<ExclusionZone> getExclusionZones() {
        return this.exclusionZones;
    }

    @Nonnull
    @Override
    public StructurePlacementType<?> type() {
        return ModStructurePlacementTypes.VALHELSIA_RANDOM_SPREAD.get();
    }
}
