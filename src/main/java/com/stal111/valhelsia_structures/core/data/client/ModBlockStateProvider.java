package com.stal111.valhelsia_structures.core.data.client;

import com.stal111.valhelsia_structures.common.block.*;
import com.stal111.valhelsia_structures.common.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.valhelsia.valhelsia_core.core.data.ValhelsiaBlockStateProvider;

/**
 * Mod Block State Provider <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.client.ModBlockStateProvider
 *
 * @author Valhelsia Team
 * @since 2020-11-13
 */
public class ModBlockStateProvider extends ValhelsiaBlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ValhelsiaStructures.REGISTRY_MANAGER, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
       // getRemainingBlocks().remove(ModBlocks.JUNGLE_HEAD);

        getRemainingBlocks().remove(ModBlocks.STRIPPED_WOODEN_POSTS.get(ModBlocks.WoodType.LAPIDIFIED_JUNGLE));
        getRemainingBlocks().remove(ModBlocks.CUT_STRIPPED_WOODEN_POSTS.get(ModBlocks.WoodType.LAPIDIFIED_JUNGLE));
        getRemainingBlocks().remove(ModBlocks.BUNDLED_STRIPPED_POSTS.get(ModBlocks.WoodType.LAPIDIFIED_JUNGLE));

        take(block -> this.brazierBlock(block, modLoc("block/brazier_fire"), modLoc("block/brazier_top")), ModBlocks.BRAZIER);
        take(block -> this.brazierBlock(block, modLoc("block/soul_brazier_fire"), modLoc("block/soul_brazier_top")), ModBlocks.SOUL_BRAZIER);
        forEach(block -> getName(block).contains("bundled"), block -> axisBlock((RotatedPillarBlock) block, modLoc("block/bundled_posts/" + getName(block)), modLoc("block/bundled_posts/" + getName(block) + "_top")));
        forEach(block -> block instanceof PostBlock, this::postBlock);
        forEach(block -> block instanceof CutPostBlock, this::cutPostBlock);
        take(block -> paneBlock((IronBarsBlock) block, modLoc("block/metal_framed_glass"), modLoc("block/metal_framed_glass_pane_top")), ModBlocks.METAL_FRAMED_GLASS_PANE);
        ModBlocks.COLORED_METAL_FRAMED_GLASS_PANES.forEach((color, registryObject) -> {
            take(block -> paneBlock((IronBarsBlock) block, modLoc("block/" + color.getName() + "_metal_framed_glass"), modLoc("block/metal_framed_glass_pane_top")), registryObject);
        });
        take(block -> paneBlock((IronBarsBlock) block, modLoc("block/paper_wall"), modLoc("block/paper_wall_top")), ModBlocks.PAPER_WALL);
        take(this::hangingVinesBlock, ModBlocks.HANGING_VINES_BODY, ModBlocks.HANGING_VINES);
        forEach(block -> block instanceof JarBlock, this::jarBlock);
        forEach(block -> block instanceof BigJarBlock, this::bigJarBlock);
        take( block -> logBlock((RotatedPillarBlock) block), ModBlocks.LAPIDIFIED_JUNGLE_LOG);
        take(block -> axisBlock((RotatedPillarBlock) block, modLoc("block/lapidified_jungle_log"), modLoc("block/lapidified_jungle_log")), ModBlocks.LAPIDIFIED_JUNGLE_WOOD);
        ResourceLocation lapidifiedJunglePlanks = modLoc("block/lapidified_jungle_planks");
        take(this::simpleBlock, ModBlocks.LAPIDIFIED_JUNGLE_PLANKS);
        take(block -> slabBlock((SlabBlock) block, lapidifiedJunglePlanks, lapidifiedJunglePlanks), ModBlocks.LAPIDIFIED_JUNGLE_SLAB);
        take(block -> stairsBlock((StairBlock) block, lapidifiedJunglePlanks), ModBlocks.LAPIDIFIED_JUNGLE_STAIRS);
        take(block -> pressurePlateBlock(block, lapidifiedJunglePlanks), ModBlocks.LAPIDIFIED_JUNGLE_PRESSURE_PLATE);
        take(block -> buttonBlock((ButtonBlock) block, lapidifiedJunglePlanks), ModBlocks.LAPIDIFIED_JUNGLE_BUTTON);
        take(block -> fenceBlock((FenceBlock) block, lapidifiedJunglePlanks), ModBlocks.LAPIDIFIED_JUNGLE_FENCE);
        take(block -> fenceGateBlock((FenceGateBlock) block, lapidifiedJunglePlanks), ModBlocks.LAPIDIFIED_JUNGLE_FENCE_GATE);
        take(this::withExistingModel, ModBlocks.HIBISCUS, ModBlocks.GIANT_FERN);
        take(block -> torchBlock(block, modLoc("block/doused_torch")), ModBlocks.DOUSED_TORCH, ModBlocks.DOUSED_SOUL_TORCH);
        take(block -> wallTorchBlock(block, modLoc("block/doused_torch")), ModBlocks.DOUSED_WALL_TORCH, ModBlocks.DOUSED_SOUL_WALL_TORCH);
        take(this::bonePileBlock, ModBlocks.BONE_PILE);

        forEach(block -> block instanceof ValhelsiaStoneBlock, block -> withExistingModel(block, true));
        take(this::valhelsiaGrassBlock, ModBlocks.GRASS_BLOCK);

        take(block -> simpleBlock(block, models().cubeAll(this.getName(block), modLoc("block/dungeon_door"))), ModBlocks.DUNGEON_DOOR_LEAF);
        forEach(block -> block instanceof BigJarTopBlock, block -> simpleBlock(block, getExistingModel(modLoc("block/big_jar_top"))));
        take(block -> simpleBlock(block, getExistingModel(modLoc("block/explorers_tent"))), ModBlocks.EXPLORERS_TENT);

        forEach(block -> block instanceof LanternBlock, this::lanternBlock);
        forEach(block -> block instanceof SleepingBagBlock, this::sleepingBagBlock);

        forEach(this::simpleBlock);
    }

    private void brazierBlock(Block block, ResourceLocation fireTexture, ResourceLocation brazierTop) {
        ModelFile model = models().withExistingParent(getName(block), modLoc("template_brazier")).texture("fire", fireTexture).texture("brazier_top", brazierTop);
        ModelFile offModel = models().withExistingParent(getName(block) + "_off", modLoc("template_brazier_off")).texture("brazier_top", brazierTop);

        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.LIT, false).modelForState().modelFile(offModel).addModel()
                .partialState().with(BlockStateProperties.LIT, true).modelForState().modelFile(model).addModel();
    }

    private void postBlock(Block block) {
        String name = this.getName(block);

        ModelFile model = models().withExistingParent(name, modLoc("block/post"))
                .texture("post_side", modLoc("block/post/" + name))
                .texture("post_end", modLoc("block/post/" + name + "_top"));

        ModelFile attachedModel = models().withExistingParent("attached_" + name, modLoc("block/attached_post"))
                .texture("post_side", modLoc("block/post/" + name))
                .texture("post_end", modLoc("block/post/" + name + "_top"));

        getVariantBuilder(block).forAllStatesExcept(state -> {
            int rotationX = state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? 0 : 90;
            int rotationY = state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.X ? 90 : 0;

            return ConfiguredModel.builder()
                    .modelFile(state.getValue(PostBlock.ATTACHED) ? attachedModel : model)
                    .rotationX(rotationX)
                    .rotationY(rotationY)
                    .build();
        }, PostBlock.WATERLOGGED);
    }

    private void cutPostBlock(Block block) {
        String name = this.getName(block);

        getVariantBuilder(block).forAllStatesExcept(state -> {
            Direction facing = state.getValue(DirectionalBlock.FACING);
            int rotationX = facing == Direction.DOWN ? 180 : facing == Direction.UP ? 0 : 90;
            int rotationY = facing == Direction.SOUTH ? 180 : facing == Direction.WEST ? 270 : facing == Direction.EAST ? 90 : 0;

            int parts = state.getValue(ModBlockStateProperties.PARTS_1_4);

            ModelFile model = parts == 4 ? getExistingModel(modLoc(name.substring(4))) : models().withExistingParent(name + "_" + parts, modLoc("block/cut_post_" + parts))
                    .texture("post_side", modLoc("block/post/" + name.substring(4)))
                    .texture("post_end", modLoc("block/post/" + name.substring(4) + "_top"));

            ModelFile attachedModel = parts == 4 ? getExistingModel(modLoc("attached_" + name.substring(4))) : models().withExistingParent("attached_" + name + "_" + parts, modLoc("block/attached_cut_post_" + parts))
                    .texture("post_side", modLoc("block/post/" + name.substring(4)))
                    .texture("post_end", modLoc("block/post/" + name.substring(4) + "_top"));

            return ConfiguredModel.builder()
                    .modelFile(state.getValue(PostBlock.ATTACHED) ? attachedModel : model)
                    .rotationX(rotationX)
                    .rotationY(rotationY)
                    .build();
        }, PostBlock.WATERLOGGED);
    }

    private void hangingVinesBlock(Block block) {
        String name = this.getName(block);

        ModelFile model = getExistingModel(modLoc("block/" + name));
        ModelFile attachedModel = getExistingModel(modLoc("block/" + "attached_" + name));

        getVariantBuilder(block)
                .partialState().with(ModBlockStateProperties.ATTACHED, true).modelForState().modelFile(attachedModel).addModel()
                .partialState().with(ModBlockStateProperties.ATTACHED, false).modelForState().modelFile(model).addModel();
    }

    private void jarBlock(Block block) {
        String name = this.getName(block);
        ModelFile model = models().withExistingParent(name, modLoc("block/jar")).texture("jar", modLoc("block/jar/" + name));
        ModelFile rotatedModel = models().withExistingParent("rotated_" + name, modLoc("block/rotated_jar")).texture("jar", modLoc("block/jar/" + name));

        getVariantBuilder(block).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(state.getValue(ModBlockStateProperties.ROTATED) ? rotatedModel : model).build(), ModBlockStateProperties.TREASURE, BlockStateProperties.WATERLOGGED);
    }

    private void bigJarBlock(Block block) {
        String name = this.getName(block);
        ModelFile model = models().withExistingParent(name, modLoc("block/big_jar")).texture("jar", modLoc("block/jar/big/" + name));
        ModelFile rotatedModel = models().withExistingParent("rotated_" + name, modLoc("block/rotated_big_jar")).texture("jar", modLoc("block/jar/big/" + name));

        getVariantBuilder(block).forAllStatesExcept(state -> {
            int rotation = state.getValue(ModBlockStateProperties.ROTATION_0_7);
            return ConfiguredModel.builder().modelFile(rotation % 2 == 0 ? model : rotatedModel).rotationY(rotation % 2 == 0 ? rotation * 45 : (rotation - 1) * 45).build();
        }, ModBlockStateProperties.TREASURE, BlockStateProperties.WATERLOGGED);
    }

    private void torchBlock(Block block) {
        torchBlock(block, modLoc("block/" + this.getName(block)));
    }

    private void torchBlock(Block block, ResourceLocation texture) {
        getVariantBuilder(block)
                .partialState().setModels(new ConfiguredModel(models().torch(this.getName(block), texture)));
    }

    private void wallTorchBlock(Block block, ResourceLocation texture) {
        getVariantBuilder(block)
                .forAllStatesExcept(state -> ConfiguredModel.builder()
                        .modelFile(models().torchWall(this.getName(block), texture))
                        .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 90) % 360)
                        .build(),
                        BlockStateProperties.WATERLOGGED
                );
    }

    private void valhelsiaGrassBlock(Block block) {
        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.SNOWY, false).modelForState()
                .modelFile(getExistingModel(mcLoc("block/grass_block"))).nextModel()
                .modelFile(getExistingModel(mcLoc("block/grass_block"))).rotationY(90).nextModel()
                .modelFile(getExistingModel(mcLoc("block/grass_block"))).rotationY(180).nextModel()
                .modelFile(getExistingModel(mcLoc("block/grass_block"))).rotationY(270).addModel()
                .partialState().with(BlockStateProperties.SNOWY, true).modelForState()
                .modelFile(getExistingModel(mcLoc("block/grass_block_snow"))).addModel();
    }

    private void bonePileBlock(Block block) {
        String name = this.getName(block);
        ModelFile model = models().getBuilder(name).parent(new ModelFile.UncheckedModelFile("builtin/generated")).texture("layer0", modLoc("block/" + name));
        getVariantBuilder(block).partialState().modelForState().modelFile(model).rotationX(90).addModel();

        this.models().withExistingParent(name + "_inventory", this.mcLoc("block/pressure_plate_up")).texture("texture", modLoc("block/" + name));
    }

    private void lanternBlock(Block block) {
        ModelFile model = models().withExistingParent(getName(block), mcLoc("template_lantern")).texture("lantern", modLoc("block/" + this.getName(block)));
        ModelFile hangingModel = models().withExistingParent(getName(block) + "_hanging", mcLoc("template_hanging_lantern")).texture("lantern", modLoc("block/" + this.getName(block)));

        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.HANGING, false).modelForState().modelFile(model).addModel()
                .partialState().with(BlockStateProperties.HANGING, true).modelForState().modelFile(hangingModel).addModel();
    }

    private void sleepingBagBlock(Block block) {
        ModelFile footModel = models().withExistingParent(getName(block) + "_foot", modLoc("sleeping_bag_foot")).texture("0", modLoc("block/sleeping_bag/" + this.getName(block)));
        ModelFile headModel = models().withExistingParent(getName(block) + "_head", modLoc("sleeping_bag_head")).texture("0", modLoc("block/sleeping_bag/" + this.getName(block)));
        models().withExistingParent(getName(block) + "_inventory", modLoc("sleeping_bag_inventory")).texture("0", modLoc("block/sleeping_bag/" + this.getName(block)));

        getVariantBuilder(block).forAllStatesExcept(state -> {
            return ConfiguredModel.builder()
                    .modelFile(state.getValue(BlockStateProperties.BED_PART) == BedPart.FOOT ? footModel : headModel)
                    .rotationY((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() % 360)
                    .build();
        }, BlockStateProperties.WATERLOGGED);
    }
}
