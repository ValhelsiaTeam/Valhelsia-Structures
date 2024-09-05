package com.stal111.valhelsia_structures.common.block.entity;

import com.stal111.valhelsia_structures.core.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Giant Fern Block Entity <br>
 * ValhelsiaStructures - com.stal111.valhelsia_structures.common.block.entity.GiantFernBlockEntity
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2020-12-22
 */
public class GiantFernBlockEntity extends BlockEntity {

    public GiantFernBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GIANT_FERN.get(), pos, state);
    }
}
