package com.stal111.valhelsia_structures.core.config;

import com.stal111.valhelsia_structures.common.world.structures.AbstractValhelsiaStructure;
import com.stal111.valhelsia_structures.common.world.structures.StructureSettings;
import com.stal111.valhelsia_structures.core.init.ModStructures;
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

        for (AbstractValhelsiaStructure structure : ModStructures.MOD_STRUCTURES) {
            StructureSettings structureSettings = structure.getStructureSettings();

            structureSettings.spawnChance().setConfiguredValue(defaultValue -> builder.comment("Spawn Chance [default: " + defaultValue + "]. \n 1.0 = generate the structure always, 0.0 = never generate the structure").defineInRange(structure.getName() + ".spawn_chance", defaultValue, 0.0, 1.0));
            structureSettings.spacing().setConfiguredValue(defaultValue -> builder.comment("Spacing (in chunks) [default: " + defaultValue + "]").defineInRange(structure.getName() + ".spacing", defaultValue, 0, 200));
            structureSettings.separation().setConfiguredValue(defaultValue -> builder.comment("Minimum Separation (in chunks) [default: " + defaultValue + "]").defineInRange(structure.getName() + ".separation", defaultValue, 0, 200));
        }

        builder.pop();

        builder.push("blocks");

        this.disableDousedTorch = builder.comment("Enable/Disable the Doused Torch Feature. If disabled Water will no longer transform normal Torches into Doused Torches. \n Doused Torches will however still generate in structures. [default: false]").define("doused_torch.disable", false);

        builder.pop();
    }
}
