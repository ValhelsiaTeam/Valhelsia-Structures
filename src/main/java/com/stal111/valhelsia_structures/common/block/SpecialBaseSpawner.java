package com.stal111.valhelsia_structures.common.block;

//import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
//import net.minecraft.ResourceLocationException;
//import net.minecraft.block.Block;
//import net.minecraft.block.Blocks;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.particles.ParticleTypes;
//import net.minecraft.util.random.WeightedRandomList;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.MobEntity;
//import net.minecraft.entity.SpawnReason;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.nbt.CompoundNBT;
//import net.minecraft.nbt.ListNBT;
//import net.minecraft.particles.ParticleTypes;
//import net.minecraft.util.*;
//import net.minecraft.util.math.AxisAlignedBB;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.server.ServerWorld;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.registries.ForgeRegistries;
//
//import javax.annotation.Nullable;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.function.Function;
//
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.SpawnData;
//
///**
// * Special Base Spawner <br>
// * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.SpecialBaseSpawner
// *
// * @author Valhelsia Team
// * @version 1.17.1-0.1.0
// */
//public abstract class SpecialBaseSpawner {
//
//    private WeightedRandomList<SpawnData> spawnPotentials = WeightedRandomList.create();
//    private int spawnDelay = 20;
//    private SpawnData nextSpawnData = new SpawnData();
//    private double spin;
//    private double oSpin;
//    private int minSpawnDelay = 80;
//    private int maxSpawnDelay = 100;
//    private int spawnCount = 5;
//    @Nullable
//    private Entity displayEntity;
//    private int maxNearbyEntities = 7;
//    private int requiredPlayerRange = 8;
//    private int spawnRange = 4;
//    private int waveCount = 0;
//
//    @Nullable
//    private ResourceLocation getEntityId(@Nullable Level level, BlockPos pos) {
//        String s = this.nextSpawnData.getTag().getString("id");
//
//        try {
//            return StringUtil.isNullOrEmpty(s) ? null : new ResourceLocation(s);
//        } catch (ResourceLocationException resourcelocationexception) {
//            ValhelsiaStructures.LOGGER.warn("Invalid entity id '{}' at spawner {}:[{},{},{}]", s, level != null ? level.dimension().location() : "<null>", pos.getX(), pos.getY(), pos.getZ());
//            return null;
//        }
//    }
//
//    public void setEntityId(EntityType<?> type) {
//        this.nextSpawnData.getTag().putString("id", Objects.requireNonNull(ForgeRegistries.ENTITIES.getKey(type)).toString());
//    }
//
//    private boolean isNearPlayer(Level level, BlockPos pos) {
//        return level.hasNearbyAlivePlayer(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, this.requiredPlayerRange);
//    }
//
//    public void clientTick(Level level, BlockPos pos) {
//        if (!this.isNearPlayer(level, pos)) {
//            this.oSpin = this.spin;
//        } else {
//            double d0 = pos.getX() + level.random.nextDouble();
//            double d1 = pos.getY() + level.random.nextDouble();
//            double d2 = pos.getZ() + level.random.nextDouble();
//            level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
//            level.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
//            if (this.spawnDelay > 0) {
//                --this.spawnDelay;
//            }
//
//            this.oSpin = this.spin;
//            this.spin = (this.spin + (1000.0F / (this.spawnDelay + 200.0F))) % 360.0D;
//        }
//    }
//
//    public void tick() {
//        if (!this.isActivated()) {
//            this.prevMobRotation = this.mobRotation;
//        } else {
//            World world = this.getWorld();
//            BlockPos pos = this.getSpawnerPosition();
//            if (world.isRemote()) {
//                double x = ((float) pos.getX() + world.rand.nextFloat());
//                double y = ((float) pos.getY() + world.rand.nextFloat());
//                double z = ((float) pos.getZ() + world.rand.nextFloat());
//                world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
//                world.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
//
//                if (this.spawnDelay > 0) {
//                    --this.spawnDelay;
//                }
//
//                this.prevMobRotation = this.mobRotation;
//                this.mobRotation = (this.mobRotation + (double) (1000.0F / ((float) this.spawnDelay + 200.0F))) % 360.0D;
//            } else {
//                if (this.spawnDelay == -1) {
//                    this.resetTimer();
//                }
//
//                if (this.spawnDelay > 0) {
//                    --this.spawnDelay;
//                    return;
//                }
//
//                boolean flag = false;
//
//                for (int i = 0; i < this.spawnCount; ++i) {
//                    CompoundNBT compoundnbt = this.spawnData.getNbt();
//                    Optional<EntityType<?>> optional = EntityType.readEntityType(compoundnbt);
//                    if (!optional.isPresent()) {
//                        this.resetTimer();
//                        return;
//                    }
//
//                    ListNBT listnbt = compoundnbt.getList("Pos", 6);
//                    int j = listnbt.size();
//                    double d0 = j >= 1 ? listnbt.getDouble(0) : pos.getX() + (world.rand.nextDouble() - world.rand.nextDouble()) * (double) this.spawnRange + 0.5D;
//                    double d1 = j >= 2 ? listnbt.getDouble(1) : (double) (pos.getY() + world.rand.nextInt(3) - 1);
//                    double d2 = j >= 3 ? listnbt.getDouble(2) : pos.getZ() + (world.rand.nextDouble() - world.rand.nextDouble()) * (double) this.spawnRange + 0.5D;
//                    if (world.hasNoCollisions(optional.get().getBoundingBoxWithSizeApplied(d0, d1, d2))) {
//                        ServerWorld serverWorld = (ServerWorld) world;
//                        Entity entity = EntityType.loadEntityAndExecute(compoundnbt, serverWorld, (p_221408_6_) -> {
//                            p_221408_6_.setLocationAndAngles(d0, d1, d2, p_221408_6_.rotationYaw, p_221408_6_.rotationPitch);
//                            return p_221408_6_;
//                        });
//                        if (entity == null) {
//                            this.resetTimer();
//                            return;
//                        }
//
//                        int k = world.getEntitiesWithinAABB(entity.getClass(), (new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), (pos.getX() + 1), (pos.getY() + 1), pos.getZ() + 1)).grow(this.spawnRange)).size();
//                        if (k >= this.maxNearbyEntities) {
//                            this.resetTimer();
//                            return;
//                        }
//
//                        entity.setLocationAndAngles(entity.getPosX(), entity.getPosY(), entity.getPosZ(), world.rand.nextFloat() * 360.0F, 0.0F);
//
//                        if (entity instanceof MobEntity) {
//                            if (this.spawnData.getNbt().size() == 1 && this.spawnData.getNbt().contains("id", 8)) {
//                                ((MobEntity) entity).onInitialSpawn(serverWorld, world.getDifficultyForLocation(entity.getPosition()), SpawnReason.SPAWNER, null, null);
//                            }
//                        }
//
//                        if (!serverWorld.func_242106_g(entity)) {
//                            this.resetTimer();
//                            return;
//                        }
//
//                        world.playEvent(2004, pos, 0);
//                        if (entity instanceof MobEntity) {
//                            ((MobEntity) entity).spawnExplosionParticle();
//                        }
//
//                        flag = true;
//
//                        if ((world.getRandom().nextDouble() <= 0.15 && this.waveCount >= 1) || this.waveCount >= 3) {
//                            world.playEvent(null, 2001, getSpawnerPosition(), Block.getStateId(world.getBlockState(getSpawnerPosition())));
//                            world.setBlockState(getSpawnerPosition(), Blocks.AIR.getDefaultState());
//                        }
//                    }
//                }
//
//                if (flag) {
//                    this.waveCount++;
//                    this.resetTimer();
//                }
//            }
//        }
//    }
//
//    private void resetTimer() {
//        if (this.maxSpawnDelay <= this.minSpawnDelay) {
//            this.spawnDelay = this.minSpawnDelay;
//        } else {
//            int i = this.maxSpawnDelay - this.minSpawnDelay;
//            this.spawnDelay = this.minSpawnDelay + this.getWorld().getRandom().nextInt(i);
//        }
//
//        if (!this.potentialSpawns.isEmpty()) {
//            this.setNextSpawnData(WeightedRandom.getRandomItem(this.getWorld().getRandom(), this.potentialSpawns));
//        }
//
//        this.broadcastEvent(1);
//    }
//
//    public void read(CompoundNBT nbt) {
//        this.spawnDelay = nbt.getShort("Delay");
//        this.potentialSpawns.clear();
//        if (nbt.contains("SpawnPotentials", 9)) {
//            ListNBT listNBT = nbt.getList("SpawnPotentials", 10);
//
//            for (int i = 0; i < listNBT.size(); ++i) {
//                this.potentialSpawns.add(new WeightedSpawnerEntity(listNBT.getCompound(i)));
//            }
//        }
//
//        if (nbt.contains("SpawnData", 10)) {
//            this.setNextSpawnData(new WeightedSpawnerEntity(1, nbt.getCompound("SpawnData")));
//        } else if (!this.potentialSpawns.isEmpty()) {
//            this.setNextSpawnData(WeightedRandom.getRandomItem(this.getWorld().getRandom(), this.potentialSpawns));
//        }
//
//        if (nbt.contains("MinSpawnDelay", 99)) {
//            this.minSpawnDelay = nbt.getShort("MinSpawnDelay");
//            this.maxSpawnDelay = nbt.getShort("MaxSpawnDelay");
//            this.spawnCount = nbt.getShort("SpawnCount");
//        }
//
//        if (nbt.contains("MaxNearbyEntities", 99)) {
//            this.maxNearbyEntities = nbt.getShort("MaxNearbyEntities");
//            this.activatingRangeFromPlayer = nbt.getShort("RequiredPlayerRange");
//        }
//
//        if (nbt.contains("SpawnRange", 99)) {
//            this.spawnRange = nbt.getShort("SpawnRange");
//        }
//
//        if (this.getWorld() != null) {
//            this.cachedEntity = null;
//        }
//        this.waveCount = nbt.getInt("WaveCount");
//    }
//
//    public CompoundNBT write(CompoundNBT compound) {
//        ResourceLocation resourceLocation = this.getEntityId();
//
//        if (resourceLocation != null) {
//            compound.putShort("Delay", (short) this.spawnDelay);
//            compound.putShort("MinSpawnDelay", (short) this.minSpawnDelay);
//            compound.putShort("MaxSpawnDelay", (short) this.maxSpawnDelay);
//            compound.putShort("SpawnCount", (short) this.spawnCount);
//            compound.putShort("MaxNearbyEntities", (short) this.maxNearbyEntities);
//            compound.putShort("RequiredPlayerRange", (short) this.activatingRangeFromPlayer);
//            compound.putShort("SpawnRange", (short) this.spawnRange);
//            compound.put("SpawnData", this.spawnData.getNbt().copy());
//            compound.putInt("WaveCount", this.waveCount);
//            ListNBT listNBT = new ListNBT();
//
//            if (this.potentialSpawns.isEmpty()) {
//                listNBT.add(this.spawnData.toCompoundTag());
//            } else {
//                for (WeightedSpawnerEntity weightedSpawnerEntity : this.potentialSpawns) {
//                    listNBT.add(weightedSpawnerEntity.toCompoundTag());
//                }
//            }
//
//            compound.put("SpawnPotentials", listNBT);
//        }
//        return compound;
//    }
//
//    @Nullable
//    @OnlyIn(Dist.CLIENT)
//    public Entity getCachedEntity() {
//        if (this.cachedEntity == null) {
//            this.cachedEntity = EntityType.loadEntityAndExecute(this.spawnData.getNbt(), this.getWorld(), Function.identity());
//        }
//
//        return this.cachedEntity;
//    }
//
//    /**
//     * Sets the delay to minDelay if parameter given is 1, else return false.
//     */
//    public boolean setDelayToMin(int delay) {
//        if (delay == 1 && this.getWorld().isRemote()) {
//            this.spawnDelay = this.minSpawnDelay;
//            return true;
//        }
//        return false;
//    }
//
//    public void setNextSpawnData(WeightedSpawnerEntity nextSpawnData) {
//        this.spawnData = nextSpawnData;
//    }
//
//    public abstract void broadcastEvent(int id);
//
//    public abstract World getWorld();
//
//    public abstract BlockPos getSpawnerPosition();
//
//    @OnlyIn(Dist.CLIENT)
//    public double getMobRotation() {
//        return this.mobRotation;
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    public double getPrevMobRotation() {
//        return this.prevMobRotation;
//    }
//
//    @Nullable
//    public Entity getSpawnerEntity() {
//        return null;
//    }
//}