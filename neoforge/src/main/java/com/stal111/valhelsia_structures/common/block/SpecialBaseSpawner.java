package com.stal111.valhelsia_structures.common.block;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ProblemReporter;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.extensions.IOwnedSpawner;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
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
    private WeightedList<SpawnData> spawnPotentials = WeightedList.of();
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
    private int waveCount = 0;

    public void setEntityId(EntityType<?> type, @Nullable Level level, RandomSource random, BlockPos pos) {
        this.getOrCreateNextSpawnData(level, random, pos).getEntityToSpawn().putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(type).toString());
    }

    private boolean isNearPlayer(Level pLevel, BlockPos pPos) {
        return pLevel.hasNearbyAlivePlayer(pPos.getX() + 0.5D, pPos.getY() + 0.5D, pPos.getZ() + 0.5D, this.requiredPlayerRange);
    }

    public void clientTick(Level level, BlockPos pos) {
        if (!this.isNearPlayer(level, pos)) {
            this.oSpin = this.spin;
        } else if (this.displayEntity != null) {
            double d0 = pos.getX() + level.random.nextDouble();
            double d1 = pos.getY() + level.random.nextDouble();
            double d2 = pos.getZ() + level.random.nextDouble();
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
                    try (ProblemReporter.ScopedCollector scopedCollector = new ProblemReporter.ScopedCollector(this::toString, LOGGER)) {
                        ValueInput input = TagValueInput.create(scopedCollector, serverLevel.registryAccess(), spawnData.getEntityToSpawn());
                        Optional<EntityType<?>> entityOptional = EntityType.by(input);

                        if (entityOptional.isEmpty()) {
                            this.delay(serverLevel, pos);
                            return;
                        }

                        Vec3 vec3 = input.read("Pos", Vec3.CODEC).orElseGet(() -> new Vec3(pos.getX() + (random.nextDouble() - random.nextDouble()) * this.spawnRange + 0.5, pos.getY() + random.nextInt(3) - 1, pos.getZ() + (random.nextDouble() - random.nextDouble()) * this.spawnRange + 0.5));

                        if (serverLevel.noCollision(entityOptional.get().getSpawnAABB(vec3.x, vec3.y, vec3.z))) {
                            BlockPos spawnPos = BlockPos.containing(vec3);

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

                            Entity entity = EntityType.loadEntityRecursive(input, serverLevel, EntitySpawnReason.SPAWNER, (e) -> {
                                e.snapTo(vec3.x, vec3.y, vec3.z, e.getYRot(), e.getXRot());
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

                            entity.snapTo(entity.getX(), entity.getY(), entity.getZ(), serverLevel.random.nextFloat() * 360.0F, 0.0F);

                            if (entity instanceof Mob mob) {
                                boolean flag1 = spawnData.getEntityToSpawn().size() == 1 && spawnData.getEntityToSpawn().getString("id").isPresent();
                                EventHooks.finalizeMobSpawnSpawner(mob, serverLevel, serverLevel.getCurrentDifficultyAt(entity.blockPosition()), EntitySpawnReason.SPAWNER, null, this, flag1);

                                spawnData.getEquipment().ifPresent(mob::equip);
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
            this.setNextSpawnData(level, pos, spawnData);
        });
        this.broadcastEvent(level, pos, 1);
    }

    public void load(@Nullable Level level, BlockPos pos, ValueInput input) {
        this.spawnDelay = input.getShortOr("Delay",  (short) 20);

        input.read("SpawnData", SpawnData.CODEC).ifPresent(spawnData -> this.setNextSpawnData(level, pos, spawnData));

        this.spawnPotentials = input.read("SpawnPotentials", SpawnData.LIST_CODEC).orElseGet(() -> WeightedList.of(this.nextSpawnData != null ? this.nextSpawnData : new SpawnData()));

        this.minSpawnDelay = input.getIntOr("MinSpawnDelay", 200);
        this.maxSpawnDelay = input.getIntOr("MaxSpawnDelay", 800);
        this.spawnCount = input.getIntOr("SpawnCount", 4);

        this.maxNearbyEntities = input.getIntOr("MaxNearbyEntities", 6);
        this.requiredPlayerRange = input.getIntOr("RequiredPlayerRange", 16);
        this.spawnRange = input.getIntOr("SpawnRange", 4);
        this.waveCount = input.getIntOr("WaveCount", 0);

        this.displayEntity = null;
    }

    public void save(ValueOutput output) {
        output.putShort("Delay", (short) this.spawnDelay);
        output.putShort("MinSpawnDelay", (short) this.minSpawnDelay);
        output.putShort("MaxSpawnDelay", (short) this.maxSpawnDelay);
        output.putShort("SpawnCount", (short) this.spawnCount);
        output.putShort("MaxNearbyEntities", (short) this.maxNearbyEntities);
        output.putShort("RequiredPlayerRange", (short) this.requiredPlayerRange);
        output.putShort("SpawnRange", (short) this.spawnRange);
        output.storeNullable("SpawnData", SpawnData.CODEC, this.nextSpawnData);
        output.store("SpawnPotentials", SpawnData.LIST_CODEC, this.spawnPotentials);
        output.putInt("WaveCount", this.waveCount);
    }

    @Nullable
    public Entity getOrCreateDisplayEntity(Level level, BlockPos pos) {
        if (this.displayEntity == null) {
            CompoundTag tag = this.getOrCreateNextSpawnData(level, level.getRandom(), pos).getEntityToSpawn();

            if (tag.getString("id").isEmpty()) {
                return null;
            }

            this.displayEntity = EntityType.loadEntityRecursive(tag, level, EntitySpawnReason.SPAWNER, Function.identity());
        }

        return this.displayEntity;
    }

    public boolean onEventTriggered(Level level, int i) {
        if (i == 1) {
            if (level.isClientSide()) {
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
            this.setNextSpawnData(level, pos, this.spawnPotentials.getRandom(random).orElseGet(SpawnData::new));
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