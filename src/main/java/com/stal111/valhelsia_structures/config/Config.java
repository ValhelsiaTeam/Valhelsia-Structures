package com.stal111.valhelsia_structures.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.utils.StructureType;
import com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure;
import com.stal111.valhelsia_structures.world.structures.RemovedStructure;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.gui.ConfigErrorScreen;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Config
 * Valhelsia Structures - com.stal111.valhelsia_structures.config.Config
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-05-27
 */
@Mod.EventBusSubscriber
public class Config {
    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec COMMON_CONFIG;
    public static final ForgeConfigSpec CLIENT_CONFIG;

    static {
        StructureGenConfig.init(COMMON_BUILDER, CLIENT_BUILDER);
        BlockConfig.init(COMMON_BUILDER, CLIENT_BUILDER);

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).preserveInsertionOrder().sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }

    public static boolean validateConfig() {
        boolean flag = true;

        for (Map.Entry<StructureType, List<AbstractValhelsiaStructure>> entry : ModStructures.STRUCTURES_MAP.entrySet()) {
            for (AbstractValhelsiaStructure structure : entry.getValue()) {
                if (!(structure instanceof RemovedStructure)) {
                    StructureConfigEntry configEntry = structure.getStructureConfigEntry();

                    if ((configEntry.configuredSpacing.get() - configEntry.configuredSeparation.get()) <= 0) {
                        Minecraft.getInstance().displayGuiScreen(new ConfigErrorScreen(new TranslationTextComponent("gui.valhelsia_structures.config.spacing_error"), "structures." + structure.getName()  + ".spacing", new TranslationTextComponent("gui.valhelsia_structures.config.spacing_solution", configEntry.configuredSeparation.get() + 1)));
                        flag = false;
                    }

                    for (String string : configEntry.configuredBiomeCategories.get()) {
                        if (Arrays.stream(Biome.Category.values()).noneMatch(category -> category.getName().equals(string))) {
                            Minecraft.getInstance().displayGuiScreen(new ConfigErrorScreen(new TranslationTextComponent("gui.valhelsia_structures.config.invalid_biome_category").appendString(" " + string), "structures." + structure.getName()  + ".biome_categories", new TranslationTextComponent("gui.valhelsia_structures.config.ignoring")));
                            flag = false;
                        }
                    }

                    for (String string : configEntry.configuredBlacklistedBiomes.get()) {
                        if (!ForgeRegistries.BIOMES.containsKey(new ResourceLocation(string))) {
                            Minecraft.getInstance().displayGuiScreen(new ConfigErrorScreen(new TranslationTextComponent("gui.valhelsia_structures.config.invalid_blacklisted_biome").appendString(" " + string), "structures." + structure.getName()  + ".blacklisted_biomes", new TranslationTextComponent("gui.valhelsia_structures.config.ignoring")));
                            flag = false;
                        }
                    }
                }
            }
        }
        ValhelsiaStructures.configValidated = true;
        return flag;
    }
}
