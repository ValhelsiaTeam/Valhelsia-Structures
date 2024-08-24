package com.stal111.valhelsia_structures.core.init;

import com.stal111.valhelsia_structures.common.block.*;
import com.stal111.valhelsia_structures.common.item.BigJarBlockItem;
import com.stal111.valhelsia_structures.common.item.DyeableBlockItem;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;
import net.valhelsia.valhelsia_core.api.client.ValhelsiaRenderType;
import net.valhelsia.valhelsia_core.api.common.block.StrippableRotatedPillarBlock;
import net.valhelsia.valhelsia_core.api.common.block.entity.ValhelsiaBlockProperties;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryClass;
import net.valhelsia.valhelsia_core.api.common.registry.helper.block.BlockEntrySet;
import net.valhelsia.valhelsia_core.api.common.registry.helper.block.BlockRegistryEntry;
import net.valhelsia.valhelsia_core.api.common.registry.helper.block.BlockRegistryHelper;
import net.valhelsia.valhelsia_core.api.common.registry.helper.block.ToolType;
import org.jetbrains.annotations.NotNull;

/**
 * Blocks <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.init.ModBlocks
 *
 * @author Valhelsia Team
 */
public class ModBlocks implements RegistryClass {

    public static final BlockRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getBlockHelper();

    private static final BlockBehaviour.OffsetFunction BONE_PILE_OFFSET = (state, level, pos) -> {
        return new Vec3(0.0D, -0.46875D, 0.0D);
    };

    private static final MapColorProvider AXIS_COLOR_PROVIDER = (state, topColor, barkColor) -> {
        return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : barkColor;
    };

    private static final MapColorProvider FACING_COLOR_PROVIDER = (state, topColor, barkColor) -> {
        return state.getValue(HorizontalDirectionalBlock.FACING).getAxis() == Direction.Axis.Y ? topColor : barkColor;
    };

    public static final BlockRegistryEntry<SpecialSpawnerBlock> SPECIAL_SPAWNER = HELPER.register("special_spawner", () -> new SpecialSpawnerBlock(Block.Properties.ofLegacyCopy(Blocks.SPAWNER).strength(-1.0F, 3600000.0F).noLootTable())).withItem().renderType(ValhelsiaRenderType.CUTOUT);
    public static final BlockRegistryEntry<BrazierBlock> BRAZIER = HELPER.register("brazier", () -> new BrazierBlock(true, 1, Block.Properties.ofLegacyCopy(Blocks.IRON_BARS).noOcclusion().lightLevel(state -> state.getValue(BrazierBlock.LIT) ? 15 : 0)))
            .withItem().toolType(ToolType.PICKAXE).renderType(ValhelsiaRenderType.CUTOUT);
    public static final BlockRegistryEntry<BrazierBlock> SOUL_BRAZIER = HELPER.register("soul_brazier", () -> new BrazierBlock(false, 2, Block.Properties.ofLegacyCopy(Blocks.IRON_BARS).noOcclusion().lightLevel(state -> state.getValue(BrazierBlock.LIT) ? 11 : 0)))
            .withItem().toolType(ToolType.PICKAXE).renderType(ValhelsiaRenderType.CUTOUT);

    public static final BlockEntrySet<PostBlock, WoodType> WOODEN_POSTS = HELPER.registerEntrySet(WoodType.class, "post",
            type -> new PostBlock(type.buildProperties(false, AXIS_COLOR_PROVIDER).noOcclusion()),
            registryObject -> registryObject.withItem().toolType(ToolType.AXE)
    );

    public static final BlockEntrySet<PostBlock, WoodType> STRIPPED_WOODEN_POSTS = HELPER.registerEntrySet(WoodType.class, s -> "stripped_" + s + "_post",
            type -> new PostBlock(type.buildProperties(true, AXIS_COLOR_PROVIDER).noOcclusion()),
            registryObject -> registryObject.withItem().toolType(ToolType.AXE)
    );

    public static final BlockEntrySet<CutPostBlock, WoodType> CUT_WOODEN_POSTS = HELPER.registerEntrySet(WoodType.class, s -> "cut_" + s + "_post",
            type -> new CutPostBlock(type.buildProperties(false, FACING_COLOR_PROVIDER).mapColor(type.getBarkColor()).noOcclusion()),
            registryObject -> registryObject.withItem().toolType(ToolType.AXE)
    );

    public static final BlockEntrySet<CutPostBlock, WoodType> CUT_STRIPPED_WOODEN_POSTS = HELPER.registerEntrySet(WoodType.class, s -> "cut_stripped_" + s + "_post",
            type -> new CutPostBlock(type.buildProperties(true, FACING_COLOR_PROVIDER).mapColor(type.getBarkColor()).noOcclusion()),
            registryObject -> registryObject.withItem().toolType(ToolType.AXE)
    );

    public static final BlockEntrySet<RotatedPillarBlock, WoodType> BUNDLED_STRIPPED_POSTS = HELPER.registerEntrySet(WoodType.class, s -> "bundled_stripped_" + s + "_posts",
            type -> new RotatedPillarBlock(type.buildProperties(true, AXIS_COLOR_PROVIDER)),
            registryObject -> registryObject.withItem().toolType(ToolType.AXE)
    );
    public static final BlockEntrySet<RotatedPillarBlock, WoodType> BUNDLED_POSTS = HELPER.registerEntrySet(WoodType.class, s -> "bundled_" + s + "_posts",
            type -> new StrippableRotatedPillarBlock(ModBlocks.BUNDLED_STRIPPED_POSTS.get(type), type.buildProperties(false, AXIS_COLOR_PROVIDER)),
            registryObject -> registryObject.withItem().toolType(ToolType.AXE)
    );

    public static final BlockRegistryEntry<TransparentBlock> METAL_FRAMED_GLASS = HELPER.register("metal_framed_glass", () -> new TransparentBlock(Block.Properties.ofLegacyCopy(Blocks.GLASS))).withItem().renderType(ValhelsiaRenderType.CUTOUT);
    public static final BlockRegistryEntry<IronBarsBlock> METAL_FRAMED_GLASS_PANE = HELPER.register("metal_framed_glass_pane", () -> new IronBarsBlock(Block.Properties.ofLegacyCopy(Blocks.GLASS_PANE))).withItem().renderType(ValhelsiaRenderType.CUTOUT);
    public static final BlockEntrySet<StainedGlassBlock, DyeColor> COLORED_METAL_FRAMED_GLASS = HELPER.registerColorEntrySet("metal_framed_glass",
            color -> new StainedGlassBlock(color, Block.Properties.ofLegacyCopy(Blocks.RED_STAINED_GLASS)),
            entry -> entry.withItem().renderType(ValhelsiaRenderType.TRANSLUCENT)
    );
    public static final BlockEntrySet<StainedGlassPaneBlock, DyeColor> COLORED_METAL_FRAMED_GLASS_PANES = HELPER.registerColorEntrySet("metal_framed_glass_pane",
            color -> new StainedGlassPaneBlock(color, Block.Properties.ofLegacyCopy(Blocks.RED_STAINED_GLASS_PANE)),
            entry -> entry.withItem().renderType(ValhelsiaRenderType.TRANSLUCENT)
    );

    public static final BlockRegistryEntry<IronBarsBlock> PAPER_WALL = HELPER.register("paper_wall", () -> new IronBarsBlock(Block.Properties.of().strength(0.3F).sound(SoundType.WOOL).noOcclusion())).withItem();
    public static final BlockRegistryEntry<HangingVinesBodyBlock> HANGING_VINES_BODY = HELPER.register("hanging_vines_body", () -> new HangingVinesBodyBlock(Block.Properties.of().noCollission().strength(0.2F).sound(SoundType.VINE))).renderType(ValhelsiaRenderType.CUTOUT);
    public static final BlockRegistryEntry<HangingVinesBlock> HANGING_VINES = HELPER.register("hanging_vines", () -> new HangingVinesBlock(Block.Properties.of().randomTicks().noCollission().strength(0.2F).sound(SoundType.VINE))).withItem().renderType(ValhelsiaRenderType.CUTOUT);
    public static final BlockRegistryEntry<JarBlock> GLAZED_JAR = HELPER.register("glazed_jar", () -> new JarBlock(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion())).withItem();
    public static final BlockRegistryEntry<JarBlock> CRACKED_GLAZED_JAR = HELPER.register("cracked_glazed_jar", () -> new JarBlock(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.0F).noOcclusion())).withItem();
    public static final BlockEntrySet<JarBlock, DyeColor> COLORED_GLAZED_JARS = HELPER.registerColorEntrySet("glazed_jar",
            color -> new JarBlock(Block.Properties.of().mapColor(color.getMapColor()).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()),
            BlockRegistryEntry::withItem
    );
    public static final BlockRegistryEntry<BigJarBlock> BIG_GLAZED_JAR = HELPER.register("big_glazed_jar", () -> new BigJarBlock(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion())).withItem(registryObject -> new BigJarBlockItem(registryObject.get(), new Item.Properties())).renderType(ValhelsiaRenderType.CUTOUT);

    public static final BlockRegistryEntry<BigJarBlock> CRACKED_BIG_GLAZED_JAR = HELPER.register("cracked_big_glazed_jar", () -> new BigJarBlock(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.4F).noOcclusion())).withItem(registryObject -> new BigJarBlockItem(registryObject.get(), new Item.Properties())).renderType(ValhelsiaRenderType.CUTOUT);

    public static final BlockEntrySet<BigJarBlock, DyeColor> BIG_COLORED_GLAZED_JARS = HELPER.registerColorEntrySet(color -> "big_" + color + "_glazed_jar",
            color -> new BigJarBlock(Block.Properties.of().mapColor(color.getMapColor()).requiresCorrectToolForDrops().strength(1.4F).noOcclusion()),
            entry -> entry.withItem(registryObject -> new BigJarBlockItem(registryObject.get(), new Item.Properties())).renderType(ValhelsiaRenderType.CUTOUT)
    );
    public static final BlockRegistryEntry<ExplorersTentBlock> EXPLORERS_TENT = HELPER.register("explorers_tent", () -> new ExplorersTentBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.BROWN_WOOL).noOcclusion())).withItem(registryObject -> new DyeableBlockItem(registryObject.get(), new Item.Properties()));
    //public static final BlockRegistryEntry<BushBlock> HIBISCUS = HELPER.register("hibiscus", () -> new BushBlock(Block.Properties.ofLegacyCopy(Blocks.POPPY))).withItem().renderType(ValhelsiaRenderType.CUTOUT);
    public static final BlockRegistryEntry<GiantFernBlock> GIANT_FERN = HELPER.register("giant_fern", () -> new GiantFernBlock(Block.Properties.ofLegacyCopy(Blocks.POPPY))).withItem();
    public static final BlockRegistryEntry<DousedTorchBlock> DOUSED_TORCH = HELPER.register("doused_torch", () -> new DousedTorchBlock((TorchBlock) Blocks.TORCH, Block.Properties.of().noCollission().instabreak().sound(SoundType.WOOD))).renderType(ValhelsiaRenderType.CUTOUT);
    public static final BlockRegistryEntry<DousedWallTorchBlock> DOUSED_WALL_TORCH = HELPER.register("doused_wall_torch", () -> new DousedWallTorchBlock((TorchBlock) Blocks.WALL_TORCH, Block.Properties.of().noCollission().instabreak().sound(SoundType.WOOD))).renderType(ValhelsiaRenderType.CUTOUT);
    public static final BlockRegistryEntry<DousedTorchBlock> DOUSED_SOUL_TORCH = HELPER.register("doused_soul_torch", () -> new DousedTorchBlock((TorchBlock) Blocks.SOUL_TORCH, Block.Properties.of().noCollission().instabreak().sound(SoundType.WOOD))).renderType(ValhelsiaRenderType.CUTOUT);
    public static final BlockRegistryEntry<DousedWallTorchBlock> DOUSED_SOUL_WALL_TORCH = HELPER.register("doused_soul_wall_torch", () -> new DousedWallTorchBlock((TorchBlock) Blocks.SOUL_WALL_TORCH, Block.Properties.of().noCollission().instabreak().sound(SoundType.WOOD))).renderType(ValhelsiaRenderType.CUTOUT);
    public static final BlockRegistryEntry<DungeonDoorBlock> DUNGEON_DOOR = HELPER.register("dungeon_door", () -> new DungeonDoorBlock(Block.Properties.of().strength(50.0F, 100.0F).requiresCorrectToolForDrops().noOcclusion())).withItem();
    public static final BlockRegistryEntry<DungeonDoorLeafBlock> DUNGEON_DOOR_LEAF = HELPER.register("dungeon_door_leaf", () -> new DungeonDoorLeafBlock(Block.Properties.of().strength(50.0F, 100.0F).requiresCorrectToolForDrops().noOcclusion().lootFrom(ModBlocks.DUNGEON_DOOR)));
    public static final BlockRegistryEntry<BonePileBlock> BONE_PILE = HELPER.register("bone_pile", () -> new BonePileBlock(ValhelsiaBlockProperties.of().offsetType(BONE_PILE_OFFSET).mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(2.0F).sound(SoundType.BONE_BLOCK).noOcclusion().dynamicShape())).withItem().renderType(ValhelsiaRenderType.CUTOUT);
    public static final BlockRegistryEntry<Block> BONE_PILE_BLOCK = HELPER.register("bone_pile_block", () -> new Block(Block.Properties.ofLegacyCopy(Blocks.BONE_BLOCK))).withItem();
    public static final BlockRegistryEntry<UnlitLanternBlock> UNLIT_LANTERN = HELPER.register("unlit_lantern", () -> new UnlitLanternBlock(() -> Blocks.LANTERN, Block.Properties.ofLegacyCopy(Blocks.LANTERN).lightLevel(value -> 0))).withItem().toolType(ToolType.PICKAXE).renderType(ValhelsiaRenderType.CUTOUT);
    public static final BlockRegistryEntry<UnlitLanternBlock> UNLIT_SOUL_LANTERN = HELPER.register("unlit_soul_lantern", () -> new UnlitLanternBlock(() -> Blocks.SOUL_LANTERN, Block.Properties.ofLegacyCopy(Blocks.SOUL_LANTERN).lightLevel(value -> 0))).withItem().toolType(ToolType.PICKAXE).renderType(ValhelsiaRenderType.CUTOUT);

    //Sleeping Bags
    public static final BlockEntrySet<SleepingBagBlock, DyeColor> SLEEPING_BAGS = HELPER.registerColorEntrySet("sleeping_bag",
            color -> new SleepingBagBlock(Block.Properties.of().mapColor(color.getMapColor()).strength(0.2F).noOcclusion().sound(SoundType.WOOL)),
            blockRegistryObject -> blockRegistryObject.withItem(registryObject -> new BedItem(registryObject.get(), new Item.Properties()))
    );

    public enum WoodType implements StringRepresentable {
        OAK("oak", true, MapColor.WOOD, MapColor.PODZOL),
        SPRUCE("spruce", true, MapColor.PODZOL, MapColor.COLOR_BROWN),
        BIRCH("birch", true, MapColor.SAND, MapColor.QUARTZ),
        JUNGLE("jungle", true, MapColor.DIRT, MapColor.PODZOL),
        ACACIA("acacia", true, MapColor.COLOR_ORANGE, MapColor.STONE),
        DARK_OAK("dark_oak", true, MapColor.COLOR_BROWN, MapColor.COLOR_BROWN),
        MANGROVE("mangrove", true, MapColor.COLOR_RED, MapColor.PODZOL),
        CRIMSON("crimson", false, MapColor.CRIMSON_STEM, MapColor.CRIMSON_STEM),
        WARPED("warped", false, MapColor.WARPED_STEM, MapColor.WARPED_STEM);
        
        private final String name;
        private final boolean flammable;
        private final MapColor topColor;
        private final MapColor barkColor;

        WoodType(String name, boolean flammable, MapColor topColor, MapColor barkColor) {
            this.name = name;
            this.flammable = flammable;
            this.topColor = topColor;
            this.barkColor = barkColor;
        }

        @NotNull
        @Override
        public String getSerializedName() {
            return this.name;
        }

        public BlockBehaviour.Properties buildProperties(boolean stripped, MapColorProvider colorProvider) {
            var properties = BlockBehaviour.Properties.of()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .mapColor(state -> colorProvider.getMapColor(state, this.topColor, stripped ? this.topColor : this.barkColor));

            if (this.isFlammable()) {
                properties = properties.ignitedByLava();
            }

            return properties;
        }

        public boolean isFlammable() {
            return this.flammable;
        }

        public MapColor getTopColor() {
            return this.topColor;
        }

        public MapColor getBarkColor() {
            return this.barkColor;
        }
    }

    public interface MapColorProvider {
        MapColor getMapColor(BlockState state, MapColor topColor, MapColor barkColor);
    }
}