package com.stal111.valhelsia_structures.tileentity;

import com.stal111.valhelsia_structures.init.ModTileEntities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;
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

    public float getLeafAngle(float partialTicks) {
        return MathHelper.lerp(partialTicks, this.prevLeafAngle, this.leafAngle);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 16, this.getUpdateTag());
    }

    @Nonnull
    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, SUpdateTileEntityPacket packet) {
        if (this.world != null) {
            this.read(this.world.getBlockState(packet.getPos()), packet.getNbtCompound());
        }
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(getPos().add(-5, -5, -5), getPos().add(5, 5, 5));
    }
}
