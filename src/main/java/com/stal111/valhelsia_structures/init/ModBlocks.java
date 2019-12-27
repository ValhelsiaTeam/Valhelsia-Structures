package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.block.SpecialSpawnerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;

public enum ModBlocks {
    SPECIAL_SPAWNER(new SpecialSpawnerBlock(Block.Properties.from(Blocks.SPAWNER)));

    private final Block block;

    ModBlocks(Block block) {
        this.block = block;
    }

    public String getName() {
        return String.valueOf(this).toLowerCase();
    }

    public Block getBlock() {
        if (block.getRegistryName() == null) {
            block.setRegistryName(ValhelsiaStructures.MOD_ID, getName());
        }
        return block;
    }

    public Item getItem() {
        return getBlock().asItem();
    }
}
