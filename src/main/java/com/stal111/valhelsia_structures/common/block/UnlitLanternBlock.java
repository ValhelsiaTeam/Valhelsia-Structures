package com.stal111.valhelsia_structures.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;

import java.util.function.Supplier;

/**
 * Unlit Lantern Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.UnlitLanternBlock
 *
 * @author Valhelsia Team
 * @version 1.18.1 - 0.2.0
 * @since 2022-01-20
 */
public class UnlitLanternBlock extends LanternBlock {

    private final Supplier<Block> lanternBlock;

    public UnlitLanternBlock(Supplier<Block> lanternBlock, Properties properties) {
        super(properties);
        this.lanternBlock = lanternBlock;
    }

    public Block getLitLantern() {
        return this.lanternBlock.get();
    }
}
