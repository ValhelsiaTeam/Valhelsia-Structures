package com.stal111.valhelsia_structures.core.data.client;

import com.stal111.valhelsia_structures.common.block.SleepingBagBlock;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.ValhelsiaGrassBlock;
import com.stal111.valhelsia_structures.common.block.ValhelsiaStoneBlock;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.core.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.core.data.ValhelsiaItemModelProvider;

import java.util.Objects;

/**
 * Mod Item Model Provider <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.client.ModItemModelProvider
 *
 * @author Valhelsia Team
 * @version 1.19 - 0.2.0
 * @since 2020-11-13
 */
public class ModItemModelProvider extends ValhelsiaItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ValhelsiaStructures.REGISTRY_MANAGER, existingFileHelper);
    }

    @Override
    protected void registerModels() {
       // getRemainingBlockItems().remove(ModBlocks.JUNGLE_HEAD);
        getRemainingBlockItems().remove(ModBlocks.STRIPPED_LAPIDIFIED_JUNGLE_POST);
        getRemainingBlockItems().remove(ModBlocks.CUT_STRIPPED_LAPIDIFIED_JUNGLE_POST);

        forEachBlockItem(item -> item.getBlock() instanceof ValhelsiaGrassBlock || item.getBlock() instanceof ValhelsiaStoneBlock, item -> withParent(item, true));
        takeBlockItem(item -> withParent(item, getName(item) + "_off"), ModBlocks.BRAZIER, ModBlocks.SOUL_BRAZIER);
        takeBlockItem(item -> simpleModelBlockTexture(item, "metal_framed_glass"), ModBlocks.METAL_FRAMED_GLASS_PANE);
        forEachBlockItem(item -> item.getBlock() instanceof CutPostBlock, item -> withParent(item, getName(item) + "_1"));
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
                ModBlocks.DUNGEON_DOOR,
                ModBlocks.UNLIT_LANTERN,
                ModBlocks.UNLIT_SOUL_LANTERN
        );

        forEachBlockItem(item -> item.getBlock() instanceof SleepingBagBlock, this::withParentInventory);

        forEachBlockItem(this::withParent);

        forEachItem(this::simpleModel);
    }

    private String getName(Item item) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath();
    }
}
