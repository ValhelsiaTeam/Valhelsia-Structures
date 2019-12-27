package com.stal111.valhelsia_structures.event;

import com.stal111.valhelsia_structures.block.SpecialAbstractSpawner;
import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.tileentity.SpecialMobSpawnerTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerInteractBlockListener {

    @SubscribeEvent
    public static void onPlayerInteractBlock(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        ItemStack stack = event.getItemStack();
        BlockState state = world.getBlockState(pos);
        if (!world.isRemote()) {
            if (state.getBlock() == ModBlocks.SPECIAL_SPAWNER.getBlock()) {
                if (stack.getItem() instanceof SpawnEggItem) {
                    TileEntity tileentity = world.getTileEntity(pos);
                    if (tileentity instanceof SpecialMobSpawnerTileEntity) {
                        SpecialAbstractSpawner abstractspawner = ((SpecialMobSpawnerTileEntity)tileentity).getSpawnerBaseLogic();
                        EntityType<?> entity = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
                        abstractspawner.setEntityType(entity);
                        tileentity.markDirty();
                        world.notifyBlockUpdate(pos, state, state, 3);
                        stack.shrink(1);
                        event.getPlayer().swingArm(event.getHand());
                    }
                }
            }
        }
    }
}
