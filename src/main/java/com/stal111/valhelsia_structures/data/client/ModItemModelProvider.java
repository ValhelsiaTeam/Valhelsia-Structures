package com.stal111.valhelsia_structures.data.client;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.block.ValhelsiaGrassBlock;
import com.stal111.valhelsia_structures.block.ValhelsiaStoneBlock;
import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import net.valhelsia.valhelsia_core.data.ValhelsiaItemModelProvider;

import java.util.Objects;
import java.util.Set;

/**
 * Mod Item Model Provider
 * Valhelsia Structures - com.stal111.valhelsia_structures.data.client.ModItemModelProvider
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-13
 */
public class ModItemModelProvider extends ValhelsiaItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ValhelsiaStructures.REGISTRY_MANAGER, existingFileHelper);
    }

    @Override
    protected void register(Set<RegistryObject<Item>> items, Set<RegistryObject<Item>> blockItems) {
        forEach(blockItems, item -> item.getRegistryName().getPath().contains("lapidified_jungle_post"), item -> {});

        forEach(blockItems, item -> ((BlockItem) item).getBlock() instanceof ValhelsiaGrassBlock || ((BlockItem) item).getBlock() instanceof ValhelsiaStoneBlock, item -> withParent(item, true));
        takeBlockItem(blockItems, item -> withParent(item, Objects.requireNonNull(item.getRegistryName()).getPath() + "_off"), ModBlocks.BRAZIER, ModBlocks.SOUL_BRAZIER);
        takeBlockItem(blockItems, item -> simpleModelBlockTexture(item, "metal_framed_glass"), ModBlocks.METAL_FRAMED_GLASS_PANE);
        takeBlockItem(blockItems, this::simpleModelBlockTexture,
                ModBlocks.HANGING_VINES,
                ModBlocks.PAPER_WALL
        );
        takeBlockItem(blockItems, this::withParentInventory, ModBlocks.LAPIDIFIED_JUNGLE_BUTTON, ModBlocks.LAPIDIFIED_JUNGLE_FENCE);

        take(blockItems, item -> simpleModelBlockTexture(item, "doused_torch"),
                ModItems.DOUSED_TORCH,
                ModItems.DOUSED_SOUL_TORCH
        );

        forEach(blockItems, this::withParent);

        forEach(items, this::simpleModel);
    }
}
