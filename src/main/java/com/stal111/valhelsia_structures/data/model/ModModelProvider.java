package com.stal111.valhelsia_structures.data.model;

import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;
import net.valhelsia.valhelsia_core.datagen.ValhelsiaModelProvider;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * @author Vahelsia Team - stal111
 * @since 07.08.2024
 */
public class ModModelProvider extends ValhelsiaModelProvider {

    public ModModelProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators, Consumer<Item> consumer) {
        ModBlockModels.create(generators);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {

    }

    @Override
    public Collection<RegistryEntry<Block, ? extends Block>> getBlocks() {
        return ModBlocks.HELPER.getRegistryEntries();
    }
}
