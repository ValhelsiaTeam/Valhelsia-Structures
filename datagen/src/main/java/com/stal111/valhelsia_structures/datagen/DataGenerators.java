package com.stal111.valhelsia_structures.datagen;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.datagen.worldgen.structure.ModStructureSets;
import com.stal111.valhelsia_structures.datagen.worldgen.structure.ModStructures;
import com.stal111.valhelsia_structures.datagen.model.ModBlockModels;
import com.stal111.valhelsia_structures.datagen.recipes.ModRecipeProvider;
import com.stal111.valhelsia_structures.datagen.tags.ModBiomeTagsProvider;
import com.stal111.valhelsia_structures.datagen.tags.ModBlockTagsProvider;
import com.stal111.valhelsia_structures.datagen.tags.ModItemTagsProvider;
import com.stal111.valhelsia_structures.datagen.worldgen.modifier.ModBiomeModifiers;
import com.stal111.valhelsia_structures.datagen.worldgen.processors.ModProcessorLists;
import com.stal111.valhelsia_structures.datagen.worldgen.structure.pools.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.valhelsia.valhelsia_core.datagen.DataProviderContext;
import net.valhelsia.valhelsia_core.datagen.model.ValhelsiaModelProvider;
import net.valhelsia.valhelsia_core.datagen.recipes.ValhelsiaRecipeProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        DataProviderContext context = DataProviderContext.of(output, lookupProvider, ValhelsiaStructures.REGISTRY_MANAGER, existingFileHelper);


        generator.addProvider(event.includeClient(), new ValhelsiaModelProvider(context, ModBlockModels::new, null));

        generator.addProvider(event.includeClient(), new ModSoundsProvider(output, existingFileHelper));

        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(context);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(context, blockTagsProvider.contentsGetter()));
       // generator.addProvider(event.includeServer(), new ModStructureTagsProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModBiomeTagsProvider(output, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new ValhelsiaRecipeProvider(context, ModRecipeProvider::new));

        var registrySetBuilder = new RegistrySetBuilder();

        registrySetBuilder.add(Registries.PROCESSOR_LIST, ModProcessorLists::new);
        registrySetBuilder.add(Registries.STRUCTURE_SET, ModStructureSets::new);
        registrySetBuilder.add(Registries.STRUCTURE, ModStructures::new);
        registrySetBuilder.add(Registries.TEMPLATE_POOL, bootstrapContext -> {
            new SimpleStructurePools(bootstrapContext);
            new BigTreePools(bootstrapContext);
            new DesertHousePools(bootstrapContext);
            new PlayerHousePools(bootstrapContext);
            new SpawnerDungeonPools(bootstrapContext);
            new MobPools(bootstrapContext);
        });
        registrySetBuilder.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::new);

        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(output, lookupProvider, registrySetBuilder, Set.of(ValhelsiaStructures.MOD_ID)));
    }
}
