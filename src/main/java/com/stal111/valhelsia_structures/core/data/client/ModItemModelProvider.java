package com.stal111.valhelsia_structures.core.data.client;

import com.stal111.valhelsia_structures.common.block.CutPostBlock;
import com.stal111.valhelsia_structures.common.block.SleepingBagBlock;
import com.stal111.valhelsia_structures.common.block.ValhelsiaGrassBlock;
import com.stal111.valhelsia_structures.common.block.ValhelsiaStoneBlock;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import com.stal111.valhelsia_structures.core.init.ModItems;
import net.minecraft.data.PackOutput;
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
 * @since 2020-11-13
 */
public class ModItemModelProvider extends ValhelsiaItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ValhelsiaStructures.REGISTRY_MANAGER, existingFileHelper);
    }

    @Override
    protected void registerModels() {
       // getRemainingBlockItems().remove(ModBlocks.JUNGLE_HEAD);
        getRemainingBlockItems().remove(ModBlocks.STRIPPED_LAPIDIFIED_JUNGLE_POST.getRegistryObject());
        getRemainingBlockItems().remove(ModBlocks.CUT_STRIPPED_LAPIDIFIED_JUNGLE_POST.getRegistryObject());

        forEachBlockItem(item -> item.getBlock() instanceof ValhelsiaGrassBlock || item.getBlock() instanceof ValhelsiaStoneBlock, item -> withParent(item, true));
        takeBlockItem(item -> withParent(item, getName(item) + "_off"), ModBlocks.BRAZIER, ModBlocks.SOUL_BRAZIER);
        takeBlockItem(item -> simpleModelBlockTexture(item, "metal_framed_glass"), ModBlocks.METAL_FRAMED_GLASS_PANE);
        ModBlocks.COLORED_METAL_FRAMED_GLASS_PANES.forEach((color, registryObject) -> {
            takeBlockItem(item -> simpleModelBlockTexture(item, color.getName() + "_metal_framed_glass"), registryObject);
        });
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

        takeBlockItem(blockItem -> this.getBuilder(this.getName(blockItem)).parent(this.getExistingFile(this.mcLoc("item/generated")))
                .texture("layer0", "item/explorers_tent")
                .texture("layer1", "item/explorers_tent_layer"), ModBlocks.EXPLORERS_TENT);

        forEachBlockItem(this::withParent);

        forEachItem(this::simpleModel);
    }

    private String getName(Item item) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath();
    }
}
