package com.stal111.valhelsia_structures.common.block.entity;

import com.stal111.valhelsia_structures.common.block.SpecialBaseSpawner;
import com.stal111.valhelsia_structures.core.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

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

    private final SpecialBaseSpawner spawner = new SpecialBaseSpawner() {
        @Override
        public void broadcastEvent(Level level, @Nonnull BlockPos pos, int i) {
            level.blockEvent(pos, Blocks.SPAWNER, i, 0);
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

    public SpecialSpawnerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SPECIAL_SPAWNER.get(), pos, state);
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, SpecialSpawnerBlockEntity blockEntity) {
        blockEntity.spawner.clientTick(level, pos);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SpecialSpawnerBlockEntity blockEntity) {
        blockEntity.spawner.serverTick((ServerLevel) level, pos);
    }

    @Override
    public void loadAdditional(@Nonnull CompoundTag tag, HolderLookup.@NotNull Provider lookupProvider) {
        super.loadAdditional(tag, lookupProvider);
        this.spawner.load(this.level, this.worldPosition, tag);
    }

    @Override
    public void saveAdditional(@Nonnull CompoundTag tag, HolderLookup.@NotNull Provider lookupProvider) {
        this.spawner.save(this.level, this.worldPosition, tag);
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Nonnull
    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider lookupProvider) {
        CompoundTag tag = this.saveWithoutMetadata(lookupProvider);
        tag.remove("SpawnPotentials");
        return tag;
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        return this.spawner.onEventTriggered(Objects.requireNonNull(this.level), id) || super.triggerEvent(id, type);
    }

    @Override
    public boolean onlyOpCanSetNbt() {
        return true;
    }

    public SpecialBaseSpawner getSpawner() {
        return this.spawner;
    }
}
