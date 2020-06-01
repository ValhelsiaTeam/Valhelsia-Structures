package com.stal111.valhelsia_structures.world.template;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.JigsawReplacementStructureProcessor;

/**
 * Additional Processors for Valhelsia Structures
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.template.Processors
 *
 * @author Valhelsia Team
 * @version 15.0.3
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
    public static final ObsidianReplacementStructureProcessor OBSIDIAN_REPLACEMENT_PROCESSOR = new ObsidianReplacementStructureProcessor();

    /**
     * Processor that causes stone to be replaced with a different type to prevent it being overwritten by other generation.
     */
    public static final StoneReplacementStructureProcessor STONE_REPLACEMENT_PROCESSOR = new StoneReplacementStructureProcessor();

}
