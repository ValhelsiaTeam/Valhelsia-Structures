package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.core.init.world.ModStructurePoolElementTypes;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.Optional;

public class ValhelsiaSinglePoolElement extends SinglePoolElement {

    public static final MapCodec<ValhelsiaSinglePoolElement> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            templateCodec(),
            processorsCodec(),
            projectionCodec(),
            overrideLiquidSettingsCodec()
    ).apply(instance, ValhelsiaSinglePoolElement::new));

    public ValhelsiaSinglePoolElement(Either<ResourceLocation, StructureTemplate> template, Holder<StructureProcessorList> processors, StructureTemplatePool.Projection projection, Optional<LiquidSettings> overrideLiquidSettings) {
        super(template, processors, projection, overrideLiquidSettings);
    }

    @Override
    protected StructurePlaceSettings getSettings(Rotation rotation, BoundingBox boundingBox, LiquidSettings liquidSettings, boolean offset) {
        return super.getSettings(rotation, boundingBox, liquidSettings, offset).popProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
    }

    @Override
    public StructurePoolElementType<?> getType() {
        return ModStructurePoolElementTypes.VALHELSIA_SINGLE_POOL_ELEMENT.get();
    }
}
