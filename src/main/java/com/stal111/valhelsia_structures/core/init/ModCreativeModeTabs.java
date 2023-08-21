package com.stal111.valhelsia_structures.core.init;

import com.google.common.collect.ImmutableList;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.valhelsia.valhelsia_core.api.common.item.tab.CreativeTabFactory;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryClass;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;
import net.valhelsia.valhelsia_core.api.common.registry.helper.MappedRegistryHelper;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Valhelsia Team - stal111
 * @since 2023-08-19
 */
public class ModCreativeModeTabs implements RegistryClass {

    public static final MappedRegistryHelper<CreativeModeTab> HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.CREATIVE_MODE_TAB);

    public static final ImmutableList<Supplier<? extends ItemLike>> HIDDEN_ITEMS = ImmutableList.<Supplier<? extends ItemLike>>builder()
            .add(ModBlocks.STRIPPED_WOODEN_POSTS.get(ModBlocks.WoodType.LAPIDIFIED_JUNGLE))
            .add(ModBlocks.CUT_STRIPPED_WOODEN_POSTS.get(ModBlocks.WoodType.LAPIDIFIED_JUNGLE))
            .add(ModBlocks.BUNDLED_STRIPPED_POSTS.get(ModBlocks.WoodType.LAPIDIFIED_JUNGLE))
            .add(ModBlocks.HIBISCUS)
            .add(ModBlocks.GIANT_FERN)
            .build();

    public static final RegistryEntry<CreativeModeTab> MAIN = HELPER.register("main", CreativeTabFactory.create(builder -> builder
            .icon(() -> new ItemStack(ModBlocks.BRAZIER.get()))
            .title(Component.translatable("itemGroup.valhelsia_structures"))
            .displayItems((parameters, output) -> {
                List<Item> hiddenItems = HIDDEN_ITEMS.stream().map(supplier -> supplier.get().asItem()).toList();

                ValhelsiaStructures.REGISTRY_MANAGER.getItemHelper().getRegistryEntries().forEach(entry -> {
                    if (!hiddenItems.contains(entry.get())) {
                        output.accept(entry.get());
                    }
                });
            })
    ));
}
