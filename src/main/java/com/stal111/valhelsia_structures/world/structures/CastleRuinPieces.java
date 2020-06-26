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

public class CastleRuinPieces {
    public static void register() {
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(ValhelsiaStructures.MOD_ID, "castle_ruins"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new SingleJigsawPiece(ValhelsiaStructures.MOD_ID + ":castle_ruin"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
    }

    public static void generate(ChunkGenerator generator, TemplateManager templateManager, BlockPos position, List<StructurePiece> pieces, SharedSeedRandom random) {
        JigsawManager.func_236823_a_(new ResourceLocation(ValhelsiaStructures.MOD_ID, "castle_ruins"), 7, CastleRuinPiece::new, generator, templateManager, position, pieces, random, true, true);
    }

    public static class CastleRuinPiece extends AbstractVillagePiece {
        public CastleRuinPiece(TemplateManager templateManager, JigsawPiece jigsawPiece, BlockPos position, int groundLevelDelta, Rotation rotation, MutableBoundingBox bounds) {
            super(ModStructurePieces.CASTLE_RUIN, templateManager, jigsawPiece, position, groundLevelDelta, rotation, bounds);
        }

        public CastleRuinPiece(TemplateManager templateManager, CompoundNBT compoundNBT) {
            super(templateManager, compoundNBT, ModStructurePieces.CASTLE_RUIN);
        }
    }
}