package com.stal111.valhelsia_structures.core.data.server.loot;

import com.stal111.valhelsia_structures.common.block.*;
import com.stal111.valhelsia_structures.common.block.properties.DungeonDoorPart;
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.valhelsia.valhelsia_core.core.data.ValhelsiaBlockLootTables;

/**
 * Mod Block Loot Tables <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.server.loot.ModBlockLootTables
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.2.0
 * @since 2020-11-22
 */
public class ModBlockLootTables extends ValhelsiaBlockLootTables {

    public ModBlockLootTables() {
        super(ValhelsiaStructures.REGISTRY_MANAGER);
    }

    @Override
    public void addTables() {
        getRemainingBlocks().removeIf(block ->
                block.get() instanceof ValhelsiaStoneBlock || block.get() instanceof ValhelsiaGrassBlock || block.get() == ModBlocks.DUNGEON_DOOR_LEAF.get() || block.get() instanceof BigJarTopBlock
        );

        forEach(block -> block instanceof SlabBlock, block -> add(block, ValhelsiaBlockLootTables::droppingSlab));
        take(this::dropWhenSilkTouch, ModBlocks.METAL_FRAMED_GLASS, ModBlocks.METAL_FRAMED_GLASS_PANE);
        forEach(block -> block instanceof JarBlock || block instanceof BigJarBlock, this::dropWhenSilkTouch);
        take(block -> add(block, createShearsOnlyDrop(ModBlocks.HANGING_VINES.get())), ModBlocks.HANGING_VINES, ModBlocks.HANGING_VINES_BODY);
        take(block -> add(block, bonePile ->
                        LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(setCountFromIntegerProperty(bonePile, LootItem.lootTableItem(bonePile), ModBlockStateProperties.LAYERS_1_5).when(HAS_SILK_TOUCH).otherwise(setCountFromIntegerProperty(bonePile, LootItem.lootTableItem(Items.BONE), ModBlockStateProperties.LAYERS_1_5))))),
                ModBlocks.BONE_PILE);
        take(block -> add(block, createSilkTouchDispatchTable(block, withSurvivesExplosion(block, LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(ConstantValue.exactly(9)))))), ModBlocks.BONE_PILE_BLOCK);
        forEach(block -> block instanceof CutPostBlock, block -> add(block, cutPostBlock -> LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(setCountFromIntegerProperty(block, LootItem.lootTableItem(block), ModBlockStateProperties.PARTS_1_4)))));
        take(block -> add(block, createSinglePropConditionTable(block, DungeonDoorBlock.PART, DungeonDoorPart.MIDDLE_1)), ModBlocks.DUNGEON_DOOR);

        forEach(block -> block instanceof SleepingBagBlock, block -> createSinglePropConditionTable(block, BlockStateProperties.BED_PART, BedPart.HEAD));

        forEach(this::registerDropSelfLootTable);
    }
}
