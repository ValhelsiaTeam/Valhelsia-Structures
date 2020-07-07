package com.stal111.valhelsia_structures.utils;

import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.world.template.Processors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.template.StructureProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JigsawHelper {

    public static void register(String name, JigsawPattern.PlacementBehaviour placementBehaviour, List<Pair<String, Integer>> list, StructureProcessor... processors) {
        register(name, placementBehaviour, list, false, processors);
    }

    public static void register(String name, JigsawPattern.PlacementBehaviour placementBehaviour, List<Pair<String, Integer>> list, boolean replaceStone, StructureProcessor... processors) {
        List<Pair<JigsawPiece, Integer>> newList = new ArrayList<>();

        List<StructureProcessor> processorList = new ArrayList<>(Arrays.asList(processors));
        processorList.add(Processors.RED_GLASS_AND_STRUCTURE_BLOCK);

        if (replaceStone) {
            processorList.add(Processors.STONE_REPLACEMENT_PROCESSOR);
        }

        for (Pair<String, Integer> pair : list) {
            newList.add(Pair.of(new SingleJigsawPiece(ValhelsiaStructures.MOD_ID + ":" + pair.getFirst(), processorList), pair.getSecond()));
        }
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(ValhelsiaStructures.MOD_ID, name), new ResourceLocation("empty"), newList, placementBehaviour));
    }
}
