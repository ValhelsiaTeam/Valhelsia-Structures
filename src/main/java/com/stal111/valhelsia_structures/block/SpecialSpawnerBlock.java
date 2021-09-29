package com.stal111.valhelsia_structures.block;

import com.stal111.valhelsia_structures.tileentity.SpecialMobSpawnerTileEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.valhelsia.valhelsia_core.block.ValhelsiaContainerBlock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

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
    public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
        return new SpecialMobSpawnerTileEntity();
    }

    @Override
    public int getExpDrop(BlockState state, LevelReader world, BlockPos pos, int fortune, int silktouch) {
        return 10 + RANDOM.nextInt(15) + RANDOM.nextInt(15);
    }

    @Nonnull
    @Override
    public InteractionResult onBlockActivated(@Nonnull BlockState state, Level world, @Nonnull BlockPos pos, Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult result) {
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