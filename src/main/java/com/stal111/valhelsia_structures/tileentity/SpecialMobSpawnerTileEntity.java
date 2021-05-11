package com.stal111.valhelsia_structures.tileentity;

import com.stal111.valhelsia_structures.block.SpecialAbstractSpawner;
import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.init.ModTileEntities;
import com.stal111.valhelsia_structures.utils.SpecialWeightedSpawnerEntity;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Special Spawner Tile Entity
 * Valhelsia Structures - com.stal111.valhelsia_structures.tileentity.SpecialMobSpawnerTileEntity
 *
 * @author Valhelsia Team
 * @version 16.1.0
 */
public class SpecialMobSpawnerTileEntity extends TileEntity implements ITickableTileEntity {

    private final SpecialAbstractSpawner spawnerLogic = new SpecialAbstractSpawner() {
        public void broadcastEvent(int id) {
            if (SpecialMobSpawnerTileEntity.this.world != null){
                SpecialMobSpawnerTileEntity.this.world.addBlockEvent(SpecialMobSpawnerTileEntity.this.pos, ModBlocks.SPECIAL_SPAWNER.get(), id, 0);
            }
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
        super(ModTileEntities.SPECIAL_SPAWNER.get());
    }

    @Override
    public void read(@Nonnull BlockState state, @Nonnull CompoundNBT compound) {
        super.read(state, compound);
        this.spawnerLogic.read(compound);
    }

    @Nonnull
    @Override
    public CompoundNBT write(@Nonnull CompoundNBT compound) {
        super.write(compound);
        return spawnerLogic.write(compound);
    }

    @Override
    public void tick() {
        this.spawnerLogic.tick();
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
    }

    @Nonnull
    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT compoundnbt = this.write(new CompoundNBT());
        compoundnbt.remove("SpawnPotentials");
        return compoundnbt;
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, SUpdateTileEntityPacket packet) {
        if (this.world != null) {
            this.read(this.world.getBlockState(packet.getPos()), packet.getNbtCompound());
        }
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