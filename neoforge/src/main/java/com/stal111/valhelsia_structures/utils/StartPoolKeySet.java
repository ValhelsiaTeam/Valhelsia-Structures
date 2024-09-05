package com.stal111.valhelsia_structures.utils;

import com.stal111.valhelsia_structures.common.builtin.ResourceKeyHelper;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.valhelsia.valhelsia_core.api.common.world.structure.jigsaw.JigsawBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.UnaryOperator;

/**
 * @author Valhelsia Team
 * @since 2023-03-12
 */
public sealed interface StartPoolKeySet permits StartPoolKeySet.Simple, StartPoolKeySet.WithFurnished {

    String FURNISHED_PREFIX = "furnished";

    static Simple simple(ResourceKeyHelper<StructureTemplatePool> helper, String key) {
        return new Simple(helper.createKey(key));
    }

    static WithFurnished withFurnishedVariant(ResourceKeyHelper<StructureTemplatePool> helper, String key) {
        return new WithFurnished(helper.createKey(key), helper.createKey(FURNISHED_PREFIX + "/" + key));
    }

    void forEachKey(BiConsumer<ResourceKey<StructureTemplatePool>, Boolean> consumer);

    default void create(TemplatePoolHelper helper, BootstrapContext<StructureTemplatePool> context, String folder, UnaryOperator<JigsawBuilder> builder) {
        this.forEachKey((key, furnished) -> {
            helper.create(key, context, furnished ? FURNISHED_PREFIX + "/" + folder : folder, builder);
        });
    }

    default void create(TemplatePoolHelper helper, BootstrapContext<StructureTemplatePool> context, UnaryOperator<JigsawBuilder> builder) {
        this.forEachKey((key, furnished) -> {
            helper.create(key, context, furnished ? FURNISHED_PREFIX : null, builder);
        });
    }

    default void create(TemplatePoolHelper helper, BootstrapContext<StructureTemplatePool> context, String folder, UnaryOperator<JigsawBuilder> builder, @Nullable TerrainAdjustment terrainAdjustment) {
        this.forEachKey((key, furnished) -> {
            helper.create(key, context, furnished ? FURNISHED_PREFIX + "/" + folder : folder, builder, terrainAdjustment);
        });
    }

    default void create(TemplatePoolHelper helper, BootstrapContext<StructureTemplatePool> context, UnaryOperator<JigsawBuilder> builder, @Nullable TerrainAdjustment terrainAdjustment) {
        this.forEachKey((key, furnished) -> {
            helper.create(key, context, furnished ? FURNISHED_PREFIX : null, builder, terrainAdjustment);
        });
    }

    record Simple(ResourceKey<StructureTemplatePool> key) implements StartPoolKeySet {
        @Override
        public void forEachKey(BiConsumer<ResourceKey<StructureTemplatePool>, Boolean> consumer) {
            consumer.accept(this.key(), false);
        }
    }

    record WithFurnished(ResourceKey<StructureTemplatePool> defaultKey, ResourceKey<StructureTemplatePool> furnishedKey) implements StartPoolKeySet {

        @Override
        public void forEachKey(BiConsumer<ResourceKey<StructureTemplatePool>, Boolean> consumer) {
            consumer.accept(this.defaultKey(), false);
            consumer.accept(this.furnishedKey(), true);
        }
    }
}
