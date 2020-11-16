package com.stal111.valhelsia_structures.data.client;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

import java.util.Objects;

/**
 * Mod Item Model Provider
 * Valhelsia Structures - com.stal111.valhelsia_structures.data.client.ModItemModelProvider
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-13
 */
public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ValhelsiaStructures.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withParent(ModBlocks.GLAZED_JAR);
        withParent(ModBlocks.CRACKED_GLAZED_JAR);
        ModBlocks.COLORED_GLAZED_JARS.forEach(this::withParent);

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        //builder(itemGenerated, "jar");
    }

    private void builder(ModelFile itemGenerated, String name) {
        getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

    private <T extends Block>void withParent(RegistryObject<T> registryObject) {
        String name = Objects.requireNonNull(registryObject.get().getRegistryName()).getPath();
        withExistingParent(name, modLoc("block/" + name));
    }
}
