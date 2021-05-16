package com.stal111.valhelsia_structures.utils;

import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.world.template.Processors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.jigsaw.*;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.StructureProcessorList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Jigsaw Helper
 * Valhelsia Structures - com.stal111.valhelsia_structures.utils.JigsawHelper
 *
 * @author Valhelsia Team
 * @version 16.0.5
 */
public class JigsawHelper {

    public static JigsawPattern register(String name, JigsawPattern.PlacementBehaviour placementBehaviour, List<Pair<String, Integer>> list, StructureProcessor... processors) {
        return register(name, placementBehaviour, list, false, processors);
    }

    public static JigsawPattern register(String name, JigsawPattern.PlacementBehaviour placementBehaviour, List<Pair<String, Integer>> list, boolean replaceStone, StructureProcessor... processors) {
        return register(name, placementBehaviour, list, replaceStone, false, processors);
    }

    public static JigsawPattern register(String name, JigsawPattern.PlacementBehaviour placementBehaviour, List<Pair<String, Integer>> list, boolean replaceStone, boolean legacyPiece, StructureProcessor... processors) {
        List<Pair<Function<JigsawPattern.PlacementBehaviour, ? extends JigsawPiece>, Integer>> newList = new ArrayList<>();

        List<StructureProcessor> processorList = new ArrayList<>(Arrays.asList(processors));
        processorList.add(Processors.RED_GLASS);
        processorList.add(Processors.GRASS_BLOCK_REPLACEMENT_PROCESSOR);

        if (replaceStone) {
            processorList.add(Processors.STONE_REPLACEMENT_PROCESSOR);
        }

        for (Pair<String, Integer> pair : list) {
            if (!legacyPiece) {
                newList.add(Pair.of(JigsawPiece.func_242861_b (ValhelsiaStructures.MOD_ID + ":" + pair.getFirst(), new StructureProcessorList(processorList)), pair.getSecond()));
            } else {
                newList.add(Pair.of(JigsawPiece.func_242851_a (ValhelsiaStructures.MOD_ID + ":" + pair.getFirst(), new StructureProcessorList(processorList)), pair.getSecond()));
            }
        }
        return JigsawPatternRegistry.func_244094_a(new JigsawPattern(new ResourceLocation(ValhelsiaStructures.MOD_ID, name), new ResourceLocation("empty"), newList, placementBehaviour));
    }
}
