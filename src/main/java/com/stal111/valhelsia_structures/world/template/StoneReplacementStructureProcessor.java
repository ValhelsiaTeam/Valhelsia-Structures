package com.stal111.valhelsia_structures.world.template;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Stone Replacement Structure Processor
 * Valhelsia-Structures - com.stal111.valhelsia_structures.world.template.StoneReplacementStructureProcessor
 *
 * Processor to replace vanilla stone with a replacement that is identical to vanilla stone with one exception -
 * it won't get replaced in later stages of world generation / decoration. The loot table matches vanilla stone types
 * so the player won't end up with unusual stone in their inventory unless they're in creative mode.
 *
 * @author Valhelsia Team
 * @version 15.0.3
 * @since 2020-05-27
 */

public class StoneReplacementStructureProcessor extends StructureProcessor {

    @Nullable
    @Override
    public Template.BlockInfo process(@Nonnull IWorldReader world, @Nonnull BlockPos pos, @Nonnull Template.BlockInfo blockInfo0, @Nonnull Template.BlockInfo blockInfo, @Nonnull PlacementSettings placementSettings, @Nullable Template template) {
        Block block = blockInfo.state.getBlock();
        if (block == Blocks.STONE) {
            return new Template.BlockInfo(blockInfo.pos, ModBlocks.STONE.get().getDefaultState(), null);
        } else if (block == Blocks.GRANITE) {
            return new Template.BlockInfo(blockInfo.pos, ModBlocks.GRANITE.get().getDefaultState(), null);
        } else if (block == Blocks.DIORITE) {
            return new Template.BlockInfo(blockInfo.pos, ModBlocks.DIORITE.get().getDefaultState(), null);
        } else if (block == Blocks.ANDESITE) {
            return new Template.BlockInfo(blockInfo.pos, ModBlocks.ANDESITE.get().getDefaultState(), null);
        }

        return blockInfo;
    }

    @Override
    @Nonnull
    protected IStructureProcessorType getType() {
        return IStructureProcessorType.JIGSAW_REPLACEMENT;
    }

    @Override
    @Nonnull
    protected <T> Dynamic<T> serialize0(@Nonnull DynamicOps<T> ops) {
        return new Dynamic<>(ops, ops.emptyMap());
    }
}
