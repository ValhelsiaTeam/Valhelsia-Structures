package com.stal111.valhelsia_structures.common.block;

import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.common.block.entity.JarBlockEntity;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.common.helper.VoxelShapeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Jar Block <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.JarBlock
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.1.0
 * @since 2020-11-13
 */
public class JarBlock extends Block implements SimpleWaterloggedBlock, EntityBlock {

    public static final BooleanProperty TREASURE = ModBlockStateProperties.TREASURE;
    public static final BooleanProperty ROTATED = ModBlockStateProperties.ROTATED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE = VoxelShapeHelper.combineAll(
            Block.box(5.0D, 0.0D, 5.0D, 11.0D, 4.0D, 11.0D),
            Block.box(7.0D, 4.0D, 7.0D, 9.0D, 7.0D, 9.0D),
            Block.box(6.0D, 7.0D, 6.0D, 10.0D, 8.0D, 10.0D)
    );

    public JarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(TREASURE, false).setValue(ROTATED, false).setValue(WATERLOGGED, false));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new JarBlockEntity(pos, state);
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE;
    }

    @Nonnull
    @Override
    public InteractionResult use(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);

        if (!(level.getBlockEntity(pos) instanceof JarBlockEntity jarBlockEntity)) {
            return InteractionResult.PASS;
        }

        boolean canBePotted = this.canBePotted(Block.byItem(stack.getItem()));

        if (!canBePotted && jarBlockEntity.hasPlant()) {
            ItemStack flowerStack = jarBlockEntity.getPlant();

            if (stack.isEmpty()) {
                player.setItemInHand(hand, flowerStack);
            } else if (!player.addItem(flowerStack)) {
                player.drop(flowerStack, false);
            }
            jarBlockEntity.setPlant(ItemStack.EMPTY);

        } else if (canBePotted && !jarBlockEntity.hasPlant()) {
            jarBlockEntity.setPlant(stack.copy().split(1));

            player.awardStat(Stats.POT_FLOWER);

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        } else {
            return InteractionResult.PASS;
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    private boolean canBePotted(Block block) {
        ResourceLocation registryName = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block));
        ResourceLocation pottedName = new ResourceLocation(registryName.getNamespace(), "potted_" + registryName.getPath());

        if (!ForgeRegistries.BLOCKS.containsKey(pottedName)) {
            return false;
        }

        boolean flag = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(pottedName)).builtInRegistryHolder().is(BlockTags.FLOWER_POTS);

        return flag && !block.asItem().builtInRegistryHolder().is(ModTags.Items.JAR_BLACKLISTED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;

        return this.defaultBlockState().setValue(WATERLOGGED, flag).setValue(ROTATED, (Mth.floor((double) ((180.0F + context.getRotation()) * 8.0F / 360.0F) + 0.5D) & 7) % 2 != 0);
    }

    private void dropPlant(Level level, BlockPos pos) {
        if (level.isClientSide()) {
            return;
        }
        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof JarBlockEntity jarBlockEntity && jarBlockEntity.hasPlant()) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), jarBlockEntity.getPlant());
        }
    }

    @Override
    public void onRemove(BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            this.dropPlant(level, pos);
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Nonnull
    @Override
    public BlockState updateShape(@Nonnull BlockState state, @Nonnull Direction direction, @Nonnull BlockState neighborState, @Nonnull LevelAccessor level, @Nonnull BlockPos currentPos, @Nonnull BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TREASURE, ROTATED, WATERLOGGED);
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
