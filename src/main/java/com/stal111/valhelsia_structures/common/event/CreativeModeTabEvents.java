package com.stal111.valhelsia_structures.common.event;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * @author Valhelsia Team
 * @since 2023-01-12
 */
public class CreativeModeTabEvents {

    @SubscribeEvent
    public void registerTabs(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(ValhelsiaStructures.MOD_ID, "main"), builder -> builder
                .icon(() -> new ItemStack(ModBlocks.BRAZIER.get()))
                .title(Component.translatable("itemGroup.valhelsia_structures"))
                .displayItems((featureFlags, output, hasOp) -> {
                    ValhelsiaStructures.REGISTRY_MANAGER.getItemHelper().getRegistryObjects().forEach(registryObject -> {
                        output.accept(registryObject.get());
                    });
                })
        );
    }
}
