package com.stal111.valhelsia_structures.core.init;

import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.common.block.*;
import com.stal111.valhelsia_structures.common.block.properties.BlockProperties;
import com.stal111.valhelsia_structures.common.item.BigJarBlockItem;
import com.stal111.valhelsia_structures.common.item.DyeableBlockItem;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.valhelsia.valhelsia_core.client.util.ValhelsiaRenderType;
import net.valhelsia.valhelsia_core.common.block.StrippableRotatedPillarBlock;
import net.valhelsia.valhelsia_core.core.registry.RegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.block.BlockRegistryHelper;
import net.valhelsia.valhelsia_core.core.registry.helper.block.BlockRegistryObject;
import net.valhelsia.valhelsia_core.core.registry.helper.block.RenderType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Blocks <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModBlocks
 *
 * @author Valhelsia Team
 * @version 1.19 - 0.2.0
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks implements RegistryClass {

    public static final BlockRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getBlockHelper();

    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<SpecialSpawnerBlock> SPECIAL_SPAWNER = HELPER.create("special_spawner", () -> new SpecialSpawnerBlock(Block.Properties.copy(Blocks.SPAWNER).strength(-1.0F, 3600000.0F).noLootTable())).withItem();
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<BrazierBlock> BRAZIER = HELPER.create("brazier", () -> new BrazierBlock(true, 1, Block.Properties.copy(Blocks.IRON_BARS).noOcclusion().lightLevel(state -> state.getValue(BrazierBlock.LIT) ? 15 : 0))).withItem();
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<BrazierBlock> SOUL_BRAZIER = HELPER.create("soul_brazier", () -> new BrazierBlock(false, 2, Block.Properties.copy(Blocks.IRON_BARS).noOcclusion().lightLevel(state -> state.getValue(BrazierBlock.LIT) ? 11 : 0))).withItem();

    // Posts
    public static final BlockRegistryObject<PostBlock> OAK_POST = HELPER.create("oak_post", () -> new PostBlock(() -> Blocks.OAK_LOG)).withItem();
    public static final BlockRegistryObject<PostBlock> SPRUCE_POST = HELPER.create("spruce_post", () -> new PostBlock(() -> Blocks.SPRUCE_LOG)).withItem();
    public static final BlockRegistryObject<PostBlock> BIRCH_POST = HELPER.create("birch_post", () -> new PostBlock(() -> Blocks.BIRCH_LOG)).withItem();
    public static final BlockRegistryObject<PostBlock> JUNGLE_POST = HELPER.create("jungle_post", () -> new PostBlock(() -> Blocks.JUNGLE_LOG)).withItem();
    public static final BlockRegistryObject<PostBlock> ACACIA_POST = HELPER.create("acacia_post", () -> new PostBlock(() -> Blocks.ACACIA_LOG)).withItem();
    public static final BlockRegistryObject<PostBlock> DARK_OAK_POST = HELPER.create("dark_oak_post", () -> new PostBlock(() -> Blocks.DARK_OAK_LOG)).withItem();
    public static final BlockRegistryObject<PostBlock> WARPED_POST = HELPER.create("warped_post", () -> new PostBlock(() -> Blocks.WARPED_STEM)).withItem();
    public static final BlockRegistryObject<PostBlock> CRIMSON_POST = HELPER.create("crimson_post", () -> new PostBlock(() -> Blocks.CRIMSON_STEM)).withItem();
    public static final BlockRegistryObject<PostBlock> LAPIDIFIED_JUNGLE_POST = HELPER.create("lapidified_jungle_post", () -> new PostBlock(new ResourceLocation(ValhelsiaStructures.MOD_ID, "lapidified_jungle_log"), BlockProperties.LAPIDIFIED_JUNGLE_LOG)).withItem();

    public static final BlockRegistryObject<PostBlock> STRIPPED_OAK_POST = HELPER.create("stripped_oak_post", () -> new PostBlock(() -> Blocks.STRIPPED_OAK_LOG)).withItem();
    public static final BlockRegistryObject<PostBlock> STRIPPED_SPRUCE_POST = HELPER.create("stripped_spruce_post", () -> new PostBlock(() -> Blocks.STRIPPED_SPRUCE_LOG)).withItem();
    public static final BlockRegistryObject<PostBlock> STRIPPED_BIRCH_POST = HELPER.create("stripped_birch_post", () -> new PostBlock(() -> Blocks.STRIPPED_BIRCH_LOG)).withItem();
    public static final BlockRegistryObject<PostBlock> STRIPPED_JUNGLE_POST = HELPER.create("stripped_jungle_post", () -> new PostBlock(() -> Blocks.STRIPPED_JUNGLE_LOG)).withItem();
    public static final BlockRegistryObject<PostBlock> STRIPPED_ACACIA_POST = HELPER.create("stripped_acacia_post", () -> new PostBlock(() -> Blocks.STRIPPED_ACACIA_LOG)).withItem();
    public static final BlockRegistryObject<PostBlock> STRIPPED_DARK_OAK_POST = HELPER.create("stripped_dark_oak_post", () -> new PostBlock(() -> Blocks.STRIPPED_DARK_OAK_LOG)).withItem();
    public static final BlockRegistryObject<PostBlock> STRIPPED_WARPED_POST = HELPER.create("stripped_warped_post", () -> new PostBlock(() -> Blocks.STRIPPED_WARPED_STEM)).withItem();
    public static final BlockRegistryObject<PostBlock> STRIPPED_CRIMSON_POST = HELPER.create("stripped_crimson_post", () -> new PostBlock(() -> Blocks.STRIPPED_CRIMSON_STEM)).withItem();
    public static final BlockRegistryObject<PostBlock> STRIPPED_LAPIDIFIED_JUNGLE_POST = HELPER.create("stripped_lapidified_jungle_post", () -> new PostBlock(new ResourceLocation(ValhelsiaStructures.MOD_ID, "lapidified_jungle_log"), BlockProperties.LAPIDIFIED_JUNGLE_LOG)).withItem();

    //Cut Posts
    public static final BlockRegistryObject<CutPostBlock> CUT_OAK_POST = HELPER.create("cut_oak_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.WOOD, MaterialColor.PODZOL))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_SPRUCE_POST = HELPER.create("cut_spruce_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.PODZOL, MaterialColor.COLOR_BROWN))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_BIRCH_POST = HELPER.create("cut_birch_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.SAND, MaterialColor.QUARTZ))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_JUNGLE_POST = HELPER.create("cut_jungle_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.DIRT, MaterialColor.PODZOL))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_ACACIA_POST = HELPER.create("cut_acacia_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.COLOR_ORANGE, MaterialColor.STONE))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_DARK_OAK_POST = HELPER.create("cut_dark_oak_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.COLOR_BROWN, MaterialColor.COLOR_BROWN))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_WARPED_POST = HELPER.create("cut_warped_post", () -> new CutPostBlock(BlockProperties.createCutNetherPostBlock(MaterialColor.WARPED_STEM))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_CRIMSON_POST = HELPER.create("cut_crimson_post", () -> new CutPostBlock(BlockProperties.createCutNetherPostBlock(MaterialColor.CRIMSON_STEM))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_LAPIDIFIED_JUNGLE_POST = HELPER.create("cut_lapidified_jungle_post", () -> new CutPostBlock(BlockProperties.LAPIDIFIED_JUNGLE_LOG.noOcclusion())).withItem();

    public static final BlockRegistryObject<CutPostBlock> CUT_STRIPPED_OAK_POST = HELPER.create("cut_stripped_oak_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.WOOD, MaterialColor.PODZOL))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_STRIPPED_SPRUCE_POST = HELPER.create("cut_stripped_spruce_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.PODZOL, MaterialColor.COLOR_BROWN))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_STRIPPED_BIRCH_POST = HELPER.create("cut_stripped_birch_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.SAND, MaterialColor.QUARTZ))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_STRIPPED_JUNGLE_POST = HELPER.create("cut_stripped_jungle_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.DIRT, MaterialColor.PODZOL))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_STRIPPED_ACACIA_POST = HELPER.create("cut_stripped_acacia_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.COLOR_ORANGE, MaterialColor.STONE))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_STRIPPED_DARK_OAK_POST = HELPER.create("cut_stripped_dark_oak_post", () -> new CutPostBlock(BlockProperties.createCutPostBlock(MaterialColor.COLOR_BROWN, MaterialColor.COLOR_BROWN))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_STRIPPED_WARPED_POST = HELPER.create("cut_stripped_warped_post", () -> new CutPostBlock(BlockProperties.createCutNetherPostBlock(MaterialColor.WARPED_STEM))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_STRIPPED_CRIMSON_POST = HELPER.create("cut_stripped_crimson_post", () -> new CutPostBlock(BlockProperties.createCutNetherPostBlock(MaterialColor.CRIMSON_STEM))).withItem();
    public static final BlockRegistryObject<CutPostBlock> CUT_STRIPPED_LAPIDIFIED_JUNGLE_POST = HELPER.create("cut_stripped_lapidified_jungle_post", () -> new CutPostBlock(BlockProperties.LAPIDIFIED_JUNGLE_LOG.noOcclusion())).withItem();

    // Bundled Posts
    public static final BlockRegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_OAK_POSTS = HELPER.createStrippedLogBlock("bundled_stripped_oak_posts", MaterialColor.WOOD, MaterialColor.WOOD);
    public static final BlockRegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_SPRUCE_POSTS = HELPER.createStrippedLogBlock("bundled_stripped_spruce_posts", MaterialColor.PODZOL, MaterialColor.PODZOL);
    public static final BlockRegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_BIRCH_POSTS = HELPER.createStrippedLogBlock("bundled_stripped_birch_posts", MaterialColor.SAND, MaterialColor.SAND);
    public static final BlockRegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_JUNGLE_POSTS = HELPER.createStrippedLogBlock("bundled_stripped_jungle_posts", MaterialColor.DIRT, MaterialColor.DIRT);
    public static final BlockRegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_ACACIA_POSTS = HELPER.createStrippedLogBlock("bundled_stripped_acacia_posts", MaterialColor.COLOR_ORANGE, MaterialColor.COLOR_ORANGE);
    public static final BlockRegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_DARK_OAK_POSTS = HELPER.createStrippedLogBlock("bundled_stripped_dark_oak_posts", MaterialColor.COLOR_BROWN, MaterialColor.COLOR_BROWN);
    public static final BlockRegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_CRIMSON_POSTS = HELPER.createStrippedLogBlock("bundled_stripped_crimson_posts", MaterialColor.CRIMSON_STEM, MaterialColor.CRIMSON_STEM);
    public static final BlockRegistryObject<RotatedPillarBlock> BUNDLED_STRIPPED_WARPED_POSTS = HELPER.createStrippedLogBlock("bundled_stripped_warped_posts", MaterialColor.WARPED_STEM, MaterialColor.WARPED_STEM);

    public static final BlockRegistryObject<StrippableRotatedPillarBlock> BUNDLED_OAK_POSTS = HELPER.createLogBlock("bundled_oak_posts", BUNDLED_STRIPPED_OAK_POSTS, MaterialColor.WOOD, MaterialColor.PODZOL);
    public static final BlockRegistryObject<StrippableRotatedPillarBlock> BUNDLED_SPRUCE_POSTS = HELPER.createLogBlock("bundled_spruce_posts", BUNDLED_STRIPPED_SPRUCE_POSTS, MaterialColor.PODZOL, MaterialColor.COLOR_BROWN);
    public static final BlockRegistryObject<StrippableRotatedPillarBlock> BUNDLED_BIRCH_POSTS = HELPER.createLogBlock("bundled_birch_posts", BUNDLED_STRIPPED_BIRCH_POSTS, MaterialColor.SAND, MaterialColor.QUARTZ);
    public static final BlockRegistryObject<StrippableRotatedPillarBlock> BUNDLED_JUNGLE_POSTS = HELPER.createLogBlock("bundled_jungle_posts", BUNDLED_STRIPPED_JUNGLE_POSTS, MaterialColor.DIRT, MaterialColor.PODZOL);
    public static final BlockRegistryObject<StrippableRotatedPillarBlock> BUNDLED_ACACIA_POSTS = HELPER.createLogBlock("bundled_acacia_posts", BUNDLED_STRIPPED_ACACIA_POSTS, MaterialColor.COLOR_ORANGE, MaterialColor.STONE);
    public static final BlockRegistryObject<StrippableRotatedPillarBlock> BUNDLED_DARK_OAK_POSTS = HELPER.createLogBlock("bundled_dark_oak_posts", BUNDLED_STRIPPED_DARK_OAK_POSTS, MaterialColor.COLOR_BROWN, MaterialColor.COLOR_BROWN);
    public static final BlockRegistryObject<StrippableRotatedPillarBlock> BUNDLED_CRIMSON_POSTS = HELPER.createLogBlock("bundled_crimson_posts", BUNDLED_STRIPPED_CRIMSON_POSTS, MaterialColor.CRIMSON_STEM, MaterialColor.CRIMSON_STEM);
    public static final BlockRegistryObject<StrippableRotatedPillarBlock> BUNDLED_WARPED_POSTS = HELPER.createLogBlock("bundled_warped_posts", BUNDLED_STRIPPED_WARPED_POSTS, MaterialColor.WARPED_STEM, MaterialColor.WARPED_STEM);

    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<GlassBlock> METAL_FRAMED_GLASS = HELPER.create("metal_framed_glass", () -> new GlassBlock(Block.Properties.copy(Blocks.GLASS))).withItem();
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<IronBarsBlock> METAL_FRAMED_GLASS_PANE = HELPER.create("metal_framed_glass_pane", () -> new IronBarsBlock(Block.Properties.copy(Blocks.GLASS_PANE))).withItem();
    @RenderType(ValhelsiaRenderType.TRANSLUCENT)
    public static final Map<DyeColor, BlockRegistryObject<StainedGlassBlock>> COLORED_METAL_FRAMED_GLASS = registerColoredMetalFramedGlass();
    @RenderType(ValhelsiaRenderType.TRANSLUCENT)
    public static final Map<DyeColor, BlockRegistryObject<StainedGlassPaneBlock>> COLORED_METAL_FRAMED_GLASS_PANES = registerColoredMetalFramedGlassPanes();

    public static final BlockRegistryObject<IronBarsBlock> PAPER_WALL = HELPER.create("paper_wall", () -> new IronBarsBlock(Block.Properties.of(Material.DECORATION).strength(0.3F).sound(SoundType.WOOL).noOcclusion())).withItem();
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<HangingVinesBodyBlock> HANGING_VINES_BODY = HELPER.create("hanging_vines_body", () -> new HangingVinesBodyBlock(Block.Properties.of(Material.REPLACEABLE_PLANT).noCollission().strength(0.2F).sound(SoundType.VINE))).withItem();
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<HangingVinesBlock> HANGING_VINES = HELPER.create("hanging_vines", () -> new HangingVinesBlock(Block.Properties.of(Material.REPLACEABLE_PLANT).randomTicks().noCollission().strength(0.2F).sound(SoundType.VINE))).withItem();
    //public static final RegistryObject<JungleHeadBlock> JUNGLE_HEAD = HELPER.register("jungle_head", new JungleHeadBlock(Block.Properties.from(Blocks.COBBLESTONE).notSolid()), ValhelsiaRenderType.CUTOUT);
    public static final BlockRegistryObject<JarBlock> GLAZED_JAR = HELPER.create("glazed_jar", () -> new JarBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion())).withItem();
    public static final BlockRegistryObject<JarBlock> CRACKED_GLAZED_JAR = HELPER.create("cracked_glazed_jar", () -> new JarBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.0F).noOcclusion())).withItem();
    public static final List<BlockRegistryObject<JarBlock>> COLORED_GLAZED_JARS = registerColoredGlazedJars();
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<BigJarBlock> BIG_GLAZED_JAR = HELPER.create("big_glazed_jar", () -> new BigJarBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion())).withItem(registryObject -> new BigJarBlockItem(registryObject.get(), new Item.Properties()));
    public static final BlockRegistryObject<BigJarTopBlock> BIG_GLAZED_JAR_TOP = HELPER.create("big_glazed_jar_top", () -> new BigJarTopBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()));

    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<BigJarBlock> CRACKED_BIG_GLAZED_JAR = HELPER.create("cracked_big_glazed_jar", () -> new BigJarBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion())).withItem(registryObject -> new BigJarBlockItem(registryObject.get(), new Item.Properties()));
    public static final BlockRegistryObject<BigJarTopBlock> CRACKED_BIG_GLAZED_JAR_TOP = HELPER.create("cracked_big_glazed_jar_top", () -> new BigJarTopBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()));

    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final List<BlockRegistryObject<BigJarBlock>> BIG_COLORED_GLAZED_JARS = registerBigColoredGlazedJars();
    public static final BlockRegistryObject<RotatedPillarBlock> LAPIDIFIED_JUNGLE_LOG = HELPER.create("lapidified_jungle_log", () -> new RotatedPillarBlock(BlockProperties.LAPIDIFIED_JUNGLE_LOG)).withItem();
    public static final BlockRegistryObject<RotatedPillarBlock> LAPIDIFIED_JUNGLE_WOOD = HELPER.create("lapidified_jungle_wood", () -> new RotatedPillarBlock(BlockProperties.LAPIDIFIED_JUNGLE_LOG)).withItem();
    public static final BlockRegistryObject<Block> LAPIDIFIED_JUNGLE_PLANKS = HELPER.create("lapidified_jungle_planks", () -> new Block(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS)).withItem();
    public static final BlockRegistryObject<SlabBlock> LAPIDIFIED_JUNGLE_SLAB = HELPER.create("lapidified_jungle_slab", () -> new SlabBlock(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS)).withItem();
    public static final BlockRegistryObject<StairBlock> LAPIDIFIED_JUNGLE_STAIRS = HELPER.create("lapidified_jungle_stairs", () -> new StairBlock(() -> ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get().defaultBlockState(), BlockProperties.LAPIDIFIED_JUNGLE_PLANKS)).withItem();
    public static final BlockRegistryObject<PressurePlateBlock> LAPIDIFIED_JUNGLE_PRESSURE_PLATE = HELPER.create("lapidified_jungle_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockProperties.LAPIDIFIED_JUNGLE_PLANKS.strength(0.5F), SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON)).withItem();
    public static final BlockRegistryObject<ButtonBlock> LAPIDIFIED_JUNGLE_BUTTON = HELPER.create("lapidified_jungle_button", () -> new ButtonBlock(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS.strength(0.5F), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON)).withItem();
    public static final BlockRegistryObject<FenceBlock> LAPIDIFIED_JUNGLE_FENCE = HELPER.create("lapidified_jungle_fence", () -> new FenceBlock(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS)).withItem();
    public static final BlockRegistryObject<FenceGateBlock> LAPIDIFIED_JUNGLE_FENCE_GATE = HELPER.create("lapidified_jungle_fence_gate", () -> new FenceGateBlock(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS, SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN)).withItem();
    public static final BlockRegistryObject<ExplorersTentBlock> EXPLORERS_TENT = HELPER.create("explorers_tent", () -> new ExplorersTentBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_WOOL).noOcclusion())).withItem(registryObject -> new DyeableBlockItem(registryObject.get(), new Item.Properties()));
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<BushBlock> HIBISCUS = HELPER.create("hibiscus", () -> new BushBlock(Block.Properties.copy(Blocks.POPPY))).withItem();
    public static final BlockRegistryObject<GiantFernBlock> GIANT_FERN = HELPER.create("giant_fern", () -> new GiantFernBlock(Block.Properties.copy(Blocks.POPPY))).withItem();
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<DousedTorchBlock> DOUSED_TORCH = HELPER.create("doused_torch", () -> new DousedTorchBlock((TorchBlock) Blocks.TORCH, Block.Properties.of(Material.DECORATION).noCollission().instabreak()));
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<DousedWallTorchBlock> DOUSED_WALL_TORCH = HELPER.create("doused_wall_torch", () -> new DousedWallTorchBlock((TorchBlock) Blocks.WALL_TORCH, Block.Properties.of(Material.DECORATION).noCollission().instabreak()));
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<DousedTorchBlock> DOUSED_SOUL_TORCH = HELPER.create("doused_soul_torch", () -> new DousedTorchBlock((TorchBlock) Blocks.SOUL_TORCH, Block.Properties.of(Material.DECORATION).noCollission().instabreak()));
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<DousedWallTorchBlock> DOUSED_SOUL_WALL_TORCH = HELPER.create("doused_soul_wall_torch", () -> new DousedWallTorchBlock((TorchBlock) Blocks.SOUL_WALL_TORCH, Block.Properties.of(Material.DECORATION).noCollission().instabreak()));
    public static final BlockRegistryObject<DungeonDoorBlock> DUNGEON_DOOR = HELPER.create("dungeon_door", () -> new DungeonDoorBlock(Block.Properties.of(Material.METAL).strength(50.0F, 100.0F).requiresCorrectToolForDrops().noOcclusion())).withItem();
    public static final BlockRegistryObject<DungeonDoorLeafBlock> DUNGEON_DOOR_LEAF = HELPER.create("dungeon_door_leaf", () -> new DungeonDoorLeafBlock(Block.Properties.of(Material.METAL).strength(50.0F, 100.0F).requiresCorrectToolForDrops().noOcclusion().lootFrom(ModBlocks.DUNGEON_DOOR)));
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<BonePileBlock> BONE_PILE = HELPER.create("bone_pile", () -> new BonePileBlock(Block.Properties.copy(Blocks.BONE_BLOCK).noOcclusion())).withItem();
    public static final BlockRegistryObject<Block> BONE_PILE_BLOCK = HELPER.create("bone_pile_block", () -> new Block(Block.Properties.copy(Blocks.BONE_BLOCK))).withItem();
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<UnlitLanternBlock> UNLIT_LANTERN = HELPER.create("unlit_lantern", () -> new UnlitLanternBlock(() -> Blocks.LANTERN, Block.Properties.copy(Blocks.LANTERN).lightLevel(value -> 0))).withItem();
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<UnlitLanternBlock> UNLIT_SOUL_LANTERN = HELPER.create("unlit_soul_lantern", () -> new UnlitLanternBlock(() -> Blocks.SOUL_LANTERN, Block.Properties.copy(Blocks.SOUL_LANTERN).lightLevel(value -> 0))).withItem();

    //Sleeping Bags
    public static final Map<DyeColor, BlockRegistryObject<SleepingBagBlock>> SLEEPING_BAGS = registerSleepingBags();

    // Workarounds for structures:

    // stone that can't be replaced during later generation steps
    public static final BlockRegistryObject<Block> STONE = HELPER.create("stone", () -> new ValhelsiaStoneBlock(() -> Blocks.STONE, Block.Properties.copy(Blocks.STONE).lootFrom(() -> Blocks.STONE)));
    public static final BlockRegistryObject<Block> GRANITE = HELPER.create("granite", () -> new ValhelsiaStoneBlock(() -> Blocks.GRANITE, Block.Properties.copy(Blocks.GRANITE).lootFrom(() -> Blocks.GRANITE)));
    public static final BlockRegistryObject<Block> DIORITE = HELPER.create("diorite", () -> new ValhelsiaStoneBlock(() -> Blocks.DIORITE, Block.Properties.copy(Blocks.DIORITE).lootFrom(() -> Blocks.DIORITE)));
    public static final BlockRegistryObject<Block> ANDESITE = HELPER.create("andesite", () -> new ValhelsiaStoneBlock(() -> Blocks.ANDESITE, Block.Properties.copy(Blocks.ANDESITE).lootFrom(() -> Blocks.ANDESITE)));
    // grass block on which features cannot generate
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<Block> GRASS_BLOCK = HELPER.create("grass_block", () -> new ValhelsiaGrassBlock(Block.Properties.copy(Blocks.GRASS_BLOCK).lootFrom(() -> Blocks.GRASS_BLOCK)));
    // dirt that wont transform into grass blocks
    public static final BlockRegistryObject<Block> DIRT = HELPER.create("dirt", () -> new ValhelsiaStoneBlock(() -> Blocks.DIRT, Block.Properties.copy(Blocks.DIRT).lootFrom(() -> Blocks.DIRT)));
    public static final BlockRegistryObject<Block> COARSE_DIRT = HELPER.create("coarse_dirt", () -> new ValhelsiaStoneBlock(() -> Blocks.COARSE_DIRT, Block.Properties.copy(Blocks.COARSE_DIRT).lootFrom(() -> Blocks.COARSE_DIRT)));

    private static List<BlockRegistryObject<JarBlock>> registerColoredGlazedJars() {
        List<BlockRegistryObject<JarBlock>> list = new ArrayList<>();
        for (DyeColor color : DyeColor.values()) {
            list.add(HELPER.create(color.getName() + "_glazed_jar", () -> new JarBlock(Block.Properties.of(Material.STONE, color).requiresCorrectToolForDrops().strength(1.4F).noOcclusion())).withItem());
        }
        return list;
    }

    private static List<BlockRegistryObject<BigJarBlock>> registerBigColoredGlazedJars() {
        List<BlockRegistryObject<BigJarBlock>> list = new ArrayList<>();

        for (DyeColor color : DyeColor.values()) {
            list.add(HELPER.create("big_" + color.getName() + "_glazed_jar", () -> new BigJarBlock(Block.Properties.of(Material.STONE, color).requiresCorrectToolForDrops().strength(1.4F).noOcclusion())).withItem(registryObject -> new BigJarBlockItem(registryObject.get(), new Item.Properties())));
            HELPER.create("big_" + color.getName() + "_glazed_jar_top", () -> new BigJarTopBlock(Block.Properties.of(Material.STONE, color).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()));
        }
        return list;
    }

    private static Map<DyeColor, BlockRegistryObject<SleepingBagBlock>> registerSleepingBags() {
        return Arrays.stream(DyeColor.values())
                .map(color -> Pair.of(color, HELPER.create(color.getName() + "_sleeping_bag",
                        () -> new SleepingBagBlock(Block.Properties.of(Material.WOOL, color).strength(0.2F).noOcclusion().sound(SoundType.WOOL)))
                        .withItem(registryObject -> new BedItem(registryObject.get(), new Item.Properties()))))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }

    private static Map<DyeColor, BlockRegistryObject<StainedGlassBlock>> registerColoredMetalFramedGlass() {
        return Arrays.stream(DyeColor.values())
                .map(color -> Pair.of(color, HELPER.create(color.getName() + "_metal_framed_glass",
                        () -> new StainedGlassBlock(color, Block.Properties.copy(Blocks.RED_STAINED_GLASS))).withItem()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }

    private static Map<DyeColor, BlockRegistryObject<StainedGlassPaneBlock>> registerColoredMetalFramedGlassPanes() {
        return Arrays.stream(DyeColor.values())
                .map(color -> Pair.of(color, HELPER.create(color.getName() + "_metal_framed_glass_pane",
                        () -> new StainedGlassPaneBlock(color, Block.Properties.copy(Blocks.RED_STAINED_GLASS_PANE))).withItem()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }
}