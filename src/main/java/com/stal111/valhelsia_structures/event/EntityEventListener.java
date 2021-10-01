package com.stal111.valhelsia_structures.event;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.init.ModEntities;
import com.stal111.valhelsia_structures.init.ModStructures;
import net.minecraft.world.entity.Entity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.world.storage.MapDecoration;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.valhelsia.valhelsia_core.helper.TradeHelper;
import net.valhelsia.valhelsia_core.util.ItemStackUtils;

/**
 * Entity Event Listener <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.event.EntityEventListener
 * <p>
 * For all entity-related event handling.
 * </p>
 * @author Valhelsia Team
 * @version 0.1.5
 * @since 2021-05-11
 */
@Mod.EventBusSubscriber(modid = ValhelsiaStructures.MOD_ID)
public class EntityEventListener {

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Pillager && !event.getWorld().isRemote()) {
            PillagerEntity pillagerEntity = (PillagerEntity) entity;
            pillagerEntity.goalSelector.addGoal(5, new MeleeAttackGoal(pillagerEntity, 1.0D, true));
        }
    }

    @SubscribeEvent
    public static void onVillagerTrade(VillagerTradesEvent event) {
        TradeHelper.addVillagerTrade(event,
                VillagerProfession.CARTOGRAPHER,
                1,
                (trader, rand) -> {
                    ItemStack stack = ItemStackUtils.getFilledMap(trader.world, trader.getPosition(), ModStructures.SPAWNER_DUNGEON.get(), MapDecoration.Type.RED_X);
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
                    ItemStack stack = ItemStackUtils.getFilledMap(trader.world, trader.getPosition(), ModStructures.CASTLE.get(), MapDecoration.Type.RED_X);
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
            event.put(ModEntities.MOSSY_SKELETON.get(), AbstractSkeletonEntity.registerAttributes().create());
        }
    }
}
