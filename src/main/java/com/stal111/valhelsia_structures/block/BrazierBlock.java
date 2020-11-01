package com.stal111.valhelsia_structures.block;

import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.CampfireTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.valhelsia.valhelsia_core.helper.FireExtinguishHelper;
import net.valhelsia.valhelsia_core.helper.FlintAndSteelHelper;

import javax.annotation.Nullable;
import java.util.Random;

public class BrazierBlock extends Block implements IWaterLoggable {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 5.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    protected static final VoxelShape INNER_SHAPE = Block.makeCuboidShape(2.0D, 8.0D, 2.0D, 14.0D, 14.0D, 14.0D);

    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private final boolean smokey;
    private final int fireDamage;

    public BrazierBlock(boolean smokey, int fireDamage, Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(LIT, Boolean.TRUE).with(WATERLOGGED, Boolean.FALSE));
        this.smokey = smokey;
        this.fireDamage = fireDamage;

        FireExtinguishHelper.addExtinguishFireEffect(
                state -> state.getBlock() == this && state.get(LIT),
                this.getDefaultState().with(BrazierBlock.LIT, false),
                (world, blockPos) -> world.playEvent(null, 1009, blockPos, 0));

        FlintAndSteelHelper.addUse(
                state -> state.getBlock() == this && !state.get(LIT),
                this.getDefaultState(),
                (playerEntity, world, blockPos) -> world.playSound(playerEntity, blockPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, new Random().nextFloat() * 0.4F + 0.8F),
                world -> ActionResultType.func_233537_a_(world.isRemote()));
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
        if (!entity.isImmuneToFire() && state.get(LIT) && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entity)) {
            if (entity.getPosX() >= pos.getX() + 0.1 && entity.getPosZ() >= pos.getZ() + 0.1 && entity.getPosX() <= pos.getX() + 0.9 && entity.getPosZ() <= pos.getZ() + 0.9) {
                entity.attackEntityFrom(DamageSource.IN_FIRE, this.fireDamage);
            }
        }

        super.onEntityCollision(state, worldIn, pos, entity);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IWorld iworld = context.getWorld();
        BlockPos blockpos = context.getPos();
        boolean flag = iworld.getFluidState(blockpos).getFluid() == Fluids.WATER;
        return this.getDefaultState().with(WATERLOGGED, flag).with(LIT, !flag);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }

        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.combineAndSimplify(SHAPE, INNER_SHAPE, IBooleanFunction.ONLY_FIRST);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.get(LIT)) {
            if (rand.nextInt(10) == 0) {
                worldIn.playSound((float) pos.getX() + 0.5F, (float) pos.getY() + 0.5F, (float) pos.getZ() + 0.5F, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.6F, false);
            }

            if (this.smokey && rand.nextInt(5) == 0) {
                for(int i = 0; i < rand.nextInt(1) + 1; ++i) {
                    worldIn.addParticle(ParticleTypes.LAVA, (float) pos.getX() + 0.5F, (float) pos.getY() + 0.5F, (float) pos.getZ() + 0.5F, rand.nextFloat() / 2.0F, 5.0E-5D, rand.nextFloat() / 2.0F);
                }
            }

        }
    }

    @Override
    public boolean receiveFluid(IWorld world, BlockPos pos, BlockState state, FluidState fluidState) {
        if (!state.get(BlockStateProperties.WATERLOGGED) && fluidState.getFluid() == Fluids.WATER) {
            boolean flag = state.get(LIT);
            if (flag) {
                if (!world.isRemote()) {
                    world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                TileEntity tileentity = world.getTileEntity(pos);
                if (tileentity instanceof CampfireTileEntity) {
                    ((CampfireTileEntity)tileentity).dropAllItems();
                }
            }

            world.setBlockState(pos, state.with(WATERLOGGED, Boolean.TRUE).with(LIT, Boolean.FALSE), 3);
            world.getPendingFluidTicks().scheduleTick(pos, fluidState.getFluid(), fluidState.getFluid().getTickRate(world));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onProjectileCollision(World world, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) {
        if (!world.isRemote() && projectile.isBurning()) {
            Entity entity = projectile.func_234616_v_();
            boolean flag = entity == null || entity instanceof PlayerEntity || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(world, entity);
            if (flag && !state.get(LIT) && !state.get(WATERLOGGED)) {
                BlockPos blockpos = hit.getPos();
                world.setBlockState(blockpos, state.with(BlockStateProperties.LIT, true), 11);
            }
        }

    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LIT, WATERLOGGED);
    }

    @Override
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }
}
