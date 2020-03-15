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

import javax.annotation.Nullable;

public class ObsidianReplacementStructureProcessor extends StructureProcessor {

    @Nullable
    @Override
    public Template.BlockInfo process(IWorldReader world, BlockPos pos, Template.BlockInfo p_215194_3, Template.BlockInfo p_215194_4_, PlacementSettings p_215194_5_, @Nullable Template p_process_6_) {
        Block block = p_215194_4_.state.getBlock();
        if (block != Blocks.OBSIDIAN) {
            return p_215194_4_;
        } else {
            return new Template.BlockInfo(p_215194_4_.pos, ModBlocks.SPECIAL_SPAWNER.getBlock().getDefaultState(), null);
        }
    }

    @Override
    protected IStructureProcessorType getType() {
        return IStructureProcessorType.JIGSAW_REPLACEMENT;
    }

    @Override
    protected <T> Dynamic serialize0(DynamicOps<T> ops) {
        return new Dynamic(ops, ops.emptyMap());
    }
}
