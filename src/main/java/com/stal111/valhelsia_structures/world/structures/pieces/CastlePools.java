package com.stal111.valhelsia_structures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.init.ModStructurePieces;
import com.stal111.valhelsia_structures.utils.JigsawHelper;
import com.stal111.valhelsia_structures.world.template.Processors;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Castle Pieces
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.pieces.CastlePieces
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2020-05-27
 */

public class CastlePools {

    public static final JigsawPattern PATTERN = JigsawHelper.register("castles", JigsawPattern.PlacementBehaviour.RIGID, ImmutableList.of(Pair.of("castle", 1)), true);

//    public static void generate(ChunkGenerator generator, TemplateManager templateManager, BlockPos position, List<StructurePiece> pieces, SharedSeedRandom random) {
//        JigsawManager.func_236823_a_(new ResourceLocation(ValhelsiaStructures.MOD_ID, "castles"), 7, CastlePiece::new, generator, templateManager, position, pieces, random, true, true);
//    }

//    public static class CastlePiece extends AbstractVillagePiece {
//        public CastlePiece(TemplateManager templateManager, JigsawPiece jigsawPiece, BlockPos position, int groundLevelDelta, Rotation rotation, MutableBoundingBox bounds) {
//            super(ModStructurePieces.CASTLE, templateManager, jigsawPiece, position, groundLevelDelta, rotation, bounds);
//        }
//
//        public CastlePiece(TemplateManager templateManager, CompoundNBT compoundNBT) {
//            super(templateManager, compoundNBT, ModStructurePieces.CASTLE);
//        }
//    }
}