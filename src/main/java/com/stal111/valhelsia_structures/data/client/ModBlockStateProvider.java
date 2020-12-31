package com.stal111.valhelsia_structures.data.client;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.block.JarBlock;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

import java.util.Objects;

/**
 * Mod Block State Provider
 * Valhelsia Structures - com.stal111.valhelsia_structures.data.client.ModBlockStateProvider
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-13
 */
public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ValhelsiaStructures.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        jarBlock(ModBlocks.GLAZED_JAR);
        jarBlock(ModBlocks.CRACKED_GLAZED_JAR);
        ModBlocks.COLORED_GLAZED_JARS.forEach(this::jarBlock);

        logBlock(ModBlocks.LAPIDIFIED_JUNGLE_LOG.get());
        axisBlock(ModBlocks.LAPIDIFIED_JUNGLE_WOOD.get(), modLoc("block/lapidified_jungle_log"), modLoc("block/lapidified_jungle_log"));
        simpleBlock(ModBlocks.LAPIDIFIED_JUNGLE_PLANKS.get());
        ResourceLocation lapidifiedJunglePlanks = modLoc("block/lapidified_jungle_planks");
        slabBlock(ModBlocks.LAPIDIFIED_JUNGLE_SLAB.get(), lapidifiedJunglePlanks, lapidifiedJunglePlanks);
        stairsBlock(ModBlocks.LAPIDIFIED_JUNGLE_STAIRS.get(), lapidifiedJunglePlanks);
        pressurePlateBlock(ModBlocks.LAPIDIFIED_JUNGLE_PRESSURE_PLATE, lapidifiedJunglePlanks);
        buttonBlock(ModBlocks.LAPIDIFIED_JUNGLE_BUTTON, lapidifiedJunglePlanks);
        fenceBlock(ModBlocks.LAPIDIFIED_JUNGLE_FENCE, lapidifiedJunglePlanks);
        fenceGateBlock(ModBlocks.LAPIDIFIED_JUNGLE_FENCE_GATE.get(), lapidifiedJunglePlanks);

        simpleBlock(ModBlocks.HIBISCUS.get(), models().getExistingFile(modLoc("block/hibiscus")));
        simpleBlock(ModBlocks.GIANT_FERN.get(), models().getExistingFile(modLoc("block/giant_fern")));
    }

    private void jarBlock(RegistryObject<JarBlock> registryObject) {
        String name = Objects.requireNonNull(registryObject.get().getRegistryName()).getPath();
        ModelFile model = models().withExistingParent(name, modLoc("block/jar")).texture("jar", modLoc("block/jar/" + name));

        getVariantBuilder(registryObject.get()).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(model).build(), JarBlock.WATERLOGGED);
    }

    private void pressurePlateBlock(RegistryObject<PressurePlateBlock> registryObject, ResourceLocation texture) {
        String name = Objects.requireNonNull(registryObject.get().getRegistryName()).getPath();
        ModelFile model = models().withExistingParent(name, mcLoc("block/pressure_plate_up")).texture("texture", texture);
        ModelFile modelDown = models().withExistingParent(name + "_down", mcLoc("block/pressure_plate_down")).texture("texture", texture);

        getVariantBuilder(registryObject.get())
                .partialState().with(PressurePlateBlock.POWERED, false)
                    .modelForState().modelFile(model).addModel()
                .partialState().with(PressurePlateBlock.POWERED, true)
                    .modelForState().modelFile(modelDown).addModel();
    }

    private <T extends AbstractButtonBlock>void buttonBlock(RegistryObject<T> registryObject, ResourceLocation texture) {
        String name = Objects.requireNonNull(registryObject.get().getRegistryName()).getPath();
        ModelFile model = models().withExistingParent(name, mcLoc("block/button")).texture("texture", texture);
        ModelFile modelPressed = models().withExistingParent(name + "_pressed", mcLoc("block/button_pressed")).texture("texture", texture);
        models().withExistingParent(name + "_inventory", mcLoc("block/button_inventory")).texture("texture", texture);

        getVariantBuilder(registryObject.get())
                .forAllStates(state -> {
                    Direction facing = state.get(BlockStateProperties.HORIZONTAL_FACING);
                    AttachFace face = state.get(BlockStateProperties.FACE);

                    int rotationX = face == AttachFace.CEILING ? 180 : face == AttachFace.WALL ? 90 : 0;
                    int rotationY = (int) facing.rotateY().getHorizontalAngle() + 90;

                    return ConfiguredModel.builder()
                            .modelFile(!state.get(BlockStateProperties.POWERED) ? model : modelPressed)
                            .rotationX(rotationX)
                            .rotationY(face == AttachFace.CEILING ? rotationY - 180 : rotationY)
                            .uvLock(face == AttachFace.WALL)
                            .build();
                });
    }

    private void fenceBlock(RegistryObject<FenceBlock> registryObject, ResourceLocation texture) {
        super.fenceBlock(registryObject.get(), texture);

        String name = Objects.requireNonNull(registryObject.get().getRegistryName()).getPath();
        models().fenceInventory(name + "_inventory", texture);
    }
}
