package com.stal111.valhelsia_structures.data.client;

import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Objects;

/**
 * Mod Language Provider
 * Valhelsia Structures - com.stal111.valhelsia_structures.data.client.ModLanguageProvider
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-15
 */
public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(DataGenerator gen) {
        super(gen, ValhelsiaStructures.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModBlocks.GLAZED_JAR.get(), "Glazed Jar");
        add(ModBlocks.CRACKED_GLAZED_JAR.get(), "Cracked Glazed Jar");

        ModBlocks.COLORED_GLAZED_JARS.forEach(registryObject -> {
            String path = Objects.requireNonNull(registryObject.get().getRegistryName()).getPath().split("_", 2)[0];
            add(registryObject.get(), path.substring(0, 1).toUpperCase() + path.substring(1) + " Glazed Jar");
        });
    }
}
