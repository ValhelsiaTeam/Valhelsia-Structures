package com.stal111.valhelsia_structures.common.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

/**
 * Big Jar Block Item <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.item.BigJarBlockItem
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-10-04
 */
public class BigJarBlockItem extends BlockItem {

    public BigJarBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    protected boolean mustSurvive() {
        return false;
    }
}
