package com.stal111.valhelsia_structures.common.event;

import com.google.common.collect.ImmutableList;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Valhelsia Team
 * @since 2023-01-12
 */
public class CreativeModeTabEvents {

    public static final ImmutableList<Supplier<? extends ItemLike>> HIDDEN_ITEMS = ImmutableList.<Supplier<? extends ItemLike>>builder()
            .add(ModBlocks.STRIPPED_WOODEN_POSTS.get(ModBlocks.WoodType.LAPIDIFIED_JUNGLE))
            .add(ModBlocks.CUT_STRIPPED_WOODEN_POSTS.get(ModBlocks.WoodType.LAPIDIFIED_JUNGLE))
            .add(ModBlocks.BUNDLED_STRIPPED_POSTS.get(ModBlocks.WoodType.LAPIDIFIED_JUNGLE))
            .add(ModBlocks.HIBISCUS)
            .add(ModBlocks.GIANT_FERN)
            .build();

    @SubscribeEvent
    public void registerTabs(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(ValhelsiaStructures.MOD_ID, "main"), builder -> builder
                .icon(() -> new ItemStack(ModBlocks.BRAZIER.get()))
                .title(Component.translatable("itemGroup.valhelsia_structures"))
                .displayItems((parameters, output) -> {
                    List<Item> hiddenItems = HIDDEN_ITEMS.stream().map(supplier -> supplier.get().asItem()).toList();

                    ValhelsiaStructures.REGISTRY_MANAGER.getItemHelper().getRegistryObjects().forEach(registryObject -> {
                        if (!hiddenItems.contains(registryObject.get())) {
                            output.accept(registryObject.get());
                        }
                    });
                })
        );
    }
}
