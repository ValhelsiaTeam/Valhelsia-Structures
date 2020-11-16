package com.stal111.valhelsia_structures.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.stal111.valhelsia_structures.ValhelsiaStructures;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.io.File;

/**
 * Config
 * Valhelsia Structures - com.stal111.valhelsia_structures.config.Config
 *
 * @author Valhelsia Team
 * @version 16.0.1
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
}
