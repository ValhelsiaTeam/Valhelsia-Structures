package com.stal111.valhelsia_structures.world.template;

import com.google.common.collect.ImmutableList;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.template.*;

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
    public static final RuleStructureProcessor OBSIDIAN_REPLACEMENT_PROCESSOR = new RuleStructureProcessor(ImmutableList.of(
            new RuleEntry(new BlockMatchRuleTest(Blocks.OBSIDIAN), AlwaysTrueRuleTest.INSTANCE, ModBlocks.SPECIAL_SPAWNER.get().getDefaultState())
    ));

    /**
     * Processor that causes stone to be replaced with a different type to prevent it being overwritten by other generation.
     */
    public static final RuleStructureProcessor STONE_REPLACEMENT_PROCESSOR = new RuleStructureProcessor(ImmutableList.of(
            new RuleEntry(new BlockMatchRuleTest(Blocks.STONE), AlwaysTrueRuleTest.INSTANCE, ModBlocks.STONE.get().getDefaultState()),
            new RuleEntry(new BlockMatchRuleTest(Blocks.GRANITE), AlwaysTrueRuleTest.INSTANCE, ModBlocks.GRANITE.get().getDefaultState()),
            new RuleEntry(new BlockMatchRuleTest(Blocks.DIORITE), AlwaysTrueRuleTest.INSTANCE, ModBlocks.DIORITE.get().getDefaultState()),
            new RuleEntry(new BlockMatchRuleTest(Blocks.ANDESITE), AlwaysTrueRuleTest.INSTANCE, ModBlocks.ANDESITE.get().getDefaultState())
    ));
}
