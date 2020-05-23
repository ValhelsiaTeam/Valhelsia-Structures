package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.block.BrazierBlock;
import com.stal111.valhelsia_structures.block.PostBlock;
import com.stal111.valhelsia_structures.block.SpecialSpawnerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<SpecialSpawnerBlock> SPECIAL_SPAWNER = register("special_spawner", new SpecialSpawnerBlock(Block.Properties.from(Blocks.SPAWNER).hardnessAndResistance(-1.0F, 3600000.0F).noDrops()));
    public static final RegistryObject<BrazierBlock> BRAZIER = register("brazier", new BrazierBlock(Block.Properties.from(Blocks.IRON_BARS).lightValue(15).notSolid()));
    public static final RegistryObject<PostBlock> OAK_POST = register("oak_post", new PostBlock(Block.Properties.from(Blocks.OAK_LOG).notSolid()));
    public static final RegistryObject<PostBlock> SPRUCE_POST = register("spruce_post", new PostBlock(Block.Properties.from(Blocks.SPRUCE_LOG).notSolid()));
    public static final RegistryObject<PostBlock> BIRCH_POST = register("birch_post", new PostBlock(Block.Properties.from(Blocks.BIRCH_LOG).notSolid()));
    public static final RegistryObject<PostBlock> JUNGLE_POST = register("jungle_post", new PostBlock(Block.Properties.from(Blocks.JUNGLE_LOG).notSolid()));
    public static final RegistryObject<PostBlock> ACACIA_POST = register("acacia_post", new PostBlock(Block.Properties.from(Blocks.ACACIA_LOG).notSolid()));
    public static final RegistryObject<PostBlock> DARK_OAK_POST = register("dark_oak_post", new PostBlock(Block.Properties.from(Blocks.DARK_OAK_LOG).notSolid()));
    public static final RegistryObject<GlassBlock> METAL_FRAMED_GLASS = register("metal_framed_glass", new GlassBlock(Block.Properties.from(Blocks.GLASS)));
    public static final RegistryObject<PaneBlock> METAL_FRAMED_GLASS_PANE = register("metal_framed_glass_pane", new PaneBlock(Block.Properties.from(Blocks.GLASS_PANE)));

    private static <T extends Block> RegistryObject<T> register(String name, T block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block, new Item.Properties().group(ItemGroup.MISC)));
        return BLOCKS.register(name, () -> block);
    }
}
