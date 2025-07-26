package com.stal111.valhelsia_structures.datagen.loot

import com.stal111.valhelsia_structures.common.block.BonePileBlock
import com.stal111.valhelsia_structures.common.block.CutPostBlock
import com.stal111.valhelsia_structures.common.block.SleepingBagBlock
import com.stal111.valhelsia_structures.common.block.properties.DungeonDoorPart
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties
import com.stal111.valhelsia_structures.core.init.ModBlocks
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BedPart
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue

class ModBlockLoot(lookupProvider: HolderLookup.Provider, val blocks: List<() -> Block>) :
    BlockLootSubProvider(setOf<Item>(), FeatureFlags.REGISTRY.allFlags(), lookupProvider) {

    override fun generate() {
        add(ModBlocks.SPECIAL_SPAWNER.get(), noDrop())
        dropSelf(ModBlocks.BRAZIER.get())
        dropSelf(ModBlocks.SOUL_BRAZIER.get())

        for (woodType in ModBlocks.WoodType.entries) {
            dropSelf(ModBlocks.WOODEN_POSTS[woodType]!!.get())
            dropSelf(ModBlocks.STRIPPED_WOODEN_POSTS[woodType]!!.get())

            add(ModBlocks.CUT_WOODEN_POSTS[woodType]!!.get()) { createCutPostDrops(it) }
            add(ModBlocks.CUT_STRIPPED_WOODEN_POSTS[woodType]!!.get()) { createCutPostDrops(it) }

            dropSelf(ModBlocks.BUNDLED_POSTS[woodType]!!.get())
            dropSelf(ModBlocks.BUNDLED_STRIPPED_POSTS[woodType]!!.get())
        }

        dropWhenSilkTouch(ModBlocks.METAL_FRAMED_GLASS.get())
        dropWhenSilkTouch(ModBlocks.METAL_FRAMED_GLASS_PANE.get())

        ModBlocks.COLORED_METAL_FRAMED_GLASS.forEach { (_, block) ->
            dropWhenSilkTouch(block.get())
        }

        ModBlocks.COLORED_METAL_FRAMED_GLASS_PANES.forEach { (_, block) ->
            dropWhenSilkTouch(block.get())
        }

        dropSelf(ModBlocks.PAPER_WALL.get())
        dropWhenSilkTouch(ModBlocks.HANGING_VINES.get())
        dropSelf(ModBlocks.GLAZED_JAR.get())
        dropSelf(ModBlocks.CRACKED_GLAZED_JAR.get())

        ModBlocks.COLORED_GLAZED_JARS.forEach { (_, block) ->
            dropSelf(block.get())
        }

        dropSelf(ModBlocks.EXPLORERS_TENT.get())
        dropSelf(ModBlocks.GIANT_FERN.get())
        dropSelf(ModBlocks.UNLIT_TORCH.get())
        dropSelf(ModBlocks.UNLIT_SOUL_TORCH.get())
        dropSelf(ModBlocks.DUNGEON_DOOR.get())
        add(ModBlocks.DUNGEON_DOOR.get()) {
            createSinglePropConditionTable(it, ModBlockStateProperties.DUNGEON_DOOR_PART, DungeonDoorPart.MIDDLE_2)
        }

        add(ModBlocks.BONE_PILE.get()) { createBonePileDrops(it) }
        dropSelf(ModBlocks.BONE_PILE_BLOCK.get())
        dropSelf(ModBlocks.UNLIT_LANTERN.get())
        dropSelf(ModBlocks.UNLIT_SOUL_LANTERN.get())

        ModBlocks.SLEEPING_BAGS.forEach { (_, block) ->
            add(block.get()) { createSinglePropConditionTable(it, SleepingBagBlock.PART, BedPart.HEAD) }
        }
    }

    fun createCutPostDrops(block: Block) = LootTable.lootTable().withPool(
        LootPool.lootPool()
            .setRolls(ConstantValue.exactly(1.0F))
            .add(LootItem.lootTableItem(block).apply(
                (1..4)
            ) {
                SetItemCountFunction.setCount(ConstantValue.exactly(it.toFloat()))
                    .`when`(
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                            StatePropertiesPredicate.Builder.properties().hasProperty(CutPostBlock.PARTS, it)
                        )
                    )
            })
    )

    fun createBonePileDrops(block: Block) = LootTable.lootTable().withPool(
        LootPool.lootPool()
            .setRolls(ConstantValue.exactly(1.0F))
            .add(LootItem.lootTableItem(block).apply(
                (1..5)
            ) {
                SetItemCountFunction.setCount(ConstantValue.exactly(it.toFloat()))
                    .`when`(
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                            StatePropertiesPredicate.Builder.properties().hasProperty(BonePileBlock.LAYERS, it)
                        )
                    )
            })
    )

    override fun getKnownBlocks() = blocks.map { it() }
}