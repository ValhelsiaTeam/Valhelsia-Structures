package com.stal111.valhelsia_structures.world.template;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.jigsaw.IJigsawDeserializer;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.template.*;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

/**
 * Valhelsia Single Jigsaw Piece
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.template.ValhelsiaSingleJigsawPiece
 *
 * This is a version of SingleJigsawPiece that replaces the placement settings in order to remove the default processors
 * (which ignore air blocks rather than carve them out) and add our own replacement processors.
 *
 * @author Valhelsia Team
 * @version 14.0.3
 * @since 2019-10-31
 */
public class ValhelsiaSingleJigsawPiece extends SingleJigsawPiece {
    public static IJigsawDeserializer VALHELSIA_SINGLE_POOL_ELEMENT = IJigsawDeserializer.register("valhelsia_single_pool_element", ValhelsiaSingleJigsawPiece::new);

    public ValhelsiaSingleJigsawPiece(String location) {
        this(location, ImmutableList.of());
    }

    public ValhelsiaSingleJigsawPiece(String location, List<StructureProcessor> processors) {
        this(location, processors, JigsawPattern.PlacementBehaviour.RIGID);
    }

    public ValhelsiaSingleJigsawPiece(String location, List<StructureProcessor> processors, JigsawPattern.PlacementBehaviour placementBehaviour) {
        super(location, processors, placementBehaviour);
    }

    public ValhelsiaSingleJigsawPiece(Dynamic<?> dynamic) {
        super(dynamic);
    }

    @Override
    public boolean place(TemplateManager templateManager, @Nonnull IWorld world, @Nonnull BlockPos position, @Nonnull Rotation rotation, @Nonnull MutableBoundingBox bounds, @Nonnull Random random) {
        Template template = templateManager.getTemplateDefaulted(this.location);
        PlacementSettings placementSettings = createPlacementSettings(rotation, bounds);
        if (!template.addBlocksToWorld(world, position, placementSettings, 18)) {
            return false;
        } else {
            for(Template.BlockInfo blockInfo : Template.processBlockInfos(template, world, position, placementSettings, func_214857_a(templateManager, position, rotation, false))) {
                func_214846_a(world, blockInfo, position, rotation, random, bounds);
            }

            return true;
        }
    }

    @Override
    protected @Nonnull PlacementSettings createPlacementSettings(@Nonnull Rotation rotation, @Nonnull MutableBoundingBox bounds) {
        PlacementSettings placementSettings = new PlacementSettings();
        placementSettings.setBoundingBox(bounds);
        placementSettings.setRotation(rotation);
        placementSettings.func_215223_c(true);
        placementSettings.setIgnoreEntities(false);
        placementSettings.addProcessor(Processors.RED_GLASS_AND_STRUCTURE_BLOCK);
        placementSettings.addProcessor(JigsawReplacementStructureProcessor.INSTANCE);
        placementSettings.addProcessor(Processors.OBSIDIAN);
        //Processors.PIECE_PROCESSOR_LIST.forEach(placementSettings::addProcessor);
        this.processors.forEach(placementSettings::addProcessor);
        this.getPlacementBehaviour().getStructureProcessors().forEach(placementSettings::addProcessor);
        return placementSettings;
    }

    @Override
    public @Nonnull IJigsawDeserializer getType() {
        return VALHELSIA_SINGLE_POOL_ELEMENT;
    }
}
