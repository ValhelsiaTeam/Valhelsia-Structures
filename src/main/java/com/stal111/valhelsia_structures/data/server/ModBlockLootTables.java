package com.stal111.valhelsia_structures.data.server;

import com.google.common.collect.ImmutableSet;
import com.stal111.valhelsia_structures.block.JarBlock;
import com.stal111.valhelsia_structures.block.ValhelsiaStoneBlock;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraftforge.fml.RegistryObject;
import net.valhelsia.valhelsia_core.data.ValhelsiaBlockLootTables;

import java.util.*;
import java.util.function.Predicate;

/**
 * Mod Block Loot Tables
 * Valhelsia Structures - com.stal111.valhelsia_structures.data.server.ModBlockLootTables
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-22
 */
public class ModBlockLootTables extends ValhelsiaBlockLootTables {

    @Override
    public void addTables() {
        Set<RegistryObject<Block>> remainingBlocks = new HashSet<>(ModBlocks.HELPER.getDeferredRegister().getEntries());

        takeAll(remainingBlocks, block -> block instanceof SlabBlock).forEach(block ->
                registerLootTable(block, ValhelsiaBlockLootTables::droppingSlab));

        takeAll(remainingBlocks, block -> block instanceof ValhelsiaStoneBlock).forEach(block ->
                registerDropping(block, ((ValhelsiaStoneBlock) block).getPickBlock().get()));

        takeAll(remainingBlocks, Arrays.asList(ModBlocks.METAL_FRAMED_GLASS, ModBlocks.METAL_FRAMED_GLASS_PANE)).forEach(this::registerSilkTouch);

        remainingBlocks.removeIf(blockRegistryObject -> blockRegistryObject.get() instanceof JarBlock);

        takeAll(remainingBlocks, Arrays.asList(ModBlocks.HANGING_VINES, ModBlocks.HANGING_VINES_BODY)).forEach(block ->
                registerLootTable(block, droppingSheared(ModBlocks.HANGING_VINES.get())));

        remainingBlocks.forEach(blockRegistryObject -> registerDropSelfLootTable(blockRegistryObject.get()));
    }

    public static <T extends Block> Collection<? extends T> takeAll(Set<RegistryObject<T>> src, List<RegistryObject<? extends T>> blocks) {
        List<T> ret = new ArrayList<>();

        for (RegistryObject<? extends T> registryObject : blocks) {
            ret.add(registryObject.get());
        }
        src.removeAll(blocks);

        return ret;
    }


    public static Collection<Block> takeAll(Set<RegistryObject<Block>> src, Predicate<Block> predicate) {
        List<Block> ret = new ArrayList<>();

        Iterator<RegistryObject<Block>> iter = src.iterator();
        while (iter.hasNext()) {
            RegistryObject<Block> block = iter.next();
            if (predicate.test(block.get())) {
                iter.remove();
                ret.add(block.get());
            }
        }

        return ret;
    }
}
