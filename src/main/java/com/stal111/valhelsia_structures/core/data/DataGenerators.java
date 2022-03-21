package com.stal111.valhelsia_structures.core.data;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import com.stal111.valhelsia_structures.core.data.client.ModBlockStateProvider;
import com.stal111.valhelsia_structures.core.data.client.ModItemModelProvider;
import com.stal111.valhelsia_structures.core.data.server.*;
import com.stal111.valhelsia_structures.core.data.server.loot.ModLootModifierProvider;
import com.stal111.valhelsia_structures.core.data.server.loot.ModLootTableProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

/**
 * Data Generators <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.core.data.DataGenerators
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
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
            generator.addProvider(new ModStructureTagsProvider(generator, existingFileHelper));
            generator.addProvider(new ModBiomeTagsProvider(generator, existingFileHelper));

            generator.addProvider(new ModLootTableProvider(generator));
            generator.addProvider(new ModRecipeProvider(generator));
            generator.addProvider(new ModLootModifierProvider(generator));
        }
    }
}
