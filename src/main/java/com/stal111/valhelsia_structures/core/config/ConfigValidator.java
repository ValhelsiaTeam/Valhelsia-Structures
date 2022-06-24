package com.stal111.valhelsia_structures.core.config;

import com.stal111.valhelsia_structures.core.init.world.ModStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.registries.RegistryObject;
import net.valhelsia.valhelsia_core.core.config.AbstractConfigValidator;

/**
 * Config Validator <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.config.ConfigValidator
 *
 * @author Valhelsia Team
 * @version 1.19 - 0.2.0
 * @since 2021-02-16
 */
public class ConfigValidator extends AbstractConfigValidator {

    @Override
    public void validate() {
        for (RegistryObject<Structure> structure : ModStructures.HELPER.getRegistryObjects()) {
//            ValhelsiaStructureSettings valhelsiaStructureSettings = structure.getStructureSettings();
//
//            if ((valhelsiaStructureSettings.spacing().get() - valhelsiaStructureSettings.separation().get()) <= 0) {
//                this.addError(new TranslatableComponent("gui.valhelsia_structures.config.spacing_error"), "structures." + structure.getName() + ".spacing", new TranslatableComponent("gui.valhelsia_structures.config.spacing_solution", valhelsiaStructureSettings.separation().get() + 1));
//            }
        }
    }
}
