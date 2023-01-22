package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.common.block.*;
import com.stal111.valhelsia_structures.common.block.properties.BlockProperties;
import com.stal111.valhelsia_structures.common.item.BigJarBlockItem;
import com.stal111.valhelsia_structures.common.item.DyeableBlockItem;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.ValhelsiaBlockProperties;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.StringRepresentable;
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
import net.valhelsia.valhelsia_core.core.registry.helper.block.*;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Blocks <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModBlocks
 *
 * @author Valhelsia Team
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks implements RegistryClass {

    public static final BlockRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getBlockHelper();

    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<SpecialSpawnerBlock> SPECIAL_SPAWNER = HELPER.create("special_spawner", () -> new SpecialSpawnerBlock(Block.Properties.copy(Blocks.SPAWNER).strength(-1.0F, 3600000.0F).noLootTable())).withItem();
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<BrazierBlock> BRAZIER = HELPER.create("brazier", () -> new BrazierBlock(true, 1, Block.Properties.copy(Blocks.IRON_BARS).noOcclusion().lightLevel(state -> state.getValue(BrazierBlock.LIT) ? 15 : 0)))
            .withItem().toolType(ToolType.PICKAXE);
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<BrazierBlock> SOUL_BRAZIER = HELPER.create("soul_brazier", () -> new BrazierBlock(false, 2, Block.Properties.copy(Blocks.IRON_BARS).noOcclusion().lightLevel(state -> state.getValue(BrazierBlock.LIT) ? 11 : 0)))
            .withItem().toolType(ToolType.PICKAXE);

    public static final BlockSet<WoodType, PostBlock> WOODEN_POSTS = HELPER.createSet(WoodType.class, "post",
            type -> new PostBlock(type.getDefaultProperties().noOcclusion()),
            registryObject -> registryObject.withItem().toolType(ToolType.AXE)
    );

    public static final BlockSet<WoodType, PostBlock> STRIPPED_WOODEN_POSTS = HELPER.createSet(WoodType.class, s -> "stripped_" + s + "_post",
            type -> new PostBlock(type.getDefaultProperties().noOcclusion()),
            registryObject -> registryObject.withItem().toolType(ToolType.AXE)
    );

    public static final BlockSet<WoodType, CutPostBlock> CUT_WOODEN_POSTS = HELPER.createSet(WoodType.class, s -> "cut_" + s + "_post",
            type -> new CutPostBlock(type.getDefaultProperties().color(type.getBarkColor()).noOcclusion()),
            registryObject -> registryObject.withItem().toolType(ToolType.AXE)
    );

    public static final BlockSet<WoodType, CutPostBlock> CUT_STRIPPED_WOODEN_POSTS = HELPER.createSet(WoodType.class, s -> "cut_stripped_" + s + "_post",
            type -> new CutPostBlock(type.getDefaultProperties().color(type.getBarkColor()).noOcclusion()),
            registryObject -> registryObject.withItem().toolType(ToolType.AXE)
    );

    public static final BlockSet<WoodType, RotatedPillarBlock> BUNDLED_STRIPPED_POSTS = HELPER.createSet(WoodType.class, s -> "bundled_stripped_" + s + "_posts", woodType -> new RotatedPillarBlock(((ValhelsiaBlockProperties) woodType.getDefaultProperties()).color(state -> {
        return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? woodType.getTopColor() : woodType.getBarkColor();
    })), registryObject -> registryObject.withItem().toolType(ToolType.AXE));
    public static final BlockSet<WoodType, RotatedPillarBlock> BUNDLED_POSTS = HELPER.createSet(WoodType.class, s -> "bundled_" + s + "_posts", woodType -> new StrippableRotatedPillarBlock(ModBlocks.BUNDLED_STRIPPED_POSTS.get(woodType), ((ValhelsiaBlockProperties) woodType.getDefaultProperties()).color(state -> {
        return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? woodType.getTopColor() : woodType.getBarkColor();
    })), registryObject -> registryObject.withItem().toolType(ToolType.AXE));

    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<GlassBlock> METAL_FRAMED_GLASS = HELPER.create("metal_framed_glass", () -> new GlassBlock(Block.Properties.copy(Blocks.GLASS))).withItem();
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<IronBarsBlock> METAL_FRAMED_GLASS_PANE = HELPER.create("metal_framed_glass_pane", () -> new IronBarsBlock(Block.Properties.copy(Blocks.GLASS_PANE))).withItem();
    @RenderType(ValhelsiaRenderType.TRANSLUCENT)
    public static final BlockSet<DyeColor, StainedGlassBlock> COLORED_METAL_FRAMED_GLASS = HELPER.createColorVariants("metal_framed_glass",
            color -> new StainedGlassBlock(color, Block.Properties.copy(Blocks.RED_STAINED_GLASS)),
            BlockRegistryObject::withItem
    );
    @RenderType(ValhelsiaRenderType.TRANSLUCENT)
    public static final BlockSet<DyeColor, StainedGlassPaneBlock> COLORED_METAL_FRAMED_GLASS_PANES = HELPER.createColorVariants("metal_framed_glass_pane",
            color -> new StainedGlassPaneBlock(color, Block.Properties.copy(Blocks.RED_STAINED_GLASS_PANE)),
            BlockRegistryObject::withItem
    );

    public static final BlockRegistryObject<IronBarsBlock> PAPER_WALL = HELPER.create("paper_wall", () -> new IronBarsBlock(Block.Properties.of(Material.DECORATION).strength(0.3F).sound(SoundType.WOOL).noOcclusion())).withItem();
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<HangingVinesBodyBlock> HANGING_VINES_BODY = HELPER.create("hanging_vines_body", () -> new HangingVinesBodyBlock(Block.Properties.of(Material.REPLACEABLE_PLANT).noCollission().strength(0.2F).sound(SoundType.VINE)));
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<HangingVinesBlock> HANGING_VINES = HELPER.create("hanging_vines", () -> new HangingVinesBlock(Block.Properties.of(Material.REPLACEABLE_PLANT).randomTicks().noCollission().strength(0.2F).sound(SoundType.VINE))).withItem();
    //public static final RegistryObject<JungleHeadBlock> JUNGLE_HEAD = HELPER.register("jungle_head", new JungleHeadBlock(Block.Properties.from(Blocks.COBBLESTONE).notSolid()), ValhelsiaRenderType.CUTOUT);
    public static final BlockRegistryObject<JarBlock> GLAZED_JAR = HELPER.create("glazed_jar", () -> new JarBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion())).withItem();
    public static final BlockRegistryObject<JarBlock> CRACKED_GLAZED_JAR = HELPER.create("cracked_glazed_jar", () -> new JarBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.0F).noOcclusion())).withItem();
    public static final BlockSet<DyeColor, JarBlock> COLORED_GLAZED_JARS = HELPER.createColorVariants("glazed_jar",
            color -> new JarBlock(Block.Properties.of(Material.STONE, color).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()),
            BlockRegistryObject::withItem
    );
    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<BigJarBlock> BIG_GLAZED_JAR = HELPER.create("big_glazed_jar", () -> new BigJarBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion())).withItem(registryObject -> new BigJarBlockItem(registryObject.get(), new Item.Properties()));
    public static final BlockRegistryObject<BigJarTopBlock> BIG_GLAZED_JAR_TOP = HELPER.create("big_glazed_jar_top", () -> new BigJarTopBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()));

    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final BlockRegistryObject<BigJarBlock> CRACKED_BIG_GLAZED_JAR = HELPER.create("cracked_big_glazed_jar", () -> new BigJarBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion())).withItem(registryObject -> new BigJarBlockItem(registryObject.get(), new Item.Properties()));
    public static final BlockRegistryObject<BigJarTopBlock> CRACKED_BIG_GLAZED_JAR_TOP = HELPER.create("cracked_big_glazed_jar_top", () -> new BigJarTopBlock(Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()));

    @RenderType(ValhelsiaRenderType.CUTOUT)
    public static final Map<DyeColor, BlockRegistryObject<BigJarBlock>> BIG_COLORED_GLAZED_JARS = HELPER.createColorVariants(color -> "big_" + color + "_glazed_jar",
            color -> new BigJarBlock(Block.Properties.of(Material.STONE, color).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()),
            blockRegistryObject -> blockRegistryObject.withItem(registryObject -> new BigJarBlockItem(registryObject.get(), new Item.Properties()))
    );
    public static final BlockRegistryObject<RotatedPillarBlock> LAPIDIFIED_JUNGLE_LOG = HELPER.create("lapidified_jungle_log", () -> new RotatedPillarBlock(BlockProperties.LAPIDIFIED_JUNGLE_LOG)).withItem();
    public static final BlockRegistryObject<RotatedPillarBlock> LAPIDIFIED_JUNGLE_WOOD = HELPER.create("lapidified_jungle_wood", () -> new RotatedPillarBlock(BlockProperties.LAPIDIFIED_JUNGLE_LOG)).withItem();
    public static final BlockRegistryObject<Block> LAPIDIFIED_JUNGLE_PLANKS = HELPER.create("lapidified_jungle_planks", () -> new Block(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS)).withItem();
    public static final BlockRegistryObject<StairBlock> LAPIDIFIED_JUNGLE_STAIRS = HELPER.create("lapidified_jungle_stairs", () -> new StairBlock(() -> ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get().defaultBlockState(), BlockProperties.LAPIDIFIED_JUNGLE_PLANKS)).withItem();
    public static final BlockRegistryObject<SlabBlock> LAPIDIFIED_JUNGLE_SLAB = HELPER.create("lapidified_jungle_slab", () -> new SlabBlock(BlockProperties.LAPIDIFIED_JUNGLE_PLANKS)).withItem();
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
    public static final BlockSet<DyeColor, SleepingBagBlock> SLEEPING_BAGS = HELPER.createColorVariants("sleeping_bag",
            color -> new SleepingBagBlock(Block.Properties.of(Material.WOOL, color).strength(0.2F).noOcclusion().sound(SoundType.WOOL)),
            blockRegistryObject -> blockRegistryObject.withItem(registryObject -> new BedItem(registryObject.get(), new Item.Properties()))
    );

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

    public enum WoodType implements StringRepresentable {
        OAK("oak", BlockBehaviour.Properties.copy(Blocks.OAK_LOG), true, MaterialColor.WOOD, MaterialColor.PODZOL),
        SPRUCE("spruce", BlockBehaviour.Properties.copy(Blocks.SPRUCE_LOG), true, MaterialColor.PODZOL, MaterialColor.COLOR_BROWN),
        BIRCH("birch", BlockBehaviour.Properties.copy(Blocks.BIRCH_LOG), true, MaterialColor.SAND, MaterialColor.QUARTZ),
        JUNGLE("jungle", BlockBehaviour.Properties.copy(Blocks.JUNGLE_LOG), true, MaterialColor.DIRT, MaterialColor.PODZOL),
        ACACIA("acacia", BlockBehaviour.Properties.copy(Blocks.ACACIA_LOG), true, MaterialColor.COLOR_ORANGE, MaterialColor.STONE),
        DARK_OAK("dark_oak", BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LOG), true, MaterialColor.COLOR_BROWN, MaterialColor.COLOR_BROWN),
        CRIMSON("crimson", BlockBehaviour.Properties.copy(Blocks.CRIMSON_STEM), false, MaterialColor.CRIMSON_STEM, MaterialColor.CRIMSON_STEM),
        WARPED("warped", BlockBehaviour.Properties.copy(Blocks.WARPED_STEM), false, MaterialColor.WARPED_STEM, MaterialColor.WARPED_STEM),
        LAPIDIFIED_JUNGLE("lapidified_jungle", BlockProperties.LAPIDIFIED_JUNGLE_LOG, false, MaterialColor.DIRT, MaterialColor.DIRT);

        private final String name;
        private final BlockBehaviour.Properties defaultProperties;
        private final boolean flammable;
        private final MaterialColor topColor;
        private final MaterialColor barkColor;

        WoodType(String name, BlockBehaviour.Properties defaultProperties, boolean flammable, MaterialColor topColor, MaterialColor barkColor) {
            this.name = name;
            this.defaultProperties = defaultProperties;
            this.flammable = flammable;
            this.topColor = topColor;
            this.barkColor = barkColor;
        }

        @NotNull
        @Override
        public String getSerializedName() {
            return this.name;
        }

        public BlockBehaviour.Properties getDefaultProperties() {
            return this.defaultProperties;
        }

        public boolean isFlammable() {
            return this.flammable;
        }

        public MaterialColor getTopColor() {
            return this.topColor;
        }

        public MaterialColor getBarkColor() {
            return this.barkColor;
        }
    }
}