package com.stal111.valhelsia_structures.core.config;

import com.stal111.valhelsia_structures.common.world.structures.ValhelsiaStructureSettings;
import net.minecraftforge.common.ForgeConfigSpec;

/**
 * Common Config <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.config.CommonConfig
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.1.0
 * @since 2021-10-01
 */
public class CommonConfig {

    public final ForgeConfigSpec.IntValue flatnessDelta;
    public final ForgeConfigSpec.IntValue minStructureDistance;

    public final ForgeConfigSpec.BooleanValue disableDousedTorch;

    public CommonConfig(ForgeConfigSpec.Builder builder) {
        builder.push("structures");
        this.flatnessDelta = builder.comment("How flat does terrain need to be for surface structures to spawn? (in blocks) [default: 4]").defineInRange("global.flatness_delta", 4, 0, 64);
        this.minStructureDistance = builder.comment("How many chunks need to be at least between two structures? (in chunks) [default: 5]").defineInRange("global.min_structure_distance", 5, 0, 64);

        ValhelsiaStructureSettings.DEFAULT_MARGIN.setConfiguredValue(defaultValue -> builder.comment("The default margin around a valhelsia structure if not otherwise specified in the structure json file. [default: " + defaultValue + "]").define("global.default_margin", defaultValue));

//        for (RegistryObject<Structure> registryObject : ModStructures.HELPER.getRegistryObjects()) {
//            String name = registryObject.getId().getPath();
//            ValhelsiaStructureSettings settings = ModStructures.STRUCTURE_SETTINGS_MAP.get(name);
//
//            settings.spawnChance().setConfiguredValue(defaultValue -> builder.comment("Spawn Chance [default: " + defaultValue + "]. \n 1.0 = generate the structure always, 0.0 = never generate the structure").defineInRange(name + ".spawn_chance", defaultValue, 0.0, 1.0));
//            if (settings.size() != null) {
//                settings.size().setConfiguredValue(defaultValue -> builder.comment("The size of the structure [default: " + defaultValue + "]. Used in the calculations that check whether the surface area is flat enough to generate the structure.").defineInRange(name + ".size", defaultValue, 0, 100));
//            }
//            settings.margin().setConfiguredValue(defaultValue -> builder.comment("The margin around the structure [default: " + defaultValue + "].").defineInRange(name + ".margin", defaultValue, 0, 50));
//        }

        builder.pop();

        builder.push("blocks");

        this.disableDousedTorch = builder.comment("Enable/Disable the Doused Torch Feature. If disabled Water will no longer transform normal Torches into Doused Torches. \n Doused Torches will however still generate in structures. [default: false]").define("doused_torch.disable", false);

        builder.pop();
    }
}
