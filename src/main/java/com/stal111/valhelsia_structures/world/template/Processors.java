package com.stal111.valhelsia_structures.world.template;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.StructureProcessor;

import java.util.List;

/**
 * Additional Processors for Valhelsia Structures
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.template.Processors
 *
 * @author Valhelsia Team
 * @version 14.0.3
 * @since 2019-10-31
 */
public class Processors {
    /**
     * Processor that causes red glass to be ignored when placing the structure.
     */
    public static final BlockIgnoreStructureProcessor RED_GLASS_AND_STRUCTURE_BLOCK = new BlockIgnoreStructureProcessor(ImmutableList.of(Blocks.RED_STAINED_GLASS, Blocks.STRUCTURE_BLOCK));

    /**
     * Processor that causes obsidian to be replaced with the special spawner when placing the structure.
     */
    public static final ObsidianReplacementStructureProcessor OBSIDIAN = new ObsidianReplacementStructureProcessor();

    /**
     * List of the processors that get applied to Valhelsia Structures jigsaw pieces.
     */
    public static final List<StructureProcessor> PIECE_PROCESSOR_LIST = ImmutableList.of(RED_GLASS_AND_STRUCTURE_BLOCK, OBSIDIAN);
}
