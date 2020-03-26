package com.stal111.valhelsia_structures.block;

import com.stal111.valhelsia_structures.tileentity.SpecialMobSpawnerTileEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Special Spawner Block
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.SpecialSpawnerBlock
 *
 * @author Valhelsia Team
 * @version 14.0.3
 * @since 2019-10-31
 */
public class SpecialSpawnerBlock extends ContainerBlock {

    public SpecialSpawnerBlock(Properties properties) {
        super(properties);
    }

    public TileEntity createNewTileEntity(@Nonnull IBlockReader world) {
        return new SpecialMobSpawnerTileEntity();
    }

    @SuppressWarnings("deprecation")
    @Override
    @Deprecated
    public void spawnAdditionalDrops(BlockState state, World worldIn, BlockPos pos, ItemStack stack) {
        super.spawnAdditionalDrops(state, worldIn, pos, stack);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return 10 + RANDOM.nextInt(15) + RANDOM.nextInt(15);
    }

    @Override
    public @Nonnull BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nonnull BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @SuppressWarnings("deprecation")
    @Override
    @Deprecated
    public @Nonnull ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }
}
