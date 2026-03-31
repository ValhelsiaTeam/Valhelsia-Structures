package com.stal111.valhelsia_structures.common.event;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.illager.Pillager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

/**
 * Entity Event Listener <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.event.EntityEventListener
 * <p>
 * For all entity-related event handling.
 * </p>
 * @author Valhelsia Team
 * @since 2021-05-11
 */
@EventBusSubscriber(modid = ValhelsiaStructures.MOD_ID)
public class EntityEventListener {

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Pillager entity && !event.getLevel().isClientSide()) {
            entity.goalSelector.addGoal(5, new MeleeAttackGoal(entity, 1.0D, true));
        }
    }

    //TODO: Add villager trades
//    @SubscribeEvent
//    public static void onVillagerTrade(VillagerTradesEvent event) {
//        TradeHelper.addVillagerTrade(event,
//                VillagerProfession.CARTOGRAPHER,
//                1,
//                (level, trader, rand) -> {
//                    ItemStack stack = ItemStackUtils.getFilledMap(trader.level(), trader.blockPosition(), ModTags.Structures.ON_SPAWNER_DUNGEON_EXPLORER_MAPS, MapDecorationTypes.RED_X, "filled_map.valhelsia_structures.spawner_dungeon");
//                    if (stack == null) {
//                        return null;
//                    }
//                    return new BasicItemListing(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.COMPASS), stack, 12, 5, 1).getOffer(level, trader, rand);
//                }
//        );
//        TradeHelper.addVillagerTrade(event,
//                VillagerProfession.CARTOGRAPHER,
//                2,
//                (level, trader, rand) -> {
//                    ItemStack stack = ItemStackUtils.getFilledMap(trader.level(), trader.blockPosition(), ModTags.Structures.ON_CASTLE_EXPLORER_MAPS, MapDecorationTypes.RED_X, "filled_map.valhelsia_structures.castle");
//                    if (stack == null) {
//                        return null;
//                    }
//                    return new BasicItemListing(new ItemStack(Items.EMERALD, 12), new ItemStack(Items.COMPASS), stack, 12, 10, 1).getOffer(level, trader, rand);
//                }
//        );
//    }
}
