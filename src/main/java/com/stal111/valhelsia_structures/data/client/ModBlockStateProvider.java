package com.stal111.valhelsia_structures.data.client;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.block.BrazierBlock;
import com.stal111.valhelsia_structures.block.JarBlock;
import com.stal111.valhelsia_structures.block.PostBlock;
import com.stal111.valhelsia_structures.block.ValhelsiaStoneBlock;
import com.stal111.valhelsia_structures.block.properties.ModBlockStateProperties;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.valhelsia.valhelsia_core.data.ValhelsiaBlockStateProvider;

import java.util.Objects;

/**
 * Mod Block State Provider
 * Valhelsia Structures - com.stal111.valhelsia_structures.data.client.ModBlockStateProvider
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-13
 */
public class ModBlockStateProvider extends ValhelsiaBlockStateProvider {

    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ValhelsiaStructures.REGISTRY_MANAGER, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        getRemainingBlocks().remove(ModBlocks.DUNGEON_DOOR_LEAF);

        forEach(block -> block instanceof BrazierBlock, this::brazierBlock);
        forEach(block -> block instanceof PostBlock, this::postBlock);
        take(block -> paneBlock((PaneBlock) block, modLoc("block/metal_framed_glass"), modLoc("block/metal_framed_glass_pane_top")), ModBlocks.METAL_FRAMED_GLASS_PANE);
        take(block -> paneBlock((PaneBlock) block, modLoc("block/paper_wall"), modLoc("block/paper_wall_top")), ModBlocks.PAPER_WALL);
        take(this::hangingVinesBlock, ModBlocks.HANGING_VINES_BODY, ModBlocks.HANGING_VINES);
        forEach(block -> block instanceof JarBlock, this::jarBlock);
        take(block -> torchBlock(block, modLoc("block/doused_torch")), ModBlocks.DOUSED_TORCH, ModBlocks.DOUSED_SOUL_TORCH);
        take(block -> wallTorchBlock(block, modLoc("block/doused_torch")), ModBlocks.DOUSED_WALL_TORCH, ModBlocks.DOUSED_SOUL_WALL_TORCH);
        take(this::layerBlock, ModBlocks.BONE_PILE);

        forEach(block -> block instanceof ValhelsiaStoneBlock, block -> withExistingModel(block, true));
        take(this::valhelsiaGrassBlock, ModBlocks.GRASS_BLOCK);

        forEach(this::simpleBlock);
    }

    private void brazierBlock(Block block) {
        String name = Objects.requireNonNull(block.getRegistryName()).getPath();
        ModelFile model = getExistingModel(modLoc("block/" + name));
        ModelFile offModel = getExistingModel(modLoc("block/" + name + "_off"));

        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.LIT, true).modelForState().modelFile(model).addModel()
                .partialState().with(BlockStateProperties.LIT, false).modelForState().modelFile(offModel).addModel();
    }

    private void postBlock(Block block) {
        String name = Objects.requireNonNull(block.getRegistryName()).getPath();

        ModelFile model = models().withExistingParent(name, modLoc("block/post"))
                .texture("post_side", modLoc("block/post/" + name))
                .texture("post_end", modLoc("block/post/" + name + "_top"));

        ModelFile attached = models().withExistingParent(name + "_attached", modLoc("block/post_attached"))
                .texture("post_side", modLoc("block/post/" + name))
                .texture("post_end", modLoc("block/post/" + name + "_top"));

        getVariantBuilder(block).forAllStatesExcept(state -> {
            int rotationX = state.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? 0 : 90;
            int rotationY = state.get(RotatedPillarBlock.AXIS) == Direction.Axis.X ? 90 : 0;

            return ConfiguredModel.builder()
                    .modelFile(state.get(PostBlock.ATTACHED) ? attached : model)
                    .rotationX(rotationX)
                    .rotationY(rotationY)
                    .build();
        }, PostBlock.WATERLOGGED);
    }

    private void hangingVinesBlock(Block block) {
        String name = Objects.requireNonNull(block.getRegistryName()).getPath();

        ModelFile model = getExistingModel(modLoc("block/" + name));
        ModelFile attachedModel = getExistingModel(modLoc("block/" + "attached_" + name));

        getVariantBuilder(block)
                .partialState().with(ModBlockStateProperties.ATTACHED, true).modelForState().modelFile(attachedModel).addModel()
                .partialState().with(ModBlockStateProperties.ATTACHED, false).modelForState().modelFile(model).addModel();
    }

    private void jarBlock(Block block) {
        String name = Objects.requireNonNull(block.getRegistryName()).getPath();
        ModelFile model = models().withExistingParent(name, modLoc("block/jar")).texture("jar", modLoc("block/jar/" + name));

        getVariantBuilder(block).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(model).build(), BlockStateProperties.WATERLOGGED);
    }

    private void torchBlock(Block block) {
        String name = Objects.requireNonNull(block.getRegistryName()).getPath();
        torchBlock(block, modLoc("blocks/" + name));
    }

    private void torchBlock(Block block, ResourceLocation texture) {
        String name = Objects.requireNonNull(block.getRegistryName()).getPath();

        getVariantBuilder(block)
                .partialState().setModels(new ConfiguredModel(models().torch(name, texture)));
    }

    private void wallTorchBlock(Block block, ResourceLocation texture) {
        String name = Objects.requireNonNull(block.getRegistryName()).getPath();

        getVariantBuilder(block)
                .forAllStatesExcept(state -> ConfiguredModel.builder()
                                .modelFile(models().torchWall(name, texture))
                                .rotationY(((int) state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + 90) % 360)
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

    private void layerBlock(Block block) {
        String name = Objects.requireNonNull(block.getRegistryName()).getPath();

        getVariantBuilder(block).forAllStates(state -> {
            int height = state.get(BlockStateProperties.LAYERS_1_8) * 2;

            ModelFile model = height == 16 ? cubeAll(block) : models().withExistingParent(name + "_" + height, mcLoc("block/snow_height" + height)).texture("texture", modLoc("block/" + name));

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .build();
        });
    }
}
