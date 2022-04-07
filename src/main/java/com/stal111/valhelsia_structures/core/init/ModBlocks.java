package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.common.block.*;
import com.stal111.valhelsia_structures.common.block.properties.BlockProperties;
import com.stal111.valhelsia_structures.common.item.BigJarBlockItem;
import com.stal111.valhelsia_structures.common.item.DyeableBlockItem;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.client.util.ValhelsiaRenderType;
import net.valhelsia.valhelsia_core.core.registry.block.BlockRegistryHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Blocks <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModBlocks
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.2.0
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {

    public static final BlockRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getBlockHelper();

    public static final RegistryObject<SpecialSpawnerBlock> SPECIAL_SPAWNER = HELPER.register("special_spawner", () -> new SpecialSpawnerBlock(Block.Properties.copy(Blocks.SPAWNER).strength(-1.0F, 3600000.0F).noDrops()), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<BrazierBlock> BRAZIER = HELPER.register("brazier", () -> new BrazierBlock(true, 1, Block.Properties.copy(Blocks.IRON_BARS).noOcclusion().lightLevel(state -> state.getValue(BrazierBlock.LIT) ? 15 : 0)), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<BrazierBlock> SOUL_BRAZIER = HELPER.register("soul_brazier", () -> new BrazierBlock(false, 2, Block.Properties.copy(Blocks.IRON_BARS).noOcclusion().lightLevel(state -> state.getValue(BrazierBlock.LIT) ? 11 : 0)), ValhelsiaRenderType.CUTOUT);

    // Posts
    public static final RegistryObject<PostBlock> OAK_POST = HELPER.register("oak_post", () -> new PostBlock(() -> Blocks.OAK_LOG));
    public static final RegistryObject<PostBlock> SPRUCE_POST = HELPER.register("spruce_post", () -> new PostBlock(() -> Blocks.SPRUCE_LOG));
    public static final RegistryObject<PostBlock> BIRCH_POST = HELPER.register("birch_post", () -> new PostBlock(() -> Blocks.BIRCH_LOG));
    public static final RegistryObject<PostBlock> JUNGLE_POST = HELPER.register("jungle_post", () -> new PostBlock(() -> Blocks.JUNGLE_LOG));
    public static final RegistryObject<PostBlock> ACACIA_POST = HELPER.register("acacia_post", () -> new PostBlock(() -> Blocks.ACACIA_LOG));
    public static final RegistryObject<PostBlock> DARK_OAK_POST = HELPER.register("dark_oak_post", () -> new PostBlock(() -> Blocks.DARK_OAK_LOG));
    public static final RegistryObject<PostBlock> WARPED_POST = HELPER.register("warped_post", () -> new PostBlock(() -> Blocks.WARPED_STEM));
    public static final RegistryObject<PostBlock> CRIMSON_POST = HELPER.register("crimson_post", () -> new PostBlock(() -> Blocks.CRIMSON_STEM));
    //public static final RegistryObject<PostBlock> LAPIDIFIED_JUNGLE_POST = HELPER.register("lapidified_jungle_post", new PostBlock(new ResourceLocation(ValhelsiaStructures.MOD_ID, "lapidified_jungle_log"), BlockProperties.LAPIDIFIED_JUNGLE_LOG));
    //Cut Posts
    public static final RegistryObject<CutPostBlock> CUT_OAK_POST = HELPER.register("cut_oak_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.WOOD, MaterialColor.PODZOL)));
    public static final RegistryObject<CutPostBlock> CUT_SPRUCE_POST = HELPER.register("cut_spruce_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.PODZOL, MaterialColor.COLOR_BROWN)));
    public static final RegistryObject<CutPostBlock> CUT_BIRCH_POST = HELPER.register("cut_birch_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.SAND, MaterialColor.QUARTZ)));
    public static final RegistryObject<CutPostBlock> CUT_JUNGLE_POST = HELPER.register("cut_jungle_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.DIRT, MaterialColor.PODZOL)));
    public static final RegistryObject<CutPostBlock> CUT_ACACIA_POST = HELPER.register("cut_acacia_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.COLOR_ORANGE, MaterialColor.STONE)));
    public static final RegistryObject<CutPostBlock> CUT_DARK_OAK_POST = HELPER.register("cut_dark_oak_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.COLOR_BROWN, MaterialColor.COLOR_BROWN)));
    public static final RegistryObject<CutPostBlock> CUT_WARPED_POST = HELPER.register("cut_warped_post", () -> new CutPostBlock(BlockProperties.createCutNetherPostBlock(MaterialColor.WARPED_STEM)));
    public static final RegistryObject<CutPostBlock> CUT_CRIMSON_POST = HELPER.register("cut_crimson_post", () -> new CutPostBlock(BlockProperties.createCutNetherPostBlock(MaterialColor.CRIMSON_STEM)));
    //public static final RegistryObject<CutPostBlock> CUT_LAPIDIFIED_JUNGLE_POST = HELPER.register("cut_lapidified_jungle_post", new CutPostBlock(BlockProperties.LAPIDIFIED_JUNGLE_LOG.notSolid()));

    // Bundled Posts
    public static final RegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_OAK_POSTS = HELPER.registerStrippedLogBlock("bundled_stripped_oak_posts", MaterialColor.WOOD, MaterialColor.WOOD);
    public static final RegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_SPRUCE_POSTS = HELPER.registerStrippedLogBlock("bundled_stripped_spruce_posts", MaterialColor.PODZOL, MaterialColor.PODZOL);
    public static final RegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_BIRCH_POSTS = HELPER.registerStrippedLogBlock("bundled_stripped_birch_posts", MaterialColor.SAND, MaterialColor.SAND);
    public static final RegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_JUNGLE_POSTS = HELPER.registerStrippedLogBlock("bundled_stripped_jungle_posts", MaterialColor.DIRT, MaterialColor.DIRT);
    public static final RegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_ACACIA_POSTS = HELPER.registerStrippedLogBlock("bundled_stripped_acacia_posts", MaterialColor.COLOR_ORANGE, MaterialColor.COLOR_ORANGE);
    public static final RegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_DARK_OAK_POSTS = HELPER.registerStrippedLogBlock("bundled_stripped_dark_oak_posts", MaterialColor.COLOR_BROWN, MaterialColor.COLOR_BROWN);
    public static final RegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_CRIMSON_POSTS = HELPER.registerStrippedLogBlock("bundled_stripped_crimson_posts", MaterialColor.CRIMSON_STEM, MaterialColor.CRIMSON_STEM);
    public static final RegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_WARPED_POSTS = HELPER.registerStrippedLogBlock("bundled_stripped_warped_posts", MaterialColor.WARPED_STEM, MaterialColor.WARPED_STEM);

    public static final RegistryObject<RotatedPillarBlock> BUNDLED_OAK_POSTS = HELPER.registerLogBlock("bundled_oak_posts", BUNDLED_STRIPPED_OAK_POSTS, MaterialColor.WOOD, MaterialColor.PODZOL);
    public static final RegistryObject<RotatedPillarBlock> BUNDLED_SPRUCE_POSTS = HELPER.registerLogBlock("bundled_spruce_posts", BUNDLED_STRIPPED_SPRUCE_POSTS, MaterialColor.PODZOL, MaterialColor.COLOR_BROWN);
    public static final RegistryObject<RotatedPillarBlock> BUNDLED_BIRCH_POSTS = HELPER.registerLogBlock("bundled_birch_posts", BUNDLED_STRIPPED_BIRCH_POSTS, MaterialColor.SAND, MaterialColor.QUARTZ);
    public static final RegistryObject<RotatedPillarBlock> BUNDLED_JUNGLE_POSTS = HELPER.registerLogBlock("bundled_jungle_posts", BUNDLED_STRIPPED_JUNGLE_POSTS, MaterialColor.DIRT, MaterialColor.PODZOL);
    public static final RegistryObject<RotatedPillarBlock> BUNDLED_ACACIA_POSTS = HELPER.registerLogBlock("bundled_acacia_posts", BUNDLED_STRIPPED_ACACIA_POSTS, MaterialColor.COLOR_ORANGE, MaterialColor.STONE);
    public static final RegistryObject<RotatedPillarBlock> BUNDLED_DARK_OAK_POSTS = HELPER.registerLogBlock("bundled_dark_oak_posts", BUNDLED_STRIPPED_DARK_OAK_POSTS, MaterialColor.COLOR_BROWN, MaterialColor.COLOR_BROWN);
    public static final RegistryObject<RotatedPillarBlock> BUNDLED_CRIMSON_POSTS = HELPER.registerLogBlock("bundled_crimson_posts", BUNDLED_STRIPPED_CRIMSON_POSTS, MaterialColor.CRIMSON_STEM, MaterialColor.CRIMSON_STEM);
    public static final RegistryObject<RotatedPillarBlock> BUNDLED_WARPED_POSTS = HELPER.registerLogBlock("bundled_warped_posts", BUNDLED_STRIPPED_WARPED_POSTS, MaterialColor.WARPED_STEM, MaterialColor.WARPED_STEM);

    public static final RegistryObject<GlassBlock> METAL_FRAMED_GLASS = HELPER.register("metal_framed_glass", () -> new GlassBlock(Block.Properties.copy(Blocks.GLASS)), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<IronBarsBlock> METAL_FRAMED_GLASS_PANE = HELPER.register("metal_framed_glass_pane", () -> new IronBarsBlock(Block.Properties.copy(Blocks.GLASS_PANE)), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<IronBarsBlock> PAPER_WALL = HELPER.register("paper_wall", () -> new IronBarsBlock(Block.Properties.of(Material.DECORATION).strength(0.3F).sound(SoundType.WOOL).noOcclusion()));
    public static final RegistryObject<HangingVinesBodyBlock> HANGING_VINES_BODY = HELPER.registerNoItem("hanging_vines_body", () -> new HangingVinesBodyBlock(Block.Properties.of(Material.REPLACEABLE_PLANT).noCollission().strength(0.2F).sound(SoundType.VINE)), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<HangingVinesBlock> HANGING_VINES = HELPER.register("hanging_vines", () -> new HangingVinesBlock(Block.Properties.of(Material.REPLACEABLE_PLANT).randomTicks().noCollission().strength(0.2F).sound(SoundType.VINE)), ValhelsiaRenderType.CUTOUT);
    //public static final RegistryObject<JungleHeadBlock> JUNGLE_HEAD = HELPER.register("jungle_head", new JungleHeadBlock(Block.Properties.from(Blocks.COBBLESTONE).notSolid()), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<JarBlock> GLAZED_JAR = HELPER.register("glazed_jar", () -> new JarBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()));
    public static final RegistryObject<JarBlock> CRACKED_GLAZED_JAR = HELPER.register("cracked_glazed_jar", () -> new JarBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.0F).noOcclusion()));
    public static final List<RegistryObject<JarBlock>> COLORED_GLAZED_JARS = registerColoredGlazedJars();
    public static final RegistryObject<BigJarBlock> BIG_GLAZED_JAR = HELPER.register("big_glazed_jar", () -> new BigJarBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()), (block) -> new BigJarBlockItem(block.get(), new Item.Properties().tab(HELPER.getDefaultCreativeTab())), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<BigJarTopBlock> BIG_GLAZED_JAR_TOP = HELPER.registerNoItem("big_glazed_jar_top", () -> new BigJarTopBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()), ValhelsiaRenderType.CUTOUT);

    public static final RegistryObject<BigJarBlock> CRACKED_BIG_GLAZED_JAR = HELPER.register("cracked_big_glazed_jar", () -> new BigJarBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()), (block) -> new BigJarBlockItem(block.get(), new Item.Properties().tab(HELPER.getDefaultCreativeTab())), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<BigJarTopBlock> CRACKED_BIG_GLAZED_JAR_TOP = HELPER.registerNoItem("cracked_big_glazed_jar_top", () -> new BigJarTopBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()), ValhelsiaRenderType.CUTOUT);

    public static final List<RegistryObject<BigJarBlock>> BIG_COLORED_GLAZED_JARS = registerBigColoredGlazedJars();
    public static final RegistryObject<RotatedPillarBlock> LAPIDIFIED_JUNGLE_LOG = HELPER.register("lapidified_jungle_log", () -> new RotatedPillarBlock(BlockProperties.LAPIDIFIED_JUNGLE_LOG));
    public static final RegistryObject<RotatedPillarBlock> LAPIDIFIED_JUNGLE_WOOD = HELPER.register("lapidified_jungle_wood", () -> new RotatedPillarBlock(BlockProperties.LAPIDIFIED_JUNGLE_LOG));
    public static final RegistryObject<Block> LAPIDIFIED_JUNGLE_PLANKS = HELPER.register("lapidified_jungle_planks", () -> new Block(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS));
    public static final RegistryObject<SlabBlock> LAPIDIFIED_JUNGLE_SLAB = HELPER.register("lapidified_jungle_slab", () -> new SlabBlock(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS));
    public static final RegistryObject<StairBlock> LAPIDIFIED_JUNGLE_STAIRS = HELPER.register("lapidified_jungle_stairs", () -> new StairBlock(() -> ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get().defaultBlockState(), BlockProperties.LAPIDIFIED_JUNGLE_PLANKS));
    public static final RegistryObject<PressurePlateBlock> LAPIDIFIED_JUNGLE_PRESSURE_PLATE = HELPER.register("lapidified_jungle_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockProperties.LAPIDIFIED_JUNGLE_PLANKS.strength(0.5F)));
    public static final RegistryObject<WoodButtonBlock> LAPIDIFIED_JUNGLE_BUTTON = HELPER.register("lapidified_jungle_button", () -> new WoodButtonBlock(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS.strength(0.5F)));
    public static final RegistryObject<FenceBlock> LAPIDIFIED_JUNGLE_FENCE = HELPER.register("lapidified_jungle_fence", () -> new FenceBlock(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS));
    public static final RegistryObject<FenceGateBlock> LAPIDIFIED_JUNGLE_FENCE_GATE = HELPER.register("lapidified_jungle_fence_gate", () -> new FenceGateBlock(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS));
    public static final RegistryObject<ExplorersTentBlock> EXPLORERS_TENT = HELPER.register("explorers_tent", () -> new ExplorersTentBlock(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS.noCollission()), block -> new DyeableBlockItem(block.get(), new Item.Properties().tab(HELPER.getDefaultCreativeTab())));
    public static final RegistryObject<BushBlock> HIBISCUS = HELPER.register("hibiscus", () -> new BushBlock(Block.Properties.copy(Blocks.POPPY)), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<GiantFernBlock> GIANT_FERN = HELPER.register("giant_fern", () -> new GiantFernBlock(Block.Properties.copy(Blocks.POPPY)));
    public static final RegistryObject<DousedTorchBlock> DOUSED_TORCH = HELPER.registerNoItem("doused_torch", () -> new DousedTorchBlock((TorchBlock) Blocks.TORCH, Block.Properties.of(Material.DECORATION).noCollission().instabreak()), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<DousedWallTorchBlock> DOUSED_WALL_TORCH = HELPER.registerNoItem("doused_wall_torch", () -> new DousedWallTorchBlock((TorchBlock) Blocks.WALL_TORCH, Block.Properties.of(Material.DECORATION).noCollission().instabreak()), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<DousedTorchBlock> DOUSED_SOUL_TORCH = HELPER.registerNoItem("doused_soul_torch", () -> new DousedTorchBlock((TorchBlock) Blocks.SOUL_TORCH, Block.Properties.of(Material.DECORATION).noCollission().instabreak()), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<DousedWallTorchBlock> DOUSED_SOUL_WALL_TORCH = HELPER.registerNoItem("doused_soul_wall_torch", () -> new DousedWallTorchBlock((TorchBlock) Blocks.SOUL_WALL_TORCH, Block.Properties.of(Material.DECORATION).noCollission().instabreak()), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<DungeonDoorBlock> DUNGEON_DOOR = HELPER.register("dungeon_door", () -> new DungeonDoorBlock(Block.Properties.of(Material.METAL).strength(50.0F, 100.0F).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<DungeonDoorLeafBlock> DUNGEON_DOOR_LEAF = HELPER.registerNoItem("dungeon_door_leaf", () -> new DungeonDoorLeafBlock(Block.Properties.of(Material.METAL).strength(50.0F, 100.0F).requiresCorrectToolForDrops().noOcclusion().lootFrom(ModBlocks.DUNGEON_DOOR)));
    public static final RegistryObject<BonePileBlock> BONE_PILE = HELPER.register("bone_pile", () -> new BonePileBlock(Block.Properties.copy(Blocks.BONE_BLOCK).noOcclusion()), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<Block> BONE_PILE_BLOCK = HELPER.register("bone_pile_block", () -> new Block(Block.Properties.copy(Blocks.BONE_BLOCK)));
    public static final RegistryObject<UnlitLanternBlock> UNLIT_LANTERN = HELPER.register("unlit_lantern", () -> new UnlitLanternBlock(() -> Blocks.LANTERN, Block.Properties.copy(Blocks.LANTERN).lightLevel(value -> 0)), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<UnlitLanternBlock> UNLIT_SOUL_LANTERN = HELPER.register("unlit_soul_lantern", () -> new UnlitLanternBlock(() -> Blocks.SOUL_LANTERN, Block.Properties.copy(Blocks.SOUL_LANTERN).lightLevel(value -> 0)), ValhelsiaRenderType.CUTOUT);

    //Sleeping Bags
    public static final List<RegistryObject<SleepingBagBlock>> SLEEPING_BAGS = registerSleepingBags();

    // Workarounds for structures:

    // stone that can't be replaced during later generation steps
    public static final RegistryObject<Block> STONE = HELPER.register("stone", () -> new ValhelsiaStoneBlock(() -> Blocks.STONE, Block.Properties.copy(Blocks.STONE).lootFrom(() -> Blocks.STONE)));
    public static final RegistryObject<Block> GRANITE = HELPER.register("granite", () -> new ValhelsiaStoneBlock(() -> Blocks.GRANITE, Block.Properties.copy(Blocks.GRANITE).lootFrom(() -> Blocks.GRANITE)));
    public static final RegistryObject<Block> DIORITE = HELPER.register("diorite", () -> new ValhelsiaStoneBlock(() -> Blocks.DIORITE, Block.Properties.copy(Blocks.DIORITE).lootFrom(() -> Blocks.DIORITE)));
    public static final RegistryObject<Block> ANDESITE = HELPER.register("andesite", () -> new ValhelsiaStoneBlock(() -> Blocks.ANDESITE, Block.Properties.copy(Blocks.ANDESITE).lootFrom(() -> Blocks.ANDESITE)));
    // grass block on which features cannot generate
    public static final RegistryObject<Block> GRASS_BLOCK = HELPER.register("grass_block", () -> new ValhelsiaGrassBlock(Block.Properties.copy(Blocks.GRASS_BLOCK).lootFrom(() -> Blocks.GRASS_BLOCK)), ValhelsiaRenderType.CUTOUT);
    // dirt that wont transform into grass blocks
    public static final RegistryObject<Block> DIRT = HELPER.register("dirt", () -> new ValhelsiaStoneBlock(() -> Blocks.DIRT, Block.Properties.copy(Blocks.DIRT).lootFrom(() -> Blocks.DIRT)));
    public static final RegistryObject<Block> COARSE_DIRT = HELPER.register("coarse_dirt", () -> new ValhelsiaStoneBlock(() -> Blocks.COARSE_DIRT, Block.Properties.copy(Blocks.COARSE_DIRT).lootFrom(() -> Blocks.COARSE_DIRT)));

    private static List<RegistryObject<JarBlock>> registerColoredGlazedJars() {
        List<RegistryObject<JarBlock>> list = new ArrayList<>();
        for (DyeColor color : DyeColor.values()) {
            list.add(HELPER.register(color.getName() + "_glazed_jar", () -> new JarBlock(Block.Properties.of(Material.STONE, color).requiresCorrectToolForDrops().strength(1.4F).noOcclusion())));
        }
        return list;
    }

    private static List<RegistryObject<BigJarBlock>> registerBigColoredGlazedJars() {
        List<RegistryObject<BigJarBlock>> list = new ArrayList<>();

        for (DyeColor color : DyeColor.values()) {
            list.add(HELPER.register("big_" + color.getName() + "_glazed_jar", () -> new BigJarBlock(Block.Properties.of(Material.STONE, color).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()), (block) -> new BigJarBlockItem(block.get(), new Item.Properties().tab(HELPER.getDefaultCreativeTab())), ValhelsiaRenderType.CUTOUT));
            HELPER.registerNoItem("big_" + color.getName() + "_glazed_jar_top", () -> new BigJarTopBlock(Block.Properties.of(Material.STONE, color).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()), ValhelsiaRenderType.CUTOUT);
        }
        return list;
    }

    private static List<RegistryObject<SleepingBagBlock>> registerSleepingBags() {
        return Arrays.stream(DyeColor.values())
                .map(color -> HELPER.register(color.getName() + "_sleeping_bag",
                        () -> new SleepingBagBlock(Block.Properties.of(Material.WOOL, color).strength(0.2F).noOcclusion()),
                        registryObject -> new BedItem(registryObject.get(), new Item.Properties().tab(HELPER.getDefaultCreativeTab()))))
                .toList();
    }
}