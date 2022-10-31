package com.stal111.valhelsia_structures.utils;

import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.common.world.structures.processor.ModProcessors;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Jigsaw Helper <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.utils.JigsawHelper
 *
 * @author Valhelsia Team
 * @version 1.18.2-0.1.0
 */
public class JigsawHelper {

    public static Holder<StructureTemplatePool> register(String name, StructureTemplatePool.Projection projection, List<Pair<String, Integer>> list, StructureProcessor... processors) {
        return register(name, projection, list, false, processors);
    }

    public static Holder<StructureTemplatePool> register(String name, StructureTemplatePool.Projection projection, List<Pair<String, Integer>> list, boolean replaceStone, StructureProcessor... processors) {
        return register(name, projection, list, replaceStone, false, processors);
    }

    public static Holder<StructureTemplatePool> register(String name, StructureTemplatePool.Projection projection, List<Pair<String, Integer>> list, boolean replaceStone, boolean legacyPiece, StructureProcessor... processors) {
        List<Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer>> newList = new ArrayList<>();

        List<StructureProcessor> processorList = new ArrayList<>(Arrays.asList(processors));
        processorList.add(ModProcessors.GRASS_BLOCK_REPLACEMENT_PROCESSOR);

        if (replaceStone) {
            processorList.add(ModProcessors.STONE_REPLACEMENT_PROCESSOR);
        }

        ResourceLocation resourceLocation = new ResourceLocation(ValhelsiaStructures.MOD_ID, name);

        Holder<StructureProcessorList> holder = BuiltinRegistries.register(BuiltinRegistries.PROCESSOR_LIST, resourceLocation, new StructureProcessorList(processorList));

        for (Pair<String, Integer> pair : list) {
            if (!legacyPiece) {
                newList.add(Pair.of(StructurePoolElement.single(ValhelsiaStructures.MOD_ID + ":" + pair.getFirst(), holder), pair.getSecond()));
            } else {
                newList.add(Pair.of(StructurePoolElement.legacy(ValhelsiaStructures.MOD_ID + ":" + pair.getFirst(), holder), pair.getSecond()));
            }
        }
        return Pools.register(new StructureTemplatePool(new ResourceLocation(ValhelsiaStructures.MOD_ID, name), new ResourceLocation("empty"), newList, projection));
    }
}
