package com.stal111.valhelsia_structures.core.config;

import com.stal111.valhelsia_structures.common.world.structures.AbstractValhelsiaStructure;
import com.stal111.valhelsia_structures.common.world.structures.StructureSettings;
import com.stal111.valhelsia_structures.core.init.ModStructures;
import net.minecraft.network.chat.TranslatableComponent;
import net.valhelsia.valhelsia_core.core.config.AbstractConfigValidator;

/**
 * Config Validator <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.config.ConfigValidator
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.1.0
 * @since 2021-02-16
 */
public class ConfigValidator extends AbstractConfigValidator {

    @Override
    public void validate() {
        for (AbstractValhelsiaStructure structure : ModStructures.MOD_STRUCTURES) {
            StructureSettings structureSettings = structure.getStructureSettings();

            if ((structureSettings.spacing().get() - structureSettings.separation().get()) <= 0) {
                this.addError(new TranslatableComponent("gui.valhelsia_structures.config.spacing_error"), "structures." + structure.getName() + ".spacing", new TranslatableComponent("gui.valhelsia_structures.config.spacing_solution", structureSettings.separation().get() + 1));
            }
        }
    }
}
