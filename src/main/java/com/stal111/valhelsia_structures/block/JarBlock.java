package com.stal111.valhelsia_structures.block;

import com.stal111.valhelsia_structures.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.tileentity.JarTileEntity;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.helper.VoxelShapeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Jar Block
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.JarBlock
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-13
 */
public class JarBlock extends Block implements IWaterLoggable {

    public static final BooleanProperty TREASURE = ModBlockStateProperties.TREASURE;
    public static final BooleanProperty ROTATED = ModBlockStateProperties.ROTATED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE = VoxelShapeHelper.combineAll(
            Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 4.0D, 11.0D),
            Block.makeCuboidShape(7.0D, 4.0D, 7.0D, 9.0D, 7.0D, 9.0D),
            Block.makeCuboidShape(6.0D, 7.0D, 6.0D, 10.0D, 8.0D, 10.0D)
    );

    public JarBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(TREASURE, false).with(ROTATED, false).with(WATERLOGGED, false));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new JarTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        return SHAPE;
    }

    @Nonnull
    @Override
    public ActionResultType onBlockActivated(@Nonnull BlockState state, World world, @Nonnull BlockPos pos, PlayerEntity player, @Nonnull Hand hand, @Nonnull BlockRayTraceResult hit) {
        ItemStack stack = player.getHeldItem(hand);
        TileEntity tileEntity = world.getTileEntity(pos);

        if (!(tileEntity instanceof JarTileEntity)) {
            return ActionResultType.PASS;
        }
        JarTileEntity jarTileEntity = (JarTileEntity) tileEntity;

        if (!canBePotted((Block.getBlockFromItem(stack.getItem()))) && jarTileEntity.hasPlant()) {
            ItemStack flowerStack = jarTileEntity.getPlant();

            if (stack.isEmpty()) {
                player.setHeldItem(hand, flowerStack);
            } else if (!player.addItemStackToInventory(flowerStack)) {
                player.dropItem(flowerStack, false);
            }
            jarTileEntity.setPlant(ItemStack.EMPTY);

        } else if (canBePotted((Block.getBlockFromItem(stack.getItem()))) && !jarTileEntity.hasPlant()) {
            jarTileEntity.setPlant(stack.copy().split(1));

            player.addStat(Stats.POT_FLOWER);

            if (!player.abilities.isCreativeMode) {
                stack.shrink(1);
            }
        } else {
            return ActionResultType.PASS;
        }

        return ActionResultType.func_233537_a_(world.isRemote);
    }

    private boolean canBePotted(Block block) {
        ResourceLocation registryName = block.getRegistryName();
        if (registryName == null) {
            return false;
        }
        boolean flag = BlockTags.FLOWER_POTS.getAllElements().contains(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block.getRegistryName().getNamespace(), "potted_" + block.getRegistryName().getPath())));

        return flag && !ModTags.Items.JAR_BLACKLISTED.contains(block.asItem());
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        boolean flag = fluidstate.getFluid() == Fluids.WATER;
        return this.getDefaultState().with(WATERLOGGED, flag).with(ROTATED, (MathHelper.floor((double) ((180.0F + context.getPlacementYaw()) * 8.0F / 360.0F) + 0.5D) & 7) % 2 != 0);
    }

    @Override
    public void onReplaced(BlockState state, @Nonnull World world, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.isIn(newState.getBlock())) {
            TileEntity tileentity = world.getTileEntity(pos);
            if (tileentity instanceof JarTileEntity) {
                JarTileEntity jarTileEntity = (JarTileEntity) tileentity;
                if (jarTileEntity.hasPlant()) {
                    InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), jarTileEntity.getPlant());
                }
            }
        }
        super.onReplaced(state, world, pos, newState, isMoving);
    }

    @Nonnull
    @Override
    public BlockState updatePostPlacement(BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull IWorld world, @Nonnull BlockPos currentPos, @Nonnull BlockPos facingPos) {
        if (state.get(WATERLOGGED)) {
            world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(TREASURE, ROTATED, WATERLOGGED);
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
}
