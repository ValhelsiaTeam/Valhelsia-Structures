package com.stal111.valhelsia_structures.block;

import com.stal111.valhelsia_structures.tileentity.SpecialMobSpawnerTileEntity;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.AbstractSpawner;

public class SpecialSpawnerBlock extends ContainerBlock {

    public SpecialSpawnerBlock(Properties properties) {
        super(properties);
    }

    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new SpecialMobSpawnerTileEntity();
    }

    public void spawnAdditionalDrops(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.spawnAdditionalDrops(state, world, pos, stack);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return 10 + RANDOM.nextInt(15) + RANDOM.nextInt(15);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack stack = player.getHeldItemMainhand();
        TileEntity tileentity = world.getTileEntity(pos);
        if (stack.getItem() instanceof SpawnEggItem) {
            if (tileentity instanceof SpecialMobSpawnerTileEntity) {
                SpecialAbstractSpawner abstractspawner = ((SpecialMobSpawnerTileEntity)tileentity).getSpawnerBaseLogic();
                EntityType<?> entitytype1 = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
                abstractspawner.setEntityType(entitytype1);
                tileentity.markDirty();
                world.notifyBlockUpdate(pos, state, state, 3);
                stack.shrink(1);
                return ActionResultType.SUCCESS;
            }
        }
        return super.onBlockActivated(state, world, pos, player, hand, result);
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }
}
