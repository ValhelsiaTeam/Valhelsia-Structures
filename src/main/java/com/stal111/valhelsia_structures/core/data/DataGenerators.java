package com.stal111.valhelsia_structures.core.data;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.data.server.ModBiomeTagsProvider;
import com.stal111.valhelsia_structures.core.data.server.ModBlockTagsProvider;
import com.stal111.valhelsia_structures.core.data.server.ModItemTagsProvider;
import com.stal111.valhelsia_structures.core.data.server.loot.ModBlockLootTables;
import com.stal111.valhelsia_structures.core.data.server.loot.ModLootModifierProvider;
import com.stal111.valhelsia_structures.data.ModSoundsProvider;
import com.stal111.valhelsia_structures.data.recipes.ModRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.valhelsia.valhelsia_core.api.datagen.DataProviderContext;
import net.valhelsia.valhelsia_core.api.datagen.recipes.ValhelsiaRecipeProvider;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Data Generators <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.DataGenerators
 *
 * @author Valhelsia Team
 * @since 2020-11-13
 */
@Mod.EventBusSubscriber(modid = ValhelsiaStructures.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        DataProviderContext context = new DataProviderContext(output, lookupProvider, ValhelsiaStructures.REGISTRY_MANAGER);

        //generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, existingFileHelper));
        //generator.addProvider(event.includeClient(), new ModItemModelProvider(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModSoundsProvider(output, existingFileHelper));

        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(context);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(output, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
       // generator.addProvider(event.includeServer(), new ModStructureTagsProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModBiomeTagsProvider(output, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK))));
        generator.addProvider(event.includeServer(), new ModLootModifierProvider(output));

        generator.addProvider(event.includeServer(), new ValhelsiaRecipeProvider(context, ModRecipeProvider::new));

        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(output, lookupProvider, ValhelsiaStructures.REGISTRY_MANAGER.buildRegistrySet(), Set.of(ValhelsiaStructures.MOD_ID)));
    }
}
