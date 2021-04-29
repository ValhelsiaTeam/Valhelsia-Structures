package com.stal111.valhelsia_structures.data.client;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.block.CutPostBlock;
import com.stal111.valhelsia_structures.block.ValhelsiaGrassBlock;
import com.stal111.valhelsia_structures.block.ValhelsiaStoneBlock;
import com.stal111.valhelsia_structures.init.ModBlocks;
import com.stal111.valhelsia_structures.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.valhelsia.valhelsia_core.data.ValhelsiaItemModelProvider;

import java.util.Objects;

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
    protected void registerModels() {
        getRemainingBlockItems().removeIf(item -> item.get().getRegistryName().getPath().contains("lapidified_jungle_post"));
        getRemainingBlockItems().remove(ModBlocks.JUNGLE_HEAD);

        forEachBlockItem(item -> item.getBlock() instanceof ValhelsiaGrassBlock || item.getBlock() instanceof ValhelsiaStoneBlock, item -> withParent(item, true));
        takeBlockItem(item -> withParent(item, Objects.requireNonNull(item.getRegistryName()).getPath() + "_off"), ModBlocks.BRAZIER, ModBlocks.SOUL_BRAZIER);
        takeBlockItem(item -> simpleModelBlockTexture(item, "metal_framed_glass"), ModBlocks.METAL_FRAMED_GLASS_PANE);
        forEachBlockItem(item -> item.getBlock() instanceof CutPostBlock, item -> withParent(item, item.getRegistryName().getPath() + "_1"));
        takeBlockItem(this::simpleModelBlockTexture,
                ModBlocks.HANGING_VINES,
                ModBlocks.PAPER_WALL
        );
        takeBlockItem(this::withParentInventory,
                ModBlocks.LAPIDIFIED_JUNGLE_BUTTON,
                ModBlocks.LAPIDIFIED_JUNGLE_FENCE,
                ModBlocks.BONE_PILE
        );

        takeBlockItem(item -> simpleModelBlockTexture(item, "doused_torch"),
                ModItems.DOUSED_TORCH,
                ModItems.DOUSED_SOUL_TORCH
        );

        takeBlockItem(this::simpleModel,
                ModBlocks.DUNGEON_DOOR
        );

        forEachBlockItem(this::withParent);

        forEachItem(this::simpleModel);
    }
}
