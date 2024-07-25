package com.stal111.valhelsia_structures.common.block;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.phys.AABB;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Special Base Spawner <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.SpecialBaseSpawner
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 */
public abstract class SpecialBaseSpawner {

    private static final Logger LOGGER = ValhelsiaStructures.LOGGER;

    private int spawnDelay = 20;
    private SimpleWeightedRandomList<SpawnData> spawnPotentials = SimpleWeightedRandomList.empty();
    private SpawnData nextSpawnData = new SpawnData();
    private double spin;
    private double oSpin;
    private int minSpawnDelay = 80;
    private int maxSpawnDelay = 100;
    private int spawnCount = 5;
    /** Cached instance of the entity to render inside the spawner. */
    @Nullable
    private Entity displayEntity;
    private int maxNearbyEntities = 7;
    private int requiredPlayerRange = 8;
    private int spawnRange = 4;
    private short waveCount = 0;

    public void setEntityId(EntityType<?> type) {
        this.nextSpawnData.getEntityToSpawn().putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(type).toString());
    }

    private boolean isNearPlayer(Level pLevel, BlockPos pPos) {
        return pLevel.hasNearbyAlivePlayer((double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, (double)this.requiredPlayerRange);
    }

    public void clientTick(Level level, BlockPos pos) {
        if (!this.isNearPlayer(level, pos)) {
            this.oSpin = this.spin;
        } else {
            double d0 = (double)pos.getX() + level.random.nextDouble();
            double d1 = (double)pos.getY() + level.random.nextDouble();
            double d2 = (double)pos.getZ() + level.random.nextDouble();
            level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            if (this.spawnDelay > 0) {
                --this.spawnDelay;
            }

            this.oSpin = this.spin;
            this.spin = (this.spin + (double)(1000.0F / ((float)this.spawnDelay + 200.0F))) % 360.0D;
        }
    }

    public void serverTick(@Nonnull ServerLevel serverLevel, @Nonnull BlockPos pos) {
        if (this.isNearPlayer(serverLevel, pos)) {
            if (this.spawnDelay == -1) {
                this.delay(serverLevel, pos);
            }

            if (this.spawnDelay > 0) {
                --this.spawnDelay;
            } else {
                boolean flag = false;

                for(int i = 0; i < this.spawnCount; ++i) {
                    CompoundTag compoundtag = this.nextSpawnData.getEntityToSpawn();
                    Optional<EntityType<?>> optional = EntityType.by(compoundtag);

                    if (optional.isEmpty()) {
                        this.delay(serverLevel, pos);
                        return;
                    }

                    ListTag listTag = compoundtag.getList("Pos", 6);
                    int j = listTag.size();
                    double d0 = j >= 1 ? listTag.getDouble(0) : (double)pos.getX() + (serverLevel.random.nextDouble() - serverLevel.random.nextDouble()) * (double)this.spawnRange + 0.5D;
                    double d1 = j >= 2 ? listTag.getDouble(1) : (double)(pos.getY() + serverLevel.random.nextInt(3) - 1);
                    double d2 = j >= 3 ? listTag.getDouble(2) : (double)pos.getZ() + (serverLevel.random.nextDouble() - serverLevel.random.nextDouble()) * (double)this.spawnRange + 0.5D;

                    if (serverLevel.noCollision(optional.get().getSpawnAABB(d0, d1, d2))) {
                        Entity entity = EntityType.loadEntityRecursive(compoundtag, serverLevel, (e) -> {
                            e.moveTo(d0, d1, d2, e.getYRot(), e.getXRot());
                            return e;
                        });
                        if (entity == null) {
                            this.delay(serverLevel, pos);
                            return;
                        }

                        int k = serverLevel.getEntitiesOfClass(entity.getClass(), (new AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1)).inflate(this.spawnRange)).size();
                        if (k >= this.maxNearbyEntities) {
                            this.delay(serverLevel, pos);
                            return;
                        }

                        entity.moveTo(entity.getX(), entity.getY(), entity.getZ(), serverLevel.random.nextFloat() * 360.0F, 0.0F);

                        if (entity instanceof Mob mob) {
                            if (this.nextSpawnData.getEntityToSpawn().size() == 1 && this.nextSpawnData.getEntityToSpawn().contains("id", 8)) {
                                mob.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.SPAWNER, null);
                            }
                        }

                        if (!serverLevel.tryAddFreshEntityWithPassengers(entity)) {
                            this.delay(serverLevel, pos);
                            return;
                        }

                        serverLevel.levelEvent(2004, pos, 0);
                        if (entity instanceof Mob mob) {
                            mob.spawnAnim();
                        }

                        flag = true;

                        if ((serverLevel.getRandom().nextDouble() <= 0.1D * (this.waveCount + 1) && this.waveCount >= 1) || this.waveCount >= 3) {
                            serverLevel.destroyBlock(pos, true);
                       }
                    }
                }

                if (flag) {
                    this.delay(serverLevel, pos);
                    this.waveCount++;
                }
            }
        }
    }

    private void delay(Level level, BlockPos pos) {
        if (this.maxSpawnDelay <= this.minSpawnDelay) {
            this.spawnDelay = this.minSpawnDelay;
        } else {
            this.spawnDelay = this.minSpawnDelay + level.getRandom().nextInt(this.maxSpawnDelay - this.minSpawnDelay);
        }

        this.spawnPotentials.getRandom(level.getRandom()).ifPresent(spawnData -> {
            this.setNextSpawnData(level, pos, spawnData.data());
        });
        this.broadcastEvent(level, pos, 1);
    }

    public void load(@Nullable Level level, BlockPos pos, CompoundTag tag) {
        this.spawnDelay = tag.getShort("Delay");

        boolean flag = tag.contains("SpawnPotentials", 9);
        boolean flag1 = tag.contains("SpawnData", 10);

        if (!flag) {
            SpawnData spawndata;
            if (flag1) {
                spawndata = SpawnData.CODEC.parse(NbtOps.INSTANCE, tag.getCompound("SpawnData")).resultOrPartial((p_186391_) -> {
                    LOGGER.warn("Invalid SpawnData: {}", p_186391_);
                }).orElseGet(SpawnData::new);
            } else {
                spawndata = new SpawnData();
            }

            this.spawnPotentials = SimpleWeightedRandomList.single(spawndata);
            this.setNextSpawnData(level, pos, spawndata);
        } else {
            ListTag listtag = tag.getList("SpawnPotentials", 10);
            this.spawnPotentials = SpawnData.LIST_CODEC.parse(NbtOps.INSTANCE, listtag).resultOrPartial((p_186388_) -> {
                LOGGER.warn("Invalid SpawnPotentials list: {}", p_186388_);
            }).orElseGet(SimpleWeightedRandomList::empty);
            if (flag1) {
                SpawnData spawnData = SpawnData.CODEC.parse(NbtOps.INSTANCE, tag.getCompound("SpawnData")).resultOrPartial((p_186380_) -> {
                    LOGGER.warn("Invalid SpawnData: {}", p_186380_);
                }).orElseGet(SpawnData::new);
                this.setNextSpawnData(level, pos, spawnData);
            } else {
                this.spawnPotentials.getRandom(Objects.requireNonNull(level).getRandom()).ifPresent(dataWrapper -> {
                    this.setNextSpawnData(level, pos, dataWrapper.data());
                });
            }
        }

        if (tag.contains("MinSpawnDelay", 99)) {
            this.minSpawnDelay = tag.getShort("MinSpawnDelay");
            this.maxSpawnDelay = tag.getShort("MaxSpawnDelay");
            this.spawnCount = tag.getShort("SpawnCount");
        }

        if (tag.contains("MaxNearbyEntities", 99)) {
            this.maxNearbyEntities = tag.getShort("MaxNearbyEntities");
            this.requiredPlayerRange = tag.getShort("RequiredPlayerRange");
        }

        if (tag.contains("SpawnRange", 99)) {
            this.spawnRange = tag.getShort("SpawnRange");
        }

        if (tag.contains("WaveCount")) {
            this.waveCount = tag.getShort("WaveCount");
        }

        this.displayEntity = null;
    }

    public CompoundTag save(@Nullable Level level, BlockPos pos, CompoundTag tag) {
        tag.putShort("Delay", (short) this.spawnDelay);
        tag.putShort("MinSpawnDelay", (short) this.minSpawnDelay);
        tag.putShort("MaxSpawnDelay", (short) this.maxSpawnDelay);
        tag.putShort("SpawnCount", (short) this.spawnCount);
        tag.putShort("MaxNearbyEntities", (short) this.maxNearbyEntities);
        tag.putShort("RequiredPlayerRange", (short) this.requiredPlayerRange);
        tag.putShort("SpawnRange", (short) this.spawnRange);
        tag.put("SpawnData", SpawnData.CODEC.encodeStart(NbtOps.INSTANCE, this.nextSpawnData).result().orElseThrow(() -> new IllegalStateException("Invalid SpawnData")));
        tag.put("SpawnPotentials", SpawnData.LIST_CODEC.encodeStart(NbtOps.INSTANCE, this.spawnPotentials).result().orElseThrow());
        tag.putShort("WaveCount", this.waveCount);

        return tag;
    }

    @Nullable
    public Entity getOrCreateDisplayEntity(Level level) {
        if (this.displayEntity == null) {
            this.displayEntity = EntityType.loadEntityRecursive(this.nextSpawnData.getEntityToSpawn(), level, Function.identity());
        }

        return this.displayEntity;
    }

    public boolean onEventTriggered(Level level, int i) {
        if (i == 1) {
            if (level.isClientSide) {
                this.spawnDelay = this.minSpawnDelay;
            }

            return true;
        }
        return false;
    }

    public void setNextSpawnData(@Nullable Level level, BlockPos pos, SpawnData spawnData) {
        this.nextSpawnData = spawnData;
    }

    public abstract void broadcastEvent(Level level, BlockPos pos, int i);

    public double getSpin() {
        return this.spin;
    }

    public double getOSpin() {
        return this.oSpin;
    }

    @Nullable
    public Entity getSpawnerEntity() {
        return null;
    }
}