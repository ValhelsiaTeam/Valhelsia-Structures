package com.stal111.valhelsia_structures.data.server;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.block.JarBlock;
import com.stal111.valhelsia_structures.block.ValhelsiaGrassBlock;
import com.stal111.valhelsia_structures.block.ValhelsiaStoneBlock;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.state.properties.BlockStateProperties;
import net.valhelsia.valhelsia_core.data.ValhelsiaBlockLootTables;

/**
 * Mod Block Loot Tables
 * Valhelsia Structures - com.stal111.valhelsia_structures.data.server.ModBlockLootTables
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
                block.get() instanceof ValhelsiaStoneBlock ||
                        block.get() instanceof ValhelsiaGrassBlock ||
                        block.get() instanceof JarBlock
        );

        forEach(block -> block instanceof SlabBlock, block -> registerLootTable(block, ValhelsiaBlockLootTables::droppingSlab));
        take(this::registerSilkTouch, ModBlocks.METAL_FRAMED_GLASS, ModBlocks.METAL_FRAMED_GLASS_PANE);
        take(block -> registerLootTable(block, droppingSheared(ModBlocks.HANGING_VINES.get())), ModBlocks.HANGING_VINES, ModBlocks.HANGING_VINES_BODY);
        take(block -> registerLootTable(block, bonePile ->
                LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(setCountFromIntegerProperty(bonePile, ItemLootEntry.builder(bonePile), BlockStateProperties.LAYERS_1_8).acceptCondition(SILK_TOUCH).alternatively(setCountFromIntegerProperty(bonePile, ItemLootEntry.builder(Items.BONE), BlockStateProperties.LAYERS_1_8))))),
                ModBlocks.BONE_PILE);
        take(block -> registerLootTable(block, bonePile ->
                droppingWithSilkTouch(bonePile, withSurvivesExplosion(bonePile, ItemLootEntry.builder(Items.BONE).acceptFunction(SetCount.builder(ConstantRange.of(9)))))),
                ModBlocks.BONE_PILE_BLOCK);

        forEach(this::registerDropSelfLootTable);
    }
}
