package com.stal111.valhelsia_structures.tileentity;

import com.stal111.valhelsia_structures.block.properties.DungeonDoorPart;
import com.stal111.valhelsia_structures.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.init.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

/**
 * Dungeon Door Tile Entity
 * Valhelsia Structures - com.stal111.valhelsia_structures.tileentity.DungeonDoorTileEntity
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-13
 */
public class DungeonDoorTileEntity extends TileEntity implements ITickableTileEntity {

    private float prevLeafAngle;
    private float leafAngle;

    private BlockPos mainBlock = BlockPos.ZERO;

    public DungeonDoorTileEntity() {
        super(ModTileEntities.DUNGEON_DOOR.get());
    }

    @Override
    public void tick() {
        this.prevLeafAngle = leafAngle;

        boolean open = getBlockState().get(BlockStateProperties.OPEN);
        if (open && this.leafAngle < 1.0F) {
            this.leafAngle += 0.1F;
        } else if (!open && this.leafAngle >= 0.1) {
            this.leafAngle -= 0.1F;
        }
    }

    public BlockPos getMainBlock() {
        return mainBlock;
    }

    public void setMainBlock(BlockPos mainBlock) {
        this.mainBlock = mainBlock;
    }

    public float getLeafAngle(float partialTicks) {
        return MathHelper.lerp(partialTicks, this.prevLeafAngle, this.leafAngle);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);

        if (mainBlock != BlockPos.ZERO) {
            compound.putLong("mainBlock", mainBlock.toLong());
        }
        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        if (compound.contains("mainBlock")) {
            mainBlock = BlockPos.fromLong(compound.getLong("mainBlock"));
        }
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 16, this.getUpdateTag());
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        BlockState state = getTileEntity().getBlockState();

        if (state.get(ModBlockStateProperties.DUNGEON_DOOR_PART) == DungeonDoorPart.MIDDLE_1) {
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(
                    getPos().add(-5, -5, -5),
                    getPos().add(5, 5, 5));
            return axisAlignedBB;
        }
        return new AxisAlignedBB(0, 0, 0, 0, 0, 0);
    }
}
