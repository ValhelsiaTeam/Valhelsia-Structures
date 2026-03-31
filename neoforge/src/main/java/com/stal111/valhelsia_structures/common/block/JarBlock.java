package com.stal111.valhelsia_structures.common.block;

import com.stal111.valhelsia_structures.common.block.entity.JarBlockEntity;
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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

    private static final VoxelShape SHAPE = Shapes.or(
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

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof JarBlockEntity jarBlockEntity)) {
            return InteractionResult.FAIL;
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
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        }

        return InteractionResult.SUCCESS;
    }

    private boolean canBePotted(Block block) {
        Identifier registryName = BuiltInRegistries.BLOCK.getKey(block);
        Identifier pottedName = Identifier.fromNamespaceAndPath(registryName.getNamespace(), "potted_" + registryName.getPath());

        if (!BuiltInRegistries.BLOCK.containsKey(pottedName)) {
            return false;
        }

        boolean flag = BuiltInRegistries.BLOCK.getValue(pottedName).builtInRegistryHolder().is(BlockTags.FLOWER_POTS);

        return flag && !block.asItem().builtInRegistryHolder().is(ModTags.Items.JAR_BLACKLISTED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;

        return this.defaultBlockState().setValue(WATERLOGGED, flag).setValue(ROTATED, (Mth.floor((double) ((180.0F + context.getRotation()) * 8.0F / 360.0F) + 0.5D) & 7) % 2 != 0);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
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
