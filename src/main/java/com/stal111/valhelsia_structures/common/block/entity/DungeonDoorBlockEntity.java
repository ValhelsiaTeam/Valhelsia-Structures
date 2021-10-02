package com.stal111.valhelsia_structures.common.block.entity;

import com.stal111.valhelsia_structures.core.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;

/**
 * Dungeon Door Block Entity <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.entity.DungeonDoorBlockEntity
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-01-13
 */
public class DungeonDoorBlockEntity extends BlockEntity implements LidBlockEntity {

    private float leafAngle;
    private float oLeafAngle;

    public DungeonDoorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DUNGEON_DOOR.get(), pos, state);
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, DungeonDoorBlockEntity blockEntity) {
        blockEntity.oLeafAngle = blockEntity.leafAngle;

        boolean open = blockEntity.getBlockState().getValue(BlockStateProperties.OPEN);
        if (open && blockEntity.leafAngle < 1.0F) {
            blockEntity.leafAngle += 0.1F;
        } else if (!open && blockEntity.leafAngle >= 0.1) {
            blockEntity.leafAngle -= 0.1F;
        }
    }

    @Override
    public float getOpenNess(float partialTicks) {
        return Mth.lerp(partialTicks, this.oLeafAngle, this.leafAngle);
    }

    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(this.worldPosition.offset(-5, -5, -5), this.worldPosition.offset(5, 5, 5));
    }
}
