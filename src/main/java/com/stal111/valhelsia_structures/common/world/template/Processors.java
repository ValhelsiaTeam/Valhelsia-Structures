package com.stal111.valhelsia_structures.common.world.template;

import com.google.common.collect.ImmutableList;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.AlwaysTrueTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProcessorRule;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleProcessor;

/**
 * Additional Processors for Valhelsia Structures <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.world.template.Processors
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2019-10-31
 */
public class Processors {

    /**
     * Processor that causes stone to be replaced with a different type to prevent it being overwritten by other generation.
     */
    public static final RuleProcessor STONE_REPLACEMENT_PROCESSOR = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new BlockMatchTest(Blocks.STONE), AlwaysTrueTest.INSTANCE, ModBlocks.STONE.get().defaultBlockState()),
            new ProcessorRule(new BlockMatchTest(Blocks.GRANITE), AlwaysTrueTest.INSTANCE, ModBlocks.GRANITE.get().defaultBlockState()),
            new ProcessorRule(new BlockMatchTest(Blocks.DIORITE), AlwaysTrueTest.INSTANCE, ModBlocks.DIORITE.get().defaultBlockState()),
            new ProcessorRule(new BlockMatchTest(Blocks.ANDESITE), AlwaysTrueTest.INSTANCE, ModBlocks.ANDESITE.get().defaultBlockState())
    ));

    /**
     * Processor that causes grass blocks to be replaced with a different type to prevent features generating on it.
     */
    public static final RuleProcessor GRASS_BLOCK_REPLACEMENT_PROCESSOR = new RuleProcessor(ImmutableList.of(
            new ProcessorRule(new BlockMatchTest(Blocks.GRASS_BLOCK), AlwaysTrueTest.INSTANCE, ModBlocks.GRASS_BLOCK.get().defaultBlockState())
    ));
}
