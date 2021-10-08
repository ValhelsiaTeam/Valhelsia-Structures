package com.stal111.valhelsia_structures.common.block;

 import com.google.common.collect.Lists;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.ResourceLocationException;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.StringUtil;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

/**
 * Special Base Spawner <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.SpecialBaseSpawner
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 */
public abstract class SpecialBaseSpawner {

    private int spawnDelay = 20;
    private WeightedRandomList<SpawnData> spawnPotentials = WeightedRandomList.create();
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

    @Nullable
    private ResourceLocation getEntityId(@Nullable Level level, BlockPos pos) {
        String s = this.nextSpawnData.getTag().getString("id");

        try {
            return StringUtil.isNullOrEmpty(s) ? null : new ResourceLocation(s);
        } catch (ResourceLocationException resourcelocationexception) {
            ValhelsiaStructures.LOGGER.warn("Invalid entity id '{}' at spawner {}:[{},{},{}]", s, level != null ? level.dimension().location() : "<null>", pos.getX(), pos.getY(), pos.getZ());
            return null;
        }
    }

    public void setEntityId(EntityType<?> type) {
        this.nextSpawnData.getTag().putString("id", Objects.requireNonNull(ForgeRegistries.ENTITIES.getKey(type)).toString());
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
                    CompoundTag compoundtag = this.nextSpawnData.getTag();
                    Optional<EntityType<?>> optional = EntityType.by(compoundtag);
                    if (!optional.isPresent()) {
                        this.delay(serverLevel, pos);
                        return;
                    }

                    ListTag listtag = compoundtag.getList("Pos", 6);
                    int j = listtag.size();
                    double d0 = j >= 1 ? listtag.getDouble(0) : (double)pos.getX() + (serverLevel.random.nextDouble() - serverLevel.random.nextDouble()) * (double)this.spawnRange + 0.5D;
                    double d1 = j >= 2 ? listtag.getDouble(1) : (double)(pos.getY() + serverLevel.random.nextInt(3) - 1);
                    double d2 = j >= 3 ? listtag.getDouble(2) : (double)pos.getZ() + (serverLevel.random.nextDouble() - serverLevel.random.nextDouble()) * (double)this.spawnRange + 0.5D;
                    if (serverLevel.noCollision(optional.get().getAABB(d0, d1, d2))) {
                        Entity entity = EntityType.loadEntityRecursive(compoundtag, serverLevel, (p_151310_) -> {
                            p_151310_.moveTo(d0, d1, d2, p_151310_.getYRot(), p_151310_.getXRot());
                            return p_151310_;
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
                        if (entity instanceof Mob) {
                            if (this.nextSpawnData.getTag().size() == 1 && this.nextSpawnData.getTag().contains("id", 8)) {
                                ((Mob)entity).finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.SPAWNER, null, null);
                            }
                        }

                        if (!serverLevel.tryAddFreshEntityWithPassengers(entity)) {
                            this.delay(serverLevel, pos);
                            return;
                        }

                        serverLevel.levelEvent(2004, pos, 0);
                        if (entity instanceof Mob) {
                            ((Mob) entity).spawnAnim();
                        }

                        flag = true;

                        System.out.println(0.15D * this.waveCount + "  " +  this.waveCount);
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

        this.spawnPotentials.getRandom(level.getRandom()).ifPresent((spawnData) -> {
            this.setNextSpawnData(level, pos, spawnData);
        });
        this.broadcastEvent(level, pos, 1);
    }

    public void load(@Nullable Level level, BlockPos pos, CompoundTag tag) {
        this.spawnDelay = tag.getShort("Delay");
        List<SpawnData> list = Lists.newArrayList();
        if (tag.contains("SpawnPotentials", 9)) {
            ListTag listTag = tag.getList("SpawnPotentials", 10);

            for(int i = 0; i < listTag.size(); ++i) {
                list.add(new SpawnData(listTag.getCompound(i)));
            }
        }

        this.spawnPotentials = WeightedRandomList.create(list);
        if (tag.contains("SpawnData", 10)) {
            this.setNextSpawnData(level, pos, new SpawnData(1, tag.getCompound("SpawnData")));
        } else if (!list.isEmpty()) {
            this.spawnPotentials.getRandom(level != null ? level.getRandom() : new Random()).ifPresent((p_151338_) -> {
                this.setNextSpawnData(level, pos, p_151338_);
            });
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
        if (this.getEntityId(level, pos) != null) {
            tag.putShort("Delay", (short) this.spawnDelay);
            tag.putShort("MinSpawnDelay", (short) this.minSpawnDelay);
            tag.putShort("MaxSpawnDelay", (short) this.maxSpawnDelay);
            tag.putShort("SpawnCount", (short) this.spawnCount);
            tag.putShort("MaxNearbyEntities", (short) this.maxNearbyEntities);
            tag.putShort("RequiredPlayerRange", (short) this.requiredPlayerRange);
            tag.putShort("SpawnRange", (short) this.spawnRange);
            tag.put("SpawnData", this.nextSpawnData.getTag().copy());
            tag.putShort("WaveCount", this.waveCount);

            ListTag listTag = new ListTag();
            if (this.spawnPotentials.isEmpty()) {
                listTag.add(this.nextSpawnData.save());
            } else {
                for (SpawnData spawndata : this.spawnPotentials.unwrap()) {
                    listTag.add(spawndata.save());
                }
            }

            tag.put("SpawnPotentials", listTag);
        }
        return tag;
    }

    @Nullable
    public Entity getOrCreateDisplayEntity(Level level) {
        if (this.displayEntity == null) {
            this.displayEntity = EntityType.loadEntityRecursive(this.nextSpawnData.getTag(), level, Function.identity());
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