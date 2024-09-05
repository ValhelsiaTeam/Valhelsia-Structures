package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.StartPoolKeySet;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Valhelsia Team
 * @since 2023-03-12
 */
public record StartPoolDecider(Holder<StructureTemplatePool> defaultStartPool, @Nullable Holder<StructureTemplatePool> furnishedStartPool) {

    public static final Codec<StartPoolDecider> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            StructureTemplatePool.CODEC.fieldOf("default").forGetter(decider -> {
                return decider.defaultStartPool;
            }),
            StructureTemplatePool.CODEC.optionalFieldOf("furnished").forGetter(decider -> {
                return Optional.ofNullable(decider.furnishedStartPool);
            })
    ).apply(instance, (defaultStartPool, furnishedStartPool) -> {
        return new StartPoolDecider(defaultStartPool, furnishedStartPool.orElse(null));
    }));

    public static StartPoolDecider of(HolderGetter<StructureTemplatePool> holderGetter, StartPoolKeySet keySet) {
        return switch (keySet) {
            case StartPoolKeySet.Simple simple -> new StartPoolDecider(holderGetter.getOrThrow(simple.key()), null);
            case StartPoolKeySet.WithFurnished withFurnished -> new StartPoolDecider(holderGetter.getOrThrow(withFurnished.defaultKey()), holderGetter.getOrThrow(withFurnished.furnishedKey()));
        };
    }

    public Holder<StructureTemplatePool> decide() {
        if (this.furnishedStartPool == null || !ValhelsiaStructures.isFurnitureInstalled()) {
            return this.defaultStartPool;
        }

        return this.furnishedStartPool;
    }
}
