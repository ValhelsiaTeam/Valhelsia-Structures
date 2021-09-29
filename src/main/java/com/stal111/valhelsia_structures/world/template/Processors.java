package com.stal111.valhelsia_structures.world.template;

import com.google.common.collect.ImmutableList;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.gen.feature.template.*;

import net.minecraft.world.level.levelgen.structure.templatesystem.AlwaysTrueTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProcessorRule;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleProcessor;

/**
 * Additional Processors for Valhelsia Structures
 * Valhelsia Structures - com.stal111.valhelsia_structures.world.template.Processors
 *
 * @author Valhelsia Team
 * @version 16.0.5
 * @since 2019-10-31
 */
public class Processors {

    /**
     * Processor that causes red glass to be ignored when placing the structure.
     */
    public static final BlockIgnoreProcessor RED_GLASS = new BlockIgnoreProcessor(ImmutableList.of(Blocks.RED_STAINED_GLASS));

    /**
     * Processor that causes obsidian to be replaced with the special spawner when placing the structure.
     */
    public static final RuleProcessor OBSIDIAN_REPLACEMENT_PROCESSOR = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new BlockMatchTest(Blocks.OBSIDIAN), AlwaysTrueTest.INSTANCE, ModBlocks.SPECIAL_SPAWNER.get().getDefaultState())
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

    /**
     * Processor that causes grass blocks to be replaced with a different type to prevent features generating on it.
     */
    public static final RuleStructureProcessor GRASS_BLOCK_REPLACEMENT_PROCESSOR = new RuleStructureProcessor(ImmutableList.of(
            new RuleEntry(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), AlwaysTrueRuleTest.INSTANCE, ModBlocks.GRASS_BLOCK.get().getDefaultState())
    ));
}
