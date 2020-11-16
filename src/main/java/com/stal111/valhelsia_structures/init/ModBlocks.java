package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.block.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

/**
 * Blocks
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModBlocks
 *
 * @author Valhelsia Team
 * @version 16.0.4
 */
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ValhelsiaStructures.MOD_ID);

    public static final RegistryObject<SpecialSpawnerBlock> SPECIAL_SPAWNER = register("special_spawner", new SpecialSpawnerBlock(Block.Properties.from(Blocks.SPAWNER).hardnessAndResistance(-1.0F, 3600000.0F).noDrops()));
    public static final RegistryObject<BrazierBlock> BRAZIER = register("brazier", new BrazierBlock(true, 1, Block.Properties.from(Blocks.IRON_BARS).notSolid().setLightLevel(state -> state.get(BrazierBlock.LIT) ? 15 : 0)));
    public static final RegistryObject<BrazierBlock> SOUL_BRAZIER = register("soul_brazier", new BrazierBlock(false, 2, Block.Properties.from(Blocks.IRON_BARS).notSolid().setLightLevel(state -> state.get(BrazierBlock.LIT) ? 11 : 0)));
    public static final RegistryObject<PostBlock> OAK_POST = register("oak_post", new PostBlock(Block.Properties.from(Blocks.OAK_LOG).notSolid()));
    public static final RegistryObject<PostBlock> SPRUCE_POST = register("spruce_post", new PostBlock(Block.Properties.from(Blocks.SPRUCE_LOG).notSolid()));
    public static final RegistryObject<PostBlock> BIRCH_POST = register("birch_post", new PostBlock(Block.Properties.from(Blocks.BIRCH_LOG).notSolid()));
    public static final RegistryObject<PostBlock> JUNGLE_POST = register("jungle_post", new PostBlock(Block.Properties.from(Blocks.JUNGLE_LOG).notSolid()));
    public static final RegistryObject<PostBlock> ACACIA_POST = register("acacia_post", new PostBlock(Block.Properties.from(Blocks.ACACIA_LOG).notSolid()));
    public static final RegistryObject<PostBlock> DARK_OAK_POST = register("dark_oak_post", new PostBlock(Block.Properties.from(Blocks.DARK_OAK_LOG).notSolid()));
    public static final RegistryObject<GlassBlock> METAL_FRAMED_GLASS = register("metal_framed_glass", new GlassBlock(Block.Properties.from(Blocks.GLASS)));
    public static final RegistryObject<PaneBlock> METAL_FRAMED_GLASS_PANE = register("metal_framed_glass_pane", new PaneBlock(Block.Properties.from(Blocks.GLASS_PANE)));
    public static final RegistryObject<PaneBlock> PAPER_WALL = register("paper_wall", new PaneBlock(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.3F).sound(SoundType.CLOTH).notSolid()));
    public static final RegistryObject<HangingVinesBodyBlock> HANGING_VINES_BODY = registerNoItem("hanging_vines_body", new HangingVinesBodyBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0.2F).sound(SoundType.VINE)));
    public static final RegistryObject<HangingVinesBlock> HANGING_VINES = register("hanging_vines", new HangingVinesBlock(Block.Properties.create(Material.TALL_PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.2F).sound(SoundType.VINE)));
    public static final RegistryObject<JarBlock> GLAZED_JAR = register("glazed_jar", new JarBlock(Block.Properties.create(Material.ROCK, MaterialColor.BROWN).setRequiresTool().hardnessAndResistance(1.4F).notSolid()));
    public static final RegistryObject<JarBlock> CRACKED_GLAZED_JAR = register("cracked_glazed_jar", new JarBlock(Block.Properties.create(Material.ROCK, MaterialColor.BROWN).setRequiresTool().hardnessAndResistance(1.0F).notSolid()));
    public static final List<RegistryObject<JarBlock>> COLORED_GLAZED_JARS = registerColoredGlazedJars();

    // Workaround for structures - stone that can't be replaced during later generation steps:
    public static final RegistryObject<Block> STONE = register("stone", new ValhelsiaStoneBlock(() -> Blocks.STONE, Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)));
    public static final RegistryObject<Block> GRANITE = register("granite", new ValhelsiaStoneBlock(() -> Blocks.GRANITE, Block.Properties.create(Material.ROCK, MaterialColor.DIRT).hardnessAndResistance(1.5F, 6.0F)));
    public static final RegistryObject<Block> DIORITE = register("diorite", new ValhelsiaStoneBlock(() -> Blocks.DIORITE, Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(1.5F, 6.0F)));
    public static final RegistryObject<Block> ANDESITE = register("andesite", new ValhelsiaStoneBlock(() -> Blocks.ANDESITE, Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)));

    // Workaround for structures - dirt that wont transform into grass blocks:
    public static final RegistryObject<Block> DIRT = register("dirt", new ValhelsiaStoneBlock(() -> Blocks.DIRT, Block.Properties.from(Blocks.DIRT)));

    private static List<RegistryObject<JarBlock>> registerColoredGlazedJars() {
        List<RegistryObject<JarBlock>> list = new ArrayList<>();
        for (DyeColor color : DyeColor.values()) {
            list.add(register(color.getTranslationKey() + "_glazed_jar", new JarBlock(Block.Properties.create(Material.ROCK, color).setRequiresTool().hardnessAndResistance(1.4F).notSolid())));
        }
        return list;
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, T block) {
        return BLOCKS.register(name, () -> block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, T block) {
        ModItems.registerBlockItem(name, block);
        return BLOCKS.register(name, () -> block);
    }
}
