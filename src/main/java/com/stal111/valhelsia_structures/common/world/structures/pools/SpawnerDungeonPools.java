package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.valhelsia.valhelsia_core.core.registry.RegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.TemplatePoolRegistryHelper;


/**
 * @author Valhelsia Team
 */
public class SpawnerDungeonPools implements RegistryClass {

    public static final TemplatePoolRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registries.TEMPLATE_POOL);

    public static final ResourceKey<StructureTemplatePool> START = HELPER.createKey("spawner_dungeon/cave_entrances");

    public static void bootstrap(BootstapContext<StructureTemplatePool> context) {
        HELPER.createPool(context, "spawner_dungeon/cave_entrance", "spawner_dungeon/cave_entrances", builder -> builder.element("large").element("small_1").element("small_2").element("small_3").removeWater(), TerrainAdjustment.BEARD_THIN);
        HELPER.createPool(context, "spawner_dungeon/cave_entrance", "spawner_dungeon/cave_entrance/large_bottom", builder -> builder.element("large_bottom").removeWater());
        HELPER.createPool(context, "spawner_dungeon/cave_entrance", "spawner_dungeon/cave_entrance/small_1_bottom", builder -> builder.element("small_1_bottom").removeWater());
        HELPER.createPool(context, "spawner_dungeon/cave_entrance", "spawner_dungeon/cave_entrance/small_2_bottom", builder -> builder.element("small_2_bottom").removeWater());
        HELPER.createPool(context, "spawner_dungeon/cave_entrance", "spawner_dungeon/cave_entrance/small_3_bottom", builder -> builder.element("small_3_bottom").removeWater());
        HELPER.createPool(context, "spawner_dungeon/cave", "spawner_dungeon/caves", builder -> builder.element("cave_1").element("cave_2").removeWater());
        HELPER.createPool(context, "spawner_dungeon/entrance", "spawner_dungeon/entrances", builder -> builder.element("entrance_1").element("entrance_2").removeWater());
        HELPER.createPool(context, "spawner_dungeon/main_room", "spawner_dungeon/main_rooms", builder -> builder.element("main_room_1").element("main_room_2").element("main_room_3").removeWater());
        HELPER.createPool(context, "spawner_dungeon/side_room", "spawner_dungeon/side_rooms", builder -> builder.element("side_room_1").element("side_room_2").element("side_room_3").element("side_room_4").removeWater());
        HELPER.createPool(context, "spawner_dungeon/spawner_room", "spawner_dungeon/spawner_rooms", builder -> builder.element("zombie_spawner_room").element("skeleton_spawner_room").element("spider_spawner_room").removeWater());

        HELPER.createPool(context, "spawner_dungeon/stairs", "spawner_dungeon/stairs", builder -> builder.element("stairs_1").element("stairs_2").element("stairs_3").element("stairs_4").element("stairs_5").element("stairs_6").removeWater());
        HELPER.createPool(context, "spawner_dungeon/passage", "spawner_dungeon/passages", builder -> builder.element("passage_1").element("passage_2").element("passage_3").element("passage_4").element("passage_5").element("passage_6").removeWater());
    }
}