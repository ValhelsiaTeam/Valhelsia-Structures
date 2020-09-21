package com.stal111.valhelsia_structures.block;

import com.google.common.collect.Lists;
import com.stal111.valhelsia_structures.utils.SpecialWeightedSpawnerEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

public abstract class SpecialAbstractSpawner {
    private static final Logger LOGGER = LogManager.getLogger();
    private int spawnDelay = 20;
    private final List<SpecialWeightedSpawnerEntity> potentialSpawns = Lists.newArrayList();
    private SpecialWeightedSpawnerEntity spawnData = new SpecialWeightedSpawnerEntity();
    private double mobRotation;
    private double prevMobRotation;
    private int minSpawnDelay = 80;
    private int maxSpawnDelay = 100;
    private int spawnCount = 5;
    private Entity cachedEntity;
    private int maxNearbyEntities = 7;
    private int activatingRangeFromPlayer = 8;
    private int spawnRange = 4;
    private int waveCount = 0;

    @Nullable
    private ResourceLocation getEntityId() {
        String s = this.spawnData.getNbt().getString("id");

        try {
            return StringUtils.isNullOrEmpty(s) ? null : new ResourceLocation(s);
        } catch (ResourceLocationException var4) {
            BlockPos blockpos = this.getSpawnerPosition();
            LOGGER.warn("Invalid entity id '{}' at spawner {}:[{},{},{}]", s, this.getWorld().getDimensionKey().getLocation(), blockpos.getX(), blockpos.getY(), blockpos.getZ());
            return null;
        }
    }

    public void setEntityType(EntityType<?> type) {
        this.spawnData.getNbt().putString("id", Objects.requireNonNull(ForgeRegistries.ENTITIES.getKey(type)).toString());
    }

    /**
     * Returns true if there's a player close enough to this mob spawner to activate it.
     */
    private boolean isActivated() {
        BlockPos blockpos = this.getSpawnerPosition();

        for(PlayerEntity playerentity : this.getWorld().getPlayers()) {
            if (EntityPredicates.NOT_SPECTATING.test(playerentity) && EntityPredicates.IS_LIVING_ALIVE.test(playerentity)) {
                double d0 = playerentity.getDistanceSq((double) blockpos.getX() + 0.5D, (double) blockpos.getY() + 0.5D, (double) blockpos.getZ() + 0.5D);
                if (this.activatingRangeFromPlayer < 0.0D || d0 < this.activatingRangeFromPlayer * this.activatingRangeFromPlayer) {
                    if (playerentity.getPosY() >= blockpos.getY()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void tick() {
        if (!this.isActivated()) {
            this.prevMobRotation = this.mobRotation;
        } else {
            World world = this.getWorld();
            BlockPos blockpos = this.getSpawnerPosition();
            if (world.isRemote) {
                double d3 = ((float)blockpos.getX() + world.rand.nextFloat());
                double d4 = ((float)blockpos.getY() + world.rand.nextFloat());
                double d5 = ((float)blockpos.getZ() + world.rand.nextFloat());
                world.addParticle(ParticleTypes.SMOKE, d3, d4, d5, 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, d3, d4, d5, 0.0D, 0.0D, 0.0D);
                if (this.spawnDelay > 0) {
                    --this.spawnDelay;
                }

                this.prevMobRotation = this.mobRotation;
                this.mobRotation = (this.mobRotation + (double)(1000.0F / ((float)this.spawnDelay + 200.0F))) % 360.0D;
            } else {
                if (this.spawnDelay == -1) {
                    this.resetTimer();
                }

                if (this.spawnDelay > 0) {
                    --this.spawnDelay;
                    return;
                }

                boolean flag = false;

                for(int i = 0; i < this.spawnCount; ++i) {
                    CompoundNBT compoundnbt = this.spawnData.getNbt();
                    Optional<EntityType<?>> optional = EntityType.readEntityType(compoundnbt);
                    if (!optional.isPresent()) {
                        this.resetTimer();
                        return;
                    }

                    ListNBT listnbt = compoundnbt.getList("Pos", 6);
                    int j = listnbt.size();
                    double d0 = j >= 1 ? listnbt.getDouble(0) : (double)blockpos.getX() + (world.rand.nextDouble() - world.rand.nextDouble()) * (double)this.spawnRange + 0.5D;
                    double d1 = j >= 2 ? listnbt.getDouble(1) : (double)(blockpos.getY() + world.rand.nextInt(3) - 1);
                    double d2 = j >= 3 ? listnbt.getDouble(2) : (double)blockpos.getZ() + (world.rand.nextDouble() - world.rand.nextDouble()) * (double)this.spawnRange + 0.5D;
                    if (world.hasNoCollisions(optional.get().getBoundingBoxWithSizeApplied(d0, d1, d2))) {
                        ServerWorld serverWorld = (ServerWorld) world;
                        Entity entity = EntityType.loadEntityAndExecute(compoundnbt, serverWorld, (p_221408_6_) -> {
                            p_221408_6_.setLocationAndAngles(d0, d1, d2, p_221408_6_.rotationYaw, p_221408_6_.rotationPitch);
                            return p_221408_6_;
                        });
                        if (entity == null) {
                            this.resetTimer();
                            return;
                        }

                        int k = world.getEntitiesWithinAABB(entity.getClass(), (new AxisAlignedBB(blockpos.getX(), blockpos.getY(), blockpos.getZ(), (blockpos.getX() + 1), (blockpos.getY() + 1), (double)(blockpos.getZ() + 1))).grow((double)this.spawnRange)).size();
                        if (k >= this.maxNearbyEntities) {
                            this.resetTimer();
                            return;
                        }

                        entity.setLocationAndAngles(entity.getPosX(), entity.getPosY(), entity.getPosZ(), world.rand.nextFloat() * 360.0F, 0.0F);
                        if (entity instanceof MobEntity) {

                            if (this.spawnData.getNbt().size() == 1 && this.spawnData.getNbt().contains("id", 8)) {
                                ((MobEntity)entity).onInitialSpawn(serverWorld, world.getDifficultyForLocation(entity.getPosition()), SpawnReason.SPAWNER, null, null);
                            }
                        }

                        if (!serverWorld.func_242106_g(entity)) {
                            this.resetTimer();
                            return;
                        }

                        world.playEvent(2004, blockpos, 0);
                        if (entity instanceof MobEntity) {
                            ((MobEntity) entity).spawnExplosionParticle();
                        }

                        flag = true;

                        if ((new Random().nextDouble() <= 0.15 && this.waveCount >= 1) || this.waveCount >= 3) {
                            world.playEvent(null, 2001, getSpawnerPosition(), Block.getStateId(world.getBlockState(getSpawnerPosition())));
                            world.setBlockState(getSpawnerPosition(), Blocks.AIR.getDefaultState());
                        }
                    }
                }

                if (flag) {
                    this.waveCount++;
                    this.resetTimer();
                }
            }

        }
    }

    private void resetTimer() {
        if (this.maxSpawnDelay <= this.minSpawnDelay) {
            this.spawnDelay = this.minSpawnDelay;
        } else {
            int i = this.maxSpawnDelay - this.minSpawnDelay;
            this.spawnDelay = this.minSpawnDelay + this.getWorld().rand.nextInt(i);
        }

        if (!this.potentialSpawns.isEmpty()) {
            this.setNextSpawnData(WeightedRandom.getRandomItem(this.getWorld().rand, this.potentialSpawns));
        }

        this.broadcastEvent(1);
    }

    public void read(CompoundNBT nbt) {
        this.spawnDelay = nbt.getShort("Delay");
        this.potentialSpawns.clear();
        if (nbt.contains("SpawnPotentials", 9)) {
            ListNBT listnbt = nbt.getList("SpawnPotentials", 10);

            for(int i = 0; i < listnbt.size(); ++i) {
                this.potentialSpawns.add(new SpecialWeightedSpawnerEntity(listnbt.getCompound(i)));
            }
        }

        if (nbt.contains("SpawnData", 10)) {
            this.setNextSpawnData(new SpecialWeightedSpawnerEntity(1, nbt.getCompound("SpawnData")));
        } else if (!this.potentialSpawns.isEmpty()) {
            this.setNextSpawnData(WeightedRandom.getRandomItem(this.getWorld().rand, this.potentialSpawns));
        }

        if (nbt.contains("MinSpawnDelay", 99)) {
            this.minSpawnDelay = nbt.getShort("MinSpawnDelay");
            this.maxSpawnDelay = nbt.getShort("MaxSpawnDelay");
            this.spawnCount = nbt.getShort("SpawnCount");
        }

        if (nbt.contains("MaxNearbyEntities", 99)) {
            this.maxNearbyEntities = nbt.getShort("MaxNearbyEntities");
            this.activatingRangeFromPlayer = nbt.getShort("RequiredPlayerRange");
        }

        if (nbt.contains("SpawnRange", 99)) {
            this.spawnRange = nbt.getShort("SpawnRange");
        }

        if (this.getWorld() != null) {
            this.cachedEntity = null;
        }
        this.waveCount = nbt.getInt("WaveCount");

    }

    public CompoundNBT write(CompoundNBT compound) {
        ResourceLocation resourcelocation = this.getEntityId();
        if (resourcelocation != null) {
            compound.putShort("Delay", (short) this.spawnDelay);
            compound.putShort("MinSpawnDelay", (short) this.minSpawnDelay);
            compound.putShort("MaxSpawnDelay", (short) this.maxSpawnDelay);
            compound.putShort("SpawnCount", (short) this.spawnCount);
            compound.putShort("MaxNearbyEntities", (short) this.maxNearbyEntities);
            compound.putShort("RequiredPlayerRange", (short) this.activatingRangeFromPlayer);
            compound.putShort("SpawnRange", (short) this.spawnRange);
            compound.put("SpawnData", this.spawnData.getNbt().copy());
            compound.putInt("WaveCount", this.waveCount);
            ListNBT listnbt = new ListNBT();
            if (this.potentialSpawns.isEmpty()) {
                listnbt.add(this.spawnData.toCompoundTag());
            } else {
                for (SpecialWeightedSpawnerEntity weightedspawnerentity : this.potentialSpawns) {
                    listnbt.add(weightedspawnerentity.toCompoundTag());
                }
            }

            compound.put("SpawnPotentials", listnbt);
        }
        return compound;
    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public Entity getCachedEntity() {
        if (this.cachedEntity == null) {
            this.cachedEntity = EntityType.loadEntityAndExecute(this.spawnData.getNbt(), this.getWorld(), Function.identity());
            if (this.spawnData.getNbt().size() == 1 && this.spawnData.getNbt().contains("id", 8) && this.cachedEntity instanceof MobEntity) {
            }
        }

        return this.cachedEntity;
    }

    /**
     * Sets the delay to minDelay if parameter given is 1, else return false.
     */
    public boolean setDelayToMin(int delay) {
        if (delay == 1 && this.getWorld().isRemote) {
            this.spawnDelay = this.minSpawnDelay;
            return true;
        } else {
            return false;
        }
    }

    public void setNextSpawnData(SpecialWeightedSpawnerEntity nextSpawnData) {
        this.spawnData = nextSpawnData;
    }

    public abstract void broadcastEvent(int id);

    public abstract World getWorld();

    public abstract BlockPos getSpawnerPosition();

    @OnlyIn(Dist.CLIENT)
    public double getMobRotation() {
        return this.mobRotation;
    }

    @OnlyIn(Dist.CLIENT)
    public double getPrevMobRotation() {
        return this.prevMobRotation;
    }

    @Nullable
    public Entity getSpawnerEntity() {
        return null;
    }
}
