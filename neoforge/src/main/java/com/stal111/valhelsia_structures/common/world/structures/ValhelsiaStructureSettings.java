package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

/**
 * @author Valhelsia Team
 * @since 2022-12-22
 */
public record ValhelsiaStructureSettings(
        double spawnChance,
        int customMargin,
        boolean individualTerrainAdjustment,
        LiquidSettings liquidSettings,
        int flatnessDelta) {

    public static final MapCodec<ValhelsiaStructureSettings> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.DOUBLE.optionalFieldOf("spawn_chance", 1.0D).forGetter(ValhelsiaStructureSettings::spawnChance),
            Codec.INT.optionalFieldOf("custom_margin", 12).forGetter(ValhelsiaStructureSettings::customMargin),
            Codec.BOOL.optionalFieldOf("individual_terrain_adjustment", false).forGetter(ValhelsiaStructureSettings::individualTerrainAdjustment),
            LiquidSettings.CODEC.optionalFieldOf("liquid_settings", LiquidSettings.APPLY_WATERLOGGING).forGetter(ValhelsiaStructureSettings::liquidSettings),
            ExtraCodecs.intRange(0, 64).optionalFieldOf("flatness_delta", 4).forGetter(ValhelsiaStructureSettings::flatnessDelta)
    ).apply(instance, ValhelsiaStructureSettings::new));

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private double spawnChance = 1.0D;
        private int customMargin = 12;
        private boolean individualTerrainAdjustment = false;
        private LiquidSettings liquidSettings = LiquidSettings.APPLY_WATERLOGGING;
        private int flatnessDelta = 4;

        private Builder() {}

        public Builder setSpawnChance(double spawnChance) {
            this.spawnChance = spawnChance;
            return this;
        }

        public Builder setCustomMargin(int customMargin) {
            this.customMargin = customMargin;
            return this;
        }

        public Builder enableIndividualTerrainAdjustment(boolean individualTerrainAdjustment) {
            this.individualTerrainAdjustment = individualTerrainAdjustment;
            return this;
        }

        public Builder setLiquidSettings(LiquidSettings liquidSettings) {
            this.liquidSettings = liquidSettings;
            return this;
        }

        public ValhelsiaStructureSettings build() {
            return new ValhelsiaStructureSettings(this.spawnChance, this.customMargin, this.individualTerrainAdjustment, this.liquidSettings, this.flatnessDelta);
        }
    }
}
