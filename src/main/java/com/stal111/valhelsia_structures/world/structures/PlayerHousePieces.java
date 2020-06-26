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
import net.minecraft.world.gen.feature.jigsaw.*;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;

public class PlayerHousePieces {

    public static void register() {
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(ValhelsiaStructures.MOD_ID, "player_houses"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new SingleJigsawPiece(ValhelsiaStructures.MOD_ID + ":player_house"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
    }

    public static void generate(ChunkGenerator generator, TemplateManager templateManager, BlockPos position, List<StructurePiece> pieces, SharedSeedRandom random) {
        JigsawManager.func_236823_a_(new ResourceLocation(ValhelsiaStructures.MOD_ID, "player_houses"), 7, PlayerHousePiece::new, generator, templateManager, position, pieces, random, true, true);
    }

    public static class PlayerHousePiece extends AbstractVillagePiece {
        public PlayerHousePiece(TemplateManager templateManager, JigsawPiece p_i50560_2_, BlockPos p_i50560_3_, int p_i50560_4_, Rotation p_i50560_5_, MutableBoundingBox p_i50560_6_) {
            super(ModStructurePieces.PLAYER_HOUSE, templateManager, p_i50560_2_, p_i50560_3_, p_i50560_4_, p_i50560_5_, p_i50560_6_);
        }

        public PlayerHousePiece(TemplateManager templateManager, CompoundNBT compoundNBT) {
            super(templateManager, compoundNBT, ModStructurePieces.PLAYER_HOUSE);
        }
    }
}
