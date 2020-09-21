package com.stal111.valhelsia_structures.event;

import com.stal111.valhelsia_structures.config.StructureGenConfig;
import com.stal111.valhelsia_structures.init.ModStructureFeatures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

/**
 * Biome Loading Listener
 * Valhelsia Structures - com.stal111.valhelsia_structures.event.BiomeLoadingListener
 *
 * @author Valhelsia Team
 * @version 16.0.3
 * @since 2020-09-17
 */

@Mod.EventBusSubscriber
public class BiomeLoadingListener {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onPlayerInteractBlock(BiomeLoadingEvent event) {
        ResourceLocation name = event.getName();
        Biome.Category category = event.getCategory();

        // Check Blacklist
        if (StructureGenConfig.BLACKLISTED_BIOMES.get().contains(name.toString())) {
            return;
        }

        // Add Structures
        if (StructureGenConfig.GENERATE_CASTLES.get() && checkBiome(StructureGenConfig.CASTLE_BIOME_CATEGORIES.get(), StructureGenConfig.CASTLE_BIOME_BLACKLIST.get(), name, category)) {
            event.getGeneration().withStructure(ModStructureFeatures.CASTLE);
        }

        if (StructureGenConfig.GENERATE_CASTLE_RUINS.get() && checkBiome(StructureGenConfig.CASTLE_RUIN_BIOME_CATEGORIES.get(), StructureGenConfig.CASTLE_RUIN_BIOME_BLACKLIST.get(), name, category)) {
            event.getGeneration().withStructure(ModStructureFeatures.CASTLE_RUIN);
        }

        if (StructureGenConfig.GENERATE_FORGES.get() && checkBiome(StructureGenConfig.FORGE_BIOME_CATEGORIES.get(), StructureGenConfig.FORGE_BIOME_BLACKLISTS.get(), name, category)) {
            event.getGeneration().withStructure(ModStructureFeatures.FORGE);
        }

        if (StructureGenConfig.GENERATE_PLAYER_HOUSES.get() && checkBiome(StructureGenConfig.PLAYER_HOUSE_BIOME_CATEGORIES.get(), StructureGenConfig.PLAYER_HOUSE_BIOME_BLACKLIST.get(), name, category)) {
            event.getGeneration().withStructure(ModStructureFeatures.PLAYER_HOUSE);
        }

        if (StructureGenConfig.GENERATE_TOWER_RUINS.get() && checkBiome(StructureGenConfig.TOWER_RUIN_BIOME_CATEGORIES.get(), StructureGenConfig.TOWER_RUIN_BIOME_BLACKLIST.get(), name, category)) {
            event.getGeneration().withStructure(ModStructureFeatures.TOWER_RUIN);
        }

        if (StructureGenConfig.GENERATE_DESERT_HOUSES.get() && checkBiome(StructureGenConfig.DESERT_HOUSE_BIOME_CATEGORIES.get(), StructureGenConfig.DESERT_HOUSE_BIOME_BLACKLIST.get(), name, category)) {
            event.getGeneration().withStructure(ModStructureFeatures.DESERT_HOUSE);
        }

        if (StructureGenConfig.GENERATE_SMALL_DUNGEONS.get() && checkBiome(StructureGenConfig.SMALL_DUNGEON_BIOME_CATEGORIES.get(), StructureGenConfig.SMALL_DUNGEON_BIOME_BLACKLIST.get(), name, category)) {
            event.getGeneration().withStructure(ModStructureFeatures.SMALL_DUNGEON);
        }
    }

    private static boolean checkBiome(List<? extends Biome.Category> allowedBiomeCategories, List<? extends String> blacklistedBiomes, ResourceLocation name, Biome.Category category) {
        boolean flag = allowedBiomeCategories.contains(category.toString()) || allowedBiomeCategories.contains(category);

        if (!blacklistedBiomes.isEmpty() && flag) {
            flag = !blacklistedBiomes.contains(name.toString());
        }

        return flag;
    }
}
