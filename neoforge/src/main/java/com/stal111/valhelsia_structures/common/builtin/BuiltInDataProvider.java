package com.stal111.valhelsia_structures.common.builtin;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public abstract class BuiltInDataProvider<T> {

    private final ResourceKey<? extends Registry<T>> registry;

    protected BuiltInDataProvider(ResourceKey<? extends Registry<T>> registry) {this.registry = registry;}

    public ResourceKey<T> createKey(String name) {
        return ResourceKey.create(this.registry, ValhelsiaStructures.location(name));
    }
}
