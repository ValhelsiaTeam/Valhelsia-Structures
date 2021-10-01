package com.stal111.valhelsia_structures.data;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.data.client.ModBlockStateProvider;
import com.stal111.valhelsia_structures.data.client.ModItemModelProvider;
import com.stal111.valhelsia_structures.data.server.ModBlockTagsProvider;
import com.stal111.valhelsia_structures.data.server.ModItemTagsProvider;
import com.stal111.valhelsia_structures.data.server.loot.ModLootModifierProvider;
import com.stal111.valhelsia_structures.data.server.loot.ModLootTableProvider;
import com.stal111.valhelsia_structures.data.server.ModRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

/**
 * Data Generators
 * Valhelsia Structures - com.stal111.valhelsia_structures.data.DataGenerators
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2020-11-13
 */
@Mod.EventBusSubscriber(modid = ValhelsiaStructures.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        if (event.includeClient()) {
            generator.addProvider(new ModBlockStateProvider(generator, existingFileHelper));
            generator.addProvider(new ModItemModelProvider(generator, existingFileHelper));
        }

        if (event.includeServer()) {
            ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(generator, existingFileHelper);
            generator.addProvider(blockTagsProvider);
            generator.addProvider(new ModItemTagsProvider(generator, blockTagsProvider, existingFileHelper));

            generator.addProvider(new ModLootTableProvider(generator));
            generator.addProvider(new ModRecipeProvider(generator));
            generator.addProvider(new ModLootModifierProvider(generator));
        }
    }
}
