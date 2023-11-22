package com.stal111.valhelsia_structures.utils;

import net.neoforged.neoforge.common.ModConfigSpec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

/**
 * Configurable Value <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.utils.ConfigurableValue
 *
 * @author Valhelsia Team
 * @version 1.18.2 - 0.1.0
 * @since 2022-03-20
 */
public class ConfigurableValue<D> {

    private final D defaultValue;
    @Nullable private ModConfigSpec.ConfigValue<D> configuredValue;

    public ConfigurableValue(@Nonnull D defaultValue) {
        this(defaultValue, null);
    }

    public ConfigurableValue(@Nonnull D defaultValue, @Nullable ModConfigSpec.ConfigValue<D> configuredValue) {
        this.defaultValue = defaultValue;
        this.configuredValue = configuredValue;
    }

    public static<D> ConfigurableValue<D> of(D defaultValue) {
        return new ConfigurableValue<>(defaultValue);
    }

    public D getDefaultValue() {
        return this.defaultValue;
    }

    @Nullable
    public ModConfigSpec.ConfigValue<D> getConfiguredValue() {
        return this.configuredValue;
    }

    public void setConfiguredValue(ModConfigSpec.ConfigValue<D> configuredValue) {
        this.setConfiguredValue(d -> configuredValue);
    }

    public void setConfiguredValue(Function<D, ModConfigSpec.ConfigValue<D>> configValueFunction) {
        this.configuredValue = configValueFunction.apply(this.getDefaultValue());
    }

    public D get() {
        if (this.getConfiguredValue() != null) {
            return this.getConfiguredValue().get();
        }

        return this.getDefaultValue();
    }
}
