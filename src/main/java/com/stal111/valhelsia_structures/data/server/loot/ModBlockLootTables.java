package com.stal111.valhelsia_structures.data.server.loot;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.common.block.*;
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.loot.functions.SetCount;
import net.valhelsia.valhelsia_core.data.ValhelsiaBlockLootTables;

/**
 * Mod Block Loot Tables
 * Valhelsia Structures - com.stal111.valhelsia_structures.data.server.loot.ModBlockLootTables
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-22
 */
public class ModBlockLootTables extends ValhelsiaBlockLootTables {

    public ModBlockLootTables() {
        super(ValhelsiaStructures.REGISTRY_MANAGER);
    }

    @Override
    public void addTables() {
        getRemainingBlocks().removeIf(block ->
                block.get() instanceof ValhelsiaStoneBlock || block.get() instanceof ValhelsiaGrassBlock || block.get() == ModBlocks.DUNGEON_DOOR_LEAF.get()
        );

        forEach(block -> block instanceof SlabBlock, block -> registerLootTable(block, ValhelsiaBlockLootTables::droppingSlab));
        take(this::registerSilkTouch, ModBlocks.METAL_FRAMED_GLASS, ModBlocks.METAL_FRAMED_GLASS_PANE);
        forEach(block -> block instanceof JarBlock || block instanceof BigJarBlock, this::registerSilkTouch);
        take(block -> registerLootTable(block, droppingSheared(ModBlocks.HANGING_VINES.get())), ModBlocks.HANGING_VINES, ModBlocks.HANGING_VINES_BODY);
        take(block -> registerLootTable(block, bonePile ->
                        LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(setCountFromIntegerProperty(bonePile, ItemLootEntry.builder(bonePile), ModBlockStateProperties.LAYERS_1_5).acceptCondition(SILK_TOUCH).alternatively(setCountFromIntegerProperty(bonePile, ItemLootEntry.builder(Items.BONE), ModBlockStateProperties.LAYERS_1_5))))),
                ModBlocks.BONE_PILE);
        take(block -> registerLootTable(block, bonePile ->
                        droppingWithSilkTouch(bonePile, withSurvivesExplosion(bonePile, ItemLootEntry.builder(Items.BONE).acceptFunction(SetCount.builder(ConstantRange.of(9)))))),
                ModBlocks.BONE_PILE_BLOCK);
        forEach(block -> block instanceof CutPostBlock, block -> registerLootTable(block, cutPostBlock -> LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(setCountFromIntegerProperty(block, ItemLootEntry.builder(block), ModBlockStateProperties.PARTS_1_4)))));

        forEach(this::registerDropSelfLootTable);
    }
}
