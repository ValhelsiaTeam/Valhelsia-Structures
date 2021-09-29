package com.stal111.valhelsia_structures.config;

import com.stal111.valhelsia_structures.init.ModStructures;
import com.stal111.valhelsia_structures.world.structures.AbstractValhelsiaStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import net.valhelsia.valhelsia_core.util.AbstractConfigValidator;
import net.valhelsia.valhelsia_core.world.IValhelsiaStructure;

import java.util.Arrays;

/**
 * Config Validator
 * Valhelsia Structures - com.stal111.valhelsia_structures.config.ConfigValidator
 *
 * @author Valhelsia Team
 * @version 0.1.3
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
                    this.addError(new TranslatableComponent("gui.valhelsia_structures.config.invalid_biome_category").appendString(" " + string), "structures." + structure.getName() + ".biome_categories", new TranslationTextComponent("gui.valhelsia_core.config.ignoring"));
                }
            }

            for (String biome : configEntry.configuredBlacklistedBiomes.get()) {
                if (!biome.contains("*") && !ForgeRegistries.BIOMES.containsKey(new ResourceLocation(biome))) {
                    this.addError(new TranslationTextComponent("gui.valhelsia_structures.config.invalid_blacklisted_biome").appendString(" " + biome), "structures." + structure.getName() + ".blacklisted_biomes", new TranslationTextComponent("gui.valhelsia_core.config.ignoring"));
                }
            }
        }

        for (String biome : StructureGenConfig.BLACKLISTED_BIOMES.get()) {
            if (!biome.contains("*") && !ForgeRegistries.BIOMES.containsKey(new ResourceLocation(biome))) {
                this.addError(new TranslationTextComponent("gui.valhelsia_structures.config.invalid_blacklisted_biome").appendString(" " + biome), "structures.global.blacklisted_biomes", new TranslationTextComponent("gui.valhelsia_core.config.ignoring"));
            }
        }
    }
}
