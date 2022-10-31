package com.stal111.valhelsia_structures.common.world.structures.jigsaw;

import com.mojang.datafixers.util.Pair;
import com.stal111.valhelsia_structures.common.world.structures.processor.ModProcessors;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Valhelsia Team
 * @since 2022-10-31
 */
public class JigsawBuilder {

    @Nullable
    private final String group;
    private final String path;

    private StructureTemplatePool.Projection projection = StructureTemplatePool.Projection.RIGID;

    private final List<Pair<String, Integer>> elements = new ArrayList<>();
    private final List<StructureProcessor> processors = new ArrayList<>();

    private JigsawBuilder(@NotNull String group, String name) {
        this.group = group;
        this.path = group + "/" + name;
    }

    private JigsawBuilder(String name) {
        this.group = null;
        this.path = name;
    }

    public static JigsawBuilder builder(String group, String name) {
        return new JigsawBuilder(group, name).processor(ModProcessors.STONE_REPLACEMENT_PROCESSOR).processor(ModProcessors.GRASS_BLOCK_REPLACEMENT_PROCESSOR);
    }

    public static JigsawBuilder builder(String name) {
        return new JigsawBuilder(name).processor(ModProcessors.STONE_REPLACEMENT_PROCESSOR).processor(ModProcessors.GRASS_BLOCK_REPLACEMENT_PROCESSOR);
    }

    public JigsawBuilder projection(StructureTemplatePool.Projection projection) {
        this.projection = projection;

        return this;
    }

    public JigsawBuilder element(String location) {
        return this.element(location, 1);
    }

    public JigsawBuilder element(String location, int weight) {
        this.elements.add(Pair.of(this.group != null ? this.group + "/" + location : location, weight));

        return this;
    }

    public JigsawBuilder processor(StructureProcessor processor) {
        this.processors.add(processor);

        return this;
    }

    public JigsawBuilder removeWater() {
        this.processors.add(ModProcessors.REMOVE_WATER_PROCESSOR);

        return this;
    }

    public Holder<StructureTemplatePool> build() {
        List<Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer>> list = new ArrayList<>();

        ResourceLocation location = new ResourceLocation(ValhelsiaStructures.MOD_ID, this.path);

        Holder<StructureProcessorList> holder = BuiltinRegistries.register(BuiltinRegistries.PROCESSOR_LIST, location, new StructureProcessorList(this.processors));

        for (Pair<String, Integer> pair : this.elements) {
            list.add(Pair.of(StructurePoolElement.single(ValhelsiaStructures.MOD_ID + ":" + pair.getFirst(), holder), pair.getSecond()));
        }

        return Pools.register(new StructureTemplatePool(new ResourceLocation(ValhelsiaStructures.MOD_ID, this.path), new ResourceLocation("empty"), list, this.projection));
    }
}
