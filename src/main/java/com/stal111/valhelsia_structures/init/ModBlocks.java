package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.ValhelsiaStructuresItemGroups;
import com.stal111.valhelsia_structures.block.BrazierBlock;
import com.stal111.valhelsia_structures.block.PostBlock;
import com.stal111.valhelsia_structures.block.SpecialSpawnerBlock;
import com.stal111.valhelsia_structures.block.ValhelsiaStoneBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Blocks
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModBlocks
 *
 * @author Valhelsia Team
 * @version 15.0.3
 */
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<SpecialSpawnerBlock> SPECIAL_SPAWNER = register("special_spawner", new SpecialSpawnerBlock(Block.Properties.from(Blocks.SPAWNER).hardnessAndResistance(-1.0F, 3600000.0F).noDrops()));
    public static final RegistryObject<BrazierBlock> BRAZIER = register("brazier", new BrazierBlock(Block.Properties.from(Blocks.IRON_BARS).harvestTool(ToolType.PICKAXE).lightValue(15).notSolid()));
    public static final RegistryObject<PostBlock> OAK_POST = register("oak_post", new PostBlock(Block.Properties.from(Blocks.OAK_LOG).harvestTool(ToolType.AXE).notSolid()));
    public static final RegistryObject<PostBlock> SPRUCE_POST = register("spruce_post", new PostBlock(Block.Properties.from(Blocks.SPRUCE_LOG).harvestTool(ToolType.AXE).notSolid()));
    public static final RegistryObject<PostBlock> BIRCH_POST = register("birch_post", new PostBlock(Block.Properties.from(Blocks.BIRCH_LOG).harvestTool(ToolType.AXE).notSolid()));
    public static final RegistryObject<PostBlock> JUNGLE_POST = register("jungle_post", new PostBlock(Block.Properties.from(Blocks.JUNGLE_LOG).harvestTool(ToolType.AXE).notSolid()));
    public static final RegistryObject<PostBlock> ACACIA_POST = register("acacia_post", new PostBlock(Block.Properties.from(Blocks.ACACIA_LOG).harvestTool(ToolType.AXE).notSolid()));
    public static final RegistryObject<PostBlock> DARK_OAK_POST = register("dark_oak_post", new PostBlock(Block.Properties.from(Blocks.DARK_OAK_LOG).harvestTool(ToolType.AXE).notSolid()));
    public static final RegistryObject<GlassBlock> METAL_FRAMED_GLASS = register("metal_framed_glass", new GlassBlock(Block.Properties.from(Blocks.GLASS).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<PaneBlock> METAL_FRAMED_GLASS_PANE = register("metal_framed_glass_pane", new PaneBlock(Block.Properties.from(Blocks.GLASS_PANE).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<PaneBlock> PAPER_WALL = register("paper_wall", new PaneBlock(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.3F).harvestTool(ToolType.AXE).sound(SoundType.CLOTH).notSolid()));

    // Workaround for structures - stone that can't be replaced during later generation steps:
    public static final RegistryObject<Block> STONE = register("stone", new ValhelsiaStoneBlock(() -> Blocks.STONE, Block.Properties.create(Material.ROCK, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5F, 6.0F)));
    public static final RegistryObject<Block> GRANITE = register("granite", new ValhelsiaStoneBlock(() -> Blocks.GRANITE, Block.Properties.create(Material.ROCK, MaterialColor.DIRT).harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5F, 6.0F)));
    public static final RegistryObject<Block> DIORITE = register("diorite", new ValhelsiaStoneBlock(() -> Blocks.DIORITE, Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5F, 6.0F)));
    public static final RegistryObject<Block> ANDESITE = register("andesite", new ValhelsiaStoneBlock(() -> Blocks.ANDESITE, Block.Properties.create(Material.ROCK, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5F, 6.0F)));

    private static <T extends Block> RegistryObject<T> register(String name, T block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block, new Item.Properties().group(ValhelsiaStructuresItemGroups.MAIN)));
        return BLOCKS.register(name, () -> block);
    }
}
