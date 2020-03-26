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
 * Small Dungeon Pieces
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.structures.SmallDungeonPieces
 *
 * @author Valhelsia Team
 * @version 14.0.3
 * @since 2020-03-22
 */
public class SmallDungeonPieces {

    private static boolean isRegistered = false;

    public static void register() {
        if (!isRegistered) {
            JigsawManager.REGISTRY.register(
                    new JigsawPattern(new ResourceLocation(ValhelsiaStructures.MOD_ID, "dungeon1/entrances"),
                            new ResourceLocation("empty"),
                            ImmutableList.of(
                                    Pair.of(new ValhelsiaSingleJigsawPiece(ValhelsiaStructures.MOD_ID + ":dungeon1/entrance"), 1),
                                    Pair.of(new ValhelsiaSingleJigsawPiece(ValhelsiaStructures.MOD_ID + ":dungeon1/entrance1"), 1)),
                            JigsawPattern.PlacementBehaviour.RIGID));

            JigsawManager.REGISTRY.register(
                    new JigsawPattern(new ResourceLocation(ValhelsiaStructures.MOD_ID, "dungeon1/main_rooms"),
                            new ResourceLocation("empty"),
                            ImmutableList.of(
                                    Pair.of(new ValhelsiaSingleJigsawPiece(ValhelsiaStructures.MOD_ID + ":dungeon1/main_room"), 1),
                                    Pair.of(new ValhelsiaSingleJigsawPiece(ValhelsiaStructures.MOD_ID + ":dungeon1/main_room1"), 1),
                                    Pair.of(new ValhelsiaSingleJigsawPiece(ValhelsiaStructures.MOD_ID + ":dungeon1/main_room2"), 1)),
                            JigsawPattern.PlacementBehaviour.RIGID));

            JigsawManager.REGISTRY.register(
                    new JigsawPattern(new ResourceLocation(ValhelsiaStructures.MOD_ID, "dungeon1/side_rooms"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new ValhelsiaSingleJigsawPiece(ValhelsiaStructures.MOD_ID + ":dungeon1/side_room"), 1), Pair.of(new ValhelsiaSingleJigsawPiece(ValhelsiaStructures.MOD_ID + ":dungeon1/side_room1"), 1)), JigsawPattern.PlacementBehaviour.RIGID));

            isRegistered = true;
        }
    }

    public static void generate(ChunkGenerator<?> generator, TemplateManager templateManager, BlockPos position, List<StructurePiece> pieces, SharedSeedRandom random) {
        register();
        JigsawManager.func_214889_a(new ResourceLocation(ValhelsiaStructures.MOD_ID, "dungeon1/entrances"), 7, SmallDungeonPiece::new, generator, templateManager, position, pieces, random);
    }

    public static class SmallDungeonPiece extends AbstractVillagePiece {
        public SmallDungeonPiece(TemplateManager templateManager, JigsawPiece jigsawPiece, BlockPos position, int groundLevelDelta, Rotation rotation, MutableBoundingBox bounds) {
            super(ModStructurePieces.SMALL_DUNGEON, templateManager, jigsawPiece, position, groundLevelDelta, rotation, bounds);
        }

        public SmallDungeonPiece(TemplateManager templateManager, CompoundNBT compoundNBT) {
            super(templateManager, compoundNBT, ModStructurePieces.SMALL_DUNGEON);
        }
    }
}
