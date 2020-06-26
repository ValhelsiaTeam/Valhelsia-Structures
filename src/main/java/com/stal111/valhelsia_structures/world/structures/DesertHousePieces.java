package com.stal111.valhelsia_structures.world.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.init.ModStructurePieces;
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
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;

/**
 * Desert House Pieces
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.structures.DesertHousePieces
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2020-05-27
 */
public class DesertHousePieces {

    public static void register() {
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(ValhelsiaStructures.MOD_ID, "desert_houses"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new SingleJigsawPiece(ValhelsiaStructures.MOD_ID + ":desert_house"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
    }

    public static void generate(ChunkGenerator generator, TemplateManager templateManager, BlockPos position, List<StructurePiece> pieces, SharedSeedRandom random) {
        JigsawManager.func_236823_a_(new ResourceLocation(ValhelsiaStructures.MOD_ID, "desert_houses"), 7, DesertHousePiece::new, generator, templateManager, position, pieces, random, true, true);
    }

    public static class DesertHousePiece extends AbstractVillagePiece {
        public DesertHousePiece(TemplateManager templateManager, JigsawPiece jigsawPiece, BlockPos position, int groundLevelDelta, Rotation rotation, MutableBoundingBox bounds) {
            super(ModStructurePieces.DESERT_HOUSE, templateManager, jigsawPiece, position, groundLevelDelta, rotation, bounds);
        }

        public DesertHousePiece(TemplateManager templateManager, CompoundNBT compoundNBT) {
            super(templateManager, compoundNBT, ModStructurePieces.DESERT_HOUSE);
        }
    }
}
