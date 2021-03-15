package com.stal111.valhelsia_structures.block;

import com.stal111.valhelsia_structures.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.config.BlockConfig;
import com.stal111.valhelsia_structures.tileentity.JarTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
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
import net.minecraft.tileentity.CampfireTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.valhelsia.valhelsia_core.helper.VoxelShapeHelper;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Jar Block
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.JarBlock
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-13
 */
public class JarBlock extends Block implements IWaterLoggable {

    private static final VoxelShape SHAPE = VoxelShapeHelper.combineAll(
            Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 4.0D, 11.0D),
            Block.makeCuboidShape(7.0D, 4.0D, 7.0D, 9.0D, 7.0D, 9.0D),
            Block.makeCuboidShape(6.0D, 7.0D, 6.0D, 10.0D, 8.0D, 10.0D)
    );

    public static final BooleanProperty TREASURE = ModBlockStateProperties.TREASURE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public JarBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(TREASURE, Boolean.FALSE).with(WATERLOGGED, Boolean.FALSE));
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

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        ItemStack stack = player.getHeldItem(hand);
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof JarTileEntity) {
            JarTileEntity jarTileEntity = (JarTileEntity) tileEntity;
            if (!canBePotted((Block.getBlockFromItem(stack.getItem()))) && jarTileEntity.hasPlant()) {
                ItemStack flowerStack = jarTileEntity.getPlant();

                if (!world.isRemote) {
                    if (stack.isEmpty()) {
                        player.setHeldItem(hand, flowerStack);
                    } else if (!player.addItemStackToInventory(flowerStack)) {
                        player.dropItem(flowerStack, false);
                    }
                }

                jarTileEntity.setPlant(ItemStack.EMPTY);

                return ActionResultType.func_233537_a_(world.isRemote);
            } else if (canBePotted((Block.getBlockFromItem(stack.getItem()))) && !jarTileEntity.hasPlant()) {
                jarTileEntity.setPlant(stack.copy().split(1));

                player.addStat(Stats.POT_FLOWER);

                if (!player.abilities.isCreativeMode) {
                    stack.shrink(1);
                }

                return ActionResultType.func_233537_a_(world.isRemote);
            }
        }

        return ActionResultType.CONSUME;
    }

    private boolean canBePotted(Block block) {
        for (Block flowerPot : BlockTags.FLOWER_POTS.getAllElements()) {
            if (Objects.requireNonNull(flowerPot.getRegistryName()).getPath().startsWith("potted_")) {
                String name = flowerPot.getRegistryName().getPath().split("_", 2)[1];
                if (Objects.requireNonNull(block.getRegistryName()).getPath().equals(name)) {
                    if (!BlockConfig.JAR_BLACKLIST.get().contains(flowerPot.getRegistryName().getNamespace() + ":" + name)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        boolean flag = fluidstate.getFluid() == Fluids.WATER;
        return this.getDefaultState().with(WATERLOGGED, flag);
    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
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

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        if (state.get(WATERLOGGED)) {
            world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(TREASURE, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
}
