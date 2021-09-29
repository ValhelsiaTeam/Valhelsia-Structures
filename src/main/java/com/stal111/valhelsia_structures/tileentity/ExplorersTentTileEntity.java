package com.stal111.valhelsia_structures.tileentity;

import com.stal111.valhelsia_structures.init.ModTileEntities;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * Explorers Tent Tile Entity
 * Valhelsia Structures - com.stal111.valhelsia_structures.tileentity.ExplorersTentTileEntity
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-12-10
 */
public class ExplorersTentTileEntity extends BlockEntity implements IDyeableTileEntity {

    private int color = 10511680;

    public ExplorersTentTileEntity() {
        super(null);
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public void read(BlockState state, CompoundTag compound) {
        super.read(state, compound);
        this.color =  compound.contains("Color") ? compound.getInt("Color") : 10511680;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("Color", color);

        return compound;
    }
}
