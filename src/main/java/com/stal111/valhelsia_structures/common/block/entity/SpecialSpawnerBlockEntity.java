package com.stal111.valhelsia_structures.common.block.entity;

import com.stal111.valhelsia_structures.init.ModTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Special Spawner Block Entity <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.entity.SpecialSpawnerBlockEntity
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-10-01
 */
public class SpecialSpawnerBlockEntity extends BlockEntity {

    private final BaseSpawner spawner = new BaseSpawner() {
        @Override
        public void broadcastEvent(Level level, @Nonnull BlockPos pos, int i) {
            level.blockEvent(pos, Blocks.SPAWNER, i, 0);
            SpecialSpawnerBlockEntity.this.waveCount++;
        }

        @Override
        public void setNextSpawnData(@Nullable Level level, @Nonnull BlockPos pos, @Nonnull SpawnData spawnData) {
            super.setNextSpawnData(level, pos, spawnData);
            if (level != null) {
                BlockState state = level.getBlockState(pos);
                level.sendBlockUpdated(pos, state, state, 4);
            }
        }

        @Nonnull
        public BlockEntity getSpawnerBlockEntity() {
            return SpecialSpawnerBlockEntity.this;
        }
    };

    private int waveCount = 0;

    public SpecialSpawnerBlockEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.SPECIAL_SPAWNER.get(), pos, state);
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, SpecialSpawnerBlockEntity blockEntity) {
        blockEntity.spawner.clientTick(level, pos);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SpecialSpawnerBlockEntity blockEntity) {
        blockEntity.spawner.serverTick((ServerLevel) level, pos);

        if ((blockEntity.waveCount >= 1 && level.random.nextDouble() <= 0.15D) || blockEntity.waveCount >= 3) {
            level.destroyBlock(pos, false);
        }
    }

    @Override
    public void load(@Nonnull CompoundTag compound) {
        super.load(compound);
        this.spawner.load(this.level, this.worldPosition, compound);
        this.waveCount = compound.getInt("WaveCount");
    }

    @Nonnull
    @Override
    public CompoundTag save(@Nonnull CompoundTag compound) {
        super.save(compound);
        this.spawner.save(this.level, this.worldPosition, compound);
        compound.putInt("WaveCount", this.waveCount);
        return compound;
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return new ClientboundBlockEntityDataPacket(this.worldPosition, 1, this.getUpdateTag());
    }

    @Nonnull
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundtag = this.save(new CompoundTag());
        compoundtag.remove("SpawnPotentials");
        return compoundtag;
    }

    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket packet) {
        if (this.level != null) {
            this.load(packet.getTag());
        }
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        return this.spawner.onEventTriggered(Objects.requireNonNull(this.level), id) || super.triggerEvent(id, type);
    }

    @Override
    public boolean onlyOpCanSetNbt() {
        return true;
    }

    public BaseSpawner getSpawner() {
        return this.spawner;
    }
}