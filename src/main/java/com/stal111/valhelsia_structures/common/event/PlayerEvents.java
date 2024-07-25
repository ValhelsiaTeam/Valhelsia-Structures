package com.stal111.valhelsia_structures.common.event;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerSetSpawnEvent;

/**
 * Player Events <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.event.PlayerEvents
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.2.0
 * @since 2022-04-03
 */
@EventBusSubscriber(modid = ValhelsiaStructures.MOD_ID)
public class PlayerEvents {

    @SubscribeEvent
    public static void onPlayerSetSpawn(PlayerSetSpawnEvent event) {
        BlockPos pos = event.getNewSpawn();

        if (pos == null) {
            return;
        }

        BlockState state = event.getEntity().level().getBlockState(pos);

        if (state.is(ModTags.Blocks.SLEEPING_BAGS) || state.is(ModBlocks.EXPLORERS_TENT.get())) {
            event.setCanceled(true);
        }
    }
}
