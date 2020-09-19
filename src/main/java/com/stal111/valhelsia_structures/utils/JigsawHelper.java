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
 * @version 16.0.3
 */

public class JigsawHelper {

    public static JigsawPattern register(String name, JigsawPattern.PlacementBehaviour placementBehaviour, List<Pair<String, Integer>> list, StructureProcessor... processors) {
        return register(name, placementBehaviour, list, false, processors);
    }

    public static JigsawPattern register(String name, JigsawPattern.PlacementBehaviour placementBehaviour, List<Pair<String, Integer>> list, boolean replaceStone, StructureProcessor... processors) {
        List<Pair<Function<JigsawPattern.PlacementBehaviour, ? extends JigsawPiece>, Integer>> newList = new ArrayList<>();

        List<StructureProcessor> processorList = new ArrayList<>(Arrays.asList(processors));
        processorList.add(Processors.RED_GLASS_AND_STRUCTURE_BLOCK);

        if (replaceStone) {
            processorList.add(Processors.STONE_REPLACEMENT_PROCESSOR);
        }

        for (Pair<String, Integer> pair : list) {
            newList.add(Pair.of(JigsawPiece.func_242851_a (ValhelsiaStructures.MOD_ID + ":" + pair.getFirst(), new StructureProcessorList(processorList)), pair.getSecond()));
        }
        return JigsawPatternRegistry.func_244094_a(new JigsawPattern(new ResourceLocation(ValhelsiaStructures.MOD_ID, name), new ResourceLocation("empty"), newList, placementBehaviour));
    }
}
