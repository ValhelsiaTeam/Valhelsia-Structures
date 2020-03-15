package com.stal111.valhelsia_structures.world.template;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.JigsawReplacementStructureProcessor;

/**
 * Additional Processors for ValhelsiaStructure
 * ValhelsiaStructure - com.stal111.valhelsia_structure.world.template.Processors
 *
 * @author Valhelsia Team
 * @version 0.1
 * @since 2019-10-31
 */
public class Processors {
    /**
     * Processor that causes red glass to be ignored when placing the structure.
     */
    public static final BlockIgnoreStructureProcessor RED_GLASS_AND_STRUCTURE_BLOCK = new BlockIgnoreStructureProcessor(ImmutableList.of(Blocks.RED_STAINED_GLASS, Blocks.STRUCTURE_BLOCK));
    public static final ObsidianReplacementStructureProcessor OBSIDIAN = new ObsidianReplacementStructureProcessor();

}
