package com.stal111.valhelsia_structures.data.client;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.block.JarBlock;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.data.DataGenerator;
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
        registerJar(ModBlocks.GLAZED_JAR);
        registerJar(ModBlocks.CRACKED_GLAZED_JAR);
        ModBlocks.COLORED_GLAZED_JARS.forEach(this::registerJar);
    }

    private void registerJar(RegistryObject<JarBlock> registryObject) {
        String name = Objects.requireNonNull(registryObject.get().getRegistryName()).getPath();
        ModelFile model = models().withExistingParent(name, modLoc("block/jar"))
                .texture("jar", modLoc("block/jar/" + name));

        getVariantBuilder(registryObject.get()).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(model).build(), JarBlock.WATERLOGGED);
    }
}
