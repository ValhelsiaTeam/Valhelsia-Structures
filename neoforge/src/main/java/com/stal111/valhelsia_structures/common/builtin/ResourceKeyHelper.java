package com.stal111.valhelsia_structures.common.builtin;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class ResourceKeyHelper<T> {
    private final ResourceKey<? extends Registry<T>> registry;

    private ResourceKeyHelper(ResourceKey<? extends Registry<T>> registry) {
        this.registry = registry;
    }

    public static <T> ResourceKeyHelper<T> create(ResourceKey<? extends Registry<T>> registry) {
        return new ResourceKeyHelper<>(registry);
    }

    public ResourceKey<T> createKey(String name) {
        return ResourceKey.create(this.registry, ValhelsiaStructures.location(name));
    }
}
