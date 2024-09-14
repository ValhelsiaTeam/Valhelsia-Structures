package com.stal111.valhelsia_structures.datagen

import com.stal111.valhelsia_structures.core.ValhelsiaStructures
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.data.event.GatherDataEvent
import net.valhelsia.dataforge.DataForge

@Mod(ValhelsiaStructures.MOD_ID)
class DataGenerators(eventBus: IEventBus) {

    init {
        DataForge.setup(ProviderCollector(), eventBus)
    }

    fun gatherData(event: GatherDataEvent?) {

        //DataForge.INSTANCE.setCollector(new Collector());
//        DataGenerator generator = event.getGenerator();
//        PackOutput output = generator.getPackOutput();
//        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
//        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
//
//        DataProviderContext context = DataProviderContext.of(output, lookupProvider, ValhelsiaStructures.REGISTRY_MANAGER, existingFileHelper);
//
//
//        generator.addProvider(event.includeClient(), new ValhelsiaModelProvider(context, ModBlockModels::new, null));
//
//        generator.addProvider(event.includeClient(), new ModSoundsProvider(output, existingFileHelper));
//
//        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(context);
//        generator.addProvider(event.includeServer(), blockTagsProvider);
//        generator.addProvider(event.includeServer(), new ModItemTagsProvider(context, blockTagsProvider.contentsGetter()));
//       // generator.addProvider(event.includeServer(), new ModStructureTagsProvider(output, lookupProvider, existingFileHelper));
//        generator.addProvider(event.includeServer(), new ModBiomeTagsProvider(output, lookupProvider, existingFileHelper));
//
//        generator.addProvider(event.includeServer(), new ValhelsiaRecipeProvider(context, ModRecipeProvider::new));
//
//        var registrySetBuilder = new RegistrySetBuilder();
//
//        registrySetBuilder.add(Registries.PROCESSOR_LIST, ModProcessorLists::new);
//        registrySetBuilder.add(Registries.STRUCTURE_SET, ModStructureSets::new);
//        registrySetBuilder.add(Registries.STRUCTURE, ModStructures::new);
//        registrySetBuilder.add(Registries.TEMPLATE_POOL, bootstrapContext -> {
//            new SimpleStructurePools(bootstrapContext);
//            new BigTreePools(bootstrapContext);
//            new DesertHousePools(bootstrapContext);
//            new PlayerHousePools(bootstrapContext);
//            new SpawnerDungeonPools(bootstrapContext);
//            new MobPools(bootstrapContext);
//        });
//        registrySetBuilder.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::new);
//
//        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(output, lookupProvider, registrySetBuilder, Set.of(ValhelsiaStructures.MOD_ID)));
    }
}
