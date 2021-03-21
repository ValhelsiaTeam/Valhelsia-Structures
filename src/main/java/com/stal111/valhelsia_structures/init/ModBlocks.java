package com.stal111.valhelsia_structures.init;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.block.*;
import com.stal111.valhelsia_structures.block.properties.BlockProperties;
import com.stal111.valhelsia_structures.item.block.DyeableBlockItem;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.valhelsia.valhelsia_core.registry.block.BlockRegistryHelper;
import net.valhelsia.valhelsia_core.util.ValhelsiaRenderType;

import java.util.ArrayList;
import java.util.List;

/**
 * Blocks
 * Valhelsia Structures - com.stal111.valhelsia_structures.init.ModBlocks
 *
 * @author Valhelsia Team
 * @version 16.1.0
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {

    public static final BlockRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getBlockHelper();

    public static final RegistryObject<SpecialSpawnerBlock> SPECIAL_SPAWNER = HELPER.register("special_spawner", new SpecialSpawnerBlock(Block.Properties.from(Blocks.SPAWNER).hardnessAndResistance(-1.0F, 3600000.0F).noDrops()), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<BrazierBlock> BRAZIER = HELPER.register("brazier", new BrazierBlock(true, 1, Block.Properties.from(Blocks.IRON_BARS).notSolid().setLightLevel(state -> state.get(BrazierBlock.LIT) ? 15 : 0)), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<BrazierBlock> SOUL_BRAZIER = HELPER.register("soul_brazier", new BrazierBlock(false, 2, Block.Properties.from(Blocks.IRON_BARS).notSolid().setLightLevel(state -> state.get(BrazierBlock.LIT) ? 11 : 0)), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<PostBlock> OAK_POST = HELPER.register("oak_post", new PostBlock(() -> Blocks.OAK_LOG));
    public static final RegistryObject<PostBlock> SPRUCE_POST = HELPER.register("spruce_post", new PostBlock(() -> Blocks.SPRUCE_LOG));
    public static final RegistryObject<PostBlock> BIRCH_POST = HELPER.register("birch_post", new PostBlock(() -> Blocks.BIRCH_LOG));
    public static final RegistryObject<PostBlock> JUNGLE_POST = HELPER.register("jungle_post", new PostBlock(() -> Blocks.JUNGLE_LOG));
    public static final RegistryObject<PostBlock> ACACIA_POST = HELPER.register("acacia_post", new PostBlock(() -> Blocks.ACACIA_LOG));
    public static final RegistryObject<PostBlock> DARK_OAK_POST = HELPER.register("dark_oak_post", new PostBlock(() -> Blocks.DARK_OAK_LOG));
    public static final RegistryObject<PostBlock> WARPED_POST = HELPER.register("warped_post", new PostBlock(() -> Blocks.WARPED_STEM));
    public static final RegistryObject<PostBlock> CRIMSON_POST = HELPER.register("crimson_post", new PostBlock(() -> Blocks.CRIMSON_STEM));
    public static final RegistryObject<PostBlock> LAPIDIFIED_JUNGLE_POST = HELPER.register("lapidified_jungle_post", new PostBlock(new ResourceLocation(ValhelsiaStructures.MOD_ID, "lapidified_jungle_log"), BlockProperties.LAPIDIFIED_JUNGLE_LOG));
    public static final RegistryObject<GlassBlock> METAL_FRAMED_GLASS = HELPER.register("metal_framed_glass", new GlassBlock(Block.Properties.from(Blocks.GLASS)), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<PaneBlock> METAL_FRAMED_GLASS_PANE = HELPER.register("metal_framed_glass_pane", new PaneBlock(Block.Properties.from(Blocks.GLASS_PANE)), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<PaneBlock> PAPER_WALL = HELPER.register("paper_wall", new PaneBlock(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.3F).sound(SoundType.CLOTH).notSolid()));
    public static final RegistryObject<HangingVinesBodyBlock> HANGING_VINES_BODY = HELPER.registerNoItem("hanging_vines_body", new HangingVinesBodyBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0.2F).sound(SoundType.VINE)), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<HangingVinesBlock> HANGING_VINES = HELPER.register("hanging_vines", new HangingVinesBlock(Block.Properties.create(Material.TALL_PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.2F).sound(SoundType.VINE)), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<JungleHeadBlock> JUNGLE_HEAD = HELPER.register("jungle_head", new JungleHeadBlock(Block.Properties.from(Blocks.COBBLESTONE).notSolid()), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<JarBlock> GLAZED_JAR = HELPER.register("glazed_jar", new JarBlock(Block.Properties.create(Material.ROCK, MaterialColor.BROWN).setRequiresTool().hardnessAndResistance(1.4F).notSolid()));
    public static final RegistryObject<JarBlock> CRACKED_GLAZED_JAR = HELPER.register("cracked_glazed_jar", new JarBlock(Block.Properties.create(Material.ROCK, MaterialColor.BROWN).setRequiresTool().hardnessAndResistance(1.0F).notSolid()));
    public static final List<RegistryObject<JarBlock>> COLORED_GLAZED_JARS = registerColoredGlazedJars();
    public static final RegistryObject<RotatedPillarBlock> LAPIDIFIED_JUNGLE_LOG = HELPER.register("lapidified_jungle_log", new RotatedPillarBlock(BlockProperties.LAPIDIFIED_JUNGLE_LOG));
    public static final RegistryObject<RotatedPillarBlock> LAPIDIFIED_JUNGLE_WOOD = HELPER.register("lapidified_jungle_wood", new RotatedPillarBlock(BlockProperties.LAPIDIFIED_JUNGLE_LOG));
    public static final RegistryObject<Block> LAPIDIFIED_JUNGLE_PLANKS = HELPER.register("lapidified_jungle_planks", new Block(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS));
    public static final RegistryObject<SlabBlock> LAPIDIFIED_JUNGLE_SLAB = HELPER.register("lapidified_jungle_slab", new SlabBlock(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS));
    public static final RegistryObject<StairsBlock> LAPIDIFIED_JUNGLE_STAIRS = HELPER.register("lapidified_jungle_stairs", new StairsBlock(() -> ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get().getDefaultState(), BlockProperties.LAPIDIFIED_JUNGLE_PLANKS));
    public static final RegistryObject<PressurePlateBlock> LAPIDIFIED_JUNGLE_PRESSURE_PLATE = HELPER.register("lapidified_jungle_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockProperties.LAPIDIFIED_JUNGLE_PLANKS.hardnessAndResistance(0.5F)));
    public static final RegistryObject<WoodButtonBlock> LAPIDIFIED_JUNGLE_BUTTON = HELPER.register("lapidified_jungle_button", new WoodButtonBlock(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS.hardnessAndResistance(0.5F)));
    public static final RegistryObject<FenceBlock> LAPIDIFIED_JUNGLE_FENCE = HELPER.register("lapidified_jungle_fence", new FenceBlock(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS));
    public static final RegistryObject<FenceGateBlock> LAPIDIFIED_JUNGLE_FENCE_GATE = HELPER.register("lapidified_jungle_fence_gate", new FenceGateBlock(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS));
    private static final ExplorersTentBlock EXPLORERS_TENT_BLOCK = new ExplorersTentBlock(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS.doesNotBlockMovement());
    public static final RegistryObject<ExplorersTentBlock> EXPLORERS_TENT = HELPER.register("explorers_tent", EXPLORERS_TENT_BLOCK, new DyeableBlockItem(EXPLORERS_TENT_BLOCK, new Item.Properties().group(HELPER.getDefaultGroup())));
    public static final RegistryObject<BushBlock> HIBISCUS = HELPER.register("hibiscus", new BushBlock(AbstractBlock.Properties.from(Blocks.POPPY)), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<GiantFernBlock> GIANT_FERN = HELPER.register("giant_fern", new GiantFernBlock(AbstractBlock.Properties.from(Blocks.POPPY)));
    public static final RegistryObject<DousedTorchBlock> DOUSED_TORCH = HELPER.registerNoItem("doused_torch", new DousedTorchBlock((TorchBlock) Blocks.TORCH, AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance()), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<DousedWallTorchBlock> DOUSED_WALL_TORCH = HELPER.registerNoItem("doused_wall_torch", new DousedWallTorchBlock((TorchBlock) Blocks.WALL_TORCH, AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance()), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<DousedTorchBlock> DOUSED_SOUL_TORCH = HELPER.registerNoItem("doused_soul_torch", new DousedTorchBlock((TorchBlock) Blocks.SOUL_TORCH, AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance()), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<DousedWallTorchBlock> DOUSED_SOUL_WALL_TORCH = HELPER.registerNoItem("doused_soul_wall_torch", new DousedWallTorchBlock((TorchBlock) Blocks.SOUL_WALL_TORCH, AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance()), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<DungeonDoorBlock> DUNGEON_DOOR = HELPER.register("dungeon_door", new DungeonDoorBlock(AbstractBlock.Properties.create(Material.IRON).notSolid()));
    public static final RegistryObject<DungeonDoorLeafBlock> DUNGEON_DOOR_LEAF = HELPER.registerNoItem("dungeon_door_leaf", new DungeonDoorLeafBlock(AbstractBlock.Properties.create(Material.IRON).notSolid()));
    public static final RegistryObject<BonePileBlock> BONE_PILE = HELPER.register("bone_pile", new BonePileBlock(AbstractBlock.Properties.from(Blocks.BONE_BLOCK).notSolid()), ValhelsiaRenderType.CUTOUT);
    public static final RegistryObject<Block> BONE_PILE_BLOCK = HELPER.register("bone_pile_block", new Block(AbstractBlock.Properties.from(Blocks.BONE_BLOCK)));

    // Workarounds for structures:

    // stone that can't be replaced during later generation steps
    public static final RegistryObject<Block> STONE = HELPER.register("stone", new ValhelsiaStoneBlock(() -> Blocks.STONE, AbstractBlock.Properties.from(Blocks.STONE).lootFrom(Blocks.STONE)));
    public static final RegistryObject<Block> GRANITE = HELPER.register("granite", new ValhelsiaStoneBlock(() -> Blocks.GRANITE, AbstractBlock.Properties.from(Blocks.GRANITE).lootFrom(Blocks.GRANITE)));
    public static final RegistryObject<Block> DIORITE = HELPER.register("diorite", new ValhelsiaStoneBlock(() -> Blocks.DIORITE, AbstractBlock.Properties.from(Blocks.DIORITE).lootFrom(Blocks.DIORITE)));
    public static final RegistryObject<Block> ANDESITE = HELPER.register("andesite", new ValhelsiaStoneBlock(() -> Blocks.ANDESITE, AbstractBlock.Properties.from(Blocks.ANDESITE).lootFrom(Blocks.ANDESITE)));
    // grass block on which features cannot generate
    public static final RegistryObject<Block> GRASS_BLOCK = HELPER.register("grass_block", new ValhelsiaGrassBlock(Block.Properties.from(Blocks.GRASS_BLOCK).lootFrom(Blocks.GRASS_BLOCK)), ValhelsiaRenderType.CUTOUT);
    // dirt that wont transform into grass blocks
    public static final RegistryObject<Block> DIRT = HELPER.register("dirt", new ValhelsiaStoneBlock(() -> Blocks.DIRT, Block.Properties.from(Blocks.DIRT).lootFrom(Blocks.DIRT)));
    public static final RegistryObject<Block> COARSE_DIRT = HELPER.register("coarse_dirt", new ValhelsiaStoneBlock(() -> Blocks.COARSE_DIRT, Block.Properties.from(Blocks.COARSE_DIRT).lootFrom(Blocks.COARSE_DIRT)));

    private static List<RegistryObject<JarBlock>> registerColoredGlazedJars() {
        List<RegistryObject<JarBlock>> list = new ArrayList<>();
        for (DyeColor color : DyeColor.values()) {
            list.add(HELPER.register(color.getTranslationKey() + "_glazed_jar", new JarBlock(Block.Properties.create(Material.ROCK, color).setRequiresTool().hardnessAndResistance(1.4F).notSolid())));
        }
        return list;
    }
}
