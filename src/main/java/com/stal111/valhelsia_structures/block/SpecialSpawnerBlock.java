package com.stal111.valhelsia_structures.block;

import com.stal111.valhelsia_structures.tileentity.SpecialMobSpawnerTileEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.block.ValhelsiaContainerBlock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Special Spawner Block
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.SpecialSpawnerBlock
 *
 * @author Valhelsia Team
 * @version 16.1.0
 */
public class SpecialSpawnerBlock extends ValhelsiaContainerBlock {

    public SpecialSpawnerBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SpecialMobSpawnerTileEntity();
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return 10 + RANDOM.nextInt(15) + RANDOM.nextInt(15);
    }

    @Nonnull
    @Override
    public ActionResultType onBlockActivated(@Nonnull BlockState state, World world, @Nonnull BlockPos pos, PlayerEntity player, @Nonnull Hand hand, @Nonnull BlockRayTraceResult result) {
        ItemStack stack = player.getHeldItemMainhand();
        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity == null || !(stack.getItem() instanceof SpawnEggItem)) {
            return ActionResultType.PASS;
        }
        SpecialAbstractSpawner spawner = ((SpecialMobSpawnerTileEntity) tileEntity).getSpawnerBaseLogic();

        spawner.setEntityType(((SpawnEggItem) stack.getItem()).getType(stack.getTag()));
        tileEntity.markDirty();
        world.notifyBlockUpdate(pos, state, state, 3);

        if (!player.abilities.isCreativeMode) {
            stack.shrink(1);
        }

        return ActionResultType.func_233537_a_(world.isRemote());
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return ItemStack.EMPTY;
    }
}