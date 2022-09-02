package com.stal111.valhelsia_structures.common.event;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModEntities;
import com.stal111.valhelsia_structures.utils.ModTags;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.valhelsia.valhelsia_core.common.helper.TradeHelper;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;

/**
 * Entity Event Listener <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.event.EntityEventListener
 * <p>
 * For all entity-related event handling.
 * </p>
 * @author Valhelsia Team
 * @since 2021-05-11
 */
@Mod.EventBusSubscriber(modid = ValhelsiaStructures.MOD_ID)
public class EntityEventListener {

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Pillager entity && !event.getLevel().isClientSide()) {
            entity.goalSelector.addGoal(5, new MeleeAttackGoal(entity, 1.0D, true));
        }
    }

    @SubscribeEvent
    public static void onVillagerTrade(VillagerTradesEvent event) {
        TradeHelper.addVillagerTrade(event,
                VillagerProfession.CARTOGRAPHER,
                1,
                (trader, rand) -> {
                    ItemStack stack = ItemStackUtils.getFilledMap(trader.level, trader.blockPosition(), ModTags.Structures.ON_SPAWNER_DUNGEON_EXPLORER_MAPS, MapDecoration.Type.RED_X, "filled_map.valhelsia_structures.spawner_dungeon");
                    if (stack == null) {
                        return null;
                    }
                    return new MerchantOffer(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.COMPASS), stack, 12, 5, 1);
                }
        );
        TradeHelper.addVillagerTrade(event,
                VillagerProfession.CARTOGRAPHER,
                2,
                (trader, rand) -> {
                    ItemStack stack = ItemStackUtils.getFilledMap(trader.level, trader.blockPosition(), ModTags.Structures.ON_CASTLE_EXPLORER_MAPS, MapDecoration.Type.RED_X, "filled_map.valhelsia_structures.castle");
                    if (stack == null) {
                        return null;
                    }
                    return new MerchantOffer(new ItemStack(Items.EMERALD, 12), new ItemStack(Items.COMPASS), stack, 12, 10, 1);
                }
        );
    }

    @Mod.EventBusSubscriber(modid = ValhelsiaStructures.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents {

        @SubscribeEvent
        public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
            event.put(ModEntities.MOSSY_SKELETON.get(), AbstractSkeleton.createAttributes().build());
        }
    }
}
