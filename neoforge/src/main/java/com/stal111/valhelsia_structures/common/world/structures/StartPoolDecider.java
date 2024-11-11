package com.stal111.valhelsia_structures.common.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.utils.StartPoolKeySet;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;

/**
 * @author Valhelsia Team
 * @since 2023-03-12
 */
public record StartPoolDecider(Holder<StructureTemplatePool> defaultStartPool, Optional<Holder<StructureTemplatePool>> furnishedStartPool) {

    public static final Codec<StartPoolDecider> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            StructureTemplatePool.CODEC.fieldOf("default").forGetter(StartPoolDecider::defaultStartPool),
            StructureTemplatePool.CODEC.optionalFieldOf("furnished").forGetter(StartPoolDecider::furnishedStartPool)
    ).apply(instance, StartPoolDecider::new));

    public static StartPoolDecider of(HolderGetter<StructureTemplatePool> holderGetter, StartPoolKeySet keySet) {
        return switch (keySet) {
            case StartPoolKeySet.Simple simple -> new StartPoolDecider(holderGetter.getOrThrow(simple.key()), Optional.empty());
            case StartPoolKeySet.WithFurnished withFurnished -> new StartPoolDecider(holderGetter.getOrThrow(withFurnished.defaultKey()), Optional.of(holderGetter.getOrThrow(withFurnished.furnishedKey())));
        };
    }

    public Holder<StructureTemplatePool> decide() {
        if (this.furnishedStartPool.isEmpty() || !ValhelsiaStructures.isFurnitureInstalled()) {
            return this.defaultStartPool;
        }

        return this.furnishedStartPool.get();
    }
}
