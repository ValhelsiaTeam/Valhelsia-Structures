package com.stal111.valhelsia_structures.common.world.structures.height;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;

import java.util.function.Supplier;

/**
 * @author Valhelsia Team
 * @since 2022-10-28
 */
public class DeferredCodec<V> implements Codec<V>{

    private final Supplier<Codec<V>> codec;

    public DeferredCodec(Supplier<Codec<V>> codec) {
        this.codec = codec;
    }

    @Override
    public <T> DataResult<Pair<V, T>> decode(final DynamicOps<T> ops, final T input){
        return this.codec.get().decode(ops, input);
    }

    @Override
    public <T> DataResult<T> encode(final V input, final DynamicOps<T> ops, final T prefix){
        return this.codec.get().encode(input, ops, prefix);
    }
}
