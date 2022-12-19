package com.stal111.valhelsia_structures.utils;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;

import java.util.List;

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
//        List<Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer>> newList = new ArrayList<>();
//
//        List<StructureProcessor> processorList = new ArrayList<>(Arrays.asList(processors));
//        processorList.add(ModProcessors.GRASS_BLOCK_REPLACEMENT_PROCESSOR);
//
//        if (replaceStone) {
//            processorList.add(ModProcessors.STONE_REPLACEMENT_PROCESSOR);
//        }
//
//        ResourceLocation resourceLocation = new ResourceLocation(ValhelsiaStructures.MOD_ID, name);
//
//        Holder<StructureProcessorList> holder = BuiltinRegistries.register(BuiltinRegistries.PROCESSOR_LIST, resourceLocation, new StructureProcessorList(processorList));
//
//        for (Pair<String, Integer> pair : list) {
//            if (!legacyPiece) {
//                newList.add(Pair.of(StructurePoolElement.single(ValhelsiaStructures.MOD_ID + ":" + pair.getFirst(), holder), pair.getSecond()));
//            } else {
//                newList.add(Pair.of(StructurePoolElement.legacy(ValhelsiaStructures.MOD_ID + ":" + pair.getFirst(), holder), pair.getSecond()));
//            }
//        }
//        return Pools.register(new StructureTemplatePool(new ResourceLocation(ValhelsiaStructures.MOD_ID, name), new ResourceLocation("empty"), newList, projection));
        return Holder.direct(null);
    }
}
