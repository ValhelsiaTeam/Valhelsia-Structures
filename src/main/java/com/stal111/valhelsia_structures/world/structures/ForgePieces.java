package com.stal111.valhelsia_structures.world.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.init.ModStructurePieces;
import com.stal111.valhelsia_structures.world.template.ValhelsiaSingleJigsawPiece;
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
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;

/**
 * Forge Pieces
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.ForgePieces
 *
 * @author Valhelsia Team
 * @version 14.0.3
 * @since 2020-03-22
 */
public class ForgePieces {
    private static boolean isRegistered = false;

    public static void register() {
        if (!isRegistered) {
            JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(ValhelsiaStructures.MOD_ID, "forges"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new ValhelsiaSingleJigsawPiece(ValhelsiaStructures.MOD_ID + ":forge"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
            isRegistered = true;
        }
    }

    public static void generate(ChunkGenerator<?> generator, TemplateManager templateManager, BlockPos position, List<StructurePiece> pieces, SharedSeedRandom random) {
        register();
        JigsawManager.func_214889_a(new ResourceLocation(ValhelsiaStructures.MOD_ID, "forges"), 7, ForgePiece::new, generator, templateManager, position, pieces, random);
    }

    public static class ForgePiece extends AbstractVillagePiece {
        public ForgePiece(TemplateManager templateManager, JigsawPiece jigsawPiece, BlockPos position, int groundLevelDelta, Rotation rotation, MutableBoundingBox bounds) {
            super(ModStructurePieces.FORGE, templateManager, jigsawPiece, position, groundLevelDelta, rotation, bounds);
        }

        public ForgePiece(TemplateManager templateManager, CompoundNBT compoundNBT) {
            super(templateManager, compoundNBT, ModStructurePieces.FORGE);
        }
    }
}
