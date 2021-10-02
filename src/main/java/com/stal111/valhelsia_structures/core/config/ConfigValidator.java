package com.stal111.valhelsia_structures.core.config;

import com.stal111.valhelsia_structures.core.init.ModStructures;
import com.stal111.valhelsia_structures.common.world.structures.AbstractValhelsiaStructure;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.common.world.IValhelsiaStructure;
import net.valhelsia.valhelsia_core.core.config.AbstractConfigValidator;

import java.util.Arrays;

/**
 * Config Validator <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.config.ConfigValidator
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-02-16
 */
public class ConfigValidator extends AbstractConfigValidator {

    @Override
    public void validate() {
        for (IValhelsiaStructure iStructure : ModStructures.MOD_STRUCTURES) {
            AbstractValhelsiaStructure structure = (AbstractValhelsiaStructure) iStructure.getStructure();
            StructureConfigEntry configEntry = structure.getStructureConfigEntry();

            if ((configEntry.configuredSpacing.get() - configEntry.configuredSeparation.get()) <= 0) {
                this.addError(new TranslatableComponent("gui.valhelsia_structures.config.spacing_error"), "structures." + structure.getName() + ".spacing", new TranslatableComponent("gui.valhelsia_structures.config.spacing_solution", configEntry.configuredSeparation.get() + 1));
            }

            for (String string : configEntry.configuredBiomeCategories.get()) {
                if (Arrays.stream(Biome.BiomeCategory.values()).noneMatch(category -> category.getName().equals(string))) {
                    this.addError(new TranslatableComponent("gui.valhelsia_structures.config.invalid_biome_category").append(" " + string), "structures." + structure.getName() + ".biome_categories", new TranslatableComponent("gui.valhelsia_core.config.ignoring"));
                }
            }

            for (String biome : configEntry.configuredBlacklistedBiomes.get()) {
                if (!biome.contains("*") && !ForgeRegistries.BIOMES.containsKey(new ResourceLocation(biome))) {
                    this.addError(new TranslatableComponent("gui.valhelsia_structures.config.invalid_blacklisted_biome").append(" " + biome), "structures." + structure.getName() + ".blacklisted_biomes", new TranslatableComponent("gui.valhelsia_core.config.ignoring"));
                }
            }
        }

        for (String biome : ModConfig.COMMON.blacklistedBiomes.get()) {
            if (!biome.contains("*") && !ForgeRegistries.BIOMES.containsKey(new ResourceLocation(biome))) {
                this.addError(new TranslatableComponent("gui.valhelsia_structures.config.invalid_blacklisted_biome").append(" " + biome), "structures.global.blacklisted_biomes", new TranslatableComponent("gui.valhelsia_core.config.ignoring"));
            }
        }
    }
}
