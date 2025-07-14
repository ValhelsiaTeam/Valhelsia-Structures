package com.stal111.valhelsia_structures.common.block;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.extensions.IOwnedSpawner;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
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
public abstract class SpecialBaseSpawner implements IOwnedSpawner {

    private static final Logger LOGGER = ValhelsiaStructures.LOGGER;

    private int spawnDelay = 20;
    private SimpleWeightedRandomList<SpawnData> spawnPotentials = SimpleWeightedRandomList.empty();
    private @Nullable SpawnData nextSpawnData;
    private double spin;
    private double oSpin;
    private int minSpawnDelay = 80;
    private int maxSpawnDelay = 100;
    private int spawnCount = 5;
    /**
     * Cached instance of the entity to render inside the spawner.
     */
    @Nullable
    private Entity displayEntity;
    private int maxNearbyEntities = 7;
    private int requiredPlayerRange = 8;
    private int spawnRange = 4;
    private short waveCount = 0;

    public void setEntityId(EntityType<?> type, @Nullable Level level, RandomSource random, BlockPos pos) {
        this.getOrCreateNextSpawnData(level, random, pos).getEntityToSpawn().putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(type).toString());
    }

    private boolean isNearPlayer(Level pLevel, BlockPos pPos) {
        return pLevel.hasNearbyAlivePlayer((double) pPos.getX() + 0.5D, (double) pPos.getY() + 0.5D, (double) pPos.getZ() + 0.5D, this.requiredPlayerRange);
    }

    public void clientTick(Level level, BlockPos pos) {
        if (!this.isNearPlayer(level, pos)) {
            this.oSpin = this.spin;
        } else {
            double d0 = (double) pos.getX() + level.random.nextDouble();
            double d1 = (double) pos.getY() + level.random.nextDouble();
            double d2 = (double) pos.getZ() + level.random.nextDouble();
            level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            if (this.spawnDelay > 0) {
                --this.spawnDelay;
            }

            this.oSpin = this.spin;
            this.spin = (this.spin + (double) (1000.0F / ((float) this.spawnDelay + 200.0F))) % 360.0D;
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
                RandomSource random = serverLevel.getRandom();
                SpawnData spawnData = this.getOrCreateNextSpawnData(serverLevel, random, pos);

                for (int i = 0; i < this.spawnCount; ++i) {
                    CompoundTag tag = spawnData.getEntityToSpawn();
                    Optional<EntityType<?>> entityOptional = EntityType.by(tag);

                    if (entityOptional.isEmpty()) {
                        this.delay(serverLevel, pos);
                        return;
                    }

                    ListTag listTag = tag.getList("Pos", 6);
                    int j = listTag.size();
                    double d0 = j >= 1 ? listTag.getDouble(0) : (double) pos.getX() + (serverLevel.random.nextDouble() - serverLevel.random.nextDouble()) * (double) this.spawnRange + 0.5D;
                    double d1 = j >= 2 ? listTag.getDouble(1) : (double) (pos.getY() + serverLevel.random.nextInt(3) - 1);
                    double d2 = j >= 3 ? listTag.getDouble(2) : (double) pos.getZ() + (serverLevel.random.nextDouble() - serverLevel.random.nextDouble()) * (double) this.spawnRange + 0.5D;

                    if (serverLevel.noCollision(entityOptional.get().getSpawnAABB(d0, d1, d2))) {
                        BlockPos spawnPos = BlockPos.containing(d0, d1, d2);

                        if (spawnData.getCustomSpawnRules().isPresent()) {
                            if (!entityOptional.get().getCategory().isFriendly() && serverLevel.getDifficulty() == Difficulty.PEACEFUL) {
                                continue;
                            }

                            SpawnData.CustomSpawnRules spawnRules = spawnData.getCustomSpawnRules().get();
                            if (!spawnRules.isValidPosition(spawnPos, serverLevel)) {
                                continue;
                            }
                        } else if (!SpawnPlacements.checkSpawnRules(entityOptional.get(), serverLevel, EntitySpawnReason.SPAWNER, spawnPos, random)) {
                            continue;
                        }

                        Entity entity = EntityType.loadEntityRecursive(tag, serverLevel, EntitySpawnReason.SPAWNER, (e) -> {
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
                            boolean flag1 = spawnData.getEntityToSpawn().size() == 1 && spawnData.getEntityToSpawn().contains("id", 8);
                            EventHooks.finalizeMobSpawnSpawner(mob, serverLevel, serverLevel.getCurrentDifficultyAt(entity.blockPosition()), EntitySpawnReason.SPAWNER, (SpawnGroupData) null, this, flag1);
                            Optional<EquipmentTable> equipmentTable = spawnData.getEquipment();
                            equipmentTable.ifPresent(mob::equip);
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

                        if ((random.nextDouble() <= 0.1D * (this.waveCount + 1) && this.waveCount >= 1) || this.waveCount >= 3) {
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

        if (tag.contains("SpawnData", 10)) {
            SpawnData spawndata = SpawnData.CODEC.parse(NbtOps.INSTANCE, tag.getCompound("SpawnData")).resultOrPartial(result -> LOGGER.warn("Invalid SpawnData: {}", result)).orElseGet(SpawnData::new);
            this.setNextSpawnData(level, pos, spawndata);
        }

        if (tag.contains("SpawnPotentials", 9)) {
            ListTag listtag = tag.getList("SpawnPotentials", 10);
            this.spawnPotentials = SpawnData.LIST_CODEC.parse(NbtOps.INSTANCE, listtag).resultOrPartial(result -> LOGGER.warn("Invalid SpawnPotentials list: {}", result)).orElseGet(SimpleWeightedRandomList::empty);
        } else {
            this.spawnPotentials = SimpleWeightedRandomList.single(this.nextSpawnData != null ? this.nextSpawnData : new SpawnData());
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
        if (this.nextSpawnData != null) {
            tag.put("SpawnData", SpawnData.CODEC.encodeStart(NbtOps.INSTANCE, this.nextSpawnData).getOrThrow(error -> new IllegalStateException("Invalid SpawnData: " + error)));
        }
        tag.put("SpawnPotentials", SpawnData.LIST_CODEC.encodeStart(NbtOps.INSTANCE, this.spawnPotentials).getOrThrow());
        tag.putShort("WaveCount", this.waveCount);

        return tag;
    }

    @Nullable
    public Entity getOrCreateDisplayEntity(Level level, BlockPos pos) {
        if (this.displayEntity == null) {
            CompoundTag tag = this.getOrCreateNextSpawnData(level, level.getRandom(), pos).getEntityToSpawn();

            if (!tag.contains("id", 8)) {
                return null;
            }

            this.displayEntity = EntityType.loadEntityRecursive(tag, level, EntitySpawnReason.SPAWNER, Function.identity());
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

    private SpawnData getOrCreateNextSpawnData(@Nullable Level level, RandomSource random, BlockPos pos) {
        if (this.nextSpawnData == null) {
            this.setNextSpawnData(level, pos, this.spawnPotentials.getRandom(random).map(WeightedEntry.Wrapper::data).orElseGet(SpawnData::new));
        }
        return this.nextSpawnData;
    }

    public abstract void broadcastEvent(Level level, BlockPos pos, int i);

    public double getSpin() {
        return this.spin;
    }

    public double getOSpin() {
        return this.oSpin;
    }
}