package com.stal111.valhelsia_structures.core.data.server.loot;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.flag.FeatureFlags;
import net.valhelsia.valhelsia_core.datagen.ValhelsiaBlockLootTables;

import java.util.Set;

/**
 * Mod Block Loot Tables <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.server.loot.ModBlockLootTables
 *
 * @author Valhelsia Team
 * @since 2020-11-22
 */
public class ModBlockLootTables extends ValhelsiaBlockLootTables {

    public ModBlockLootTables(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider, ValhelsiaStructures.REGISTRY_MANAGER);
    }

    @Override
    public void generate() {
        //TODO
//        getRemainingBlocks().removeIf(block ->
//                block instanceof ValhelsiaStoneBlock || block instanceof ValhelsiaGrassBlock || block == ModBlocks.DUNGEON_DOOR_LEAF.get()
//        );
//
//        forEach(block -> block instanceof SlabBlock, block -> add(block, ValhelsiaBlockLootTables::droppingSlab));
//
//        take(this::registerDropSelfLootTable, ModBlocks.PAPER_WALL, ModBlocks.METAL_FRAMED_GLASS_PANE, ModBlocks.METAL_FRAMED_GLASS);
//        forEach(block -> block instanceof StainedGlassPaneBlock, this::registerDropSelfLootTable);
//
//        forEach(block -> block instanceof GlassBlock || block instanceof IronBarsBlock, this::dropWhenSilkTouch);
//        forEach(block -> block instanceof JarBlock || block instanceof BigJarBlock, this::dropWhenSilkTouch);
//        take(block -> add(block, createShearsOnlyDrop(ModBlocks.HANGING_VINES.get())), ModBlocks.HANGING_VINES, ModBlocks.HANGING_VINES_BODY);
//        take(block -> add(block, bonePile ->
//                        LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(setCountFromIntegerProperty(bonePile, LootItem.lootTableItem(bonePile), ModBlockStateProperties.LAYERS_1_5).when(HAS_SILK_TOUCH).otherwise(setCountFromIntegerProperty(bonePile, LootItem.lootTableItem(Items.BONE), ModBlockStateProperties.LAYERS_1_5))))),
//                ModBlocks.BONE_PILE);
//        take(block -> add(block, createSilkTouchDispatchTable(block, withSurvivesExplosion(block, LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(ConstantValue.exactly(9)))))), ModBlocks.BONE_PILE_BLOCK);
//        forEach(block -> block instanceof CutPostBlock, block -> add(block, cutPostBlock -> LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(setCountFromIntegerProperty(block, LootItem.lootTableItem(block), ModBlockStateProperties.PARTS_1_4)))));
//        take(block -> add(block, createSinglePropConditionTable(block, DungeonDoorBlock.PART, DungeonDoorPart.MIDDLE_1)), ModBlocks.DUNGEON_DOOR);
//
//        forEach(block -> block instanceof SleepingBagBlock, block -> add(block, createSinglePropConditionTable(block, BlockStateProperties.BED_PART, BedPart.HEAD)));
//
//        take(block -> this.add(block, LootTable.lootTable().withPool(applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block).apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Color", "display.color")))))), ModBlocks.EXPLORERS_TENT);
//
//        forEach(block -> block instanceof BigJarTopBlock, block -> add(block, LootTable.lootTable()));
//
//        forEach(this::registerDropSelfLootTable);
    }
}
