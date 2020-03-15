package com.stal111.valhelsia_structures.tileentity;

import javax.annotation.Nullable;

import com.stal111.valhelsia_structures.block.SpecialAbstractSpawner;
import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.init.ModTileEntities;
import com.stal111.valhelsia_structures.utils.SpecialWeightedSpawnerEntity;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpecialMobSpawnerTileEntity extends TileEntity implements ITickableTileEntity {

    private final SpecialAbstractSpawner spawnerLogic = new SpecialAbstractSpawner() {
        public void broadcastEvent(int id) {
            SpecialMobSpawnerTileEntity.this.world.addBlockEvent(SpecialMobSpawnerTileEntity.this.pos, ModBlocks.SPECIAL_SPAWNER.getBlock(), id, 0);
        }

        @Override
        public World getWorld() {
            return SpecialMobSpawnerTileEntity.this.world;
        }

        @Override
        public BlockPos getSpawnerPosition() {
            return SpecialMobSpawnerTileEntity.this.pos;
        }

        @Override
        public void setNextSpawnData(SpecialWeightedSpawnerEntity nextSpawnData) {
            super.setNextSpawnData(nextSpawnData);
            if (this.getWorld() != null) {
                BlockState blockstate = this.getWorld().getBlockState(this.getSpawnerPosition());
                this.getWorld().notifyBlockUpdate(SpecialMobSpawnerTileEntity.this.pos, blockstate, blockstate, 4);
            }

        }
    };

    public SpecialMobSpawnerTileEntity() {
        super(ModTileEntities.SPECIAL_SPAWNER.getTileEntity());
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.spawnerLogic.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        this.spawnerLogic.write(compound);
        return compound;
    }

    @Override
    public void tick() {
        this.spawnerLogic.tick();
    }

    /**
     * Retrieves packet to send to the client whenever this Tile Entity is resynced via World.notifyBlockUpdate. For
     * modded TE's, this packet comes back to you clientside in {@link #onDataPacket}
     */
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 1, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT compoundnbt = this.write(new CompoundNBT());
        compoundnbt.remove("SpawnPotentials");
        return compoundnbt;
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        return this.spawnerLogic.setDelayToMin(id) || super.receiveClientEvent(id, type);
    }

    @Override
    public boolean onlyOpsCanSetNbt() {
        return true;
    }

    public SpecialAbstractSpawner getSpawnerBaseLogic() {
        return this.spawnerLogic;
    }
}
