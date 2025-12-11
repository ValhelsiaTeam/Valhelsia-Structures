package com.stal111.valhelsia_structures.utils;

import com.mojang.datafixers.util.Either;
import com.stal111.valhelsia_structures.common.world.structures.pools.ValhelsiaPoolElementWrapper;
import com.stal111.valhelsia_structures.common.world.structures.pools.ValhelsiaSinglePoolElement;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.valhelsia.valhelsia_core.api.common.world.structure.jigsaw.JigsawBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.UnaryOperator;

//TODO: a bit ugly, find a better approach
public class TemplatePoolHelper {

    public static final TemplatePoolHelper INSTANCE = new TemplatePoolHelper();

    private final JigsawBuilder.ElementFunction elementFunction = (resourceLocation, holder, projection, terrainAdjustment) -> {
        return projection1 -> {
            return new ValhelsiaPoolElementWrapper(single(resourceLocation.toString(), holder).apply(projection), terrainAdjustment);
        };
    };

    public static Function<StructureTemplatePool.Projection, ValhelsiaSinglePoolElement> single(String id, Holder<StructureProcessorList> processors) {
        return projection -> new ValhelsiaSinglePoolElement(Either.left(Identifier.parse(id)), processors, projection, Optional.empty());
    }

    public ResourceKey<StructureTemplatePool> createKey(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, ValhelsiaStructures.identifier(name));
    }

    public void create(String name, BootstrapContext<StructureTemplatePool> context, String folder, UnaryOperator<JigsawBuilder> builder) {
        this.create(createKey(name), context, folder, builder, null);
    }

    public void create(ResourceKey<StructureTemplatePool> key, BootstrapContext<StructureTemplatePool> context, String folder, UnaryOperator<JigsawBuilder> builder) {
        this.create(key, context, folder, builder, null);
    }

    public void create(ResourceKey<StructureTemplatePool> key, BootstrapContext<StructureTemplatePool> context, String folder, UnaryOperator<JigsawBuilder> builder, @Nullable TerrainAdjustment terrainAdjustment) {
        builder.apply(JigsawBuilder.builder(key, folder, context, this.elementFunction)).build(ValhelsiaStructures.MOD_ID, terrainAdjustment);
    }

    public void create(String name, BootstrapContext<StructureTemplatePool> context, UnaryOperator<JigsawBuilder> builder) {
        this.create(createKey(name), context, builder, null);
    }

    public void create(ResourceKey<StructureTemplatePool> key, BootstrapContext<StructureTemplatePool> context, UnaryOperator<JigsawBuilder> builder) {
        this.create(key, context, builder, null);
    }

    public void create(ResourceKey<StructureTemplatePool> key, BootstrapContext<StructureTemplatePool> context, UnaryOperator<JigsawBuilder> builder, @Nullable TerrainAdjustment terrainAdjustment) {
        builder.apply(JigsawBuilder.builder(key, context, this.elementFunction)).build(ValhelsiaStructures.MOD_ID, terrainAdjustment);
    }
}
