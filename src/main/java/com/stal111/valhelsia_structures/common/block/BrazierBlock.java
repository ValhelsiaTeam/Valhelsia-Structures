package com.stal111.valhelsia_structures.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

/**
 * Brazier Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.BrazierBlock
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 */
public class BrazierBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape INNER_SHAPE = Block.box(2.0D, 8.0D, 2.0D, 14.0D, 14.0D, 14.0D);
    protected static final VoxelShape SHAPE = Shapes.join(Block.box(1.0D, 5.0D, 1.0D, 15.0D, 14.0D, 15.0D), INNER_SHAPE, BooleanOp.ONLY_FIRST);

    private final boolean spawnParticles;
    private final int fireDamage;

    public BrazierBlock(boolean spawnParticles, int fireDamage, Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(LIT, true).setValue(WATERLOGGED, false));
        this.spawnParticles = spawnParticles;
        this.fireDamage = fireDamage;
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void entityInside(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, @Nonnull Entity entity) {
        super.entityInside(state, level, pos, entity);

        if (state.getValue(LIT)) {
            if (entity.fireImmune() || !(entity instanceof LivingEntity) || EnchantmentHelper.hasFrostWalker((LivingEntity) entity)) {
                return;
            }

            if (entity.getX() >= pos.getX() + 0.1D && entity.getZ() >= pos.getZ() + 0.1D && entity.getX() <= pos.getX() + 0.9D && entity.getZ() <= pos.getZ() + 0.9D) {
                if (entity.getY() >= pos.getY() + 0.5D) {
                    entity.hurt(DamageSource.IN_FIRE, this.fireDamage);
                }
            }
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;

        return this.defaultBlockState().setValue(WATERLOGGED, flag).setValue(LIT, !flag);
    }

    @Nonnull
    @Override
    public BlockState updateShape(@Nonnull BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull LevelAccessor level, @Nonnull BlockPos currentPos, @Nonnull BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public void animateTick(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, @Nonnull Random rand) {
        if (state.getValue(LIT)) {
            if (rand.nextInt(10) == 0) {
                level.playLocalSound(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.6F, false);
            }

            if (this.spawnParticles && rand.nextInt(5) == 0) {
                for (int i = 0; i < rand.nextInt(1) + 1; i++) {
                    level.addParticle(ParticleTypes.LAVA, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, rand.nextFloat() / 2.0F, 5.0E-5D, rand.nextFloat() / 2.0F);
                }
            }
        }
    }

    @Override
    public boolean placeLiquid(@Nonnull LevelAccessor level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull FluidState fluidState) {
        if (!state.getValue(BlockStateProperties.WATERLOGGED) && fluidState.getType() == Fluids.WATER) {
            if (state.getValue(LIT) && !level.isClientSide()) {
                level.playSound(null, pos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }

            level.setBlock(pos, state.setValue(WATERLOGGED, true).setValue(LIT, false), 3);
            level.scheduleTick(pos, fluidState.getType(), fluidState.getType().getTickDelay(level));

            return true;
        }
        return false;
    }

    @Override
    public void onProjectileHit(@Nonnull Level level, @Nonnull BlockState state, @Nonnull BlockHitResult hit, @Nonnull Projectile projectile) {
        BlockPos pos = hit.getBlockPos();
        if (!level.isClientSide && projectile.isOnFire() && projectile.mayInteract(level, pos) && !state.getValue(LIT) && !state.getValue(WATERLOGGED)) {
            level.setBlock(pos, state.setValue(BlockStateProperties.LIT, true), 11);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, WATERLOGGED);
    }

    @Override
    public boolean isPathfindable(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull PathComputationType type) {
        return false;
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
